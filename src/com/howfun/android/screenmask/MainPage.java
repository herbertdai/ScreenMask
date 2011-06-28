/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainPage extends Activity {
   /** Called when the activity is first created. */
   
   public static final String TAG = "ScreenMask";

   Button mBtn4Test = null;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      findViews();
      setupListeners();
   }

   private void findViews() {
      mBtn4Test = (Button) findViewById(R.id.button4test);
   }

   private void setupListeners() {
      if(mBtn4Test != null){
         mBtn4Test.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
               
            }
         });
      }
   }
}