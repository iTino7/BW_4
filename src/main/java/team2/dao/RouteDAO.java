package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.Route;
import team2.exceptions.RecordNotFoundException;

public class RouteDAO {
    private final EntityManager entityManager;

    public RouteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Route newRoute) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newRoute);
        transaction.commit();
        System.out.println("La Route " + newRoute.getRoute_id() + " è stata creata correttamente!");
    }

    public Route findById(long routeId) {
        Route found = entityManager.find(Route.class, routeId);
        if (found == null) throw new RecordNotFoundException("Tratta", routeId);
        return found;
    }

    // public void findByIdAndDelete(long routeId) {}
    public void getAllRoutes() {
        TypedQuery<Route> query = entityManager.createQuery(
                "SELECT r FROM Route r", Route.class);
        query.getResultList().forEach(System.out::println);
    }

    public Route findRoutesByDestination(String destination) {
        TypedQuery<Route> query = entityManager.createQuery(
                "SELECT r FROM Route r WHERE r.terminusRoute = :destination", Route.class);
        query.setParameter("destination", destination);
        return query.getSingleResultOrNull();
    }
}
