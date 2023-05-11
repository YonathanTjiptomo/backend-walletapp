package com.labkoding.product.ewallet.controller;

import com.labkoding.product.ewallet.ResourceNotFoundException;
import com.labkoding.product.ewallet.data.ewallet.model.TbUser;
import com.labkoding.product.ewallet.data.ewallet.repo.TbUserRepository;
import com.labkoding.product.ewallet.data.ewallet.request.SearchRequest;
import com.labkoding.product.ewallet.data.ewallet.response.DataVaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataVa")
public class DataVaController {
    @Autowired
    TbUserRepository tbUserRepository;

    @PostMapping("/search")
    public DataVaResponse searchDataVa(@RequestBody SearchRequest searchRequest) {
        String virtualAccount = searchRequest.getVirtualAccount();

        TbUser tbUser = tbUserRepository.findByVirtualAccount(virtualAccount).orElse(null);
        if (tbUser == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return new DataVaResponse(tbUser.getEmail(), tbUser.getVirtualAccount());
    }
}
