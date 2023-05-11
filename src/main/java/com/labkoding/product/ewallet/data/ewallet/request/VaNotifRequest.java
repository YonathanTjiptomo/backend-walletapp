package com.labkoding.product.ewallet.data.ewallet.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class VaNotifRequest {
    private String virtualAccount;

    private BigDecimal amount;

}
