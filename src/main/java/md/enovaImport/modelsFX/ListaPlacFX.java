package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ListaPlacFX {

    private SimpleIntegerProperty idListy = new SimpleIntegerProperty();
    private SimpleStringProperty numerListy = new SimpleStringProperty();
    private SimpleStringProperty opisListy = new SimpleStringProperty();
    private SimpleStringProperty dataListy = new SimpleStringProperty();
    private SimpleStringProperty dataWyplaty = new SimpleStringProperty();
    private SimpleStringProperty okresListyOd = new SimpleStringProperty();
    private SimpleStringProperty okresListyDo = new SimpleStringProperty();
    private SimpleStringProperty kodWydzialu = new SimpleStringProperty();
    private SimpleStringProperty zatwierdzona = new SimpleStringProperty();

    public int getIdListy() {
        return idListy.get();
    }

    public SimpleIntegerProperty idListyProperty() {
        return idListy;
    }

    public void setIdListy(int idListy) {
        this.idListy.set(idListy);
    }

    public String getNumerListy() {
        return numerListy.get();
    }

    public SimpleStringProperty numerListyProperty() {
        return numerListy;
    }

    public void setNumerListy(String numerListy) {
        this.numerListy.set(numerListy);
    }

    public String getOpisListy() {
        return opisListy.get();
    }

    public SimpleStringProperty opisListyProperty() {
        return opisListy;
    }

    public void setOpisListy(String opisListy) {
        this.opisListy.set(opisListy);
    }

    public String getDataListy() {
        return dataListy.get();
    }

    public SimpleStringProperty dataListyProperty() {
        return dataListy;
    }

    public void setDataListy(String dataListy) {
        this.dataListy.set(dataListy);
    }

    public String getDataWyplaty() {
        return dataWyplaty.get();
    }

    public SimpleStringProperty dataWyplatyProperty() {
        return dataWyplaty;
    }

    public void setDataWyplaty(String dataWyplaty) {
        this.dataWyplaty.set(dataWyplaty);
    }

    public String getOkresListyOd() {
        return okresListyOd.get();
    }

    public SimpleStringProperty okresListyOdProperty() {
        return okresListyOd;
    }

    public void setOkresListyOd(String okresListyOd) {
        this.okresListyOd.set(okresListyOd);
    }

    public String getOkresListyDo() {
        return okresListyDo.get();
    }

    public SimpleStringProperty okresListyDoProperty() {
        return okresListyDo;
    }

    public void setOkresListyDo(String okresListyDo) {
        this.okresListyDo.set(okresListyDo);
    }

    public String getKodWydzialu() {
        return kodWydzialu.get();
    }

    public SimpleStringProperty kodWydzialuProperty() {
        return kodWydzialu;
    }

    public void setKodWydzialu(String kodWydzialu) {
        this.kodWydzialu.set(kodWydzialu);
    }

    public String getZatwierdzona() {
        return zatwierdzona.get();
    }

    public SimpleStringProperty zatwierdzonaProperty() {
        return zatwierdzona;
    }

    public void setZatwierdzona(String zatwierdzona) {
        this.zatwierdzona.set(zatwierdzona);
    }
}
