package team2.entities;

import jakarta.persistence.*;
import team2.entities.enums.TransportStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transports")
public abstract class Transport {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transport_id;
    private int maxPassengers;

    private LocalDate firstServiceDay;

    @Enumerated(EnumType.STRING)
    private TransportStatus status;

    private int no0fTickets;

    @ManyToMany(mappedBy = "transportList")
    private List<Maintenance> maintenanceList;

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
