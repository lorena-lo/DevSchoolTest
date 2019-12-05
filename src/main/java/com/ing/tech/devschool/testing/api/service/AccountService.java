package com.ing.tech.devschool.testing.api.service;

import com.ing.tech.devschool.testing.api.model.Account;
import com.ing.tech.devschool.testing.api.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);

        return account.orElse(null);
    }

    public Account findByClientName(String name) {
        Optional<Account> account = accountRepository.findByClientName(name);

        return account.orElse(null);
    }

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account createAccount(String clientName) {
        Account newAccount = Account.builder().clientName(clientName).totalBalance(0.0).build();

        accountRepository.save(newAccount);

        return newAccount;
    }

    public boolean deleteIfExists(Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (!account.isPresent()) {
            return false;
        }
        accountRepository.delete(account.get());

        return true;
    }

}
