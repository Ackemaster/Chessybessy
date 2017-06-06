package com.example.axel.chess;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Rook extends Piece implements Serializable {
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
            moves(PiecesInput, ButtonsInput, mainActivity);
        }
        somethingThere = false;
       for(tempY = this.getY()-1, tempX = getX(); tempY>-1 &&!somethingThere; tempY--){
           moves(PiecesInput, ButtonsInput, mainActivity);
        }
        somethingThere = false;
        for(tempY = this.getY(), tempX = getX()+1; tempX<8 &&!somethingThere; tempX++){
            moves(PiecesInput, ButtonsInput, mainActivity);
        }
        somethingThere = false;
        for(tempY = this.getY(), tempX = getX()-1; tempX>-1 &&!somethingThere; tempX--){
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
