/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RechercheController implements Initializable {
Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private TextField rechercher;
    @FXML
    private Button recherche;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Type;
    @FXML
    private Button Ajouter_Reclamation;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void rechecher(ActionEvent event) {
    }

    @FXML
    private void ReclamationButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewAdmin.fxml"));
            Parent root = loader.load();
            ReclamationViewAdminController controller = loader.getController();
            Reclamation.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void TypeButton(ActionEvent event) {
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
    }

    void setData(Reclamation r) {
        recherche.setText(r.getSujet());
        int column = 0;
        int row = 1;
        try{
            List <Reclamation> reclamation = new ArrayList<>();
            String s = "select * from gestion_reclamation WHERE (UPPER(sujet) LIKE '%"+ (recherche.getText().toUpperCase())+"%')OR(email = '"+recherche.getText()+"')";
            System.out.println(s);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {
                Reclamation pr = new Reclamation();
                pr.setEmail(rs.getString("email"));
                pr.setSujet(rs.getString("sujet"));
                pr.setMessage(rs.getString("message"));
                pr.setId_reclamation(rs.getInt("id_reclamation"));

                reclamation.add(pr);

            }

            for (int i = 0; i < reclamation.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ReclamationItemAdmin.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ReclamationItemAdminController pc = fxmlloader.getController();
                pc.setData (reclamation.get(i));
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
        } catch (SQLException ex) {
            Logger.getLogger(RechercheController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RechercheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    

