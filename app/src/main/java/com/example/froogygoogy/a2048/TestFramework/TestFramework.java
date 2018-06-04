package com.example.froogygoogy.a2048.TestFramework;

import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;

public class TestFramework extends GameActivity {
    private   TestFrameworkController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestFrameworkController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/10);
        return controller;
    }
}
