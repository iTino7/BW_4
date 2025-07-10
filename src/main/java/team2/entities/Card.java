package team2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "card")
    private List<Pass> passList;

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

    public List<Pass> getPassList() {
        return passList;
    }

    public void setPassList(List<Pass> passList) {
        this.passList = passList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
