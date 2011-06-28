/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import com.howfun.android.screenmask.R;

import android.content.Context;
import android.util.AttributeSet;

public class FruitMask extends Mask {

   public FruitMask(Context context, int x, int y) {
      super(context);
      init(x, y);
   }

   public FruitMask(Context context, AttributeSet as) {
      super(context, as);
   }

   @Override
   public void show() {

   }

   @Override
   public void dismiss() {

   }

   private void init(int x, int y) {
      mCenterX = x;
      mCenterY = y;
      mRectWidth = 100;
      mRectHeight = 100;
      setRect();
      setBackgroundResource(R.drawable.orange_slice);
      setClickable(false);
   }
}
