package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.TravelTicket;
import team2.exceptions.NotFoundException;

public class TravelTicketDAO {
    private final EntityManager entityManager;

    public TravelTicketDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(TravelTicket newTicket) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newTicket);
        transaction.commit();
        System.out.println("Ticket " + newTicket.toString() + " creato correttamente!");
    }

    public TravelTicket findById(long ticketId) {
        TravelTicket found = entityManager.find(TravelTicket.class, ticketId);
        if (found == null) throw new NotFoundException(ticketId);
        return found;
    }

    public void findByIdAndDelete(long ticketId) {
        TravelTicket found = this.findById(ticketId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("Ticket " + found.getId() + " rimosso correttamente!");

    }
}
