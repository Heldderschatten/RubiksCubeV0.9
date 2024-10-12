package org.example.rubikscubev09;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.Node;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import org.example.rubikscubev09.Files.CubeToJsonConverter;

import org.example.rubikscubev09.cubes.Controller_Cube_2by2;
import org.example.rubikscubev09.data.Cube;
import org.example.rubikscubev09.data.Graph;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class Cube_UI_Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileChooser fileChooser;
    private Cube cube;
    private Button[] moveButtons;

    @FXML
    private GridPane cubePane;
    @FXML
    private Button btBack;
    @FXML
    private Button btSelctCube;
    @FXML
    private Button btTest;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Group group;
    @FXML
    private VBox buttonBox;
    @FXML
    private Label solvedLabel;
    @FXML
    private TextArea lastMoves;

    @FXML
    public void initialize(){
        System.out.println("Cube_UI_Controller.initialize(@FXML)");
        //scene = cubePane.getScene();
        //scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleGlobalKeyPress);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Cube_UI_Controller.initialize");
        setNewCube();
    }
    private void handleGlobalKeyPress(KeyEvent event) {
        // Hier kannst du globale Tasteneingaben verarbeiten
        System.out.println("Globale Taste gedrückt: " + event.getCode());
    }
    public void setGridSize(int n) {
        // Entferne alle existierenden Elemente aus der GridPane
        cubePane.getChildren().clear();

        // Entferne alte Spalten- und Zeilenbeschränkungen
        cubePane.getColumnConstraints().clear();
        cubePane.getRowConstraints().clear();

        // Definiere die Größe der Spalten und Zeilen
        for (int i = 0; i < n; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / n); // Gleichmäßige Verteilung der Spalten
            cubePane.getColumnConstraints().add(col);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / n); // Gleichmäßige Verteilung der Zeilen
            cubePane.getRowConstraints().add(row);
        }
    }

    public void pressedSelectCube(ActionEvent event) {
        System.out.println("Cube_UI_Controller.pressedSelectCube");
        setNewCube();
    }
    private void setNewCube(){
        System.out.println("Cube_UI_Controller.setNewCube");
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File file = fileChooser.showOpenDialog(stage);
        String filepath = file.getPath();
        cube = CubeToJsonConverter.readGCubeFromJson(filepath);
        addMoveButtons();
        updatecubePane();
        updateMoveslist();
    }
    public void safeCube(){
        DirectoryChooser chooser =  new DirectoryChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        TextInputDialog dilaog = new TextInputDialog();
        Optional<String> safeNameopt = dilaog.showAndWait();
        File file = chooser.showDialog(stage);
        String safeName = safeNameopt.get();
        String filePath = file.getPath() + System.getProperty("file.separator") + safeNameopt.get()+".json";
        CubeToJsonConverter.writeCubeToJson(cube,filePath);

    }

    private void addMoveButtons() {
        System.out.println("Cube_UI_Controller.addMoveButtons");
        Set<String> stringsMoveButtons = cube.getPossibleMovesStringToInt().keySet();
        buttonBox.getChildren().clear();
        moveButtons = new Button[stringsMoveButtons.size()];
        int counter = 0;
        for (String s : stringsMoveButtons) {
            moveButtons[counter] = new Button(s);
            moveButtons[counter].setOnAction(event -> pressedAMoveButton(event));
            counter++;
        }
        buttonBox.getChildren().addAll(moveButtons);

    }

    public void pressedAMoveButton(ActionEvent event) {
        System.out.println("Cube_UI_Controller.pressedAMoveButton");
        System.out.println(((Button) event.getSource()).getText());
        cube.doStep(((Button) event.getSource()).getText());
        updatecubePane();
        updateMoveslist();
    }
    private void initMoveList(){

    }
    private void updateMoveslist(){
        lastMoves.clear();
        lastMoves.setText(cube.getDidSteps().toString());

    }
    public void updatecubePane() {
        setGridSize((cube.getSize() * 2) + 1);
        for (org.example.rubikscubev09.data.Node node : cube.getWorkingGraph().getNodes()) {
            Label label = new Label(node.getPoint().getName());
            label.setTextFill(node.getPoint().getColor());
            label.setFont(Font.font("Arial",25));
            cubePane.add(label, node.getPostion()[0], node.getPostion()[1]);
        }
        solvedLabel.setVisible(cube.checkCubeSolved());
    }

    public void pressed_btBack(ActionEvent event) throws IOException {
        System.out.println("Cube_UI_Controller.pressed_btBack");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cube schliesen");
        alert.setContentText("Sicher das du zurück zum Start willst?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void setCubePane(Graph graph) {

    }
}
