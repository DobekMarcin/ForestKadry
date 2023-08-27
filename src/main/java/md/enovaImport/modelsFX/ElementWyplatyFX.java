package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ElementWyplatyFX {
    private SimpleIntegerProperty idElementu = new SimpleIntegerProperty();
    private SimpleStringProperty nazwaElementu = new SimpleStringProperty();
    private SimpleStringProperty kodElementu = new SimpleStringProperty();
    private SimpleStringProperty okresElementuOd = new SimpleStringProperty();
    private SimpleStringProperty okresElementuDo = new SimpleStringProperty();
    private SimpleStringProperty czasElementu = new SimpleStringProperty();
    private SimpleIntegerProperty dniElementu = new SimpleIntegerProperty();
    private SimpleDoubleProperty wartoscElementu = new SimpleDoubleProperty();

    public int getIdElementu() {
        return idElementu.get();
    }

    public SimpleIntegerProperty idElementuProperty() {
        return idElementu;
    }

    public void setIdElementu(int idElementu) {
        this.idElementu.set(idElementu);
    }

    public String getNazwaElementu() {
        return nazwaElementu.get();
    }

    public SimpleStringProperty nazwaElementuProperty() {
        return nazwaElementu;
    }

    public void setNazwaElementu(String nazwaElementu) {
        this.nazwaElementu.set(nazwaElementu);
    }

    public String getKodElementu() {
        return kodElementu.get();
    }

    public SimpleStringProperty kodElementuProperty() {
        return kodElementu;
    }

    public void setKodElementu(String kodElementu) {
        this.kodElementu.set(kodElementu);
    }

    public String getOkresElementuOd() {
        return okresElementuOd.get();
    }

    public SimpleStringProperty okresElementuOdProperty() {
        return okresElementuOd;
    }

    public void setOkresElementuOd(String okresElementuOd) {
        this.okresElementuOd.set(okresElementuOd);
    }

    public String getOkresElementuDo() {
        return okresElementuDo.get();
    }

    public SimpleStringProperty okresElementuDoProperty() {
        return okresElementuDo;
    }

    public void setOkresElementuDo(String okresElementuDo) {
        this.okresElementuDo.set(okresElementuDo);
    }

    public String getCzasElementu() {
        return czasElementu.get();
    }

    public SimpleStringProperty czasElementuProperty() {
        return czasElementu;
    }

    public void setCzasElementu(String czasElementu) {
        this.czasElementu.set(czasElementu);
    }

    public int getDniElementu() {
        return dniElementu.get();
    }

    public SimpleIntegerProperty dniElementuProperty() {
        return dniElementu;
    }

    public void setDniElementu(int dniElementu) {
        this.dniElementu.set(dniElementu);
    }

    public double getWartoscElementu() {
        return wartoscElementu.get();
    }

    public SimpleDoubleProperty wartoscElementuProperty() {
        return wartoscElementu;
    }

    public void setWartoscElementu(double wartoscElementu) {
        this.wartoscElementu.set(wartoscElementu);
    }
}
