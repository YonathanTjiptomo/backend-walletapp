package com.labkoding.product.ewallet.controller;
import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.ResourceNotFoundException;
import com.labkoding.product.ewallet.data.ewallet.model.TbChatting;
import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import com.labkoding.product.ewallet.data.ewallet.model.TbNotifikasi;
import com.labkoding.product.ewallet.data.ewallet.model.TbTransaction;
import com.labkoding.product.ewallet.data.ewallet.repo.TbChattingRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbMoneyRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbNotifikasiRepository;
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

    @Autowired
    TbNotifikasiRepository tbNotifikasiRepository;


    @RequestMapping(value = {"/send-money"}, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ChatResponse sendMoney (@RequestBody ChatRequest chatRequest, @RequestParam("uid") String uid) throws FirebaseAuthException {
        String message = chatRequest.getMessage();
        TbChatting tbChatting = new TbChatting();
        tbChatting.setMessage(message);
        tbChatting.setMessageType(2);
        tbChatting.setUserIdFrom(uid);
        tbChatting.setCreatedBy(uid);
        tbChatting.setUpdatedBy(uid);
        String friendId = chatRequest.getUserIdTo();
        BigDecimal amount = chatRequest.getAmount();
        tbChatting.setUserIdTo(friendId);
        tbChatting.setAmount(amount);
        tbChattingRepository.save(tbChatting);
        BigDecimal saldo = moneyService.getMoney(uid);
        TbMoney tbMoney = tbMoneyRepository.findByUid(uid).orElse(null);
        TbMoney money = tbMoneyRepository.findByUid(friendId).orElse(null);
        money.setAmount(money.getAmount().add(amount));
        if (tbMoney == null){
            return null;
        }
        tbMoney.setAmount(tbMoney.getAmount().subtract(amount));
        if (saldo.compareTo(amount)<0) {
            throw new ResourceNotFoundException("Money not enough");
        }
        tbMoneyRepository.save(money);
        tbMoneyRepository.save(tbMoney);
        TbTransaction tbTransaction = new TbTransaction();
        TbTransaction transaction = new TbTransaction();
        tbTransaction.setAmount(amount);
        tbTransaction.setUserId(uid);
        tbTransaction.setStatus(1);
        tbTransaction.setTransactionId(UUID.randomUUID().toString());
        tbTransaction.setDescription("Send Money");
        tbTransaction.setTransactionDate(new Date());
        transaction.setAmount(amount);
        transaction.setUserId(friendId);
        transaction.setStatus(1);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setDescription("Terima Saldo");
        transaction.setTransactionDate(new Date());
        tbTransactionRepository.save(transaction);
        tbTransactionRepository.save(tbTransaction);
        TbNotifikasi notifikasi = new TbNotifikasi();
        TbNotifikasi tbNotifikasi = new TbNotifikasi();
        tbNotifikasi.setUserId(friendId);
        tbNotifikasi.setPesanNotif("Saldo Diterima");
        tbNotifikasi.setCreatedBy(uid);
        tbNotifikasi.setUpdatedBy(uid);
        notifikasi.setUserId(uid);
        notifikasi.setPesanNotif("Pengiriman Saldo Berhasil");
        notifikasi.setCreatedBy(uid);
        notifikasi.setUpdatedBy(uid);
        tbNotifikasiRepository.save(tbNotifikasi);
        tbNotifikasiRepository.save(notifikasi);
        ChatResponse response = new ChatResponse();
        response.setMessage(message);
        response.setAmount(amount);
        response.setStatus("success");
        return response;
    }

    @PostMapping(value = "/get-message", produces = "application/json")
    public List<String> getMessage(@RequestBody Map<String, String> request, @RequestParam("uid") String uid) {
        String userIdTo = request.get("userIdTo");
        List<TbChatting> chattingList = tbChattingRepository.findByUserIdFromAndUserIdToOrUserIdFromAndUserIdTo(uid, userIdTo, userIdTo, uid);
        List<String> messages = new ArrayList<>();
        for (TbChatting chatting : chattingList) {
            if (chatting.getMessageType() == 1) {
                messages.add(chatting.getMessage());
            } else if (chatting.getMessageType() ==  2) {
                messages.add(chatting.getMessage()+" amount: "+ chatting.getAmount());
            } else {
                messages.add(chatting.getMessage());
            }
        }
        return messages;
    }

    @PostMapping(value = "/send-message", produces = "application/json")
    public @ResponseBody ChatResponse sendMessage (@RequestBody ChatRequest chatRequest, @RequestParam("uid") String uid) {
        String message = chatRequest.getMessage();
        String friendId = chatRequest.getUserIdTo();
        TbChatting tbChatting = new TbChatting();
        tbChatting.setMessage(message);
        tbChatting.setMessageType(1);
        tbChatting.setAmount(null);
        tbChatting.setUserIdTo(friendId);
        tbChatting.setCreatedBy(uid);
        tbChatting.setUpdatedBy(uid);
        tbChatting.setUserIdFrom(uid);
        tbChattingRepository.save(tbChatting);
        TbNotifikasi tbNotifikasi = new TbNotifikasi();
        tbNotifikasi.setCreatedBy(uid);
        tbNotifikasi.setUpdatedBy(uid);
        tbNotifikasi.setUserId(friendId);
        tbNotifikasi.setPesanNotif("Pesan Masuk");
        tbNotifikasiRepository.save(tbNotifikasi);
        ChatResponse responses = new ChatResponse();
        responses.setMessage(message);
        responses.setStatus("success");
        return responses;
    }
}
