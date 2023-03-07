/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import entities.Abonnement;
import entities.utilisateur;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import services.AbonnementService;
import typeenumeration.Type;

/**
 * FXML Controller class
 *
 * @author tamim
 */
public class Abonnement_userController implements Initializable {

    @FXML
    private ComboBox<Type> type;
    @FXML
    private TextField nb_jour;
    @FXML
    private Label affich_nb_jour;
    @FXML
    private Label affich_type;
    AbonnementService as = new AbonnementService();
    @FXML
    private Label warning;
    @FXML
    private Button Accueil;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().addAll(Type.standard, Type.bronze, Type.silver, Type.gold);
        Update();
        warning.setVisible(false);

    }
    BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "rk7zxdn9vgc5vft3",
            "y7fx325ygjr7wpb3",
            "71639f693a1c954f1a4f536b979f49e8"
    );

    private void Update() {
        try {
            List<Abonnement> list = as.Recherche(utilisateur.getCurrent_user().getId_utilisateur());
            if (list.isEmpty()) {
                affich_nb_jour.setVisible(false);
                affich_type.setVisible(false);
                affich_nb_jour.setText("0");
                affich_type.setText("0");
            } else {
                affich_nb_jour.setVisible(true);
                affich_type.setVisible(true);
                affich_nb_jour.setText(Integer.toString(list.get(0).getNb_jour()));
                affich_type.setText(list.get(0).getType().toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Abonnement_userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter_abonnement(ActionEvent event) {
        Abonnement a = new Abonnement();
        List<Abonnement> list = new ArrayList<>();
        try {
            list = as.Recherche(utilisateur.getCurrent_user().getId_utilisateur());
        } catch (SQLException ex) {
            Logger.getLogger(Abonnement_userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (list.isEmpty()) {
            a.setNb_jour(Integer.parseInt(nb_jour.getText()));
            if (type.getValue() == Type.standard) {
                a.setRemise(5);
                a.setType(Type.standard);
            }
            if (type.getValue() == Type.bronze) {
                a.setRemise(10);
                a.setType(Type.bronze);
            }
            if (type.getValue() == Type.silver) {
                a.setRemise(15);
                a.setType(Type.silver);
            }
            if (type.getValue() == Type.gold) {
                a.setRemise(20);
                a.setType(Type.gold);
            }
            a.setId_client(utilisateur.getCurrent_user().getId_utilisateur());
            try {
                // Collect the payment details from the user
                TextInputDialog cardNumberDialog = new TextInputDialog();
                cardNumberDialog.setHeaderText("Enter your credit card number");
                Optional<String> cardNumberResult = cardNumberDialog.showAndWait();
                if (!cardNumberResult.isPresent()) {
                    // User canceled the dialog, so do nothing
                    return;
                }
                String cardNumber = cardNumberResult.get();

                TextInputDialog expirationDialog = new TextInputDialog();
                expirationDialog.setHeaderText("Enter your card's expiration date (MM/YYYY)");
                Optional<String> expirationResult = expirationDialog.showAndWait();
                if (!expirationResult.isPresent()) {
                    // User canceled the dialog, so do nothing
                    return;
                }
                String expirationDate = expirationResult.get();

                TextInputDialog cvvDialog = new TextInputDialog();
                cvvDialog.setHeaderText("Enter your card's CVV code");
                Optional<String> cvvResult = cvvDialog.showAndWait();
                if (!cvvResult.isPresent()) {
                    // User canceled the dialog, so do nothing
                    return;
                }
                String cvv = cvvResult.get();
                float value = 0;
                if (a.getType() == Type.standard) {
                    value = a.getNb_jour() * 2;
                }
                if (a.getType() == Type.bronze) {
                    value = a.getNb_jour() * 3;
                }
                if (a.getType() == Type.silver) {
                    value = a.getNb_jour() * 4;
                }
                if (a.getType() == Type.gold) {
                    value = a.getNb_jour() * 5;
                }
                BigDecimal amount = new BigDecimal(value);

                // Process the payment with the collected details
                TransactionRequest request = new TransactionRequest()
                        .amount(amount)
                        .creditCard()
                        .number(cardNumber)
                        .expirationDate(expirationDate)
                        .cvv(cvv)
                        .done();

                Result<Transaction> result = gateway.transaction().sale(request);
                if (result.isSuccess()) {
                    as.Create(a);
                    warning.setText("");
                    warning.setVisible(false);
                    nb_jour.clear();
                    type.getSelectionModel().clearSelection();
                } else {
                    warning.setText("Paiment Echouee");
                    warning.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Abonnement_userController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            warning.setText("Abonnement Existe");
            warning.setVisible(true);
        }
        Update();

    }

    @FXML
    private void Accueil(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("../interfaces/Acceuil_user.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
