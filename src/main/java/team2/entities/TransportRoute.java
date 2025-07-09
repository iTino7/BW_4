package team2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "transports_routes")
public class TransportRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route routes;
    @ManyToOne
    @JoinColumn(name = "trasport_id", nullable = false)
    private Transport transports;
    @Column(name = "actual_time_min")
    private double actualTimeMin;

    public TransportRoute() {
    }

    public TransportRoute(Route routes, Transport transports, double actualTimeMin) {
        this.routes = routes;
        this.transports = transports;
        this.actualTimeMin = actualTimeMin;
    }

    public long getId() {
        return id;
    }

    public Route getRoutes() {
        return routes;
    }

    public void setRoutes(Route routes) {
        this.routes = routes;
    }

    public Transport getTransports() {
        return transports;
    }

    public void setTransports(Transport transports) {
        this.transports = transports;
    }

    public double getActualTimeMin() {
        return actualTimeMin;
    }

    public void setActualTimeMin(double actualTimeMin) {
        this.actualTimeMin = actualTimeMin;
    }

    @Override
    public String toString() {
        return "TransportRoute{" +
                "id=" + id +
                ", routes=" + routes +
                ", transports=" + transports +
                ", actualTimeMin=" + actualTimeMin +
                '}';
    }
}

