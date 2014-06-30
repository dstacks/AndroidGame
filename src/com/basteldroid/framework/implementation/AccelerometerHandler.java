package com.basteldroid.framework.implementation;

import com.basteldroid.robotgame.SampleGame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHandler implements SensorEventListener {

	private static float screenX;
	static float screenY;
	static float screenZ;
	
	public AccelerometerHandler(Context context) {
		SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		
		if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
		}
	}
	
	static final int ACCELEROMETER_AXIS_SWAP[][] = {
		{1, -1, 0, 1},
		{-1, -1, 1, 0},
		{-1, 1, 0, 1},
		{1, 1, 1, 0} 
	};
	
	// Hier müsst ihr Spiel Klasse einsetzen
	// Ersetzt SampleGame mit der Spiel Klasse die ihr nutzt und importiert diese.
	@Override
	public void onSensorChanged(SensorEvent event) {
		final int[] as = ACCELEROMETER_AXIS_SWAP[SampleGame.screenRotation];
		screenX = (float) as[0] * event.values[as[2]];
		screenY = (float) as[1] * event.values[as[3]];
		screenZ = event.values[2];
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	public static float getAccelX() {
		return screenX;
	}

	public static float getAccelY() {
		return screenY;
	}

	public static float getAccelZ() {
		return screenZ;
	}

	public static void setScreenX(float screenX) {
		AccelerometerHandler.screenX = screenX;
	}

	
}
