package com.example.froogygoogy.a2048.Framework;

import android.graphics.Bitmap;

import com.example.froogygoogy.a2048.Framework.TouchHandler.TouchEvent;

import java.util.List;

/**
 * Created by jvilar on 29/03/16.
 */
public interface IGameController {
    void onUpdate(float deltaTime, List<TouchEvent> touchEvents);
    Bitmap onDrawingRequested();
}
