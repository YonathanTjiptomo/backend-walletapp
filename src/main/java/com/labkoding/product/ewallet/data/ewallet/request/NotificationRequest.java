package com.labkoding.product.ewallet.data.ewallet.request;

public class NotificationRequest {
    private String virtualAccount;
    private Integer amount;

    public String getVirtualAccount() {
        return virtualAccount;
    }

    public void setVirtualAccount(String virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
