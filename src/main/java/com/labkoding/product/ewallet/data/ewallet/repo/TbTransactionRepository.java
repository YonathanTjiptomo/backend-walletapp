package com.labkoding.product.ewallet.data.ewallet.repo;

import com.labkoding.product.ewallet.data.ewallet.model.TbTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TbTransactionRepository extends JpaRepository<TbTransaction, Integer>, JpaSpecificationExecutor<TbTransaction> {
    Optional<TbTransaction> findByTransactionId(String transactionId);
    List<TbTransaction> findAllByUserId(String uid);
}
