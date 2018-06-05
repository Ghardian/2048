package com.example.froogygoogy.a2048.NormalMode;

import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;

public class NormalMode extends GameActivity {
    private NormalModeController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.controller = new NormalModeController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/4);
        return controller;    }
}
