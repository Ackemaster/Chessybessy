package com.example.axel.chess;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.axel.chess.R.id.rook_icon;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main);
        final LinearLayout theLayout = (LinearLayout) findViewById(R.id.linearLayout);
        //final Button button = (Button)findViewById(R.id.button3);
        //button.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        //final Button button2 = (Button)findViewById(R.id.button2);
        //button2.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
       // ViewGroup linearLayout = (ViewGroup) findViewById(R.id.blackbutton);
ImageView rook = (ImageView)findViewById(R.id.rook_icon);
        final ImageButton butt = new ImageButton(this);
        final Button byt = (Button)findViewById(R.id.button);

        Button huh = (Button)findViewById(R.id.button);
        byt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               byt.setText("Funkade");
            }
        });
        for (int i = 0; i < 8; i++){
            for (int y = 0; y < 8; y++){
                Button bt = (Button)findViewById(R.id.button);

                bt.setId(y);
                bt.setText("Button " + y);
                bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                bt.setWidth(50);
                bt.setHeight(50);
                bt.setY(i*50);
                bt.setX(y*50);

                theLayout.addView(bt);
            }
        }
        //android:background="@android:color/black"
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        /*rook.setY(height/8);
        rook.setX(width/8+width/8);
        TextView bla = (TextView) findViewById(R.id.testext);
        bla.setText(width + " " + height);
        Button bt = new Button(this);
        bt.setVisibility(View.VISIBLE);
        bt.setText("9");
        bt.setX(height/2);
        bt.setY(width/2);
        bt.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT));
        */
    }
}
