package com.example.froogygoogy.a2048.TestView;

import android.graphics.Bitmap;

import com.example.froogygoogy.a2048.Framework.Graphics;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.Framework.TouchHandler;
import com.example.froogygoogy.a2048.Mechanics.Mechanics;

import java.util.List;

public class TestViewController  implements IGameController {
    private float currentX,currentY,targetX;
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
        graphics.clear(0xFF997777);

        for(int i = 0; i < 4; i++)
        {
            graphics.drawRect(side*0+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            graphics.drawRect(side*1+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            graphics.drawRect(side*2+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            graphics.drawRect(side*3+5,side*(i+2),side-10,side-10,0xFFFFFFFF);
            for (int j = 0; j < 4; j++)
            {
                graphics.drawText(mechanics.getValue(i,j), (side*j+5)+side/3, (side*(i+2)+side*3/5));
            }
        }

        return graphics.getFrameBuffer();
    }
}
