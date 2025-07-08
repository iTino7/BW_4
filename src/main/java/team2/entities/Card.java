package team2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "activation_dates")
    private LocalDate activationDate;
    @Column(name = "expiring_dates")
    private LocalDate expiringDate;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User owner;

    public Card() {
    }

    ;

    public Card(LocalDate activationDate, User owner) {
        this.owner = owner;
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
