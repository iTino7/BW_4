package team2.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long route_id;

    @Column(name = "departure_point", nullable = false)
    private String departurePoint;

    @Column(name = "terminus_route", nullable = false)
    private String terminusRoute;

    @Column(name = "estimated_time", nullable = false)
    private double estimatedTime;

    @ManyToMany
    @JoinTable(name = "transports_routes",
            joinColumns = @JoinColumn(name = "route_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "transport_id", nullable = false)
    )
    private List<Transport> transportsList2;

    @OneToMany(mappedBy = "routes")
    private List<TransportRoute> transportRouteList;

    public Route() {
    }

    public Route(String departurePoint, String terminusRoute, double estimatedTime) {
        this.departurePoint = departurePoint;
        this.terminusRoute = terminusRoute;
        this.estimatedTime = estimatedTime;
    }

    public long getRoute_id() {
        return route_id;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getTerminusRoute() {
        return terminusRoute;
    }

    public void setTerminusRoute(String terminusRoute) {
        this.terminusRoute = terminusRoute;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return "Route{" +
                "route_id=" + route_id +
                ", departurePoint='" + departurePoint + '\'' +
                ", terminusRoute='" + terminusRoute + '\'' +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}
