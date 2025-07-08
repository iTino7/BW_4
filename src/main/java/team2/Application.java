package team2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team2.dao.*;
import team2.entities.*;
import team2.entities.enums.PassType;
import team2.entities.enums.TransportStatus;

import java.time.LocalDate;
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
        User user1FromDB = ud.findUserByID(1);

        CardDAO cd = new CardDAO(em);
        Card card1 = new Card(LocalDate.now(), user1FromDB);

        TransportDAO td = new TransportDAO(em);

        Transport bus = new Bus(40, TransportStatus.IN_SERVICE, LocalDate.of(2024, 12, 10), 100);

        Transport tram = new Tram(60, TransportStatus.UNDER_MAINTENANCE, LocalDate.of(1993, 10, 30), 276);


        Transport foundTransport = td.findById(2);
        System.out.println("id found: " + foundTransport);

        //td.findByIdAndDelete(2);



        //td.save(tram);
        //td.save(bus);

        RouteDAO rt = new RouteDAO(em);
        Route route1 = new Route("Stazione", "Ponte", 25.10);

        //rt.save(route1);

        //ud.saveUser(user1);
        //cd.saveCard(card1);

        TravelTicketDAO ttd = new TravelTicketDAO(em);
//
//        TravelTicket ticket1 = new Ticket(LocalDate.now());
//
//        TravelTicket pass1 = new Pass(PassType.MONTHLY, LocalDate.now().plusMonths(1), LocalDate.now());
//
//        ttd.save(ticket1);
//        ttd.save(pass1);







        System.out.println("Hello World!");

        em.close();
        emf.close();
    }
}
