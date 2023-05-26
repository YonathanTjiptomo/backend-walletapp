package com.labkoding.product.ewallet.data.ewallet.repo;

import com.labkoding.product.ewallet.data.ewallet.model.TbChatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbChattingRepository extends JpaRepository<TbChatting, Integer> {
    List<TbChatting> findByUserIdFrom(String uid);
    List<TbChatting> findByUserIdFromOrUserIdTo(String uid, String userIdTo);
    List<TbChatting> findByUserIdFromAndUserIdToOrUserIdFromAndUserIdTo(String uid, String userIdTo, String userIdTo2, String uid2);
}
