package com.example.axel.chess;

import android.widget.ImageButton;

import java.util.HashMap;

/**
 * Created by Axel on 2017-05-24.
 */

public class Knight extends Piece {
    private HashMap<Integer, ImageButton> tempButtons;
    private Piece thisPiece;
    private int tempY;
    private int tempX;
    private boolean hasMoved;
    private Piece tempPieces[][];
    private boolean somethingThere;
    public Knight(int motive){
        super(motive, true);
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity){
        tempButtons = ButtonsInput;
        tempPieces = PiecesInput;
        somethingThere = false;

        if(outOfBoundsX(1) && outOfBoundsY(2)){
            tempY = getY()+2;
            tempX = getX()+1;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(1) && outOfBoundsY(-2)){
            tempY = getY()-2;
            tempX = getX()+1;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(-1) && outOfBoundsY(2)){
            tempY = getY()+2;
            tempX = getX()-1;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(-1) && outOfBoundsY(-2)){
            tempY = getY()-2;
            tempX = getX()-1;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(2) && outOfBoundsY(1)){
            tempY = getY()+1;
            tempX = getX()+2;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(-2) && outOfBoundsY(1)){
            tempY = getY()+1;
            tempX = getX()-2;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(-2) && outOfBoundsY(-1)){
            tempY = getY()-1;
            tempX = getX()-2;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(2) && outOfBoundsY(-1)){
            tempY = getY()-1;
            tempX = getX()+2;
            moves(ButtonsInput);
        }
    }
}
