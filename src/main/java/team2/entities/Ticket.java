package team2.entities;

import java.time.LocalDate;

public class Ticket extends TravelTicket {
    private boolean isActive;

    public Ticket(LocalDate issuedDate) {
        super(issuedDate);
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
