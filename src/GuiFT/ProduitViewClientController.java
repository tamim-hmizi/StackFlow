/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import services.ProduitService;
import utils.MyDB;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitViewClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ProduitService ps = new ProduitService();
Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button Ajouter_Produits;
    @FXML
    private Button ProduitButton;
    @FXML
    private TextField Search;
    @FXML
    private Button searchButton1;
    @FXML
    private Button mes_produit;
    @FXML
    private Button Accueil;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            List<Produits> p = ps.Read();
            for (int i = 0; i < p.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Produits.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitsController pc = fxmlloader.getController();
                pc.setData(p.get(i));
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

                System.out.println();

                GridPane.setMargin(anchorPane, new Insets(10));
            }
            List<Produits> lp = ps.Read();
            ArrayList<String> ar = new ArrayList<String>();

            String s = "select * from gestion_produit ORDER BY id desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);

            while (rs.next()) {
                ar.add(rs.getString("titre"));
                ar.add(Float.toString(rs.getFloat("poids")));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(rs.getDate("date"));
                ar.add(strDate);
                ar.add(Float.toString(rs.getInt("prix")));
                System.out.println(ar);

            }
            TextFields.bindAutoCompletion(Search, ar);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitViewClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ShowForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterProduit.fxml"));
            Parent root = loader.load();
            FormAjouterProduitController controller = loader.getController();
            Ajouter_Produits.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void ProduitButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewClient.fxml"));
            Parent root = loader.load();
            ProduitViewClientController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
            searchButton1.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void MesProduitView(ActionEvent event) {
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
