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

    public int howManyTicketPerPeriod(LocalDate firstDate, LocalDate secondDate) {
        int sum = 0;
        TypedQuery<Reseller> query = entityManager.createQuery("SELECT r FROM Reseller r JOIN travelTicketList WHERE r.travelTicketList.element(issuedDate) BETWEEN :firstDate AND :secondDate" , Reseller.class );
        query.setParameter("firstDate" , firstDate);
        query.setParameter("secondDate" , secondDate);
        List<Reseller> found = query.getResultList();
        for (Reseller reseller : found) {
            sum++;
        }
        return sum;
    }
}
