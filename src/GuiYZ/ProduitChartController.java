/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import java.io.IOException;
import utils.MyDB;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitChartController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private PieChart piechart;

    Connection cnx = MyDB.getInstance().getCnx();
    private Statement st;
    private ResultSet rs;
    ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            String query = "SELECT COUNT(*),id_voyage FROM gestion_produit GROUP BY id_voyage";
            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();

            while (rs.next()) {
                String query1 = "SELECT station_depart ,station_arrive FROM gestion_voyage where id_voyage = " + rs.getString("id_voyage");
                Statement st = cnx.createStatement();
                ResultSet rs1 = st.executeQuery(query1);
                while (rs1.next()) {
                    String s = rs1.getString("station_depart")+"-"+rs1.getString("station_arrive");
                    data.add(new PieChart.Data(s, rs.getInt("COUNT(*)")));
                }
                piechart.setLegendSide(Side.LEFT);
                piechart.setData(data);
                // TODO
            }
        } catch (SQLException ex) {    
            Logger.getLogger(ProduitChartController.class.getName()).log(Level.SEVERE, null, ex);
        }

}

    @FXML
    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VoyageViewAdmin.fxml"));
            Parent root = loader.load();
            Retour.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
