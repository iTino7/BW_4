package team2.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Ticket")
public class Ticket extends TravelTicket {
    private boolean isActive;

    public Ticket() {
    }

    public Ticket(LocalDate issuedDate, Reseller reseller) {
        super(issuedDate, reseller);
        this.isActive = true;
    }

    ;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "isActive=" + isActive +
                ", id=" + id +
                '}';
    }
}
