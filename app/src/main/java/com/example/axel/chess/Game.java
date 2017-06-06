package com.example.axel.chess;

import java.io.Serializable;

/**
 * Created by Axel on 2017-06-04.
 */

public class Game implements Serializable{
    private static final long serialVersionUID = -29238982928391L;
    private Piece[][] allPieces;
    private String whosTurn;
    public Game() {
    allPieces = new Piece[8][8];
        whosTurn = "";
    }

    public void setWhosTurn(String input){whosTurn = input;}

    public String getWhosTurn(){
        return whosTurn;
    }

    public void switchPlayer(){
        if(whosTurn.equals(WhitePlayer)){
            whosTurn = BlackPlayer;
        }
        else{
            whosTurn = WhitePlayer;
        }
    }

    public Piece getPiece(int y, int x){
        return allPieces[y][x];
    }

    public Piece[][] getAllPieces(){
        return allPieces;
    }

    public void basicSetup(){
        //White pieces
        for (int lapCountX = 0; lapCountX < 8; lapCountX++) {
            if(lapCountX==0 || lapCountX==7){
                allPieces[7][lapCountX]= new Rook(R.drawable.wrook, true);
                allPieces[7][lapCountX].setPosition(7,lapCountX);
            }
            else if(lapCountX==1 || lapCountX==6){
                allPieces [7][lapCountX]= new Knight(R.drawable.wknight, true);
                allPieces[7][lapCountX].setPosition(7,lapCountX);
            }
            else if(lapCountX==2 || lapCountX==5){
                allPieces[7][lapCountX] = new Bishop(R.drawable.wbishop, true);
                allPieces[7][lapCountX].setPosition(7,lapCountX);
            }
            else if(lapCountX==3){
                allPieces[7][lapCountX] = new King(R.drawable.wking, true);
                allPieces[7][lapCountX].setPosition(7,lapCountX);
            }
            else if(lapCountX==4){
                allPieces [7][lapCountX]= new Queen(R.drawable.wqueen, true);
                allPieces[7][lapCountX].setPosition(7,lapCountX);
            }
        }

        //White pawns
        for (int lapCountX = 0; lapCountX < 8; lapCountX++){
            allPieces[6][lapCountX] = new Pawn(R.drawable.wpawn, true);
            allPieces[6][lapCountX].setPosition(6,lapCountX);
        }

        //Black pawns
        for (int lapCountX = 0; lapCountX < 8; lapCountX++){
            allPieces[1][lapCountX] = new Pawn(R.drawable.bpawn, false);
            allPieces[1][lapCountX].setPosition(1,lapCountX);
        }

        //Black Pieces
        for (int lapCountX = 0; lapCountX < 8; lapCountX++) {
            if(lapCountX==0 || lapCountX==7){
                allPieces[0][lapCountX] = new Rook(R.drawable.brook, false);
                allPieces[0][lapCountX].setPosition(0,lapCountX);
            }
            else if(lapCountX==1 || lapCountX==6){
                allPieces[0][lapCountX] = new Knight(R.drawable.bknight, false);
                allPieces[0][lapCountX].setPosition(0,lapCountX);
            }
            else if(lapCountX==2 || lapCountX==5){
                allPieces[0][lapCountX] = new Bishop(R.drawable.bbishop, false);
                allPieces[0][lapCountX].setPosition(0,lapCountX);
            }
            else if(lapCountX==3){
                allPieces[0][lapCountX] = new King(R.drawable.bking, false);
                allPieces[0][lapCountX].setPosition(0,lapCountX);
            }
            else if(lapCountX==4){
                allPieces[0][lapCountX] = new Queen(R.drawable.bqueen, false);
                allPieces[0][lapCountX].setPosition(0,lapCountX);
            }
        }
    }
    public void setAllPieces(Piece[][] piecesInput){
        allPieces = piecesInput;
    }

    public String getWhitePlayer() {
        return WhitePlayer;
    }

    public void setWhitePlayer(String whitePlayer) {
        WhitePlayer = whitePlayer;
    }

    private String WhitePlayer;

    public String getBlackPlayer() {
        return BlackPlayer;
    }

    public void setBlackPlayer(String blackPlayer) {
        BlackPlayer = blackPlayer;
    }

    private String BlackPlayer;
}
