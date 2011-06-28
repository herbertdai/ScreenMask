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

public class FruitMask extends Mask{

   public FruitMask(Context context) {
      super(context);
   }

   public FruitMask(Context context, AttributeSet as) {
      super(context, as);
   }
   
   @Override
   public void show(){
      
   }
   
   @Override
   public void dismiss() {
      
   }
}
