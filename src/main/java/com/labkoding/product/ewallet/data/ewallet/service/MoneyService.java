package com.labkoding.product.ewallet.data.ewallet.service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import com.labkoding.product.ewallet.data.ewallet.repo.TbMoneyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyService {
    FirebaseAuth firebaseAuth;
    TbMoneyRepository moneyRepository;

    public MoneyService(TbMoneyRepository moneyRepository, FirebaseAuth firebaseAuth) {
        this.moneyRepository = moneyRepository;
        this.firebaseAuth = firebaseAuth;
    }

    public BigDecimal getMoney(String uid) throws FirebaseAuthException {
        TbMoney money = moneyRepository.findByUid(uid).orElse(null);
        if (money == null) {
            money = new TbMoney();
            money.setUid(uid);
            money.setAmount(new BigDecimal(0));
            moneyRepository.save(money);
        }
        return money.getAmount();
    }
}