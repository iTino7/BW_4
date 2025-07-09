package team2.entities;

import jakarta.persistence.*;
import team2.entities.enums.ResellerStatusType;

import java.util.List;

@Entity
@Table(name = "resellers")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "reseller_type")
public abstract class Reseller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long resellerId;

    @Column(name = "issued_tickets", nullable = false)
    protected long issuedTicket;

    @Column(name = "issued_passes", nullable = false)
    protected long issuedPasses;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    protected ResellerStatusType status;


    @OneToMany
    private List<TravelTicket> travelTicketList;


    public Reseller() {
    }

    ;

    public Reseller(ResellerStatusType status) {
        this.status = status;
        this.issuedTicket = 0;
        this.issuedPasses = 0;

    }

    ;

    public long getResellerId() {
        return resellerId;
    }


    public long getIssuedTicket() {
        return issuedTicket;
    }

    public void setIssuedTicket(long issuedTicket) {
        this.issuedTicket = issuedTicket;
    }

    public long getIssuedPasses() {
        return issuedPasses;
    }

    public void setIssuedPasses(long issuedPasses) {
        this.issuedPasses = issuedPasses;
    }

    public ResellerStatusType getStatus() {
        return status;
    }

    public void setStatus(ResellerStatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reseller{" +
                "resellerId=" + resellerId +
                ", issuedTicket=" + issuedTicket +
                ", issuedPasses=" + issuedPasses +
                ", status=" + status +
                '}';
    }
}
