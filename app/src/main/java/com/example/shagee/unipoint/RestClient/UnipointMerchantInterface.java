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
                                                   @Query("invoiceNumber") String invoiceNumber,
                                                   @Query("merchantUserRefId") String merchantUserRefId);

    @POST("points/addFromAmount")
    Call<TransctionHistoryResponse> addPointsFromAmount(@Query("customerMobileNumber") String customerMobileNumber,
                                                   @Query("billAmount") String billAmount,
                                                   @Query("merchantUserRefId") String merchantUserRefId,
                                                   @Query("transactionType") String transactionType);

    @POST("points/redeem")
    Call<TransctionHistoryResponse> redeemPoints(@Query("customerMobileNumber") String customerMobileNumber,
                                                 @Query("billValue") String billValue,
                                                 @Query("points") String points,
                                                 @Query("merchantUserRefId") String merchantUserRefId,
                                                 @Query("transactionType") String transactionType);

    @GET("points/getTransactionHistory")
    Call<TransctionHistoryResponse> getTransactionHistory(@Query("merchantUserRefId") String merchantUserRefId);

    @GET("offers/getOfferClaimingHistory")
    Call<OfferClaimHistoryResponse> getOfferClaimingHistory(@Query("merchantUserRefId") String merchantUserRefId);

    @POST("offers/releaseOffer")
    Call<OfferClaimHistoryResponse> releaseOffer(@Query("offerRefId") String offerRefId,
                                                 @Query("merchantUserRefId") String merchantUserRefId,
                                                 @Query("unipointCustomerRefId") String unipointCustomerRefId,
                                                 @Query("pointsAdded") String pointsAdded);
}
