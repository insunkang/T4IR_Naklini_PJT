package com.project.fishbegin.mappoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.fishbegin.R;


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

		intent = new Intent(Point1.this, Point2.class);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btnOnboard:
				intent.putExtra("button","Onboard");
				startActivityForResult(intent, 100);
				break;
			case R.id.btnFreshwater:
				intent.putExtra("button","Freshwater");
				startActivityForResult(intent, 100);
				break;
			case R.id.btnRock:
				intent.putExtra("button","Rock");
				startActivityForResult(intent, 100);
				break;
			case R.id.btnSea:
				intent.putExtra("button","Sea");
				startActivityForResult(intent, 100);
				break;
		}
	}

	// 다시 돌아왔을 때
	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		// 리스트뷰 아이템 안 누르고 뒤로가기만 눌렀을 때
		if(resultCode == RESULT_CANCELED)
			return;
		Intent intent2 = getIntent();
		intent2.putExtra("returnCategory", intent.getStringExtra("returnCategory"));
		intent2.putExtra("Parcelable", intent.getParcelableExtra("Parcelable"));
		setResult(RESULT_OK, intent2);
		finish();
	}
}













