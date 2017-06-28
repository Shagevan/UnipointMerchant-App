package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

public class TransactionHistory {

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("pointsAwarded")
    private String pointsAwarded;

    @SerializedName("billValue")
    private String billValue;

    @SerializedName("invoiceNumber")
    private String invoiceNumber;

    @SerializedName("TransactionDateTime")
    private String transactionDateTime;

    @SerializedName("loyalityLevel")
    private String loyalityLevel;

    public TransactionHistory(String phoneNumber, String pointsAwarded, String billValue, String invoiceNumber, String transactionDateTime, String loyalityLevel) {
        this.phoneNumber = phoneNumber;
        this.pointsAwarded = pointsAwarded;
        this.billValue = billValue;
        this.invoiceNumber = invoiceNumber;
        this.transactionDateTime = transactionDateTime;
        this.loyalityLevel = loyalityLevel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(String pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    public String getBillValue() {
        return billValue;
    }

    public void setBillValue(String billValue) {
        this.billValue = billValue;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getLoyalityLevel() {
        return loyalityLevel;
    }

    public void setLoyalityLevel(String loyalityLevel) {
        this.loyalityLevel = loyalityLevel;
    }
}
