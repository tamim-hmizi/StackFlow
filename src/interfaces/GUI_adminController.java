/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import services.utilisateurService;
import typeenumeration.Role;

/**
 * FXML Controller class
 *
 * @author larbi
 */
public class GUI_adminController implements Initializable {

    @FXML
    private ListView<utilisateur> list;
    @FXML
    private ComboBox<Role> Filter_By_Type;
    @FXML
    private TextField id_modifier;
    @FXML
    private TextField nom_modifier;
    @FXML
    private TextField prenom_modifier;
    @FXML
    private TextField cin_modifier;
    @FXML
    private TextField password_modifier;
    @FXML
    private TextField email_modifier;
    @FXML
    private TextField tel_modifier;
    @FXML
    private ComboBox<Role> role_modifier;
    @FXML
    private TextField iduser_supp;

    utilisateurService us = new utilisateurService();
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        role_modifier.getItems().addAll(Role.values());
        UpdateTable();
        Filter_By_Type.getItems().addAll(Role.values());
    }

    public void UpdateTable() {

        try {
            List<utilisateur> utilisateurs = us.Read();
            ObservableList<utilisateur> data = FXCollections.observableArrayList(utilisateurs);
            list.setItems(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        iduser_supp.clear();
        id_modifier.clear();
        nom_modifier.clear();
        prenom_modifier.clear();
        password_modifier.clear();
        email_modifier.clear();
        tel_modifier.clear();
        cin_modifier.clear();
        role_modifier.getSelectionModel().clearSelection();

    }

    @FXML
    private void filtrer(ActionEvent event) {
        try {
            List<utilisateur> data = us.Read();
            ObservableList<utilisateur> olp = FXCollections.observableArrayList(data);
            if (Filter_By_Type.getValue() == Role.all) {
                list.setItems(olp);

            }
            if (Filter_By_Type.getValue() == Role.admin) {
                FilteredList<utilisateur> filteredData = new FilteredList<>(olp);
                Predicate<utilisateur> Filterer = a -> a.getRole() == Role.admin;
                filteredData.setPredicate(Filterer);
                list.setItems(filteredData);

            }
            if (Filter_By_Type.getValue() == Role.client) {
                FilteredList<utilisateur> filteredData = new FilteredList<>(olp);
                Predicate<utilisateur> Filterer = a -> a.getRole() == Role.client;
                filteredData.setPredicate(Filterer);
                list.setItems(filteredData);

            }
            if (Filter_By_Type.getValue() == Role.transporteur) {
                FilteredList<utilisateur> filteredData = new FilteredList<>(olp);
                Predicate<utilisateur> Filterer = a -> a.getRole() == Role.transporteur;
                filteredData.setPredicate(Filterer);
                list.setItems(filteredData);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void modifier_user(ActionEvent event) {
        try {

            if (nom_modifier.getText().isEmpty() || prenom_modifier.getText().isEmpty() || email_modifier.getText().isEmpty() || password_modifier.getText().isEmpty() || tel_modifier.getText().isEmpty() || cin_modifier.getText().isEmpty()) {

                showMessageDialog(null, "Vérifier Vos Champs");
            } else if (cin_modifier.getText().length() != 8) {
                showMessageDialog(null, "Le Numéro de CIN Doit Etre Composer de 8 Chiffres");
            } else if (tel_modifier.getText().length() != 8) {
                showMessageDialog(null, "Le Numéro de Tel Doit Etre Composer de 8 Chiffres");
            } else {
                utilisateur r = new utilisateur();
                r.setId_utilisateur(Integer.parseInt(id_modifier.getText()));
                r.setNom(nom_modifier.getText());
                r.setPrenom(prenom_modifier.getText());
                r.setPassword(password_modifier.getText());
                r.setEmail(email_modifier.getText());
                r.setNum_tel(Integer.parseInt(tel_modifier.getText()));
                r.setCin(Integer.parseInt(cin_modifier.getText()));
                r.setRole(role_modifier.getValue());
                us.Update(r);
                UpdateTable();
                System.out.println("personne ajouter avec succes");
            }

        } catch (SQLException ex) {
            System.out.println("gg1" + ex.getMessage());
        }
    }

    @FXML
    private void supprimer_user(ActionEvent event) {
        int id_a_supprimer = Integer.parseInt(iduser_supp.getText());
        try {
            us.Delete(id_a_supprimer);
            UpdateTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    private void Accueil(ActionEvent event) throws IOException {
                    Parent root = FXMLLoader.load(getClass().getResource("Acceuille_admin.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

}
