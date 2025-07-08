package team2.entities;

import team2.entities.enums.PassType;

import java.time.LocalDate;

public class Pass extends TravelTicket {
    private PassType passType;
    private LocalDate expiringDate;

    public Pass() {
    }

    public Pass(PassType passType, LocalDate expiringDate, LocalDate issuedDate) {
        super(issuedDate);
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
