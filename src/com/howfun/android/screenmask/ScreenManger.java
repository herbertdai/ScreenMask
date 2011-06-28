package com.howfun.android.screenmask;

import java.util.ArrayList;

import android.content.Context;

public class ScreenManger {
   
   private Context mContext;
   
   private ArrayList<Mask> mMasks;
   
   public ScreenManger(Context context) {
      mContext = context;
      
      mMasks = new ArrayList<Mask> ();
      
   }
   
   /*
    * Add one mask to screen
    * @return true   success
    *         false  if fail
    */
   public boolean Add(Mask mask) {
      
      return false;
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
