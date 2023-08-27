package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import md.enovaImport.modelsFX.ImportModelFX;
import md.enovaImport.modelsFX.ListaPlacFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.ListaPlac;

import java.sql.SQLException;
import java.util.List;

public class ListWindowController {

    private final ObservableList<ListaPlacFX> listaPlacFXObservableList = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    @FXML
    private TableColumn listTableId;
    @FXML
    private TableColumn listTableNrColumn;
    @FXML
    private TableColumn listTableDescriptionColumn;
    @FXML
    private TableColumn listTableListDateColumn;
    @FXML
    private TableColumn listTablePaymentDateColumn;
    @FXML
    private TableColumn listTableDateFromColumn;
    @FXML
    private TableColumn listTableDateToColumn;
    @FXML
    private TableColumn listTableCodeColumn;
    @FXML
    private TableColumn listTableAgreeColumn;
    @FXML
    private TableView listTable;
    private MainWindowController mainWindowController;
    private Integer importId = 0;

    public void initialize() {
        if (importId > 0) {
            try {
                List<ListaPlac> listaPlacList = importDAO.getListaPlacListById(importId);

                listaPlacList.forEach(element -> {
                    ListaPlacFX modelFX = new ListaPlacFX();
                    modelFX.setIdListy(element.getIdListy());
                    modelFX.setNumerListy(element.getNumerListy());
                    modelFX.setOpisListy(element.getOpisListy());
                    modelFX.setDataListy(element.getDataListy().toString());
                    modelFX.setDataWyplaty(element.getDataWyplaty().toString());
                    modelFX.setOkresListyOd(element.getOkresListyOd().toString());
                    modelFX.setOkresListyDo(element.getOkresListyDo().toString());
                    modelFX.setKodWydzialu(element.getKodWydzialu());
                    modelFX.setZatwierdzona(element.getZatwierdzona().toString());
                    listaPlacFXObservableList.add(modelFX);
                });
            } catch (SQLException e) {
                e.printStackTrace();
                DialogUtils.errorDialog(e.getMessage());
            }
            listTableId.setCellValueFactory(new PropertyValueFactory<ListaPlac, Integer>("idListy"));
            listTableNrColumn.setCellValueFactory(new PropertyValueFactory<ListaPlacFX, String>("numerListy"));
            listTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ListaPlac, String>("opisListy"));
            listTableListDateColumn.setCellValueFactory(new PropertyValueFactory<ListaPlac, String>("dataListy"));
            listTablePaymentDateColumn.setCellValueFactory(new PropertyValueFactory<ListaPlac, String>("dataWyplaty"));
            listTableDateFromColumn.setCellValueFactory(new PropertyValueFactory<ListaPlac, String>("okresListyOd"));
            listTableDateToColumn.setCellValueFactory(new PropertyValueFactory<ListaPlac, String>("okresListyDo"));
            listTableCodeColumn.setCellValueFactory(new PropertyValueFactory<ListaPlac, String>("kodWydzialu"));
            listTableAgreeColumn.setCellValueFactory(new PropertyValueFactory<ListaPlac, String>("zatwierdzona"));
            listTable.setItems(listaPlacFXObservableList);

            listTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
        }
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void backButton() {
        mainWindowController.setImportWindow();
    }

    public void listDetailsButton() {
        ListaPlacFX listaPlacFX = (ListaPlacFX) listTable.getSelectionModel().getSelectedItem();
        if (listaPlacFX != null) {
            mainWindowController.setListaPlacId(listaPlacFX.getIdListy());
            mainWindowController.setPaymentWindow();
        } else {
            DialogUtils.choosePosition();
        }
    }
}
