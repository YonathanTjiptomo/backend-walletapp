package com.labkoding.product.ewallet.data.ewallet.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class QrRequest {
    private BigDecimal amount;
    private String uid;
}
