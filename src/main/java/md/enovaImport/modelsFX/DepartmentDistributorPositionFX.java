package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DepartmentDistributorPositionFX {

    private SimpleIntegerProperty korg_id = new SimpleIntegerProperty();
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty account = new SimpleStringProperty();

    public DepartmentDistributorPositionFX() {
    }

    public int getKorg_id() {
        return korg_id.get();
    }

    public SimpleIntegerProperty korg_idProperty() {
        return korg_id;
    }

    public void setKorg_id(int korg_id) {
        this.korg_id.set(korg_id);
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

    public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }
}
