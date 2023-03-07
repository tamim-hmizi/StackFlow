/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.SendMail;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField email;
Connection cnx = MyDB.getInstance().getCnx();
private static String subject = "Forget Password";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Validate(ActionEvent event) throws SQLException {
        String emailAddress = email.getText();
       if(emailAddress == null || emailAddress.isEmpty())
       {
           System.out.println("ghalet nayek");
       }
       else
       {
            String user = "select * from gestion_utilisateur WHERE email = ?";
              PreparedStatement st = cnx.prepareStatement(user);
             st.setString(1, emailAddress);
            ResultSet rs = st.executeQuery(); 
            while(rs.next())
            {
                utilisateur r = new utilisateur();
                r.setId_utilisateur(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setPrenom(rs.getString(3));
                r.setEmail(rs.getString(7));
                r.setNum_tel(rs.getInt(8));
                r.setCin(rs.getInt(4));
                r.setPassword(rs.getString(6));
                
                SendMail send = new SendMail();
                send.envoyerMail(r.getEmail(), subject, r.getPassword());
            }
       }
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("Sign_in.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
