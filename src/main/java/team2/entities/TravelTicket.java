package team2.entities;

import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.List;

public abstract class TravelTicket {
    protected long id;
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
