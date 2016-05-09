package com.music_class;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Audio.Media;

public class MusicGet {
	
	/**
	 * 获取数据库中音乐文件的信息
	 * @param context
	 * @return 返回list（音乐集合）
	 */
	public List<Music> getMusicList(Context context) {
		List<Music> list = new ArrayList<Music>();
		
		String[] media_music_info = new String[] { 
				Media.TITLE, 
				Media.DURATION,
				Media.ARTIST,
				Media._ID, 
				Media.DISPLAY_NAME, 
				Media.DATA,
				Media.ALBUM_ID, 
				Media.SIZE};
		
		Cursor cursor =context.getContentResolver().query(
				Media.EXTERNAL_CONTENT_URI,
				media_music_info, 
				null, 
				null, 
				Media.DEFAULT_SORT_ORDER);
		cursor.moveToFirst();
		
		for (int i = 0; i < cursor.getCount(); i++) {
			Long size = cursor
					.getLong(cursor.getColumnIndexOrThrow(Media.SIZE));
			if (size > 2*1024 * 1024) {// 筛选出大于2M的文件
				Music music = new Music();
				int time = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(Media.DURATION)));
				time /= 1000;  
		        int minute = time / 60;  
		        int second = time % 60;  	
//		        minute %= 60;
		        String sTime= String.format("%02d:%02d", minute, second); 
				music.setMusic_name(cursor.getString(cursor
						.getColumnIndex(Media.TITLE)));
				music.setSinger_name(cursor.getString(cursor
						.getColumnIndex(Media.ARTIST)));
				if(music.getSinger_name().equals("<unknown>")){
					music.setSinger_name("未知艺术家");
				}
				music.setMusic_timelength(sTime);
				music.setMusic_url(cursor.getString(cursor
						.getColumnIndex(Media.DATA)));
				list.add(music);
			}
			cursor.moveToNext();
		}
		return list;
	}

	
}
