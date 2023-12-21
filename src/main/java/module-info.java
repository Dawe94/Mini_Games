module com.number_guessing {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mini_games to javafx.fxml;
    exports com.mini_games;
}
