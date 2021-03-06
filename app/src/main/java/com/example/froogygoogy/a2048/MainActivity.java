package com.example.froogygoogy.a2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.froogygoogy.a2048.BaseFive.ModeFive;
import com.example.froogygoogy.a2048.BaseThree.ModeThree;
import com.example.froogygoogy.a2048.Hexa.ModeHexa;
import com.example.froogygoogy.a2048.NormalMode.NormalMode;
import com.example.froogygoogy.a2048.TestFramework.TestFramework;
import com.example.froogygoogy.a2048.TestMechanics.TestMechanics;
import com.example.froogygoogy.a2048.TestMechanicsBase.TestMechanicsBase;
import com.example.froogygoogy.a2048.TestNormalMode.TestNormalMode;
import com.example.froogygoogy.a2048.TestView.TestView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void NormalMode(View view) {
        Intent intent = new Intent(this, NormalMode.class);
        startActivity(intent);
    }
    public void HexaMode(View view) {
        Intent intent = new Intent(this, ModeHexa.class);
        startActivity(intent);
    }
    public void BaseThreeMode(View view) {
        Intent intent = new Intent(this, ModeThree.class);
        startActivity(intent);
    }
    public void BaseFiveMode(View view) {
        Intent intent = new Intent(this, ModeFive.class);
        startActivity(intent);
    }
    public void TestMechanicsBase(View view) {
        Intent intent = new Intent(this, TestMechanicsBase.class);
        startActivity(intent);
    }
}
