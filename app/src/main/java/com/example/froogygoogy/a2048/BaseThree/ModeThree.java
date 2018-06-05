package com.example.froogygoogy.a2048.BaseThree;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;

public class ModeThree extends GameActivity {
    private ModeThreeController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Context mContext;
        mContext = this;
        this.controller = new ModeThreeController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/4,mContext);
        return controller;    }
}

