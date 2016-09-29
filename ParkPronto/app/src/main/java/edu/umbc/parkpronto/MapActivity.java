package edu.umbc.parkpronto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import edu.umbc.parkpronto.model.ParkingInfoFactory;
import edu.umbc.parkpronto.model.ParkingPermit;
import edu.umbc.parkpronto.model.ParkingType;
import edu.umbc.parkpronto.model.ParkingZone;
import edu.umbc.parkpronto.util.ParkPronto;
import edu.umbc.parkpronto.util.SharedPrefManager;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback ,View.OnClickListener {

    private GoogleMap mMap;
    private Button mButtonA,mButtonB,mButtonC,mButtonD;
    Map<ParkingPermit, ArrayList<ParkingZone>> data;

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ParkingInfoFactory factory = new ParkingInfoFactory();
        data = factory.getData();
        initializeUI();
    }

    private void initializeUI() {
        mButtonA = (Button) findViewById(R.id.input1btn);
        mButtonB = (Button) findViewById(R.id.input2btn);
        mButtonC = (Button) findViewById(R.id.input3btn);
        mButtonD = (Button) findViewById(R.id.input4btn);
        mButtonA.setOnClickListener(this);
        mButtonB.setOnClickListener(this);
        mButtonC.setOnClickListener(this);
        mButtonD.setOnClickListener(this);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);

        // Zoom to UMBC
        LatLngBounds umbcBounds = new LatLngBounds(new LatLng(39.250842, -76.724043), new LatLng(39.262102, -76.700987));
        mMap.setLatLngBoundsForCameraTarget(umbcBounds);
        LatLng umbcCentre = new LatLng(39.255928, -76.711093);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(umbcCentre, 15));
        //mMap.setBuildingsEnabled(true);
        mMap.setLatLngBoundsForCameraTarget(umbcBounds);


        // Get last accessed permit
        ParkingPermit lastSavedPermit = new SharedPrefManager().getLastSavedPermit();

        // Plot parking zones
        ArrayList<ParkingZone> zones = data.get(lastSavedPermit);
        plotZones(lastSavedPermit, zones);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.input1btn :
                mButtonA.setBackground(getDrawable(R.drawable.roundbtna));
                mButtonB.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonC.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonD.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                break;

            case R.id.input2btn :
                mButtonA.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonB.setBackground(getDrawable(R.drawable.roundbtnb));
                mButtonC.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonD.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                break;
            case R.id.input3btn :
                mButtonA.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonB.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonC.setBackground(getDrawable(R.drawable.roundbtnc));
                mButtonD.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                break;

            case R.id.input4btn :
                mButtonA.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonB.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonC.setBackground(getDrawable(R.drawable.roundbtn_unselected));
                mButtonD.setBackground(getDrawable(R.drawable.roundbtnd));
                break;
        }
    }

    public void plotZones(ParkingPermit permit, ArrayList<ParkingZone> zones) {
        mMap.clear();
        int color = getColorForPermit(permit);

        for(ParkingZone zone : zones) {
            if(zone.type == ParkingType.LOT)
            {
                Polygon polygon = mMap.addPolygon(new PolygonOptions()
                        .addAll(Arrays.asList(zone.coordinates))
                        .zIndex(10)
                        .strokeWidth(0)
                        .strokeColor(color)
                        .fillColor(color));
            }
            else {
                Polyline polyline = mMap.addPolyline(new PolylineOptions()
                        .addAll(Arrays.asList(zone.coordinates))
                        .width(24)

                        .color(color));

                        ;

            }
        }

    }


    private int getColorForPermit(ParkingPermit parkingPermit) {
        if(parkingPermit == ParkingPermit.A)
            return getColor(R.color.colorAWithAlpha);
        if(parkingPermit == ParkingPermit.B)
            return getColor(R.color.colorBWithAlpha);
        if(parkingPermit == ParkingPermit.C)
            return getColor(R.color.colorCWithAlpha);
        if(parkingPermit == ParkingPermit.D)
            return getColor(R.color.colorDWithAlpha);


        else return R.color.colorPrimary;

    }

}
