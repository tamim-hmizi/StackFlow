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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.utilisateurService;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class SignInController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Label wrong_login;
    @FXML
    private PasswordField password;

    utilisateurService us = new utilisateurService();
    @FXML
    private Hyperlink fp;
 

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sign_in(ActionEvent event) {
        try {
            List<utilisateur> list = us.Current_user(email.getText(), password.getText());
            if (list.isEmpty()) {
                wrong_login.setText("Email Or Password are wrong");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    utilisateur.setCurrent_user(list.get(i));
                    if ("admin".equals(list.get(i).getRole().toString())) {
                        Parent root = FXMLLoader.load(getClass().getResource("Acceuille_admin.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Parent root = FXMLLoader.load(getClass().getResource("Acceuil_user.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }

                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        wrong_login.setText("");
        email.clear();
        password.clear();
    }

    @FXML
    private void to_sign_up(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Sign_up.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   


    @FXML
    private void forgotpass(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword.fxml"));
            Parent root = loader.load();
            ForgetPasswordController controller = loader.getController();
            fp.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
