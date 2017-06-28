package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("transaction_id")
    private int transactionId;
    @SerializedName("transaction_type")
    private String transactionType;
    @SerializedName("invoice_number")
    private String invoiceNumber;
    @SerializedName("date_time")
    private String date;
    @SerializedName("product_list")
    private String productList;
    @SerializedName("points_redeemed")
    private float pointsRedmed;
    @SerializedName("points_awarded")
    private float pointsAwarded;
    @SerializedName("total_bill_value")
    private float totalBillValue;
    @SerializedName("unipoint_customer_ref_id")
    private int unipointCustomerId;
    @SerializedName("outlet_ref_id")
    private int outletRefId;
    @SerializedName("added_by_ref")
    private int addedByRefId;
    @SerializedName("added_date_time")
    private String addedDateTime;
    @SerializedName("last_modified_date_time")
    private String lastModifiedDateTime;
    @SerializedName("last_modified_fields")
    private String lastModifiedFields;
    @SerializedName("subscribe_merchant_ref_id")
    private int subscribedMerchantRefId;

    public Transaction(int transactionId, String transactionType, String invoiceNumber, String date, String productList, float pointsRedmed, float pointsAwarded, float totalBillValue, int unipointCustomerId, int outletRefId, int addedByRefId, String addedDateTime, String lastModifiedDateTime, String lastModifiedFields, int subscribedMerchantRefId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.productList = productList;
        this.pointsRedmed = pointsRedmed;
        this.pointsAwarded = pointsAwarded;
        this.totalBillValue = totalBillValue;
        this.unipointCustomerId = unipointCustomerId;
        this.outletRefId = outletRefId;
        this.addedByRefId = addedByRefId;
        this.addedDateTime = addedDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.lastModifiedFields = lastModifiedFields;
        this.subscribedMerchantRefId = subscribedMerchantRefId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public float getPointsRedmed() {
        return pointsRedmed;
    }

    public void setPointsRedmed(float pointsRedmed) {
        this.pointsRedmed = pointsRedmed;
    }

    public float getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(float pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    public float getTotalBillValue() {
        return totalBillValue;
    }

    public void setTotalBillValue(float totalBillValue) {
        this.totalBillValue = totalBillValue;
    }

    public int getUnipointCustomerId() {
        return unipointCustomerId;
    }

    public void setUnipointCustomerId(int unipointCustomerId) {
        this.unipointCustomerId = unipointCustomerId;
    }

    public int getOutletRefId() {
        return outletRefId;
    }

    public void setOutletRefId(int outletRefId) {
        this.outletRefId = outletRefId;
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

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getLastModifiedFields() {
        return lastModifiedFields;
    }

    public void setLastModifiedFields(String lastModifiedFields) {
        this.lastModifiedFields = lastModifiedFields;
    }

    public int getSubscribedMerchantRefId() {
        return subscribedMerchantRefId;
    }

    public void setSubscribedMerchantRefId(int subscribedMerchantRefId) {
        this.subscribedMerchantRefId = subscribedMerchantRefId;
    }
}
