package com.labkoding.product.ewallet.data.ewallet.model;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@Entity
@Table(name = "tb_friend")
public class TbFriend implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id_friend", nullable = false, referencedColumnName = "user_id")
    private TbUser friend;

    @Column(name = "created_dt", nullable = false)
    private Date createdDt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_dt", nullable = false)
    private Date updatedDt;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

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
