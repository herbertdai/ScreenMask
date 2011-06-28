package com.howfun.android.screenmask;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class ScreenView extends ViewGroup {
   
   public static final String TAG = "ScreenView";

   public ScreenView(Context context) {
      super(context);
   }

   public ScreenView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public ScreenView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
   }

   @Override
   protected void onLayout(boolean changed, int l, int t, int r, int b) {
      Utils.log(TAG, "screen view on layout");
      // TODO Auto-generated method stub

   }

}
