package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Card;
import team2.exceptions.NotFoundException;

public class CardDAO {
    private final EntityManager entityManager;

    public CardDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveCard(Card newCard) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newCard);
        transaction.commit();
        System.out.println("New card with id " + newCard.getId() + " added successfully!");
    }

    public Card findCardByID(long id) {
        Card found = entityManager.find(Card.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void deleteCardByID(long id) {
        Card found = this.findCardByID(id);
        if (found == null) throw new NotFoundException(id);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(found);
        transaction.commit();
        System.out.println("Card with id " + found.getId() + " successfully removed!");
    }
}
