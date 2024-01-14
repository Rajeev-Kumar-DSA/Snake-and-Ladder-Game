module org.example.snake_ladder_dsa {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.snake_ladder_dsa to javafx.fxml;
    exports org.example.snake_ladder_dsa;
}