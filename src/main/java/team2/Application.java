package team2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team2.dao.TravelTicketDAO;
import team2.dao.UserDAO;
import team2.entities.Pass;
import team2.entities.Ticket;
import team2.entities.TravelTicket;
import team2.entities.User;
import team2.entities.enums.PassType;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW_4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

//        UserDAO ud = new UserDAO(em);
//        User user1 = new User("Mario Rossi");

        //ud.saveUser(user1);

        TravelTicketDAO td = new TravelTicketDAO(em);
//
//        TravelTicket ticket1 = new Ticket(LocalDate.now());
//
//        TravelTicket pass1 = new Pass(PassType.MONTHLY, LocalDate.now().plusMonths(1), LocalDate.now());
//
//        td.save(ticket1);
//        td.save(pass1);

        





        System.out.println("Hello World!");
    }
}
