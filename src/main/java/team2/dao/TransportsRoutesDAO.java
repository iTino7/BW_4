package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.TransportRoute;
import team2.exceptions.NoResultException;
import team2.exceptions.NotFoundException;

public class TransportsRoutesDAO {
    private final EntityManager entityManager;

    public TransportsRoutesDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(TransportRoute newTransportRoute) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newTransportRoute);
        transaction.commit();
        System.out.println("Route run recorded: " + newTransportRoute.toString());
    }

    public TransportRoute findById(long id) {
        TransportRoute found = entityManager.find(TransportRoute.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public long countRunsByTransportAndRoute(long transportId, long routeId) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(rr) FROM TransportRoute rr WHERE rr.transports.transport_id = :transportId AND rr.routes.route_id = :routeId", Long.class);
        query.setParameter("transportId", transportId);
        query.setParameter("routeId", routeId);
        return query.getSingleResult();
    }

    public Double currentAverageTime(long transportId, long routeId) {
        TypedQuery<Double> query = entityManager.createQuery("SELECT AVG(rr.actualTimeMin) FROM TransportRoute rr WHERE rr.transports.transport_id = :transportId AND rr.routes.route_id = :routeId", Double.class);
        query.setParameter("transportId", transportId);
        query.setParameter("routeId", routeId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
