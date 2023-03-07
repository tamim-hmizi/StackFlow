package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;

public class QRCodeGenerator {
    private static final String FILE_FORMAT = "png";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    public static void generateQRCode(int id, String nom, String prenom, String filePath) throws WriterException, IOException {
        String data = id + " " + nom + " " + prenom;
        generateQRCode(data, filePath);
    }

    public static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        // Create the QR code writer
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // Set the QR code parameters
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
        EncodeHintType hintType = EncodeHintType.MARGIN;
        int hintValue = 2;

        // Generate the QR code image
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        int qrCodeWidth = bitMatrix.getWidth();
        int qrCodeHeight = bitMatrix.getHeight();
        BufferedImage qrCodeImage = new BufferedImage(qrCodeWidth, qrCodeHeight, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < qrCodeWidth; x++) {
            for (int y = 0; y < qrCodeHeight; y++) {
                qrCodeImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }

        // Write the image to a file
        File qrCodeFile = new File(filePath);
        ImageIO.write(qrCodeImage, FILE_FORMAT, qrCodeFile);
    }

public static Image generateQRCode(int id, String nom, String prenom) throws WriterException {
    String data = id + " " + nom + " " + prenom;
    // Create the QR code writer
    QRCodeWriter qrCodeWriter = new QRCodeWriter();

    // Set the QR code parameters
    ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
    EncodeHintType hintType = EncodeHintType.MARGIN;
    int hintValue = 2;

    // Generate the QR code image
    BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
    int qrCodeWidth = bitMatrix.getWidth();
    int qrCodeHeight = bitMatrix.getHeight();
    BufferedImage qrCodeImage = new BufferedImage(qrCodeWidth, qrCodeHeight, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < qrCodeWidth; x++) {
        for (int y = 0; y < qrCodeHeight; y++) {
            qrCodeImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
        }
    }

    // Convert the image to a JavaFX Image object
    Image qrCodeFxImage = SwingFXUtils.toFXImage(qrCodeImage, null);

    return qrCodeFxImage;
}

}
