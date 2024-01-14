package org.example.snake_ladder_dsa;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    // here I will store board cordinate
    // need array
    ArrayList<Pair<Integer, Integer>> positionCoordinates;  // store x and y cordinate
    // I will skip 0,0 =>

    // map to store the ladder and snake in 1D array ->
    ArrayList<Integer> snakeLadderPosition;

    // make a constructor for board class
    public Board(){
        positionCoordinates = new ArrayList<>();   // use 1D array for mapping the co-ordinate
        // call the populated method here
        populatePositionCoordinate();

        // call snake and ladder method
        populateSnakeLadder();
    }

    // put the value
    private void populatePositionCoordinate(){
        // dummy value
        positionCoordinates.add(new Pair<>(0, 0));

        // using loop -> height and width
        for(int i=0; i<SnakeLadder.height; i++){
            for(int j=0; j<SnakeLadder.width; j++){
                // now need to generate x and y co-ordinate
                // X - Coordinates
                int xCoordinate = 0;
                // for even of ith row -> xCoordinate will increase
                if(i%2 == 0){
                    // x will increase
                    xCoordinate = j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }
                // for odd of ith row -> xCoordinate will decrease (because my grid value is arranged in decrease formate
                else{
                    xCoordinate = SnakeLadder.tileSize * SnakeLadder.height - (j*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                }

                // Y - Coordinates -> decrease the height
                // - SnakeLadder.tileSize * SnakeLadder.height = 400 (go to snakeLadder class)
                // for i=0, j=0;  means for grid 1 coordinate
                // for i=0, j=1; means for grid 2 coordinate
                int yCoordinate = SnakeLadder.tileSize * SnakeLadder.height - (i*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                positionCoordinates.add(new Pair<>(xCoordinate, yCoordinate));
            }
        }
    }


    /// populate snake and ladder
    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();

        // add all number in list
        for(int i=0; i<101; i++){
            snakeLadderPosition.add(i);
        }

        // now I will set all position for snake and ladder position in list
        // -- Ladder
        snakeLadderPosition.set(4, 25);   // ladder : 4 to go 25 grid
        snakeLadderPosition.set(13, 46);
        snakeLadderPosition.set(33, 49);
        snakeLadderPosition.set(42, 63);
        snakeLadderPosition.set(50, 69);
        snakeLadderPosition.set(62, 81);
        snakeLadderPosition.set(74, 92);

        // snake
        snakeLadderPosition.set(27, 5);   // snake : 27 to come back 5
        snakeLadderPosition.set(40, 3);
        snakeLadderPosition.set(43, 18);
        snakeLadderPosition.set(54, 31);
        snakeLadderPosition.set(66, 45);
        snakeLadderPosition.set(76, 58);
        snakeLadderPosition.set(89, 53);
        snakeLadderPosition.set(99, 41);

    }


    // for currentPosition snake and ladder is present or not
    public int getNewPosition(int currentPosition){
        // it is possible for current snake , ladder , or nothing is present
        if(currentPosition>0 && currentPosition<=100){
            return snakeLadderPosition.get(currentPosition);
        }

        return -1;
    }

    //  for finding the currentPosition of player
    // getter method for xCordinate
    int getXCoordinate(int position){
        // position should greater 0 and less 100
        if (position >= 1 && position <= 100) {
            return  positionCoordinates.get(position).getKey();
        }
        return -1;  // when will outofbound
    }

    // getter method for yCordinate
    int getYCoordinate(int position){
        // position should greater 0 and less 100
        if (position >= 1 && position <= 100) {
            return  positionCoordinates.get(position).getValue();
        }
        return -1;  // when will outofbound
    }


//    // make main method -> for testing purpose
//    public static void main(String[] args) {
//        // create object for board class
//        Board board = new Board();
//
//        // i need to print the position
//        for(int i=0; i<board.positionCoordinates.size(); i++){
//            System.out.println(i + " $   x :" + board.positionCoordinates.get(i).getKey() +
//                    "     y :" + board.positionCoordinates.get(i).getValue());
//        }
//    }

}
