/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiFT;

import entities.ClientRecp;
import entities.Produits;
import entities.Voyage;
import services.ClientRecpService;
import services.ProduitService;
import utils.MyDB;
import entities.utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class FormAjouterProduitController implements Initializable {

    Connection cnx = MyDB.getInstance().getCnx();
    @FXML
    private TextField titre;
    @FXML
    private DatePicker date;
    @FXML
    private TextField poids;
    @FXML
    private TextField prix;
    @FXML
    private Button imagebutton;
    @FXML
    private Label imageurl;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private Button submit;
    @FXML
    private Label imageurlsave;
    ProduitService ps = new ProduitService();
    ClientRecpService crs = new ClientRecpService();
    @FXML
    private Button ProduitButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField Search;
    @FXML
    private ChoiceBox<String> SelectItem;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                SelectItem.getItems().add(it);
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(FormAjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ChooseImage(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Image", "*.jpg"));
        File f = fc.showOpenDialog(null);
        String newPath = "C:\\Users\\larbi\\OneDrive\\Documents\\NetBeansProjects\\Amine_stackflow\\Stackflowtest\\src\\Image\\";
        String extension = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf('.') + 1);
        String name = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1, f.getAbsolutePath().lastIndexOf(".") - 1);
        File SourceFile = new File(f.getAbsolutePath());
        File destinationFile = new File(newPath + name + "." + extension);
        if (f != null) {
            imageurl.setText(f.getAbsolutePath());
            imageurlsave.setText("/Image/" + name + "." + extension);
            Files.copy(SourceFile.toPath(), destinationFile.toPath());

        }
    }

    @FXML
    private void ajouterProduit(ActionEvent event) throws IOException {
        try {

            String selected = SelectItem.getValue();
            System.out.println(selected);
            int end = selected.lastIndexOf("0");
            int start = selected.indexOf("0");
            System.out.println("start " + start);
            System.out.println("end " + end);
            String st = selected.substring(start + 1, end);
            System.out.println(st);
            
            ClientRecp cr = new ClientRecp();
            cr.setNom(nom.getText());
            cr.setPrenom(prenom.getText());
            cr.setTel(tel.getText());
            cr.setEmail(email.getText());
            crs.Create(cr);

            Produits p = new Produits();
            p.setTitre(titre.getText());
            p.setImage(imageurlsave.getText());
            Date d = Date.valueOf(date.getValue());
            p.setDate(d);
            p.setId_voyage(Integer.parseInt(st));
            p.setPoids(Float.parseFloat(poids.getText()));
            p.setPrix(Float.parseFloat(prix.getText()));
            int id_recp = crs.Read().get(0).getId();
            p.setId_recp(id_recp);
            p.setId_client(1);
            p.setId_transporteur(1);
            p.setType_produit(1);
            ps.Create(p);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewClient.fxml"));
            Parent root = loader.load();
            ProduitViewClientController controller = loader.getController();
            submit.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

    @FXML
    private void ProduitButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProduitViewClient.fxml"));
            Parent root = loader.load();
            ProduitViewClientController controller = loader.getController();
            ProduitButton.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FormAjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Seach.fxml"));
            Parent root = loader.load();
            SeachController controller = loader.getController();
            Produits p = new Produits();
            p.setTitre(Search.getText());
            controller.setDate(p);
            searchButton.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
