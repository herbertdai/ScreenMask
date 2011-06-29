package com.howfun.android.screenmask;

import android.util.Log;

public class Utils {

   public static final int NORTH = 0;
   public static final int SOUTH = 1;
   public static final int WEST = 2;
   public static final int EAST = 3;
   public static final int[] DIRECTIONS = { NORTH, SOUTH, WEST, EAST };

   public static final int[] MOVE_STEPS = {5,10,15};

   public static void log(String tag, String info) {
      if (ScreenMaskApplication.DEBUG) {
         Log.d("Screen Mask>>>>>>>>>" + tag, "-------->" + info);
      }
   }

}
