package org.tensorflow.demo.pointdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.demo.Fish_Detail_InfoList;
import org.tensorflow.demo.R;

import java.util.ArrayList;


public class Fish_Detail_Info_Activity extends AppCompatActivity {
    Context mContext;
    Fish_Detail_Info_DBHandler handler;
    ArrayList<Species> speciesList;
    Species species;

    ArrayList<String> targetList;

    RecyclerView infoView;
    FishInfoAdapter adapter;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = Fish_Detail_Info_Activity.this;
        setContentView(R.layout.fish_detail_info);
        infoView = findViewById(R.id.fish_info);
        handler = Fish_Detail_Info_DBHandler.open(mContext);

        intent = getIntent();
        targetList = intent.getStringArrayListExtra("fishArray");

        speciesList = handler.SpeciesList(targetList);
        Log.d("species", speciesList.size()+"");
        adapter = new FishInfoAdapter(mContext, R.layout.info_detail, speciesList);
        infoView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        infoView.setLayoutManager(manager);
        infoView.setAdapter(adapter);
    }
}
