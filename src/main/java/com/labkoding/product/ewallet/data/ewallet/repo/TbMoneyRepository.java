package com.labkoding.product.ewallet.data.ewallet.repo;

import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface TbMoneyRepository extends JpaRepository<TbMoney, Integer> {
    Optional<TbMoney> findByUid(String uid);
    Optional<TbMoney> findByAmount(BigDecimal saldo);

}