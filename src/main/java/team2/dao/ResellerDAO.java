package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.Reseller;
import team2.exceptions.RecordNotFoundException;

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
        System.out.println("Venditore " + newReseller.toString() + " creato correttamente!");
    }

    public Reseller findById(long resellerId) {
        Reseller found = entityManager.find(Reseller.class, resellerId);
        if (found == null) throw new RecordNotFoundException("Reseller", resellerId);
        return found;
    }

    public void findByIdAndDelete(long resellerId) {
        Reseller found = this.findById(resellerId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("Venditore " + found.getResellerId() + " rimosso correttamente!");

    }

    public void countTicketAndPassesByReseller(long resellerId) {
        Reseller found = this.findById(resellerId);

        TypedQuery<Long> firstQuery = entityManager.createQuery(
                "SELECT COUNT(t) FROM Reseller r JOIN r.travelTicketList t WHERE r.resellerId = :resellerId AND TYPE(t) = Pass",
                Long.class);
        TypedQuery<Long> secondQuery = entityManager.createQuery(
                "SELECT COUNT(t) FROM Reseller r JOIN r.travelTicketList t WHERE r.resellerId = :resellerId AND TYPE(t) = Ticket",
                Long.class);

        firstQuery.setParameter("resellerId", resellerId);
        secondQuery.setParameter("resellerId", resellerId);

        Long passCount = firstQuery.getSingleResultOrNull();
        Long ticketCount = secondQuery.getSingleResultOrNull();
        if (passCount == null || ticketCount == null) {
            System.out.println("Ops.. Qualcosa è andato storto.");
        }
        System.out.println("Il venditore con id " + resellerId + " ha venduto " + (passCount + found.getIssuedPasses()) + " abbonamenti e " + (ticketCount + found.getIssuedTicket()) + " biglietti.");
    }

}
