/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Produits;
import Services.ProduitService;
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
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitsController implements Initializable {

    ProduitService ps = new ProduitService();
    @FXML
    private TableView<Produits> produitTv;
    @FXML
    private TableColumn<Produits, String> TitreTV;
    @FXML
    private TableColumn<Produits, String> ImageTV;
    @FXML
    private TableColumn<Produits, Float> PoidsTV;
    @FXML
    private TableColumn<Produits, String> DateTV;
    @FXML
    private TableColumn<Produits, Integer> PrixTV;
    @FXML
    private TableColumn<Produits, String> EtatTV;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<Produits> p = ps.Read();
            ObservableList<Produits> olp = FXCollections.observableArrayList(p);
            produitTv.setItems(olp);
            TitreTV.setCellValueFactory(new PropertyValueFactory("Titre"));
            ImageTV.setCellValueFactory(new PropertyValueFactory("Image"));
            PoidsTV.setCellValueFactory(new PropertyValueFactory("Poids"));
            DateTV.setCellValueFactory(new PropertyValueFactory("Date"));
            PrixTV.setCellValueFactory(new PropertyValueFactory("Prix"));
            EtatTV.setCellValueFactory(new PropertyValueFactory("Etat"));
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }    

    @FXML
    private void AjouterProduits(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
            Parent root = loader.load();
            AjouterProduitController controller = loader.getController();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
