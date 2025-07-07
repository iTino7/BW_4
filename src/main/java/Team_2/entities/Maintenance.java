package Team_2.entities;


import java.time.LocalDate;

public class Maintenance {
    private long id;
    private LocalDate startingDate;
    private LocalDate finalDate;

    public Maintenance () {}

    public Maintenance(LocalDate startingDate, LocalDate finalDate) {
        this.startingDate = startingDate;
        this.finalDate = finalDate;
    }

    public long getId() {
        return id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", finalDate=" + finalDate +
                '}';
    }
}
