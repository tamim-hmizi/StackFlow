/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Type;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javax.swing.JOptionPane.showMessageDialog;
import services.ReclamationService;
import services.TypeService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FormAjouterReclamationAdminController implements Initializable {

    @FXML
    private Button Reclamation;
    @FXML
    private TextField email;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea msg;
    @FXML
    private Button Ajouter_Reclamation;

    ReclamationService rs = new ReclamationService();
    TypeService ps = new TypeService();
    @FXML
    private Button Type;
    Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private ChoiceBox<String> type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ajouter_Reclamation.setDisable(true);
        msg.textProperty().addListener((observable, oldValue, newValue) -> {
            Ajouter_Reclamation.setDisable(newValue.isEmpty()||sujet.getText().isEmpty()||email.getText().isEmpty());
        });
        
        
        
        try {
            // TODO
            String s = "select * from gestion_type where gestion = 'reclamation' ";
            Statement st = cnx.createStatement();
            ResultSet ps = st.executeQuery(s);
            while (ps.next()){
                Type t = new Type();
                t.setType(ps.getString("type"));
                t.setId_type(ps.getInt(1));
                String ty = t.getType()+"(002"+t.getId_type()+"2123)";
                type.getItems().add(ty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormAjouterReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
    private boolean isValidEmail(String email) {
    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return email.matches(regex);
    }

    @FXML
    private void AjouterReclamation(ActionEvent event) {
        try {
            String s = type.getValue();
            System.out.println(s);
            int start = s.indexOf("(");
            System.out.println(start);
            int end = s.lastIndexOf(")");
            System.out.println(end);
            String typee = s.substring(start+4, end-4);
            String typeee = s.substring(0,start);
            System.out.println(typeee);
            String req = "select * from gestion_type where gestion = 'reclamation' AND type ='"+typeee+"'";
            System.out.println(req);
            Statement st = cnx.createStatement();
            ResultSet ps = st.executeQuery(req);
            Reclamation r = new Reclamation();
           // if (!isValidEmail(email.getText())) {
            //showMessageDialog(null, "Email invalide"); }
            //else{
           
           // }
            while(ps.next()){
            
            r.setEmail(email.getText());
            r.setSujet(sujet.getText());
            r.setMessage(msg.getText());
            r.setId_type(Integer.parseInt(typee));
             rs.ajouter(r);
            //r.setId_type(ps.getInt(1));
            }
           
            System.out.println("Reclamation ajouter avec succes");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationViewAdmin.fxml"));
            Parent root = loader.load();
            ReclamationViewAdminController controller = loader.getController();
            Ajouter_Reclamation.getScene().setRoot(root);
            msg.clear();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReclamationViewAdminController.class.getName()).log(Level.SEVERE, null, ex);
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
