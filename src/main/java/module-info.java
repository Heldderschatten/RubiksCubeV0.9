module org.example.rubikscubev09 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires com.google.gson;
    opens org.example.rubikscubev09.data to com.google.gson;

    opens org.example.rubikscubev09 to javafx.fxml;
    exports org.example.rubikscubev09;
}