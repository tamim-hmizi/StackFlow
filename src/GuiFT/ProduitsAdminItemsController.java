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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitsAdminItemsController implements Initializable {

    @FXML
    private Label imgLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label titre;
    @FXML
    private Label poids;
    @FXML
    private Label date;
    @FXML
    private Label prix;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Label id;
    
    ProduitService ps = new ProduitService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Delete(ActionEvent event) {
        
        try {
            Produits p = new Produits();
            p.setId(Integer.parseInt(id.getText()));
            ps.Delete(p);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewAdmin.fxml"));
            Parent root = loader.load();
            ProduitViewAdminController controller = loader.getController();
            
            DeleteButton.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsAdminItemsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProduitsAdminItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setData(Produits p) {
        id.setText(Integer.toString(p.getId()));
        titre.setText(p.getTitre());
        Image image = new Image(p.getImage());
        img.setImage(image);
        prix.setText(Float.toString(p.getPrix()));
        poids.setText(Float.toString(p.getPoids()));
        DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        String date_string = date_format.format(p.getDate());
        date.setText(date_string);
        imgLabel.setText(p.getImage());

    }

    @FXML
    private void Update(ActionEvent event) {
         try {
            
            Produits p = new Produits();
            p.setId(Integer.parseInt(id.getText()));
            p.setTitre(titre.getText());
            p.setImage(imgLabel.getText());
             System.out.println(imgLabel.getText());
            p.setPrix(Float.parseFloat(prix.getText()));
            p.setPoids(Float.parseFloat(poids.getText()));
            java.sql.Date d = java.sql.Date.valueOf(date.getText());
            p.setDate(d);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitsDetailsAdmin.fxml"));
            Parent root = loader.load();
            ProduitsDetailsAdminController controller = loader.getController();
            controller.setData(p);
            UpdateButton.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error"+ex.getMessage());
        }
    }
    
}
