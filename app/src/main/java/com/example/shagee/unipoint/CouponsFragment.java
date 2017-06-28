package com.example.shagee.unipoint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.shagee.unipoint.Model.TransactionHistory;
import com.example.shagee.unipoint.RestClient.PostgrestClient;
import com.example.shagee.unipoint.RestClient.PostgrestInterface;
import com.example.shagee.unipoint.RestClient.UnipointMerchantClient;
import com.example.shagee.unipoint.RestClient.UnipointMerchantInterface;

import java.util.ArrayList;
import java.util.List;

public class CouponsFragment extends Fragment{

        private EditText coupNameET;
        private EditText coupCodeET;
        private Button releaseCoupBtn;
        private RecyclerView couponClaimingHistoryRecyclerViewer;
        private GridLayoutManager couClaimingHistGridManager;
        private List<TransactionHistory> lastTransactions = new ArrayList<>();
        private LastTransactionAdapter lastTransactionAdapter;
        private PostgrestInterface postgrestService;
        private UnipointMerchantInterface unipointMerchantService;


        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View couponFragmentView = inflater.inflate(R.layout.fragment_coupons, container, false);

            // INITIALIZE CLIENT
            postgrestService = PostgrestClient.getClient().create(PostgrestInterface.class);
            unipointMerchantService = UnipointMerchantClient.getClient().create(UnipointMerchantInterface.class);

            // INITIALIZE VIEWS
            coupNameET = (EditText) couponFragmentView.findViewById(R.id.OffersFragmentOfferNameET);
            coupCodeET = (EditText) couponFragmentView.findViewById(R.id.OffersFragmentOfferCodeET);
            releaseCoupBtn = (Button) couponFragmentView.findViewById(R.id.OffersFragmentReleaseOfferBtn);
            couponClaimingHistoryRecyclerViewer = (RecyclerView) couponFragmentView.findViewById(R.id.CouponClaimingHistoryRecyclerViewer);
            couClaimingHistGridManager = new GridLayoutManager(getContext(),1);
            lastTransactionAdapter = new LastTransactionAdapter(getContext(), lastTransactions);
            //couponClaimingHistoryRecyclerViewer.hasFixedSize();
            couponClaimingHistoryRecyclerViewer.setLayoutManager(couClaimingHistGridManager);
            couponClaimingHistoryRecyclerViewer.setAdapter(lastTransactionAdapter);


        return couponFragmentView;
    }

}
