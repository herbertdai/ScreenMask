/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import com.howfun.android.screenmask.R;
import com.howfun.android.screenmask.Utils;

public class BugMask extends Mask {
   
   public static final String TAG = "BugMask";

   private static final float MAX_ANGLE = 180.0f;
   private static final float MIN_ANGLE = -180.0f;
   private static final float[] ANGLES = { MAX_ANGLE, MIN_ANGLE };

   private AnimationDrawable mAnimation;
   
   public BugMask(Context context, int x, int y) {
      super(context);
      init(x, y);
      show();
   }

   public BugMask(Context context, AttributeSet as) {
      super(context, as);
   }

   @Override
   public void show() {

      this.setBackgroundResource(R.anim.bug_anim);
      mAnimation = (AnimationDrawable) this.getBackground();
   }
   
   @Override 
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      if (mAnimation != null) {
         mAnimation.start();
      }
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
