package org.example.rubikscubev09;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.rubikscubev09.data.Graph;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Cube_UI_Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileChooser fileChooser;

    @FXML
    private GridPane cubePane;
    @FXML
    private Button btBack;
    @FXML
    private Button btSelctCube;
    @FXML
    private Button btTest;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Cube_UI_Controller.initialize");
    }
    public void pressedBtTest(ActionEvent event){
        System.out.println("Cube_UI_Controller.pressedBtTest");
        cubePane.setGridLinesVisible(true);
        int i = cubePane.getRowCount()+1;
        cubePane.addRow(i,null);
    }
    public void pressed_btBack(ActionEvent event) throws IOException {
        System.out.println("Cube_UI_Controller.pressed_btBack");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cube schliesen");
        alert.setContentText("Sicher das du zurück zum Start willst?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void presed_SelectCube(ActionEvent event){
        System.out.println("Cube_UI_Controller.presed_SelectCube");
        fileChooser = new FileChooser();
        fileChooser.setTitle("SelectCube");
        File cubeFile = fileChooser.showOpenDialog(stage);
    }
    private void setCubePane(Graph graph){

    }
}
