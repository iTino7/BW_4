package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Transport;

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
        return em.find(Transport.class, id);
    }



}
