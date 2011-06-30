/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask;

import com.howfun.android.screenmask.mask.BugMask;
import com.howfun.android.screenmask.mask.FruitMask;
import com.howfun.android.screenmask.mask.CoinMask;
import com.howfun.android.screenmask.mask.Mask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;

public class MainPage extends Activity {
	/** Called when the activity is first created. */

	public static final String TAG = "MainPage";

	private static final int NO_MASK = -1;
	private static final int STATIC_MASK = 1;
	private static final int MOVABLE_MASK = 2;


	private int mMaskId = NO_MASK;
	private int mMaskType = NO_MASK;
	
	DisplayMetrics dm;
	
	ScreenView mScreenView = null;

	private Context mContext = null;
	private Sound mSound = null;
	ScreenManger mScreenManager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		findViews();
		setupListeners();
		mScreenManager.setScreenView(mScreenView);
		Utils.showMessageDlg(this, R.string.help);
	}

	private void init() {
	   dm = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(dm);
      Utils.mWidthPixels = dm.widthPixels;
      Utils.mHeightPixels = dm.heightPixels;
      
		mContext = this;
		mSound = new Sound(mContext);
		mScreenManager = new ScreenManger(mContext);

	}

	private void findViews() {
		mScreenView = (ScreenView) findViewById(R.id.screen_view);
	}

	private void setupListeners() {
		if (mScreenView != null) {
			mScreenView.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						int x = (int) event.getX();
						int y = (int) event.getY();
						addMask(x, y);
					}
					return false;
				}
			});
		}
	}

	private boolean needClearScreen(int oldType, int newType) {
		if (oldType == NO_MASK) {
			return false;
		} else if (oldType != newType) {
			return true;
		} else {
			return false;
		}

	}

	private void addMask(int x, int y) {
		Mask mask = null;
		switch (mMaskId) {
		case R.id.fruit_mask:
			mask = new FruitMask(mContext, x, y);
			mScreenManager.addMask(mask);
			break;
		case R.id.coin_mask:
			mask = new CoinMask(mContext, x, y);
			mSound.play(R.raw.coin1);
			mScreenManager.addMask(mask);
			break;
		case R.id.bug_mask:
			mask = new BugMask(mContext, x, y);
			mScreenManager.addMask(mask);
			break;
		case NO_MASK:
			Utils.log(TAG, "no mask selected");
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		int oldType = mMaskType;
		switch (item.getItemId()) {
		case R.id.fruit_mask:
			mMaskType = STATIC_MASK;
			mMaskId = R.id.fruit_mask;
			break;
		case R.id.coin_mask:
			mMaskType = STATIC_MASK;
			mMaskId = R.id.coin_mask;
			break;
		case R.id.bug_mask:
			mMaskType = MOVABLE_MASK;
			mMaskId = R.id.bug_mask;
			break;
		}
		if (needClearScreen(oldType, mMaskType)) {
			if (mMaskType == STATIC_MASK){
				mScreenManager.removeMovableMasks();
			}
				
			if (mMaskType == MOVABLE_MASK){
				mScreenManager.removeStaticMasks();
			}
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mScreenManager != null) {
			//TODO stop thread
		}
	}

}