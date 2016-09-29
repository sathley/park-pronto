package edu.umbc.parkpronto.model;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.umbc.parkpronto.R;
import edu.umbc.parkpronto.util.ParkPronto;

/**
 * Created by sushantathley on 9/28/16.
 */

public class ParkingInfoFactory {

    private Map<ParkingPermit, ArrayList<ParkingZone>> mData;

    public ParkingInfoFactory() {
        ArrayList<ParkingZone> A = new ArrayList<ParkingZone>();
        ArrayList<ParkingZone> B = new ArrayList<ParkingZone>();
        ArrayList<ParkingZone> C = new ArrayList<ParkingZone>();
        ArrayList<ParkingZone> D = new ArrayList<ParkingZone>();


        mData = new HashMap<ParkingPermit, ArrayList<ParkingZone>>();

        ParkingZone zone = new ParkingZone("A1", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.254944, -76.703395),
                new LatLng(39.254284, -76.702899),
                new LatLng(39.254442, -76.702556),
                new LatLng(39.255140, -76.702964)
        };
        A.add(zone);

        zone = new ParkingZone("A2", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.255143, -76.705419),
                new LatLng(39.254653, -76.705676),
                new LatLng(39.254429, -76.705086),
                new LatLng(39.254923, -76.704780)
        };
        A.add(zone);

        zone = new ParkingZone("A3", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.254225, -76.705784),
                new LatLng(39.253993, -76.705140),
                new LatLng(39.253262, -76.705886),
                new LatLng(39.253581, -76.706460)
        };
        A.add(zone);

        zone = new ParkingZone("A4", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.253374, -76.706669),
                new LatLng(39.253070, -76.706175),
                new LatLng(39.252638, -76.706599),
                new LatLng(39.252746, -76.707291)
        };
        A.add(zone);

        zone = new ParkingZone("A5", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.FULL;
        zone.coordinates = new LatLng[]{
                new LatLng(39.252333, -76.706388),
                new LatLng(39.252319, -76.706085),
                new LatLng(39.252182, -76.706093),
                new LatLng(39.252198, -76.706388)
        };
        A.add(zone);

        zone = new ParkingZone("A6", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.FIVE_TO_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.254748, -76.707412),
                new LatLng(39.254437, -76.706754),
                new LatLng(39.254179, -76.706950),
                new LatLng(39.254464, -76.707573)
        };
        A.add(zone);

        zone = new ParkingZone("A7", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.LESS_THAN_FIVE;
        zone.coordinates = new LatLng[]{
                new LatLng(39.254391, -76.707661),
                new LatLng(39.254003, -76.707055),
                new LatLng(39.253551, -76.707548),
                new LatLng(39.253763, -76.707873),
                new LatLng(39.253935, -76.707712),
                new LatLng(39.254162, -76.708066)
        };
        A.add(zone);

        zone = new ParkingZone("A8", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.253960, -76.708383),
                new LatLng(39.253580, -76.707782),
                new LatLng(39.253263, -76.708152),
                new LatLng(39.253472, -76.708367),
                new LatLng(39.253566, -76.708291),
                new LatLng(39.253730, -76.708570)
        };
        A.add(zone);

        zone = new ParkingZone("A9", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.255050, -76.708476),
                new LatLng(39.254796, -76.708642),
                new LatLng(39.254605, -76.708149),
                new LatLng(39.254861, -76.707990)
        };
        A.add(zone);

        zone = new ParkingZone("A10", ParkingPermit.A, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.256802, -76.716947),
                new LatLng(39.257675, -76.719109),
                new LatLng(39.258215, -76.718591),
                new LatLng(39.257089, -76.716684)
        };
        A.add(zone);



        zone = new ParkingZone("D1", ParkingPermit.D, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.254944, -76.703395),
                new LatLng(39.254284, -76.702899),
                new LatLng(39.254442, -76.702556),
                new LatLng(39.255140, -76.702964)
        };
        D.add(zone);

        zone = new ParkingZone("D2", ParkingPermit.D, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.254657, -76.704360),
                new LatLng(39.254259, -76.704789),
                new LatLng(39.253868, -76.704113),
                new LatLng(39.254342, -76.703850)
        };
        D.add(zone);

        zone = new ParkingZone("D3", ParkingPermit.D, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.251540, -76.707299),
                new LatLng(39.250834, -76.706285),
                new LatLng(39.250755, -76.706382),
                new LatLng(39.251419, -76.707422)
        };
        D.add(zone);

        zone = new ParkingZone("D4", ParkingPermit.D, ParkingType.LOT);
        zone.availability = ParkingAvailability.GREATER_THAN_TEN;
        zone.coordinates = new LatLng[]{
                new LatLng(39.253665, -76.708704),
                new LatLng(39.253119, -76.708238),
                new LatLng(39.252980, -76.708557),
                new LatLng(39.253535, -76.709029)
        };
        D.add(zone);


        // Curved paths
        JSONObject obj = null;
        JSONArray arr = null;
        try {
            obj = new JSONObject("{ \"snappedPoints\": [ { \"location\": { \"latitude\": 39.254514417830315, \"longitude\": -76.706253691376915 }, \"originalIndex\": 0, \"placeId\": \"ChIJk9MAaLQdyIkRekVP0Vs6Vqg\" }, { \"location\": { \"latitude\": 39.2545138, \"longitude\": -76.7062542 }, \"placeId\": \"ChIJk9MAaLQdyIkRekVP0Vs6Vqg\" }, { \"location\": { \"latitude\": 39.2545138, \"longitude\": -76.7062542 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2541632, \"longitude\": -76.7065428 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2538799, \"longitude\": -76.706819799999991 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2536967, \"longitude\": -76.7070339 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2536967, \"longitude\": -76.7070339 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.25362, \"longitude\": -76.7071235 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.2532867, \"longitude\": -76.707546 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.2529138, \"longitude\": -76.7080489 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.252612718444276, \"longitude\": -76.70849298303969 }, \"originalIndex\": 1, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" } ] }");

            zone = new ParkingZone("A11", ParkingPermit.A, ParkingType.STREET);
            zone.availability = ParkingAvailability.GREATER_THAN_TEN;
            arr = obj.getJSONArray("snappedPoints");
            zone.coordinates = new LatLng[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                zone.coordinates[i] = new LatLng(arr.getJSONObject(i).getJSONObject("location").getDouble("latitude"), arr.getJSONObject(i).getJSONObject("location").getDouble("longitude"));
            }
            A.add(zone);


            obj = new JSONObject("{ \"snappedPoints\": [ { \"location\": { \"latitude\": 39.254559912284549, \"longitude\": -76.705999664377416 }, \"originalIndex\": 0, \"placeId\": \"ChIJp8KfaLQdyIkRlJiAHZ3gm-w\" }, { \"location\": { \"latitude\": 39.254570600000008, \"longitude\": -76.70602439999999 }, \"placeId\": \"ChIJp8KfaLQdyIkRlJiAHZ3gm-w\" }, { \"location\": { \"latitude\": 39.254570600000008, \"longitude\": -76.70602439999999 }, \"placeId\": \"ChIJGynbZ7QdyIkRCKLJILyv_NA\" }, { \"location\": { \"latitude\": 39.2546162, \"longitude\": -76.706169899999992 }, \"placeId\": \"ChIJGynbZ7QdyIkRCKLJILyv_NA\" }, { \"location\": { \"latitude\": 39.2546162, \"longitude\": -76.706169899999992 }, \"placeId\": \"ChIJk9MAaLQdyIkRekVP0Vs6Vqg\" }, { \"location\": { \"latitude\": 39.2545138, \"longitude\": -76.7062542 }, \"placeId\": \"ChIJk9MAaLQdyIkRekVP0Vs6Vqg\" }, { \"location\": { \"latitude\": 39.2545138, \"longitude\": -76.7062542 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2541632, \"longitude\": -76.7065428 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2538799, \"longitude\": -76.706819799999991 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2536967, \"longitude\": -76.7070339 }, \"placeId\": \"ChIJ_6UxR7QdyIkRBeYgWDquhos\" }, { \"location\": { \"latitude\": 39.2536967, \"longitude\": -76.7070339 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.25362, \"longitude\": -76.7071235 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.2532867, \"longitude\": -76.707546 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.2529138, \"longitude\": -76.7080489 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.2525165, \"longitude\": -76.708634899999993 }, \"placeId\": \"ChIJo6qO1LUdyIkRNh8UAnXa1-Q\" }, { \"location\": { \"latitude\": 39.2525165, \"longitude\": -76.708634899999993 }, \"placeId\": \"ChIJPQABcbYdyIkRKQxr4fpnv9Q\" }, { \"location\": { \"latitude\": 39.2524078, \"longitude\": -76.7085197 }, \"originalIndex\": 1, \"placeId\": \"ChIJPQABcbYdyIkRKQxr4fpnv9Q\" } ] }");

            zone = new ParkingZone("A12", ParkingPermit.A, ParkingType.STREET);
            zone.availability = ParkingAvailability.GREATER_THAN_TEN;
            arr = obj.getJSONArray("snappedPoints");
            zone.coordinates = new LatLng[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                zone.coordinates[i] = new LatLng(arr.getJSONObject(i).getJSONObject("location").getDouble("latitude"), arr.getJSONObject(i).getJSONObject("location").getDouble("longitude"));
            }
            A.add(zone);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        mData.put(ParkingPermit.A, A);
        mData.put(ParkingPermit.B, B);
        mData.put(ParkingPermit.C, C);
        mData.put(ParkingPermit.D, D);


    }

    public Map<ParkingPermit, ArrayList<ParkingZone>> getData() {
        return mData;
    }


}
