/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.ImageView;

public abstract class Mask extends ImageView {
   
   protected int mCenterX;
   protected int mCenterY;
   protected int mRectWidth;
   protected int mRectHeight;

   protected Rect mRect;

   public Mask(Context context) {
      super(context);
   }

   protected abstract void init(int x,int y);


   public int getCenterX() {
      return mCenterX;
   }

   public int getCenterY() {
      return mCenterY;
   }

   public Rect getRect() {
      return mRect;
   }

   public void setRect() {
      int left = mCenterX - mRectWidth / 2;
      int top = mCenterY - mRectHeight / 2;
      int right = mCenterX + mRectWidth / 2;
      int bottom = mCenterY + mRectHeight / 2;
      mRect = new Rect(left, top, right, bottom);
   }

   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas); 
      
   }

   
}
