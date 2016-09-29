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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

import edu.umbc.parkpronto.model.ParkingInfoFactory;
import edu.umbc.parkpronto.model.ParkingPermit;
import edu.umbc.parkpronto.model.ParkingZone;

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

        // Add a marker in Sydney and move the camera
        LatLng umbc = new LatLng(39.255858, -76.711184);
        mMap.addMarker(new MarkerOptions().position(umbc).title("Marker in UMBC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(umbc));
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


    public void onAClick() {

    }

    public void onBClick() {

    }

    public void onCClick() {

    }

    public void onDClick() {

    }
}
