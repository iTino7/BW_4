package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.TravelTicket;
import team2.exceptions.NotFoundException;

import java.time.LocalDate;

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

    public void countTravelTicketByPeriod(LocalDate startDate, LocalDate endDate) {

        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(tt) FROM TravelTicket tt WHERE tt.issuedDate BETWEEN :startDate AND :endDate ", Long.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        Long count = query.getSingleResult();

        System.out.println("Nel periodo di tempo inserito sono stati emessi " + count + " titoli di viaggio.");
    }

//    Tieni traccia del numero di biglietti/abbonamenti emessi in un determinato periodo di tempo e per il rivenditore.

}
