package com.labkoding.product.ewallet.data.ewallet.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


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
    private Integer amount;

    @Column(name = "created_dt", nullable = false)
    private Date createdDt;

    @Column(name = "updated_dt", nullable = false)
    private Date updatedDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = 0;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

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
