/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiYZ;

import entities.Avis;
import entities.Voyage;
import services.AvisService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
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

/**
 * FXML Controller class
 *
 * @author jazzmyna
 */
public class AvisItemAdminController implements Initializable {

    @FXML
    private Label idClient;
    @FXML
    private Label IdAvis;
    @FXML
    private Label IdCommentaire;
    @FXML
    private Label id;
    @FXML
    private Button UpdateButton2;
    @FXML
    private Button DeleteButton2;
    
    AvisService as =new AvisService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UpdateButton(ActionEvent event) {
         try {
            
            Avis a = new Avis();
            a.setId_avis(Integer.parseInt(id.getText()));
            List<Avis> la = as.ReadSingel(a);
             System.out.println(la);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierAvis.fxml"));
            Parent root = loader.load();
            ModifierAvisController controller = loader.getController();
            controller.setData(la);
            UpdateButton2.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(VoyageItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AvisItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeleteButton(ActionEvent event) {
         try {
             Avis a = new Avis();
             
             a.setId_avis(Integer.parseInt(id.getText()));
              System.out.println("aaa"+a);
             as.Delete(a);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvisViewAdmin.fxml"));
            Parent root = loader.load();
            AvisViewAdminController controller = loader.getController();
            DeleteButton2.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(VoyageItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AvisItemAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void setData(Avis a) {
        
        id.setText(Integer.toString(a.getId_avis()));
        idClient.setText(Integer.toString(a.getId_client()));
        IdAvis.setText(Integer.toString(a.getAvis()));
        IdCommentaire.setText(a.getCommentaire());
    }
    
}
