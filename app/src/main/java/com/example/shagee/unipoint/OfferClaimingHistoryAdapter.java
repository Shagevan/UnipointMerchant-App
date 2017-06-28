package com.example.shagee.unipoint;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shagee.unipoint.Model.OfferClaimHistory;

import java.util.ArrayList;
import java.util.List;

public class OfferClaimingHistoryAdapter extends RecyclerView.Adapter<OfferClaimingHistoryAdapter.ViewHolder> {

    private Context ctx;
    private List<OfferClaimHistory> offerClaimingHistories = new ArrayList<>();

    public OfferClaimingHistoryAdapter(Context ctx, List<OfferClaimHistory> lastTransactions) {
        this.ctx = ctx;
        this.offerClaimingHistories = lastTransactions;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView phoneNumber;
        public TextView pointsAwarded;
        public TextView billValue;
        public TextView invoiceNumber;
        public TextView date;

        public ViewHolder(View v) {
            super(v);
            phoneNumber = (TextView) v.findViewById(R.id.LastTransactionPhoneNumber);
            pointsAwarded = (TextView) v.findViewById(R.id.LastTransactionPointsAwarded);
            billValue = (TextView) v.findViewById(R.id.LastTransactionBillValue);
            invoiceNumber = (TextView) v.findViewById(R.id.LastTransactionInvoiceNumber);
            date = (TextView) v.findViewById(R.id.LastTransactionDate);
        }
    }

    @Override
    public OfferClaimingHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_transaction_row, parent, false);
        ViewHolder vh = new OfferClaimingHistoryAdapter.ViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(OfferClaimingHistoryAdapter.ViewHolder holder, int position) {

        /*holder.phoneNumber.setText(String.valueOf(offerClaimingHistories.get(position).getPhoneNumber()));
        holder.pointsAwarded.setText(String.valueOf(offerClaimingHistories.get(position).getPointsAwarded()));
        holder.billValue.setText(String.valueOf(offerClaimingHistories.get(position).getBillValue()));
        holder.invoiceNumber.setText(offerClaimingHistories.get(position).getInvoiceNumber());
        holder.date.setText(offerClaimingHistories.get(position).getTransactionDateTime());*/
    }

    @Override
    public int getItemCount() {
        return offerClaimingHistories.size();
    }

}


