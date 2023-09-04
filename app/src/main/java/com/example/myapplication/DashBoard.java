package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.Home_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    Home_Fragment homeFragment=new Home_Fragment();
    ProfileFragment profileFragment=new ProfileFragment();
    Whislist_Fragment whislistFragment=new Whislist_Fragment();
    Search_Fragment searchFragment=new Search_Fragment();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

          toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open,
                R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();






       /* Home_Fragment home_fragment = new Home_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("first", str);
        home_fragment.setArguments(bundle);

        // Replace the fragment_container with the new fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, home_fragment);
        fragmentTransaction.commit();
*/
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_Fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i=item.getItemId();
                if(i==R.id.home){

                    Toast.makeText(DashBoard.this, "Home", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_Fragment()).commit();



                }  else if (i==R.id.search) {
                    Toast.makeText(DashBoard.this, "Search", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Search_Fragment()).commit();



                }
                else if(i==R.id.account) {
                    Toast.makeText(DashBoard.this, "Account", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

                } else if (i==R.id.wish_list) {
                    Toast.makeText(DashBoard.this, "whishlist", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Whislist_Fragment()).commit();

                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_home){

            Toast.makeText(DashBoard.this, "Home", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_Fragment()).commit();



        }  else if (id==R.id.nav_account) {
            Toast.makeText(DashBoard.this, "Profile", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();


        }
        else if(id==R.id.nav_share) {
            Toast.makeText(DashBoard.this, "share", Toast.LENGTH_SHORT).show();

        } else if (id==R.id.nav_about) {
            Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "dfgdgfdg", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
}