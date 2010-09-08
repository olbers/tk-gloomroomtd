package com.telekawaru.gloomroomtd.classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DockReceiver extends BroadcastReceiver {

	private static final int EXTRA_DOCK_STATE_UNDOCKED = 0;
	private static final int EXTRA_DOCK_STATE_CAR = 1;
	private static final int EXTRA_DOCK_STATE_DESK = 2;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getExtras().getInt("android.intent.extra.DOCK_STATE", 1) == EXTRA_DOCK_STATE_CAR) {
			
		} else if (intent.getExtras().getInt("android.intent.extra.DOCK_STATE", 1) == EXTRA_DOCK_STATE_DESK) {
			
		} else if (intent.getExtras().getInt("android.intent.extra.DOCK_STATE", 1) == EXTRA_DOCK_STATE_UNDOCKED) {
			
		}
	}

}
