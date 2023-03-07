/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationChartController implements Initializable {

    @FXML
    private PieChart piechart;
    
    Connection cnx = MyDB.getInstance().getCnx();
    private Statement st;
    private ResultSet rs;
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    @FXML
    private TextField rechercher;
    @FXML
    private Button recherche;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            String query = "SELECT COUNT(*),id_type FROM gestion_reclamation GROUP BY id_type";
            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();

            while (rs.next()) {
                String query1 = "SELECT type FROM gestion_type where id_type = " + rs.getString("id_type");
                Statement st = cnx.createStatement();
                ResultSet rs1 = st.executeQuery(query1);
                while (rs1.next()) {
                    String s = rs1.getString("type");
                    data.add(new PieChart.Data(s, rs.getInt("COUNT(*)")));
                }
                piechart.setLegendSide(Side.LEFT);
                piechart.setData(data);
                // TODO
            }
        } catch (SQLException ex) {    
            Logger.getLogger(ReclamationChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeViewAdmin.fxml"));
            Parent root = loader.load();
            TypeViewAdminController controller = loader.getController();
            Type.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
