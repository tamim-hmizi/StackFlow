/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.utilisateur;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class Acceuille_adminController implements Initializable {

    @FXML
    private Button log_out;
    @FXML
    private ImageView user_image;
    @FXML
    private Label id;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label password;
    @FXML
    private Label cin;
    @FXML
    private Label num;
    @FXML
    private Label rolee;
    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                utilisateur u  = utilisateur.getCurrent_user();
        id.setText(Integer.toString(u.getId_utilisateur()));
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        password.setText(u.getPassword());
        cin.setText(Integer.toString(u.getCin()));
        num.setText(Integer.toString(u.getNum_tel()));
        rolee.setText(u.getRole().toString());
        email.setText(u.getEmail());
        Image i = new Image("file:///"+u.getImage());
        user_image.setImage(i);
    }    


    @FXML
    private void messagerie(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("MessagerieAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void produit(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("../GuiFT/ProduitViewAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void stock(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("../GuiFT/StockViewAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void abonnement(ActionEvent event) throws IOException {
                   Parent root = FXMLLoader.load(getClass().getResource("../gui/Abonnement.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void Livraison(ActionEvent event) throws IOException {
              Parent root = FXMLLoader.load(getClass().getResource("../gui/Livraison.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void voyage(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("../GuiYZ/VoyageViewAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void avis(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("../GuiYZ/AvisViewAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../gui/ReclamationViewAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void typereclamation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/TypeViewAdmin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {
                   Parent root = FXMLLoader.load(getClass().getResource("Sign_in.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void users(ActionEvent event) throws IOException {
        
                        Parent root = FXMLLoader.load(getClass().getResource("GUI_admin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
