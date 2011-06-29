/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import android.content.Context;
import com.howfun.android.screenmask.R;
public class CoinMask extends StaticMask {

   public static final String TAG = "CoinMask";

   public static final int RECT_WIDTH = 100;
   public static final int RECT_HEIGHT = 100;

   private int[] mCoins = { R.drawable.one_dollar,
         R.drawable.one_dollar_reverse };

   public CoinMask(Context context, int x, int y) {
      super(context);
      init(x, y);
   }


   protected void init(int x, int y) {
      mCenterX = x;
      mCenterY = y;
      mRectWidth = RECT_WIDTH;
      mRectHeight = RECT_HEIGHT;
      setRect();
      int whichIcon = (int) (Math.random() * mCoins.length);
      int resId = mCoins[whichIcon];
      setImageResource(resId);
      setClickable(false);
   }

}
