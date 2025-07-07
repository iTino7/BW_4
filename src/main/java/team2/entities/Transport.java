package team2.entities;

import team2.entities.enums.TransportStatus;

import java.time.LocalDate;

public abstract class Transport {

    private long transport_id;
    private int max_passengers;

    private LocalDate first_service_day;

    private TransportStatus status;

    private int no_of_tickets;

    public Transport() {
    }

    public Transport(int max_passengers, TransportStatus status, LocalDate first_service_day, int no_of_tickets) {
        this.max_passengers = max_passengers;
        this.status = status;
        this.first_service_day = first_service_day;
        this.no_of_tickets = no_of_tickets;
    }

    public long getTransport_id() {
        return transport_id;
    }

    public int getMax_passengers() {
        return max_passengers;
    }

    public void setMax_passengers(int max_passengers) {
        this.max_passengers = max_passengers;
    }

    public LocalDate getFirst_service_day() {
        return first_service_day;
    }

    public void setFirst_service_day(LocalDate first_service_day) {
        this.first_service_day = first_service_day;
    }

    public TransportStatus getStatus() {
        return status;
    }

    public void setStatus(TransportStatus status) {
        this.status = status;
    }

    public int getNo_of_tickets() {
        return no_of_tickets;
    }

    public void setNo_of_tickets(int no_of_tickets) {
        this.no_of_tickets = no_of_tickets;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transport_id=" + transport_id +
                ", max_passengers=" + max_passengers +
                ", first_service_day=" + first_service_day +
                ", status=" + status +
                ", no_of_tickets=" + no_of_tickets +
                '}';
    }
}
