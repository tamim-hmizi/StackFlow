/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import services.ProduitService;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class SeachController implements Initializable {

    Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private TextField Search;
    @FXML
    private Button ProduitButton;
    @FXML
    private Button StockButton;
    @FXML
    private Button Ajouter_Produits;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox ProduitLayouts;
    @FXML
    private Button searchButton;
    ProduitService ps = new ProduitService();
    private Label searchLabel;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    public void setDate(Produits p) {
        Search.setText(p.getTitre());
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
                fxmlloader.setLocation(getClass().getResource("ProduitsAdminItems.fxml"));
                HBox hbox = fxmlloader.load();
                ProduitsAdminItemsController pc = fxmlloader.getController();
                pc.setData(produits.get(i));
                ProduitLayouts.getChildren().add(hbox);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SeachController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SeachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }
    

    @FXML
    private void SearchBar(KeyEvent event) {
        
    }

    @FXML
    private void ProduitButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewAdmin.fxml"));
            Parent root = loader.load();
            ProduitViewAdminController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StockView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StockViewAdmin.fxml"));
            Parent root = loader.load();
            StockViewAdminController controller = loader.getController();
            StockButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ShowForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Gui/FormAjouterProduitAdmin.fxml"));
            Parent root = loader.load();
            FormAjouterProduitAdminController controller = loader.getController();
            Ajouter_Produits.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void searchButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Seach.fxml"));
            Parent root = loader.load();
            SeachController controller = loader.getController();
            Produits p = new Produits();
            p.setTitre(Search.getText());
            controller.setDate(p);
            searchButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../interfaces/Acceuille_admin.fxml"));
            Parent root = loader.load();
            Accueil.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
