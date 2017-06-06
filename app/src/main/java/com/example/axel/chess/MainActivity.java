package com.example.axel.chess;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.axel.chess.R.drawable.wrook;

public class MainActivity extends AppCompatActivity {
    HashMap<Integer, ImageButton>ButtonMap;
    Piece allPieces;
    RelativeLayout theLayout;
    MainActivity mainly;
    ArrayList<Game> myGames;
    Player thisPlayer;
    Game thisGame;
    Button tempButton;

    private void setLoginView(){
        setContentView(R.layout.main_menu);
        theLayout = (RelativeLayout) findViewById(R.id.mainlayout);
        EditText userName = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        tempButton = (Button)findViewById(R.id.button);
        userName.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tempButton.performClick();
                    return true;
                }
                return false;
            }
        });
    }


private void InitiateField(){
    for(int y = 0; y < 8; y++) {boolean black = true;
        if (y % 2 > 0) {
            black = false;
        }
        for (int x = 0; x< 8; x++) {
            ImageButton bt = new ImageButton(this);
            bt.setId(y * 8 + x);
            if (black) {
                bt.setImageResource(R.drawable.tom);
                black = false;
            } else {
                bt.setImageResource(R.drawable.tom);
                black = true;
            }
            bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            bt.setScaleType(ImageButton.ScaleType.FIT_XY);
            bt.setScaleX(0.9f);
            bt.setScaleY(0.9f);
            bt.setY(100+y * 120);
            bt.setX(x * 90);
            ButtonMap.put(y*8 + x, bt);
            theLayout.addView(bt);
        }
    }
}
    private void InitiatePieces(){
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                buttonCreator(y, x);
            }
        }
    }

    private void setToolbar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Wicked Chess");
        myToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMain();
            }});
    }

    private void getGames(View v){
        setContentView(R.layout.getgames);
        theLayout = (RelativeLayout) findViewById(R.id.gamesLayout);
        setToolbar();
        readGames();
        for(int i = 0; i<myGames.size(); i++) {
            Button bta = new Button(this);
            bta.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            bta.setX(400);
            bta.setY(i*100);
            final int finalI = i;
            bta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thisGame = myGames.get(finalI);
                }
            });
            theLayout.addView(bta);
        }
    }

     void setMain(){
        setContentView(R.layout.main_menu);
        theLayout = (RelativeLayout) findViewById(R.id.mainlayout);
        setToolbar();
        Button newGame = (Button)findViewById(R.id.button2);
        Button allGames = (Button)findViewById(R.id.button);
        allGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGames(v);
            }
    });
    }

    private void newGame(){
        setContentView(R.layout.newgame);
        theLayout = (RelativeLayout) findViewById(R.id.addlayout);
        setToolbar();
        ObjectInputStream input;
        String filename = "login.txt";
        int i = 0;
        try {
            while (true){
                input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(), "") + File.separator + filename)));
                Player tempPlayer = (Player) input.readObject();
                Button tempButton = (Button)findViewById(R.id.button);
                tempButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tempButton.setX(400);
                tempButton.setY(i*100);
                tempButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createNewGame();
                    }
                });
                theLayout.addView(tempButton);
                input.close();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private void createNewGame(){
        Game tempGame = new Game();
        writeGame(tempGame);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main);
        theLayout = (RelativeLayout) findViewById(R.id.linearLayout);
        //final Button button = (Button)findViewById(R.id.button3);
        //button.getBackground().setColorFilter(0x00000000, PorterDuff.Mode.MULTIPLY);
        //final Button button2 = (Button)findViewById(R.id.button2);
        //button2.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
       // ViewGroup linearLayout = (ViewGroup) findViewById(R.id.blackbutton);
        //ImageView rook = (ImageView)findViewById(R.id.rook_icon);
        //final ImageButton butt = new ImageButton(this);


        ButtonMap= new HashMap<Integer, ImageButton>();
       thisGame = new Game();
        thisGame.basicSetup();
        thisGame.setBlackPlayer("Else");
        thisGame.setWhitePlayer("Someone");
        thisPlayer = new Player("Someone", "Bla");
        writeGame(thisGame);

        Initiate(theLayout);
    }

    public void Initiate(View v){
        setContentView(R.layout.activity_main);
        theLayout = (RelativeLayout) findViewById(R.id.linearLayout);
        setToolbar();
        InitiateField();
        InitiatePieces();
        setDefaultColor();
    }
    public void setDefaultColor(){
        for(int i = 0; i<8; i++) {
            boolean black = true;
            if (i % 2 > 0) {
                black = false;
            }
            for (int x = 0; x< 8; x++) {
                if (black) {
                    ButtonMap.get(i * 8 + x).setBackgroundColor(Color.WHITE);
                    black = false;
                } else {
                    ButtonMap.get(i * 8 + x).setBackgroundColor(Color.DKGRAY);
                    black = true;
                }
                final int tempfinalY = i;
                final int tempFinalX = x;
                final MainActivity tempAct = this;
                if (getThisGame().getWhosTurn().equals(getPlayer().getUsername())) {
                    ButtonMap.get(i * 8 + x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDefaultColor();
                            try {
                                if (thisGame.getWhosTurn().equals(thisGame.getWhitePlayer()) == thisGame.getPiece(tempfinalY, tempFinalX).getFriendly()){
                                    thisGame.getPiece(tempfinalY, tempFinalX).getPossibleMoves(thisGame.getAllPieces(), ButtonMap, tempAct);
                                }
                            } catch (NullPointerException e) {

                            }
                        }
                    });
                }else{
                    ButtonMap.get(i * 8 + x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setDefaultColor();
                        }
                    });
                }
            }
        }
    }

    public void moveDone(){

    }

    public void buttonCreator(int y, int x){
        ImageButton bt = new ImageButton(this);

        bt.setId(y*8 + x);
        try {
            bt.setImageResource(thisGame.getPiece(y, x).getMotive());
        }catch(NullPointerException e){
            bt.setImageResource(R.drawable.tom);
        }
        bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        bt.setScaleType(ImageButton.ScaleType.FIT_XY);
        bt.setScaleX(0.9f);
        bt.setScaleY(0.9f);
        bt.setY(100+y*120);
        bt.setX(x*90);
        final int finalX = x;
        final int finalY = y;
        final MainActivity tempAct = this;
        if(getThisGame().getWhosTurn().equals(getPlayer().getUsername())) {
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDefaultColor();
                    if (thisPlayer.getUsername().equals(thisGame.getWhitePlayer()) == thisGame.getPiece(finalY, finalX).getFriendly()) {
                        thisGame.getPiece(finalY, finalX).getPossibleMoves(thisGame.getAllPieces(), ButtonMap, tempAct);
                    }
                }
            });
        }
        else{
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDefaultColor();
                }
            });
        }
        ButtonMap.put((8*y)+x,bt);
        theLayout.addView(bt);
    }
    public void playerLogin(String Username, String Password){
        ObjectInputStream input;
        String filename = "login.txt";

        try {
            while (true){
                input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(), "") + File.separator + filename)));
            Player tempPlayer = (Player) input.readObject();
                if (tempPlayer.getUsername().equals(Username) && tempPlayer.getPassword().equals(Password)){
                    thisPlayer = tempPlayer;
                    break;
                }
            input.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    public void writeLogin(){
        String filename = "login.txt";
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
            out.writeObject(thisPlayer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game getThisGame(){
        return thisGame;
    }

    public Player getPlayer(){
        return thisPlayer;
    }

    public void writeGame(Game tempGame){
        tempGame.switchPlayer();
        String filename = "game.txt";
        Game leGame = new Game();
        leGame.setAllPieces(tempGame.getAllPieces());
        leGame.setWhitePlayer(tempGame.getWhitePlayer());
        leGame.setBlackPlayer(tempGame.getBlackPlayer());
        leGame.setWhosTurn(tempGame.getWhosTurn());
        try {
            File f = new File("C:/Users/Axel/AndroidStudioProjects/Chess/Chessybessy/app/src/main/java/com/example/axel/chess/game.txt");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream out = new ObjectOutputStream(fos);
            String hw = "Hello World!";
            out.writeObject(leGame);
            out.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void readGames(){
        myGames = new ArrayList<Game>();
        try {

        File filename = new File("game.txt");
        FileInputStream fos = new FileInputStream(filename);
            ObjectInputStream input = new ObjectInputStream(fos);

            for(;;){
                myGames.add(myGames.size(), (Game)input.readObject());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
