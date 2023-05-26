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
        String uidFriend = friendRequest.getEmail();
        TbUser tbUser = tbUserRepository.findByEmail(uidFriend).orElse(null);
        TbFriend tbFriend = new TbFriend();
        tbFriend.setUserId(uid);
        if (tbUser == null){
            return null;
        }
        tbFriend.setUserIdFriend(tbUser.getUid());
        tbFriend.setCreatedBy(uid);
        tbFriend.setUpdatedBy(uid);
        tbFriendRepository.save(tbFriend);
        FriendResponse friendResponse = new FriendResponse();
        friendResponse.setEmail(tbUser.getEmail());
        return friendResponse;
    }

    @RequestMapping(value = {"/get-friend"}, method = RequestMethod.POST, produces = "application/json")
    public List<TbFriend> getFriend(@RequestBody Map request, @RequestParam("uid") String uid) {
        List<TbFriend> friendList = tbFriendRepository.findAllByUserId(uid);
        return friendList;
    }
}
