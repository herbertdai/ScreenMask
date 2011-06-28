package com.howfun.android.screenmask;

import java.util.ArrayList;

import com.howfun.android.screenmask.mask.Mask;

import android.content.Context;
import android.graphics.Rect;

public class ScreenManger {

   public static final String TAG = "ScreenManager";

   private Context mContext;

   private ScreenView mScreenView;

   private ArrayList<Mask> mMasks;

   public ScreenManger(Context context) {
      mContext = context;
      mMasks = new ArrayList<Mask>();

   }

   public void setScreenView(ScreenView screenView) {
      this.mScreenView = screenView;
   }

   /*
    * Add one mask to screen
    * 
    * @return true success false if fail
    */
   public boolean add(Mask mask) {

      if (mask == null) {
         Utils.log(TAG, "mask is null,return");
         return false;
      }
      // TODO add a mask
      Utils.log(TAG, "add a mask");
      mMasks.add(mask);
      mScreenView.addView(mask);
      Rect rect = mask.getRect();
      mask.layout(rect.left, rect.top, rect.right, rect.bottom);
      return true;
   }

   public void remove(Mask mask) {
      
   }

   public void remove(int maskId) {

   }

   public void removeAll() {

   }

   /*
    * Refresh all masks on screen
    */
   public void renderScreen() {

   }

}
