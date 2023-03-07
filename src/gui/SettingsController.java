/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tamim
 */
public class SettingsController implements Initializable {

    @FXML
    private RadioButton Radio_Light;
    @FXML
    private RadioButton Radio_Dark;

    private String urlTheme = getClass().getResource("../css/DarkTheme.css").toExternalForm();
    @FXML
    private AnchorPane full_body;
    final ToggleGroup group = new ToggleGroup();
    final ToggleGroup group1 = new ToggleGroup();
    @FXML
    private RadioButton Radio_Fullscreen;
    @FXML
    private RadioButton Radio_normal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Radio_Dark.setToggleGroup(group);
        Radio_Light.setToggleGroup(group);
        Radio_Light.setSelected(true);
        Radio_Fullscreen.setToggleGroup(group1);
        Radio_normal.setToggleGroup(group1);
        Radio_normal.setSelected(true);
        Radio_Fullscreen.setOnAction(this::Switch_Fullscreen);
        Radio_normal.setOnAction(this::Switch_normal);
    }

    @FXML
    private void Switch_Light(ActionEvent event) {
        full_body.getStylesheets().remove(urlTheme);
    }

    @FXML
    private void Switch_Dark(ActionEvent event) {
        full_body.getStylesheets().add(urlTheme);
    }

    @FXML
    private void Switch_Fullscreen(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setFullScreen(true);
    }

    @FXML
    private void Switch_normal(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setFullScreen(false);
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../interfaces/Acceuil_user.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
