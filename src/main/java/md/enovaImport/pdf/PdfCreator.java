package md.enovaImport.pdf;

import com.itextpdf.io.exceptions.IOException;


import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import md.enovaImport.modelsFX.SendMailFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.PdfElement;
import md.enovaImport.utils.DialogUtils;

import java.io.File;
import java.io.FileNotFoundException;


import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PdfCreator {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    ImportDAO importDAO = new ImportDAO();


    public void createPdf(SendMailFX sendMail) {
        try {
            DataToPdf dataToPdf = new DataToPdf();
            List<PdfElement> pdfElementList = dataToPdf.getElements(sendMail);

            String importName = importDAO.getImportName(sendMail.getImportId());
            String fileName = "C:\\EMAIL\\" + importName + "\\" + sendMail.getName() + ".pdf";

            if (sendMail.getPathFile().isEmpty()) {
                File file = new File(fileName);
                if (file.exists()) {
                    for (int i = 1; i < 100; i++) {
                        fileName = "C:\\EMAIL\\" + importName + "\\" + sendMail.getName() + "_" + i + ".pdf";
                        if (new File(fileName).exists()) {
                            continue;
                        } else {
                            break;
                        }
                    }

                }

                createPdf(fileName, sendMail, pdfElementList);
            } else {
                createPdf(fileName, sendMail, pdfElementList);
            }
        } catch (DocumentException | java.io.IOException | SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void createPdf(String dest, SendMailFX sendMailFX, List<PdfElement> elementList) throws IOException, DocumentException, java.io.IOException {

        Font plFontTableHeader = FontFactory.getFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED, 16);
        Font plFontTable = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED, 12);
        Font plFontTitle = FontFactory.getFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED, 16);

        String pattern = "yyyy-MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        String forest = "FOREST GORLICE SP. Z O.O.";
        String address = "ul. Biecka 9, 38-300 Gorlice";
        Paragraph forestParagraph = new Paragraph(forest);
        Paragraph addressParagraph = new Paragraph(address);

        forestParagraph.setAlignment(Element.ALIGN_RIGHT);
        addressParagraph.setAlignment(Element.ALIGN_RIGHT);

        document.add(forestParagraph);
        document.add(addressParagraph);

        String title = sendMailFX.getName();
        Paragraph titleParagraph = new Paragraph(title, plFontTitle);
        titleParagraph.setAlignment(Element.ALIGN_LEFT);
        document.add(Chunk.NEWLINE);

        String code = String.valueOf(sendMailFX.getCode());
        String paymentDate = sendMailFX.getPaymentDate();

        Paragraph codeParagraph = null;
        try {
            codeParagraph = new Paragraph("Nr prac. " + code + "          " + "Data wypłaty: " + paymentDate + "          Wypłata za okres: " + simpleDateFormat.format(formatter.parse(sendMailFX.getListDate())), plFontTable);
        } catch (ParseException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        codeParagraph.setAlignment(Element.ALIGN_LEFT);


        document.add(titleParagraph);
        document.add(codeParagraph);
        document.add(Chunk.NEWLINE);

        Paragraph agreementAmount = new Paragraph("Kwota z umowy: " + df.format(sendMailFX.getAgreementAmount()));
        agreementAmount.setAlignment(Element.ALIGN_LEFT);
        document.add(agreementAmount);


        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(3);
        table.setWidths(new int[]{180, 20, 60});

        PdfPCell elementName = new PdfPCell(new Phrase("Nazwa składnika", plFontTableHeader));
        elementName.setHorizontalAlignment(HorizontalAlignment.LEFT.ordinal());
        table.addCell(elementName);

        PdfPCell value = new PdfPCell(new Phrase("Dni", plFontTableHeader));
        value.setHorizontalAlignment(HorizontalAlignment.CENTER.ordinal());
        table.addCell(value);

        PdfPCell days = new PdfPCell(new Phrase("Wartość", plFontTableHeader));
        days.setHorizontalAlignment(HorizontalAlignment.CENTER.ordinal());
        table.addCell(days);

        elementList.sort(new Comparator<PdfElement>() {
            @Override
            public int compare(PdfElement o1, PdfElement o2) {
                return Integer.compare(o1.getOrder(), o2.getOrder());
            }
        });

        elementList.forEach(item -> {

            PdfPCell tableValue = new PdfPCell(new Phrase(item.getElementAlias(), plFontTable));
            tableValue.setHorizontalAlignment(HorizontalAlignment.LEFT.ordinal());
            table.addCell(tableValue);

            tableValue = new PdfPCell(new Phrase(String.valueOf(item.getElementDays() == 0 ? "" : item.getElementDays()), plFontTable));
            tableValue.setHorizontalAlignment(HorizontalAlignment.RIGHT.ordinal());
            table.addCell(tableValue);

            tableValue = new PdfPCell(new Phrase(df.format(Math.abs(item.getElementVaule())), plFontTable));
            tableValue.setHorizontalAlignment(HorizontalAlignment.RIGHT.ordinal());
            table.addCell(tableValue);
        });

        table.setWidthPercentage(100);

        document.add(table);
        document.add(Chunk.NEWLINE);

        Paragraph amout = new Paragraph("DO WYPŁATY: " + df.format(sendMailFX.getAmount()), plFontTable);

        document.add(amout);
        document.close();
        sendMailFX.setPathFile(dest);
        try {
            importDAO.updateSendEmailFile(sendMailFX);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

    }
}
