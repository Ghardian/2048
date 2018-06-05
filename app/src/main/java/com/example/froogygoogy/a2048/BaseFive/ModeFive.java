package com.example.froogygoogy.a2048.BaseFive;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.BaseThree.ModeThreeController;
import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;

public class ModeFive extends GameActivity {
    private ModeFiveController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Context mContext;
        mContext = this;
        this.controller = new ModeFiveController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/4,mContext);
        return controller;    }
}

