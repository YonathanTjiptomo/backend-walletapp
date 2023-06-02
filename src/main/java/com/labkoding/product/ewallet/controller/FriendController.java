package com.labkoding.product.ewallet.controller;
import com.google.firebase.auth.FirebaseAuthException;
import com.labkoding.product.ewallet.data.ewallet.model.TbFriend;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.repo.TbFriendRepository;
import com.labkoding.product.ewallet.data.ewallet.repo.TbUserRepository;
import com.labkoding.product.ewallet.data.ewallet.request.FriendRequest;
import com.labkoding.product.ewallet.data.ewallet.response.FriendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/friend")
public class FriendController {

    @Autowired
    TbUserRepository tbUserRepository;
    @Autowired
    TbFriendRepository tbFriendRepository;
    @RequestMapping(value= {"/add-friend"}, method= RequestMethod.POST, produces = "application/json")
    public @ResponseBody FriendResponse addFriend(@RequestBody FriendRequest friendRequest, @RequestParam("uid") String uid) throws FirebaseAuthException {
        String friendEmail = friendRequest.getEmail();
        TbUser myFriendEmail = tbUserRepository.findByEmail(friendEmail).orElse(null);
        TbUser myUserId = tbUserRepository.findByUid(uid).orElse(null);
        TbFriend tbFriend = new TbFriend();
        TbFriend friend = new TbFriend();
        if (myFriendEmail == null) {
            return null;
        }
        friend.setUserId(myFriendEmail.getUid());
        friend.setFriend(myUserId);
        friend.setUpdatedBy(uid);
        friend.setCreatedBy(uid);
        tbFriend.setUserId(uid);
        tbFriend.setFriend(myFriendEmail);
        tbFriend.setCreatedBy(uid);
        tbFriend.setUpdatedBy(uid);
        tbFriendRepository.save(friend);
        tbFriendRepository.save(tbFriend);
        FriendResponse friendResponse = new FriendResponse();
        friendResponse.setEmail(myFriendEmail.getEmail());
        return friendResponse;
    }

    @RequestMapping(value = {"/get-friend"}, method = RequestMethod.POST, produces = "application/json")
    public List<Map<String, String>> getFriend(@RequestParam("uid") String uid) {
        List<TbFriend> friendList = tbFriendRepository.findByUserId(uid);
        List<Map<String, String>> friendDataList = new ArrayList<>();
        for (TbFriend friend : friendList) {
            Map<String, String> friendData = new HashMap<>();
            friendData.put("userIdFriend", friend.getFriend().getUid());
            friendData.put("friendEmail", friend.getFriend().getEmail());
            friendDataList.add(friendData);
        }
        return friendDataList;
    }
}
