package edu.umbc.parkpronto;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import edu.umbc.parkpronto.model.MapType;
import edu.umbc.parkpronto.model.ParkingAvailability;
import edu.umbc.parkpronto.model.ParkingInfoFactory;
import edu.umbc.parkpronto.model.ParkingPermit;
import edu.umbc.parkpronto.model.ParkingType;
import edu.umbc.parkpronto.model.ParkingZone;
import edu.umbc.parkpronto.util.SharedPrefManager;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private RelativeLayout mButtonA, mButtonB, mButtonC, mButtonD;
    private Button innerBtnA, innerBtnB, innerBtnC, innerBtnD;
    private Button answer0, answer1, answer2, answer3;
    Map<ParkingPermit, ArrayList<ParkingZone>> data;
    SharedPrefManager prefManager;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 847;
    private NavigationView mNavigationView;
    private CoordinatorLayout mCoordinatorLayout;
    private CardView mCardView;
    private MapType mMapType = MapType.NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        prefManager = new SharedPrefManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mCardView = (CardView) findViewById(R.id.cv_answer);
        mCardView.setVisibility(View.INVISIBLE);

        ParkingInfoFactory factory = new ParkingInfoFactory();
        data = factory.getData();

        initializeUI();
    }

    private void initializeUI() {
        mButtonA = (RelativeLayout) findViewById(R.id.input1btn);
        mButtonB = (RelativeLayout) findViewById(R.id.input2btn);
        mButtonC = (RelativeLayout) findViewById(R.id.input3btn);
        mButtonD = (RelativeLayout) findViewById(R.id.input4btn);

        innerBtnA = (Button) findViewById(R.id.inner1btn);
        innerBtnB = (Button) findViewById(R.id.inner2btn);
        innerBtnC = (Button) findViewById(R.id.inner3btn);
        innerBtnD = (Button) findViewById(R.id.inner4btn);

        answer0 = (Button) findViewById(R.id.btn_0);
        answer1 = (Button) findViewById(R.id.btn_1);
        answer2 = (Button) findViewById(R.id.btn_2);
        answer3 = (Button) findViewById(R.id.btn_3);

        mButtonA.setOnClickListener(this);
        mButtonB.setOnClickListener(this);
        mButtonC.setOnClickListener(this);
        mButtonD.setOnClickListener(this);

        innerBtnA.setOnClickListener(this);
        innerBtnB.setOnClickListener(this);
        innerBtnC.setOnClickListener(this);
        innerBtnD.setOnClickListener(this);

        answer0.setOnClickListener(this);
        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);

        //Check which preference was set by user
        if (prefManager.getLastSavedPermit() != null)
            setAppropriateButton(prefManager.getLastSavedPermit());

    }

    private void setAppropriateButton(ParkingPermit lastSavedPermit) {
        if (lastSavedPermit.equals(ParkingPermit.A)) {
            toggleButtonA();
        } else if (lastSavedPermit.equals(ParkingPermit.B)) {
            toggleButtonB();

        } else if (lastSavedPermit.equals(ParkingPermit.C)) {
            toggleButtonC();

        } else if (lastSavedPermit.equals(ParkingPermit.D)) {
            toggleButtonD();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_type_normal) {
            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_json));
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mNavigationView.getMenu().findItem(R.id.nav_type_satellite).setChecked(false);
            item.setChecked(true);

        } else if (id == R.id.nav_type_satellite) {

            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mNavigationView.getMenu().findItem(R.id.nav_type_normal).setChecked(false);
            item.setChecked(true);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        checkPermission();
        mMap = googleMap;
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.style_json));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        // Zoom to UMBC
        LatLngBounds umbcBounds = new LatLngBounds(new LatLng(39.250842, -76.724043), new LatLng(39.262102, -76.700987));
        mMap.setLatLngBoundsForCameraTarget(umbcBounds);
        LatLng umbcCentre = new LatLng(39.255928, -76.711093);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(umbcCentre, 15));
        mMap.setLatLngBoundsForCameraTarget(umbcBounds);


        // Get last accessed permit
        ParkingPermit lastSavedPermit = new SharedPrefManager().getLastSavedPermit();

        // Plot parking zones
        ArrayList<ParkingZone> zones = data.get(lastSavedPermit);
        plotZones(lastSavedPermit, zones);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // input1btn -> Outer relative layout
            // inner1btn -> inner btn layout.

            case R.id.input1btn:
                toggleButtonA();
                break;
            case R.id.input2btn:
                toggleButtonB();
                break;
            case R.id.input3btn:
                toggleButtonC();
                break;

            case R.id.input4btn:
                toggleButtonD();
                break;

            case R.id.inner1btn:
                toggleButtonA();
                break;

            case R.id.inner2btn:
                toggleButtonB();
                break;
            case R.id.inner3btn:
                toggleButtonC();
                break;

            case R.id.inner4btn:
                toggleButtonD();
                break;

            // answer buttons

            case R.id.btn_0:
                mCardView.setVisibility(View.INVISIBLE);
                break;

            case R.id.btn_1:
                mCardView.setVisibility(View.INVISIBLE);
                break;

            case R.id.btn_2:
                mCardView.setVisibility(View.INVISIBLE);
                break;

            case R.id.btn_3:
                mCardView.setVisibility(View.INVISIBLE);
                break;


        }
    }

    public void plotZones(ParkingPermit permit, ArrayList<ParkingZone> zones) {
        mMap.clear();
        int color = getColorForPermit(permit);

        for (ParkingZone zone : zones) {
            if (zone.type == ParkingType.LOT) {
                Polygon polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(Arrays.asList(zone.coordinates))
                        .zIndex(10)
                        .strokeWidth(5)
                        .strokeColor(color)

                        .fillColor(color));


                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(getPolygonCenterPoint(polygon.getPoints()));
                markerOptions.icon(BitmapDescriptorFactory.fromResource(getBitmapForAvailability(zone.availability)));
                markerOptions.snippet(zone.name);
                markerOptions.title(getSnippetForAvailability(zone.availability));

                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(zone.id);


            } else {
                Polyline polyline = mMap.addPolyline(new PolylineOptions()
                        .addAll(Arrays.asList(zone.coordinates))
                        .width(24)
                        .color(color));
                int count = polyline.getPoints().size();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(polyline.getPoints().get(count / 2));
                markerOptions.icon(BitmapDescriptorFactory.fromResource(getBitmapForAvailability(zone.availability)));
                markerOptions.title(getSnippetForAvailability(zone.availability));

                Marker marker = mMap.addMarker(markerOptions);
                marker.setTag(zone.id);

            }
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String id = (String) marker.getTag();
                // mCardView.setVisibility(View.VISIBLE);
                showCardView();
                return false;

            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mCardView.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void showCardView() {

        mCardView.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_left);
        mCardView.startAnimation(animation);

    }

    private int getBitmapForAvailability(ParkingAvailability availability) {
        switch (availability) {
            case FULL: {
                return R.drawable.marker0;
            }
            case LESS_THAN_FIVE: {
                return R.drawable.marker1;
            }
            case FIVE_TO_TEN: {
                return R.drawable.marker2;
            }
            case GREATER_THAN_TEN: {
                return R.drawable.marker3;
            }

        }
        return 0;
    }

    private String getSnippetForAvailability(ParkingAvailability availability) {
        switch (availability) {
            case FULL: {
                return "FULL";
            }
            case LESS_THAN_FIVE: {
                return "0-5";
            }
            case FIVE_TO_TEN: {
                return "5-10";
            }
            case GREATER_THAN_TEN: {
                return "10+";
            }

        }
        return null;
    }

    private int getColorForPermit(ParkingPermit parkingPermit) {
        if (parkingPermit == ParkingPermit.A)
            return getColor(R.color.colorAWithAlpha);
        if (parkingPermit == ParkingPermit.B)
            return getColor(R.color.colorBWithAlpha);
        if (parkingPermit == ParkingPermit.C)
            return getColor(R.color.colorCWithAlpha);
        if (parkingPermit == ParkingPermit.D)
            return getColor(R.color.colorDWithAlpha);


        else return R.color.colorPrimary;

    }

    public void toggleButtonA() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtna));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));

        if (mMap != null)
            plotZones(ParkingPermit.A, data.get(ParkingPermit.A));
    }

    public void toggleButtonB() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtnb));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        if (mMap != null)
            plotZones(ParkingPermit.B, data.get(ParkingPermit.B));
    }

    public void toggleButtonC() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtnc));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        if (mMap != null)
            plotZones(ParkingPermit.C, data.get(ParkingPermit.C));
    }

    public void toggleButtonD() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtnd));
        if (mMap != null)
            plotZones(ParkingPermit.D, data.get(ParkingPermit.D));
    }

    private void checkPermission() {

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    mMap.setMyLocationEnabled(true);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private LatLng getPolygonCenterPoint(List<LatLng> polygonPointsList) {
        LatLng centerLatLng = null;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < polygonPointsList.size(); i++) {
            builder.include(polygonPointsList.get(i));
        }
        LatLngBounds bounds = builder.build();
        centerLatLng = bounds.getCenter();

        return centerLatLng;
    }


}
