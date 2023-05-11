package com.labkoding.product.ewallet.data.ewallet.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataVaResponse {
    private String email;
    private String virtualAccount;

    public DataVaResponse(String email, String virtualAccount) {
        this.email = email;
        this.virtualAccount = virtualAccount;
    }

}
