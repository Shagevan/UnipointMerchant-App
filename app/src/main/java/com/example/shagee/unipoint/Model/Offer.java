package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("offerid")
    private int offerId;
    @SerializedName("issue_date_time")
    private String issueDateTime;
    @SerializedName("points_allocated")
    private int pointsAllocated;
    @SerializedName("expiry_date")
    private String expiryDate;
    @SerializedName("offer_heading")
    private String offerHeading;
    @SerializedName("offer_subheading")
    private String offerSubHeading;
    @SerializedName("offer_description")
    private String offerDescription;
    @SerializedName("offer_filter_types")
    private String offerFilterTypes;
    @SerializedName("merchant_ref_id")
    private int merchantRefId;
    @SerializedName("video_url")
    private String videoUrl;
    @SerializedName("offer_type")
    private String offerType;
    @SerializedName("offer_content")
    private String offerContent;
    @SerializedName("offer_redemtion_code")
    private String offerRedemtionCode;
    @SerializedName("merchant_logo_url")
    private String merchantLogoUrl;
    @SerializedName("offer_promo_code")
    private String offerPromoCode;

    public Offer(int offerId, String issueDateTime, int pointsAllocated, String expiryDate, String offerHeading, String offerSubHeading, String offerDescription, String offerFilterTypes, int merchantRefId, String videoUrl, String offerType, String offerContent, String offerRedemtionCode, String merchantLogoUrl, String offerPromoCode) {
        this.offerId = offerId;
        this.issueDateTime = issueDateTime;
        this.pointsAllocated = pointsAllocated;
        this.expiryDate = expiryDate;
        this.offerHeading = offerHeading;
        this.offerSubHeading = offerSubHeading;
        this.offerDescription = offerDescription;
        this.offerFilterTypes = offerFilterTypes;
        this.merchantRefId = merchantRefId;
        this.videoUrl = videoUrl;
        this.offerType = offerType;
        this.offerContent = offerContent;
        this.offerRedemtionCode = offerRedemtionCode;
        this.merchantLogoUrl = merchantLogoUrl;
        this.offerPromoCode = offerPromoCode;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(String issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public int getPointsAllocated() {
        return pointsAllocated;
    }

    public void setPointsAllocated(int pointsAllocated) {
        this.pointsAllocated = pointsAllocated;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOfferHeading() {
        return offerHeading;
    }

    public void setOfferHeading(String offerHeading) {
        this.offerHeading = offerHeading;
    }

    public String getOfferSubHeading() {
        return offerSubHeading;
    }

    public void setOfferSubHeading(String offerSubHeading) {
        this.offerSubHeading = offerSubHeading;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getOfferFilterTypes() {
        return offerFilterTypes;
    }

    public void setOfferFilterTypes(String offerFilterTypes) {
        this.offerFilterTypes = offerFilterTypes;
    }

    public int getMerchantRefId() {
        return merchantRefId;
    }

    public void setMerchantRefId(int merchantRefId) {
        this.merchantRefId = merchantRefId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getOfferContent() {
        return offerContent;
    }

    public void setOfferContent(String offerContent) {
        this.offerContent = offerContent;
    }

    public String getOfferRedemtionCode() {
        return offerRedemtionCode;
    }

    public void setOfferRedemtionCode(String offerRedemtionCode) {
        this.offerRedemtionCode = offerRedemtionCode;
    }

    public String getMerchantLogoUrl() {
        return merchantLogoUrl;
    }

    public void setMerchantLogoUrl(String merchantLogoUrl) {
        this.merchantLogoUrl = merchantLogoUrl;
    }

    public String getOfferPromoCode() {
        return offerPromoCode;
    }

    public void setOfferPromoCode(String offerPromoCode) {
        this.offerPromoCode = offerPromoCode;
    }
}
