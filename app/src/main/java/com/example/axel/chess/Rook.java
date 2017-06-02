package com.example.axel.chess;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

public class Rook extends Piece {
    HashMap<Integer, ImageButton> tempButtons;
    int tempY;
    int tempX;
    Piece thisPiece;
    Piece[][]tempPieces;
    boolean somethingThere;
    public Rook(int motive, boolean friendly){
        super(motive, friendly);
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity){
        tempButtons = ButtonsInput;
        tempPieces = PiecesInput;
        somethingThere = false;

        for(tempY = this.getY()+1, tempX = getX(); tempY<8 &&!somethingThere; tempY++){
            moves(ButtonsInput);
            mainActivity.setDefaultColor();
        }
        somethingThere = false;
       for(tempY = this.getY()-1, tempX = getX(); tempY>-1 &&!somethingThere; tempY--){
           moves(ButtonsInput);
        }
        somethingThere = false;
        for(tempY = this.getY(), tempX = getX()+1; tempX<8 &&!somethingThere; tempX++){
            moves(ButtonsInput);
        }
        somethingThere = false;
        for(tempY = this.getY(), tempX = getX()-1; tempX>-1 &&!somethingThere; tempX--){
            moves(ButtonsInput);
        }
    }
}
