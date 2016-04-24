package com.baiyi.main;

import com.baiyi.util.AnimUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView iv_home,iv_menu;
	private RelativeLayout level1, level2, level3;
	private boolean isShowLeve2 = true;// 是否显示二级菜单
	private boolean isShowLeve3 = true;// 是否显示三级菜单
	private boolean isShowMenu = true;//是否显示整个菜单（包括一级和二级菜单）
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initListener();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		setContentView(R.layout.activity_main);
		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_menu = (ImageView) findViewById(R.id.iv_menu);
		level1 = (RelativeLayout) findViewById(R.id.level1);
		level2 = (RelativeLayout) findViewById(R.id.level2);
		level3 = (RelativeLayout) findViewById(R.id.level3);

	}

	/**
	 * 初始化事件监听
	 */
	private void initListener() {
		iv_home.setOnClickListener(this);
		iv_menu.setOnClickListener(this);
	}

	/**
	 * 手机HOME键和MENU键的事件监听。
	 * 现在的手机似乎没有这个了。。。
	 * ！！！（！！（所以一般可以不用管这个）！！）！！！
	 * 
	 * 该方法主要是关闭所有菜单，包括第一级菜单。
	 * 			 打开第一级菜单。
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(keyCode == KeyEvent.KEYCODE_MENU){
			
			if(isShowMenu){
				//需要关闭所有菜单
				int startOffset = 0;
				if(isShowLeve3){
					AnimUtil.closeMenu(level3, startOffset);
					isShowLeve3 = !isShowLeve3;
					startOffset += 200;
				}
				if(isShowLeve2){
					AnimUtil.closeMenu(level2, startOffset);
					isShowLeve2 = !isShowLeve2;
					startOffset += 200;
				}
				AnimUtil.closeMenu(level1, startOffset);
			}else{
				//需要显示所有菜单
				AnimUtil.showMenu(level1, 0);
				AnimUtil.showMenu(level2, 200);
				isShowLeve2 = !isShowLeve2;
				AnimUtil.showMenu(level3, 400);
				isShowLeve3 = !isShowLeve3;
				
			}
			isShowMenu = !isShowMenu;
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	/**
	 * 菜单点击事件监听
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_home:
			if(AnimUtil.animCount != 0){
				//说明有动画在执行，直接返回，而不执行下面的操作
				return;
			}
			int startOffset = 0;
			if (isShowLeve2) {
				//若第三级菜单处于显示阶段，将隐藏
				if(isShowLeve3){
					AnimUtil.closeMenu(level3,startOffset);
					isShowLeve3 = !isShowLeve3;
					startOffset += 200;
				}
				// 将执行隐藏第二级菜单的操作
				AnimUtil.closeMenu(level2,startOffset);
			} else {
				// 将执行显示第二级菜单的操作
				AnimUtil.showMenu(level2,startOffset);
			}
			isShowLeve2 = !isShowLeve2;
			break;
		case R.id.iv_menu:
			if(AnimUtil.animCount != 0){
				//说明有动画在执行，直接返回，而不执行下面的操作
				return;
			}
			if (isShowLeve3) {
				// 将执行隐藏第三级菜单的操作
				AnimUtil.closeMenu(level3,0);
			} else {
				// 将执行显示第三级菜单的操作
				AnimUtil.showMenu(level3,0);
			}
			isShowLeve3 = !isShowLeve3;
			break;
		default:
			break;
		}
	}

}
