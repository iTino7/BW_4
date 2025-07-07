package Team_2.entities;

import Team_2.entities.enums.TransportStatus;

import java.time.LocalDate;

public abstract class Transport {

    private long transport_id;
    private int maxPassengers;

    private LocalDate firstServiceDay;

    private TransportStatus status;

    private int no0fTickets;

    public Transport() {
    }

    public Transport(int maxPassengers, TransportStatus status, LocalDate firstServiceDay, int no0fTickets) {
        this.maxPassengers = maxPassengers;
        this.status = status;
        this.firstServiceDay = firstServiceDay;
        this.no0fTickets = no0fTickets;
    }

    public long getTransport_id() {
        return transport_id;
    }


    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public TransportStatus getStatus() {
        return status;
    }

    public void setStatus(TransportStatus status) {
        this.status = status;
    }

    public LocalDate getFirstServiceDay() {
        return firstServiceDay;
    }

    public void setFirstServiceDay(LocalDate firstServiceDay) {
        this.firstServiceDay = firstServiceDay;
    }

    public int getNo0fTickets() {
        return no0fTickets;
    }

    public void setNo0fTickets(int no0fTickets) {
        this.no0fTickets = no0fTickets;
    }


    @Override
    public String toString() {
        return "Transport{" +
                "transport_id=" + transport_id +
                ", maxPassengers=" + maxPassengers +
                ", firstServiceDay=" + firstServiceDay +
                ", status=" + status +
                ", no0fTickets=" + no0fTickets +
                '}';
    }
}
