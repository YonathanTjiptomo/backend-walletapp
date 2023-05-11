package com.labkoding.product.ewallet.data.ewallet.service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.repo.TbUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    FirebaseAuth firebaseAuth;
    TbUserRepository userRepository;

    public UserService(FirebaseAuth firebaseAuth, TbUserRepository userRepository) {
        this.firebaseAuth = firebaseAuth;
        this.userRepository = userRepository;
    }

    public void saveUser(String uid, String email) throws FirebaseAuthException {
        UserRecord userRecord = firebaseAuth.getUser(uid);
        TbUser user = new TbUser();
        user.setEmail(userRecord.getEmail());
        user.setUid(userRecord.getUid());
        long number = 5233200000000000L + (long) (Math.random() * 100000000000L);
        user.setVirtualAccount("" + number);
        userRepository.save(user);
    }

    public String getVa(String uid) throws FirebaseAuthException {
        TbUser user = userRepository.findByUid(uid).orElse(null);
        if (user == null){
            return null;
        }
        return user.getVirtualAccount();
    }
}
