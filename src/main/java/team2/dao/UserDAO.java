package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Card;
import team2.entities.Pass;
import team2.entities.User;
import team2.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public String checkCardValidity(long id) {
        String result = "";

        User found = this.findUserByID(id);
        if (found == null) throw new NotFoundException(id);

        Card card = found.getCard();
        List<Pass> userPass = card.getPassList().stream().filter(pass -> pass.getExpiringDate().isAfter(LocalDate.now())).toList();

        LocalDate dateEx = card.getExpiringDate();

        if (dateEx.isBefore(LocalDate.now())) {
            System.out.println("La tua carta è scaduta.");
            result = "Scaduta";
            return result;
        } else if (userPass.isEmpty()) {
            System.out.println("La tua carta è ancora valida, ma non hai un abbonamento attivo.");
            result = "noPass";
            return result;
        } else {
            System.out.println("La tua carta è ancora valida! Hai un abbonamento " +userPass.getFirst().getPassType() + " attivo " );
            result = "yesPass";
            return result;
        }
    }
}
