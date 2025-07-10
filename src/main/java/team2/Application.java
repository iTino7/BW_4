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
import java.time.LocalDateTime;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW_4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        UserDAO ud = new UserDAO(em);
        User user1 = new User("Mario Rossi");
        User user2 = new User("Giuseppe Verdi");

        ud.saveUser(user1);
        ud.saveUser(user2);
        User user1FromDB = ud.findUserByID(1);
        User user2FromDB = ud.findUserByID(2);

        CardDAO cd = new CardDAO(em);
        Card card1 = new Card(LocalDate.of(2024, 06, 25), user1FromDB);
        Card card2 = new Card(LocalDate.of(2025, 05, 18), user2FromDB);

        cd.saveCard(card1);
        cd.saveCard(card2);

        TransportDAO td = new TransportDAO(em);
        Transport bus = new Bus(40, TransportStatus.IN_SERVICE, LocalDate.of(2024, 12, 10), 100);
        Transport tram = new Tram(60, TransportStatus.UNDER_MAINTENANCE, LocalDate.of(1993, 10, 30), 276);
        td.save(bus);
        td.save(tram);
        Transport busFromDB = td.findById(1);
        Transport tramFromDB = td.findById(2);

        ResellerDAO rld = new ResellerDAO(em);
        Reseller tabacchi1 = new AuthorizedReseller(ResellerStatusType.CLOSED, 5, 10);
        Reseller tabacchi2 = new AuthorizedReseller(ResellerStatusType.OPEN, 15, 20);
        Reseller atm1 = new AutomaticMachine(ResellerStatusType.IN_SERVICE, 25, 30);
        Reseller atm2 = new AutomaticMachine(ResellerStatusType.OUT_OF_ORDER, 35, 40);

        rld.save(tabacchi1);
        rld.save(tabacchi2);
        rld.save(atm1);
        rld.save(atm2);

        Reseller resellerFromDB = rld.findById(1);

        TravelTicketDAO ttd = new TravelTicketDAO(em);
        TravelTicket ticket1 = new Ticket(LocalDate.now(), resellerFromDB);
        TravelTicket ticket2 = new Ticket(LocalDate.now(), resellerFromDB);
        TravelTicket pass1 = new Pass(PassType.MONTHLY, LocalDate.now().plusMonths(1), LocalDate.now(), resellerFromDB);
        TravelTicket pass2 = new Pass(PassType.WEEKLY, LocalDate.now().plusDays(7), LocalDate.now(), resellerFromDB);

        ttd.save(ticket1);
        ttd.save(ticket2);
        ttd.save(pass1);
        ttd.save(pass2);

        RouteDAO rd = new RouteDAO(em);
        Route route1 = new Route("Central Station", "Bridge", 25.10);
        Route route2 = new Route("Middle Town", "Up Town", 48.12);
        Route route3 = new Route("Central Park", "Lake", 15.30);
        Route route4 = new Route("Cathedral", "Hospital", 35.50);

        rd.save(route1);
        rd.save(route2);
        rd.save(route3);
        rd.save(route4);
        Route route1FromDB = rd.findById(1);
        Route route2FromDB = rd.findById(2);

        MaintenanceDAO md = new MaintenanceDAO(em);
        Maintenance maintenance1 = new Maintenance(LocalDate.of(2025, 2, 5), LocalDate.of(2025, 2, 12), busFromDB);
        Maintenance maintenance2 = new Maintenance(LocalDate.of(2025, 3, 8), null, tramFromDB);

        md.save(maintenance1);
        md.save(maintenance2);

        TransportsRoutesDAO trd = new TransportsRoutesDAO(em);
        TransportRoute transportRoutes1 = new TransportRoute(route1FromDB, busFromDB, 30.10, LocalDateTime.now().minusHours(3));
        TransportRoute transportRoutes2 = new TransportRoute(route1FromDB, tramFromDB, 45.10, LocalDateTime.now().minusHours(1));
        TransportRoute transportRoutes3 = new TransportRoute(route1FromDB, busFromDB, 32.10, LocalDateTime.now().minusHours(2));
        TransportRoute transportRoutes4 = new TransportRoute(route2FromDB, busFromDB, 47.10, LocalDateTime.now().minusMinutes(30));
        TransportRoute transportRoutes5 = new TransportRoute(route2FromDB, busFromDB, 49.10, LocalDateTime.now().minusMinutes(15));
        TransportRoute transportRoutes6 = new TransportRoute(route1FromDB, busFromDB, 28.10, LocalDateTime.now().plusMinutes(5));
        TransportRoute transportRoutes7 = new TransportRoute(route1FromDB, busFromDB, 24.8, LocalDateTime.now().plusMinutes(15));
        TransportRoute transportRoutes8 = new TransportRoute(route1FromDB, busFromDB, 26.5, LocalDateTime.now().plusMinutes(25));


        trd.save(transportRoutes1);
        trd.save(transportRoutes2);
        trd.save(transportRoutes3);
        trd.save(transportRoutes4);
        trd.save(transportRoutes5);
        trd.save(transportRoutes6);
        trd.save(transportRoutes7);
        trd.save(transportRoutes8);


        System.out.println("**************** METODO CERCA N.OF TICKET *******************");

        rld.countTicketAndPassesByReseller(1);
        ttd.countTravelTicketByPeriod(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31));

        System.out.println("************ MAINTENANCES PER VEHICLE ************");
/*
        td.getServicePeriodByID(1);

*/
        System.out.println("**************** CORSE *******************");

        System.out.println("numero di tratte");
        trd.countNumberOfRuns(busFromDB, route1FromDB);
        trd.countNumberOfRuns(tramFromDB, route1FromDB);
        trd.countNumberOfRuns(busFromDB, route2FromDB);

        System.out.println("tempo medio di tratte");
        trd.averageRunTime(busFromDB, route1FromDB);
        trd.averageRunTime(tramFromDB, route1FromDB);
        trd.averageRunTime(busFromDB, route2FromDB);


        em.close();
        emf.close();
    }
}
