package edu.umbc.parkpronto.util;

import edu.umbc.parkpronto.model.ParkingPermit;

/**
 * Created by sushantathley on 9/28/16.
 */

public class SharedPrefManager {

    public ParkingPermit getLastSavedPermit() {
        return ParkingPermit.A;
    }

    public void savePermit(ParkingPermit parkingPermit) {

    }
}
