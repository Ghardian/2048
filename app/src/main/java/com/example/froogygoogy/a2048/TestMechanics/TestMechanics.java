package com.example.froogygoogy.a2048.TestMechanics;

import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;

public class TestMechanics extends GameActivity {
    private  TestMechanicsController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestMechanicsController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/10);
        return controller;
    }
}
