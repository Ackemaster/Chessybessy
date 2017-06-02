package com.example.axel.chess;

import android.widget.ImageButton;

import java.util.HashMap;

/**
 * Created by Axel on 2017-05-24.
 */

public class King extends Piece {
    private HashMap<Integer, ImageButton> tempButtons;
    private Piece thisPiece;
    private int tempY;
    private int tempX;
    private boolean hasMoved;
    private Piece tempPieces[][];
    private boolean somethingThere;
    public King(int motive){
        super(motive, true);
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity) {
        tempButtons = ButtonsInput;
        tempPieces = PiecesInput;
        somethingThere = false;
        tempY = getY();
        tempX = getX();
        if(outOfBoundsY(1)) {
            tempY = this.getY() + 1;
            tempX = this.getX();
            moves(ButtonsInput);
            if(outOfBoundsX(1)){
                tempX++;
                moves(ButtonsInput);
            }
            if(outOfBoundsX(-1)){
                tempX--;
                moves(ButtonsInput);
            }
        }
        if(outOfBoundsY(-1)){
            tempY = getY()-1;
            moves(ButtonsInput);
            if(outOfBoundsX(1)){
                tempX++;
                moves(ButtonsInput);
            }
            if(outOfBoundsX(-1)){
                tempX--;
                moves(ButtonsInput);
            }
        }
        tempY = getY();
        if(outOfBoundsX(1)){
            tempX = getX()+1;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(-1)){
            tempX = getX()-1;
            moves(ButtonsInput);
        }
    }
}
