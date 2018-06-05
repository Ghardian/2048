package com.example.froogygoogy.a2048.TestNormalMode;

import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;

public class TestNormalMode extends GameActivity{
    private  TestNormalModeController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestNormalModeController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/4);
        return controller;    }
}
