package team2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Ticket")
public class Ticket extends TravelTicket {

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;


    public Ticket() {
    }

    public Ticket(LocalDate validationDate) {
        this.validationDate = validationDate;
    }

    public LocalDate getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(LocalDate validationDate) {
        this.validationDate = validationDate;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", transportId=" + (transport != null ? transport.getTransport_id() : "null") +
                ", validationDate=" + validationDate +
                '}';
    }
}
