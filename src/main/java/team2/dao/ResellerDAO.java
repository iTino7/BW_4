package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.Reseller;
import team2.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public class ResellerDAO {
    private final EntityManager entityManager;

    public ResellerDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Reseller newReseller) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newReseller);
        transaction.commit();
        System.out.println("Reseller " + newReseller.toString() + " creato correttamente!");
    }

    public Reseller findById(long resellerId) {
        Reseller found = entityManager.find(Reseller.class, resellerId);
        if (found == null) throw new NotFoundException(resellerId);
        return found;
    }

    public void findByIdAndDelete(long resellerId) {
        Reseller found = this.findById(resellerId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("Reseller " + found.getResellerId() + " rimosso correttamente!");

    }

    public long countByResellerAndPeriod(long resellerId, LocalDate startDate, LocalDate endDate, String type) {
        Reseller found = this.findById(resellerId);
        long ticketType;
        String ticketTypeQuery = "";


        if (type.equals("Pass")) {
            ticketTypeQuery = "AND TYPE(t) = Pass";
            ticketType = found.getIssuedPasses();
        } else {
            ticketTypeQuery = "AND TYPE(t) = Ticket";
            ticketType = found.getIssuedTicket();
        }

        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(t) FROM Reseller r JOIN r.travelTicketList t " +
                        "WHERE r.resellerId = :resellerId AND t.issuedDate BETWEEN :startDate AND :endDate " + ticketTypeQuery ,
                Long.class
        );
        query.setParameter("resellerId", resellerId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        Long count = query.getSingleResult();


        return count + ticketType;
    }

}
