package org.studying.repository;

import org.studying.entity.DebitCard;

import java.util.Optional;

public interface DebitCardRepository {
    Optional<DebitCard> save(DebitCard debitCard);
    Optional<DebitCard> getDebitCardById(Long id);
    void deleteBankClient(DebitCard debitCard);
}
