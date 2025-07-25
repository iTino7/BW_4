package team2.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate;
    @Column(name = "final_date")
    private LocalDate finalDate;

    @ManyToOne
    @JoinColumn(name = "transport_id", nullable = false)
    private Transport vehicles;


    public Maintenance() {
    }

    public Maintenance(LocalDate startingDate, LocalDate finalDate, Transport vehicles) {
        this.startingDate = startingDate;
        this.finalDate = finalDate;
        this.vehicles = vehicles;
    }

    public long getId() {
        return id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", finalDate=" + finalDate +
                '}';
    }
}
