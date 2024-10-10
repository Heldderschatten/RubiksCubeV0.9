package org.example.rubikscubev09;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private String[] availableStrings = {"2*2", "Cube"};
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button bt_generate;
    @FXML
    private ChoiceBox<String> cb_CubeSelectBox;


    @FXML
    public void pressed_bt_generate(ActionEvent event) throws IOException {
        System.out.println("HelloController.bt_generate");
        String s = cb_CubeSelectBox.getValue();
        System.out.println("Select: " + s);
        if (s == null) {
            System.out.println("No input");
        } else if (s.equals(availableStrings[0])) {
            root = FXMLLoader.load(getClass().getResource("2by2.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (s.equals(availableStrings[1])) {
            root = FXMLLoader.load(getClass().getResource("Cube.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Wrong input");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("HelloController.initialize");
        cb_CubeSelectBox.getItems().addAll(availableStrings);

    }
}