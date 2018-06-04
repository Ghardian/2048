package com.example.froogygoogy.a2048.Mechanics;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Mechanics {
    int [][] grid = new int[4][4];

    public Mechanics() {
        //AddNumber();
        //AddNumber();
        slideUp();
    }
    public void AddNumber()
    {
        LinkedList<int[]> options = new LinkedList();;
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
                }
            }
        }
        int pos = (int)(Math.random()*options.size());
        Log.d("grid",""+pos);
        int[] spot = options.get(pos);
        double r = Math.random();
        grid[spot[0]][spot[1]]= r > 0.9 ? 4 : 2;
        for (int i = 0; i<4;i++)
        {
            for (int j = 0;j<4;j++)
            {
               Log.d("grid","Valores "+ i + ","+ j+ " = "+grid[i][j]);
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
        int[][] test = new int[][]{{2,4,2,2},{0,2,2,4},{2,2,2,2},{2,0,0,4}};
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{test[3][i], test[2][i], test[1][i], test[0][i]};
            Log.d("Total test",""+i + "-"+row[3]+row[2]+row[1]+row[0]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            test[0][i] = row[3];
            test[1][i] = row[2];
            test[2][i] = row[1];
            test[3][i] = row[0];
        }
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{test[0][i], test[1][i], test[2][i], test[3][i]};
            Log.d("Total Final ",""+row[0]+row[1]+row[2]+row[3]);
        }
    }
    public void slideDown()
    {
        int[][] test = new int[][]{{2,4,2,2},{0,2,2,4},{2,2,2,2},{2,0,0,4}};
        for(int i = 0; i < 4; i++)//correcto
        {
            int[] row = new int[]{test[0][i], test[1][i], test[2][i], test[3][i]};
            Log.d("Total test",""+i + "-"+row[0]+row[1]+row[2]+row[3]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            test[0][i] = row[0];
            test[1][i] = row[1];
            test[2][i] = row[2];
            test[3][i] = row[3];
        }
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{test[0][i], test[1][i], test[2][i], test[3][i]};
            Log.d("Total Final ",""+row[0]+row[1]+row[2]+row[3]);
        }
    }
    public void slideLeft()
    {
        int[][] test = new int[][]{{2,4,2,2},{0,2,2,4},{2,2,2,2},{2,0,0,4}};
        for(int i = 0; i < 4; i++)//modo correcto para este
        {
            int[] row = new int[]{test[i][3], test[i][2], test[i][1], test[i][0]};
            Log.d("Total",""+row[3]+row[2]+row[1]+row[0]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            test[i] = new int[]{row[3], row[2], row[1], row[0]};
        }
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{test[i][3], test[i][2], test[i][1], test[i][0]};
            Log.d("Total Final ",""+row[3]+row[2]+row[1]+row[0]);
        }
    }
    public void slideRight()
    {
        int[][] test = new int[][]{{2,4,2,2},{0,2,2,4},{2,2,2,2},{2,0,0,4}};
        for(int i = 0; i < 4; i++)//modo correcto para este
        {
            int[] row = new int[]{test[i][0], test[i][1], test[i][2], test[i][3]};
            Log.d("Total",""+row[0]+row[1]+row[2]+row[3]);
            row = slide(row);
            row = combine(row);
            row = slide(row);
            test[i] = new int[]{row[0], row[1], row[2], row[3]};
        }
        for(int i = 0; i < 4; i++)
        {
            int[] row = new int[]{test[i][0], test[i][1], test[i][2], test[i][3]};
            Log.d("Total Final ",""+row[0]+row[1]+row[2]+row[3]);
        }
    }

}
