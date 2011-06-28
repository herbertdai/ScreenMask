/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask;

import com.howfun.android.screenmask.mask.FruitMask;
import com.howfun.android.screenmask.mask.CoinMask;
import com.howfun.android.screenmask.mask.Mask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainPage extends Activity {
   /** Called when the activity is first created. */

   public static final String TAG = "ScreenMask";

   Button mBtn4Test = null;
   ScreenView mScreenView = null;

   Context mContext = null;
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
   }

   private void init() {
      mContext = this;
      mScreenManager = new ScreenManger(mContext);
   }

   private void findViews() {
      mBtn4Test = (Button) findViewById(R.id.button4test);
      mScreenView = (ScreenView) findViewById(R.id.screen_view);
   }

   private void setupListeners() {
      if (mBtn4Test != null) {
         mBtn4Test.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
               Utils.log(TAG, "button 4 test clicked");
               // TODO gen a test mask
            }
         });
      }

      if (mScreenView != null) {
         mScreenView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                  int x = (int) event.getX();
                  int y = (int) event.getY();
                  Mask mask = new CoinMask(mContext, x, y);
                  mScreenManager.add(mask);
               }
               return false;
            }
         });
      }
   }

      
}