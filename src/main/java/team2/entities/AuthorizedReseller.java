package team2.entities;

import team2.entities.enums.ResellerStatusType;

public class AuthorizedReseller extends Reseller {
    public AuthorizedReseller() {
    }

    ;

    public AuthorizedReseller(ResellerStatusType status) {
        super(status);
    }

    ;
}
