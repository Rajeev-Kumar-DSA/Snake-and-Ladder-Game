package org.example.snake_ladder_dsa;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    // here need coin
    private Circle coin;   // coin will present in circle formate
    private int currentPosition;
    private String name;

    private static Board gameBoard = new Board();


    // make a constructor
    public Player(int tileSize, Color coinColor, String playerName){
        // make circle -> pass the parmater
        coin = new Circle(tileSize/2);  /// passing the radius of circle
        coin.setFill(coinColor);
        // initially coin position -> we can keep the coin anywhere in board
        // currently I kept it on 1st tile
        currentPosition = 0;  // we can set any position -> current I keep 1, we can keep out of board
        movePlayer(1);   /// calling the player move
        name = playerName;
    }

    public void movePlayer(int diceValue){
        // how player will be move
        // when value will less or equal to 100 then coin will move otherwise stand will same position
        if(currentPosition + diceValue <= 100){
            currentPosition += diceValue;  // increase my position value
            // inside this method coin will move
            // make returnType
            TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);  // call my animation


            // ------ I mapped all snake and ladder -- now need to keep the coin accordingly when snake and ladder will come
            // check snake and ladder is present or not
            int newPosition = gameBoard.getNewPosition(currentPosition);
            // check
            if(newPosition != currentPosition && newPosition != -1){
                // now need to make a move a coin -> means snake or ladder is present for currentPosition
                currentPosition = newPosition;
                secondMove = translateAnimation(6); /// animate hoga 200*6 ke multiple me milisecond ke liye
            }

            if(secondMove == null){
                firstMove.play();
            }else{
//                firstMove.play();
//                secondMove.play();
                // play then take some pause then agin play for second
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(300)), secondMove);
                sequentialTransition.play();
            }
        }

        // ---- here animated method no need to write this logic
//        // move player location  -> go to board class -> first I will find what is my current location then add dice value then move the coin
//        // after making getting x and y coordinate in board class
//        // here I will call coordinate
//        int x = gameBoard.getXCoordinate(currentPosition);
//        int y = gameBoard.getYCoordinate(currentPosition);   /// getting y coordinate
//
//        // move the coin location in board   => for coin I will pass the location
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }


//    // adding animation ---  currently - for any dice value taking 1000 milisecond
//    private void translateAnimation(){
//        // coin will move slow - slow
//        // pass here time in milisecond
//        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), coin);
//        // where want to go -> which place coin will go  -> take position
//        animate.setToX(gameBoard.getXCoordinate(currentPosition));
//        animate.setToY(gameBoard.getYCoordinate(currentPosition));
//
//        animate.setAutoReverse(false);
//        // play the animation
//        animate.play();
//    }

    // --- i will seperate the animation according to dice value
//    private void translateAnimation(int diceValue){
//        // coin will move slow - slow
//        // pass here time in milisecond
//        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue), coin);
//        // where want to go -> which place coin will go  -> take position
//        animate.setToX(gameBoard.getXCoordinate(currentPosition));
//        animate.setToY(gameBoard.getYCoordinate(currentPosition));
//
//        animate.setAutoReverse(false);
//        // play the animation
//        animate.play();
//    }


    // when any ladder or snake will be come first reach that grid then jump for snake and ladder
    // here movement will sequencly
    private TranslateTransition translateAnimation(int diceValue){
        // coin will move slow - slow
        // pass here time in milisecond
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue), coin);
        // where want to go -> which place coin will go  -> take position
        animate.setToX(gameBoard.getXCoordinate(currentPosition));
        animate.setToY(gameBoard.getYCoordinate(currentPosition));

        animate.setAutoReverse(false);
        // play the animation
        return animate;
    }


    //--for winning player name
    boolean isWinner(){
        if(currentPosition == 100){
            return true;
        }
        return false;
    }


    // bring the both coin on starting position -> this method will when game will restart
    public void startingPosition(){
        currentPosition = 0;
        movePlayer(1);

    }

    // make getter -> right click generator -> select Getter for circle, position, name
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
