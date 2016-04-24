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
	private boolean isShowLeve2 = true;// �Ƿ���ʾ�����˵�
	private boolean isShowLeve3 = true;// �Ƿ���ʾ�����˵�
	private boolean isShowMenu = true;//�Ƿ���ʾ�����˵�������һ���Ͷ����˵���
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initListener();
	}

	/**
	 * ��ʼ���ؼ�
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
	 * ��ʼ���¼�����
	 */
	private void initListener() {
		iv_home.setOnClickListener(this);
		iv_menu.setOnClickListener(this);
	}

	/**
	 * �ֻ�HOME����MENU�����¼�������
	 * ���ڵ��ֻ��ƺ�û������ˡ�����
	 * ������������������һ����Բ��ù������������������
	 * 
	 * �÷�����Ҫ�ǹر����в˵���������һ���˵���
	 * 			 �򿪵�һ���˵���
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(keyCode == KeyEvent.KEYCODE_MENU){
			
			if(isShowMenu){
				//��Ҫ�ر����в˵�
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
				//��Ҫ��ʾ���в˵�
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
	 * �˵�����¼�����
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_home:
			if(AnimUtil.animCount != 0){
				//˵���ж�����ִ�У�ֱ�ӷ��أ�����ִ������Ĳ���
				return;
			}
			int startOffset = 0;
			if (isShowLeve2) {
				//���������˵�������ʾ�׶Σ�������
				if(isShowLeve3){
					AnimUtil.closeMenu(level3,startOffset);
					isShowLeve3 = !isShowLeve3;
					startOffset += 200;
				}
				// ��ִ�����صڶ����˵��Ĳ���
				AnimUtil.closeMenu(level2,startOffset);
			} else {
				// ��ִ����ʾ�ڶ����˵��Ĳ���
				AnimUtil.showMenu(level2,startOffset);
			}
			isShowLeve2 = !isShowLeve2;
			break;
		case R.id.iv_menu:
			if(AnimUtil.animCount != 0){
				//˵���ж�����ִ�У�ֱ�ӷ��أ�����ִ������Ĳ���
				return;
			}
			if (isShowLeve3) {
				// ��ִ�����ص������˵��Ĳ���
				AnimUtil.closeMenu(level3,0);
			} else {
				// ��ִ����ʾ�������˵��Ĳ���
				AnimUtil.showMenu(level3,0);
			}
			isShowLeve3 = !isShowLeve3;
			break;
		default:
			break;
		}
	}

}
