package com.labkoding.product.ewallet.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.ResourceNotFoundException;
import com.labkoding.product.ewallet.data.ewallet.model.TbChatting;
import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import com.labkoding.product.ewallet.data.ewallet.model.TbTransaction;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.repo.TbChattingRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbMoneyRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbTransactionRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbUserRepository;
import com.labkoding.product.ewallet.data.ewallet.request.ChatRequest;
import com.labkoding.product.ewallet.data.ewallet.response.ChatResponse;
import com.labkoding.product.ewallet.data.ewallet.service.MoneyService;
import com.labkoding.product.ewallet.data.ewallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChattingController {
    @Autowired
    TbChattingRepository tbChattingRepository;

    @Autowired
    MoneyService moneyService;

    @Autowired
    TbMoneyRepository tbMoneyRepository;

    @Autowired
    TbTransactionRepository tbTransactionRepository;

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/send-money"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ChatResponse sendMoney (@RequestBody ChatRequest chatRequest, @RequestParam("uid") String uid) throws FirebaseAuthException {
        Integer id = userService.getId(uid);
        String message = chatRequest.getMessage();
        TbChatting tbChatting = new TbChatting();
        tbChatting.setMessage(message);
        tbChatting.setMessageType(1);
        tbChatting.setUserIdFrom(id);
        tbChatting.setCreatedBy(id);
        tbChatting.setUpdatedBy(id);
        tbChatting.setUserIdTo(2);
        tbChattingRepository.save(tbChatting);
        BigDecimal saldo = moneyService.getMoney(uid);
        BigDecimal amount = chatRequest.getAmount();
        TbMoney tbMoney = tbMoneyRepository.findByUid(uid).orElse(null);
        tbMoney.setAmount(tbMoney.getAmount().subtract(amount));
        if (saldo.compareTo(amount)<0) {
            throw new ResourceNotFoundException("Money not enough");
        }
        tbMoneyRepository.save(tbMoney);
        TbTransaction tbTransaction = new TbTransaction();
        tbTransaction.setAmount(amount);
        tbTransaction.setStatus(1);
        tbTransaction.setTransactionId(UUID.randomUUID().toString());
        tbTransaction.setDescription("Send Money");
        tbTransaction.setTransactionDate(new Date());
        tbTransactionRepository.save(tbTransaction);
        ChatResponse response = new ChatResponse();
        response.setStatus("Success");
        return response;
    }
}
