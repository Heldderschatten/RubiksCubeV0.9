module org.example.rubikscubev09 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rubikscubev09 to javafx.fxml;
    exports org.example.rubikscubev09;
}