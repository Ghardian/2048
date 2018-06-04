package com.example.froogygoogy.a2048.Framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

import static android.graphics.Bitmap.Config.ARGB_8888;
import static java.lang.System.out;

/**
 * Created by jvilar on 29/03/16.
 */
public class Graphics {
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;

    public Graphics(int width, int height) {
        this.frameBuffer = Bitmap.createBitmap(width, height, ARGB_8888);
        canvas = new Canvas(frameBuffer);
        paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(20);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public Bitmap getFrameBuffer() {
        return frameBuffer;
    }

    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, color & 0xff);
    }

    public void drawRect(float x, float y, float width, float height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    public int getWidth() {
        return frameBuffer.getWidth();
    }

    public int getHeight() {
        return frameBuffer.getHeight();
    }

    public void drawBitmap(Bitmap ball, float x, float y) {
        canvas.drawBitmap(ball,x,y,null);
    }

    public void drawBitmap(Bitmap bitmap, float x, float y, float minX, float maxX) {
        int leftDst = (int)Math.max(minX, x),
                rightDst = (int) Math.min(maxX, x + bitmap.getWidth() - 1),
                leftSrc = x >= minX ? 0 : (int) (minX - x),
                rightSrc = leftSrc + rightDst - leftDst
                        ;
        Rect src = new Rect(leftSrc,0,rightSrc, ((int) (bitmap.getHeight())));
        Rect dst =  new Rect(leftDst,(int)y,rightDst, ((int) (y+bitmap.getHeight())));;

        canvas.drawBitmap(bitmap,src,dst,null);
    }
    public  void drawText(String text, int x, int y )
    {
        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText(text, x , y, paint);
    }
}
