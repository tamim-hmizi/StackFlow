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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.AbonnementService;
import typeenumeration.Type;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.List;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author tamim
 */
public class AbonnementController implements Initializable {

    AbonnementService as = new AbonnementService();

    @FXML
    private TextField id_sup;
    @FXML
    private TextField id_modif;
    @FXML
    private TextField nb_jour_modif;
    @FXML
    private ComboBox<Type> type_modif;
    @FXML
    private TextField remise_modif;
    @FXML
    private TextField id_client_modif;
    @FXML
    private ComboBox<Type> type_ajout;
    @FXML
    private TextField id_client_ajout;
    @FXML
    private TextField remise_ajout;
    @FXML
    private TextField nb_jour_ajout;
    @FXML
    private ListView<Abonnement> list;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_supprimer;
    @FXML
    private ComboBox<Type> Filter_By_Type;
    @FXML
    private Button btn_export;
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
        UpdateList();
        type_modif.getItems().addAll(Type.standard,Type.bronze,Type.silver,Type.gold);
        type_ajout.getItems().addAll(Type.standard,Type.bronze,Type.silver,Type.gold);
        Filter_By_Type.getItems().addAll(Type.values());

    }
    BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "rk7zxdn9vgc5vft3",
            "y7fx325ygjr7wpb3",
            "71639f693a1c954f1a4f536b979f49e8"
    );

    @FXML
    private void ajouter(javafx.event.ActionEvent event) {
        try {
            Abonnement a = new Abonnement();
            a.setId_client(Integer.parseInt(id_client_ajout.getText()));
            a.setType(type_ajout.getValue());
            a.setRemise(Integer.parseInt(remise_ajout.getText()));
            a.setNb_jour(Integer.parseInt(nb_jour_ajout.getText()));
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
                value = a.getNb_jour()*2;
            }
            if (a.getType() == Type.bronze) {
                value = a.getNb_jour()*3;
            }
            if (a.getType() == Type.silver) {
                value = a.getNb_jour()*4;
            }
            if (a.getType() == Type.gold) {
                value = a.getNb_jour()*5;
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
                UpdateList();
                Filter(event);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void UpdateList() {

        try {
            List<Abonnement> abonnements = as.Read();
            ObservableList<Abonnement> olp = FXCollections.observableArrayList(abonnements);
            list.setItems(olp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        id_sup.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_sup.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        nb_jour_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nb_jour_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        remise_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                remise_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_client_modif.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_client_modif.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_client_ajout.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_client_ajout.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        remise_ajout.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                remise_ajout.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        nb_jour_ajout.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nb_jour_ajout.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        btn_ajout.setDisable(true);

        type_ajout.valueProperty().addListener((observable, oldValue, newValue) -> {
            btn_ajout.setDisable(id_client_ajout.getText().isEmpty() || remise_ajout.getText().isEmpty() || nb_jour_ajout.getText().isEmpty() || newValue == null);
        });
        btn_modif.setDisable(true);
        type_modif.valueProperty().addListener((observable, oldValue, newValue) -> {
            btn_modif.setDisable(id_modif.getText().isEmpty() || id_client_modif.getText().isEmpty() || remise_modif.getText().isEmpty() || nb_jour_modif.getText().isEmpty() || newValue == null);
        });
        btn_supprimer.setDisable(true);
        id_sup.textProperty().addListener((observable, oldValue, newValue) -> {
            btn_supprimer.setDisable(newValue.isEmpty());
        });
        id_sup.clear();
        id_modif.clear();
        nb_jour_modif.clear();
        remise_modif.clear();
        id_client_modif.clear();
        id_client_ajout.clear();
        remise_ajout.clear();
        nb_jour_ajout.clear();
        type_ajout.getSelectionModel().clearSelection();
        type_modif.getSelectionModel().clearSelection();
    }

    @FXML
    private void supprimer(javafx.event.ActionEvent event) {
        int id_a_supprimer = Integer.parseInt(id_sup.getText());
        try {
            as.Delete(id_a_supprimer);
            UpdateList();
            Filter(event);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void modifier(javafx.event.ActionEvent event) {
        try {
            Abonnement a = new Abonnement();
            a.setId_client(Integer.parseInt(id_client_modif.getText()));
            a.setId(Integer.parseInt(id_modif.getText()));
            a.setType(type_modif.getValue());
            a.setRemise(Integer.parseInt(remise_modif.getText()));
            a.setNb_jour(Integer.parseInt(nb_jour_modif.getText()));
            as.Update(a);
            UpdateList();
            Filter(event);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void Filter(javafx.event.ActionEvent event) {
        try {
            List<Abonnement> data = as.Read();
            ObservableList<Abonnement> olp = FXCollections.observableArrayList(data);
            if (Filter_By_Type.getValue() == Type.All) {
                list.setItems(olp);
            }
            if (Filter_By_Type.getValue() == Type.standard) {
                FilteredList<Abonnement> filteredData = new FilteredList<>(olp);
                Predicate<Abonnement> Filterer = a -> a.getType() == Type.standard;
                filteredData.setPredicate(Filterer);
                list.setItems(filteredData);
            }
            if (Filter_By_Type.getValue() == Type.bronze) {
                FilteredList<Abonnement> filteredData = new FilteredList<>(olp);
                Predicate<Abonnement> Filterer = a -> a.getType() == Type.bronze;
                filteredData.setPredicate(Filterer);
                list.setItems(filteredData);
            }
            if (Filter_By_Type.getValue() == Type.silver) {
                FilteredList<Abonnement> filteredData = new FilteredList<>(olp);
                Predicate<Abonnement> Filterer = a -> a.getType() == Type.silver;
                filteredData.setPredicate(Filterer);
                list.setItems(filteredData);
            }
            if (Filter_By_Type.getValue() == Type.gold) {
                FilteredList<Abonnement> filteredData = new FilteredList<>(olp);
                Predicate<Abonnement> Filterer = a -> a.getType() == Type.gold;
                filteredData.setPredicate(Filterer);
                list.setItems(filteredData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void export(ActionEvent event) {
        ObservableList<Abonnement> abonnements = list.getItems();

        // Create a new workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheetName = "Abonnements";
        workbook.createSheet(sheetName);

        // Create the header row
        String[] headers = {"ID", "ID client", "Remise", "Nombre de jours", "Type", "Date de début"};
        Row headerRow = workbook.getSheet(sheetName).createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Create the data rows
        int rowNum = 1;
        for (Abonnement abonnement : abonnements) {
            Row row = workbook.getSheet(sheetName).createRow(rowNum++);
            row.createCell(0).setCellValue(abonnement.getId());
            row.createCell(1).setCellValue(abonnement.getId_client());
            row.createCell(2).setCellValue(abonnement.getRemise());
            row.createCell(3).setCellValue(abonnement.getNb_jour());
            row.createCell(4).setCellValue(abonnement.getType().toString());
            row.createCell(5).setCellValue(abonnement.getDate_debut());
        }

        // Show the file chooser dialog
        Stage stage = (Stage) btn_export.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Abonnements");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            // Save the workbook to the selected file
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
