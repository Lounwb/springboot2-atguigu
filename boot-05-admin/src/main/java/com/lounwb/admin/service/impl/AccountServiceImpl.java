package com.lounwb.admin.service.impl;

import com.lounwb.admin.bean.Account;
import com.lounwb.admin.mapper.AccountMapper;
import com.lounwb.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    public Account getActById(Integer id){
        return accountMapper.getActById(id);
    }
}
