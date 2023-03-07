/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import services.utilisateurService;
import typeenumeration.Role;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class Sign_upController implements Initializable {

    @FXML
    private Button btn_ajout;
    @FXML
    private ComboBox<Role> comb;
    @FXML
    private TextField cin;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private TextField pas;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    utilisateurService us = new utilisateurService();
    @FXML
    private Button qrcode;
    @FXML
    private ImageView user_img;
    @FXML
    private Button selectImageBtn;
    @FXML
    private Label imageURL;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        utilisateur u = new utilisateur();
        comb.getItems().add(Role.client);
        comb.getItems().add(Role.transporteur);

        cin.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") && newValue.length() == 8) {
                cin.setText(newValue.replaceAll("[^\\d]", ""));

            }
        });

        tel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") && newValue.length() == 8) {
                tel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
 
   

   
    }

    @FXML
    private void ajouter(ActionEvent event) {

        try {

            if (nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || pas.getText().isEmpty() || tel.getText().isEmpty() || cin.getText().isEmpty()) {

                showMessageDialog(null, "Vérifier Vos Champs");
            } else if (cin.getText().length() != 8) {
                showMessageDialog(null, "Le Numéro de CIN Doit Etre Composer de 8 Chiffres");
            } else if (tel.getText().length() != 8) {
                showMessageDialog(null, "Le Numéro de Tel Doit Etre Composer de 8 Chiffres");
            } else if (!isValidEmail(email.getText())) {
                showMessageDialog(null, "Email invalide");
            } else {
                utilisateur r = new utilisateur();
                r.setImage(imageURL.getText());
                r.setNom(nom.getText());
                r.setPrenom(prenom.getText());
                r.setPassword(pas.getText());
                r.setEmail(email.getText());
                r.setNum_tel(Integer.parseInt(tel.getText()));
                r.setCin(Integer.parseInt(cin.getText()));
                r.setRole(comb.getValue());
                us.Create(r);
                nom.clear();
                prenom.clear();
                pas.clear();
                email.clear();
                tel.clear();
                cin.clear();
                comb.getSelectionModel().clearSelection();
                imageURL.setText("");
                user_img.setImage(null);
                System.out.println("personne ajouter avec succes");
            }

        } catch (SQLException ex) {
            System.out.println("gg1" + ex.getMessage());
        }
        to_sign_in(event);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        return email.matches(regex) && us.unique_email(email);
    }

    @FXML
    private void qrcode(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QrCode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) qrcode.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void selectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        Image image = new Image(selectedFile.toURI().toString());
        String name = selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().lastIndexOf("//") +1,selectedFile.getAbsolutePath().lastIndexOf(".")-1);
        System.out.println(name);
        user_img.setImage(image);
        imageURL.setText(name);
        
        System.out.println(image);
    }
    }

    @FXML
    private void handleImageClick(MouseEvent event) {
          if (event.getClickCount() == 2) {
        selectImage(null);
    }
    }

    @FXML
    private void to_sign_in(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign_in.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
