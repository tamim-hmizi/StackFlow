package interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import utils.QRCodeGenerator;


public class QrCodeController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private ImageView qrCodeImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
private void onGenerateQRCode(ActionEvent event) throws WriterException {
    try {
        // Get the values of the input fields
        int id = Integer.parseInt(idField.getText());
        String nom = nomField.getText();
        String prenom = prenomField.getText();

        // Generate the QR code and display it in the image view
        Image qrCode = QRCodeGenerator.generateQRCode(id, nom, prenom);
        qrCodeImage.setImage(qrCode);
    } catch (NumberFormatException e) {
        // Display an error message if the ID is not a valid integer
        Alert alert = new Alert(AlertType.ERROR, "L'ID doit être un entier.");
        alert.showAndWait();
    }
}


public static Image generateQRCode(int id, String nom, String prenom) {
    try {
        String data = String.format("%d %s %s", id, nom, prenom);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        return image;
    } catch (WriterException e) {
        e.printStackTrace();
        return null;
    }
}


}
