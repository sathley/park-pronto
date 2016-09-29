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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

import edu.umbc.parkpronto.model.ParkingInfoFactory;
import edu.umbc.parkpronto.model.ParkingPermit;
import edu.umbc.parkpronto.model.ParkingZone;
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
            setLastSavedPreference(prefManager.getLastSavedPermit());

    }

    private void setLastSavedPreference(ParkingPermit lastSavedPermit) {
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

        // Add a marker in Sydney and move the camera
        LatLng umbc = new LatLng(39.255858, -76.711184);
        mMap.addMarker(new MarkerOptions().position(umbc).title("Marker in UMBC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(umbc));
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

    public void toggleButtonA() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtna));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
    }

    public void toggleButtonB() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtnb));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
    }

    public void toggleButtonC() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtnc));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
    }

    public void toggleButtonD() {
        innerBtnA.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnB.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnC.setBackground(getResources().getDrawable(R.drawable.roundbtn_unselected));
        innerBtnD.setBackground(getResources().getDrawable(R.drawable.roundbtnd));
    }
}
