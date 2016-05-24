package com.baiyi.select;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	//������ؼ�
	private EditText editText;
	private ImageView iv_select;
	
	//�������ڿؼ�
	private int popuWindowHeight = 300;
	//��̬�����ؼ�����û����xml�ļ����ã�
	private PopupWindow popupWindow;
	
	//listview�е����ݡ�
	private ArrayList<String> lists = new ArrayList<String>();
	//listview�ؼ���
	private ListView listView;
	//listview�ؼ���������
	private MyAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initListener();
		initDate();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.editText);
		iv_select = (ImageView) findViewById(R.id.iv_select);
	}

	/**
	 * ��ʼ�������¼�
	 */
	private void initListener() {
		iv_select.setOnClickListener(this);
	}

	/**
	 * ��ʼ������
	 */
	private void initDate() {
		for (int i = 10; i < 25; i++) {
			lists.add("2013" + i + "160" + i);
		}
		initListView();
	}

	/**
	 * ��ʼ������ѡ������
	 */
	private void initListView() {
		listView = new ListView(MainActivity.this);
		listView.setBackgroundResource(R.drawable.select2);
		// listView.setVerticalScrollBarEnabled(false);//����listView����ֱ���������ɼ�

		adapter = new MyAdapter();

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				editText.setText(lists.get(arg2));
				popupWindow.dismiss();
			}
		});

	}

	private void showNumberList() {
		if (popupWindow == null) {
			popupWindow = new PopupWindow(listView, editText.getWidth(),
					popuWindowHeight);
		}
		//��popupWindow�����õ�����㣬����ͬʱ���������������ԡ�
			//�����ڲ�view��ȡ���㣬��������Ϊtrue
		popupWindow.setFocusable(true);
			//�������ñ���ͼƬ
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
			//�������õ���ⲿʱ��popupWindow��ʧΪtrue
		popupWindow.setOutsideTouchable(true);
		
		popupWindow.showAsDropDown(editText, 0, 0);
	}

	//����¼�������Ҳ��������ͼ��ĵ���¼���
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_select:
			showNumberList();
			break;
		}
	}

	/**
	 * listview������
	 *
	 */
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return lists.size();
		}

		@Override
		public Object getItem(int arg0) {
			return lists.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		/**
		 * Ϊlistview������ݺͰ󶨿ؼ�
		 */
		@Override
		public View getView(final int arg0, View arg1, ViewGroup arg2) {
			final View view = View.inflate(MainActivity.this,
					R.layout.adapter_lists, null);
			TextView textView = (TextView) view.findViewById(R.id.tv_number);
			ImageView  imageView = (ImageView ) view.findViewById(R.id.iv_delete);

			textView.setText(lists.get(arg0));
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// Log.e("MainActivity", "position="+arg0);
					lists.remove(arg0);
					notifyDataSetChanged();// ˢ��listView������
					//ˢ��popuWindow�Ľ��棨�ж�listview�ĸ߶��Ƿ����popuWindow�ĸ߶ȣ�
					//������������popuWindow�ĸ߶Ȳ��䡣
					//��С��������popuWindow�ĸ߶ȱ�Ϊlistview�ĸ߶ȡ�
					int listviewHeight = view.getHeight() * lists.size();
					popupWindow.update(
							editText.getWidth(),
							listviewHeight > popuWindowHeight ? popuWindowHeight
									: listviewHeight);

					// ��û�����ݵ�ʱ��popuWindowӦ�øı�Ϊ���ɼ���
					if (lists.size() == 0) {
						popupWindow.dismiss();
						iv_select.setVisibility(View.GONE);//������ͼ���Ϊ���ɼ�
					}
				}
			});

			return view;
		}
	}

}