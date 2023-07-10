package org.studying.repository;

import jakarta.persistence.EntityManager;
import org.studying.entity.DebitCard;

import java.util.Optional;

public class DebitCardRepositoryImpl implements DebitCardRepository{
    private EntityManager entityManager;

    public DebitCardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<DebitCard> save(DebitCard debitCard) {
        try {
            entityManager.getTransaction().begin();
            if(debitCard.getId() == null) {
                entityManager.persist(debitCard);
            } else {
                entityManager.merge(debitCard);
            }
            entityManager.getTransaction().commit();
            return Optional.of(debitCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<DebitCard> getDebitCardById(Long id) {
        DebitCard debitCard = entityManager.find(DebitCard.class, id);
        return debitCard != null ? Optional.of(debitCard) : Optional.empty();
    }

    @Override
    public void deleteBankClient(DebitCard debitCard) {
        entityManager.getTransaction().begin();
        if(entityManager.contains(debitCard)) {
            entityManager.remove(debitCard);
        } else {
            entityManager.merge(debitCard);
        }
        entityManager.getTransaction().commit();
    }
}
