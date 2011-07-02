package com.howfun.android.screenmask;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

   private static final String TAG = "AudioPlay";

   private Context mContext = null;
   private MediaPlayer mPlayer = null;

   public Sound(Context ctx) {
      mContext = ctx;
   }

   public void play(int res) {
      Utils.log(TAG, "in play,audio res id:" + res);
      mPlayer = MediaPlayer.create(mContext, res);
      mPlayer.setLooping(false);
      mPlayer.start();
      mPlayer.setOnCompletionListener(mCompletionListener);
   }

   private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
      
      @Override
      public void onCompletion(MediaPlayer mp) {
         Utils.log(TAG, "onCompletion");
         mp.release();
      }
   };
      
   
   public void stop() {
      if (mPlayer != null) {
         mPlayer.stop();
         mPlayer.release();
         mPlayer = null;
      }
   }
}
