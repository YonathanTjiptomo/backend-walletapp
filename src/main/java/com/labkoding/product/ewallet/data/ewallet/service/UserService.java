package com.labkoding.product.ewallet.data.ewallet.service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    FirebaseAuth firebaseAuth;
    UserRepository userRepository;

    public UserService(FirebaseAuth firebaseAuth, UserRepository userRepository) {
        this.firebaseAuth = firebaseAuth;
        this.userRepository = userRepository;
    }

    public void saveUser(String uid, String email) throws FirebaseAuthException {
        UserRecord userRecord = firebaseAuth.getUser(uid);
        TbUser user = new TbUser();
        user.setEmail(userRecord.getEmail());
        user.setUid(userRecord.getUid());
        userRepository.save(user);
    }
}
