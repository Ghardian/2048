package com.example.froogygoogy.a2048.TestView;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.froogygoogy.a2048.Framework.Graphics;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.Framework.TouchHandler;
import com.example.froogygoogy.a2048.Mechanics.Mechanics;

import java.util.List;

public class TestViewController  implements IGameController {
    private float currentX,currentY,targetX;
    private float startX,startY,endX,endY;
    private boolean touching;
    private float speed;
    private int width,height,side;
    private Graphics graphics;
    private Mechanics mechanics;



    public TestViewController(int widthPixels, int heightPixels, int squareSide) {
        this.width = widthPixels;
        this.height = heightPixels;
        this.side = squareSide;
        this.currentX = (width/2)- (side/2);
        this.currentY = height/2;
        this.speed = width;
        this.touching = false;
        this.graphics = new Graphics(width,height);
        mechanics = new Mechanics();

    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {
        for (TouchHandler.TouchEvent event : touchEvents) {
            switch (event.type)
            {
                case TOUCH_DOWN:
                    startX = event.x;
                    startY = event.y;
                case TOUCH_DRAGGED:
                    break;
                case TOUCH_UP:
                    endX = event.x;
                    endY = event.y;

                    float difX,difY;
                    difX = startX - endX;
                    difY = startY - endY;
                    Log.d("Salida ","Valores  "+ startX + ","+ startY + "-" + endX + "," + endY + "-" + difX + "," + difY);

                    if(Math.abs(difX)>Math.abs(difY) && Math.abs(difX) > 0.0f && Math.abs(difY) > 0.0f)
                    {
                        if(difX < 0)
                        {
                            mechanics.slideRight();
                        }
                        else
                        {
                            mechanics.slideLeft();
                        }
                    }
                    else
                    {
                        if(difY < 0)
                        {
                            mechanics.slideDown();
                        }
                        else
                        {
                            mechanics.slideUp();
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public Bitmap onDrawingRequested() {
        graphics.clear(0xFF997777);

        for(int i = 0; i < 4; i++)
        {
            graphics.drawRect(side*0+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            graphics.drawRect(side*1+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            graphics.drawRect(side*2+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            graphics.drawRect(side*3+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            for (int j = 0; j < 4; j++)
            {
                int value = mechanics.getValue(i,j);
                if(value!=0)
                {
                    if(value < 10)
                    {
                        graphics.drawText(""+value, (side*j+5)+side/3, (side*(i+2)+side*3/5));
                    }
                    else if(value < 100)
                    {
                        graphics.drawText(""+value, (side*j+10)+side*1/5, (side*(i+2)+side*3/5));

                    }
                    else if(value < 1000)
                    {
                        graphics.drawText(""+value, (side*j+5)+side*1/6, (side*(i+2)+side*3/5));
                    }
                    else
                    {
                        graphics.drawText(""+value, (side*j+15), (side*(i+2)+side*3/5));

                    }
                }
            }
        }
        return graphics.getFrameBuffer();
    }
}
