package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;


public class DepartmentFX {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty code = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private Button distributor = new Button();

    public DepartmentFX() {
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

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
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

    public Button getDistributor() {
        return distributor;
    }

    public void setDistributor(Button distributor) {
        this.distributor = distributor;
    }
}
