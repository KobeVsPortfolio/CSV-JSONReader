package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.util.Logger;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.lang.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

public class GeneratePdfReport {

    String[] wrT = null;

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    public ByteArrayOutputStream generatePdf(List<WasdappEntryResponse> entries) throws IOException {
        try {
            if (entries != null) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                PDDocument doc = new PDDocument();

                int x = 0;
                ArrayList<PDPage> paginas = new ArrayList<>();
                for (WasdappEntryResponse i : entries) {
                    PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
                    doc.addPage(page);
                    paginas.add(page);
                    PDPageContentStream CS = new PDPageContentStream(doc, paginas.get(x), PDPageContentStream.AppendMode.APPEND, true, true);

                    BufferedImage image = createQR(String.valueOf(i.getId()));
                    PDImageXObject pdImage = JPEGFactory.createFromImage(doc, image);

                    CS.drawImage(pdImage, 600, 40);

                    CS.beginText();
                    CS.setFont(PDType1Font.COURIER, 30);
                    CS.newLineAtOffset(50, 500);
                    if (i.getName() != null) {
                        CS.showText(i.getName());
                    }
                    CS.endText();

                    CS.beginText();
                    CS.setFont(PDType1Font.HELVETICA, 30);
                    CS.newLineAtOffset(600, 500);
                    CS.showText("WasDapp");
                    CS.endText();

                    wrT = WordUtils.wrap(i.getOmschrijving(), 100).split("\\r?\\n");
                    for (int w = 0; w < wrT.length; w++) {
                        CS.beginText();
                        CS.setFont(PDType1Font.HELVETICA, 12);
                        CS.newLineAtOffset(165, 300 - w * 15);
                        wrT[w] = i.getOmschrijving();
                        if (i.getOmschrijving() != null) {
                            CS.showText(i.getOmschrijving());
                        }
                        CS.endText();
                    }

                    CS.beginText();
                    CS.setFont(PDType1Font.COURIER, 20);
                    CS.newLineAtOffset(50, 100);
                    if (i.getLocatie() != null) {
                        CS.showText(i.getLocatie());
                    }
                    CS.endText();
                    CS.close();
                    x++;
                }

                doc.save(output);
                doc.close();
                return output;
            }
        } catch (Exception e) {
            LOGGER.error("Error writing PDF.");
        }
        return null;

    }

    public BufferedImage createQR(String id) throws IOException {
        ByteArrayOutputStream bout
                = QRCode.from(id)
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();
        byte[] data = bout.toByteArray();
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        BufferedImage image = ImageIO.read(input);
        return image;
    }
}
