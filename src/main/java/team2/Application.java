package team2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team2.dao.RouteDAO;
import team2.dao.UserDAO;
import team2.entities.Route;
import team2.entities.User;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW_4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UserDAO ud = new UserDAO(em);
        User user1 = new User("Mario Rossi");
        RouteDAO rt = new RouteDAO(em);
        Route route1 = new Route("Stazione", "Ponte", 25.10);

        //rt.save(route1);

        //ud.saveUser(user1);

        System.out.println("Hello World!");
    }
}
