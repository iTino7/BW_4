package team2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team2.dao.TransportDAO;
import team2.dao.UserDAO;
import team2.entities.Bus;
import team2.entities.Tram;
import team2.entities.Transport;
import team2.entities.User;
import team2.entities.enums.TransportStatus;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW_4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UserDAO ud = new UserDAO(em);
        User user1 = new User("Mario Rossi");

        TransportDAO td = new TransportDAO(em);

        Transport bus = new Bus(40, TransportStatus.IN_SERVICE, LocalDate.of(2024, 12, 10), 100);

        Transport tram = new Tram(60, TransportStatus.UNDER_MAINTENANCE, LocalDate.of(1993, 10, 30), 276);

        //td.save(tram);
        //td.save(bus);


        //ud.saveUser(user1);

        System.out.println("Hello World!");

        em.close();
        emf.close();
    }
}
