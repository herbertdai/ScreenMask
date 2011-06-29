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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;

import com.howfun.android.screenmask.mask.CoinMask;
import com.howfun.android.screenmask.mask.FruitMask;
import com.howfun.android.screenmask.mask.Mask;

public class MainPage extends Activity {
   /** Called when the activity is first created. */

   public static final String TAG = "MainPage";
   
   private static final int NO_MASK = -1;

   ScreenView mScreenView = null;

   private int mMaskId = R.id.fruit_mask;
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
   
   private void addMask(int x,int y){
	   Mask mask = null;
	   switch(mMaskId){
	   case R.id.fruit_mask:
		   mask = new FruitMask(mContext, x,y);
		   mScreenManager.add(mask);
		   break;
	   case R.id.coin_mask:
		   mask = new CoinMask(mContext, x,y);
		   mSound.play(R.raw.coin1);
		   mScreenManager.add(mask);
		   break;
	   case R.id.bug_mask:
		   mask = new BugMask(mContext, x,y);
		   mScreenManager.add(mask);
		   break;
	   case NO_MASK:
		   Utils.log(TAG, "no mask selected");
		   break;
	   }
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu){
	   MenuInflater inflater = getMenuInflater();
	   inflater.inflate(R.menu.options, menu);
	   return super.onCreateOptionsMenu(menu);
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item){
	   super.onOptionsItemSelected(item);
	   switch(item.getItemId()){
	   case R.id.fruit_mask:
		   mMaskId = R.id.fruit_mask;
		   break;
	   case R.id.coin_mask:
		   mMaskId = R.id.coin_mask;
		   break;
	   case R.id.bug_mask:
		   mMaskId = R.id.bug_mask;
		   break;
	   case R.id.exit:
	      finish();
		   break;
	   }
	   return true;
   }
   
   @Override
   protected void onResume(){
	   super.onResume();
   }
   
   @Override
   protected void onPause(){
	   super.onPause();
   }

      
}