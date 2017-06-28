package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

public class OfferClaimHistory {

    @SerializedName("offerName")
    private String offerName;

    @SerializedName("offerCode")
    private String offerCode;

    @SerializedName("claimedDate")
    private String claimedDate;

    @SerializedName("loyalityLevel")
    private String loyalityLevel;

    public OfferClaimHistory(String offerName, String offerCode, String claimedDate, String loyalityLevel) {
        this.offerName = offerName;
        this.offerCode = offerCode;
        this.claimedDate = claimedDate;
        this.loyalityLevel = loyalityLevel;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public String getClaimedDate() {
        return claimedDate;
    }

    public void setClaimedDate(String claimedDate) {
        this.claimedDate = claimedDate;
    }

    public String getLoyalityLevel() {
        return loyalityLevel;
    }

    public void setLoyalityLevel(String loyalityLevel) {
        this.loyalityLevel = loyalityLevel;
    }
}
