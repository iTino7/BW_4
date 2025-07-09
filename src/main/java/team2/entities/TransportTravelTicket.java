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
    private Ticket ticket;
    @Column(name = "validation_dates")
    private LocalDate validationDate;

    public TransportTravelTicket() {
    }

    public TransportTravelTicket(Transport transport, Ticket ticket, LocalDate validationDate ) {
        this.transport = transport;
        this.ticket = ticket;
        this.validationDate = validationDate;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LocalDate getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(LocalDate validationDate) {
        this.validationDate = validationDate;
    }

    @Override
    public String toString() {
        return "TransportTravelTicket{" +
                "id=" + id +
                ", transport=" + transport +
                ", ticket=" + ticket +
                ", validationDate=" + validationDate +
                '}';
    }
}
