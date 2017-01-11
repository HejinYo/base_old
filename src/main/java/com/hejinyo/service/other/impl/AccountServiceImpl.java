package com.hejinyo.service.other.impl;

import com.hejinyo.mapper.other.AccountMapper;
import com.hejinyo.model.other.Account;
import com.hejinyo.service.other.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Account Service 实现类
 */

@Service(value = "accountsService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public List<Account> getAllAccounts(Account account) {
        return accountMapper.getAllAccounts(account);
    }

    @Override
    public List<Account> getTest(String account) {
        return accountMapper.getTest(account);
    }
}
