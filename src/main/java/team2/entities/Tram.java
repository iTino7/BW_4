package team2.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import team2.entities.enums.TransportStatus;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Tram")
public class Tram extends Transport {
    public Tram() {
    }

    public Tram(int maxPassengers, TransportStatus status, LocalDate firstServiceDay, int no0fTickets) {
        super(maxPassengers, status, firstServiceDay, no0fTickets);
    }
}
