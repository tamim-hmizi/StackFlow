/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import entities.Voyage;
import services.VoyageService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class VoyageViewAdminController implements Initializable {

    @FXML
    private Button VoyageButton;
    @FXML
    private Button Ajouter_Voyage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    VoyageService vs = new VoyageService();
    @FXML
    private Button AvisId;
    @FXML
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
                fxmlloader.setLocation(getClass().getResource("VoyageItemAdmin.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                VoyageItemAdminController pc = fxmlloader.getController();
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
            }
        } catch (IOException ex) {
            Logger.getLogger(VoyageItemsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VoyageItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void VoyageButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            VoyageViewAdminController controller = loader.getController();
            VoyageButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void ShowVoyageForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormAjouterVoyageAdmin.fxml"));
            Parent root = loader.load();
            FormAjouterVoyageAdminController controller = loader.getController();

            Ajouter_Voyage.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void btnAvis(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvisViewAdmin.fxml"));
            Parent root = loader.load();
            AvisViewAdminController controller = loader.getController();
            AvisId.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void export(ActionEvent event) {
           // Create a new FileChooser object
    FileChooser fileChooser = new FileChooser();

    // Set the title for the file chooser dialog
    fileChooser.setTitle("Save File");

    // Set the initial directory for the file chooser dialog
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

    // Set the file extension filters to only show text files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show the file chooser dialog and get the chosen file
    File file = fileChooser.showSaveDialog(null);
    List<Voyage> list=new ArrayList<>();
        try {
            list = vs.Read();
        
    // If a file was chosen, write the list data to the file
    if (file != null) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i =0;i<list.size();i++) {
                Voyage voyage = list.get(i);
                writer.write(voyage.getStation_depart()+ "\t" + voyage.getStation_arrive() + "\t" + voyage.getDate_depart() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    } catch (SQLException ex) {
            Logger.getLogger(VoyageViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void statistique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitChart.fxml"));
            Parent root = loader.load();
            ProduitChartController controller = loader.getController();
            statistique.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
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
