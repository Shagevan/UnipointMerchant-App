package com.example.shagee.unipoint;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shagee.unipoint.Model.Offer;
import com.example.shagee.unipoint.Model.OfferClaimHistory;
import com.example.shagee.unipoint.Model.OfferClaimHistoryResponse;
import com.example.shagee.unipoint.RestClient.PostgrestClient;
import com.example.shagee.unipoint.RestClient.PostgrestInterface;
import com.example.shagee.unipoint.RestClient.UnipointMerchantClient;
import com.example.shagee.unipoint.RestClient.UnipointMerchantInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class OffersFragment extends Fragment {

    private EditText offerNameET;
    private EditText offerCodeET;
    private Button releaseOfferBtn;
    private RecyclerView offerClaimingHistoryRecyclerViewer;
    private RecyclerView upAndRunningOffRecyclerViewer;
    private GridLayoutManager ClaimedOffersGridManager;
    private LinearLayoutManager UppAndRunningOffLinearManager;
    private TextView InfoOnOfferTV;
    private TextView AplicableToTV;
    private TextView OfferExpTV;
    private TextView totalExpressionTV;

    private List<OfferClaimHistory> offerClaimingHistories = new ArrayList<>();
    private List<Offer> runningOffers = new ArrayList<>();
    private OfferClaimingHistoryAdapter offerClaimingHistoryAdapter;
    private RunningOffersAdapter runningOffersAdapter;
    private PostgrestInterface postgrestService;
    private UnipointMerchantInterface unipointMerchantService;
    private Offer loadedOffer;
    private String merchantUserRefId;
    private  String outletRefId;
    private String merchantRefId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View offerFragmentView = inflater.inflate(R.layout.fragment_offers, container, false);

        // INITIALIZE CLIENT
        postgrestService = PostgrestClient.getClient().create(PostgrestInterface.class);
        unipointMerchantService = UnipointMerchantClient.getClient().create(UnipointMerchantInterface.class);

        SharedPreferences pref = getContext().getSharedPreferences("MerchantUserInfo", 0);
        merchantUserRefId = pref.getString("merchantUserRefId", "");
        outletRefId = pref.getString("outletRefId", "");
        merchantRefId = pref.getString("merchantRefId", "");

        // INITIALIZE VIEWS
        offerNameET = (EditText) offerFragmentView.findViewById(R.id.OffersFragmentOfferNameET);
        offerCodeET = (EditText) offerFragmentView.findViewById(R.id.OffersFragmentOfferCodeET);
        releaseOfferBtn = (Button) offerFragmentView.findViewById(R.id.OffersFragmentReleaseOfferBtn);
        InfoOnOfferTV = (TextView) offerFragmentView.findViewById(R.id.InfoOnOfferTV);
        AplicableToTV = (TextView) offerFragmentView.findViewById(R.id.OfferFragmentAplicableToTV);
        OfferExpTV = (TextView) offerFragmentView.findViewById(R.id.OfferFragmentOfferExpTV);
        totalExpressionTV = (TextView) offerFragmentView.findViewById(R.id.OfferFragmentTotExpressionTV);

        offerClaimingHistoryRecyclerViewer = (RecyclerView) offerFragmentView.findViewById(R.id.ClaimedOffersRecyclerViewer);
        upAndRunningOffRecyclerViewer = (RecyclerView) offerFragmentView.findViewById(R.id.UppAndRunningOffRecyclerViewer);

        ClaimedOffersGridManager = new GridLayoutManager(getContext(),1);
        UppAndRunningOffLinearManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        offerClaimingHistoryAdapter = new OfferClaimingHistoryAdapter(getContext(), offerClaimingHistories);

        runningOffersAdapter = new RunningOffersAdapter(getContext(),runningOffers){
            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.offerHeading.setText(runningOffers.get(position).getOfferSubHeading());
                holder.offerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadedOffer = runningOffers.get(position);
                        offerNameET.setText(runningOffers.get(position).getOfferSubHeading());
                    }
                });
            }
        };

        offerClaimingHistoryRecyclerViewer.setLayoutManager(ClaimedOffersGridManager);
        upAndRunningOffRecyclerViewer.setLayoutManager(UppAndRunningOffLinearManager);
        offerClaimingHistoryRecyclerViewer.setAdapter(offerClaimingHistoryAdapter);
        upAndRunningOffRecyclerViewer.setAdapter(runningOffersAdapter);

        releaseOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loadedOffer != null && loadedOffer.getOfferRedemtionCode().equals( offerCodeET.getText().toString())){
                    SharedPreferences pref = getContext().getSharedPreferences("MerchantUserInfo", 0);
                    SharedPreferences pref1 = getContext().getSharedPreferences("CustomerDetails", 0);
                    String merchantUserRefId = pref.getString("merchantUserRefId", "");
                    String outletRefId = pref.getString("outletRefId", "");
                    String unipointCustomerRefId = pref1.getString("unipointCustomerRefId", "");

                    releaseOffer(String.valueOf(loadedOffer.getOfferId()),merchantUserRefId,
                            unipointCustomerRefId,String.valueOf(loadedOffer.getPointsAllocated()));
                }
                else {
                    Toast.makeText(getContext(), "Incorrect Redemption code", Toast.LENGTH_LONG).show();
                }
            }
        });

        loadUppAndRunningOffers(merchantRefId);
        loadOfferClaimingHistories(merchantUserRefId);

        return offerFragmentView;
    }

    public void loadOfferClaimingHistories(String merchantUserRefId){
        Call<OfferClaimHistoryResponse> call = unipointMerchantService.getOfferClaimingHistory(merchantUserRefId);
        call.enqueue(new Callback<OfferClaimHistoryResponse>() {
            @Override
            public void onResponse(Call<OfferClaimHistoryResponse> call, Response<OfferClaimHistoryResponse> response) {
                Log.d(TAG, "success");
                if(response.body().getOfferClaimHistory() != null){
                    offerClaimingHistories.clear();
                    offerClaimingHistories.addAll(response.body().getOfferClaimHistory());
                    offerClaimingHistoryAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<OfferClaimHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

    public void releaseOffer(String offerRefId, String merchantUserRefId, String unipointCustomerRefId, String pointsAdded){
        Call<OfferClaimHistoryResponse> call = unipointMerchantService.releaseOffer(offerRefId, merchantUserRefId,unipointCustomerRefId,pointsAdded);
        call.enqueue(new Callback<OfferClaimHistoryResponse>() {
            @Override
            public void onResponse(Call<OfferClaimHistoryResponse> call, Response<OfferClaimHistoryResponse> response) {
                Log.d(TAG, "success");
                if(response.body().getOfferClaimHistory() != null){
                    offerClaimingHistories.clear();
                    offerClaimingHistories.addAll(response.body().getOfferClaimHistory());
                    offerClaimingHistoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<OfferClaimHistoryResponse> call, Throwable t) {
                Log.d(TAG, "Failed");
            }
        });
    }

    public void loadUppAndRunningOffers(String merchantRefId){
        Call<List<Offer>> call = postgrestService.getUpAndRunningOffers("eq."+merchantRefId);
        call.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                Log.d(TAG, "success");
                List<Offer> tempRunningOffers = response.body();
                if(tempRunningOffers != null){
                    runningOffers.clear();
              /*Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());
                try {
                    Date currentDate = df.parse(formattedDate);
                    for (Offer offer:tempRunningOffers) {
                        String sExpDate = offer.getExpiryDate();
                        Date expDate = df.parse(sExpDate);
                        runningOffers.add(offer);
                        if(expDate.after(currentDate)){
                            runningOffers.add(offer);
                        }
                    }*/
                    runningOffers.addAll(response.body());
                    runningOffersAdapter.notifyDataSetChanged();
                    //} catch (ParseException e) {
                    //    e.printStackTrace();
                    //}
                }

            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t)
            {
                Log.d(TAG, "Failed");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUppAndRunningOffers(merchantRefId);
        loadOfferClaimingHistories(merchantUserRefId);
    }
}
