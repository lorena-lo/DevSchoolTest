package com.ing.tech.devschool.testing.api.repository;

import com.ing.tech.devschool.testing.api.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByClientName(String clientName);
}
