package edu.umbc.parkpronto.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by sushantathley on 9/28/16.
 */

public class ParkingZone {

    public ParkingZone(String id, String name, ParkingPermit permit, ParkingType parkingType) {
        this.id = id;
        this.name = name;
        this.permit = permit;
        this.type = parkingType;
    }

    public String id;
    public String name;
    public ParkingPermit permit;
    public ParkingType type;
    public LatLng[] coordinates;
    public ParkingAvailability availability;

}
