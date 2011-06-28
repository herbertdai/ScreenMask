/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import com.howfun.android.screenmask.R;
import com.howfun.android.screenmask.Utils;

public class CoinMask extends Mask {
   
   public static final String TAG = "CoinMask";

   private int[] mCoins = { R.drawable.one_dollar,
         R.drawable.one_dollar_reverse };

   private static final float MAX_ANGLE = 180.0f;
   private static final float MIN_ANGLE = -180.0f;
   private static final float[] ANGLES = { MAX_ANGLE, MIN_ANGLE };

   public CoinMask(Context context, int x, int y) {
      super(context);
      init(x, y);
   }

   public CoinMask(Context context, AttributeSet as) {
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
      int whichIcon = (int) (Math.random() * mCoins.length);
      int resId = mCoins[whichIcon];
      setBackgroundDrawable(rotateBitmap(resId));
      setClickable(false);
   }

   private BitmapDrawable rotateBitmap(int resId) {
      Bitmap oldBitmap = BitmapFactory.decodeResource(getResources(), resId);
      final int width = oldBitmap.getWidth();
      final int height = oldBitmap.getHeight();

      int which = (int) (Math.random() * ANGLES.length);
      float angle = ANGLES[which];
      float degree = (float) (Math.random() * angle);
      Utils.log(TAG, "degree:"+degree);
      Matrix matrix = new Matrix();
      matrix.setRotate(degree);
      
      Bitmap newBitmap = Bitmap.createBitmap(oldBitmap, 0, 0, width, height,
            matrix, true);
      BitmapDrawable bitmapDrawable = new BitmapDrawable(newBitmap);
      return bitmapDrawable;
   }
}
