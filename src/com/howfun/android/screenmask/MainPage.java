/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask;

import com.howfun.android.screenmask.mask.BugMask;
import com.howfun.android.screenmask.mask.FruitMask;
import com.howfun.android.screenmask.mask.CoinMask;
import com.howfun.android.screenmask.mask.HeartMask;
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
import android.widget.Toast;

public class MainPage extends Activity {
   /** Called when the activity is first created. */

   public static final String TAG = "MainPage";

   private static final int NO_MASK = -1;

   private int mMaskId = R.id.fruit_mask;
   private int mWhichMask = (int) (Math.random() * FruitMask.mFruitIds.length);

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
      mContext = this;
      mSound = new Sound(mContext);
      mScreenManager = new ScreenManger();

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

   private void addMask(int x, int y) {
      Mask mask = null;
      switch (mMaskId) {
      case R.id.fruit_mask:
         mask = new FruitMask(mContext, x, y, mWhichMask);
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
      case R.id.heart_mask:
         mask = new HeartMask(mContext, x, y);
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
      switch (item.getItemId()) {
      case R.id.fruit_mask:
         mScreenManager.clear();
         mMaskId = R.id.fruit_mask;
         mWhichMask = (int) (Math.random() * FruitMask.mFruitIds.length);
         break;
      case R.id.coin_mask:
         mScreenManager.clear();
         mMaskId = R.id.coin_mask;
         break;
      case R.id.bug_mask:
         mScreenManager.clear();
         mMaskId = R.id.bug_mask;
         break;
      case R.id.heart_mask:
         mScreenManager.clear();
         mMaskId = R.id.heart_mask;
         break;
      case R.id.clear:
         mScreenManager.removeMovableMasks();
         mScreenManager.removeStaticMasks();
         break;
      case R.id.exit:
         showExitPrompt();
         this.finish();
         break;
      }
      return true;
   }

   private void showExitPrompt() {
      Toast.makeText(this, R.string.exit_prompt, Toast.LENGTH_LONG).show();
   }

   private void showResumePrompt() {
      Toast.makeText(this, R.string.resume_prompt, Toast.LENGTH_LONG).show();
   }

   @Override
   protected void onResume() {
      super.onResume();

      showResumePrompt();
   }

   @Override
   protected void onPause() {
      super.onPause();

      showExitPrompt();

      if (mScreenManager != null) {
         // TODO stop thread
      }
   }

}