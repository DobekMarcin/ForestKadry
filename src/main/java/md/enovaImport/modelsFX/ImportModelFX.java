package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ImportModelFX {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty opis = new SimpleStringProperty();
    private SimpleStringProperty  data = new SimpleStringProperty();
    private SimpleBooleanProperty email = new SimpleBooleanProperty();

    public ImportModelFX() {
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

    public String getOpis() {
        return opis.get();
    }

    public SimpleStringProperty opisProperty() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis.set(opis);
    }

    public String getData() {
        return data.get();
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    @Override
    public String toString() {
        return id.get()+"."+opis.get();

    }

    public boolean isEmail() {
        return email.get();
    }

    public SimpleBooleanProperty emailProperty() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email.set(email);
    }
}
