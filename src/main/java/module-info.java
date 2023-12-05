module com.number_guessing {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.number_guessing to javafx.fxml;
    exports com.number_guessing;
}
