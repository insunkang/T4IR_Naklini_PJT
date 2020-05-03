package org.tensorflow.demo.Yolo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.demo.DetectorActivity;
import org.tensorflow.demo.R;


public class YoloMainActivity extends AppCompatActivity {
    Button btn1 ;
    DetectorActivity detectorActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YoloMainActivity.this, DetectorActivity.class);
                startActivity(intent);
            }
        });
    }


}
