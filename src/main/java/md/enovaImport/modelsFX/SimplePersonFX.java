package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimplePersonFX {

    private SimpleIntegerProperty code = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();

    public int getCode() {
        return code.get();
    }

    public SimpleIntegerProperty codeProperty() {
        return code;
    }

    public void setCode(int code) {
        this.code.set(code);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
