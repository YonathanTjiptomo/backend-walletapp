package com.labkoding.product.ewallet.data.ewallet.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopUpRequest {
    private String virtualAccount;
    private Integer amount;
}
