package team2.entities;

import team2.entities.enums.ResellerStatusType;

public abstract class Reseller {
    protected long reseller_id;
    protected long issued_tickets;
    protected long issued_passes;
    protected ResellerStatusType status;

    public Reseller() {
    }

    ;

    public Reseller(ResellerStatusType status) {
        this.status = status;
        this.issued_tickets = 0;
        this.issued_passes = 0;

    }

    ;

    public long getReseller_id() {
        return reseller_id;
    }

    public ResellerStatusType getStatus() {
        return status;
    }

    public void setStatus(ResellerStatusType status) {
        this.status = status;
    }

    public long getIssued_tickets() {
        return issued_tickets;
    }

    public void setIssued_tickets(long issued_tickets) {
        this.issued_tickets = issued_tickets;
    }

    public long getIssued_passes() {
        return issued_passes;
    }

    public void setIssued_passes(long issued_passes) {
        this.issued_passes = issued_passes;
    }
}
