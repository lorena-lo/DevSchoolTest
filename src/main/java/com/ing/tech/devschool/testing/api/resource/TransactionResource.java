package com.ing.tech.devschool.testing.api.resource;

import com.ing.tech.devschool.testing.api.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionResource {

    private TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping("{sender}/{receiver}/{amount}")
    public ResponseEntity<String> transfer(@PathVariable(value = "sender") Long senderAccountNumber,
                                           @PathVariable(value = "receiver") Long receiverAccountNumber,
                                           @PathVariable(value = "amount") Double amount) {

        boolean transactionResult =
            transactionService.transfer(senderAccountNumber, receiverAccountNumber, amount);

        if (!transactionResult) {
            return ResponseEntity.badRequest().body("Transaction failed.");
        }

        return ResponseEntity.ok().body("Transaction completed.");
    }

    @PutMapping("atm/{receiver}/{amount}")
    public ResponseEntity<String> atmTransaction(@PathVariable(value = "receiver") Long receiverAccountNumber,
                                                 @PathVariable(value = "amount") Double amount) {
        boolean transactionResult =
            transactionService.atmTransaction(receiverAccountNumber, amount);

        if (!transactionResult) {
            return ResponseEntity.badRequest().body("Transaction failed.");
        }

        return ResponseEntity.ok().body("Transaction completed.");
    }

}
