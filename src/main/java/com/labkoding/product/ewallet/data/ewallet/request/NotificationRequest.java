package com.labkoding.product.ewallet.data.ewallet.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequest {
    private String virtualAccount;
    private Integer amount;
}
