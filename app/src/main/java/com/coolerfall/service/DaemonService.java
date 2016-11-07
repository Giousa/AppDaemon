package com.coolerfall.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.coolerfall.daemon.Daemon;

public class DaemonService extends Service {

	private final String TAG = DaemonService.class.getSimpleName();
	private int recLen = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG,"DaemonService---->onCreate");
		Daemon.run(this, DaemonService.class, Daemon.INTERVAL_ONE_MINUTE * 2);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(TAG,"DaemonService---->onStart");
		handler.postDelayed(runnable, 1000);
	}

	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			recLen++;
			handler.postDelayed(this, 1000);
			Log.d(TAG,"DaemonService---->recLen:"+recLen);
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG,"DaemonService---->onBind");
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG,"DaemonService---->onStartCommand");
		/* do something here */
		return super.onStartCommand(intent, flags, startId);
	}
}
