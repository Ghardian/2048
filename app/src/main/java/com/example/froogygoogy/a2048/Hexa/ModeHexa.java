package com.example.froogygoogy.a2048.Hexa;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;

public class ModeHexa extends GameActivity {
    private ModeHexaController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Context mContext;
        mContext = this;
        this.controller = new ModeHexaController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/4,mContext);
        return controller;    }
}

