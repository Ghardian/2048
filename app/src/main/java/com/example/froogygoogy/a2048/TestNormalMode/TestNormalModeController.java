package com.example.froogygoogy.a2048.TestNormalMode;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.froogygoogy.a2048.Framework.Graphics;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.Framework.TouchHandler;
import com.example.froogygoogy.a2048.Mechanics.Mechanics;

import java.util.List;

public class TestNormalModeController implements IGameController {
    private float startX,startY,endX,endY;
    private int width,height,side;
    private Graphics graphics;
    private Mechanics mechanics;



    public TestNormalModeController(int widthPixels, int heightPixels, int squareSide) {
        this.width = widthPixels;
        this.height = heightPixels;
        this.side = squareSide;
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
                    //Log.d("Salida ","Valores  "+ startX + ","+ startY + "-" + endX + "," + endY + "-" + Math.abs(difX) + "," + Math.abs(difY));

                    if(Math.abs(difX)>Math.abs(difY))
                    {
                        if(difX < 0)
                        {
                            Log.d("Salida ","Horizontal a derecha");

                            mechanics.slideRight();
                        }
                        else
                        {
                            Log.d("Salida ","Horizontal a izqierda");

                            mechanics.slideLeft();
                        }
                    }
                    else if(Math.abs(difX)<Math.abs(difY))
                    {

                        if(difY < 0)
                        {
                            Log.d("Salida ","Vertical abajo");
                            mechanics.slideDown();
                        }
                        else
                        {
                            Log.d("Salida ","Vertical arriba");
                            mechanics.slideUp();
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public Bitmap onDrawingRequested() {
        graphics.clear();
//0xFFC28170
        for(int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                int value = mechanics.getValue(i,j);
                float color =(float)( Math.log(value)/Math.log(2));
                //color = 0.0f;
                int vcol = (int)((((255-color*255/11)+1)*((255-color*255/11)+1)*-1));//Hacer formula para colores -65536 = rojo(2) -16711936 = verde(2048)
                if(value!=0)Log.d("Color"," " + color + "~" + vcol + "~" + 0xFF00FF00);
                graphics.drawRect(side*j+5,side*(i+2),side-10,side-10,vcol);
                if(value!=0)
                {
                    if(value < 10)
                    {
                        graphics.drawText(""+value, (side*j+5)+side/3, (side*(i+2)+side*3/5),80);
                    }
                    else if(value < 100)
                    {
                        graphics.drawText(""+value, (side*j+10)+side*1/5, (side*(i+2)+side*3/5),80);

                    }
                    else if(value < 1000)
                    {
                        graphics.drawText(""+value, (side*j+5)+side*1/6, (side*(i+2)+side*3/5),80);
                    }
                    else
                    {
                        graphics.drawText(""+value, (side*j+15), (side*(i+2)+side*3/5),80);

                    }
                }
            }
        }
        graphics.drawText("Score: "+ mechanics.getScore(),15,side,30);
        if(mechanics.isWin())
        {
            graphics.drawText("YOU WIN THE GAME",15,side*5/3,80);
        }
        else if(mechanics.isLost())
        {
            graphics.drawText("YOU LOST THE GAME",15,side*5/3,80);

        }
        Log.d("Win","WIN " + mechanics.isWin() + " Lost " + mechanics.isLost());
        return graphics.getFrameBuffer();
    }
}
