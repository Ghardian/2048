package com.example.froogygoogy.a2048.TestFramework;

import android.graphics.Bitmap;

import com.example.froogygoogy.a2048.Framework.Graphics;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.Framework.TouchHandler;

import java.util.List;

public class TestFrameworkController implements IGameController {
    private float currentX,currentY,targetX;
    private boolean touching;
    private float speed;
    private int width,height,side;
    private Graphics graphics;



    public TestFrameworkController(int widthPixels, int heightPixels, int squareSide) {
        this.width = widthPixels;
        this.height = heightPixels;
        this.side = squareSide;
        this.currentX = (width/2)- (side/2);
        this.currentY = height/2;
        this.speed = width;
        this.touching = false;
        this.graphics = new Graphics(width,height);
    }

    @Override
    public void onUpdate(float deltaTime, List<TouchHandler.TouchEvent> touchEvents) {
        for (TouchHandler.TouchEvent event : touchEvents) {
            switch (event.type)
            {
                case TOUCH_DOWN:
                case TOUCH_DRAGGED:
                    touching = true;
                    targetX = event.x;
                    break;
                case TOUCH_UP:
                    touching = false;
                    break;
            }
        }
        if(currentX < targetX)
        {
            currentX += deltaTime*speed;
            if(currentX > targetX)
            {
                currentX = targetX;
            }
        }
        else
        {
            currentX -= deltaTime*speed;
            if(currentX < targetX)
            {
                currentX = targetX;
            }
        }
    }

    @Override
    public Bitmap onDrawingRequested() {
        graphics.clear(0xFF22FFFF);
        //graphics.drawRect(currentX,currentY,side,side,0xFFFF0000);
        return graphics.getFrameBuffer();
    }
}

