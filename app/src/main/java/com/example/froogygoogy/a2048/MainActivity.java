package com.example.froogygoogy.a2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.froogygoogy.a2048.TestFramework.TestFramework;
import com.example.froogygoogy.a2048.TestMechanics.TestMechanics;
import com.example.froogygoogy.a2048.TestNormalMode.TestNormalMode;
import com.example.froogygoogy.a2048.TestView.TestView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void testFramework(View view) {
        Intent intent = new Intent(this, TestFramework.class);
        startActivity(intent);
    }
    public void testMechanics(View view) {
        Intent intent = new Intent(this, TestMechanics.class);
        startActivity(intent);
    }
    public void testView(View view) {
        Intent intent = new Intent(this, TestView.class);
        startActivity(intent);
    }
    public void testNormalMode(View view) {
        Intent intent = new Intent(this, TestNormalMode.class);
        startActivity(intent);
    }
}
