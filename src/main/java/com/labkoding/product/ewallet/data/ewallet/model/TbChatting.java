package com.labkoding.product.ewallet.data.ewallet.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tb_chatting")
public class TbChatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_dt", nullable = false)
    private Date createdDt;

    @Column(name = "updated_dt", nullable = false)
    private Date updatedDt;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Column(name = "user_id_from", nullable = false)
    private String userIdFrom;

    @Column(name = "user_id_to", nullable = false)
    private String userIdTo;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "message_type", nullable = false)
    private Integer messageType;

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
