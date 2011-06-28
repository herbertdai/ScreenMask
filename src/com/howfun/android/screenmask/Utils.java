package com.howfun.android.screenmask;

import android.util.Log;

public class Utils {

   public static void log(String tag, String info) {
      if (ScreenMaskApplication.DEBUG) {
         Log.d("Screen Mask>>>>>>>>>" + tag, "-------->" + info);
      }
   }

}
