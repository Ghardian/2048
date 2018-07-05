package com.example.froogygoogy.a2048.BaseThree;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.example.froogygoogy.a2048.Framework.Graphics;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.Framework.TouchHandler;
import com.example.froogygoogy.a2048.Mechanics.Mechanics;
import com.example.froogygoogy.a2048.Mechanics.MechanicsBaseThree;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ModeThreeController implements IGameController {
    public static final String MY_PREFS_NAME = "GameData";
    private float startX,startY,endX,endY;
    private int width,height,side;
    private Graphics graphics;
    private MechanicsBaseThree mechanics;
    private int MaxScore = 0;
    private int offsetWH = 0;
    private int offsetX = 5;
    private int offsetY = 2;
    private int fontSizeExtraBig = 80;
    private int fontSizeBig = 75;
    private int fontSizeMedium = 70;
    private int fontSizeSmall = 50;
    Context mContext;
    public ModeThreeController(int widthPixels, int heightPixels, int squareSide, Context context) {
        this.width = widthPixels;
        this.height = heightPixels;
        this.side = squareSide;
        this.graphics = new Graphics(width,height);
        mechanics = new MechanicsBaseThree();
        mContext = context;
        SharedPreferences prefs = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);//Cargar datos del archivo de preferences
        int max = prefs.getInt("MaxScoreBase3", -1);
        if(max != -1)
        {
            MaxScore = max;
        }
        offsetWH = (width/64);
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
        graphics.clear();

        for(int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                int value = mechanics.getValue(i,j);
                float color =(float)( Math.log(value)/Math.log(2));
                //color = 0.0f;
                int vcol = (int)((((255-color*255/11)+1)*((255-color*255/11)+1)*-1));//Hacer formula para colores -65536 = rojo(2) -16711936 = verde(2048)
                //Dibujar casilla
                graphics.drawRect(side*j+offsetX,side*(i+offsetY),side-offsetWH,side-offsetWH,vcol);
                if(value!=0)
                {
                    int unit = 10;
                    if(value < unit)
                    {
                        graphics.drawText(""+value, (side*j+(unit/2))+(side/3), (side*(i+2)+side*3/5),fontSizeExtraBig);
                    }
                    else if(value < (unit*10))
                    {
                        graphics.drawText(""+value, (side*j+unit)+(side*1/5), (side*(i+2)+side*3/5),fontSizeBig);

                    }
                    else if(value < (unit*100))
                    {
                        graphics.drawText(""+value, (side*j+(unit/2))+side*1/6, (side*(i+2)+side*3/5),fontSizeMedium);
                    }
                    else
                    {
                        graphics.drawText(""+value, (side*j+(unit+5)), (side*(i+2)+side*3/5),fontSizeMedium);
                    }
                }
            }
        }

        graphics.drawText("2048",15,side,200);
        graphics.drawText("Join the numbers and get to the 6144 tile!",15,500,fontSizeSmall);
        graphics.drawText("Current Score: "+ mechanics.getScore(),(width-(width/2)),side,fontSizeSmall);
        graphics.drawText("Max Score: "+ MaxScore,(width-(width/2)),side/2,fontSizeSmall);

        if(mechanics.isWin())
        {
            if(mechanics.getScore()>MaxScore)
            {
                MaxScore = mechanics.getScore();
                SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();//guardar en el archivo preferences
                editor.putInt("MaxScoreBase3", MaxScore);
                editor.apply();
            }
            graphics.drawText("YOU WIN THE GAME",15,side*5/3,80);
        }
        else if(mechanics.isLost())
        {
            if(mechanics.getScore()>MaxScore)
            {
                MaxScore = mechanics.getScore();
                SharedPreferences.Editor editor = mContext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();//guardar en el archivo preferences
                editor.putInt("MaxScoreBase3", MaxScore);
                editor.apply();
            }
            graphics.drawText("YOU LOST THE GAME",15,side*5/3,80);
        }
        return graphics.getFrameBuffer();
    }
}
