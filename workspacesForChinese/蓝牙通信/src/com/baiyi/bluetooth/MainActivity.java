package com.baiyi.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private TextView maintv;
	private BluetoothAdapter myBluetoothAdapter;
	private Button mainbt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();

	}

	private void initview() {
		maintv = (TextView) findViewById(R.id.main_tv);
		myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		mainbt = (Button) findViewById(R.id.main_bt);
		mainbt.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.main_bt:
			mainBtClick();
			break;

		default:
			break;
		}

	}

	private void mainBtClick() {
		if (myBluetoothAdapter == null) {
			maintv.setText("蓝牙不可用");
		} else {
			if (!myBluetoothAdapter.isEnabled()) {
				maintv.setText("蓝牙未开启");
			} else {
				maintv.setText("蓝牙已开启，可用");
			}
		}
	}

}
