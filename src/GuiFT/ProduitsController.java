/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.Produits;
import services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ProduitsController implements Initializable {

    ProduitService ps = new ProduitService();
    @FXML
    private Label TitreLabel;
    @FXML
    private Label PrixLabel;
    @FXML
    private ImageView ImageView;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setData(Produits p) {
        id.setText(Integer.toString(p.getId()));
        TitreLabel.setText(p.getTitre());
        Image image = new Image(p.getImage());
        ImageView.setImage(image);
        PrixLabel.setText(Float.toString(p.getPrix()) + "DT");

    }

    

    @FXML
    private void click(MouseEvent event) throws SQLException {
        Produits p = new Produits();
           p.setId(Integer.parseInt(id.getText()));
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitDetails.fxml"));
            Parent root = loader.load();
            ProduitDetailsController controller = loader.getController();
            controller.setData(p);
            ImageView.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
           
    }

}
