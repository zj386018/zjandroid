package com.example.filedownloadex;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button bt_getMp3Info;
	private TextView tv_mp3Info;
	private Handler handler;
	private Message m1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 100) {
					Music music = JsonHelper.getJson(msg.obj.toString());
					tv_mp3Info.setText("歌手：" + music.getSinger() + "\n" + "歌名："
							+ music.getSongname() + "\n" + "图片名："
							+ music.getImages() + "\n" + "歌曲文件："+music.getSongfile());
				}
			}
		};
	}

	private void initView() {
		bt_getMp3Info = (Button) findViewById(R.id.bt_getMp3Info);
		bt_getMp3Info.setOnClickListener(this);
		tv_mp3Info = (TextView) findViewById(R.id.tv_mp3Info);
	}

	public static String getMp3Info() {
		SoapObject soapObject = new SoapObject("http://mycoolme.example.com",
				"getMp3Info");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(soapObject);
		envelope.bodyOut = soapObject;
		envelope.dotNet = false;
		envelope.encodingStyle = SoapSerializationEnvelope.ENC;
		HttpTransportSE httpTransportSE = new HttpTransportSE(
				"http://10.64.210.28:8080/axis2/services/MyCoolMeWebService?wsdl");
		String re = "";
		try {
			httpTransportSE.call(null, envelope);
			re = envelope.getResponse().toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return re;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bt_getMp3Info:
			bt_getMp3Info();
			break;

		default:
			break;
		}
	}

	private void bt_getMp3Info() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				String re = getMp3Info();
				m1 = new Message();
				m1.what = 100;
				m1.obj = re;
				handler.sendMessage(m1);
			}
		}).start();
	}

}
