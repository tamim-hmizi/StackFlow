/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import entities.Voyage;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class BlackListeController implements Initializable {

    @FXML
    private TextField Search;
    @FXML
    private Button searchButton;
    @FXML
    private Button ProduitButton;
    @FXML
    private Button StockButton;
    @FXML
    private Button ProduitButton1;
    @FXML
    private ChoiceBox<String> select;
    @FXML
    private Button block;

    Connection cnx = MyDB.getInstance().getCnx();
    private Label id;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            List<utilisateur> ul = new ArrayList<>();
            String s = "select * from gestion_utilisateur";
            System.out.println(s);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {
                utilisateur u = new utilisateur();
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setCin(rs.getInt(4));
                u.setEmail(rs.getString(7));
                u.setId_utilisateur(rs.getInt(1));

                ul.add(u);
                String it ="id : "+u.getId_utilisateur()+ " | Nom : " + u.getNom() + " | Prenom : " + u.getPrenom() + " | Email : " + u.getEmail() + " | CIN : " + u.getCin();
                select.getItems().add(it);
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(FormAjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void Block(ActionEvent event) {
        try {
            String selected = select.getValue();
            System.out.println(selected);
            int end = selected.indexOf("|");
            int start = selected.indexOf(":");
            String id = selected.substring(start+2, end-1);
            System.out.println(id);
            String s = "UPDATE gestion_utilisateur SET status = ? where id_utilisateur = ?";
            PreparedStatement ps = cnx.prepareStatement(s);
            ps.setInt(1,0);
            ps.setInt(2,Integer.parseInt(id));

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BlackListeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Unblock(ActionEvent event) {
        try {
            String selected = select.getValue();
            System.out.println(selected);
            int end = selected.indexOf("|");
            int start = selected.indexOf(":");
            String id = selected.substring(start+2, end-1);
            System.out.println(id);
            String s = "UPDATE gestion_utilisateur SET status = ? where id_utilisateur = ?";
            PreparedStatement ps = cnx.prepareStatement(s);
            ps.setInt(1,1);
            ps.setInt(2,Integer.parseInt(id));

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BlackListeController.class.getName()).log(Level.SEVERE, null, ex);
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
