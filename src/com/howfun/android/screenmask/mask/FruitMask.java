/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import com.howfun.android.screenmask.R;

import android.content.Context;

public class FruitMask extends StaticMask {
   
   public static final int RECT_WIDTH = 100;
   public static final int RECT_HEIGHT = 100;
   
   

   public FruitMask(Context context, int x, int y) {
      super(context);
      init(x, y);
   }

   protected void init(int x, int y) {
      mCenterX = x;
      mCenterY = y;
      mRectWidth = RECT_WIDTH;
      mRectHeight = RECT_HEIGHT;
      setRect();
      setBackgroundResource(R.drawable.orange_slice);
      setClickable(false);
   }
}
