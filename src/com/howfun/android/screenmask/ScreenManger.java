package com.howfun.android.screenmask;

import java.util.ArrayList;
import java.util.Iterator;

import com.howfun.android.screenmask.mask.Mask;
import com.howfun.android.screenmask.mask.MovableMask;
import com.howfun.android.screenmask.mask.StaticMask;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;

public class ScreenManger {

	public static final String TAG = "ScreenManager";

	public static final long DELAY_MILLIS = 200L;
	
	public static final int MAX_MOVABLE_MASKS = 10;

	public static final int MSG_UPDATE_SCREEN = 1;

	private Context mContext;

	private ScreenView mScreenView;

	private ArrayList<StaticMask> mStaticMasks;
	private ArrayList<MovableMask> mMovableMasks;
	
	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_UPDATE_SCREEN:
				update();
				break;
			}
		}
	};

	private Thread mThread = new Thread() {
		public void run() {
			while (true) {
				try {
					sleep(DELAY_MILLIS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mHandler.sendEmptyMessage(MSG_UPDATE_SCREEN);
			}
		}
	};

	public ScreenManger(Context context) {
		mContext = context;
		mStaticMasks = new ArrayList<StaticMask>();
		mMovableMasks = new ArrayList<MovableMask>();
		mThread.start();
	}

	public void setScreenView(ScreenView screenView) {
		this.mScreenView = screenView;
	}

	public void addMask(Mask mask) {
		if (mask instanceof StaticMask) {
			StaticMask sMask = (StaticMask) mask;
			addStaticMask(sMask);
		} else if (mask instanceof MovableMask) {
			MovableMask mMask = (MovableMask) mask;
			addMovableMask(mMask);
		} else {
			Utils.log(TAG, "no such mask..");
		}
	}

	/*
	 * Add one static mask to screen
	 * 
	 * @return true success false if fail
	 */
	public boolean addStaticMask(StaticMask mask) {

		if (mask == null) {
			Utils.log(TAG, "mask is null,return");
			return false;
		}
		// TODO add a mask
		Utils.log(TAG, "add a static mask");
		mStaticMasks.add(mask);
		mScreenView.addView(mask);
		Rect rect = mask.getRect();
		mask.layout(rect.left, rect.top, rect.right, rect.bottom);
		return true;
	}

	public boolean addMovableMask(MovableMask mask) {
		if (mask == null) {
			Utils.log(TAG, "mask is null,return");
			return false;
		}
		// TODO add a mask
		Utils.log(TAG, "add a movable mask");
		mMovableMasks.add(mask);
		mScreenView.addView(mask);
		Rect rect = mask.getRect();
		mask.layout(rect.left, rect.top, rect.right, rect.bottom);
		
		return true;
	}

	public void remove(int maskId) {

	}

	public void removeStaticMasks() {
		mScreenView.removeAllViews();
		mStaticMasks.clear();
	}

	public void removeMovableMasks() {
		mScreenView.removeAllViews();
		mMovableMasks.clear();
	}

	public void setNextPosition() {
		if (mMovableMasks.size() == 0)
			return;

		Iterator<MovableMask> it = mMovableMasks.iterator();
		while (it.hasNext()) {
			MovableMask mask = it.next();
			int x = mask.getCenterX();
			int y = mask.getCenterY();
			int step = mask.getStep();
			int direction = mask.getDirection();
			switch (direction) {
			case Utils.NORTH:
				Utils.log(TAG, "moving north");
				mask.setCenter(x, y - step);
				break;
			case Utils.SOUTH:
				Utils.log(TAG, "moving south");
				mask.setCenter(x, y + step);
				break;
			case Utils.WEST:
				Utils.log(TAG, "moving west");
				mask.setCenter(x - step, y);
				break;
			case Utils.EAST:
				Utils.log(TAG, "moving east");
				mask.setCenter(x + step, y);
				break;
			default:
				break;
			}
			// Utils.log(TAG, "get next position,x:" + x + ",y" + y);
			
		}
	}

	public void renderScreen() {
		if (mMovableMasks.size() == 0)
			return;

		mScreenView.removeAllViews();
		Iterator<MovableMask> it = mMovableMasks.iterator();
		while (it.hasNext()) {
			MovableMask mask = it.next();
			Rect rect = mask.getRect();
			mScreenView.addView(mask);
			mask.layout(rect.left, rect.top, rect.right, rect.bottom);
		}
	}

	public void update() {
		setNextPosition();
		renderScreen();
	}
	
}
