package com.example.shagee.unipoint.RestClient;

import com.example.shagee.unipoint.Model.GetCustomerDetailResponse;
import com.example.shagee.unipoint.Model.MerchantUserLoginResponse;
import com.example.shagee.unipoint.Model.OfferClaimHistoryResponse;
import com.example.shagee.unipoint.Model.TransctionHistoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UnipointMerchantInterface {

    @POST("merchantUser/login")
    Call<MerchantUserLoginResponse> login(@Query("userName") String userName,
                                          @Query("password") String password);

    @POST("merchantUser/getCustomerDetails")
    Call<GetCustomerDetailResponse> getCustomerDetails(@Query("cardNumber") String cardNumber);

    @POST("points/addFromInvoice")
    Call<TransctionHistoryResponse> addFromInvoice(@Query("customerMobileNumber") String customerMobileNumber,
                                                   @Query("invoiceNumber") int invoiceNumber,
                                                   @Query("merchantUserRefId") int merchantUserRefId,
                                                   @Query("outletRefId") int outletRefId);

    @POST("points/addFromAmount")
    Call<TransctionHistoryResponse> addPointsFromAmount(@Query("customerMobileNumber") String customerMobileNumber,
                                                   @Query("billAmount") int billAmount,
                                                   @Query("merchantUserRefId") int merchantUserRefId,
                                                   @Query("outletRefId") int outletRefId);

    @POST("points/redeem")
    Call<TransctionHistoryResponse> redeemPoints(@Query("customerMobileNumber") String customerMobileNumber,
                                                 @Query("invoiceNumber") int invoiceNumber,
                                                 @Query("points") double points,
                                                 @Query("merchantUserRefId") int merchantUserRefId,
                                                 @Query("outletRefId") int outletRefId);

    @GET("points/getTransactionHistory")
    Call<TransctionHistoryResponse> getTransactionHistory(@Query("merchantUserRefId") int merchantUserRefId, @Query("outletRefId") int outletRefId);

    @GET("offers/getOfferClaimingHistory")
    Call<OfferClaimHistoryResponse> getOfferClaimingHistory(@Query("merchantUserRefId") int merchantUserRefId);

    @POST("offers/releaseOffer")
    Call<OfferClaimHistoryResponse> releaseOffer(@Query("offerRefId") int offerRefId,
                                                 @Query("merchantUserRefId") int merchantUserRefId,
                                                 @Query("unipointCustomerRefId") int unipointCustomerRefId,
                                                 @Query("pointsAdded") int pointsAdded);
}
