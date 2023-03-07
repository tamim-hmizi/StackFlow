/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Abonnement;
import entities.Livraison;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.poi.poifs.property.Parent;
import services.AbonnementService;
import services.LivraisonService;
import services.utilisateurService;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class Livraison_userController implements Initializable {

    @FXML
    private TextField id_stock_debut;
    @FXML
    private TextField id_stock_fin;
    @FXML
    private ComboBox<Integer> combo_transporteur;
    @FXML
    private ListView<Livraison> list;
    @FXML
    private TextField id_supp;

    LivraisonService ls = new LivraisonService();
    utilisateurService us = new utilisateurService();
    AbonnementService as = new AbonnementService();
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateList();
        try {
            combo_transporteur.getItems().addAll(us.Read_transporteur());
        } catch (SQLException ex) {
            Logger.getLogger(Livraison_userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateList() {
        try {
            List<Livraison> livraisons = ls.Read_For_User();
            ObservableList<Livraison> data = FXCollections.observableArrayList(livraisons);
            list.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        List<Abonnement> list = as.Recherche(utilisateur.getCurrent_user().getId_utilisateur());
        Livraison l = new Livraison();
        if(!list.isEmpty()){
        l.setPrix(100 * list.get(0).getRemise() / 100);
        l.setId_abonnement(list.get(0).getId());
        l.setId_client(utilisateur.getCurrent_user().getId_utilisateur());
        l.setId_transporteur(combo_transporteur.getValue());
        l.setId_stock_debut(Integer.parseInt(id_stock_debut.getText()));
        l.setId_stock_fin(Integer.parseInt(id_stock_fin.getText()));
        ls.Create(l);
        }else{
        l.setPrix(100);
        l.setId_client(utilisateur.getCurrent_user().getId_utilisateur());
        l.setId_transporteur(combo_transporteur.getValue());
        l.setId_stock_debut(Integer.parseInt(id_stock_debut.getText()));
        l.setId_stock_fin(Integer.parseInt(id_stock_fin.getText()));
        ls.Create(l);
        }
        updateList();
    }

    @FXML
    private void Annuler(ActionEvent event) throws SQLException {
          try {
            List<Livraison> livraisons = ls.Read_For_User();
              for(Livraison livraison : livraisons){
                  if(livraison.getId()==Integer.parseInt(id_supp.getText())){
                      ls.Delete(Integer.parseInt(id_supp.getText()));
                      updateList();
                  }
              }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void Accueil(ActionEvent event) throws IOException {
            javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("../interfaces/Acceuil_user.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
