package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class BookKeepingPatternsPositionFX {

    private final SimpleIntegerProperty patternId = new SimpleIntegerProperty();
    private final SimpleIntegerProperty positionId = new SimpleIntegerProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty accountBlame = new SimpleStringProperty();
    private final SimpleStringProperty accountHas = new SimpleStringProperty();
    private final SimpleBooleanProperty distributor = new SimpleBooleanProperty();
    private final SimpleStringProperty accountDistributor = new SimpleStringProperty();
    private final SimpleBooleanProperty payment = new SimpleBooleanProperty();

    private Button partsButton = new Button();

    public Button getPartsButton() {
        return partsButton;
    }

    public void setPartsButton(Button partsButton) {
        this.partsButton = partsButton;
    }

    public BookKeepingPatternsPositionFX() {
    }

    public int getPatternId() {
        return patternId.get();
    }

    public void setPatternId(int patternId) {
        this.patternId.set(patternId);
    }

    public SimpleIntegerProperty patternIdProperty() {
        return patternId;
    }

    public int getPositionId() {
        return positionId.get();
    }

    public void setPositionId(int positionId) {
        this.positionId.set(positionId);
    }

    public SimpleIntegerProperty positionIdProperty() {
        return positionId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getAccountBlame() {
        return accountBlame.get();
    }

    public void setAccountBlame(String accountBlame) {
        this.accountBlame.set(accountBlame);
    }

    public SimpleStringProperty accountBlameProperty() {
        return accountBlame;
    }

    public String getAccountHas() {
        return accountHas.get();
    }

    public void setAccountHas(String accountHas) {
        this.accountHas.set(accountHas);
    }

    public SimpleStringProperty accountHasProperty() {
        return accountHas;
    }

    public boolean isDistributor() {
        return distributor.get();
    }

    public void setDistributor(boolean distributor) {
        this.distributor.set(distributor);
    }

    public SimpleBooleanProperty distributorProperty() {
        return distributor;
    }

    public String getAccountDistributor() {
        return accountDistributor.get();
    }

    public void setAccountDistributor(String accountDistributor) {
        this.accountDistributor.set(accountDistributor);
    }

    public SimpleStringProperty accountDistributorProperty() {
        return accountDistributor;
    }

    public boolean isPayment() {
        return payment.get();
    }

    public SimpleBooleanProperty paymentProperty() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment.set(payment);
    }
}
