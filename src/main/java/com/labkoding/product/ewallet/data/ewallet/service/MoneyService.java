package com.labkoding.product.ewallet.data.ewallet.service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.labkoding.product.ewallet.data.ewallet.model.TbMoney;
import com.labkoding.product.ewallet.data.ewallet.repo.TbMoneyRepository;
import org.springframework.stereotype.Service;

@Service
public class MoneyService {
    FirebaseAuth firebaseAuth;
    TbMoneyRepository moneyRepository;

    public MoneyService(TbMoneyRepository moneyRepository, FirebaseAuth firebaseAuth) {
        this.moneyRepository = moneyRepository;
        this.firebaseAuth = firebaseAuth;
    }

    public int getMoney(String uid) throws FirebaseAuthException {
        TbMoney money = moneyRepository.findByUid(uid);
        if (money == null) {
            money = new TbMoney();
            money.setUid(uid);
        }
        int newAmount = money.getAmount();
        money.setAmount(newAmount);
        moneyRepository.save(money);
        return newAmount;
    }

    public Integer addMoney(int amount) {
        TbMoney money = new TbMoney();
        money.setAmount(money.getAmount() + amount);
        moneyRepository.save(money);
        return money.getAmount();
    }

    public Integer subtractMoney(int amount) {
        TbMoney money = new TbMoney();
        int newAmount = money.getAmount() - amount;
        if (newAmount < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        money.setAmount(newAmount);
        moneyRepository.save(money);
        return money.getAmount();
    }
}