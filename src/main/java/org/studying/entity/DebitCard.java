package org.studying.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "debit_cards")
public class DebitCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String cardHolder;
    @Column
    private String cardNumber;
    @Column
    private String expirationDate;
    @Column
    private String cvvNumber;

    public DebitCard() {}

    public DebitCard(String cardHolder) {
        this.cardHolder = cardHolder;
        this.cardNumber = generateCardNumber();
        this.expirationDate = generateExpirationDate();
        this.cvvNumber = generateCvvNumber();
    }

    public Long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    private static String generateCardNumber() {
        // Randomize 16 digits and concatenate to String
            // In the future, search for card number creation logic

        String cardNum = "";
        for(int i = 0; i < 16; i++) {
            cardNum += (int)(Math.random() * 9);
        }
        return cardNum;
    }

    private static String generateCvvNumber() {
        // Randomize 3 and concatenate to String
        String cvvNum = "";
        for(int i = 0; i < 3; i++) {
            cvvNum += (int)(Math.random() * 9);
        }
        return cvvNum;
    }

    private static String generateExpirationDate() {
        // Check current date
        LocalDate currentDate = LocalDate.now();

        // Retrieve month (mm) and year (yy) from current date
        String MM = "";
        if(currentDate.getMonthValue() < 10) {
            MM = "0" + Integer.toString(currentDate.getMonthValue());
        } else {
            MM = Integer.toString(currentDate.getMonthValue());
        }

        // Add 4 to current year
        String YY = "";
        if(!(currentDate.getYear() >= 96)) {
            YY = Integer.toString(currentDate.getYear() + 4).substring(2);
        // If current year + 4 goes over 99
        } else {
            // set date back from 00
            YY = Integer.toString(currentDate.getYear() + 4 - 100).substring(2);
        }

        // Build string mm/yy
        return MM + "/" + YY ;
    }

    @Override
    public String toString() {
        return "\nCard Number: " + this.getCardNumber() +
                "\nCard Holder:" + this.getCardHolder() +
                "\nCVV: " + this.getCvvNumber() +
                "\nExpiration Date: " + this.getExpirationDate() +
                "\n";
    }

    public static void print(Optional<DebitCard> optional) {
        System.out.println(optional.toString());
    }
}
