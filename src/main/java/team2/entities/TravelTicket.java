package team2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "travel_tickets")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_of_tickets")
public abstract class TravelTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "reseller_id")
    private Reseller reseller;

    public TravelTicket() {
    }

    public TravelTicket(LocalDate issuedDate, Reseller reseller) {
        this.reseller = reseller;
        this.issuedDate = issuedDate;
    }

    public long getId() {
        return id;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }


    @Override
    public String toString() {
        return "TravelTicket{" +
                "id=" + id +
                ", issuedDate=" + issuedDate +
                '}';
    }
}
