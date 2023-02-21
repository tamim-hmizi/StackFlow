/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Produits;
import Services.ProduitService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField TFTitre;
    @FXML
    private TextField TFImage;
    @FXML
    private TextField TFPoids;
    @FXML
    private TextField TFPrix;
    @FXML
    private TextField TFEtat;
    @FXML
    private DatePicker TFDate;

    /**
     * Initializes the controller class.
     */
    
    ProduitService ps = new ProduitService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
        try {
            Produits p = new Produits();
            p.setTitre(TFTitre.getText());
            p.setImage(TFImage.getText());
            p.setPoids(Float.parseFloat(TFPoids.getText()));
            p.setDate(TFDate.getValue().toString());
            p.setPrix(Float.parseFloat(TFPrix.getText()));
            p.setEtat(TFEtat.getText());
            ps.Create(p);
            System.out.println("Produit ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    void setData(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
