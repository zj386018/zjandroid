package com.baiyi.test;

import android.app.Activity;
import android.os.Bundle;

import com.baiyi.packageofView.Myview;

public class MainActivity extends Activity {
	
	private Myview myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myview = (Myview) findViewById(R.id.myview);
        
        
        
        
    }


}
