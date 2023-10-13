package md.enovaImport.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import md.enovaImport.Stage.MainStage;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.xml.models.Element;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MainWindowController {

    private static final String IMPORT_WINDOW_FXML = "/FXML/ImportWindow.fxml";
    private static final String LIST_WINDOW_FXML="/FXML/ListWindow.fxml";
    private static final String ELEMENT_LIST_WINDOW_FXML="/FXML/ElementWindow.fxml";
    private static final String PAYMENT_LIST_WINDOW="/FXML/PaymentWindow.fxml";
    private static final String TAX_LIST_WINDOW="/FXML/TaxWindow.fxml";
    private static final String ELEMENT_DIC_WINDOW="/FXML/ElementDicWindow.fxml";
    private static final String PERSON_LIST_WINDOW="/FXML/PersonListWindow.fxml";
    private static final String EMAIL_GENERATE_WINDOW="/FXML/SendEmailWindow.fxml";
    private static final String BOOKKEEPING_PATTERN_WINDOW="/FXML/BookkeepingWindow.fxml";

    @FXML
    private TextField statusTextField;
    @FXML
    private ToggleButton elementDicToggleButton;
    @FXML
    private ToggleButton importToggleButton;
    @FXML
    private BorderPane borderPane;
    private Stage primaryStage;
    private File file;
    private ImportDAO importDAO = new ImportDAO();
    private Integer importDetailId;
    private Integer listaPlacId;
    private Integer paymentId;
    private Integer elementId;
    private SimpleBooleanProperty importToggleButtonProperty = new SimpleBooleanProperty();

    public void initialize() {
        setImportWindow();
    }

    public void setImportWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((IMPORT_WINDOW_FXML)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            setStatus("Importowanie danych");
            Pane bordPane = fxmlLoader.load();
            ImportWindowController importWindowController = fxmlLoader.getController();
            importWindowController.setMainWindowController(this);
            borderPane.setCenter(bordPane);

        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void setStatus(String status) {
        statusTextField.setText(status);
        statusTextField.setFocusTraversable(false);
    }

    public void setListWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((LIST_WINDOW_FXML)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Pane bordPane = fxmlLoader.load();
            ListWindowController listWindowController = fxmlLoader.getController();
            listWindowController.setMainWindowController(this);
            listWindowController.setImportId(importDetailId);
            listWindowController.initialize();
            borderPane.setCenter(bordPane);
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void setElementWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((ELEMENT_LIST_WINDOW_FXML)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Pane bordPane = fxmlLoader.load();
            ElementWindowController elementWindowController = fxmlLoader.getController();
            elementWindowController.setMainWindowController(this);
            elementWindowController.setImportId(importDetailId);
            elementWindowController.setListId(listaPlacId);
            elementWindowController.setPaymentId(paymentId);
            elementWindowController.initialize();
            borderPane.setCenter(bordPane);
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void setTaxWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((TAX_LIST_WINDOW)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Pane bordPane = fxmlLoader.load();
            TaxWindowController taxWindowController = fxmlLoader.getController();
            taxWindowController.setMainWindowController(this);
            taxWindowController.setImportId(importDetailId);
            taxWindowController.setListId(listaPlacId);
            taxWindowController.setPaymentId(paymentId);
            taxWindowController.setElementId(elementId);
            taxWindowController.initialize();
            borderPane.setCenter(bordPane);
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void setPaymentWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((PAYMENT_LIST_WINDOW)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Pane bordPane = fxmlLoader.load();
            PaymentWindowController paymentWindowController = fxmlLoader.getController();
            paymentWindowController.setMainWindowController(this);
            paymentWindowController.setImportId(importDetailId);
            paymentWindowController.setListId(listaPlacId);
            paymentWindowController.initialize();
            borderPane.setCenter(bordPane);
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
    public void elementDicWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((ELEMENT_DIC_WINDOW)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Pane bordPane = fxmlLoader.load();
            ElementDicWindowController elementDicWindowController = fxmlLoader.getController();
            elementDicWindowController.setMainWindowController(this);
            borderPane.setCenter(bordPane);
            Integer newPosition = importDAO.getNewElementDictionaryPositionCount();
            if(newPosition>0)
                DialogUtils.informationDialog(DialogUtils.getBundle().getString("buffer.newposition"));

        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void personListWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((PERSON_LIST_WINDOW)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Pane bordPane = fxmlLoader.load();
            PersonListWindowController personListWindowController = fxmlLoader.getController();
            personListWindowController.setMainWindowController(this);
            borderPane.setCenter(bordPane);
            Integer newPosition = importDAO.getCountNewPersonPersonList();
            if(newPosition>0)
                DialogUtils.informationDialog(DialogUtils.getBundle().getString("person.newpostion"));

        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void closeApp() {
        Platform.exit();
        System.exit(0);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void importWindow() {
        if(importToggleButton.isSelected()==true){
        setImportWindow();

    }}

    public void sendEmailWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((EMAIL_GENERATE_WINDOW)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            setStatus("Generuj email");
            Pane bordPane = fxmlLoader.load();
            SendEmailWindowController sendEmailWindowController = fxmlLoader.getController();
            borderPane.setCenter(bordPane);
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void bookkeepingPatterns(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((BOOKKEEPING_PATTERN_WINDOW)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            setStatus("Wzorce księgowe");
            Pane bordPane = fxmlLoader.load();
            BookkeepingWindowController bookkeepingWindowController = fxmlLoader.getController();
            borderPane.setCenter(bordPane);
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void deleteDataFromDatabase() {

        int result = DialogUtils.deleteAllImport();
        if(result==1){

        try {
            importDAO.deleteImportList();
            importDAO.deleteWyplata();
            importDAO.deleteElementWyplaty();
            importDAO.deleteListaPlac();
            importDAO.deletePodatkiSkladki();
            importDAO.deleteElementWyplatySlownik();
            importDAO.deletePersonSlownik();
            importDAO.deleteSendMail();

            importDAO.addNewElementDictionary(new Element("Podatek zaliczka US"));
            importDAO.addNewElementDictionary(new Element("Emerytalna pracownik"));
            importDAO.addNewElementDictionary(new Element("Rentowa pracownik"));
            importDAO.addNewElementDictionary(new Element("Chorobowa pracownik"));
            importDAO.addNewElementDictionary(new Element("Wypadkowa pracownik"));
            importDAO.addNewElementDictionary(new Element("Emerytalna firma"));
            importDAO.addNewElementDictionary(new Element("Rentowa firma"));
            importDAO.addNewElementDictionary(new Element("Chorobowa firma"));
            importDAO.addNewElementDictionary(new Element("Wypadkowa firma"));
            importDAO.addNewElementDictionary(new Element("Zdrowotna pracownik"));
            importDAO.addNewElementDictionary(new Element("FP"));
            importDAO.addNewElementDictionary(new Element("FGSP"));
            importDAO.addNewElementDictionary(new Element("FEP"));
            importDAO.addNewElementDictionary(new Element("PPKPracownik"));
            importDAO.addNewElementDictionary(new Element("PPK firma"));

        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        initialize();
    }}

    public Integer getImportDetailId() {
        return importDetailId;
    }

    public void setImportDetailId(Integer importDetailId) {
        this.importDetailId = importDetailId;
    }

    public Integer getListaPlacId() {
        return listaPlacId;
    }

    public void setListaPlacId(Integer listaPlacId) {
        this.listaPlacId = listaPlacId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }



}
