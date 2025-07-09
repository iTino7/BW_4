package team2.entities;

import jakarta.persistence.*;
import team2.entities.enums.TransportStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transports")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_of_transportation")
public abstract class Transport {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transport_id;
    @Column(name = "max_passengers")
    private int maxPassengers;
    @Column(name = "first_service_day")
    private LocalDate firstServiceDay;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransportStatus status;
    @Column(name = "number_of_tickets")
    private int no0fTickets;

    @OneToMany(mappedBy = "vehicles")
    private List<Maintenance> maintenanceList;

    @ManyToMany(mappedBy = "transportsList2")
    private List<Route> routeList;

    @OneToMany(mappedBy = "transports")
    private List<TransportRoute> transportRouteList;


    @OneToMany(mappedBy = "transport")
    private List<TransportTravelTicket> transportTravelTickets;


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

    public List<Maintenance> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(List<Maintenance> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
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
