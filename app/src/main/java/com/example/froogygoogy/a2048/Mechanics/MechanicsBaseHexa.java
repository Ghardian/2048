package com.example.froogygoogy.a2048.Mechanics;

public class MechanicsBaseHexa  extends MechanicsParent  {
    @Override
    protected void AddNumber() {
        if(areOptions())
        {
            int pos = (int)(Math.random()*options.size());
            int[] spot = options.get(pos);
            double r = Math.random();
            grid[spot[0]][spot[1]]= r > 0.97 ? 2 : 1;
        }
    }

    @Override
    int[] combine(int[] col) {
        int[] exit = col;
        for(int i = 3; i>=1;i--)
        {
            int a = exit[i];
            int b = exit[i-1];
            if(a == b && a!=0)
            {
                score += a + b;
                exit[i] = a+1;
                exit[i-1] = 0;
                if( (a+b) == 15)
                {
                    win = true;
                }
            }
        }

        return exit;
    }
}
