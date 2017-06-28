package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

public class OfferClaim {

    @SerializedName("offer_claim_id")
    private int offerClaimId;
    @SerializedName("offer_ref_id")
    private int offerRefId;
    @SerializedName("unipoint_customer_ref_id")
    private int unipointCustomerRefId;
    @SerializedName("date_time_claimed")
    private String dateTimeClaimed;
    @SerializedName("merchant_points_added")
    private float merchantPointsAdded;
    @SerializedName("quantity_claimed")
    private int quantityClaimed;
    @SerializedName("added_by_ref")
    private int addedByRefId;
    @SerializedName("added_date_time")
    private String addedDateTime;
    @SerializedName("last_modified_by_ref")
    private int lastModifiedByRef;
    @SerializedName("last_modified_date_time")
    private String lastModifiedDateTime;
    @SerializedName("last_modified_fields")
    private String lastModifiedField;

    public OfferClaim() {
    }

    public OfferClaim(int offerClaimId, int offerRefId, int unipointCustomerRefId, String dateTimeClaimed, float merchantPointsAdded, int quantityClaimed, int addedByRefId, String addedDateTime, int lastModifiedByRef, String lastModifiedDateTime, String lastModifiedField) {
        this.offerClaimId = offerClaimId;
        this.offerRefId = offerRefId;
        this.unipointCustomerRefId = unipointCustomerRefId;
        this.dateTimeClaimed = dateTimeClaimed;
        this.merchantPointsAdded = merchantPointsAdded;
        this.quantityClaimed = quantityClaimed;
        this.addedByRefId = addedByRefId;
        this.addedDateTime = addedDateTime;
        this.lastModifiedByRef = lastModifiedByRef;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.lastModifiedField = lastModifiedField;
    }

    public int getOfferClaimId() {
        return offerClaimId;
    }

    public void setOfferClaimId(int offerClaimId) {
        this.offerClaimId = offerClaimId;
    }

    public int getOfferRefId() {
        return offerRefId;
    }

    public void setOfferRefId(int offerRefId) {
        this.offerRefId = offerRefId;
    }

    public int getUnipointCustomerRefId() {
        return unipointCustomerRefId;
    }

    public void setUnipointCustomerRefId(int unipointCustomerRefId) {
        this.unipointCustomerRefId = unipointCustomerRefId;
    }

    public String getDateTimeClaimed() {
        return dateTimeClaimed;
    }

    public void setDateTimeClaimed(String dateTimeClaimed) {
        this.dateTimeClaimed = dateTimeClaimed;
    }

    public float getMerchantPointsAdded() {
        return merchantPointsAdded;
    }

    public void setMerchantPointsAdded(float merchantPointsAdded) {
        this.merchantPointsAdded = merchantPointsAdded;
    }

    public int getQuantityClaimed() {
        return quantityClaimed;
    }

    public void setQuantityClaimed(int quantityClaimed) {
        this.quantityClaimed = quantityClaimed;
    }

    public int getAddedByRefId() {
        return addedByRefId;
    }

    public void setAddedByRefId(int addedByRefId) {
        this.addedByRefId = addedByRefId;
    }

    public String getAddedDateTime() {
        return addedDateTime;
    }

    public void setAddedDateTime(String addedDateTime) {
        this.addedDateTime = addedDateTime;
    }

    public int getLastModifiedByRef() {
        return lastModifiedByRef;
    }

    public void setLastModifiedByRef(int lastModifiedByRef) {
        this.lastModifiedByRef = lastModifiedByRef;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getLastModifiedField() {
        return lastModifiedField;
    }

    public void setLastModifiedField(String lastModifiedField) {
        this.lastModifiedField = lastModifiedField;
    }
}
