package com.baiyi.test;

import com.baiyi.packageofView.Myview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private Myview myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myview = (Myview) findViewById(R.id.myview);
        
        
        
        
    }


}
