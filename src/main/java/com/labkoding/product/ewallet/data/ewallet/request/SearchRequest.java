package com.labkoding.product.ewallet.data.ewallet.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class SearchRequest {
    private String email;
    private String virtualAccount;

    private BigDecimal amount;

}
