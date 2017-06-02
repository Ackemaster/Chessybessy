package com.example.axel.chess;

import android.widget.ImageButton;

import java.util.HashMap;

/**
 * Created by Axel on 2017-05-24.
 */

public class Queen extends Piece {
    private HashMap<Integer, ImageButton> tempButtons;
    private Piece thisPiece;
    private int tempY;
    private int tempX;
    private boolean hasMoved;
    private Piece tempPieces[][];
    private boolean somethingThere;
    public Queen(int motive){
        super(motive, true);
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity){
        tempButtons = ButtonsInput;
        tempPieces = PiecesInput;
        somethingThere = false;
        tempX = this.getX()+1;

        for(tempY = this.getY()+1,tempX = this.getX()+1; tempY<8 && tempX<8 &&!somethingThere; tempY++, tempX++){
            moves(ButtonsInput);
        }
        somethingThere = false;

        for(tempY = this.getY()-1,tempX = this.getX()+1; tempY>-1 && tempX<8 &&!somethingThere; tempY--, tempX++){
            moves(ButtonsInput);
        }
        somethingThere = false;

        for(tempY = this.getY()-1,tempX = this.getX()-1; tempY>-1 && tempX>-1 &&!somethingThere; tempY--, tempX--){
            moves(ButtonsInput);
        }
        somethingThere = false;
        for(tempY = this.getY()+1,tempX = this.getX()-1 ; tempY<8 && tempX>-1 &&!somethingThere; tempY++, tempX--){
            moves(ButtonsInput);
        }
        somethingThere = false;
        for(tempY = this.getY()+1, tempX = getX(); tempY<8 &&!somethingThere; tempY++){
            moves(ButtonsInput);
        }
        somethingThere = false;
        for(tempY = this.getY()-1, tempX = getX(); tempY<8 &&!somethingThere; tempY--){
            moves(ButtonsInput);
        }
        somethingThere = false;
        for(tempY = this.getY(), tempX = getX()+1; tempX<8 &&!somethingThere; tempX++){
            moves(ButtonsInput);
        }
        somethingThere = false;
        for(tempY = this.getY(), tempX = getX()-1; tempX<8 &&!somethingThere; tempX--){
            moves(ButtonsInput);
        }
    }
}
