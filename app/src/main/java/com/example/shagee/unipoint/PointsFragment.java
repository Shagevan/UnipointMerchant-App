package com.example.shagee.unipoint;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText redeemPtsInvoiceNoET;
    private EditText redeemPtsNoOfPtsET;
    private Button addPointsBtn;
    private Button redeemPointsBtn;
    private RecyclerView LastTransactionRecyclerViewer;
    private RecyclerView PointsRedeemingHistoryRecyclerViewer;
    private GridLayoutManager lastTransactionGridManager;
    private GridLayoutManager PointsRedeemingHistoryGridManager;
    private List<TransactionHistory> lastTransactions = new ArrayList<>();
    private LastTransactionAdapter lastTransactionAdapter;
    private UnipointMerchantInterface unipointMerchantService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pointsFragmentView = inflater.inflate(R.layout.fragment_points, container, false);

        // INITIALIZE CLIENT
        unipointMerchantService = UnipointMerchantClient.getClient().create(UnipointMerchantInterface.class);

        // INITIALIZE VIEWS
        addPtsInvoiceNoET = (EditText) pointsFragmentView.findViewById(R.id.PointsFragmentInvoiceNumET);
        redeemPtsInvoiceNoET = (EditText) pointsFragmentView.findViewById(R.id.RedeemPointsInvoiceNumET);
        redeemPtsNoOfPtsET = (EditText) pointsFragmentView.findViewById(R.id.RedeemPointsNoOfPointsET);
        addPointsBtn = (Button) pointsFragmentView.findViewById(R.id.PointsFragmentAddPointsBtn);
        redeemPointsBtn = (Button) pointsFragmentView.findViewById(R.id.RedeemPointsBtn);
        LastTransactionRecyclerViewer = (RecyclerView) pointsFragmentView.findViewById(R.id.LastTransactionRecyclerViewer);
        PointsRedeemingHistoryRecyclerViewer = (RecyclerView) pointsFragmentView.findViewById(R.id.PointsRedeemingHistoryRecyclerViewer);
        lastTransactionGridManager = new GridLayoutManager(getContext(),1);
        PointsRedeemingHistoryGridManager = new GridLayoutManager(getContext(), 1);
        lastTransactionAdapter = new LastTransactionAdapter(getContext(), lastTransactions);
        LastTransactionRecyclerViewer.setLayoutManager(lastTransactionGridManager);
        PointsRedeemingHistoryRecyclerViewer.setLayoutManager(PointsRedeemingHistoryGridManager);
        LastTransactionRecyclerViewer.setAdapter(lastTransactionAdapter);
        PointsRedeemingHistoryRecyclerViewer.setAdapter(lastTransactionAdapter);

        getTransactionHistory();

        addPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getContext().getSharedPreferences("MerchantUserInfo", 0);
                SharedPreferences pref1 = getContext().getSharedPreferences("CustomerDetails", 0);
                String merchantUserRefId = pref.getString("merchantUserRefId", "");
                String outletRefId = pref.getString("outletRefId", "");
                String unipointCustomerRefId = pref1.getString("unipointCustomerRefId", "");
                String customerMobileNumber = pref1.getString("customerMobileNumber", "");
                if(!addPtsInvoiceNoET.getText().toString().isEmpty()){
                    addPoints(customerMobileNumber,Integer.parseInt(addPtsInvoiceNoET.getText().toString()),
                            Integer.parseInt(merchantUserRefId),Integer.parseInt(outletRefId));
                }
                else{
                    Toast.makeText(getContext(), "Enter Invoice Number", Toast.LENGTH_LONG).show();
                }
            }
        });

        redeemPointsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getContext().getSharedPreferences("MerchantUserInfo", 0);
                SharedPreferences pref1 = getContext().getSharedPreferences("CustomerDetails", 0);
                String merchantUserRefId = pref.getString("merchantUserRefId", "");
                String outletRefId = pref.getString("outletRefId", "");
                String unipointCustomerRefId = pref1.getString("unipointCustomerRefId", "");
                String customerMobileNumber = pref1.getString("customerMobileNumber", "");

                if(!redeemPtsInvoiceNoET.getText().toString().isEmpty() && !redeemPtsNoOfPtsET.getText().toString().isEmpty()){
                    redeemPoints(customerMobileNumber,Integer.parseInt(redeemPtsInvoiceNoET.getText().toString()),
                            Double.parseDouble(redeemPtsNoOfPtsET.getText().toString()),
                            Integer.parseInt(merchantUserRefId),Integer.parseInt(outletRefId));
                }
                else{
                    Toast.makeText(getContext(), "Fill all the Fields", Toast.LENGTH_LONG).show();
                }

            }
        });

        return pointsFragmentView;
    }

    public void getTransactionHistory(){
        Call<TransctionHistoryResponse> call = unipointMerchantService.getTransactionHistory(32,1);
        call.enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
                Log.d(TAG, "success");
                lastTransactions.clear();
                lastTransactions.addAll(response.body().getTransactionHistory());
                lastTransactionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

    public void addPoints(String customerMobileNumber, int invoiceNumber, int merchantUserRefId, int outletRefId){
        Call<TransctionHistoryResponse> call = unipointMerchantService.addFromInvoice(customerMobileNumber,
                invoiceNumber,merchantUserRefId,outletRefId );
        call.enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
                Log.d(TAG, "success");
                lastTransactions.clear();
                lastTransactions.addAll(response.body().getTransactionHistory());
                lastTransactionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

    public void redeemPoints(String customerMobileNumber, int invoiceNumber, double points, int merchantUserRefId, int outletRefId){
        Call<TransctionHistoryResponse> call = unipointMerchantService.redeemPoints(customerMobileNumber,
                invoiceNumber,points,merchantUserRefId,outletRefId );
        call.enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
                Log.d(TAG, "success");
                lastTransactions.clear();
                lastTransactions.addAll(response.body().getTransactionHistory());
                lastTransactionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

}
