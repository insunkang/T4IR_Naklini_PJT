package org.tensorflow.demo.mappoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.demo.R;


public class Point1 extends AppCompatActivity implements OnClickListener {
	DBHandler handler;
	Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.point1);

		handler = DBHandler.open(this);

		findViewById(R.id.btnOnboard).setOnClickListener(this);
		findViewById(R.id.btnFreshwater).setOnClickListener(this);
		findViewById(R.id.btnRock).setOnClickListener(this);
		findViewById(R.id.btnSea).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btnOnboard:
				intent = new Intent(Point1.this, Point2.class);
				intent.putExtra("button","Onboard");
				startActivity(intent);
				break;
			case R.id.btnFreshwater:
				intent = new Intent(Point1.this, Point2.class);
				intent.putExtra("button","Freshwater");
				startActivity(intent);
				break;
			case R.id.btnRock:
				intent = new Intent(Point1.this, Point2.class);
				intent.putExtra("button","Rock");
				startActivity(intent);
				break;
			case R.id.btnSea:
				intent = new Intent(Point1.this, Point2.class);
				intent.putExtra("button","Sea");
				startActivity(intent);
				break;
		}
	}

}













