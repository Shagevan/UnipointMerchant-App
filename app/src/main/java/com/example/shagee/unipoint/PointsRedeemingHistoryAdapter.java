package com.example.shagee.unipoint;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shagee.unipoint.Model.TransactionHistory;

import java.util.ArrayList;
import java.util.List;

public class PointsRedeemingHistoryAdapter extends RecyclerView.Adapter<PointsRedeemingHistoryAdapter.ViewHolder> {

    private List<TransactionHistory> redemingHistories = new ArrayList<>();
    private Context ctx;

    public PointsRedeemingHistoryAdapter(Context ctx, List<TransactionHistory> redemingHistories) {
        this.ctx = ctx;
        this.redemingHistories = redemingHistories;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView phoneNumber;
        public TextView pointsAwarded;
        public TextView billValueBefore;
        public TextView billValueAfter;
        public TextView invoiceNumber;
        public TextView date;

        public ViewHolder(View v) {
            super(v);
            phoneNumber = (TextView) v.findViewById(R.id.LastTransactionPhoneNumber);
            pointsAwarded = (TextView) v.findViewById(R.id.LastTransactionPointsAwarded);
            invoiceNumber = (TextView) v.findViewById(R.id.LastTransactionInvoiceNumber);
            billValueBefore = (TextView) v.findViewById(R.id.LastTransactionBillValue);
            billValueAfter = (TextView) v.findViewById(R.id.LastTransactionBillValue);
            date = (TextView) v.findViewById(R.id.LastTransactionDate);
        }
    }

    @Override
    public PointsRedeemingHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_redeeming_history_row, parent, false);
        ViewHolder vh = new PointsRedeemingHistoryAdapter.ViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return redemingHistories.size();
    }

}

