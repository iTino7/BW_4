package team2.entities;

import jakarta.persistence.*;
import team2.entities.enums.ResellerStatusType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resellers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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


    @ManyToMany
    @JoinTable(name = "resellers_tickets", joinColumns = @JoinColumn(name = "reseller_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "travel_ticket_id", nullable = false)
    )
    private List<TravelTicket> travelTicketList;


    public Reseller() {
    }



    public Reseller(ResellerStatusType status, int issuedTicket, int issuedPasses) {
        this.status = status;
        this.issuedTicket = issuedTicket;
        this.issuedPasses = issuedPasses;

    }



    public long getResellerId() {
        return resellerId;
    }


    public List<TravelTicket> getTravelTicketList() {
        return travelTicketList;
    }

    public void setTravelTicketList(List<TravelTicket> travelTicketList) {
        this.travelTicketList = travelTicketList;
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
