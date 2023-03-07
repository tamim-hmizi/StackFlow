/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import GuiFT.ProduitViewClientController;
import entities.Voyage;
import utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class VoyageItemsController implements Initializable {

    @FXML
    private Label idDep;
    @FXML
    private Label idArri;
    @FXML
    private Label idDate;
    @FXML
    private Button DetailsView;
    @FXML
    private Label id;

    Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private Button DetailsView1;
    @FXML
    private Button DetailsView2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setData(Voyage v){
        id.setText(Integer.toString(v.getId_Voyage()));
        DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        String date_string = date_format.format(v.getDate_depart());
        idDate.setText(date_string);
        idArri.setText(v.getStation_arrive());
        idDep.setText(v.getStation_depart());
        
    }

    @FXML
    private void DetailsView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GuiFT/ProduitViewClient.fxml"));
            Parent root = loader.load();
            DetailsView.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void like(MouseEvent event) {
        try {
            String s = "select plus from Gestion_Voyage where id_voyage = "+id.getText() ;
            Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(s);
            while(rs.next()){
                int i = rs.getInt("plus");
                i =i+1;
                String req = "UPDATE gestion_voyage SET Plus = ? where id_voyage = ?";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, i);
                ps.setInt(2, Integer.parseInt(id.getText()));
                ps.executeUpdate();
                System.out.println(i);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VoyageItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void dislike(MouseEvent event) {
         try {
            String s = "select Moins from Gestion_Voyage where id_voyage = "+id.getText() ;
            Statement st = cnx.createStatement();
            ResultSet rs =  st.executeQuery(s);
            while(rs.next()){
                int i = rs.getInt("Moins");
                i =i+1;
                String req = "UPDATE gestion_voyage SET Moins = ? where id_voyage = ?";
                PreparedStatement ps = cnx.prepareStatement(req);
                ps.setInt(1, i);
                ps.setInt(2, Integer.parseInt(id.getText()));
                ps.executeUpdate();
                System.out.println(i);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VoyageItemsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
