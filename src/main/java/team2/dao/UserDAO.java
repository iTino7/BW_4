package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.User;
import team2.exceptions.NotFoundException;

import java.time.LocalDate;

public class UserDAO {
    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUser(User newUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUser);
        transaction.commit();
        System.out.println("Utente con id " + newUser.getId() + " aggiunto con successo!");
    }

    public User findUserByID(long id) {
        User found = entityManager.find(User.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void deleteUserByID(long id) {
        User found = this.findUserByID(id);
        if (found == null) throw new NotFoundException(id);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(found);
        transaction.commit();
        System.out.println("Utente con id" + found.getId() + " rimosso con successo!");
    }

    public void checkCardValidity(long id) {
        User found = this.findUserByID(id);
        if (found == null) throw new NotFoundException(id);

        LocalDate dateEx = found.getCard().getExpiringDate();

        if (dateEx.isBefore(LocalDate.now())) {
            System.out.println("La tua carta è scaduta.");
        } else {
            System.out.println("La tua carta è ancora valida.");
        }
    }
}
