package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class SendMailFX {

    private SimpleIntegerProperty importId = new SimpleIntegerProperty();
    private SimpleIntegerProperty listId = new SimpleIntegerProperty();
    private SimpleStringProperty listName = new SimpleStringProperty();
    private SimpleIntegerProperty code = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty pathFile = new SimpleStringProperty();
    private SimpleBooleanProperty isFile = new SimpleBooleanProperty();
    private SimpleBooleanProperty isSend = new SimpleBooleanProperty();
    private SimpleIntegerProperty amountId = new SimpleIntegerProperty();
    private SimpleDoubleProperty amount = new SimpleDoubleProperty();
    private SimpleStringProperty paymentDate = new SimpleStringProperty();
    private SimpleStringProperty listDate = new SimpleStringProperty();
    private SimpleDoubleProperty agreementAmount = new SimpleDoubleProperty();

    public double getAgreementAmount() {
        return agreementAmount.get();
    }

    public SimpleDoubleProperty agreementAmountProperty() {
        return agreementAmount;
    }

    public void setAgreementAmount(double agreementAmount) {
        this.agreementAmount.set(agreementAmount);
    }

    public String getPaymentDate() {
        return paymentDate.get();
    }

    public SimpleStringProperty paymentDateProperty() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate.set(paymentDate);
    }

    public String getListDate() {
        return listDate.get();
    }

    public SimpleStringProperty listDateProperty() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate.set(listDate);
    }

    private Button generateButton = new Button();
    private Button viewButton = new Button();
    private Button emailButton = new Button();
    private Button deleteButton = new Button();

    public int getAmountId() {
        return amountId.get();
    }

    public SimpleIntegerProperty amountIdProperty() {
        return amountId;
    }

    public void setAmountId(int amountId) {
        this.amountId.set(amountId);
    }

    public Button getViewButton() {
        return viewButton;
    }

    public void setViewButton(Button viewButton) {
        this.viewButton = viewButton;
    }

    public Button getEmailButton() {
        return emailButton;
    }

    public void setEmailButton(Button emailButton) {
        this.emailButton = emailButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public Button getGenerateButton() {
        return generateButton;
    }

    public void setGenerateButton(Button generateButton) {
        this.generateButton = generateButton;
    }

    public int getImportId() {
        return importId.get();
    }

    public SimpleIntegerProperty importIdProperty() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId.set(importId);
    }

    public int getListId() {
        return listId.get();
    }

    public SimpleIntegerProperty listIdProperty() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId.set(listId);
    }

    public String getListName() {
        return listName.get();
    }

    public SimpleStringProperty listNameProperty() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName.set(listName);
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

    public String getPathFile() {
        return pathFile.get();
    }

    public SimpleStringProperty pathFileProperty() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile.set(pathFile);
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

    public boolean getIsSend() {
        return isSend.get();
    }

    public SimpleBooleanProperty isSendProperty() {
        return isSend;
    }

    public void setIsSend(boolean isSend) {
        this.isSend.set(isSend);
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }
}
