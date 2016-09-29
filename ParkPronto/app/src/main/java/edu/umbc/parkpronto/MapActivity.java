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
import android.widget.RelativeLayout;

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
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private RelativeLayout mButtonA, mButtonB, mButtonC, mButtonD;
    private Button innerBtnA, innerBtnB, innerBtnC, innerBtnD;
    Map<ParkingPermit, ArrayList<ParkingZone>> data;
    SharedPrefManager prefManager;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

        mButtonA.setOnClickListener(this);
        mButtonB.setOnClickListener(this);
        mButtonC.setOnClickListener(this);
        mButtonD.setOnClickListener(this);
        innerBtnA.setOnClickListener(this);
        innerBtnB.setOnClickListener(this);
        innerBtnC.setOnClickListener(this);
        innerBtnD.setOnClickListener(this);

        //Check which preference was set by user
        if (prefManager.getLastSavedPermit() != null)
            setAppropriateButton(prefManager.getLastSavedPermit());

    }

    private void setAppropriateButton(ParkingPermit lastSavedPermit) {
              if(lastSavedPermit.equals(ParkingPermit.A))
              {
                 toggleButtonA();
              }else if(lastSavedPermit.equals(ParkingPermit.B))
              {
                  toggleButtonB();

              }else if(lastSavedPermit.equals(ParkingPermit.C))
              {
                  toggleButtonC();

              } else if(lastSavedPermit.equals(ParkingPermit.D))
              {
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
        //mMap.setMyLocationEnabled(true);

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

    public void toggleButtonA() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtna));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));

        if(mMap != null)
            plotZones(ParkingPermit.A, data.get(ParkingPermit.A));
    }

    public void toggleButtonB() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtnb));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        if(mMap != null)
        plotZones(ParkingPermit.B, data.get(ParkingPermit.B));
    }

    public void toggleButtonC() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtnc));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        if(mMap != null)
        plotZones(ParkingPermit.C, data.get(ParkingPermit.C));
    }

    public void toggleButtonD() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtnd));
        if(mMap != null)
        plotZones(ParkingPermit.D, data.get(ParkingPermit.D));
    }
}
