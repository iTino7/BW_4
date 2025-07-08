package team2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "travel_tickets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_of_tickets")
public abstract class TravelTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column (name = "issued_date")
    private LocalDate issuedDate;

    @ManyToMany(mappedBy = "travelTicketList")
    private List<Reseller> resellerList;

    public TravelTicket() {
    }

    public TravelTicket(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public long getId() {
        return id;
    }


    public List<Reseller> getResellerList() {
        return resellerList;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    @Override
    public String toString() {
        return "TravelTicket{" +
                "id=" + id +
                ", issuedDate=" + issuedDate +
                '}';
    }
}
