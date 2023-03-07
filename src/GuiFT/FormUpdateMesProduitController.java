/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import entities.Voyage;
import utils.MyDB;
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
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class FormUpdateMesProduitController implements Initializable {

    @FXML
    private TextField Search;
    @FXML
    private Button searchButton;
    @FXML
    private Button ProduitButton;
    @FXML
    private GridPane grid;
    @FXML
    private TextField prix;
    @FXML
    private Label dateLabel;
    @FXML
    private Label poidsLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label deLabel;
    @FXML
    private Label titreLabel;
    private Label aLabel;
    @FXML
    private DatePicker date;
    @FXML
    private TextField titre;
    @FXML
    private TextField poids;
    @FXML
    private Button modifier;
    @FXML
    private Label id;
    @FXML
    private ChoiceBox<String> DeA;
    
    Connection cnx =MyDB.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titreLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(titreLabel, HPos.CENTER);
        dateLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(dateLabel, HPos.CENTER);
        poidsLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(poidsLabel, HPos.CENTER);
        prixLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.setHalignment(prixLabel, HPos.CENTER);
        // TODO
        try {
            List<Voyage> voyage = new ArrayList<>();
            String s = "select * from gestion_voyage";
            System.out.println(s);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {
                Voyage v = new Voyage();
                v.setDate_arrivee(rs.getDate("date_arrivee"));
                v.setDate_depart(rs.getDate("date_depart"));
                v.setStation_arrive(rs.getString("station_arrive"));
                v.setStation_depart(rs.getString("station_depart"));
                v.setId_Voyage(rs.getInt("id_voyage"));

                voyage.add(v);
                String it = v.getStation_depart() + " (" + v.getStation_depart().substring(0, 2) + v.getStation_arrive().substring(0, 2) + "0" + v.getId_Voyage() + "0) " + v.getStation_arrive();
                DeA.getItems().add(it);
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(FormAjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void searchButton(ActionEvent event) {
    }


    @FXML
    private void ModifierProduit(ActionEvent event) {
    }

    void setData(Produits p) {
        id.setText(Integer.toString(p.getId()));
        titre.setText(p.getTitre());
        date.setValue(p.getDate().toLocalDate());
        poids.setText(Float.toString(p.getPoids()));
        prix.setText(Float.toString(p.getPrix()));
    }

    @FXML
    private void ShowProduit(ActionEvent event) {
    }
    
}
