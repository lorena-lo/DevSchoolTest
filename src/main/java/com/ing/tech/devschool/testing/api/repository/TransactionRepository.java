package com.ing.tech.devschool.testing.api.repository;

import com.ing.tech.devschool.testing.api.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
