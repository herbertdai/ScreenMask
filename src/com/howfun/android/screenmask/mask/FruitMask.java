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

   private int mFruitIds[] = { R.drawable.orange_slice, R.drawable.apple,
         R.drawable.strawberry, R.drawable.watermelon_slice, R.drawable.kiwi,
         R.drawable.lemon, R.drawable.hami_melon, R.drawable.cherry };

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
      setClickable(false);
      int random = (int) (Math.random() * mFruitIds.length);
      setBackgroundResource(mFruitIds[random]);
   }
}
