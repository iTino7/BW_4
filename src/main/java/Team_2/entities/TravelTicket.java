package Team_2.entities;

import java.time.LocalDate;

public abstract class TravelTicket {
    protected long id;
    private LocalDate issuedDate;

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
