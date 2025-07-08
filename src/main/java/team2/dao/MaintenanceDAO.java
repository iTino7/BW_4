package team2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team2.entities.Maintenance;
import team2.exceptions.NotFoundException;

public class MaintenanceDAO {
    private final EntityManager entityManager;

    public MaintenanceDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save (Maintenance newMaintenance) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newMaintenance);
        transaction.commit();
        System.out.println("Manutenzione " + newMaintenance.getId() + "creata correttamente!");
    }

    public Maintenance findMaintenanceById(long MaintenanceId) {
        Maintenance found = entityManager.find(Maintenance.class, MaintenanceId);
        if (found == null) throw new NotFoundException(MaintenanceId);
        return found;
    }

    public void findByIdAndDelete(long MaintenanceId) {
        Maintenance found = this.findMaintenanceById(MaintenanceId);

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.remove(found);

        transaction.commit();

        System.out.println("Manutenzione " + found.getId() + " eliminata correttamente!");

    }
}
