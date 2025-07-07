package team2.entities;

import java.time.LocalDate;

public class Card {
    private long id;
    private LocalDate activationDate;
    private LocalDate expiringDate;

    public Card() {
    }

    ;

    public Card(LocalDate activationDate) {
        this.activationDate = activationDate;
        this.expiringDate = activationDate.plusYears(1);
    }

    ;

    public long getId() {
        return id;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDate getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", activationDate=" + activationDate +
                ", expiringDate=" + expiringDate +
                '}';
    }
}
