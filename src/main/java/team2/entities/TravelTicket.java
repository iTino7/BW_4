package team2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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
    private Reseller reseller;

    @OneToMany(mappedBy = "ticket")
    private List<Transport> transportList;

    public TravelTicket() {
    }

    public TravelTicket(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public long getId() {
        return id;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
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
