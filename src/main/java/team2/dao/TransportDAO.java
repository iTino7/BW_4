package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team2.entities.Maintenance;
import team2.entities.Transport;
import team2.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TransportDAO {

    private final EntityManager em;

    public TransportDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Transport newTransport) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(newTransport);
        et.commit();
    }

    public Transport findById(long id) {
        Transport found = em.find(Transport.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    public void findByIdAndDelete(long id) {
        Transport found = this.findById(id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(found);
        t.commit();
        System.out.println("id delete");

    }

    public long getYears(long days) {
        return days / 365;
    }

    public long getMonths(long days) {
        return (days % 365) / 30;
    }

    public long getWeeks(long days) {
        return ((days % 365) % 30) / 7;
    }

    public long getDays(long days) {
        return ((days % 365) % 30) % 7;
    }

    public void getServicePeriodByID(long id) {
        Transport found = this.findById(id);

        LocalDate firstDay = found.getFirstServiceDay();
        long estimatedDaysOfService = ChronoUnit.DAYS.between(firstDay, LocalDate.now());

        List<Maintenance> maintenances = found.getMaintenanceList();
        long daysUnderMaintenance = 0;
        for (Maintenance maintenance : maintenances) {
            if (maintenance.getFinalDate() == null) {
                maintenance.setFinalDate(LocalDate.now());
            }
            daysUnderMaintenance += ChronoUnit.DAYS.between(maintenance.getStartingDate(), maintenance.getFinalDate());
        }
        long years = daysUnderMaintenance / 365;
        long months = (daysUnderMaintenance % 365) / 30;
        long weeks = ((daysUnderMaintenance % 365) % 30) / 7;
        long days = ((daysUnderMaintenance % 365) % 30) % 7;

        long actualDaysOfService = estimatedDaysOfService - daysUnderMaintenance;

        System.out.println("Transport vehicle with id " + id + " has been under maintenance for " + daysUnderMaintenance + " days. Specifically " + this.getMonths(daysUnderMaintenance) + " months, " + getWeeks(daysUnderMaintenance) + " weeks and " + getDays(daysUnderMaintenance) + " days.");
        System.out.println("Transport vehicle with id " + id + " has been in service for " + actualDaysOfService + " days. Specifically " + this.getYears(actualDaysOfService) + " years, " + this.getMonths(actualDaysOfService) + " months, " + this.getWeeks(actualDaysOfService) + " weeks and " + this.getDays(actualDaysOfService) + " days.");
    }

    public void countByTransportAndPeriod(long transportId, LocalDate startDate, LocalDate endDate) {
        Transport found = this.findById(transportId);

        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(t) FROM Transport tr JOIN tr.transportTravelTickets t " +
                        "WHERE tr.transportId = :transportId AND :validationDate BETWEEN :startDate AND :endDate ",
                Long.class
        );
        query.setParameter("transportId", transportId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        Long count = query.getSingleResult();


        System.out.println("Boh è una prova " + count);
    }


}
