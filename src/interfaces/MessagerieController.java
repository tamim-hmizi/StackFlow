/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.messagerie;
import entities.utilisateur;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.messagerieService;
import services.utilisateurService;
import sun.nio.ch.Secrets;
import typeenumeration.Role;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class MessagerieController implements Initializable {

    private ListView<messagerie> list_mess;
    messagerieService ms = new messagerieService();
    utilisateurService us = new utilisateurService();
    @FXML
    private TextField id_session_supp;
    @FXML
    private Button button_send;
    @FXML
    private TextField tf_message;

    @FXML
    private ComboBox<Integer> combo_transporteur;
    @FXML
    private ComboBox<Integer> combo_client;
    @FXML
    private ListView<String> list_messages;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateTable();
        try {
            combo_client.getItems().addAll(us.Read_Client());
            combo_transporteur.getItems().addAll(us.Read_transporteur());
        } catch (SQLException ex) {
            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("client".equals(utilisateur.getCurrent_user().getRole().toString())) {
            combo_client.setVisible(false);
            combo_transporteur.setVisible(true);
        }
        if ("transporteur".equals(utilisateur.getCurrent_user().getRole().toString())) {
            combo_transporteur.setVisible(false);
            combo_client.setVisible(true);
        }

    }

    @FXML
    private void send(ActionEvent event) {
        messagerie m = null;
        if ("client".equals(utilisateur.getCurrent_user().getRole().toString())) {
            m = new messagerie(utilisateur.getCurrent_user().getId_utilisateur(), combo_transporteur.getValue(), tf_message.getText());
        }
        if ("transporteur".equals(utilisateur.getCurrent_user().getRole().toString())) {
            m = new messagerie(utilisateur.getCurrent_user().getId_utilisateur(), combo_client.getValue(), tf_message.getText());
        }
        try {
            ms.Create(m);
            showmessages();
        } catch (SQLException ex) {
            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showmessages() {

        try {
            List<String> messageries = null;

            if ("client".equals(utilisateur.getCurrent_user().getRole().toString())) {
                messageries = ms.Read(utilisateur.getCurrent_user().getId_utilisateur(), combo_transporteur.getValue());
            }
            if ("transporteur".equals(utilisateur.getCurrent_user().getRole().toString())) {
                messageries = ms.Read(utilisateur.getCurrent_user().getId_utilisateur(), combo_client.getValue());
            }

            ObservableList<String> data = FXCollections.observableArrayList(messageries);
            list_messages.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void UpdateTable() {
        try {
            List<messagerie> messageries = ms.Read();
            ObservableList<messagerie> data = FXCollections.observableArrayList(messageries);
            list_mess.setItems(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void supprimer_mess(ActionEvent event) {
        int id_a_supprimer = Integer.parseInt(id_session_supp.getText());
        try {
            ms.Delete(id_a_supprimer);
            UpdateTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void select_transporteur(ActionEvent event) {
        showmessages();
    }


    @FXML
    private void Accueil(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Acceuil_user.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
