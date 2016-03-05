package com.shandian.sms;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	private String APPKEY="fb6dd1e29cf0";
	private String appsce="0e3e12672738a302859ed07ac6e71166";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化sdk
		SMSSDK.initSDK(this, APPKEY, appsce);
		//配置信息，到清单文件配置
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//注册手机号
				RegisterPage page=new RegisterPage();
				//注册回调事件
				page.setRegisterCallback(new EventHandler(){
					//事件完成后调用
					@Override
					public void afterEvent(int event, int result, Object data) {
						// TODO Auto-generated method stub
						super.afterEvent(event, result, data);
						//判断这个结果已经完成
						if(result==SMSSDK.RESULT_COMPLETE){
							//获取数据处理
							HashMap<String, Object>maps=(HashMap<String, Object>) data;
							//国家信息
						String country=(String) maps.get("country");
							//手机号
						String phone=(String) maps.get("phone");
						//提交信息
						submitUserinfo(country, phone);
						}
						
					}
				});
				//显示注册界面
				page.show(MainActivity.this);
			}
		});
	}
	/**
	 * 
	 * @param country国家
	 * @param phone手机号
	 */
	public void submitUserinfo(String country,String phone){
		Random r=new Random();
		String 	uid=Math.abs(r.nextInt())+"";
		String nickname="shandian";
		SMSSDK.submitUserInfo(uid, nickname, null, country, phone);
	}
}
