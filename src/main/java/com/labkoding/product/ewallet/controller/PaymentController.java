package com.labkoding.product.ewallet.controller;

import com.labkoding.product.ewallet.data.ewallet.model.TbTransaction;
import com.labkoding.product.ewallet.data.ewallet.repo.TbTransactionRepository;
import com.labkoding.product.ewallet.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value= {"/payment"})
public class PaymentController {
    @Autowired
    TbTransactionRepository tbTransactionRepository;
    @RequestMapping(value= {"/submit-transaction"}, method= RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map submitTransaction(@RequestBody Map request) throws ParseException {

        Map response= new HashMap();
        TbTransaction tbTransaction = new TbTransaction();
        tbTransaction.setTransactionId((String) request.get("transactionId"));
        tbTransaction.setAmount(new BigDecimal((Integer) request.get("amount")));
        tbTransaction.setTransactionDate(Util.stringToDate((String) request.get("transactionDate"), "yyyy-MM-dd HH:mm:ss.SSS Z"));
        tbTransaction.setDescription((String) request.get("description"));
        tbTransaction.setStatus((Integer) request.get("status"));
        tbTransactionRepository.save(tbTransaction);

        response.put("transactionId", request.get("transactionId"));
        response.put("status","success");
        return response;
    }

    @RequestMapping(value = {"/view-transaction"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<TbTransaction> viewTransaction(@RequestBody Map request) {
        System.out.println("test");
        List<TbTransaction> allData = tbTransactionRepository.findAll();
        return allData;
    }

    @RequestMapping(value = {"/view-transaction-by-transactionid"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map viewTransactionByTransactionId(@RequestBody Map request) {
        TbTransaction oneData = tbTransactionRepository.findByTransactionId((String) request.get("transactionId")).orElse(null);
        Map response= new HashMap();
        response.put("oneData", oneData);
        return response;
    }
}
