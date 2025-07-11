package team2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team2.dao.*;
import team2.entities.*;
import team2.entities.enums.PassType;
import team2.entities.enums.ResellerStatusType;
import team2.entities.enums.TransportStatus;
import team2.exceptions.InvalidInputException;
import team2.exceptions.RecordNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW_4");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner scan = new Scanner(System.in);

        UserDAO ud = new UserDAO(em);
        User user1 = new User("Mario Rossi");
        User user2 = new User("Giuseppe Verdi");

        //ud.saveUser(user1);
        //ud.saveUser(user2);
        User user1FromDB = ud.findUserByID(1);
        User user2FromDB = ud.findUserByID(2);

        CardDAO cd = new CardDAO(em);
        Card card1 = new Card(LocalDate.of(2024, 6, 25), user1FromDB);
        Card card2 = new Card(LocalDate.of(2025, 5, 18), user2FromDB);

        //cd.saveCard(card1);
        //cd.saveCard(card2);
        Card foundCard1 = cd.findCardByID(1);
        Card foundCard2 = cd.findCardByID(2);

        TransportDAO td = new TransportDAO(em);
        Transport bus = new Bus(40, TransportStatus.IN_SERVICE, LocalDate.of(2024, 12, 10), 100);
        Transport tram = new Tram(60, TransportStatus.UNDER_MAINTENANCE, LocalDate.of(1993, 10, 30), 276);
        //td.save(bus);
        //td.save(tram);
        Transport busFromDB = td.findById(1);
        Transport tramFromDB = td.findById(2);

        ResellerDAO rld = new ResellerDAO(em);
        Reseller tabacchi1 = new AuthorizedReseller(ResellerStatusType.CLOSED, 5, 10);
        Reseller tabacchi2 = new AuthorizedReseller(ResellerStatusType.OPEN, 15, 20);
        Reseller atm1 = new AutomaticMachine(ResellerStatusType.IN_SERVICE, 25, 30);
        Reseller atm2 = new AutomaticMachine(ResellerStatusType.OUT_OF_ORDER, 35, 40);

//        rld.save(tabacchi1);
//        rld.save(tabacchi2);
//        rld.save(atm1);
//        rld.save(atm2);

        Reseller resellerFromDB = rld.findById(1);

        TravelTicketDAO ttd = new TravelTicketDAO(em);
        TravelTicket ticket1 = new Ticket(LocalDate.now());
        TravelTicket ticket2 = new Ticket(null);
        TravelTicket pass1 = new Pass(PassType.MONTHLY, LocalDate.now().plusMonths(1), LocalDate.now(), resellerFromDB, foundCard1);
        TravelTicket pass2 = new Pass(PassType.WEEKLY, LocalDate.now().plusDays(7), LocalDate.now(), resellerFromDB, foundCard2);

