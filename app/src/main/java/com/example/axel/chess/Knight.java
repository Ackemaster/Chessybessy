package com.example.axel.chess;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Axel on 2017-05-24.
 */

public class Knight extends Piece implements Serializable {
    private HashMap<Integer, ImageButton> tempButtons;
    private Piece thisPiece;
    private int tempY;
    private int tempX;
    private boolean hasMoved;
    private Piece tempPieces[][];
    private boolean somethingThere;
    public Knight(int motive, boolean friendly){
        super(motive, friendly);
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity){
        tempButtons = ButtonsInput;
        tempPieces = PiecesInput;
        somethingThere = false;

        if(outOfBoundsX(1, this) && outOfBoundsY(2, this)){
            tempY = getY()+2;
            tempX = getX()+1;
            moves(PiecesInput, ButtonsInput, mainActivity);
        }

        if(outOfBoundsX(1, this) && outOfBoundsY(-2, this)){
            tempY = getY()-2;
            tempX = getX()+1;
            moves(PiecesInput, ButtonsInput, mainActivity);
        }

        if(outOfBoundsX(-1, this) && outOfBoundsY(2, this)){
            tempY = getY()+2;
            tempX = getX()-1;
            moves(PiecesInput, ButtonsInput, mainActivity);
        }

        if(outOfBoundsX(-1, this) && outOfBoundsY(-2, this)){
            tempY = getY()-2;
            tempX = getX()-1;
            moves(PiecesInput, ButtonsInput, mainActivity);
        }

        if(outOfBoundsX(2, this) && outOfBoundsY(1, this)){
            tempY = getY()+1;
            tempX = getX()+2;
            moves(PiecesInput, ButtonsInput, mainActivity);
        }

        if(outOfBoundsX(-2, this) && outOfBoundsY(1, this)){
            tempY = getY()+1;
            tempX = getX()-2;
            moves(PiecesInput, ButtonsInput, mainActivity);
        }

        if(outOfBoundsX(-2, this) && outOfBoundsY(-1, this)){
            tempY = getY()-1;
            tempX = getX()-2;
            moves(PiecesInput, ButtonsInput, mainActivity);
        }

        if(outOfBoundsX(2, this) && outOfBoundsY(-1, this)){
            tempY = getY()-1;
            tempX = getX()+2;
            moves(PiecesInput, ButtonsInput, mainActivity);
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
}
