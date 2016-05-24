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

	//主界面控件
	private EditText editText;
	private ImageView iv_select;
	
	//下拉窗口控件
	private int popuWindowHeight = 300;
	//动态创建控件（并没有用xml文件配置）
	private PopupWindow popupWindow;
	
	//listview中的数据。
	private ArrayList<String> lists = new ArrayList<String>();
	//listview控件。
	private ListView listView;
	//listview控件适配器。
	private MyAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initListener();
		initDate();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.editText);
		iv_select = (ImageView) findViewById(R.id.iv_select);
	}

	/**
	 * 初始化监听事件
	 */
	private void initListener() {
		iv_select.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initDate() {
		for (int i = 10; i < 25; i++) {
			lists.add("2013" + i + "160" + i);
		}
		initListView();
	}

	/**
	 * 初始化下拉选项数据
	 */
	private void initListView() {
		listView = new ListView(MainActivity.this);
		listView.setBackgroundResource(R.drawable.select2);
		// listView.setVerticalScrollBarEnabled(false);//设置listView的竖直滑动条不可见

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
		//让popupWindow里面获得点击焦点，必须同时设置下面三个属性。
			//设置内部view获取焦点，必须设置为true
		popupWindow.setFocusable(true);
			//必须设置背景图片
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
			//必须设置点击外部时，popupWindow消失为true
		popupWindow.setOutsideTouchable(true);
		
		popupWindow.showAsDropDown(editText, 0, 0);
	}

	//点击事件监听（也就是下拉图标的点击事件）
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.iv_select:
			showNumberList();
			break;
		}
	}

	/**
	 * listview适配器
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
		 * 为listview填充数据和绑定控件
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
					notifyDataSetChanged();// 刷新listView的数据
					//刷新popuWindow的界面（判断listview的高度是否大于popuWindow的高度）
					//若大于它，则popuWindow的高度不变。
					//若小于它，则popuWindow的高度变为listview的高度。
					int listviewHeight = view.getHeight() * lists.size();
					popupWindow.update(
							editText.getWidth(),
							listviewHeight > popuWindowHeight ? popuWindowHeight
									: listviewHeight);

					// 若没有数据的时候，popuWindow应该改变为不可见。
					if (lists.size() == 0) {
						popupWindow.dismiss();
						iv_select.setVisibility(View.GONE);//将下拉图标变为不可见
					}
				}
			});

			return view;
		}
	}

}