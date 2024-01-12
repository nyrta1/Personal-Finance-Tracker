package com.nurik.transactionservice.payload.request;

import com.nurik.transactionservice.model.TransactionStatus;
import lombok.Data;

@Data
public class TransactionRequest {
    private Integer amount;
    private String category;
    private String description;
    private TransactionStatus status;
}
