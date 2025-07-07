package team2.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate;
    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;

    @ManyToMany
    @JoinTable (name = "transports_maintenances",
    joinColumns = @JoinColumn(name = "maintenance_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "transport_id", nullable = false)
    )
    private List<Transport> transportList;


    public Maintenance () {}

    public Maintenance(LocalDate startingDate, LocalDate finalDate) {
        this.startingDate = startingDate;
        this.finalDate = finalDate;
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
