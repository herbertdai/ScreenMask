package com.howfun.android.screenmask;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.howfun.android.screenmask.mask.Mask;
import com.howfun.android.screenmask.mask.MovableMask;
import com.howfun.android.screenmask.mask.StaticMask;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;

public class ScreenManger {

   public static final String TAG = "ScreenManager";

   public static final int NO_MASK = 0;
   public static final int STATIC_MASK = 1;
   public static final int MOVABLE_MASK = 2;

   public static final long DELAY_MILLIS = 200L;

   public static final int MAX_MOVABLE_MASKS = 20;
   public static final int MAX_STATIC_MASKS = 50;

   public static final int MSG_UPDATE_SCREEN = 1;
   public static final int MSG_KILL_MASK = 2;

   private ScreenView mScreenView;

   private int mMaskType = NO_MASK;
   private Queue<StaticMask> mStaticMaskQueue;
   private Queue<MovableMask> mMovableMaskQueue;

   private Handler mHandler = new Handler() {

      public void handleMessage(Message msg) {
         switch (msg.what) {
         case MSG_UPDATE_SCREEN:
            update();
            break;
         case MSG_KILL_MASK:
            MovableMask mask = (MovableMask) msg.obj;
            if (mMovableMaskQueue.contains(mask)) {
               mMovableMaskQueue.remove(mask);
            }
            break;
         }
      }
   };

   private Thread mThread = new Thread() {
      public void run() {
         while (true) {
            try {
               sleep(DELAY_MILLIS);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            mHandler.sendEmptyMessage(MSG_UPDATE_SCREEN);
         }
      }
   };

   public ScreenManger() {
      mStaticMaskQueue = new LinkedList<StaticMask>();
      mMovableMaskQueue = new LinkedList<MovableMask>();

      mThread.start();
   }

   public void setScreenView(ScreenView screenView) {
      this.mScreenView = screenView;
   }

   public void addMask(Mask mask) {
      if (mask instanceof StaticMask) {
         mMaskType = STATIC_MASK;
         StaticMask sMask = (StaticMask) mask;
         addStaticMask(sMask);
      } else if (mask instanceof MovableMask) {
         mMaskType = MOVABLE_MASK;
         MovableMask mMask = (MovableMask) mask;
         addMovableMask(mMask);
      } else {
         Utils.log(TAG, "no such mask..");
      }
   }

   /*
    * Add one static mask to screen
    * 
    * @return true success false if fail
    */
   public boolean addStaticMask(StaticMask mask) {

      if (mask == null) {
         Utils.log(TAG, "mask is null,return");
         return false;
      }

      if (mStaticMaskQueue.size() == MAX_STATIC_MASKS) {
         StaticMask removeMask = mStaticMaskQueue.remove();
         mScreenView.removeView(removeMask);
      }
      // TODO add a mask
      Utils.log(TAG, "add a static mask");
      mStaticMaskQueue.add(mask);
      mScreenView.addView(mask);
      Rect rect = mask.getRect();
      mask.layout(rect.left, rect.top, rect.right, rect.bottom);
      return true;
   }

   public boolean addMovableMask(MovableMask mask) {
      if (mask == null) {
         Utils.log(TAG, "mask is null,return");
         return false;
      }

      if (mMovableMaskQueue.size() == MAX_MOVABLE_MASKS) {
         mMovableMaskQueue.remove();
      }

      Utils.log(TAG, "add a movable mask");
      mMovableMaskQueue.add(mask);
      mScreenView.addView(mask);
      // TODO
      Message msg = new Message();
      msg.what = MSG_KILL_MASK;
      msg.obj = mask;
      mHandler.sendMessageDelayed(msg, mask.getLife());
      Rect rect = mask.getRect();
      mask.layout(rect.left, rect.top, rect.right, rect.bottom);
      return true;
   }

   public void removeStaticMasks() {
      mScreenView.removeAllViews();
      mStaticMaskQueue.clear();
   }

   public void removeMovableMasks() {
      mScreenView.removeAllViews();
      mMovableMaskQueue.clear();
   }

   public void clear() {
      removeStaticMasks();
      removeMovableMasks();
   }

   public void setNextPosition() {
      if (mMovableMaskQueue.size() == 0)
         return;

      Iterator<MovableMask> it = mMovableMaskQueue.iterator();
      while (it.hasNext()) {
         MovableMask mask = it.next();
         int x = mask.getCenterX();
         int y = mask.getCenterY();
         int step = mask.getStep();
         int direction = mask.getDirection();
         switch (direction) {
         case Utils.NORTH:
            Utils.log(TAG, "moving north");
            mask.setCenter(x, y - step);
            break;
         case Utils.SOUTH:
            Utils.log(TAG, "moving south");
            mask.setCenter(x, y + step);
            break;
         case Utils.WEST:
            Utils.log(TAG, "moving west");
            mask.setCenter(x - step, y);
            break;
         case Utils.EAST:
            Utils.log(TAG, "moving east");
            mask.setCenter(x + step, y);
            break;
         default:
            break;
         }
         // Utils.log(TAG, "get next position,x:" + x + ",y" + y);

      }
   }

   public void renderScreen() {
      if (mMovableMaskQueue.size() == 0) {
         if (mMaskType == MOVABLE_MASK) {
            mScreenView.removeAllViews();
         }
         return;
      }

      mScreenView.removeAllViews();
      Iterator<MovableMask> it = mMovableMaskQueue.iterator();
      while (it.hasNext()) {
         MovableMask mask = it.next();
         Rect rect = mask.getRect();
         mScreenView.addView(mask);
         mask.layout(rect.left, rect.top, rect.right, rect.bottom);
      }
   }

   public void update() {
      setNextPosition(); // TODO
      renderScreen();
   }

}
