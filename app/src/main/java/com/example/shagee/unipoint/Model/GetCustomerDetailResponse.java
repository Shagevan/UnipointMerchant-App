package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

public class GetCustomerDetailResponse {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("cardNumber")
    private String cardNumber;

    @SerializedName("primaryMobileNumber")
    private String primaryMobileNumber;

    @SerializedName("secondaryMobileNumber")
    private String secondaryMobileNumber;

    @SerializedName("schemeLevel")
    private String schemeLevel;

    @SerializedName("totalPoints")
    private String totalPoints;

    @SerializedName("cardStatus")
    private String cardStatus;

    @SerializedName("error")
    private String error;

    @SerializedName("unipointCustomerRefId")
    private String unipointCustomerRefId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPrimaryMobileNumber() {
        return primaryMobileNumber;
    }

    public void setPrimaryMobileNumber(String primaryMobileNumber) {
        this.primaryMobileNumber = primaryMobileNumber;
    }

    public String getSecondaryMobileNumber() {
        return secondaryMobileNumber;
    }

    public void setSecondaryMobileNumber(String secondaryMobileNumber) {
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

    public String getSchemeLevel() {
        return schemeLevel;
    }

    public void setSchemeLevel(String schemeLevel) {
        this.schemeLevel = schemeLevel;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUnipointCustomerRefId() {
        return unipointCustomerRefId;
    }
    public void setUnipointCustomerRefId(String unipointCustomerRefId) {
        this.unipointCustomerRefId = unipointCustomerRefId;
    }
}
