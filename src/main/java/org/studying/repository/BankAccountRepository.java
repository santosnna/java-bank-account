package org.studying.repository;

import org.studying.entity.BankAccount;

import java.util.Optional;

public interface BankAccountRepository {
    Optional<BankAccount> save(BankAccount bankAccount);
    Optional<BankAccount> getBankAccountById(Long id);
    void deleteBankAccount(BankAccount bankAccount);
}
