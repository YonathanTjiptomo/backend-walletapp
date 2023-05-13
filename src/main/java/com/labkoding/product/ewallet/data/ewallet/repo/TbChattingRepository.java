package com.labkoding.product.ewallet.data.ewallet.repo;

import com.labkoding.product.ewallet.data.ewallet.model.TbChatting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbChattingRepository extends JpaRepository<TbChatting, Integer> {
    Optional<TbChatting> findById(Integer id);
}
