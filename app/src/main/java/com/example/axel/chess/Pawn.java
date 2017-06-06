package com.example.axel.chess;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Pawn extends Piece implements Serializable {
    private HashMap<Integer, ImageButton> tempButtons;
    private int tempY;
    private int tempX;
    private boolean hasMoved;
    private Piece tempPieces[][];
    private boolean somethingThere;
    private Pawn thisPiece;
    private int friendlyMultiplier;
    public Pawn(int motive, boolean friendly){
        super(motive, friendly);
        hasMoved = false;
        friendlyMultiplier = 1;
        if(getFriendly()){
            friendlyMultiplier = -1;
        }
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity){
        somethingThere = false;
        tempY = getY();
        tempX = getX();

        if(outOfBoundsY(1*friendlyMultiplier, this))
        {
            tempY = getY() + 1*friendlyMultiplier;
            moves(PiecesInput, ButtonsInput, mainActivity);
            if(outOfBoundsX(1, this))
            {
                tempX = getX() + 1;
                if(PiecesInput[tempY][tempX]!=null) {
                    if (PiecesInput[tempY][tempX].getFriendly() != getFriendly()) {
                        moves(PiecesInput, ButtonsInput, mainActivity);
                    }
                }
            }
            if(outOfBoundsX(-1, this))
            {
                if(PiecesInput[tempY][tempX]!=null) {
                tempX = getX() - 1;
                if (PiecesInput[tempY][tempX].getFriendly() != getFriendly())
                {
                    moves(PiecesInput,ButtonsInput, mainActivity);
                }
            }
            }
        }
        if (!hasMoved && outOfBoundsY(2*friendlyMultiplier, this))
        {
            tempX = getX();
            tempY = getY()+2*friendlyMultiplier;
            moves(PiecesInput,ButtonsInput, mainActivity);
        }

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
                    hasMoved = true;
                    tempButtons.get(thisPiece.getY() * 8 + thisPiece.getX()).setImageResource(R.drawable.tom);
                    tempButtons.get(insideTempY*8 + insideTempX).setImageResource(thisPiece.getMotive());
                    tempPieces[thisPiece.getY()][thisPiece.getX()] = null;
                    tempPieces[insideTempY][insideTempX] = thisPiece;
                    restore(tempPieces, tempButtons, tempAct);
                    tempAct.getThisGame().setAllPieces(tempPieces);
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
                    hasMoved = true;
                    tempButtons.get(thisPiece.getY() * 8 + thisPiece.getX()).setImageResource(R.drawable.tom);
                    tempButtons.get(insideTempY*8 + insideTempX).setImageResource(thisPiece.getMotive());
                    tempPieces[thisPiece.getY()][thisPiece.getX()] = null;
                    tempPieces[insideTempY][insideTempX] = thisPiece;
                    restore(tempPieces, tempButtons, tempAct);
                    tempAct.getThisGame().setAllPieces(tempPieces);
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
}
