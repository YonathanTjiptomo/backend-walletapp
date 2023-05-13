package com.labkoding.product.ewallet.data.ewallet.request;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ChatRequest {
    private String message;
    private BigDecimal amount;
    private String uid;
    private Integer userIdTo;
    private Integer userIdFrom;
    private Integer messageType;
    private Integer id;
}
