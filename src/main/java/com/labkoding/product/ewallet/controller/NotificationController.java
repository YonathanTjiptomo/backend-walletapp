package com.labkoding.product.ewallet.controller;
import com.labkoding.product.ewallet.data.ewallet.model.TbNotifikasi;
import com.labkoding.product.ewallet.data.ewallet.repo.TbNotifikasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/notifikasi")
public class NotificationController {

    @Autowired
    TbNotifikasiRepository tbNotifikasiRepository;
    @RequestMapping(value = {"/view-notifikasi"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<TbNotifikasi> viewNotifikasi(@RequestBody Map request, @RequestParam("uid") String uid) {
        List<TbNotifikasi> notifikasi = tbNotifikasiRepository.findAllByUserId(uid);
        return notifikasi;
    }
}
