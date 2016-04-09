package com.baiyi.test;

import android.app.Activity;
import android.os.Bundle;

import com.baiyi.packageofView.Myview;
import com.baiyi.xuanzhuan.RotateImageView;

public class MainActivity extends Activity {
	RotateImageView rotateImg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        rotateImg1 = (RotateImageView)findViewById(R.id.rotate_img_1);
        rotateImg1.setImageResource(R.drawable.protractor_landscape_background);
        rotateImg1.stopRotate(true);
        
    }


}
