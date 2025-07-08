package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Route;
import team2.exceptions.NotFoundException;

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
        if (found == null) throw new NotFoundException(routeId);
        return found;
    }

    // public void findByIdAndDelete(long routeId) {}


}
