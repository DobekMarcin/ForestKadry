package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PartsFX {

    private SimpleIntegerProperty patternId = new SimpleIntegerProperty();
    private SimpleIntegerProperty positionId = new SimpleIntegerProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private  SimpleIntegerProperty partsId= new SimpleIntegerProperty();
    private SimpleStringProperty partsName = new SimpleStringProperty();
    private SimpleStringProperty symbol = new SimpleStringProperty();

    public PartsFX() {
    }

    public int getPatternId() {
        return patternId.get();
    }

    public SimpleIntegerProperty patternIdProperty() {
        return patternId;
    }

    public void setPatternId(int patternId) {
        this.patternId.set(patternId);
    }

    public int getPositionId() {
        return positionId.get();
    }

    public SimpleIntegerProperty positionIdProperty() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId.set(positionId);
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

    public int getPartsId() {
        return partsId.get();
    }

    public SimpleIntegerProperty partsIdProperty() {
        return partsId;
    }

    public void setPartsId(int partsId) {
        this.partsId.set(partsId);
    }

    public String getPartsName() {
        return partsName.get();
    }

    public SimpleStringProperty partsNameProperty() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName.set(partsName);
    }

    public String getSymbol() {
        return symbol.get();
    }

    public SimpleStringProperty symbolProperty() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol.set(symbol);
    }
}
