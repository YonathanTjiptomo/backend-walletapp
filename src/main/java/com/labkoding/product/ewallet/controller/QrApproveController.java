package com.labkoding.product.ewallet.controller;


import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.ResourceNotFoundException;
import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import com.labkoding.product.ewallet.data.ewallet.model.TbTransaction;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.repo.TbMoneyRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbTransactionRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbUserRepository;
import com.labkoding.product.ewallet.data.ewallet.request.QrRequest;
import com.labkoding.product.ewallet.data.ewallet.response.QrResponse;
import com.labkoding.product.ewallet.data.ewallet.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/qrpayment")
public class QrApproveController {
    @Autowired
    TbTransactionRepository tbTransactionRepository;

    @Autowired
    TbMoneyRepository tbMoneyRepository;

    @Autowired
    MoneyService moneyService;

    @RequestMapping(value = {"/qrapprove"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody QrResponse qrApprove (@RequestBody QrRequest qrRequest) throws FirebaseAuthException {
        String uid = qrRequest.getUid();
        BigDecimal saldo = moneyService.getMoney(uid);
        BigDecimal amount = qrRequest.getAmount();
        TbMoney tbMoney = tbMoneyRepository.findByUid(uid).orElse(null);
        tbMoney.setAmount(tbMoney.getAmount().subtract(amount));
        if (saldo.compareTo(amount)<0) {
            throw new ResourceNotFoundException("Money not enough");
        }
        tbMoneyRepository.save(tbMoney);
        TbTransaction tbTransaction = new TbTransaction();
        tbTransaction.setAmount(amount);
        tbTransaction.setUserId(tbMoney.getUid());
        tbTransaction.setStatus(1);
        tbTransaction.setTransactionId(UUID.randomUUID().toString());
        tbTransaction.setDescription("Qr Payment");
        tbTransaction.setTransactionDate(new Date());
        tbTransactionRepository.save(tbTransaction);
        QrResponse response = new QrResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        return response;
    }
}
