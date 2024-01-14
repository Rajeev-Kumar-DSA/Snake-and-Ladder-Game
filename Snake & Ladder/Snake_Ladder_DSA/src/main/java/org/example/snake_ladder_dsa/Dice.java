package org.example.snake_ladder_dsa;

import javafx.scene.image.Image;

public class Dice {

    // Variable to store the last generated random value
    private int lastRolledDiceValue;   /// this will help me to current dice value
    /// -- add dice image
    private Image[] diceImages;

    // add all image in array
    public Dice(){
        lastRolledDiceValue = 0;
        diceImages = new Image[6];
        for(int i=0; i<6; i++){
            String imagePath = "file:/D:/Minor Project Acciojob/Snake & Ladder/Snake_Ladder_DSA/src/main/dice"+(i+1)+".png";
            diceImages[i] = new Image(imagePath);  // add path in array according to index
        }
    }

    // generate random number (1 to 6)
    public int getRolledDiceValue(){
//        int diceValue = (int)(Math.random()*6 + 1);  /// help of this method we can able to generate random number 1 to 6
        lastRolledDiceValue = (int)(Math.random()*6 + 1);
        System.out.println("Generated Dice Random value : " + lastRolledDiceValue);
        return lastRolledDiceValue;
    }

    // get the dice image based on the rolled value
    public Image getDiceImage(int diceValue){
        // Use the provided random value without subtracting 1
        String imagePath = "file:/D:/Minor Project Acciojob/Snake & Ladder/Snake_Ladder_DSA/src/main/dice" + diceValue + ".png";
        System.out.println("Image Path: " + imagePath);
        return new Image(imagePath);
    }

    public int getLastRolledDiceValue() {
        return lastRolledDiceValue;
    }

}
