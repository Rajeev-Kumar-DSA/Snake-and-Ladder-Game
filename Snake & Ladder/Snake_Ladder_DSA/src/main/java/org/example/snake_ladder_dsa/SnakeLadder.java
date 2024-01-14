package org.example.snake_ladder_dsa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize = 40, width = 10, height = 10;
    public static final int buttonLine = height*tileSize+50,  infoLine = buttonLine-30;  // this line will help me keep go the button in lower side (y-direction)

    // call Dice class
    private static Dice dice;
    private ImageView diceImageView;
    private Label diceLable;
    private Player playerOne, playerTwo;  /// call the player class

    /// now i will take some variable which help me to take decision, which player will roll the dice. game started or not
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;



    // creating own method -> here will present my all UI control
    private Pane createContent(){
        Pane root = new Pane();

        // set the window size -
        root.setPrefSize(width*tileSize, height*tileSize + 200);
        root.setStyle("-fx-background-color: #96ffa1;"); // Set the background color to a light gray

        // create an instance of Dice --
        dice = new Dice();

        diceImageView = new ImageView();
        diceImageView.setFitHeight(50);
        diceImageView.setFitWidth(50);
        diceImageView.setTranslateY(height*tileSize + 100);
        diceImageView.setTranslateX(width*tileSize/2.0 - 25);

        diceLable = new Label("");
        diceLable.setTranslateY(infoLine);
        diceLable.setTranslateX(160);



        /// create tile here --
//        Tile tile = new Tile(tileSize);
//        // set the tile position -> (X, Y) position
//        tile.setTranslateX(30);
//        tile.setTranslateY(40);
//        root.getChildren().addAll(tile);  /// help of this one tile will created in window screen


        /// here Using DSA code for make 100 tile
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Tile tile = new Tile(tileSize);
                // set the tile position -> (X, Y) position
//                tile.setTranslateX(j*width);
//                tile.setTranslateY(i*height);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        // add image with same size of tile
        // here I will keep image path
        // -> this way pass the path -->
        Image img = new Image("file:/D:/Minor Project Acciojob/Snake & Ladder/Snake_Ladder_DSA/src/main/snakeLadder.jpg");
        // how display image on UI screen
        ImageView board = new ImageView();
        board.setImage(img);   // set the image
        board.setFitHeight(height*tileSize);  // image set the height same of tile size
        board.setFitWidth(width*tileSize);
        // viewing the image on UI -> add on root
//        root.getChildren().add(board);


        //// --- Create Button
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("START");

        // make the button in circular formate :-  Modify button styling to make them circular
        playerOneButton.setStyle("-fx-background-radius: 15; -fx-background-color: #FF0000; -fx-font-weight: bold; -fx-text-fill: white;");
        playerTwoButton.setStyle("-fx-background-radius: 15; -fx-background-color: Blue; -fx-font-weight: bold; -fx-text-fill: white;");
        startButton.setStyle("-fx-background-radius: 15;");

        // now I will add button in Pane window  -> setting the position where want to show
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);  /// when game will open this time button will be disable

        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);

        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(160);

        ///Info display - make three label -> where will show which player will throw the dice
        Label playerOneLable = new Label("");
        Label playerTwoLable = new Label("");
        Label diceLable = new Label("Start the Game");

        //set the position for each lebel
        playerOneLable.setTranslateY(infoLine);
        playerOneLable.setTranslateX(20);

        playerTwoLable.setTranslateY(infoLine);
        playerTwoLable.setTranslateX(300);

        diceLable.setTranslateY(infoLine);
        diceLable.setTranslateX(160);

        // need to add player
        playerOne = new Player(tileSize, Color.RED, "Rajeev");
        playerTwo = new Player(tileSize-5, Color.BLUE, "Raushan");

        /// Player move action --
        // when click the player button - then my coin should move
        // for playerOne -> trigger the event which is setOnAction -> after that some auto generate the code
        //
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                // when player button will hit then
//                // first -> Roll the dice
//                int diceValue = dice.getRolledDiceValue();
//
//                // second -> Display the dice value in
//                diceLable.setText("Dice Value : " + diceValue);
//
//                // third -> move the coin of player one
//                playerOne.movePlayer(diceValue);

                // initially playerOne will play when game will started -> also playerOneTurn should be true
                if(gameStarted){
                    if(playerOneTurn){
                        /// --- playing the game
                        int diceValue = dice.getRolledDiceValue();
                        diceLable.setText("Dice Value : " + diceValue);
                        rollDice();
                        playerOne.movePlayer(diceValue);

                        // need to check the Winning -> playerOne is Win or not ......
                        if(playerOne.isWinner()){
                            // announcement
                            diceLable.setText("Winner is " + playerOne.getName());

                            // change the statement
                            playerTwoTurn = false;   // here flase the enability
                            playerTwoButton.setDisable(true);  // disable the playerTwo button
                            playerTwoLable.setText("Game has been Over");

                            // Enabling the PlayerOne -  switch the commond for player One
                            playerOneTurn = false;  // switch for playerTwo
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("Game has been Over");

                            /// after anoucement -> enable to startButton
                            startButton.setDisable(false);
                            startButton.setText("RESTART");
                            gameStarted = false;   // again start the game fresh
                        }else{
                            // Disabling the option for playerOne
                            // after moving the coin playerOne turn has been completed
                            // now controll will switched for player2
                            playerOneTurn = false;   // here flase the enability
                            playerOneButton.setDisable(true);  // disable the playerOne button
                            playerOneLable.setText("");

                            // Enabling the PlayerTwo -  switch the commond for player two
                            playerTwoTurn = true;  // switch for playerTwo
                            playerTwoButton.setDisable(false);
                            playerTwoLable.setText("Your Turn " + playerTwo.getName());
                        }
                    }

                }
            }
        });


        // make a event for player2
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // initially playerOne will play when game will started -> also playerOneTurn should be true
                if(gameStarted){
                    if(playerTwoTurn){
                        /// --- playing the game
                        int diceValue = dice.getRolledDiceValue();
                        diceLable.setText("Dice Value : " + diceValue);
                        rollDice();
                        playerTwo.movePlayer(diceValue);

                        // need to check the Winning -> playerTwo is Wining or not ......
                        // when game will win -> rest game will stop
                        if(playerTwo.isWinner()){
                            // announcement
                            diceLable.setText("Winner is " + playerTwo.getName());

                            // change the statement
                            playerTwoTurn = false;   // here flase the enability
                            playerTwoButton.setDisable(true);  // disable the playerTwo button
                            playerTwoLable.setText("Game has been Over");

                            // Enabling the PlayerOne -  switch the commond for player One
                            playerOneTurn = false;  // switch for playerTwo
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("Game has been Over");

                            /// after anoucement -> enable to startButton
                            startButton.setDisable(false);
                            startButton.setText("RESTART");
                            gameStarted = false;   // again start the game fresh
                        }else{
                            // Disabling the option for playerTwo
                            // after moving the coin PlayerTwo, turn has been completed
                            // now controll will switched for playerOne
                            playerTwoTurn = false;   // here flase the enability
                            playerTwoButton.setDisable(true);  // disable the playerTwo button
                            playerTwoLable.setText("");

                            // Enabling the PlayerOne -  switch the commond for player One
                            playerOneTurn = true;  // switch for playerTwo
                            playerOneButton.setDisable(false);
                            playerOneLable.setText("Your Turn " + playerOne.getName());
                        }
                    }

                }
            }
        });

        // event for startButton
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // when click the startbutton then game should start
                gameStarted = true;

                // keep the status
                diceLable.setText("Game has Started");
                // when game started then need to disable the startButton
                startButton.setDisable(true);  // can't click again after start game

                // my plan -> then exit button will be enable (waiting............)

                // give the message which player will start the game
                playerOneTurn = true;
                playerOneLable.setText("Your Turn " + playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();  // when game will restart

                playerTwoTurn = false;   // untill playerOne will not roll the dice till then playerTwo will wait
                playerTwoLable.setText("");   // till then text will none
                playerTwoButton.setDisable(true);  // after click start button player one will roll dice first till then playerTwo button will be disabled
                playerTwo.startingPosition();  // when game will restart


            }
        });



        // -> put in pane all button and image, player of coin
        root.getChildren().addAll(
                board,
                playerOneButton, playerTwoButton, startButton,
                playerOneLable, playerTwoLable, diceLable,
                playerOne.getCoin(), playerTwo.getCoin(),
                diceImageView
        );

        return root;
    }

    private void rollDice(){
//        int diceValue = dice.getRolledDiceValue();
//        diceLable.setText("Dice Value : " + diceValue);
//        diceImageView.setImage(dice.getDiceImage(diceValue));
        diceImageView.setImage(dice.getDiceImage(dice.getLastRolledDiceValue()));
    }


    @Override
    public void start(Stage stage) throws IOException {
//        // -> this line of code is help me to connect with HTML code
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // for this project I will prefer code method -> no need html connection line
        // here I call my own created method which is Pane
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);   // display the window
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}