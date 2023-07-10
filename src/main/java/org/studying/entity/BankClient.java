package org.studying.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "bank_clients")
public class BankClient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bank_client_id")
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String dateOfBirth;
    @Column
    private String documentId;
    @Column
    private String occupation;
    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_client_id")
    private List<BankAccount> accounts = new ArrayList<>();

    public BankClient() {}

    public BankClient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public BankClient(String firstName, String lastName, String dateOfBirth, String documentId, String occupation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.documentId = documentId;
        this.occupation = occupation;
    }

    public BankClient(String firstName, String lastName, String dateOfBirth, String documentId, String occupation, BankAccount account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.documentId = documentId;
        this.occupation = occupation;
        this.accounts.add(account);
    }

    public BankClient(String firstName, String lastName, String dateOfBirth, String documentId, String occupation, List<BankAccount> accounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.documentId = documentId;
        this.occupation = occupation;
        this.accounts = accounts;
    }

    public Long getId() { return id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getFullName() { return firstName + " " + lastName; }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getOccupation() {
        return occupation;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAccount(BankAccount bankAccount) {
        this.accounts.add(bankAccount);
    }
    public void setAccounts(List<BankAccount> bankAccounts){
        this.accounts.addAll(bankAccounts);
    }

    @Override
    public String toString() {
        return "\nName: " + this.getFullName() +
                "\nDate of Birth: " + this.getDateOfBirth() +
                "\nDocument Id: " + this.getDocumentId() +
                "\nOccupation: " + this.getOccupation() +
                "\nAccounts: " + this.getAccounts() +
                "\n";
    }

    public static void print(BankClient client) {
        System.out.println(client.toString());
    }
    public static void print(Optional<BankClient> optional) {
        System.out.println(optional.toString());
    }


}
