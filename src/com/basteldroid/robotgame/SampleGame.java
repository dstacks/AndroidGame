package com.basteldroid.robotgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.util.Log;
import android.view.WindowManager;

import com.basteldroid.androidgame.R;
import com.basteldroid.framework.Screen;
import com.basteldroid.framework.implementation.AndroidGame;

public class SampleGame extends AndroidGame {
	
	public static String map, map2;
	boolean firstTimeCreate = true;
	public static int screenRotation;

	@Override
	public Screen getInitScreen() {
		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}
		
		InputStream is = getResources().openRawResource(R.raw.map1);
		InputStream is2 = getResources().openRawResource(R.raw.map2);
		map = convertStreamToString(is);
		map2 = convertStreamToString(is2);
		
		return new SplashLoadingScreen(this);
	}
	
	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
	
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			} 
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		
		return sb.toString();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		Assets.theme.play();
		WindowManager windowMgr = (WindowManager) this.getSystemService(Activity.WINDOW_SERVICE);
		screenRotation = windowMgr.getDefaultDisplay().getRotation();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Assets.theme.pause();
	}
}
