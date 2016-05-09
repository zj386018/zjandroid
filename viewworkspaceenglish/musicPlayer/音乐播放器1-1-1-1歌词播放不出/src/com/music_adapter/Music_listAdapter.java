package com.music_adapter;

import java.util.List;

import com.music_class.*;
import com.music_player.R;
import com.music_viewholder.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Music_listAdapter extends BaseAdapter {
	
	private List<Music> mlists = null;

	private LayoutInflater mInflater;
	
	
	public Music_listAdapter(Context context, List<Music> mlists) {
		super();
		this.mlists = mlists;
		mInflater = LayoutInflater.from(context);//初始化
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	ViewHolder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
		convertView = mInflater.inflate(R.layout.main_listview_item, null);
		holder = new ViewHolder();
		holder.music_name = (TextView) convertView.findViewById(R.id.item_mname);
		holder.music_timelength = (TextView) convertView.findViewById(R.id.item_mtimelength);
		holder.singer_name = (TextView) convertView.findViewById(R.id.item_msinger);
		convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Music music = mlists.get(position);
		
		holder.music_name.setText(music.getMusic_name());
		
		holder.singer_name.setText(music.getSinger_name());
//		if(music.getSinger_name().equals("<unknown>")){
//			holder.singer_name.setText("未知音乐家");
//		}
		
		
		String s = music.getMusic_timelength();
		if(!"".equals(s)&&s!=null){//判断获得的时间是否为空
			holder.music_timelength.setText(s);
		}else{
			holder.music_timelength.setText("");
		}
		
		return convertView;
	}

}
