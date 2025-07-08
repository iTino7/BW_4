package team2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team2.dao.*;
import team2.entities.*;
import team2.entities.enums.PassType;
import team2.entities.enums.ResellerStatusType;
import team2.entities.enums.TransportStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW_4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UserDAO ud = new UserDAO(em);
        User user1 = new User("Mario Rossi");
        User user2 = new User("Giuseppe Verdi");

//        ud.saveUser(user1);
//        ud.saveUser(user2);
        User user1FromDB = ud.findUserByID(1);
        User user2FromDB = ud.findUserByID(2);

        CardDAO cd = new CardDAO(em);
        Card card1 = new Card(LocalDate.of(2024, 06, 25), user1FromDB);
        Card card2 = new Card(LocalDate.of(2025, 05, 18), user2FromDB);

//        cd.saveCard(card1);
//        cd.saveCard(card2);

        TransportDAO td = new TransportDAO(em);
        Transport bus = new Bus(40, TransportStatus.IN_SERVICE, LocalDate.of(2024, 12, 10), 100);
        Transport tram = new Tram(60, TransportStatus.UNDER_MAINTENANCE, LocalDate.of(1993, 10, 30), 276);
//        td.save(bus);
//        td.save(tram);
        Transport busFromDB = td.findById(1);
        Transport tramFromDB = td.findById(2);

        TravelTicketDAO ttd = new TravelTicketDAO(em);
        TravelTicket ticket1 = new Ticket(LocalDate.now());
        TravelTicket ticket2 = new Ticket(LocalDate.now());
        TravelTicket pass1 = new Pass(PassType.MONTHLY, LocalDate.now().plusMonths(1), LocalDate.now());
        TravelTicket pass2 = new Pass(PassType.WEEKLY, LocalDate.now().plusDays(7), LocalDate.now());

//        ttd.save(ticket1);
//        ttd.save(ticket2);
//        ttd.save(pass1);
//        ttd.save(pass2);
//


        RouteDAO rd = new RouteDAO(em);
        Route route1 = new Route("Central Station", "Bridge", 25.10);
        Route route2 = new Route("Middle Town", "Up Town", 48.12);

//        rd.save(route1);
//        rd.save(route2);

        ResellerDAO rld = new ResellerDAO(em);
        Reseller tabacchi1 = new AuthorizedReseller(ResellerStatusType.CLOSED, 5, 10 );
        Reseller tabacchi2 = new AuthorizedReseller(ResellerStatusType.OPEN, 15, 20 );
        Reseller atm1 = new AutomaticMachine(ResellerStatusType.IN_SERVICE, 25, 30 );
        Reseller atm2 = new AutomaticMachine(ResellerStatusType.OUT_OF_ORDER, 35, 40 );

//        rld.save(tabacchi1);
//        rld.save(tabacchi2);
//        rld.save(atm1);
//        rld.save(atm2);


        MaintenanceDAO md = new MaintenanceDAO(em);
        Maintenance maintenance1 = new Maintenance(LocalDate.of(2025, 2, 5), LocalDate.of(2025, 2, 12), busFromDB);
        Maintenance maintenance2 = new Maintenance(LocalDate.of(2025, 3, 8), LocalDate.of(2025, 4, 10), tramFromDB);

//        md.save(maintenance1);
//        md.save(maintenance2);


        System.out.println("**************** METODO CERCA N.OF TICKET *******************");

        TravelTicket ticketFromDB1 = ttd.findById(3);
        TravelTicket ticketFromDB2 = ttd.findById(4);

        Reseller resellerFromDB = rld.findById(1);

        List<TravelTicket> ticketList = new ArrayList<>();
        ticketList.add(ticketFromDB1);
        ticketList.add(ticketFromDB2);

        resellerFromDB.setTravelTicketList(ticketList);

        rld.save(resellerFromDB);

        int number = rld.howManyTicketPerPeriod(LocalDate.now().minusDays(30), LocalDate.now().plusDays(20));
        System.out.println(number);


        System.out.println("Hello World!");

        em.close();
        emf.close();
    }
}
