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

   private int mFruitIds[] = {R.drawable.orange_slice, R.drawable.apple, R.drawable.strawberry,
         R.drawable.watermelon_slice};
   
   public FruitMask(Context context, int x, int y) {
      super(context);
      init(x, y);
      show();
   }

   public FruitMask(Context context, AttributeSet as) {
      super(context, as);
   }

   @Override
   public void show() {

      int random = (int)(Math.random() * mFruitIds.length);
      setBackgroundResource(mFruitIds[random]);
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
      setClickable(false);
   }
}
