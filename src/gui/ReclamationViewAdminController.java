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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import services.ReclamationService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationViewAdminController implements Initializable {
Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private Button Reclamation;
    @FXML
    private Button Ajouter_Reclamation;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    ReclamationService ps = new ReclamationService();
    @FXML
    private Button Type;
    @FXML
    private TextField rechercher;
    @FXML
    private Button recherche;
    @FXML
    private Button reclamation;
    @FXML
    private Button acceuil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            List<Reclamation> r = ps.recuperer();
            for (int i = 0; i < r.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ReclamationItemAdmin.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ReclamationItemAdminController pc = fxmlloader.getController();
                pc.setData(r.get(i));
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
                
                List<Reclamation> lp = ps.recuperer();
            ArrayList<String> ar = new ArrayList<String>();

            String s = "select * from gestion_reclamation ORDER BY id_reclamation desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);

            while (rs.next()) {
                ar.add(rs.getString("email"));
                ar.add(rs.getString("sujet"));
                ar.add(rs.getString("message"));
                System.out.println(ar);

            }
            TextFields.bindAutoCompletion(rechercher, ar);
            }
        } catch (IOException ex) {
            Logger.getLogger(ReclamationViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void AjouterReclamation(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterReclamationAdmin.fxml"));
            Parent root = loader.load();
            FormAjouterReclamationAdminController controller = loader.getController();
            Ajouter_Reclamation.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void TypeButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeViewAdmin.fxml"));
            Parent root = loader.load();
            TypeViewAdminController controller = loader.getController();
            Type.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void rechecher(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Recherche.fxml"));
            Parent root = loader.load();
            RechercheController controller = loader.getController();
            Reclamation r = new Reclamation();
            r.setSujet (rechercher.getText());
            controller.setData(r);
            recherche.getScene().setRoot(root);
        }catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void reclamationChart(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationChart.fxml"));
            Parent root = loader.load();
            ReclamationChartController controller = loader.getController();
            reclamation.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void acceuilButton(ActionEvent event) throws IOException {
                    javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("../interfaces/Acceuille_admin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
