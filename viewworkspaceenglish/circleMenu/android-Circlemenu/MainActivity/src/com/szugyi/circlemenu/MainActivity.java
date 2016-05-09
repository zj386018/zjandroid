package com.szugyi.circlemenu;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.admob.zkapp.covers.Covers;
import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;
import com.szugyi.circlemenu.view.CircleLayout.OnItemClickListener;
import com.szugyi.circlemenu.view.CircleLayout.OnItemSelectedListener;

public class MainActivity extends Activity implements OnItemSelectedListener, OnItemClickListener{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Covers c = new Covers(this, "ab51d5a3cb4d45bbae9e8de9e43a432e"); 
		
		CircleLayout circleMenu = (CircleLayout)findViewById(R.id.main_circle_layout);
		circleMenu.setOnItemSelectedListener(this);
		circleMenu.setOnItemClickListener(this);

		//���TextView������Ϊ��ʾת�̰�ť�Ժ�ΪĬ�ϵ�ѡ���
		//Ĭ�ϵ���ײ�����һ����ѡ�У�Ȼ����ʾ����TextView�С�
		//selectedTextView = (TextView)findViewById(R.id.main_selected_textView);
		//selectedTextView.setText(((CircleImageView)circleMenu.getSelectedItem()).getName());
	}

	//Բ��ת�����ײ�������Ϊ����Ŀ��ѡ��
	@Override
	public void onItemSelected(View view, int position, long id, String name) {		
		//selectedTextView.setText(name);
	}

	//ѡ����ת���е�ĳһ����
	@Override
	public void onItemClick(View view, int position, long id, String name) {
		//Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();
	}
}
