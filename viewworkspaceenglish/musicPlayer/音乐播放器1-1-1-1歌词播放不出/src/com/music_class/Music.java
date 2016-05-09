package com.music_class;

public class Music {
	
	private String music_name;//音乐名
	private String singer_name;//歌手名
	private String music_timelength;//音乐时长
	private String music_url;
	
	
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	
	
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	
	
	public String getMusic_timelength() {
		return music_timelength;
	}
	public void setMusic_timelength(String music_timelength) {
		this.music_timelength = music_timelength;
	}
	
	
	
	public String getMusic_url() {
		return music_url;
	}
	public void setMusic_url(String music_url) {
		this.music_url = music_url;
	}
	
	public Music( ) {}
	
	public Music( String music_name, String singer_name,
			String music_timelength) {
		super();

		this.music_name = music_name;
		this.singer_name = singer_name;
		this.music_timelength = music_timelength;
	}
	@Override
	public String toString() {
		return "Music [music_name=" + music_name + ", singer_name="
				+ singer_name + ", music_timelength=" + music_timelength
				+ ", music_url=" + music_url + "]";
	}
	
	
	
	
	

}
