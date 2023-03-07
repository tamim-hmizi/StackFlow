/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import entities.Voyage;
import services.VoyageService;
import utils.MyDB;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class VoyageViewController implements Initializable {
Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    VoyageService vs = new VoyageService();
    @FXML
    private Button Ajouter_Voyage;
    @FXML
    private Button VoyageButton;
    @FXML
    private TextField Search;
    @FXML
    private Button SearchButton;
    private Button statistique;
    @FXML
    private Button Accueil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column = 0;
        int row = 1;
        try {
            List<Voyage> v = vs.Read();
            for (int i = 0; i < v.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("VoyageItems.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                VoyageItemsController pc = fxmlloader.getController();
                pc.setData(v.get(i));
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
                
                List<Voyage> lpv= vs.Read();
            ArrayList<String> ar = new ArrayList<String>();

            String s = "select * from gestion_voyage ORDER BY id_voyage desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);

            while (rs.next()) {
                ar.add(rs.getString("station_depart"));
                ar.add(rs.getString("station_arrive"));
                System.out.println(ar);

            }
            TextFields.bindAutoCompletion(Search, ar);
            }
        } catch (IOException ex) {
            Logger.getLogger(VoyageItemsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VoyageItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void ShowVoyageForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterVoyage.fxml"));
            Parent root = loader.load();
            FormAjouterVoyageController controller = loader.getController();
            Ajouter_Voyage.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void VoyageButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageView.fxml"));
            Parent root = loader.load();
            VoyageViewController controller = loader.getController();
            VoyageButton.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void SearchButton(ActionEvent event) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
            Parent root = loader.load();
            SearchController controller = loader.getController();
            controller.setData(Search.getText());
            VoyageButton.getScene().setRoot(root);
            
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
