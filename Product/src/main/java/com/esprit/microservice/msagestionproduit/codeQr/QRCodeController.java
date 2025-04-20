package com.esprit.microservice.msagestionproduit.codeQr;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/qrcode")
//http://localhost:8093/Produit/api/qrcode/generate?text=Produit
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String text) throws WriterException, IOException {
        byte[] qrImage = qrCodeService.generateQRCodeByteArray(text, 250, 250);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }


    @GetMapping(value = "/category/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCodeForCategory(@PathVariable Long id) throws Exception {
        byte[] qrCode = qrCodeService.generateQRCodeForCategory(id, 250, 250);
        return ResponseEntity.ok().body(qrCode);
    }
}
