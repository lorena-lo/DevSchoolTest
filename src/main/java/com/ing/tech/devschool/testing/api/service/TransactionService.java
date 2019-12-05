package com.ing.tech.devschool.testing.api.service;

import com.ing.tech.devschool.testing.api.model.Account;
import com.ing.tech.devschool.testing.api.model.Transaction;
import com.ing.tech.devschool.testing.api.repository.AccountRepository;
import com.ing.tech.devschool.testing.api.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public boolean transfer(Long senderAccountNumber, Long receiverAccountNumber, Double amount) {
        Optional<Account> sender = accountRepository.findById(senderAccountNumber);
        Optional<Account> receiver = accountRepository.findById(receiverAccountNumber);

        if (!sender.isPresent() || !receiver.isPresent()) {
            return false;
        }

        Double senderBalance = sender.get().getTotalBalance();
        Double receiverBalance = receiver.get().getTotalBalance();

        if (amount < 0 || senderBalance - amount < 0) {
            return false;
        }

        sender.get().setTotalBalance(senderBalance - amount);
        receiver.get().setTotalBalance(receiverBalance + amount);

        Transaction transaction = Transaction.builder().senderAccountNumber(senderAccountNumber)
            .receiverAccountNumber(receiverAccountNumber).amount(amount).build();

        transactionRepository.save(transaction);

        return true;
    }

    @Transactional
    public boolean atmTransaction(Long receiverAccountNumber, Double amount) {
        Optional<Account> receiver = accountRepository.findById(receiverAccountNumber);

        if (!receiver.isPresent() || amount == 0) {
            return false;
        }

        Double receiverBalance = receiver.get().getTotalBalance();

        if (amount + receiverBalance < 0) {
            return false;
        }

        receiver.get().setTotalBalance(receiverBalance + amount);

        Transaction transaction = Transaction.builder().senderAccountNumber(0L)
            .receiverAccountNumber(receiverAccountNumber).amount(amount).build();

        transactionRepository.save(transaction);

        return true;
    }

}
