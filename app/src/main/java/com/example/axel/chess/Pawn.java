package com.example.axel.chess;


import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

public class Pawn extends Piece {
    private HashMap<Integer, ImageButton> tempButtons;
    private int tempY;
    private int tempX;
    private boolean hasMoved;
    private Piece tempPieces[][];
    private boolean somethingThere;
    public Pawn(int motive){
        super(motive, true);
        hasMoved = false;
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity){
        tempButtons = ButtonsInput;
        tempPieces = PiecesInput;
        somethingThere = false;
        tempY = getY();
        tempX = getX();
        if(outOfBoundsY(1)) {
            tempY = getY() + 1;
            moves(ButtonsInput);
        }
        if(outOfBoundsX(1)) {
        tempX = getX() + 1;
        if (PiecesInput[tempY][tempX].getFriendly() != getFriendly()){
            moves(ButtonsInput);
        }}
        if(outOfBoundsX(-1)) {
            tempX = getX() - 1;
            if (PiecesInput[tempY][tempX].getFriendly() != getFriendly()){
                moves(ButtonsInput);
            }}
        tempX = getX();
        if (!hasMoved && outOfBoundsY(2)){
            tempY = getY()+2;
            moves(ButtonsInput);
            hasMoved = true;
        }

    }
}
