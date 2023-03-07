/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class SearchClientController implements Initializable {
Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private TextField Search;
    @FXML
    private Button searchButton;
    @FXML
    private Button ProduitButton;
    @FXML
    private Button Ajouter_Produits;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button mes_produit;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    
    public void setDate(Produits p) {
        Search.setText(p.getTitre());
        int column = 0;
        int row = 1;
        try {
             List<Produits> produits = new ArrayList<>();
            String s = "select * from gestion_produit WHERE (UPPER(titre) LIKE '%"+ (Search.getText().toUpperCase())+"%')OR(poids = '"+Search.getText()+"')";
            System.out.println(s);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {
                Produits pr = new Produits();
                pr.setTitre(rs.getString("titre"));
                pr.setImage(rs.getString("image"));
                pr.setPoids(rs.getFloat("poids"));
                pr.setDate(rs.getDate("date"));
                pr.setPrix(rs.getInt("prix"));
                pr.setEtat(rs.getString("etat"));
                pr.setId(rs.getInt("id"));

                produits.add(pr);

            }
            for (int i = 0; i < produits.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Produits.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitsController pc = fxmlloader.getController();
                pc.setData (produits.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchClient.fxml"));
            Parent root = loader.load();
            SearchClientController controller = loader.getController();
            Produits p = new Produits();
            p.setTitre(Search.getText());
            controller.setDate(p);
            searchButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private void ShowMesProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MesProduit.fxml"));
            Parent root = loader.load();
            MesProduitController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ShowProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewClient.fxml"));
            Parent root = loader.load();
            ProduitViewClientController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void AjouterProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterProduit.fxml"));
            Parent root = loader.load();
            FormAjouterProduitController controller = loader.getController();
            Ajouter_Produits.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
