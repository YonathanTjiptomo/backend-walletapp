package com.labkoding.product.ewallet.data.ewallet.repo;

import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbMoneyRepository extends JpaRepository<TbMoney, Integer> {
    TbMoney findByUid(String uid);
}