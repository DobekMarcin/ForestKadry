package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import md.enovaImport.modelsFX.ElementWyplatyFX;
import md.enovaImport.modelsFX.ImportModelFX;
import md.enovaImport.modelsFX.WyplataFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.ElementWyplaty;
import md.enovaImport.xml.models.Wyplata;

import java.sql.SQLException;
import java.util.List;

public class PaymentWindowController {

    private final ObservableList<WyplataFX> wyplataObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn paymentTableAgreementColumn;
    @FXML
    private TextField filterTextField;
    @FXML
    private TableColumn paymentTableNameColumn;
    @FXML
    private TableColumn paymentTableWorkerIdColumn;
    @FXML
    private TableColumn paymentTablePaymentColumn;
    @FXML
    private TableColumn paymentTableIdColumn;
    @FXML
    private TableColumn paymentTableNrColumn;
    @FXML
    private TableColumn paymentTableWorkerColumn;
    @FXML
    private TableColumn paymentTableDepartmentColumn;
    @FXML
    private TableView paymentTable;

    private MainWindowController mainWindowController;
    private Integer importId = 0;
    private Integer listId = 0;
    private final ImportDAO importDAO = new ImportDAO();

    public void initialize() {
        if (listId > 0) {

            List<Wyplata> wyplataList = null;
            try {
                wyplataList = importDAO.getWyplataList(importId, listId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            wyplataList.forEach(element -> {
                WyplataFX wyplataFX = new WyplataFX();
                wyplataFX.setId(element.getId());
                wyplataFX.setNumerWyplaty(element.getNumerWyplaty());
                wyplataFX.setKodPracownika(element.getKodPracownika());
                wyplataFX.setKodWydzialuKosztowego(element.getKodWydzialuKosztowego());
                wyplataFX.setDoWyplaty(element.getDoWyplaty());
                wyplataFX.setNazwiskoImie(element.getNazwiskoImie());
                wyplataFX.setPesel(element.getPesel());
                wyplataFX.setKwotaStawki(element.getKwotaStawki());
                wyplataObservableList.add(wyplataFX);

            });
            paymentTableIdColumn.setCellValueFactory(new PropertyValueFactory<Wyplata, Integer>("id"));
            paymentTableNrColumn.setCellValueFactory(new PropertyValueFactory<Wyplata, Integer>("numerWyplaty"));
            paymentTableWorkerColumn.setCellValueFactory(new PropertyValueFactory<Wyplata, Integer>("kodPracownika"));
            paymentTableDepartmentColumn.setCellValueFactory(new PropertyValueFactory<Wyplata, String>("kodWydzialuKosztowego"));
            paymentTablePaymentColumn.setCellValueFactory(new PropertyValueFactory<Wyplata,Double>("doWyplaty"));
            paymentTableNameColumn.setCellValueFactory(new PropertyValueFactory<Wyplata,String>("nazwiskoImie"));
            paymentTableWorkerIdColumn.setCellValueFactory(new PropertyValueFactory<Wyplata,String>("pesel"));
            paymentTableAgreementColumn.setCellValueFactory(new PropertyValueFactory<Wyplata,Double>("kwotaStawki"));

            paymentTable.setItems(wyplataObservableList);
            paymentTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));

            FilteredList<WyplataFX> filteredData = new FilteredList<>(wyplataObservableList,p->true);
            filterTextField.textProperty().addListener((observable,oldValue,newValue)->{
                filteredData.setPredicate(wyplata->{
                    if(newValue == null || newValue.isEmpty()) return true;
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(wyplata.getNazwiskoImie().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if (wyplata.getPesel().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if (String.valueOf(wyplata.getKodPracownika()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<WyplataFX> sortedList = new SortedList<>(filteredData);
            sortedList.comparatorProperty().bind(paymentTable.comparatorProperty());
            paymentTable.setItems(sortedList);
        }
    }

    public void detailButton() {
        WyplataFX wyplataFX = (WyplataFX) paymentTable.getSelectionModel().getSelectedItem();
        if (wyplataFX != null) {
            mainWindowController.setPaymentId(wyplataFX.getId());
            mainWindowController.setElementWindow();
        } else {
            DialogUtils.choosePosition();
        }
    }


    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public void backButton() {
        mainWindowController.setListWindow();
    }
}
