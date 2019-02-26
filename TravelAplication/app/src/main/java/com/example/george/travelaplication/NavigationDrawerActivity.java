package com.example.george.travelaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
import android.widget.CheckBox;
import android.widget.TextView;

public class NavigationDrawerActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
        private static final String TAG = "DRAWERTAG";
        private TextView mNameTextView;
        private TextView mEmailTextView;
        private NavigationView navigationView;
        private String EmailString;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

            MainFragment details = new MainFragment();
            details.setArguments(getIntent().getExtras());
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.replace_place, details);
        ft.commit();

        initView();
    }

    private void initView() {
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        mEmailTextView = (TextView) headerView.findViewById(R.id.header_email_textview);
        mNameTextView = (TextView) headerView.findViewById(R.id.header_name_textview);
        //Log.d(TAG, mEmailTextView.getText().toString());
        Bundle extras= getIntent().getExtras();
        if (extras != null && mEmailTextView != null && mNameTextView != null) {
            EmailString=extras.getString("Email");
            mEmailTextView.setText(extras.getString("Email"));
            mNameTextView.setText(extras.getString("Name"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_favourite){

        } else if (id == R.id.nav_aboutus) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void btnOpenNewTravelOnClick(View view) {
        Intent intent = new Intent (NavigationDrawerActivity.this, NewTravelActivity.class);

        intent.putExtra("Email", EmailString);

        startActivity(intent);
    }

    public void btnFavouriteActionOnClicked(View view) {
         Log.d(TAG, "Am apasat butonul");
        CheckBox mButton = view.findViewById(R.id.favourite_button);
         if (mButton.isChecked())
             {mButton.setBackground ( ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_bookmark));
              Log.d(TAG,"Eu incerc,dar nu prea reusesc");
             }
            else{
                mButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_bookmark_not_clicked));
                Log.d(TAG,"Cred ca nu e specific barbatesc");
            }

        }
}
