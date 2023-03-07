/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
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
import services.utilisateurService;
import typeenumeration.Role;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class Acceuil_userController implements Initializable {

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
    
    utilisateurService us = new utilisateurService();
    @FXML
    private ImageView user_image;
    @FXML
    private Button log_out;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        Parent root = FXMLLoader.load(getClass().getResource("Messagerie.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void produit(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("../GuiFT/ProduitViewClient.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void abonnement(ActionEvent event) throws IOException {
        
                  Parent root = FXMLLoader.load(getClass().getResource("../gui/Abonnement_user.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
         
    }

    @FXML
    private void Livraison(ActionEvent event) throws IOException {
        
                  Parent root = FXMLLoader.load(getClass().getResource("../gui/Livraison_user.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
         
    }

    @FXML
    private void voyage(ActionEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("../GuiYZ/VoyageView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        
                  Parent root = FXMLLoader.load(getClass().getResource("../gui/ReclamationViewClient.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
         
    }

    @FXML
    private void typereclamation(ActionEvent event) throws IOException {
        
                  Parent root = FXMLLoader.load(getClass().getResource("../gui/TypeViewClient.fxml"));
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
    private void settings(ActionEvent event) throws IOException {
                     Parent root = FXMLLoader.load(getClass().getResource("../gui/Settings.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

}
