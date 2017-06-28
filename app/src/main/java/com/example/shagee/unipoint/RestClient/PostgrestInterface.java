package com.example.shagee.unipoint.RestClient;

import com.example.shagee.unipoint.Model.Offer;
import com.example.shagee.unipoint.Model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostgrestInterface {

    @GET("transaction")
    Call<List<Transaction>> getTransaction(@Query("subscribe_merchant_ref_id") String mercantRefId,
                                           @Query("outlet_ref_id") String outletRefId);

    @GET("offer")
    Call<List<Offer>> getUpAndRunningOffers(@Query("merchant_ref_id") String mercantRefId);
}
