package com.example.axel.chess;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.HashMap;

import static com.example.axel.chess.R.drawable.wrook;

public class MainActivity extends AppCompatActivity {
    Piece[][] Pieces;
    HashMap<Integer, ImageButton>ButtonMap;
    Piece allPieces;
    RelativeLayout theLayout;

private void InitiateField(){
    for(int y = 0; y < 8; y++) {boolean black = true;
        if (y % 2 > 0) {
            black = false;
        }
        for (int x = 0; x< 8; x++) {

            ImageButton bt = new ImageButton(this);
            bt.setId(y * 8 + x);
            if (black) {
                bt.setImageResource(R.drawable.tom);
                black = false;
            } else {
                bt.setImageResource(R.drawable.tom);
                black = true;
            }
            bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            bt.setScaleType(ImageButton.ScaleType.FIT_XY);
            bt.setScaleX(0.9f);
            bt.setScaleY(0.9f);
            bt.setY(y * 120);
            bt.setX(x * 90);

            ButtonMap.put(y*8 + x, bt);
            theLayout.addView(bt);
        }
    }
}
    private void InitiatePieces(){

        //White Pieces
                for (int lapCountX = 0; lapCountX < 8; lapCountX++) {
                    if(lapCountX==0 || lapCountX==7){
                        allPieces = new Rook(R.drawable.wrook, true);
                        buttonCreator(0, lapCountX, allPieces);
                    }
                    else if(lapCountX==1 || lapCountX==6){
                        allPieces = new Knight(R.drawable.wknight);
                        buttonCreator(0, lapCountX, allPieces);
                    }
                    else if(lapCountX==2 || lapCountX==5){
                        allPieces = new Bishop(R.drawable.wbishop);
                        buttonCreator(0, lapCountX, allPieces);
                    }
                    else if(lapCountX==3){
                        allPieces = new King(R.drawable.wking);
                        buttonCreator(0, lapCountX, allPieces);
                    }
                    else if(lapCountX==4){
                        allPieces = new Queen(R.drawable.wqueen);
                        buttonCreator(0, lapCountX, allPieces);
                    }
        }

        //White pawns
        for (int lapCountX = 0; lapCountX < 8; lapCountX++){
            allPieces = new Pawn(R.drawable.wpawn);
            buttonCreator(1, lapCountX, allPieces);
        }

        //Black pawns
        for (int lapCountX = 0; lapCountX < 8; lapCountX++){
            allPieces = new Pawn(R.drawable.bpawn);
            buttonCreator(6, lapCountX, allPieces);
        }

        //Black Pieces
        for (int lapCountX = 0; lapCountX < 8; lapCountX++) {
            if(lapCountX==0 || lapCountX==7){
                allPieces = new Rook(R.drawable.brook, true);
                buttonCreator(7, lapCountX, allPieces);
            }
            else if(lapCountX==1 || lapCountX==6){
                allPieces = new Knight(R.drawable.bknight);
                buttonCreator(7, lapCountX, allPieces);
            }
            else if(lapCountX==2 || lapCountX==5){
                allPieces = new Bishop(R.drawable.bbishop);
                buttonCreator(7, lapCountX, allPieces);
            }
            else if(lapCountX==3){
                allPieces = new King(R.drawable.bking);
                buttonCreator(7, lapCountX, allPieces);
            }
            else if(lapCountX==4){
                allPieces = new Queen(R.drawable.bqueen);
                buttonCreator(7, lapCountX, allPieces);
            }
        }
    }

    private void StartPosition(){


    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main);
        theLayout = (RelativeLayout) findViewById(R.id.linearLayout);
        //final Button button = (Button)findViewById(R.id.button3);
        //button.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        //final Button button2 = (Button)findViewById(R.id.button2);
        //button2.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
       // ViewGroup linearLayout = (ViewGroup) findViewById(R.id.blackbutton);
        //ImageView rook = (ImageView)findViewById(R.id.rook_icon);
        //final ImageButton butt = new ImageButton(this);


        ButtonMap= new HashMap<Integer, ImageButton>();

        Pieces= new Piece[8][8];
        InitiateField();
        InitiatePieces();
        setDefaultColor();
    }
    public void setDefaultColor(){
        for(int i = 0; i<8; i++) {
            boolean black = true;
            if (i % 2 > 0) {
                black = false;
            }
            for (int x = 0; x< 8; x++) {
                if (black) {
                    ButtonMap.get(i*8 + x).setBackgroundColor(Color.DKGRAY);
                    black = false;
                } else {
                    ButtonMap.get(i*8 + x).setBackgroundColor(Color.WHITE);
                    black = true;
                }
                final int tempfinalY = i;
                final int tempFinalX = x;
                ButtonMap.get(i*8 + x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setDefaultColor();
                        try {
                            Pieces[tempfinalY][tempFinalX].getPossibleMoves(Pieces, ButtonMap);
                        }
                        catch(NullPointerException e){

                        }
                    }});
            }
        }
    }
    public void buttonCreator(int y, int x, Piece thisPiece){
        ImageButton bt = new ImageButton(this);

        bt.setId(y*8 + x);
        bt.setImageResource(thisPiece.getMotive());
        bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        bt.setScaleType(ImageButton.ScaleType.FIT_XY);
        bt.setScaleX(0.9f);
        bt.setScaleY(0.9f);
        bt.setY(y*120);
        bt.setX(x*90);
        thisPiece.setPosition(y, x);
        Pieces[y][x] = thisPiece;
        ButtonMap.put((8*y)+x,bt);
        final int finalX = x;
        final int finalY = y;
        ButtonMap.get(y*8 + x).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pieces[finalY][finalX].getPossibleMoves(Pieces,ButtonMap);
            }});
        theLayout.addView(bt);
    }
    public void readLogin(){
        ObjectInputStream input;
        String filename = "login.txt";

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            Pieces = (Piece[][])input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    public void writeLogin(){

        String filename = "login.txt";
        ObjectOutput out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
            out.writeObject(Pieces);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeGame(){

        String filename = "game.txt";
        ObjectOutput out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
            out.writeObject(Pieces);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readGame(){
        ObjectInputStream input;
        String filename = "game.txt";

        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            Pieces = (Piece[][])input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
