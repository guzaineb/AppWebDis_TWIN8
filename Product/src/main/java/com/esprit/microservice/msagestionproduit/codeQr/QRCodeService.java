package com.esprit.microservice.msagestionproduit.codeQr;

import com.esprit.microservice.msagestionproduit.category.CategoryService;
import com.esprit.microservice.msagestionproduit.produit.Produit;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

@Service
public class QRCodeService {
    @Autowired
    private CategoryService categoryService;
    public void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
    public void generateQRCodeForCategory(Long categoryId, int width, int height, String filePath)
            throws WriterException, IOException {
        List<Produit> produits = categoryService.getProduitsByCategory(categoryId);
        String qrText = convertProduitsToText(produits);
        generateQRCodeImage(qrText, width, height, filePath);
    }

    // New method to generate QR code byte array for a category
    public byte[] generateQRCodeForCategory(Long categoryId, int width, int height)
            throws WriterException, IOException {
        List<Produit> produits = categoryService.getProduitsByCategory(categoryId);
        String qrText = convertProduitsToText(produits);
        return generateQRCodeByteArray(qrText, width, height);
    }

    // Helper method to convert products to a formatted string
    private String convertProduitsToText(List<Produit> produits) {
        StringBuilder sb = new StringBuilder();
        sb.append("Products in Category:\n\n");
        for (Produit produit : produits) {
            sb.append("Name: ").append(produit.getNom())
                    .append("\nDescription: ").append(produit.getDescription())
                    .append("\nPrice: ").append(produit.getPrix())
                    .append("\nQuantity: ").append(produit.getQuantiteDisponible())
                   .append("\n\n");
        }
        return sb.toString();
    }
    public byte[] generateQRCodeByteArray(String text, int width, int height)
            throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        return outputStream.toByteArray();
    }
}
