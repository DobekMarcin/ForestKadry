package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonMailFX {

    private SimpleIntegerProperty id_importu = new SimpleIntegerProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleIntegerProperty code = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty path = new SimpleStringProperty();
    private SimpleBooleanProperty isFile = new SimpleBooleanProperty();
    private SimpleBooleanProperty isSend = new SimpleBooleanProperty();

    public int getId_importu() {
        return id_importu.get();
    }

    public SimpleIntegerProperty id_importuProperty() {
        return id_importu;
    }

    public void setId_importu(int id_importu) {
        this.id_importu.set(id_importu);
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

    public String getPath() {
        return path.get();
    }

    public SimpleStringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public boolean isIsFile() {
        return isFile.get();
    }

    public SimpleBooleanProperty isFileProperty() {
        return isFile;
    }

    public void setIsFile(boolean isFile) {
        this.isFile.set(isFile);
    }

    public boolean isIsSend() {
        return isSend.get();
    }

    public SimpleBooleanProperty isSendProperty() {
        return isSend;
    }

    public void setIsSend(boolean isSend) {
        this.isSend.set(isSend);
    }
}
