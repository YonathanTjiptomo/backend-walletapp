package com.labkoding.product.ewallet.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.ResourceNotFoundException;
import com.labkoding.product.ewallet.data.ewallet.model.TbChatting;
import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import com.labkoding.product.ewallet.data.ewallet.model.TbTransaction;
import com.labkoding.product.ewallet.data.ewallet.repo.TbChattingRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbMoneyRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbTransactionRepository;
import com.labkoding.product.ewallet.data.ewallet.request.ChatRequest;
import com.labkoding.product.ewallet.data.ewallet.response.ChatResponse;
import com.labkoding.product.ewallet.data.ewallet.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

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


    @RequestMapping(value = {"/send-money"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ChatResponse sendMoney (@RequestBody ChatRequest chatRequest, @RequestParam("uid") String uid) throws FirebaseAuthException {
        String message = chatRequest.getMessage();
        TbChatting tbChatting = new TbChatting();
        tbChatting.setMessage(message);
        tbChatting.setMessageType(2);
        tbChatting.setUserIdFrom(uid);
        tbChatting.setCreatedBy(uid);
        tbChatting.setUpdatedBy(uid);
        tbChatting.setUserIdTo(String.valueOf(2));
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
        response.setMessage(message);
        return response;
    }

    @PostMapping(value = "/get-message", produces = "application/json")
    public List<String> getMessage(@RequestBody Map<String, String> request, @RequestParam("uid") String uid) {
        List<TbChatting> chattingList = tbChattingRepository.findByUserIdFrom(uid);
        List<String> messages = new ArrayList<>();
        for (TbChatting chatting : chattingList) {
            messages.add(chatting.getMessage());
        }
        return messages;
    }

    @PostMapping(value = "/send-message", produces = "application/json")
    public @ResponseBody ChatResponse sendMessage (@RequestBody ChatRequest chatRequest, @RequestParam("uid") String uid) throws FirebaseAuthException {
        String message = chatRequest.getMessage();
        TbChatting tbChatting = new TbChatting();
        tbChatting.setMessage(message);
        tbChatting.setMessageType(1);
        tbChatting.setUserIdFrom(uid);
        tbChatting.setCreatedBy(uid);
        tbChatting.setUpdatedBy(uid);
        tbChatting.setUserIdTo(String.valueOf(2));
        tbChattingRepository.save(tbChatting);
        ChatResponse response = new ChatResponse();
        response.setMessage(message);
        response.setStatus("success");
        return response;
    }
}
