package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.TransportTravelTicket;
import team2.exceptions.NotFoundException;

public class TransportTravelTicketDAO {
    private final EntityManager entityManager;

    public TransportTravelTicketDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(TransportTravelTicket ttt) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(ttt);
        transaction.commit();
        System.out.println("Record " + ttt.toString() + " creato correttamente!");
    }

    public TransportTravelTicket findById(long tttId) {
        TransportTravelTicket found = entityManager.find(TransportTravelTicket.class, tttId);
        if (found == null) throw new NotFoundException(tttId);
        return found;
    }

    public void findByIdAndDelete(long tttId) {
        TransportTravelTicket found = this.findById(tttId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("Record " + found.getId() + " rimosso correttamente!");

    }
}