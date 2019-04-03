package com.resolve.security;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.resolve.security.adapters.DashboardAdapter;
import com.resolve.security.datados.Output;
import com.resolve.security.utils.Preferences;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView ivProfile;
    private TextView tvUserTitle, tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView rView = findViewById(R.id.rvDashboard);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        DashboardAdapter adapter = new DashboardAdapter();
        rView.setAdapter(adapter);

        View hView = navigationView.getHeaderView(0);
        ivProfile = hView.findViewById(R.id.ivProfile);
        tvUserTitle = hView.findViewById(R.id.tvUserTitle);
        tvLocation = hView.findViewById(R.id.tvLocation);

        setProfileInfo();
    }

    private void setProfileInfo() {
        String userData = Preferences.getString(Preferences.USER_DATA);
        Output output = new Gson().fromJson(userData, Output.class);

        // User profile
        String profileUrl = output.getUserImage();
        if (!TextUtils.isEmpty(profileUrl)) {
            Picasso.get().load(profileUrl).into(ivProfile);
        }

        // User Name
        String profileName = output.getUserName();
        tvUserTitle.setText(profileName);

        // Location name
        String locationName = output.getLocationName();
        tvLocation.setText(locationName);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        if(id == R.id.nav_logout){
            logout();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout(){
        Preferences.clearAll();
        finishAffinity();
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
