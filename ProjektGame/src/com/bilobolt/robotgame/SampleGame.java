package com.bilobolt.robotgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.provider.Telephony.Sms.Conversations;
import android.util.Log;

import com.jamescho.androidgame.R;
import com.kilobolt.framework.Screen;
import com.kilobolt.framework.implementation.AndroidGame;

public class SampleGame extends AndroidGame {

	public static String map;
	boolean firstTimeCreate = true;
	
	
	@Override
	public Screen getInitScreen() {
		if(firstTimeCreate){
			Assets.load(this);
			firstTimeCreate = false;
		}
		
		InputStream is = getResources().openRawResource(R.raw.map1);
		map = convertStreamToString(is);
		
		return new SplashLoadingScreen(this);
	}


	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
	
	private static String convertStreamToString(InputStream is){
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		String line= null;
		try{
			while((line = reader.readLine()) != null){
				sb.append((line + "\n"));
			}
		}catch(IOException e){
			Log.w("LOG", e.getMessage());
			
		}finally{
			try{
				is.close();
			}catch(IOException e){
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Assets.theme.play();
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Assets.theme.pause();	
	
	}	
		
}