//        ttd.save(ticket1);
//        ttd.save(ticket2);
//        ttd.save(pass1);
//        ttd.save(pass2);

        RouteDAO rd = new RouteDAO(em);
        Route route1 = new Route("Central Station", "Bridge", 25.10);
        Route route2 = new Route("Middle Town", "Up Town", 48.12);
        Route route3 = new Route("Central Park", "Lake", 15.30);
        Route route4 = new Route("Cathedral", "Hospital", 35.50);

        //rd.save(route1);
        //rd.save(route2);
        //rd.save(route3);
        //rd.save(route4);
        Route route1FromDB = rd.findById(1);
        Route route2FromDB = rd.findById(2);

        MaintenanceDAO md = new MaintenanceDAO(em);
        Maintenance maintenance1 = new Maintenance(LocalDate.of(2025, 2, 5), LocalDate.of(2025, 2, 12), busFromDB);
        Maintenance maintenance2 = new Maintenance(LocalDate.of(2025, 3, 8), null, tramFromDB);

        //md.save(maintenance1);
        //md.save(maintenance2);

        TransportsRoutesDAO trd = new TransportsRoutesDAO(em);
        TransportRoute transportRoutes1 = new TransportRoute(route1FromDB, busFromDB, 30.10, LocalDateTime.now().minusHours(3));
        TransportRoute transportRoutes2 = new TransportRoute(route1FromDB, tramFromDB, 45.10, LocalDateTime.now().minusHours(1));
        TransportRoute transportRoutes3 = new TransportRoute(route1FromDB, busFromDB, 32.10, LocalDateTime.now().minusHours(2));
        TransportRoute transportRoutes4 = new TransportRoute(route2FromDB, busFromDB, 47.10, LocalDateTime.now().minusMinutes(30));
        TransportRoute transportRoutes5 = new TransportRoute(route2FromDB, busFromDB, 49.10, LocalDateTime.now().minusMinutes(15));
        TransportRoute transportRoutes6 = new TransportRoute(route1FromDB, busFromDB, 28.10, LocalDateTime.now().plusMinutes(5));
        TransportRoute transportRoutes7 = new TransportRoute(route1FromDB, busFromDB, 24.8, LocalDateTime.now().plusMinutes(15));
        TransportRoute transportRoutes8 = new TransportRoute(route1FromDB, busFromDB, 26.5, LocalDateTime.now().plusMinutes(25));


        //trd.save(transportRoutes1);
        //trd.save(transportRoutes2);
        //trd.save(transportRoutes3);
        //trd.save(transportRoutes4);
        //trd.save(transportRoutes5);
        //trd.save(transportRoutes6);
        //trd.save(transportRoutes7);
        //trd.save(transportRoutes8);


        System.out.println("**************** METODO CERCA N.OF TICKET *******************");

        rld.countTicketAndPassesByReseller(1);
        ttd.countTravelTicketByPeriod(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31));

        System.out.println("************ MAINTENANCES PER VEHICLE ************");

        //td.getServicePeriodByID(1);


        System.out.println("**************** CORSE *******************");

        System.out.println("numero di tratte");
        trd.countNumberOfRuns(busFromDB, route1FromDB);
        trd.countNumberOfRuns(tramFromDB, route1FromDB);
        trd.countNumberOfRuns(busFromDB, route2FromDB);

        System.out.println("tempo medio di tratte");
        trd.averageRunTime(busFromDB, route1FromDB);
        trd.averageRunTime(tramFromDB, route1FromDB);
        trd.averageRunTime(busFromDB, route2FromDB);


        System.out.println("*************** INIZIO PROGRAMMA CON SCANNER *************");
        boolean isActive = true;
        boolean isRunning = true;

        System.out.println("Salve,");
        while (isActive) {
            System.out.println("Sei un cliente o un amministratore ?");
            String userType = scan.nextLine().toLowerCase();
            try {
                if (userType.equals("amministratore")) {
                    System.out.println("Inserisci la password: ");
                    scan.nextLine();
                    System.out.println("Benvenuto amministratore, seleziona un argomento: ");
                } else if (userType.equals("cliente")) {
                    System.out.println("Buongiorno!");
                } else {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }


            while (isRunning) {
                if (!userType.equals("amministratore") && !userType.equals("cliente")) {
                    break;
                }
                try {
                    if (userType.equals("amministratore")) {
                        while (true) {
                            System.out.println("-- MENU' PRINCIPALE --");
                            System.out.println("1 - Biglietti e Abbonamenti.\n2 - Mezzi di trasporto.\n3 - Tratte.\n0 - Chiudi programma.");
                            int userChoice = Integer.parseInt(scan.nextLine());
                            if (userChoice == 1) {
                                boolean menu1 = true;
                                while (menu1) {
                                    try {
                                        System.out.println("-- MENU' BIGLIETTI E ABBONAMENTI");
                                        System.out.println("1 - Titoli di viaggio venduti in un periodo temporale.\n2 - Titoli di viaggio venduti da uno specifico rivenditore.\n0 - Torna al menù precedente.");
                                        int ticketMethod = Integer.parseInt(scan.nextLine());
                                        switch (ticketMethod) {
                                            case 0:
                                                menu1 = false;
                                                //isActive = false;
                                                break;
                                            case 1: {
                                                System.out.println("Inserisci la prima data (ex. 2025-01-01): ");
                                                LocalDate startDate = LocalDate.parse(scan.nextLine());
                                                System.out.println("Inserisci la seconda data (ex. 2025-01-01): ");
                                                LocalDate endDate = LocalDate.parse(scan.nextLine());
                                                ttd.countTravelTicketByPeriod(startDate, endDate);
                                            }
                                            break;
                                            case 2: {
                                                System.out.println("Inserisci l'id del rivenditore: ");
                                                int resellerId = Integer.parseInt(scan.nextLine());
                                                rld.countTicketAndPassesByReseller(resellerId);
                                            }
                                            break;
                                            default:
                                                System.out.println("Devi inserire un numero valido.");
                                        }
                                    } catch (InvalidInputException | RecordNotFoundException e) {
                                        System.out.println(e.getMessage());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Valore inserito non valido.");
                                    } catch (DateTimeParseException e) {
                                        System.out.println("Formato data non valido. Inserire la data come nell'esempio.");
                                    }
                                }

                            } else if (userChoice == 2) {
                                boolean menu2 = true;
                                while (menu2) {
                                    try {
                                        System.out.println("-- MENU' MEZZI DI TRASPORTO");
                                        System.out.println("1 - Biglietti vidimati in totale in un periodo temporale.\n2 - Biglietti vidimati su uno specifico mezzo di trasporto.\n3 - Periodo di servizio e di manutenzione di un mezzo di trasporto.\n0 - Torna al menù precedente.");
                                        int method = Integer.parseInt(scan.nextLine());
                                        switch (method) {
                                            case 0:
                                                menu2 = false;
                                                break;
                                            case 1: {
                                                System.out.println("Inserisci la prima data (ex. 2025-01-01): ");
                                                LocalDate startDate = LocalDate.parse(scan.nextLine());
                                                System.out.println("Inserisci la seconda data (ex. 2025-01-01): ");
                                                LocalDate endDate = LocalDate.parse(scan.nextLine());
                                                ttd.printValidatedTicketsByPeriod(startDate, endDate);
                                            }
                                            break;
                                            case 2: {
                                                System.out.println("Inserisci l'id del mezzo di trasporto: ");
                                                int transportId = Integer.parseInt(scan.nextLine());
                                                Transport transportFromDB = td.findById(transportId);
                                                ttd.printValidatedTicketsByTransport(transportFromDB);
                                            }
                                            break;
                                            case 3: {
                                                System.out.println("Inserisci l'id del mezzo di trasporto: ");
                                                int transportId = Integer.parseInt(scan.nextLine());
                                                td.getServicePeriodByID(transportId);
                                            }
                                            break;
                                            default: {
                                                System.out.println("Devi inserire un numero valido.");
                                            }
                                        }
                                    } catch (InvalidInputException | RecordNotFoundException e) {
                                        System.out.println(e.getMessage());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Valore inserito non valido.");
                                    } catch (DateTimeParseException e) {
                                        System.out.println("Formato data non valido. Inserire la data come nell'esempio.");
                                    }
                                }

                            } else if (userChoice == 3) {
                                boolean menu3 = true;
                                while (menu3) {
                                    try {
                                        System.out.println("-- MENU' TRATTE --");
                                        System.out.println("1 - Tempo medio di percorrenza di una tratta.\n2 - Numero di volte in cui un mezzo ha percorso una tratta.\n0 - Torna al menù precedente.");
                                        int method = Integer.parseInt(scan.nextLine());
                                        switch (method) {
                                            case 0:
                                                menu3 = false;
                                                break;
                                            case 1: {
                                                System.out.println("Inserisci l'id del mezzo di trasporto di cui vuoi la media: ");
                                                int transportId = Integer.parseInt(scan.nextLine());
                                                Transport transportFromDB = td.findById(transportId);
                                                System.out.println("Inserisci l'id della tratta di cui vuoi la media: ");
                                                int routeId = Integer.parseInt(scan.nextLine());
                                                Route routeFromDB = rd.findById(routeId);
                                                trd.averageRunTime(transportFromDB, routeFromDB);
                                            }
                                            break;
                                            case 2: {
                                                System.out.println("Inserisci l'id del mezzo di trasporto: ");
                                                int transportId = Integer.parseInt(scan.nextLine());
                                                Transport transportFromDB = td.findById(transportId);
                                                System.out.println("Inserisci l'id della tratta: ");
                                                int routeId = Integer.parseInt(scan.nextLine());
                                                Route routeFromDB = rd.findById(routeId);
                                                trd.countNumberOfRuns(transportFromDB, routeFromDB);
                                            }
                                        }
                                    } catch (InvalidInputException | RecordNotFoundException e) {
                                        System.out.println(e.getMessage());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Valore inserito non valido.");
                                    } catch (DateTimeParseException e) {
                                        System.out.println("Formato data non valido. Inserire la data come nell'esempio.");
                                    }
                                }

                            } else if (userChoice == 0) {
                                System.out.println("Arrivederci!");
                                isRunning = false;
                                isActive = false;
                                break;
                            } else {
                                System.out.println("Opzione inserita non valida. Devi inserire un numero tra 0 e 3.");
                            }
                        }
                    } else {
                        boolean done = true;
                        while (done) {
                            System.out.print("Hai una tessera? (si/no): ");
                            String risposta = scan.nextLine().toLowerCase();
                            boolean abbonamento = false;
                            boolean biglietto = false;
                            TravelTicket foundTicket = null;

                            switch (risposta) {
                                case "sì":
                                case "si": {
                                    System.out.println("Inserisci il numero della tua tessera");
                                    int cardId = Integer.parseInt(scan.nextLine());
                                    Card cardFromDB = cd.findCardByID(cardId);
                                    System.out.println("Verifica validità in corso...");
                                    long userId = cardFromDB.getOwner().getId();
                                    String check = ud.checkCardValidity(userId);

                                    if (check.equals("Scaduta")) {
                                        System.out.println("Vuoi rinnovarla? ");
                                        String response = scan.nextLine().toLowerCase();
                                        if (response.equals("no")) {
                                            System.out.println("Arrivederci !");
                                            done = false;
                                            isRunning = false;
                                            isActive = false;
                                            break;
                                        } else if (response.equals("si")) {
                                            abbonamento = true;
                                        } else {
                                            throw new InvalidInputException();
                                        }
                                    } else if (check.equals("noPass")) {
                                        abbonamento = true;
                                    } else if (check.equals("yesPass")) {
                                        continue;
                                    } else {
                                        throw new InvalidInputException();
                                    }
                                    if (abbonamento) {
                                        System.out.println("Vuoi un abbonamento mensile, settimanale o annulla? ");
                                        String response = scan.nextLine().toLowerCase();
                                        if (response.equals("settimanale")) {
                                            System.out.println("Abbonamento settimanale attivato ! ");
                                        } else if (response.equals("mensile")) {
                                            System.out.println("Abbonamento mensile attivato ! ");
                                        } else if (response.equals("annulla")) {
                                            System.out.println("Arrivederci !");
                                            done = false;
                                            isRunning = false;
                                            isActive = false;
                                        } else {
                                            throw new InvalidInputException();
                                        }
                                    }
                                }


                                break;
                                case "no":
                                    System.out.println("Vuoi creare una nuova tessera o comprare un biglietto singolo ? ");
                                    break;
                                default:
                                    System.out.println("Risposta non valida. Scrivi 'sì' o 'no'.");
                                    break;
                            }
                            if (risposta.equals("no")) {
                                String response = scan.nextLine().toLowerCase();

                                if (response.equals("tessera")) {
                                    System.out.println("Inserisci il tuo nome completo: ");
                                    String username = scan.nextLine();
                                    User user = new User(username);

                                    Card card = new Card(LocalDate.now(), user);
                                    ud.saveUser(user);
                                    cd.saveCard(card);

                                    System.out.println("Vuoi un abbonamento mensile, settimanale o annulla? ");
                                    String resp = scan.nextLine().toLowerCase();
                                    if (resp.equals("settimanale")) {
                                        System.out.println("Abbonamento settimanale attivato ! ");
                                    } else if (resp.equals("mensile")) {
                                        System.out.println("Abbonamento mensile attivato ! ");
                                    } else if (resp.equals("annulla")) {
                                        System.out.println("Arrivederci !");
                                        done = false;
                                        isRunning = false;
                                        isActive = false;
                                    } else {
                                        throw new InvalidInputException();
                                    }
                                } else if (response.equals("biglietto")) {
                                    TravelTicket ticket = new Ticket(null);
                                    biglietto = true;
                                    ttd.save(ticket);
                                    foundTicket = ttd.findById(ticket.getId());
                                    System.out.println("Biglietto erogato");
                                }
                            }
                            System.out.println("Vuoi raggiungere qualche destinazione ?");
                            String resp = scan.nextLine();
                            if (resp.equals("si")) {
                                System.out.println("Scegli la destinazione: ");
                                System.out.println("1 - Bridge.\n2 - Up Town.\n3 - Lake.\n4 - Hospital. \n0 - Annulla");
                                int choice = Integer.parseInt(scan.nextLine());
                                switch (choice) {
                                    case 0: {
                                        System.out.println("Arrivederci !");
                                        done = false;
                                        isRunning = false;
                                        isActive = false;
                                    }
                                    case 1: {
                                        Route route = rd.findRoutesByDestination("Bridge");
                                        Transport transport = route.getTransportRouteList().getFirst().getTransports();
                                        System.out.println("Puoi prendere questo mezzo con id " + transport.getTransport_id() + " che parte da " + route.getDeparturePoint() + " e ti porterà a " + route.getTerminusRoute() + " in circa " + route.getEstimatedTime());
                                        if (biglietto) {
                                            ((Ticket) foundTicket).setTransport(transport);
                                            ttd.validate((Ticket) foundTicket);
                                        }
                                        done = false;
                                        isRunning = false;
                                        isActive = false;
                                        break;
                                    }
                                    case 2: {
                                        Route route = rd.findRoutesByDestination("Up Town");
                                        Transport transport = route.getTransportRouteList().getFirst().getTransports();
                                        System.out.println("Puoi prendere questo mezzo con id " + transport.getTransport_id() + " che parte da " + route.getDeparturePoint() + " e ti porterà a " + route.getTerminusRoute() + " in circa " + route.getEstimatedTime());
                                        if (biglietto) {
                                            ((Ticket) foundTicket).setTransport(transport);
                                            ttd.validate((Ticket) foundTicket);
                                        }
                                        done = false;
                                        isRunning = false;
                                        isActive = false;
                                        break;
                                    }
                                    case 3: {
                                        Route route = rd.findRoutesByDestination("Lake");
                                        Transport transport = route.getTransportRouteList().getFirst().getTransports();
                                        System.out.println("Puoi prendere questo mezzo con id " + transport.getTransport_id() + " che parte da " + route.getDeparturePoint() + " e ti porterà a " + route.getTerminusRoute() + " in circa " + route.getEstimatedTime());
                                        if (biglietto) {
                                            ((Ticket) foundTicket).setTransport(transport);
                                            ttd.validate((Ticket) foundTicket);
                                        }
                                        done = false;
                                        isRunning = false;
                                        isActive = false;
                                        break;
                                    }
                                    case 4: {
                                        Route route = rd.findRoutesByDestination("Hospital");
                                        Transport transport = route.getTransportRouteList().getFirst().getTransports();
                                        System.out.println("Puoi prendere questo mezzo con id " + transport.getTransport_id() + " che parte da " + route.getDeparturePoint() + " e ti porterà a " + route.getTerminusRoute() + " in circa " + route.getEstimatedTime());
                                        if (biglietto) {
                                            ((Ticket) foundTicket).setTransport(transport);
                                            ttd.validate((Ticket) foundTicket);
                                        }
                                        done = false;
                                        isRunning = false;
                                        isActive = false;
                                        break;
                                    }


                                }
                            }

                        }
                    }
                } catch (InvalidInputException | RecordNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Valore inserito non valido.");
                } catch (DateTimeParseException e) {
                    System.out.println("Formato data non valido. Inserire la data come nell'esempio.");
                }
            }
        }


        em.close();
        emf.close();
        scan.close();
    }
}
