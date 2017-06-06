package com.example.axel.chess;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Axel on 2017-05-24.
 */

public class Bishop extends Piece implements Serializable {
    private HashMap<Integer, ImageButton> tempButtons;
    private Piece thisPiece;
    private int tempY;
    private int tempX;
    private boolean hasMoved;
    private Piece tempPieces[][];
    private boolean somethingThere;
    public Bishop(int motive, boolean friendly){
        super(motive, friendly);
    }
    public void getPossibleMoves(Piece[][]PiecesInput, HashMap<Integer, ImageButton> ButtonsInput, MainActivity mainActivity){
        tempButtons = ButtonsInput;
        tempPieces = PiecesInput;
        somethingThere = false;

        for(tempY = this.getY()+1,tempX = this.getX()+1; tempY<8 && tempX<8 &&!somethingThere; tempY++, tempX++){
            moves(PiecesInput, ButtonsInput, mainActivity);

        }
        somethingThere = false;

        for(tempY = this.getY()-1,tempX = this.getX()+1; tempY>-1 && tempX<8 &&!somethingThere; tempY--, tempX++){
            moves(PiecesInput, ButtonsInput, mainActivity);
        }
        somethingThere = false;

        for(tempY = this.getY()-1,tempX = this.getX()-1; tempY>-1 && tempX>-1 &&!somethingThere; tempY--, tempX--){
            moves(PiecesInput, ButtonsInput, mainActivity);
        }
        somethingThere = false;
        for(tempY = this.getY()+1,tempX = this.getX()-1 ; tempY<8 && tempX>-1 &&!somethingThere; tempY++, tempX--){
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
