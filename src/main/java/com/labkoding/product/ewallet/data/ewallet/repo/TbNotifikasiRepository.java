package com.labkoding.product.ewallet.data.ewallet.repo;
import com.labkoding.product.ewallet.data.ewallet.model.TbNotifikasi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbNotifikasiRepository extends JpaRepository<TbNotifikasi, Integer> {
    List<TbNotifikasi> findAllByUserId(String uid);
}
