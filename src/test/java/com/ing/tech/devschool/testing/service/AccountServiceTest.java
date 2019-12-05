package com.ing.tech.devschool.testing.service;

import com.ing.tech.devschool.testing.api.model.Account;
import com.ing.tech.devschool.testing.api.repository.AccountRepository;
import com.ing.tech.devschool.testing.api.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void shouldReturnAnAccount() {
        Mockito.when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Account()));

        assertNotNull(accountService.findById(anyLong()));
//        Mockito.when(accountRepository.findById(2L))
//                .thenReturn(Optional.of(new Account()));
//
//        assertNotNull(accountService.findById(2L));
    }

    @Test
    public void shouldReturnNullWhenAccountNotFound() {
        Mockito.when(accountRepository.findById(2L))
                .thenReturn(Optional.empty());
        assertNull(accountService.findById(2L));


    }

    @Test
    public void shouldCreateAnAccount() {
        String clientName="clientName";
        Account expected = Account.builder().clientName(clientName).totalBalance(0.0).build();
        Account actual= accountService.createAccount(clientName);
        Mockito.verify(accountRepository,Mockito.times(1)).save(expected);
        assertEquals(expected,actual);
    }

    @Test
    public void shouldDeleteAnAccount() {
        String clientName="clientName";
        Account account = Account.builder().clientName(clientName).totalBalance(0.0).accountNumber(2L).build();
        Mockito.when(accountRepository.findById(2L))
                .thenReturn(Optional.of(account));
        boolean result= accountService.deleteIfExists(account.getAccountNumber());
        Mockito.verify(accountRepository,times(1)).delete(account);
        assertTrue(result);


    }

    @Test
    public void shouldNotDeleteAnAccount() {
        boolean result=accountService.deleteIfExists(4534534534L);
        assertFalse(result);
    }

}
