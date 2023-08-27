package md.enovaImport.modelsFX;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class ElementSlownikFX {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty nazwa = new SimpleStringProperty();
    private SimpleStringProperty alias = new SimpleStringProperty();
    private BooleanProperty czyDrukowac = new SimpleBooleanProperty();
    private SimpleIntegerProperty kolejnosc = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public SimpleStringProperty nazwaProperty() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public String getAlias() {
        return alias.get();
    }

    public SimpleStringProperty aliasProperty() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias.set(alias);
    }

    public boolean isCzyDrukowac() {
        return czyDrukowac.get();
    }

    public BooleanProperty czyDrukowacProperty() {
        return czyDrukowac;
    }

    public void setCzyDrukowac(boolean czyDrukowac) {
        this.czyDrukowac.set(czyDrukowac);
    }

    public int getKolejnosc() {
        return kolejnosc.get();
    }

    public SimpleIntegerProperty kolejnoscProperty() {
        return kolejnosc;
    }

    public void setKolejnosc(int kolejnosc) {
        this.kolejnosc.set(kolejnosc);
    }
}
