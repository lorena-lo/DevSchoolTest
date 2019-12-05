package com.ing.tech.devschool.testing;

import com.ing.tech.devschool.testing.api.model.Account;
import com.ing.tech.devschool.testing.api.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class TestingApplication implements CommandLineRunner {

    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

    @Override
    public void run(String... args) {
        accountRepository.save(Account.builder().totalBalance(1000.0).clientName("Mihai").build());
        accountRepository.save(Account.builder().totalBalance(1000.0).clientName("George").build());
        accountRepository.save(Account.builder().totalBalance(1000.0).clientName("Vasile").build());
        accountRepository.save(Account.builder().totalBalance(1000.0).clientName("Marian").build());
        accountRepository.save(Account.builder().totalBalance(1000.0).clientName("Ion").build());
    }

}
