package com.example.kdvs;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		final EditText edtUsd = (EditText) findViewById(R.id.edtUsd);
		final EditText edtKdv = (EditText) findViewById(R.id.edtKdv);
		Button btnSave = (Button) findViewById(R.id.save_btn);
		
		SharedPreferences rePref = this.getSharedPreferences("numSitting", MODE_PRIVATE);
		edtUsd.setText(rePref.getString("usd", "2.90"));
		edtKdv.setText(rePref.getString("kdv", "8.0"));
		
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences pref = getSharedPreferences("numSitting", MODE_PRIVATE);
				SharedPreferences.Editor edtPref = pref.edit();
				edtPref.putString("usd", edtUsd.getText().toString());
				edtPref.putString("kdv", edtKdv.getText().toString());
				edtPref.commit();
				SettingActivity.this.finish();
			}
		});
		
	}

}
