package org.studying;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.studying.entity.AccountTypes;
import org.studying.entity.BankAccount;
import org.studying.entity.BankClient;
import org.studying.repository.BankAccountRepositoryImpl;
import org.studying.repository.BankClientRepositoryImpl;
import org.studying.repository.DebitCardRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BankAccountRepositoryImpl bankAccountRepository = new BankAccountRepositoryImpl(entityManager);
        BankClientRepositoryImpl bankClientRepository = new BankClientRepositoryImpl(entityManager);
        DebitCardRepositoryImpl debitCardRepository = new DebitCardRepositoryImpl(entityManager);

        BankClient newClient = new BankClient();
        newClient.setFirstName("Nathan");
        newClient.setLastName("Santos");
//        newClient.setDateOfBirth("28/07/1993");
//        newClient.setDocumentId("382.502.378-89");
//        newClient.setOccupation("Software Dev.");
        newClient.setAccounts(generateAccounts());
        BankClient.print(newClient);
        bankClientRepository.save(newClient);


//        BankAccount newAcc = new BankAccount(newClient, AccountTypes.CHECKING);
//        System.out.println("Hello " + newAcc.getAccountHolder().getFirstName()
//        + "\nYour account number is: " + newAcc.getAccountNumber()
//        + "\nThis is a " + newAcc.getType() + " account."
//        + "\nThe current balance is: " + newAcc.getBalance()
//        + "\nNumber of cards allowed: " + newAcc.getIssuedCardsLimit()
//        + "\n-----------");
//
//        newAcc.deposit(-8564.15);
//        newAcc.deposit(3098.67);
//        System.out.println("Your current balance is now $" + newAcc.getBalance()
//        +"\n-----------");
//
//        newAcc.issueDebitCard();
//        System.out.println("Your current balance is now $" + newAcc.getBalance()
//                +"\n-----------");
//
//        newAcc.withdraw(-384.3);
//        newAcc.withdraw(4000);
//        newAcc.withdraw(48.67);
//        System.out.println("Your current balance is now $" + newAcc.getBalance()
//        +"\n-----------");

        entityManager.close();
        entityManagerFactory.close();
    }

    private static List<BankAccount> generateAccounts() {
        BankAccount accOne = new BankAccount(AccountTypes.CHECKING);
        BankAccount accTwo = new BankAccount(AccountTypes.SAVINGS);

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(accOne);
        accounts.add(accTwo);

        return accounts;
    }
}
