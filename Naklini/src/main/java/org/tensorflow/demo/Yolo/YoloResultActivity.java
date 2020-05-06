package org.tensorflow.demo.Yolo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.demo.R;
import org.tensorflow.demo.map.db.DataAdapter;
import org.tensorflow.demo.pointdetail.Species;

import java.util.List;

//import android.support.v7.app.AppCompatActivity;


public class YoloResultActivity extends AppCompatActivity {
    List<Species> lists ;
    DataAdapter dataAdapter;

    ImageView yoloImage;
    TextView yoloName;
    TextView yoloField;
    TextView yoloHomeTown;

    String afterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yolo_result);

        yoloImage =findViewById(R.id.yoloImage);
        yoloName = findViewById(R.id.yoloName);
        yoloField = findViewById(R.id.yoloField);
        yoloHomeTown =findViewById(R.id.yoloHomeTown);
        yoloImage.setImageResource(R.drawable.flounder);


        Intent intent = getIntent();
        String fishName = intent.getStringExtra("fishName");
        if (fishName.equals("flounder")){
            afterName="넙치";
        }
        dataAdapter = new DataAdapter(getApplicationContext());
        dataAdapter.createDatabase();
        dataAdapter.open();

        lists = dataAdapter.YoloResultView(afterName);


        yoloName.setText(lists.get(0).getName());
        yoloField.setText(lists.get(0).getDist());
        yoloHomeTown.setText(lists.get(0).getLiving());

    }


}
