package com.project.fishbegin.map.db;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.fishbegin.R;

import java.util.List;


public class dbread extends AppCompatActivity {
    TextView txt;
    List<Onboard> onboardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbread);
        txt = findViewById(R.id.txt);
        initLoadDB();
    }

    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        onboardList = mDbHelper.getTableData();


        txt.setText(onboardList.get(1).getName()+onboardList.get(1).getPoint_nm()+onboardList.get(1).getLatitude()+
                onboardList.get(1).getLongitude());

        // db 닫기
        mDbHelper.close();
    }
}
