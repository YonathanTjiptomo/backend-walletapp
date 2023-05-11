package com.labkoding.product.ewallet.data.ewallet.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Data
@Entity
@Table(name = "tb_money")
public class TbMoney {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String uid;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "created_dt", nullable = false)
    private Date createdDt;

    @Column(name = "updated_dt", nullable = false)
    private Date updatedDt;

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
    protected void preUpdate() {this.updatedDt = new Date();}
}
