package com.howfun.android.screenmask;

import com.howfun.android.screenmask.mask.MovableMask;

import android.graphics.Point;

public class Grid {
   private int mRectW;
   private int mRectH;
   private final int mXOffset;
   private final int mYOffset;
   private int mXNum;
   private int mYNum;
   private int mXLen;
   private int mYLen;

   private int[][] mGrid;

   public Grid(int rectW, int rectH) {
      // 800x480
      int widthPixels = 480;
      int heightPixels = 800 - 25;

      mRectW = rectW;
      mRectH = rectH;

      mXNum = widthPixels / rectW;
      mYNum = heightPixels / rectH;

      mXOffset = (widthPixels % rectW) / 2;
      mYOffset = (heightPixels % rectH) / 2;

      mXLen = mXNum * rectW;
      mYLen = mYNum * rectH;

      mGrid = new int[mXNum][mYNum];
   }

   public Coordinate whichGrid(Point p) {
      int x = p.x - mXOffset;
      int y = p.y - mYOffset;
      int gridX = 0;
      int gridY = 0;

      if (x == mXLen) {
         gridX = mXNum - 1;
      } else {
         gridX = x / mRectW;
      }

      if (y == mYLen) {
         gridY = mYNum - 1;
      } else {
         gridY = y / mRectH;
      }
      return new Coordinate(gridX, gridY);
   }

   public Point getCenter(Coordinate c) {// c means which grid
      int gridX = c.x;
      int gridY = c.y;
      int halfW = mRectW / 2;
      int halfH = mRectH / 2;
      int cX = gridX * mRectW + halfW + mXOffset;
      int cY = gridY * mRectH + halfH + mYOffset;
      return new Point(cX, cY);
   }

   public Point getCenter(Point p) {
      Coordinate c = whichGrid(p);
      int gridX = c.x;
      int gridY = c.y;
      int halfW = mRectW / 2;
      int halfH = mRectH / 2;
      int cX = gridX * mRectW + halfW + mXOffset;
      int cY = gridY * mRectH + halfH + mYOffset;
      return new Point(cX, cY);
   }

   public Point getNextCenter(int direction, Point center) {
      Coordinate c = whichGrid(center);
      int x = c.x;
      int y = c.y;
      switch (direction) {
      case Utils.NORTH:
         if (y != 0 && mGrid[x][y - 1] != 1) {
            mGrid[x][y] = 0;
            mGrid[x][y - 1] = 1;
            y = y - 1;
         }
         break;
      case Utils.SOUTH:
         if (y != mYNum - 1 && mGrid[x][y + 1] != 1) {
            mGrid[x][y] = 0;
            mGrid[x][y + 1] = 1;
            y = y + 1;
         }
         break;
      case Utils.WEST:
         if (x != 0 && mGrid[x - 1][y] != 1) {
            mGrid[x][y] = 0;
            mGrid[x - 1][y] = 1;
            x = x - 1;
         }
         break;
      case Utils.EAST:
         if (x != mXNum - 1 && mGrid[x + 1][y] != 1) {
            mGrid[x][y] = 0;
            mGrid[x + 1][y] = 1;
            x = x + 1;
         }
         break;
      }
      return getCenter(new Coordinate(x, y));
   }

   public Point addMask(MovableMask mask) {
      boolean exists = false;
      int x = mask.getCenterX();
      int y = mask.getCenterY();
      Coordinate c = whichGrid(new Point(x, y));
      if (mGrid[c.x][c.y] == 0) {
         mGrid[c.x][c.y] = 1;
      } else {
         exists = true;
      }

      Point p = null;
      if (!exists) {
         p = getCenter(c);
      }
      return p;
   }

   public int getXOffset() {
      return mXOffset;
   }

   public int getYOffset() {
      return mYOffset;
   }

   /**
    * Simple class containing two integer values
    */
   private static class Coordinate {
      public int x; // grid_x
      public int y; // grid_y

      public Coordinate(int newX, int newY) {
         x = newX;
         y = newY;
      }

      public boolean equals(Coordinate other) {
         if (x == other.x && y == other.y) {
            return true;
         }
         return false;
      }

      @Override
      public String toString() {
         return "Coordinate: [" + x + "," + y + "]";
      }
   }

}
