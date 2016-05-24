package com.example.filedownloadex;

public class Music {

	private String singer;
	private String songname;
	private String images;
	private String songfile;

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getSongfile() {
		return songfile;
	}

	public void setSongfile(String songfile) {
		this.songfile = songfile;
	}

	@Override
	public String toString() {
		return "Music [singer=" + singer + ", songname=" + songname
				+ ", images=" + images + ", songfile=" + songfile + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
		result = prime * result
				+ ((songfile == null) ? 0 : songfile.hashCode());
		result = prime * result
				+ ((songname == null) ? 0 : songname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		if (songfile == null) {
			if (other.songfile != null)
				return false;
		} else if (!songfile.equals(other.songfile))
			return false;
		if (songname == null) {
			if (other.songname != null)
				return false;
		} else if (!songname.equals(other.songname))
			return false;
		return true;
	}

	public Music() {
	}

	public Music(String singer, String songname, String images, String songfile) {
		super();
		this.singer = singer;
		this.songname = songname;
		this.images = images;
		this.songfile = songfile;
	}

}
