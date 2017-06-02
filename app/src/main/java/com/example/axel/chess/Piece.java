package com.example.axel.chess;


import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

public class Piece {
    private int Motive;
    private int X;
    private int Y;
    private boolean friendly;
    private HashMap<Integer, ImageButton>tempButtons;
    private Piece thisPiece;
    private int tempY;
    private int tempX;
    private Piece tempPieces[][];
    private boolean somethingThere;

    public Piece(int Drawable, boolean colour){
        Motive = Drawable;
        friendly = colour;
    }

    public int getMotive(){
        return Motive;
    }

    public void getPossibleMoves(Piece[][]Pieces, HashMap<Integer, ImageButton>Buttons){

    }

    public void restore(Piece[][]Pieces, HashMap<Integer, ImageButton> Buttons){
        tempButtons = Buttons;
        tempPieces = Pieces;
        for(int i = 0; i<8; i++) {
            boolean black = true;
            if (i % 2 > 0) {
                black = false;
            }
            for (int x = 0; x< 8; x++) {
                if (black) {
                    tempButtons.get(i*8 + x).setBackgroundColor(Color.DKGRAY);
                    black = false;
                } else {
                    tempButtons.get(i*8 + x).setBackgroundColor(Color.WHITE);
                    black = true;
                }
                final int tempfinalY = i;
                final int tempFinalX = x;
                tempButtons.get(i*8 + x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restore(tempPieces, tempButtons);
                        try{
                        tempPieces[tempfinalY][tempFinalX].getPossibleMoves(tempPieces,tempButtons);
                    }catch(NullPointerException e){

                    }
                    }});
            }
        }
        Buttons = tempButtons;
    }

    public void moves(HashMap<Integer, ImageButton> Buttons){
        thisPiece = this;
        if (tempPieces[tempY][tempX]==null){
            Buttons.get(tempY*8 + tempX).setBackgroundColor(Color.YELLOW);
            Buttons.get(tempY*8 + tempX).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempButtons.get(thisPiece.getY() * 8 + thisPiece.getX()).setImageResource(R.drawable.tom);
                    tempButtons.get(tempY*8 + tempX).setImageResource(thisPiece.getMotive());
                    tempPieces[thisPiece.getY()][thisPiece.getX()] = null;
                    tempPieces[tempY][tempX] = thisPiece;
                    restore(tempPieces, tempButtons);
                }
            });
            Buttons = tempButtons;
        }
        else if(tempPieces[tempY][tempX].getFriendly()!=this.getFriendly()){
            Buttons.get(tempY*8 + tempX).setBackgroundColor(Color.YELLOW);
            Buttons.get(tempY*8 + tempX).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tempButtons.get(thisPiece.getY() * 8 + thisPiece.getX()).setImageResource(R.drawable.tom);
                    tempButtons.get(tempY*8 + tempX).setImageResource(thisPiece.getMotive());
                    tempPieces[thisPiece.getY()][thisPiece.getX()] = null;
                    tempPieces[tempY][tempX] = thisPiece;
                    restore(tempPieces, tempButtons);
                }
            });
            Buttons = tempButtons;
            somethingThere = true;
        }
        else{
            somethingThere = true;
        }
    }

    public void setPosition(int inputY, int inputX){
        Y = inputY;
        X = inputX;
    }
    public int getY(){
        return Y;
    }
    public int getX(){
        return X;
    }
    public boolean getFriendly(){
        return friendly;
    }
    public boolean outOfBoundsX(int input) {
        if (getX() + input>7 || getX() + input<0){
            return false;
        }
        return true;
    }
    public boolean outOfBoundsY(int input) {
        if (getY() + input>7 || getY() + input<0){
            return false;
        }
        return true;
    }
}
