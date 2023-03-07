/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class VoyageDetailController implements Initializable {

    @FXML
    private Button VoyageId;
    @FXML
    private Button AvisButton;
    @FXML
    private Label id;
    @FXML
    private Button Ajouter_Avis;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ShowVoyage(ActionEvent event) {
    }

    @FXML
    private void AvisButton(ActionEvent event) {
    }

    @FXML
    private void AjouterAvis(ActionEvent event) {
    }

    void setData(String t) {
        id.setText(t);
    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../interfaces/Acceuil_user.fxml"));
            Parent root = loader.load();
            Accueil.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
