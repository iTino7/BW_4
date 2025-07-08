package team2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team2.dao.CardDAO;
import team2.dao.UserDAO;
import team2.entities.Card;
import team2.entities.User;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW_4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UserDAO ud = new UserDAO(em);
        User user1 = new User("Mario Rossi");
        User user1FromDB = ud.findUserByID(1);

        CardDAO cd = new CardDAO(em);
        Card card1 = new Card(LocalDate.now(), user1FromDB);

        //ud.saveUser(user1);
        cd.saveCard(card1);

        System.out.println("Hello World!");
    }
}
