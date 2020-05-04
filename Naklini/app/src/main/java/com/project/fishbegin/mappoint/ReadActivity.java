package com.project.fishbegin.mappoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.project.fishbegin.R;


public class ReadActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		TextView t = (TextView)findViewById(R.id.show);
		/*Product product = intent.getParcelableExtra("product");
		t.setText(product.name+", 수량: "+product.price
				+", 가격: "+product.su+", 전체가격: "+product.totPrice);*/

	}

}
