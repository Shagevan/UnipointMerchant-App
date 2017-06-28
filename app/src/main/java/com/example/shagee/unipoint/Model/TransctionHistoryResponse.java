package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransctionHistoryResponse {

    @SerializedName("transactionHistory")
    private List<TransactionHistory> transactionHistory;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public List<TransactionHistory> getTransactionHistory() {
        return transactionHistory;
    }
    public void setTransactionHistory(List<TransactionHistory> transactionHistory) {
        this.transactionHistory = transactionHistory;
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
