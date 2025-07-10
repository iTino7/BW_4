package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.Route;
import team2.entities.Transport;
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

    public void countNumberOfRuns(Transport transports, Route routes) {
        long transportId = transports.getTransport_id();
        long routeId = routes.getRoute_id();
        long numberOfRuns = countRunsByTransportAndRoute(transportId, routeId);
        System.out.println("Il " + transports.getClass().getSimpleName() + " (ID: " + transportId +
                ") ha percorso la tratta '" + routes.getDeparturePoint() + " - " +
                routes.getTerminusRoute() + "' " + numberOfRuns + " volte.");
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

    public void averageRunTime(Transport transports, Route routes) {
        long transportId = transports.getTransport_id();
        long routeId = routes.getRoute_id();
        Double avgTime = currentAverageTime(transportId, routeId);

        if (avgTime != null) {
            int roundedAvgTime = (int) (Math.round(avgTime * 100.0) / 100.0);
            System.out.println("Tempo medio di percorrenza per il " + transports.getClass().getSimpleName() + " (ID: " + transportId +
                    ") sulla tratta " + routes.getDeparturePoint() + " - " +
                    routes.getTerminusRoute() + " : " + roundedAvgTime + " minuti.");
        } else {
            System.out.println("Nessuna percorrenza trovata per il " + transports.getClass().getSimpleName() + " (ID: " + transportId +
                    ") sulla tratta " + routes.getDeparturePoint() + " - " +
                    routes.getTerminusRoute() + " .");
        }
    }

}
