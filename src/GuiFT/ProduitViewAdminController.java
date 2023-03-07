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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitViewAdminController implements Initializable {

    Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private Button ProduitButton;

    ProduitService ps = new ProduitService();
    @FXML
    private Button Ajouter_Produits;
    @FXML
    private VBox ProduitLayouts;
    @FXML
    private Button StockButton;
    @FXML
    private TextField Search;
    @FXML
    private Button searchButton;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                // TODO
                List<Produits> p = ps.Read();
                for (int i = 0; i < p.size(); i++) {

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("ProduitsAdminItems.fxml"));
                    HBox hbox = fxmlloader.load();
                    ProduitsAdminItemsController pc = fxmlloader.getController();
                    pc.setData(p.get(i));
                    ProduitLayouts.getChildren().add(hbox);

                }
            } catch (SQLException ex) {
                Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
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

        } catch (SQLException ex) {
            Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void ShowForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterProduitAdmin.fxml"));
            Parent root = loader.load();
            FormAjouterProduitAdminController controller = loader.getController();
            Ajouter_Produits.getScene().setRoot(root);

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
    private void SearchBar(KeyEvent event) {
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
