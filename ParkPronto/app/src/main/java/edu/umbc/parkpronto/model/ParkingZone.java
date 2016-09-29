package edu.umbc.parkpronto.model;

import com.google.android.gms.maps.model.LatLng;

import io.realm.RealmObject;

/**
 * Created by sushantathley on 9/28/16.
 */

public class ParkingZone {

    public ParkingZone(String id, ParkingPermit permit, ParkingType parkingType) {
        this.id = id;
        this.permit = permit;
        this.type = parkingType;
    }

    public String id;
    public ParkingPermit permit;
    public ParkingType type;
    public LatLng[] coordinates;
    public ParkingAvailability availability;

}
