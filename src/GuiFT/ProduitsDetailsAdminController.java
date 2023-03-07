/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitsDetailsAdminController implements Initializable {

    @FXML
    private Button ProduitButton;
    @FXML
    private TextField titre;
    @FXML
    private ImageView imageView;
    @FXML
    private DatePicker date;
    @FXML
    private TextField prix;
    @FXML
    private TextField poids;
    @FXML
    private Button ModifierButton;
    @FXML
    private Button AnnulerButton;
    @FXML
    private Label id;
    
    ProduitService ps = new ProduitService();
    @FXML
    private Label imageLabel;
    @FXML
    private Button StockButton;
    @FXML
    private TextField Search;
    @FXML
    private Button searchButton;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    public void setData(Produits p){
        id.setText(Integer.toString(p.getId()));
        titre.setText(p.getTitre());
        imageLabel.setText(p.getImage());
        Image image = new Image(p.getImage().toString());
        imageView.setImage(image);
        poids.setText(Float.toString(p.getPoids()));
        prix.setText(Float.toString(p.getPrix()));
        date.setValue(p.getDate().toLocalDate());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ModifierButton.setDisable(true);
        prix.textProperty().addListener((observable,oldValue,newValue)->{
         ModifierButton.setDisable(newValue.isEmpty()||poids.getText().isEmpty()||titre.getText().isEmpty());
    });
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
    private void Modifier(ActionEvent event) {
        try {
           
            
            Produits p = new Produits();
            p.setId(Integer.parseInt(id.getText()));
            
            p.setTitre(titre.getText());
            p.setImage(imageLabel.getText());
            p.setPrix(Float.parseFloat(prix.getText()));
            p.setPoids(Float.parseFloat(poids.getText()));
            java.sql.Date d = java.sql.Date.valueOf(date.getValue());
            p.setDate(d);
            ps.Update(p);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewAdmin.fxml"));
            Parent root = loader.load();
            ProduitViewAdminController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);
            prix.clear();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsDetailsAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProduitsDetailsAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void StockButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StockViewAdmin.fxml"));
            Parent root = loader.load();
            StockViewAdminController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProduitViewClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AnnulerButton(ActionEvent event) {
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
