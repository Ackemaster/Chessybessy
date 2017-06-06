package com.example.axel.chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        RelativeLayout layout = (RelativeLayout) getWindow().findViewById(R.id.mainlayout);
        layout.addView(new ImageButton(this));
    }
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}
