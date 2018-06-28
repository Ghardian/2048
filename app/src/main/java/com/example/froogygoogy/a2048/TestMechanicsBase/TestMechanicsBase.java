package com.example.froogygoogy.a2048.TestMechanicsBase;

import android.content.Context;
import android.util.DisplayMetrics;

import com.example.froogygoogy.a2048.Framework.GameActivity;
import com.example.froogygoogy.a2048.Framework.IGameController;
import com.example.froogygoogy.a2048.TestMechanics.TestMechanicsController;

public class TestMechanicsBase extends GameActivity {
    private com.example.froogygoogy.a2048.TestMechanicsBase.TestMechanicsBaseController controller;
    @Override
    protected IGameController buildGameController() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Context mContext;
        mContext = this;
        this.controller = new TestMechanicsBaseController(displayMetrics.widthPixels,displayMetrics.heightPixels,displayMetrics.widthPixels/10,mContext);
        return controller;
    }
}
