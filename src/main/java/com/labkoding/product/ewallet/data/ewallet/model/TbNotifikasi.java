package com.labkoding.product.ewallet.data.ewallet.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tb_notifikasi")
public class TbNotifikasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "pesan_notif", nullable = false)
    private String pesanNotif;

    @Column(name = "created_dt", nullable = false)
    private Date createdDt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_dt", nullable = false)
    private Date updatedDt;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Column(name = "user_id", nullable = false)
    private String userId;

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
