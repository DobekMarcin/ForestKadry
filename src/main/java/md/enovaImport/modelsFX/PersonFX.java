package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonFX {

    private SimpleIntegerProperty code = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();
    private SimpleBooleanProperty send = new SimpleBooleanProperty();

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

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public boolean isSend() {
        return send.get();
    }

    public SimpleBooleanProperty sendProperty() {
        return send;
    }

    public void setSend(boolean send) {
        this.send.set(send);
    }
}
