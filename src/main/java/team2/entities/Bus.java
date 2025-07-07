package team2.entities;

import team2.entities.enums.TransportStatus;

import java.time.LocalDate;

public class Bus extends Transport {
    public Bus() {}

    public Bus (int max_passengers, TransportStatus status, LocalDate first_service_day, int no_of_tickets) {
        super(max_passengers, status, first_service_day, no_of_tickets);
    }
}
