package org.studying.repository;

import jakarta.persistence.EntityManager;
import org.studying.entity.BankClient;

import java.util.Optional;

public class BankClientRepositoryImpl implements BankClientRepository{
    private EntityManager entityManager;

    public BankClientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<BankClient> save(BankClient bankClient) {
        try {
            entityManager.getTransaction().begin();
            if(bankClient.getId() == null) {
                entityManager.persist(bankClient);
            } else {
                bankClient = entityManager.merge(bankClient);
            }
            entityManager.getTransaction().commit();
            return Optional.of(bankClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<BankClient> getBankClientById(Long id) {
        BankClient bankClient = entityManager.find(BankClient.class, id);
        return bankClient != null ? Optional.of(bankClient) : Optional.empty();
    }

    @Override
    public void deleteBankClient(BankClient bankClient) {
        entityManager.getTransaction().begin();
        if(entityManager.contains(bankClient)) {
            entityManager.remove(bankClient);
        } else {
            entityManager.merge(bankClient);
        }
        entityManager.getTransaction().commit();
    }
}
