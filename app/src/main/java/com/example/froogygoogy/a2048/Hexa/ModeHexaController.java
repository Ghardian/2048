package com.example.froogygoogy.a2048.Hexa;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.froogygoogy.a2048.Framework.Graphics;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.Framework.TouchHandler;
import com.example.froogygoogy.a2048.Mechanics.MechanicsBaseThree;
import com.example.froogygoogy.a2048.Mechanics.MechanicsHexa;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ModeHexaController implements IGameController {
    public static final String MY_PREFS_NAME = "GameData";
    private float startX,startY,endX,endY;
    private int width,height,side;
    private Graphics graphics;
    private MechanicsHexa mechanics;
    private int MaxScore = 0;
    Context mContext;
    public ModeHexaController(int widthPixels, int heightPixels, int squareSide, Context context) {
        this.width = widthPixels;
        this.height = heightPixels;
        this.side = squareSide;
        this.graphics = new Graphics(width,height);
        mechanics = new MechanicsHexa();
        mContext = context;
        SharedPreferences prefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);//Cargar datos del archivo de preferences
        int max = prefs.getInt("MaxScoreBase6", -1);
        if(max != -1)
        {
            MaxScore = max;
        }
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

                    if(Math.abs(difX) > Math.abs(difY))
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
                    else if(Math.abs(difX) < Math.abs(difY))
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
            for (int j = 0; j < 4; j++)
            {
                int value = mechanics.getValue(i,j);
                float color =(float)( Math.log(value)/Math.log(2));
                //color = 0.0f;
                int vcol = (int)((((255-color*255/11)+1)*((255-color*255/11)+1)*-1));//Hacer formula para colores -65536 = rojo(2) -16711936 = verde(2048)
                graphics.drawRect(side*j+5,side*(i+2),side-10,side-10,vcol);
                if(value!=0)
                {
                    if(value < 10)
                    {
                        graphics.drawText(""+value, (side*j+5)+side/3, (side*(i+2)+side*3/5));
                    }
                    else
                    {
                        String exit = Integer.toHexString(value);
                        graphics.drawText(exit.toUpperCase(), (side*j+5)+side/3, (side*(i+2)+side*3/5));
                    }
                }
            }
        }
        graphics.drawText("Current Score: "+ mechanics.getScore(),15,side);
        graphics.drawText("Max Score: "+ MaxScore,15,side/2);

        if(mechanics.isWin())
        {
            if(mechanics.getScore()>MaxScore)
            {
                MaxScore = mechanics.getScore();
                SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();//guardar en el archivo preferences
                editor.putInt("MaxScoreBase6", MaxScore);
                editor.apply();
            }
            graphics.drawText("YOU WIN THE GAME",15,side*5/3);
        }
        else if(mechanics.isLost())
        {
            if(mechanics.getScore()>MaxScore)
            {
                MaxScore = mechanics.getScore();
                SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();//guardar en el archivo preferences
                editor.putInt("MaxScoreBase6", MaxScore);
                editor.apply();
            }
            graphics.drawText("YOU LOST THE GAME",15,side*5/3);
        }
        return graphics.getFrameBuffer();
    }
}
