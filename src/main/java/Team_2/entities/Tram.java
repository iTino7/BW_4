package Team_2.entities;

import Team_2.entities.enums.TransportStatus;

import java.time.LocalDate;

public class Tram extends Transport {
    public Tram() {
    }

    public Tram(int max_passengers, TransportStatus status, LocalDate first_service_day, int no_of_tickets) {
        super(max_passengers, status, first_service_day, no_of_tickets);
    }
}
