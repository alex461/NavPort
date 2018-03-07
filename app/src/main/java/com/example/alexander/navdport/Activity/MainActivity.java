package com.example.alexander.navdport.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.alexander.navdport.R;
import com.example.alexander.navdport.fragments.AlertFragment;
import com.example.alexander.navdport.fragments.EmailFragment;
import com.example.alexander.navdport.fragments.InfoFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolbar();


        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.naview);

        setFragmentByDefaul();
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment =null;

                switch (item.getItemId()){

                    case R.id.menu_mail:
                        fragment= new EmailFragment();
                        fragmentTransaction=true;
                        break;

                    case R.id.menu_info:
                        fragment=new InfoFragment();
                        fragmentTransaction = true;
                        break;

                    case R.id.menu_alert:
                        fragment=new AlertFragment();
                        fragmentTransaction = true;
                        break;
                        
                    case R.id.menu_option1:
                        Toast.makeText(MainActivity.this, "Has clickeado Opcion1", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_option2:
                        Toast.makeText(MainActivity.this, "Has Clickeado Opcion2", Toast.LENGTH_SHORT).show();
                }

                if(fragmentTransaction){
                    changeFragment(fragment,item);
                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });

    }


    private void showToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefaul(){
        changeFragment(new EmailFragment(),navigationView.getMenu().getItem(0));
    }


        private void changeFragment(Fragment fragment,MenuItem item){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.content_frame,fragment)
                    .commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
