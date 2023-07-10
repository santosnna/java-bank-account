package org.studying.repository;

import org.studying.entity.BankClient;

import java.util.Optional;

public interface BankClientRepository {
    Optional<BankClient> save(BankClient bankClient);
    Optional<BankClient> getBankClientById(Long id);
    void deleteBankClient(BankClient bankClient);
}
