/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask.mask;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Mask extends View {

   protected int mCenterX;
   protected int mCenterY;
   protected int mRectWidth;
   protected int mRectHeight;

   protected Rect mRect;

   protected boolean mIsActive;

   public Mask(Context context) {
      super(context);
   }

   public Mask(Context context, AttributeSet as) {
      super(context, as);
   }

   public void show() {

   }

   public void dismiss() {

   }

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
   
}
