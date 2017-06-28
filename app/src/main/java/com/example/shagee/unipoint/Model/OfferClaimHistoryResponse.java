package com.example.shagee.unipoint.Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferClaimHistoryResponse {

    @SerializedName("offerClaimHistory")
    private List<OfferClaimHistory> offerClaimHistory;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public List<OfferClaimHistory> getOfferClaimHistory() {
        return offerClaimHistory;
    }

    public void setOfferClaimHistory(List<OfferClaimHistory> offerClaimHistory) {
        this.offerClaimHistory = offerClaimHistory;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
