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

    public void getPossibleMoves(Piece[][]Pieces, HashMap<Integer, ImageButton>Buttons, MainActivity mainActivity){
    }

    public void setSomethingThere(boolean input){
        somethingThere = input;
    }

    public void restore(Piece[][]Pieces, HashMap<Integer, ImageButton> Buttons, MainActivity mainActivity){
        final MainActivity tempAct = mainActivity;
        tempButtons = Buttons;
        tempPieces = Pieces;
        for(int i = 0; i<8; i++) {
            boolean black = true;
            if (i % 2 > 0) {
                black = false;
            }
            for (int x = 0; x< 8; x++) {
                if (black) {
                    tempButtons.get(i*8 + x).setBackgroundColor(Color.WHITE);
                    black = false;
                } else {
                    tempButtons.get(i*8 + x).setBackgroundColor(Color.DKGRAY);
                    black = true;
                }
                final int tempfinalY = i;
                final int tempFinalX = x;
                tempButtons.get(i*8 + x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restore(tempPieces, tempButtons, tempAct);
                        try{
                        tempPieces[tempfinalY][tempFinalX].getPossibleMoves(tempPieces,tempButtons, tempAct);
                    }catch(NullPointerException e){

                    }
                    }});
            }
        }
        Buttons = tempButtons;
    }

    public void moves(Piece[][]Pieces, HashMap<Integer, ImageButton> Buttons, MainActivity mainActivity){
        thisPiece = this;
        tempPieces = Pieces;
        tempButtons = Buttons;
        final int insideTempX = tempX;
        final int insideTempY = tempY;
        final MainActivity tempAct = mainActivity;
        if (tempPieces[insideTempY][insideTempX]==null){
            tempButtons.get(insideTempY*8 + insideTempX).setBackgroundColor(Color.YELLOW);
            tempButtons.get(insideTempY*8 + insideTempX).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempButtons.get(thisPiece.getY() * 8 + thisPiece.getX()).setImageResource(R.drawable.tom);
                    tempButtons.get(insideTempY*8 + insideTempX).setImageResource(thisPiece.getMotive());
                    tempPieces[thisPiece.getY()][thisPiece.getX()] = null;
                    tempPieces[insideTempY][insideTempX] = thisPiece;
                    restore(tempPieces, tempButtons, tempAct);
                    tempAct.writeGame(tempAct.getThisGame());
                    tempAct.setMain();
                }
            });
            Buttons = tempButtons;
            Pieces = tempPieces;
        }
        else if(tempPieces[insideTempY][insideTempX].getFriendly()!=this.getFriendly()){
            tempButtons.get(insideTempY*8 + insideTempX).setBackgroundColor(Color.YELLOW);
            tempButtons.get(insideTempY*8 + insideTempX).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempButtons.get(thisPiece.getY() * 8 + thisPiece.getX()).setImageResource(R.drawable.tom);
                    tempButtons.get(insideTempY*8 + insideTempX).setImageResource(thisPiece.getMotive());
                    tempPieces[thisPiece.getY()][thisPiece.getX()] = null;
                    tempPieces[insideTempY][insideTempX] = thisPiece;
                    restore(tempPieces, tempButtons, tempAct);
                    tempAct.writeGame(tempAct.getThisGame());
                    tempAct.setMain();
                }
            });
            Buttons = tempButtons;
            setSomethingThere(true);
            Pieces = tempPieces;
        }
        else{
            setSomethingThere(true);
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
    public boolean outOfBoundsX(int input, Piece pieceInput) {
        if (pieceInput.getX() + input>7 || pieceInput.getX() + input<0){
            return false;
        }
        return true;
    }
    public boolean outOfBoundsY(int input, Piece pieceInput) {
        if (pieceInput.getY() + input>7 || pieceInput.getY() + input<0){
            return false;
        }
        return true;
    }

    public int getTempY(){
        return tempY;
    }
    public int getTempX(){
        return tempX;
    }
}
