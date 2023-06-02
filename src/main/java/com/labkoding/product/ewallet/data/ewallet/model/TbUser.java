package com.labkoding.product.ewallet.data.ewallet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tb_user")
public class TbUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_id", nullable = false, unique = true)
    private String uid;

    @Column(name = "virtual_account", nullable = false, unique = true)
    private String virtualAccount;

}
