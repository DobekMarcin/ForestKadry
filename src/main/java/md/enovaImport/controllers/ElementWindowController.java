package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import md.enovaImport.modelsFX.ElementWyplatyFX;
import md.enovaImport.modelsFX.ListaPlacFX;
import md.enovaImport.modelsFX.WyplataFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.ElementWyplaty;
import md.enovaImport.xml.models.ListaPlac;

import java.sql.SQLException;
import java.util.List;

public class ElementWindowController {

    private final ObservableList<ElementWyplatyFX> elementWyplatyObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView elementTable;
    @FXML
    private TableColumn elementTableIdColumn;
    @FXML
    private TableColumn elementTableNameColumn;
    @FXML
    private TableColumn elementTableCodeColumn;
    @FXML
    private TableColumn elementTablePeriodFromColumn;
    @FXML
    private TableColumn elementTablePeriodToColumn;
    @FXML
    private TableColumn elementTableTimeColumn;
    @FXML
    private TableColumn elementTableDayColumn;
    @FXML
    private TableColumn elementTableValueColumn;
    private MainWindowController mainWindowController;
    private Integer importId=0;
    private Integer listId=0;
    private Integer paymentId=0;
    private ImportDAO importDAO = new ImportDAO();

    public void initialize() {
        if (listId >0) {

            List<ElementWyplaty> elementWyplatyList = null;
                try {
                    elementWyplatyList = importDAO.getElementListById(importId, listId,paymentId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                elementWyplatyList.forEach(element -> {
                    ElementWyplatyFX elementWyplatyFX = new ElementWyplatyFX();
                    elementWyplatyFX.setIdElementu(element.getIdElementu());
                    elementWyplatyFX.setNazwaElementu(element.getNazwaElementu());
                    elementWyplatyFX.setKodElementu(element.getKodElementu());
                    elementWyplatyFX.setOkresElementuOd(element.getOkresElementuOd().toString());
                    elementWyplatyFX.setOkresElementuDo(element.getOkresElementuDo().toString());
                    elementWyplatyFX.setCzasElementu(element.getCzasElementu());
                    elementWyplatyFX.setDniElementu(element.getDniElementu());
                    elementWyplatyFX.setWartoscElementu(element.getWartoscElementu());
                    elementWyplatyObservableList.add(elementWyplatyFX);
                });
                elementTableIdColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,Integer>("idElementu"));
                elementTableNameColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,String>("nazwaElementu"));
                elementTableCodeColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,String>("kodElementu"));
                elementTablePeriodFromColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,String>("okresElementuOd"));
                elementTablePeriodToColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,String>("okresElementuDo"));
                elementTableTimeColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,String>("czasElementu"));
                elementTableDayColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,Integer>("dniElementu"));
                elementTableValueColumn.setCellValueFactory(new PropertyValueFactory<ElementWyplaty,Double>("wartoscElementu"));

                elementTable.setItems(elementWyplatyObservableList);

                elementTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
            }

        }

        public Integer getImportId () {
            return importId;
        }

        public void setImportId (Integer importId){
            this.importId = importId;
        }

        public Integer getListId () {
            return listId;
        }

        public void setListId (Integer listId){
            this.listId = listId;
        }

        public MainWindowController getMainWindowController () {
            return mainWindowController;
        }

        public void setMainWindowController (MainWindowController mainWindowController){
            this.mainWindowController = mainWindowController;
        }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public void backButton() {
        mainWindowController.setPaymentWindow();
    }

    public void detailButton() {
        ElementWyplatyFX elementWyplatyFX = (ElementWyplatyFX) elementTable.getSelectionModel().getSelectedItem();
        if (elementWyplatyFX != null) {
            mainWindowController.setElementId(elementWyplatyFX.getIdElementu());
            mainWindowController.setTaxWindow();
        } else {
            DialogUtils.choosePosition();
        }
    }
}
