package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.Transport;
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

    //Conto di biglietti convalidati per periodo
    public long countValidatedTicketsByPeriod(LocalDate startDate, LocalDate endDate) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.validationDate IS NOT NULL AND t.validationDate BETWEEN :startDate AND :endDate",
                Long.class
        );
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getSingleResult();
    }

    //conto di biglietti convalidati per mezzo di trasporto e periodo
    public long countValidatedTicketsByTransportAndPeriod(Transport transport, LocalDate startDate, LocalDate endDate) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.transport = :transport AND t.validationDate BETWEEN :startDate AND :endDate",
                Long.class
        );
        query.setParameter("transport", transport);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getSingleResult();
    }







}
