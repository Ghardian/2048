package com.example.froogygoogy.a2048.TestView;

import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.TestMechanics.TestMechanicsController;

public class TestView extends GameActivity {
    private  TestViewController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new TestViewController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/4);
        return controller;
    }
}
