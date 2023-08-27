package md.enovaImport.pdf;

import md.enovaImport.modelsFX.SendMailFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.PdfElement;
import md.enovaImport.xml.models.ElementSlownik;
import md.enovaImport.xml.models.PodatkiSkladki;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataToPdf {

    ImportDAO importDAO = new ImportDAO();
    public List<PdfElement> getElements(SendMailFX sendMailFX){
        List<PdfElement> pdfElementList = new ArrayList<>();

        try {
            PodatkiSkladki podatkiSkladki = importDAO.getTax(sendMailFX);
            List<ElementSlownik> elementSlownikList = importDAO.getDictionaryTaxElementList();

            if(elementSlownikList.get(0).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(0);
                pdfElement.setElementName(elementSlownikList.get(0).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(0).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(0).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getPodatekZaliczkaUS());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(1).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(1);
                pdfElement.setElementName(elementSlownikList.get(1).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(1).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(1).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getEmerytalnaPracownik());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(2).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(2);
                pdfElement.setElementName(elementSlownikList.get(2).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(2).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(2).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getRentowaPracownik());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(3).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(3);
                pdfElement.setElementName(elementSlownikList.get(3).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(3).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(3).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getChorobowaPracownik());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(4).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(4);
                pdfElement.setElementName(elementSlownikList.get(4).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(4).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(4).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getWypadkowaPracownik());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(5).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(5);
                pdfElement.setElementName(elementSlownikList.get(5).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(5).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(5).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getEmerytalnaFirma());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(6).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(6);
                pdfElement.setElementName(elementSlownikList.get(6).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(6).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(6).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getRentowaFirma());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(7).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(7);
                pdfElement.setElementName(elementSlownikList.get(7).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(7).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(7).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getChorobowaFirma());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(8).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(8);
                pdfElement.setElementName(elementSlownikList.get(8).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(8).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(8).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getWypadkowaFirma());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(9).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(9);
                pdfElement.setElementName(elementSlownikList.get(9).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(9).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(9).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getZdrowotnaPracownik());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(10).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(10);
                pdfElement.setElementName(elementSlownikList.get(10).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(10).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(10).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getFP());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(11).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(11);
                pdfElement.setElementName(elementSlownikList.get(11).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(11).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(11).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getFGSP());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(12).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(12);
                pdfElement.setElementName(elementSlownikList.get(12).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(12).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(12).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getFEP());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(13).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(13);
                pdfElement.setElementName(elementSlownikList.get(13).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(13).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(13).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getPPKPracownik());
                pdfElementList.add(pdfElement);
            }
            if(elementSlownikList.get(14).getCzyDrukowac()){
                PdfElement pdfElement = new PdfElement();
                pdfElement.setId(14);
                pdfElement.setElementName(elementSlownikList.get(14).getNazwa());
                pdfElement.setElementAlias(elementSlownikList.get(14).getAlias());
                pdfElement.setElementDays(0);
                pdfElement.setElementTime("");
                pdfElement.setOrder(elementSlownikList.get(14).getKolejnosc());
                pdfElement.setElementVaule(podatkiSkladki.getPPKFirma());
                pdfElementList.add(pdfElement);
            }

            pdfElementList=importDAO.getElementtoPdf(pdfElementList,sendMailFX);

          //  pdfElementList.forEach(element->{
          //      System.out.println(element);
          // });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
return pdfElementList;
    }

}
