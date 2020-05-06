package org.tensorflow.demo.nofish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.tensorflow.demo.DetectorActivity;
import org.tensorflow.demo.R;
import org.tensorflow.demo.map.map;
import org.tensorflow.demo.tip.TIPActivity;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nofish_activity_main);
        getSupportActionBar().setIcon(R.drawable.mainicon);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerLayout = findViewById(R.id.main_drawer);
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, R.string.open_str,R.string.close_str);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();
        CardView nofishdata = (CardView) findViewById(R.id.stopdate);
        CardView detectdata = (CardView) findViewById(R.id.searchbar);
        CardView mapdata = (CardView) findViewById(R.id.map);
        CardView tip = (CardView) findViewById(R.id.howfish);

        mapdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, map.class);
                startActivity(intent);
            }
        });
        detectdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetectorActivity.class);
                startActivity(intent);
            }
        });

        nofishdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoFishData.class);
                startActivity(intent);
            }
        });
        tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TIPActivity.class);
                startActivity(intent);
            }
        });

        NavigationView navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.Item1){
                    Intent intent = new Intent(MainActivity.this, map.class);
                    startActivity(intent);
                }
                if (id == R.id.Item2){
                    Intent intent = new Intent(MainActivity.this, DetectorActivity.class);
                    startActivity(intent);
                }
                if (id == R.id.Item3){
                    Intent intent = new Intent(MainActivity.this, NoFishData.class);
                    startActivity(intent);
                }
                if (id == R.id.Item4){
                    Intent intent = new Intent(MainActivity.this, TIPActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){

        }
        return super.onOptionsItemSelected(item);
    }

}
