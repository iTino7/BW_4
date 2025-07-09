package team2.entities;

import jakarta.persistence.*;
import team2.entities.enums.PassType;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Pass")
public class Pass extends TravelTicket {
    @Enumerated(EnumType.STRING)
    private PassType passType;
    @Column(name = "expiring_date")
    private LocalDate expiringDate;

    public Pass() {
    }

    public Pass(PassType passType, LocalDate expiringDate, LocalDate issuedDate, Reseller reseller) {
        super(issuedDate, reseller);
        this.passType = passType;
        this.expiringDate = expiringDate;
    }

    public PassType getPassType() {
        return passType;
    }

    public void setPassType(PassType passType) {
        this.passType = passType;
    }

    public LocalDate getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }

    @Override
    public String toString() {
        return "Pass{" +
                "passType=" + passType +
                ", expiringDate=" + expiringDate +
                ", id=" + id +
                '}';
    }
}
