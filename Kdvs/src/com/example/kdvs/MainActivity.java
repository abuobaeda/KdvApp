package com.example.kdvs;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	double usd;
	double kdv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Button calc_btn = (Button) findViewById(R.id.btnPrice);
		final EditText edtPrice = (EditText) findViewById(R.id.edtPrice);
		final TextView usdView = (TextView) findViewById(R.id.usdText);
		final TextView kdvView = (TextView) findViewById(R.id.kdvText);
		
		calc_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					double tryKdv = Double.parseDouble(edtPrice.getText().toString());
					double tryConv = kdv * tryKdv;
					double usdConv = tryConv / usd;
					DecimalFormat df = new DecimalFormat("#.##");
					usdView.setText(df.format(usdConv));
					kdvView.setText(df.format(tryConv));
				} catch (Exception e) {
					Toast.makeText(MainActivity.this, "Please, Enter number only", Toast.LENGTH_SHORT).show();
				}
//				TEST
//				Toast.makeText(MainActivity.this, usd + "", Toast.LENGTH_SHORT).show();
//				Toast.makeText(MainActivity.this, kdv + "", Toast.LENGTH_SHORT).show();
//				Toast.makeText(MainActivity.this, edtPrice.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent i = new Intent(MainActivity.this, SettingActivity.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences rePref = this.getSharedPreferences("numSitting", MODE_PRIVATE);
		usd = Double.parseDouble(rePref.getString("usd", "2.90"));
		kdv = Double.parseDouble(rePref.getString("kdv", "8.0")) / 100.0;
		kdv = kdv + 1.0;
	}
}
