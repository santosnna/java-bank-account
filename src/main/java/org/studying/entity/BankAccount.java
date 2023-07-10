package org.studying.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name="bank_accounts")
public class BankAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_account_id")
    private Long id;
//    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    private BankClient accountHolder;
    @Column
    private String accountNumber;
    @Column
    private AccountTypes type; // Check how to use Enum
    @Column
    private double balance;
    @Column
    private ArrayList<DebitCard> debitCards = new ArrayList<DebitCard>();
    @Column
    private int issuedCardsLimit = 2;

    public BankAccount() {}

    public BankAccount(AccountTypes type) {
        this.accountNumber = generateAccountNumber();
        this.type = type;
        this.balance = 0;
    }

    public BankAccount(BankClient accountHolder, AccountTypes type) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.type = type;
        this.balance = 0;
    }

    public Long getId() {
        return id;
    }

    public BankClient getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountTypes getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountHolder(BankClient accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setType(AccountTypes type) {
        this.type = type;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<DebitCard> getDebitCards() {
        return debitCards;
    }

    public int getIssuedCardsLimit() {
        return issuedCardsLimit;
    }

    public void setIssuedCardsLimit(int issuedCardsLimit) {
        this.issuedCardsLimit = issuedCardsLimit;
    }

    private static String generateAccountNumber() {
        // Randomize 16 digits and concatenate to String
        String accNum = "";
        for(int i = 0; i < 16; i++) {
            accNum += (int)(Math.random() * 9);
        }
        return accNum;
    }

    public void deposit(double amount) {
        if(amount < 0) {
            System.out.println("Invalid amount.");
        } else {
            this.balance = this.balance + amount;
        }
    }

    public void withdraw(double amount) {
        if (amount < 0 || amount > this.balance) {
            System.out.println("Operation cannot be executed.");
            if(amount > this.balance) {
                System.out.println("Requested amount exceeds current balance.");
            }
        } else {
            this.balance = this.balance - amount;
        }
    }

    public void issueDebitCard() {
        if(debitCards.size() < issuedCardsLimit && issuedCardsLimit > 0) {
            DebitCard newCard = new DebitCard(this.accountHolder.getFullName());
            debitCards.add(newCard);
            issuedCardsLimit = issuedCardsLimit - 1;
            System.out.println("New card successfully issued."
            +"\n***Card Details***"
                    +"\n Card Holder: " + newCard.getCardHolder()
                    +"\n Card number: " + newCard.getCardNumber()
                    +"\n Expiration Date: " + newCard.getExpirationDate()
                    +"\n CVV number: " + newCard.getCvvNumber()
            +"\n-----------");
            System.out.println("You can issue " + issuedCardsLimit + " more card(s).");
        } else {
            System.out.println("Reached number of issue cards.");
        }
    }
}
