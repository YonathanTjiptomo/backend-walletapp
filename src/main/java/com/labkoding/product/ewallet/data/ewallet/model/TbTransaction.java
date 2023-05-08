package com.labkoding.product.ewallet.data.ewallet.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_transaction")
public class TbTransaction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "created_dt", nullable = false)
    private Date createdDt;

    @Column(name = "updated_dt", nullable = false)
    private Date updatedDt;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "status", nullable = false)
    private Integer status;

    @PrePersist
    protected void prePersist(){
            if (this.createdDt == null) {
                createdDt = new Date();
            }
            if (this.updatedDt == null) {
                updatedDt = new Date();
            }
    }
    @PreUpdate
    protected void preUpdate() {
        this.updatedDt = new Date();
    }
}
