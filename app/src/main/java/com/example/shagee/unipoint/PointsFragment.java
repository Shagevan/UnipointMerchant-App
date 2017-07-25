package com.example.shagee.unipoint;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shagee.unipoint.Model.TransactionHistory;
import com.example.shagee.unipoint.Model.TransctionHistoryResponse;
import com.example.shagee.unipoint.RestClient.UnipointMerchantClient;
import com.example.shagee.unipoint.RestClient.UnipointMerchantInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PointsFragment extends Fragment {

    private EditText addPtsPhoneNoET;
    private EditText addPtsInvoiceNoET;
    private EditText redeemPtsPhoneNoET;
    private EditText redeemPtsBillAmountET;
    private EditText redeemPtsNoOfPtsET;
    private ImageButton addPointsBtn;
    private ImageButton redeemPointsBtn;
    private RecyclerView LastTransactionRecyclerViewer;
    private RecyclerView PointsRedeemingHistoryRecyclerViewer;
    private GridLayoutManager lastTransactionGridManager;
    private GridLayoutManager PointsRedeemingHistoryGridManager;
    private List<TransactionHistory> lastTransactions = new ArrayList<>();
    private LastTransactionAdapter lastTransactionAdapter;
    private UnipointMerchantInterface unipointMerchantService;
    private String merchantUserRefId;
    private String outletRefId;
    private RadioGroup radioGroup;
    private RadioButton selectBillAmountRB;
    private RadioButton selectInvoiceNoRB;
    private boolean isBillValueSelected;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pointsFragmentView = inflater.inflate(R.layout.fragment_points, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();

        // INITIALIZE CLIENT
        unipointMerchantService = UnipointMerchantClient.getClient().create(UnipointMerchantInterface.class);

        SharedPreferences pref = getContext().getSharedPreferences("MerchantUserInfo", 0);
        merchantUserRefId = pref.getString("merchantUserRefId", "");

        // INITIALIZE VIEWS
        addPtsInvoiceNoET = (EditText) pointsFragmentView.findViewById(R.id.PointsFragmentInvoiceNumET);
        redeemPtsBillAmountET = (EditText) pointsFragmentView.findViewById(R.id.RedeemPointsInvoiceNumET);
        redeemPtsNoOfPtsET = (EditText) pointsFragmentView.findViewById(R.id.RedeemPointsNoOfPointsET);
        addPointsBtn = (ImageButton) pointsFragmentView.findViewById(R.id.PointsFragmentAddPointsBtn);
        redeemPointsBtn = (ImageButton) pointsFragmentView.findViewById(R.id.RedeemPointsBtn);
        radioGroup = (RadioGroup) pointsFragmentView.findViewById(R.id.addPointsRadioGroup);
        selectBillAmountRB = (RadioButton) pointsFragmentView.findViewById(R.id.addPointsRadioBillValue);
        selectInvoiceNoRB = (RadioButton) pointsFragmentView.findViewById(R.id.addPointsRadioInvoiceNo);
        LastTransactionRecyclerViewer = (RecyclerView) pointsFragmentView.findViewById(R.id.LastTransactionRecyclerViewer);
        PointsRedeemingHistoryRecyclerViewer = (RecyclerView) pointsFragmentView.findViewById(R.id.PointsRedeemingHistoryRecyclerViewer);
        lastTransactionGridManager = new GridLayoutManager(getContext(),1);
        PointsRedeemingHistoryGridManager = new GridLayoutManager(getContext(), 1);
        lastTransactionAdapter = new LastTransactionAdapter(getContext(), lastTransactions);
        LastTransactionRecyclerViewer.setLayoutManager(lastTransactionGridManager);
        PointsRedeemingHistoryRecyclerViewer.setLayoutManager(PointsRedeemingHistoryGridManager);
        LastTransactionRecyclerViewer.setAdapter(lastTransactionAdapter);
        PointsRedeemingHistoryRecyclerViewer.setAdapter(lastTransactionAdapter);

        radioGroup.check(selectInvoiceNoRB.getId());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.addPointsRadioBillValue){
                    isBillValueSelected = true;
                    addPtsInvoiceNoET.setHint("Bill Value");
                }
                else if(i == R.id.addPointsRadioInvoiceNo){
                    isBillValueSelected = false;
                    addPtsInvoiceNoET.setHint("Invoice Number");
                }
            }
        });


        if(!merchantUserRefId.isEmpty()){
            getTransactionHistory(merchantUserRefId);
        }

        addPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref1 = getContext().getSharedPreferences("CustomerDetails", 0);
                String customerMobileNumber = pref1.getString("customerMobileNumber", "");
                if(!addPtsInvoiceNoET.getText().toString().isEmpty()){
                    if(isBillValueSelected){
                        addPointsFromAmount(customerMobileNumber,addPtsInvoiceNoET.getText().toString(),merchantUserRefId,"Cash");
                    }
                    else{
                        addPointsFromInvoice(customerMobileNumber,addPtsInvoiceNoET.getText().toString(),merchantUserRefId);
                    }

                }
                else{
                    Toast.makeText(getContext(), "Enter Invoice Number", Toast.LENGTH_LONG).show();
                }
            }
        });

        redeemPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref1 = getContext().getSharedPreferences("CustomerDetails", 0);
                String customerMobileNumber = pref1.getString("customerMobileNumber", "");

                if(!redeemPtsBillAmountET.getText().toString().isEmpty() && !redeemPtsNoOfPtsET.getText().toString().isEmpty()){
                    redeemPoints(customerMobileNumber, redeemPtsBillAmountET.getText().toString(),
                            redeemPtsNoOfPtsET.getText().toString(), merchantUserRefId, "Cash");
                }
                else{
                    Toast.makeText(getContext(), "Fill all the Fields", Toast.LENGTH_LONG).show();
                }

            }
        });

        return pointsFragmentView;
    }

    public void getTransactionHistory(String merchantUserRefId){
        Call<TransctionHistoryResponse> call = unipointMerchantService.getTransactionHistory(merchantUserRefId);
        call.enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
                Log.d(TAG, "success");
                if(response.body().getTransactionHistory() != null){
                    lastTransactions.clear();
                    lastTransactions.addAll(response.body().getTransactionHistory());
                    lastTransactionAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

    public void addPointsFromInvoice(String customerMobileNumber, String invoiceNumber, String merchantUserRefId){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait ...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();
        Call<TransctionHistoryResponse> call = unipointMerchantService.addFromInvoice(customerMobileNumber,
                invoiceNumber,merchantUserRefId);
        call.enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
                Log.d(TAG, "success");
                if(response.body().getTransactionHistory() != null){
                    lastTransactions.clear();
                    lastTransactions.addAll(response.body().getTransactionHistory());
                    lastTransactionAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

    public void addPointsFromAmount(String customerMobileNumber, String billAmount, String merchantUserRefId, String transactionType){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait ...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();
        Call<TransctionHistoryResponse> call = unipointMerchantService.addPointsFromAmount(customerMobileNumber,
                billAmount,merchantUserRefId,transactionType);
        call.enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
                Log.d(TAG, "success");
                if(response.body().getTransactionHistory() != null){
                    lastTransactions.clear();
                    lastTransactions.addAll(response.body().getTransactionHistory());
                    lastTransactionAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

    public void redeemPoints(String customerMobileNumber, String billValue, String points, String merchantUserRefId, String transactionType){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait ...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();
        Call<TransctionHistoryResponse> call = unipointMerchantService.redeemPoints(customerMobileNumber,
                billValue,points,merchantUserRefId,transactionType );
        call.enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
                Log.d(TAG, "success");
                if(response.body().getTransactionHistory() != null){
                    lastTransactions.clear();
                    lastTransactions.addAll(response.body().getTransactionHistory());
                    lastTransactionAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

}
