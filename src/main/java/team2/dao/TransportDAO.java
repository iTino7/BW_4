package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Maintenance;
import team2.entities.Transport;
import team2.exceptions.RecordNotFoundException;

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
        if (found == null) throw new RecordNotFoundException("Mezzo di trasporto", id);
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

        System.out.println("Il veicolo con id " + id + " è stato in manutenzione per " + daysUnderMaintenance + " giorni. Nello specifico " + this.getMonths(daysUnderMaintenance) + " mesi, " + getWeeks(daysUnderMaintenance) + " settimane e " + getDays(daysUnderMaintenance) + " giorni.");
        System.out.println("Il veicolo con id " + id + " è stato in servizio per " + actualDaysOfService + " giorni. Nello specifico " + this.getYears(actualDaysOfService) + " anni, " + this.getMonths(actualDaysOfService) + " mesi, " + this.getWeeks(actualDaysOfService) + " settimane e " + this.getDays(actualDaysOfService) + " giorni.");
    }

}
