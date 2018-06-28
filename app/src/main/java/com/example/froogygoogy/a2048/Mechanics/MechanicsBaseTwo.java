package com.example.froogygoogy.a2048.Mechanics;

import android.util.Log;

public class MechanicsBaseTwo extends MechanicsParent {
    @Override
    protected void AddNumber()
    {
        if(areOptions())
        {
            int pos = (int)(Math.random()*options.size());
            Log.d("grid",""+pos);
            int[] spot = options.get(pos);
            double r = Math.random();
            grid[spot[0]][spot[1]]= r > 0.95 ? 4 : 2;
        }
    }

    @Override
    int[] combine(int[] col)
    {
        int[] exit = col;
        for(int i = 3; i>=1;i--)
        {
            int a = exit[i];
            int b = exit[i-1];
            if(a == b)
            {
                score += a + b;
                exit[i] = a + b;
                exit[i-1] = 0;
                if( (a+b) == 2048)
                {
                    win = true;
                }
            }
        }
        return exit;
    }
}
