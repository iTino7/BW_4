package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Transport;
import team2.exceptions.NotFoundException;

public class TransportDAO {

    private EntityManager em;

    public TransportDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Transport transport) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(transport);
        et.commit();
    }

    public Transport findById(long id) {
        Transport found = em.find(Transport.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }


}
