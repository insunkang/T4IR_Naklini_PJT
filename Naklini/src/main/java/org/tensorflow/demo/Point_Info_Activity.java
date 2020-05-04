package org.tensorflow.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Point_Info_Activity extends AppCompatActivity {
    LinearLayout point_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_info_tool);
        point_info = findViewById(R.id.point_info);
        point_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Point_Info_Activity.this, Fish_Detail_Info_Activity.class);
                startActivity(intent);
            }
        });
    }


}
