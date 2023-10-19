package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import md.enovaImport.sql.models.BookKeepingPatterns;

import java.util.Date;

public class PayListPatternFX {

    private final SimpleIntegerProperty importId = new SimpleIntegerProperty();
    private final SimpleIntegerProperty idList = new SimpleIntegerProperty();
    private final SimpleStringProperty numberList = new SimpleStringProperty();
    private final SimpleStringProperty descriptionList = new SimpleStringProperty();
    private final SimpleStringProperty dateList = new SimpleStringProperty();
    private final SimpleStringProperty datePayment = new SimpleStringProperty();
    private final SimpleStringProperty dateFrom = new SimpleStringProperty();
    private final SimpleStringProperty dateTo = new SimpleStringProperty();
    private final SimpleStringProperty departmentCode = new SimpleStringProperty();
    private final SimpleBooleanProperty agreeList = new SimpleBooleanProperty();
    private final SimpleIntegerProperty bookKeepingPatternType = new SimpleIntegerProperty();
    private final SimpleStringProperty bookKeepingPatterntTypeName = new SimpleStringProperty();
    private final SimpleBooleanProperty book = new SimpleBooleanProperty();
    private Button bookButton = new Button();
    private ComboBox<BookKeepingPatterns> bookKeepingPatternsComboBox = new ComboBox<>();

    public int getImportId() {
        return importId.get();
    }

    public SimpleIntegerProperty importIdProperty() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId.set(importId);
    }

    public PayListPatternFX() {
    }

    public boolean isBook() {
        return book.get();
    }

    public void setBook(boolean book) {
        this.book.set(book);
    }

    public SimpleBooleanProperty bookProperty() {
        return book;
    }

    public Button getBookButton() {
        return bookButton;
    }

    public void setBookButton(Button bookButton) {
        this.bookButton = bookButton;
    }

    public ComboBox<BookKeepingPatterns> getBookKeepingPatternsComboBox() {
        return bookKeepingPatternsComboBox;
    }

    public void setBookKeepingPatternsComboBox(ComboBox<BookKeepingPatterns> bookKeepingPatternsComboBox) {
        this.bookKeepingPatternsComboBox = bookKeepingPatternsComboBox;
    }

    public int getIdList() {
        return idList.get();
    }

    public void setIdList(int idList) {
        this.idList.set(idList);
    }

    public SimpleIntegerProperty idListProperty() {
        return idList;
    }

    public String getNumberList() {
        return numberList.get();
    }

    public void setNumberList(String numberList) {
        this.numberList.set(numberList);
    }

    public SimpleStringProperty numberListProperty() {
        return numberList;
    }

    public String getDescriptionList() {
        return descriptionList.get();
    }

    public void setDescriptionList(String descriptionList) {
        this.descriptionList.set(descriptionList);
    }

    public SimpleStringProperty descriptionListProperty() {
        return descriptionList;
    }

    public String getDateList() {
        return dateList.get();
    }

    public void setDateList(String dateList) {
        this.dateList.set(dateList);
    }

    public SimpleStringProperty dateListProperty() {
        return dateList;
    }

    public String getDatePayment() {
        return datePayment.get();
    }

    public void setDatePayment(String datePayment) {
        this.datePayment.set(datePayment);
    }

    public SimpleStringProperty datePaymentProperty() {
        return datePayment;
    }

    public String getDateFrom() {
        return dateFrom.get();
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom.set(dateFrom);
    }

    public SimpleStringProperty dateFromProperty() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo.get();
    }

    public void setDateTo(String dateTo) {
        this.dateTo.set(dateTo);
    }

    public SimpleStringProperty dateToProperty() {
        return dateTo;
    }

    public String getDepartmentCode() {
        return departmentCode.get();
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode.set(departmentCode);
    }

    public SimpleStringProperty departmentCodeProperty() {
        return departmentCode;
    }

    public boolean isAgreeList() {
        return agreeList.get();
    }

    public void setAgreeList(boolean agreeList) {
        this.agreeList.set(agreeList);
    }

    public SimpleBooleanProperty agreeListProperty() {
        return agreeList;
    }

    public int getBookKeepingPatternType() {
        return bookKeepingPatternType.get();
    }

    public void setBookKeepingPatternType(int bookKeepingPatternType) {
        this.bookKeepingPatternType.set(bookKeepingPatternType);
    }

    public SimpleIntegerProperty bookKeepingPatternTypeProperty() {
        return bookKeepingPatternType;
    }

    public String getBookKeepingPatterntTypeName() {
        return bookKeepingPatterntTypeName.get();
    }

    public void setBookKeepingPatterntTypeName(String bookKeepingPatterntTypeName) {
        this.bookKeepingPatterntTypeName.set(bookKeepingPatterntTypeName);
    }

    public SimpleStringProperty bookKeepingPatterntTypeNameProperty() {
        return bookKeepingPatterntTypeName;
    }
}
