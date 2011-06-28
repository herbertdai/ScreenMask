/*
 * Mask, base class to generate sub-mask classes.
 * 
 * HOWFUN Studio
 */
package com.howfun.android.screenmask;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;



public class Mask extends View{

   protected Rect mRect;
   
   protected boolean mIsActive;
   
   public Mask(Context context) {
      super(context);
   }

   public Mask(Context context, AttributeSet as) {
      super(context, as);
   }
   
   public void show(){
      
   }
   
   public void dismiss() {
      
   }
}
