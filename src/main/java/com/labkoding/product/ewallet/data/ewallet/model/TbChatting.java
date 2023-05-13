package com.labkoding.product.ewallet.data.ewallet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tb_chatting")
public class TbChatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by", nullable = true)
    private Integer createdBy;

    @Column(name = "created_dt", nullable = false)
    private Date createdDt;

    @Column(name = "updated_dt", nullable = false)
    private Date updatedDt;

    @Column(name = "updated_by", nullable = true)
    private Integer updatedBy;

    @Column(name = "user_id_from", nullable = false)
    private Integer userIdFrom;

    @Column(name = "user_id_to", nullable = true)
    private Integer userIdTo;

    @Column(name = "message", nullable = true)
    private String message;

    @Column(name = "message_type", nullable = true)
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
