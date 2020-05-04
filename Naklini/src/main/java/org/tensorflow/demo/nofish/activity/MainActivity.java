package org.tensorflow.demo.nofish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.tensorflow.demo.R;
import org.tensorflow.demo.Yolo.YoloMainActivity;
import org.tensorflow.demo.map.map;
import org.tensorflow.demo.tip.TIPActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nofish_activity_main);
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
                Intent intent = new Intent(MainActivity.this, YoloMainActivity.class);
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_navi,menu);
        return true;
    }



}
