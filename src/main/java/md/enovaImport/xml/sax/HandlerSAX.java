package md.enovaImport.xml.sax;

import md.enovaImport.xml.models.ElementWyplaty;
import md.enovaImport.xml.models.ListaPlac;
import md.enovaImport.xml.models.PodatkiSkladki;
import md.enovaImport.xml.models.Wyplata;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HandlerSAX extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    List<ListaPlac> listaPlac = new ArrayList<>();
    ListaPlac listaPlacTemp;
    Wyplata wyplataTemp;
    ElementWyplaty elementWyplatyTemp;
    PodatkiSkladki podatkiSkladkiTemp;
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("ListaPlac")) {
            listaPlacTemp = new ListaPlac();
            listaPlacTemp.setListaWyplat(new ArrayList<Wyplata>());
        }
        if (qName.equalsIgnoreCase("Wyplata")) {
            wyplataTemp = new Wyplata();
            wyplataTemp.setElementWyplatyList(new ArrayList<ElementWyplaty>());
        }
        if (qName.equalsIgnoreCase("ElementWyplaty")) {
            elementWyplatyTemp = new ElementWyplaty();
            elementWyplatyTemp.setPodatkiSkladkiList(new ArrayList<PodatkiSkladki>());
        }
        if (qName.equalsIgnoreCase("PodatkiSkladki")) {
            podatkiSkladkiTemp = new PodatkiSkladki();

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        try {
            //LISTA PLAC
            if (qName.equalsIgnoreCase("NumerListy")) {
                listaPlacTemp.setNumerListy(currentValue.toString());
            }
            if (qName.equalsIgnoreCase("OpisListy")) {
                listaPlacTemp.setOpisListy(currentValue.toString());
            }
            if (qName.equalsIgnoreCase("DataListy")) {
                listaPlacTemp.setDataListy(sdf1.parse(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("DataWyplaty")) {
                listaPlacTemp.setDataWyplaty(sdf1.parse(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("OkresListyOd")) {
                listaPlacTemp.setOkresListyOd(sdf1.parse(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("OkresListyDo")) {
                listaPlacTemp.setOkresListyDo(sdf1.parse(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("KodWydzialu")) {
                listaPlacTemp.setKodWydzialu(currentValue.toString());
            }
            if (qName.equalsIgnoreCase("Zatwierdzona")) {
                listaPlacTemp.setZatwierdzona(Boolean.valueOf(currentValue.toString()));
            }
            //WYPLATA
            if (qName.equalsIgnoreCase("NumerWyplaty")) {
                wyplataTemp.setNumerWyplaty(Integer.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("KodPracownika")) {
                wyplataTemp.setKodPracownika(Integer.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("KodWydzialuKosztowego")) {
                wyplataTemp.setKodWydzialuKosztowego(currentValue.toString());
            }
            if(qName.equalsIgnoreCase("DoWyplaty")){
                wyplataTemp.setDoWyplaty(Double.valueOf(currentValue.toString()));
            }
            if(qName.equalsIgnoreCase("KwotaStawki")){
                wyplataTemp.setKwotaStawki(Double.valueOf(currentValue.toString()));
            }
            if(qName.equalsIgnoreCase("NazwiskoImie")){
                wyplataTemp.setNazwiskoImie(currentValue.toString());
            }
            if(qName.equalsIgnoreCase("PESEL")) {
                wyplataTemp.setPesel(currentValue.toString());
            }
            //ELEMENT WYPLATY
            if (qName.equalsIgnoreCase("NazwaElementu")) {
                elementWyplatyTemp.setNazwaElementu(currentValue.toString());
            }
            if (qName.equalsIgnoreCase("KodElementu")) {
                elementWyplatyTemp.setKodElementu(currentValue.toString());
            }
            if (qName.equalsIgnoreCase("OkresElementuOd")) {
                elementWyplatyTemp.setOkresElementuOd(sdf1.parse(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("OkresElementuDo")) {
                elementWyplatyTemp.setOkresElementuDo(sdf1.parse(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("CzasElementu")) {
                elementWyplatyTemp.setCzasElementu(currentValue.toString());
            }
            if (qName.equalsIgnoreCase("DniElementu")) {
                elementWyplatyTemp.setDniElementu(Integer.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("WartoscElementu")) {
                elementWyplatyTemp.setWartoscElementu(Double.valueOf(currentValue.toString()));
            }

            //PODATKI SKLADKI
            if (qName.equalsIgnoreCase("PodatekZaliczkaUS")) {
                podatkiSkladkiTemp.setPodatekZaliczkaUS(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("EmerytalnaPracownik")) {
                podatkiSkladkiTemp.setEmerytalnaPracownik(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("RentowaPracownik")) {
                podatkiSkladkiTemp.setRentowaPracownik(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("ChorobowaPracownik")) {
                podatkiSkladkiTemp.setChorobowaPracownik(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("WypadkowaPracownik")) {
                podatkiSkladkiTemp.setWypadkowaPracownik(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("EmerytalnaFirma")) {
                podatkiSkladkiTemp.setEmerytalnaFirma(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("RentowaFirma")) {
                podatkiSkladkiTemp.setRentowaFirma(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("ChorobowaFirma")) {
                podatkiSkladkiTemp.setChorobowaFirma(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("WypadkowaFirma")) {
                podatkiSkladkiTemp.setWypadkowaFirma(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("ZdrowotnaPracownik")) {
                podatkiSkladkiTemp.setZdrowotnaPracownik(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("FP")) {
                podatkiSkladkiTemp.setFP(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("FGSP")) {
                podatkiSkladkiTemp.setFGSP(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("FEP")) {
                podatkiSkladkiTemp.setFEP(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("PPKPracownik")) {
                podatkiSkladkiTemp.setPPKPracownik(Double.valueOf(currentValue.toString()));
            }
            if (qName.equalsIgnoreCase("PPKFirma")) {
                podatkiSkladkiTemp.setPPKFirma(Double.valueOf(currentValue.toString()));
            }
            //DODANIE ELEMENTÓW DO LIST
            if (qName.equalsIgnoreCase("PodatkiSkladki")) {
                elementWyplatyTemp.getPodatkiSkladkiList().add(podatkiSkladkiTemp);
            }
            if (qName.equalsIgnoreCase("ElementWyplaty")) {
                wyplataTemp.getElementWyplatyList().add(elementWyplatyTemp);
            }
            if (qName.equalsIgnoreCase("Wyplata")) {
                listaPlacTemp.getListaWyplat().add(wyplataTemp);
            }
            if (qName.equalsIgnoreCase("ListaPlac")) {
                listaPlac.add(listaPlacTemp);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }

    public List<ListaPlac> getListaPlac() {
        return listaPlac;
    }
}
