package com.example.shagee.unipoint;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shagee.unipoint.Model.Offer;

import java.util.ArrayList;
import java.util.List;

public class RunningOffersAdapter extends RecyclerView.Adapter<RunningOffersAdapter.ViewHolder> {

    private Context ctx;
    private List<Offer> runningOffers = new ArrayList<>();

    public RunningOffersAdapter(Context ctx, List<Offer> runningOffers) {
        this.ctx = ctx;
        this.runningOffers = runningOffers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView offerHeading;
        public ImageView offerImageView;
        public View offerView;


        public ViewHolder(View v) {
            super(v);
            offerHeading = (TextView) v.findViewById(R.id.offerHeadingTV);
            offerImageView = (ImageView) v.findViewById(R.id.offerIV);
            offerView = (ConstraintLayout) v.findViewById(R.id.offersRow);
        }
    }

    @Override
    public RunningOffersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.running_offers_row, parent, false);
        ViewHolder vh = new RunningOffersAdapter.ViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(RunningOffersAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return runningOffers.size();
    }

}



