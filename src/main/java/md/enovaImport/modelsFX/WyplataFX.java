package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class WyplataFX {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty numerWyplaty = new SimpleIntegerProperty();
    private SimpleIntegerProperty kodPracownika = new SimpleIntegerProperty();
    private  SimpleStringProperty kodWydzialuKosztowego = new SimpleStringProperty();
    private SimpleDoubleProperty doWyplaty = new SimpleDoubleProperty();
    private SimpleStringProperty nazwiskoImie = new SimpleStringProperty();
    private SimpleStringProperty pesel = new SimpleStringProperty();
    private SimpleDoubleProperty kwotaStawki = new SimpleDoubleProperty();

    public double getKwotaStawki() {
        return kwotaStawki.get();
    }

    public SimpleDoubleProperty kwotaStawkiProperty() {
        return kwotaStawki;
    }

    public void setKwotaStawki(double kwotaStawki) {
        this.kwotaStawki.set(kwotaStawki);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getNumerWyplaty() {
        return numerWyplaty.get();
    }

    public SimpleIntegerProperty numerWyplatyProperty() {
        return numerWyplaty;
    }

    public void setNumerWyplaty(int numerWyplaty) {
        this.numerWyplaty.set(numerWyplaty);
    }

    public int getKodPracownika() {
        return kodPracownika.get();
    }

    public SimpleIntegerProperty kodPracownikaProperty() {
        return kodPracownika;
    }

    public void setKodPracownika(int kodPracownika) {
        this.kodPracownika.set(kodPracownika);
    }

    public String getKodWydzialuKosztowego() {
        return kodWydzialuKosztowego.get();
    }

    public SimpleStringProperty kodWydzialuKosztowegoProperty() {
        return kodWydzialuKosztowego;
    }

    public void setKodWydzialuKosztowego(String kodWydzialuKosztowego) {
        this.kodWydzialuKosztowego.set(kodWydzialuKosztowego);
    }

    public double getDoWyplaty() {
        return doWyplaty.get();
    }

    public SimpleDoubleProperty doWyplatyProperty() {
        return doWyplaty;
    }

    public void setDoWyplaty(double doWyplaty) {
        this.doWyplaty.set(doWyplaty);
    }

    public String getNazwiskoImie() {
        return nazwiskoImie.get();
    }

    public SimpleStringProperty nazwiskoImieProperty() {
        return nazwiskoImie;
    }

    public void setNazwiskoImie(String nazwiskoImie) {
        this.nazwiskoImie.set(nazwiskoImie);
    }

    public String getPesel() {
        return pesel.get();
    }

    public SimpleStringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }
}
