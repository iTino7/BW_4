package Team_2.entities;

public class Route {
    private long route_id;
    private String departurePoint;
    private String terminusRoute;
    private double estimatedTime;

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
