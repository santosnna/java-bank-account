package org.studying.repository;

import jakarta.persistence.EntityManager;
import org.studying.entity.BankAccount;
import org.studying.entity.BankClient;

import java.util.Optional;

public class BankAccountRepositoryImpl implements BankAccountRepository {
    private EntityManager entityManager;

    public BankAccountRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<BankAccount> save(BankAccount bankAccount) {
        try {
            entityManager.getTransaction().begin();
            if(bankAccount.getId() == null) {
                entityManager.persist(bankAccount);
            } else {
                bankAccount = entityManager.merge(bankAccount);
            }
            entityManager.getTransaction().commit();
            return Optional.of(bankAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Long id) {
        BankAccount bankAccount = entityManager.find(BankAccount.class, id);
        return bankAccount != null ? Optional.of(bankAccount) : Optional.empty();
    }

    @Override
    public void deleteBankAccount(BankAccount bankAccount) {
        entityManager.getTransaction().begin();
        if(entityManager.contains(bankAccount)) {
            entityManager.remove(bankAccount);
        } else {
            entityManager.merge(bankAccount);
        }
        entityManager.getTransaction().commit();
    }
}
