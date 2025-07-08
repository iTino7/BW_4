package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Transport;
import team2.exceptions.NotFoundException;

public class TransportDAO {

    private final EntityManager em;

    public TransportDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Transport newTransport) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(newTransport);
        et.commit();
    }

    public Transport findById(long id) {
        Transport found = em.find(Transport.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findByIdAndDelete(long id) {
        try {
            Transport found = this.findById(id);
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.remove(found);
            t.commit();
            System.out.println("id delete");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
