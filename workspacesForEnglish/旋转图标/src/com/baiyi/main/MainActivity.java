package com.baiyi.main;

import com.baiyi.util.AnimUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView iv_home;
	private RelativeLayout level1,level2,level3;
	private boolean isShowLeve2 = true;//是否显示二级菜单

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initListener();
	}

	private void initView() {
		setContentView(R.layout.activity_main);
		iv_home = (ImageView) findViewById(R.id.iv_home);
		 level1 = (RelativeLayout) findViewById(R.id.level1);
		 level2 = (RelativeLayout) findViewById(R.id.level2);
		 level3 = (RelativeLayout) findViewById(R.id.level3);

	}

	private void initListener() {
		iv_home.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_home:
			if(isShowLeve2){
				//将执行隐藏第二级菜单的操作
				
				AnimUtil.closeMenu(level2);
				
			}else{
				//将执行显示第二级菜单的操作
			}
			isShowLeve2 = !isShowLeve2;
			break;

		default:
			break;
		}
	}

}
