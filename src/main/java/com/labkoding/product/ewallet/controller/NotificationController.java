package com.labkoding.product.ewallet.controller;
import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import com.labkoding.product.ewallet.data.ewallet.model.TbTransaction;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.repo.TbMoneyRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbTransactionRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbUserRepository;
import com.labkoding.product.ewallet.data.ewallet.request.VaNotifRequest;
import com.labkoding.product.ewallet.data.ewallet.response.VaNotifResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/notifikasi")
public class NotificationController {

    @Autowired
    TbUserRepository tbUserRepository;

    @Autowired
    TbMoneyRepository tbMoneyRepository;

    @Autowired
    TbTransactionRepository tbTransactionRepository;

    @RequestMapping(value = {"/notif-va"}, method = RequestMethod.POST, produces = "application/json")
    public VaNotifResponse callApi(@RequestBody VaNotifRequest vaNotifRequest) throws FirebaseAuthException {
        String virtualAccount = vaNotifRequest.getVirtualAccount();
        BigDecimal amount = vaNotifRequest.getAmount();
        TbUser tbUser = tbUserRepository.findByVirtualAccount(virtualAccount).orElse(null);
        VaNotifResponse response = new VaNotifResponse();
        if (tbUser == null){
            response.setStatus("failed");
            return response;
        }
        TbMoney tbMoney = tbMoneyRepository.findByUid(tbUser.getUid()).orElse(null);
        tbMoney.setAmount(tbMoney.getAmount().add(amount));
        tbMoneyRepository.save(tbMoney);
        TbTransaction tbTransaction = new TbTransaction();
        tbTransaction.setAmount(amount);
        tbTransaction.setStatus(1);
        tbTransaction.setTransactionId(UUID.randomUUID().toString());
        tbTransaction.setDescription("Topup "+ virtualAccount);
        tbTransaction.setTransactionDate(new Date());
        tbTransactionRepository.save(tbTransaction);
        response.setStatus("success");
        return response;
    }
}
