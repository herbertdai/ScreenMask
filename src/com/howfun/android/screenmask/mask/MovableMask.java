package com.howfun.android.screenmask.mask;

import android.content.Context;

public abstract class MovableMask extends Mask {


   private int mStep;
   protected int mDirection;
   private long mLife;

   public MovableMask(Context context) {
      super(context);
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
      this.mDirection = direction;
   }

   public int getDirection() {
      return this.mDirection;
   }
   
   public void setLife(long life){
      this.mLife = life;
   }
   
   public long getLife(){
      return this.mLife;
   }

}
