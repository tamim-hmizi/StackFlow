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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class MesProduitController implements Initializable {

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
    private VBox ProduitLayouts;
    ProduitService ps = new ProduitService();
    Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    
    void setData(String text) {
            try {
            try {
                // TODO
                List<Produits> produits = new ArrayList<>();
                String s2 = "select * from gestion_produit where id_client = "+text;
                System.out.println(s2);
                Statement st2 = cnx.createStatement();
                ResultSet rs2 = st2.executeQuery(s2);
                while (rs2.next()) {
                    Produits pr = new Produits();
                    pr.setTitre(rs2.getString("titre"));
                    pr.setImage(rs2.getString("image"));
                    pr.setPoids(rs2.getFloat("poids"));
                    pr.setDate(rs2.getDate("date"));
                    pr.setPrix(rs2.getInt("prix"));
                    pr.setEtat(rs2.getString("etat"));
                    pr.setId(rs2.getInt("id"));

                    produits.add(pr);
                }
                for (int i = 0; i < produits.size(); i++) {

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("MesProduitItems.fxml"));
                    HBox hbox = fxmlloader.load();
                    MesProduitItemsController pc = fxmlloader.getController();
                    pc.setData(produits.get(i));
                    ProduitLayouts.getChildren().add(hbox);

                }
            } catch (SQLException ex) {
                Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Produits> lp = ps.Read();
            ArrayList<String> ar = new ArrayList<String>();

            String s1 = "select * from gestion_produit ORDER BY id desc";
            Statement st1 = cnx.createStatement();
            ResultSet rs1= st1.executeQuery(s1);

            while (rs1.next()) {
                ar.add(rs1.getString("titre"));
                ar.add(Float.toString(rs1.getFloat("poids")));
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(rs1.getDate("date"));
                ar.add(strDate);
                ar.add(Float.toString(rs1.getInt("prix")));
                System.out.println(ar);

            }
            TextFields.bindAutoCompletion(Search, ar);

        } catch (SQLException ex) {
            Logger.getLogger(ProduitViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void searchButton(ActionEvent event
    ) {
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
    private void ShowProduit(ActionEvent event
    ) {
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
    private void AjouterProduit(ActionEvent event
    ) {
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
