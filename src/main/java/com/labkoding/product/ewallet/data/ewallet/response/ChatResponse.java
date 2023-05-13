package com.labkoding.product.ewallet.data.ewallet.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ChatResponse {
    private Integer id;
    private String status;
    private String message;
    private BigDecimal amount;
}
