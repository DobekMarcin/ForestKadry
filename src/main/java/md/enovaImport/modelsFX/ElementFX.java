package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleStringProperty;

public class ElementFX {

    private SimpleStringProperty elementName = new SimpleStringProperty();

    public String getElementName() {
        return elementName.get();
    }

    public SimpleStringProperty elementNameProperty() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName.set(elementName);
    }
}
