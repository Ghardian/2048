package com.example.froogygoogy.a2048.Mechanics;

import android.util.Log;

import java.util.LinkedList;

public class MechanicsBase {
    private int [][] grid = new int[4][4];
    private LinkedList<int[]> options;
    private int score = 0;
    private boolean win = false;
    private boolean lost = false;
    private int mode = 2;

    public MechanicsBase(int mode) {
        switch (mode)
        {
            case 3:
                this.mode = 3;
                break;
            case 5:
                this.mode = 5;
                break;
            case 16:
                this.mode = 16;
                Log.d("Hexa",""+this.mode);
                break;
                default:
                    this.mode = 2;
                    break;
        }
        AddNumber();
        AddNumber();
    }
    public int getValue(int i, int j)
    {
        return grid[i][j];
    }
    private void AddNumber() {

        if(areOptions())
        {
            int pos = (int)(Math.random()*options.size());
            Log.d("grid",""+pos);
            int[] spot = options.get(pos);
            double r = Math.random();
            switch (this.mode)
            {
                case 3:
                    grid[spot[0]][spot[1]]= r > 0.95 ? 6 : 3;
                    break;
                case 5:
                    grid[spot[0]][spot[1]]= r > 0.95 ? 10 : 5;
                    break;
                case 16:
                    Log.d("Hexa",""+this.mode);
                    grid[spot[0]][spot[1]]= r > 0.95 ? 2 : 1;
                    break;
                    default:
                        grid[spot[0]][spot[1]]= r > 0.95 ? 4 : 2;
                        break;
            }
        }
    }

    private boolean areOptions() {

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
    private void isGameOver()
    {
        lost = true;
        for (int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(grid[i][j] == 0)
                {
                    lost = false;
                }
                if(i!= 3 && grid[i][j] == grid[i+1][j])
                {
                    lost = false;
                }
                if(j!= 3 && grid[i][j] == grid[i][j+1])
                {
                    lost = false;
                }
            }
        }
    }
    public boolean isWin() {
        return win;
    }

    public boolean isLost() {
        return lost;
    }

    public int getScore() {
        return score;
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
                switch (this.mode)
                {
                    case 3:
                        Log.d("Hexa","suma 3");
                        score += a + b;
                        exit[i] = a + b;
                        exit[i-1] = 0;
                        if( (a+b) == 6144)
                        {
                            win = true;
                        }
                        break;
                    case 5:
                        Log.d("Hexa","suma 5");
                        score += a + b;
                        exit[i] = a + b;
                        exit[i-1] = 0;
                        if( (a+b) == 10240)
                        {
                            win = true;
                        }
                        break;
                    case 16:
                        score += a + b;
                        exit[i] = a + 1;
                        Log.d("Hexa","suma"+exit[i]);
                        exit[i-1] = 0;
                        if( (a+b) == 15)
                        {
                            win = true;
                        }
                        break;
                    default:
                        Log.d("Hexa","suma default");
                        score += a + b;
                        exit[i] = a + b;
                        exit[i-1] = 0;
                        if( (a+b) == 2048)
                        {
                            win = true;
                        }
                        break;
                }
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
    public void slideUp()
    {
        if(!win && !lost) {
            for (int i = 0; i < 4; i++) {
                int[] row = new int[]{grid[3][i], grid[2][i], grid[1][i], grid[0][i]};
                row = slide(row);
                row = combine(row);
                row = slide(row);
                grid[0][i] = row[3];
                grid[1][i] = row[2];
                grid[2][i] = row[1];
                grid[3][i] = row[0];
            }
            AddNumber();
            isGameOver();
            for (int i = 0; i < 4; i++) {
                int[] row = new int[]{grid[0][i], grid[1][i], grid[2][i], grid[3][i]};
            }
        }
    }
    public void slideDown()
    {
        if(!win && !lost)
        {
            for (int i = 0; i < 4; i++)//correcto
            {
                int[] row = new int[]{grid[0][i], grid[1][i], grid[2][i], grid[3][i]};
                row = slide(row);
                row = combine(row);
                row = slide(row);
                grid[0][i] = row[0];
                grid[1][i] = row[1];
                grid[2][i] = row[2];
                grid[3][i] = row[3];
            }
            AddNumber();
            isGameOver();
            for (int i = 0; i < 4; i++) {
                int[] row = new int[]{grid[0][i], grid[1][i], grid[2][i], grid[3][i]};
            }
        }
    }
    public void slideLeft()
    {
        if(!win && !lost)
        {
            for (int i = 0; i < 4; i++)//modo correcto para este
            {
                int[] row = new int[]{grid[i][3], grid[i][2], grid[i][1], grid[i][0]};
                row = slide(row);
                row = combine(row);
                row = slide(row);
                grid[i] = new int[]{row[3], row[2], row[1], row[0]};
            }
            AddNumber();
            isGameOver();
            for (int i = 0; i < 4; i++) {
                int[] row = new int[]{grid[i][3], grid[i][2], grid[i][1], grid[i][0]};
            }
        }
    }
    public void slideRight()
    {
        if(!win && !lost)
        {
            for (int i = 0; i < 4; i++)//modo correcto para este
            {
                int[] row = new int[]{grid[i][0], grid[i][1], grid[i][2], grid[i][3]};
                row = slide(row);
                row = combine(row);
                row = slide(row);
                grid[i] = new int[]{row[0], row[1], row[2], row[3]};
            }
            AddNumber();
            isGameOver();
            for (int i = 0; i < 4; i++) {
                int[] row = new int[]{grid[i][0], grid[i][1], grid[i][2], grid[i][3]};
            }
        }
    }

}
