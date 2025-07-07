package team2.entities;

import team2.entities.enums.TransportStatus;

import java.time.LocalDate;

public class Tram extends Transport {
    public Tram() {
    }

    public Tram(int maxPassengers, TransportStatus status, LocalDate firstServiceDay, int no0fTickets) {
        super(maxPassengers, status, firstServiceDay, no0fTickets);
    }
}
