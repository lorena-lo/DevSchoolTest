package com.ing.tech.devschool.testing.service;

import com.ing.tech.devschool.testing.api.model.Account;
import com.ing.tech.devschool.testing.api.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceIT {
    @Autowired
    private AccountService accountService;

    private long numberOfAccounts=5L;
    @Test
    public void shouldFindAnAccount() {
        String clientName="Mihai";
        long clientId=1L;
        Assert.assertEquals(clientName, accountService.findById(clientId).getClientName());
    }

    @Test
    public void shouldAddANewAccount() {
        accountService.createAccount("Eduard");
        Assert.assertEquals(accountService.findByClientName("Eduard").getClientName(),"Eduard");



    }

    @Test
    public void shouldNotFindAnyAccount() {
        Assert.assertNull(accountService.findById(600L));
    }

    @Test
    public void shouldDeleteAnAccount() {

        Account account =accountService.createAccount("Deni");
        boolean test=accountService.deleteIfExists(account.getAccountNumber());
        Assert.assertTrue(test);

    }

    @Test
    public void shouldFindAllAccounts() {
    Iterable<Account> accounts=accountService.findAll();
    Assert.assertEquals(numberOfAccounts,accounts.spliterator().getExactSizeIfKnown());
    }

}
