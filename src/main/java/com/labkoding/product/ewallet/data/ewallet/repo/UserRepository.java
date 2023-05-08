package com.labkoding.product.ewallet.data.ewallet.repo;

import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TbUser, Integer> {

}
