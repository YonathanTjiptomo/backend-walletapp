package com.labkoding.product.ewallet.data.ewallet.repo;
import com.labkoding.product.ewallet.data.ewallet.model.TbFriend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbFriendRepository extends JpaRepository<TbFriend, Integer> {
    List<TbFriend> findByUserId(String uid);
}
