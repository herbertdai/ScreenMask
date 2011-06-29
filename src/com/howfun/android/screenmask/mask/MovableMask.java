package com.howfun.android.screenmask.mask;

import android.content.Context;

public abstract class MovableMask extends Mask {

   public static int mNextId = 0;

   private int mStep;
   protected int mDirection;
   private int mId;

   public MovableMask(Context context) {
      super(context);
      mId = mNextId++;
   }

   public int getId() {
      return mId;
   }

   public void setCenter(int x, int y) {
      this.mCenterX = x;
      this.mCenterY = y;
      setRect();
   }

   public void setStep(int step) {
      mStep = step;
   }

   public int getStep() {
      return mStep;
   }

   public void setDirection(int direction) {
      mDirection = direction;
   }

   public int getDirection() {
      return mDirection;
   }

}
