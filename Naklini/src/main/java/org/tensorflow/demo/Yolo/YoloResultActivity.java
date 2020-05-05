package org.tensorflow.demo.Yolo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.demo.R;
import org.tensorflow.demo.mappoint.ExamDBHelper;


//import android.support.v7.app.AppCompatActivity;


public class YoloResultActivity extends AppCompatActivity {
    ExamDBHelper dbHelper;
    ImageView yoloImage;
    TextView yoloName;
    TextView yoloField;
    TextView yoloHomeTown;
    SQLiteDatabase db;
    String afterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yolo_result);

        yoloImage =findViewById(R.id.yoloImage);
        yoloName = findViewById(R.id.yoloName);
        yoloField = findViewById(R.id.yoloField);
        yoloHomeTown =findViewById(R.id.yoloHomeTown);

        Intent intent = getIntent();
        String fishName = intent.getStringExtra("fishName");

        if (fishName.equals("flounder")){
            afterName="넙치";
        }
        dbHelper = new ExamDBHelper(this);
        db = dbHelper.getWritableDatabase();

        String sql = "select * from SPECIES where 국명 ="+"'"+afterName+"'";
        Cursor cursor = db.rawQuery(sql,null);


        String Name = cursor.getString(1);
        String filed = cursor.getString(2);
        String homeTown = cursor.getString(3);

        yoloName.setText(Name);
        yoloField.setText(filed);
        yoloHomeTown.setText(homeTown);



    }



}
