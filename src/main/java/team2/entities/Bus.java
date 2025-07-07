package team2.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import team2.entities.enums.TransportStatus;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Bus")
public class Bus extends Transport {
    public Bus() {}

    public Bus (int maxPassengers, TransportStatus status, LocalDate firstServiceDay, int no0fTickets) {
        super(maxPassengers, status, firstServiceDay, no0fTickets);
    }
}
