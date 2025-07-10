package team2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transports_travel_tickets")
public class TransportTravelTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "transport_id", nullable = false)
    private Transport transport;
    @ManyToOne
    @JoinColumn(name = "travel_ticket_id", nullable = false)
    private TravelTicket ticket;

    public TransportTravelTicket() {
    }

    public TransportTravelTicket(Transport transport, TravelTicket ticket, LocalDate validationDate) {
        this.transport = transport;
        this.ticket = ticket;
    }

    public long getId() {
        return id;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public TravelTicket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "TransportTravelTicket{" +
                "id=" + id +
                ", transport=" + transport +
                ", ticket=" + ticket +
                '}';
    }
}
