/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.messagerie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.messagerieService;
import services.utilisateurService;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class MessagerieAdminController implements Initializable {

    @FXML
    private ListView<messagerie> list_mess;
    @FXML
    private Button Accueil;
     messagerieService ms = new messagerieService();
    utilisateurService us = new utilisateurService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         UpdateTable();
    }    

    @FXML
    private void Accueil(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Acceuille_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

}
