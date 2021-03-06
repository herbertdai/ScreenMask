/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;

import com.howfun.android.screenmask.R;
import com.howfun.android.screenmask.Utils;

public class BugMask extends MovableMask {

   public static final String TAG = "BugMask";

   public static final int RECT_WIDTH = 100;
   public static final int RECT_HEIGHT = 100;

   private static final Random RNG = new Random();

   private AnimationDrawable mAnimation;

   public BugMask(Context context, int x, int y) {
      super(context);
      init(x, y);
   }

   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      if (mAnimation != null) {
         mAnimation.start();
      }
   }

   protected void init(int x, int y) {
      mCenterX = x;
      mCenterY = y;
      mRectWidth = 100;
      mRectHeight = 100;
      setRect();

      int direction = Utils.DIRECTIONS[RNG.nextInt(Utils.DIRECTIONS.length)];
      setDirection(direction);
      Utils.log(TAG, "mask direction:" + direction);

      int step = Utils.MOVE_STEPS[RNG.nextInt(Utils.MOVE_STEPS.length)];
      setStep(step);
      Utils.log(TAG, "mask step:" + step);

      long life = Utils.LIFES[RNG.nextInt(Utils.LIFES.length)];
      setLife(life);
      Utils.log(TAG, "mask life:" + life);

      setClickable(false);
      if (direction == Utils.NORTH) {
         this.setBackgroundResource(R.anim.north_bug_anim);
      } else if (direction == Utils.SOUTH) {
         this.setBackgroundResource(R.anim.south_bug_anim);
      }

      mAnimation = (AnimationDrawable) this.getBackground();

   }

}
