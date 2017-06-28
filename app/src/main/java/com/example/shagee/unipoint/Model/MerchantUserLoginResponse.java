package com.example.shagee.unipoint.Model;

import com.google.gson.annotations.SerializedName;

public class MerchantUserLoginResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("outletName")
    private String outletName;

    @SerializedName("outletRefId")
    private String outletRefId;

    @SerializedName("merchantUserName")
    private String merchantUserName;

    @SerializedName("merchantUserRefId")
    private String merchantUserRefId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getOutletRefId() {
        return outletRefId;
    }

    public void setOutletRefId(String outletRefId) {
        this.outletRefId = outletRefId;
    }

    public String getMerchantUserName() {
        return merchantUserName;
    }

    public void setMerchantUserName(String merchantUserName) {
        this.merchantUserName = merchantUserName;
    }

    public String getMerchantUserRefId() {
        return merchantUserRefId;
    }

    public void setMerchantUserRefId(String merchantUserRefId) {
        this.merchantUserRefId = merchantUserRefId;
    }
}
