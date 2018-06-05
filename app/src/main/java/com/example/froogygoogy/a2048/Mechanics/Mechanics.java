package com.example.froogygoogy.a2048.Mechanics;

import android.util.Log;

import java.util.LinkedList;

public class Mechanics {
    int [][] grid = new int[4][4];
    LinkedList<int[]> options;

    public Mechanics() {
        grid = new  int[][]{{2,2048,2048,2048},{2048,2048,2048,2048},{2048,2048,2048,2048},{2048,2048,2048,2048}};
        AddNumber();
        AddNumber();
    }
    public int getValue(int i, int j)
    {
        return grid[i][j];
    }
    private boolean areOptions()
    {
        boolean exit = false;
        options = new LinkedList();;
        for (int i = 0; i<4;i++)
        {
            for (int j = 0;j<4;j++)
            {
                if(grid[i][j]==0)
                {
                    int [] dato = new  int[2];
                    dato[0]=i;
                    dato[1]=j;
                    options.add(dato);
                    exit = true;
                }
            }
        }
        return exit;
    }
    private void AddNumber()
    {

        if(areOptions())
        {
            int pos = (int)(Math.random()*options.size());
            Log.d("grid",""+pos);
            int[] spot = options.get(pos);
            double r = Math.random();
            grid[spot[0]][spot[1]]= r > 0.95 ? 4 : 2;
            for (int i = 0; i<4;i++)
            {
                for (int j = 0;j<4;j++)
                {
                    Log.d("grid","Valores "+ i + ","+ j+ " = "+grid[i][j]);
                }
            }
        }
    }
    private int[] slide(int[] col)
    {
        int[] exit = new int[4];
        int pos = 3;
        for (int i = 3; i>=0; i--)
        {
            if(col[i]!=0)
            {
                exit[pos] = col[i];
                pos --;
            }
        }
        return  exit;
    }
    private int[] combine(int[] col)
    {
        int[] exit = col;
        for(int i = 3; i>=1;i--)
        {
            int a = exit[i];
            int b = exit[i-1];
            if(a == b)
            {
                exit[i] = a + b;
                exit[i-1] = 0;
            }
        }

        return exit;
    }
    public void slideUp()
    {
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{grid[3][i], grid[2][i], grid[1][i], grid[0][i]};
            Log.d("Total test",""+i + "-"+row[3]+row[2]+row[1]+row[0]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            grid[0][i] = row[3];
            grid[1][i] = row[2];
            grid[2][i] = row[1];
            grid[3][i] = row[0];
        }
        AddNumber();
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{grid[0][i], grid[1][i], grid[2][i], grid[3][i]};
            Log.d("Total Final ",""+row[0]+row[1]+row[2]+row[3]);
        }

    }
    public void slideDown()
    {
        for(int i = 0; i < 4; i++)//correcto
        {
            int[] row = new int[]{grid[0][i], grid[1][i], grid[2][i], grid[3][i]};
            Log.d("Total test",""+i + "-"+row[0]+row[1]+row[2]+row[3]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            grid[0][i] = row[0];
            grid[1][i] = row[1];
            grid[2][i] = row[2];
            grid[3][i] = row[3];
        }
        AddNumber();
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{grid[0][i], grid[1][i], grid[2][i], grid[3][i]};
            Log.d("Total Final ",""+row[0]+row[1]+row[2]+row[3]);
        }
    }
    public void slideLeft()
    {
        for(int i = 0; i < 4; i++)//modo correcto para este
        {
            int[] row = new int[]{grid[i][3], grid[i][2], grid[i][1], grid[i][0]};
            Log.d("Total",""+row[3]+row[2]+row[1]+row[0]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            grid[i] = new int[]{row[3], row[2], row[1], row[0]};
        }
        AddNumber();
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{grid[i][3], grid[i][2], grid[i][1], grid[i][0]};
            Log.d("Total Final ",""+row[3]+row[2]+row[1]+row[0]);
        }
    }
    public void slideRight()
    {
        for(int i = 0; i < 4; i++)//modo correcto para este
        {
            int[] row = new int[]{grid[i][0], grid[i][1], grid[i][2], grid[i][3]};
            Log.d("Total",""+row[0]+row[1]+row[2]+row[3]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            grid[i] = new int[]{row[0], row[1], row[2], row[3]};
        }
        AddNumber();
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{grid[i][0], grid[i][1], grid[i][2], grid[i][3]};
            Log.d("Total Final ",""+row[0]+row[1]+row[2]+row[3]);
        }
    }

}
