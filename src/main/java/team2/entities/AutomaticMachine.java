package team2.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import team2.entities.enums.ResellerStatusType;

@Entity
@DiscriminatorValue("Automatic Machine")
public class AutomaticMachine extends Reseller {

    public AutomaticMachine() {
    }

    ;

    public AutomaticMachine(ResellerStatusType status) {
        super(status);
    }

    ;
}
