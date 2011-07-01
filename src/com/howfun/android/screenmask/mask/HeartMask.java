/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import com.howfun.android.screenmask.R;

import android.content.Context;

public class HeartMask extends StaticMask {

   public static final int RECT_WIDTH = 72;
   public static final int RECT_HEIGHT = 72;

   private int mHeartIds[] = { R.drawable.heart };

   public HeartMask(Context context, int x, int y) {
      super(context);
      init(x, y);
   }

   protected void init(int x, int y) {
      mCenterX = x;
      mCenterY = y;
      mRectWidth = RECT_WIDTH;
      mRectHeight = RECT_HEIGHT;
      setRect();
      setClickable(false);
      int random = (int) (Math.random() * mHeartIds.length);
      setBackgroundResource(mHeartIds[random]);
   }
}
