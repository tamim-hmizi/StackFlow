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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class SearchController implements Initializable {
Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private Button VoyageButton;
    @FXML
    private Button Ajouter_Voyage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private TextField Search;
    @FXML
    private Button SearchButton;
    VoyageService vs = new VoyageService();
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    void setData(String text) {
        Search.setText(text);
        int column = 0;
        int row = 1;
        try {
             List<Voyage> voyage = new ArrayList<>();
            String s = "select * from gestion_voyage WHERE (UPPER(station_depart) LIKE '%"+ (Search.getText().toUpperCase())+"%')OR(UPPER(station_arrive) LIKE '%"+ (Search.getText().toUpperCase())+"%')";
            System.out.println(s);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {
                Voyage vr = new Voyage();
                vr.setDate_arrivee(rs.getDate("date_arrivee"));
                vr.setDate_depart(rs.getDate("date_depart"));
                vr.setStation_arrive(rs.getString("station_arrive"));
                vr.setStation_depart(rs.getString("station_depart"));
                vr.setId_Voyage(rs.getInt("id_voyage"));

                voyage.add(vr);

            }
            for (int i = 0; i < voyage.size(); i++) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("VoyageItems.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                VoyageItemsController pc = fxmlloader.getController();
                pc.setData (voyage.get(i));
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
                List<Voyage> lpv= vs.Read();
            ArrayList<String> ar = new ArrayList<String>();

            String s1 = "select * from gestion_voyage ORDER BY id_voyage desc";
            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(s1);

            while (rs1.next()) {
                ar.add(rs1.getString("station_depart"));
                ar.add(rs1.getString("station_arrive"));

            }
            TextFields.bindAutoCompletion(Search, ar);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
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
