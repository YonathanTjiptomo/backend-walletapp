package com.labkoding.product.ewallet.data.ewallet.repo;

import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TbUserRepository extends JpaRepository<TbUser, Integer> {
    Optional<TbUser> findByUid(String uid);
    Optional<TbUser> findByVirtualAccount(String virtualAccount);
    Optional<TbUser> findByEmail(String email);
}
