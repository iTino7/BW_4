package team2.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import team2.entities.enums.ResellerStatusType;

@Entity
@DiscriminatorValue("Authorized Reseller")
public class AuthorizedReseller extends Reseller {
    public AuthorizedReseller() {
    }

    ;

    public AuthorizedReseller(ResellerStatusType status) {
        super(status);
    }

    ;
}
