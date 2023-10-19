package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import md.enovaImport.modelsFX.ImportModelFX;
import md.enovaImport.modelsFX.PayListPatternFX;
import md.enovaImport.modelsFX.SendMailFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.ImportModel;
import md.enovaImport.sql.models.PaylistPattern;
import md.enovaImport.sql.models.SendMail;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;

import java.sql.SQLException;
import java.util.List;

public class ListPatternWindowController {

    @FXML
    private TableView listTable;
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
    private ComboBox importComboBox;

    private final ObservableList<PayListPatternFX> payListPatternFXES = FXCollections.observableArrayList();
    private ImportDAO importDAO = new ImportDAO();
    private List<ImportModel> importList;
    private final ObservableList<ImportModelFX> importModelFXES = FXCollections.observableArrayList();
    public void initialize(){
        try {
            importList = importDAO.getImportList();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        importList.forEach(element -> {
            ImportModelFX importModelFX = new ImportModelFX();
            importModelFX.setId(element.getId());
            importModelFX.setOpis(element.getOpis());
            importModelFX.setData(element.getDataImportu().toString());
            importModelFXES.add(importModelFX);
        });

        importComboBox.getItems().addAll(importModelFXES);

        importComboBox.setCellFactory(cell -> new ListCell<ImportModelFX>() {
            final GridPane gridPane = new GridPane();
            final Label labelId = new Label();
            final Label labelOpis = new Label();
            final Label labelData = new Label();

            {
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(10, 30, 10), new ColumnConstraints(200, 200, 100));
                gridPane.add(labelId, 0, 1);
                gridPane.add(labelOpis, 1, 1);
            }

            @Override
            protected void updateItem(ImportModelFX importModelFX, boolean b) {
                super.updateItem(importModelFX, b);

                if (!b && importModelFX != null) {
                    labelId.setText(importModelFX.getId() + ".");
                    labelOpis.setText(importModelFX.getOpis());
                    setGraphic(gridPane);
                } else {
                    setGraphic(null);
                }
            }
        });
    }

    public void loadData() {
        try {
            ImportModelFX selectedImportModelFX = (ImportModelFX) importComboBox.getSelectionModel().getSelectedItem();
            if (selectedImportModelFX != null) {
                Integer importId = selectedImportModelFX.getId();
                importDAO.generateBookKeepingPatternList(importId);
                loadDataToTable(importId);
            } else {
                DialogUtils.errorDialog("Wybierz pozycję importu!");
            }
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void loadDataToTable(Integer importId) {
        updatePayMentPatternObservableList(importId);
        listTable.setItems(payListPatternFXES);



/*
        sendEmailTable.setItems(sendMailFXObservableList);
        sendEmailTableIdColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Integer>("importId"));
        sendEmailTableIdListColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Integer>("listId"));
        sendEmailTableNameListColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, String>("listName"));
        sendEmailTableCodeColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Integer>("code"));
        sendEmailTableNameColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, String>("name"));
        sendEmailTablePathColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, String>("pathFile"));
        sendEmailTableGenerateColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("generateButton"));
        sendEmailTableViewColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("viewButton"));
        sendEmailTableEmailColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("emailButton"));
        sendEmailTableDeleteColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("deleteButton"));

        sendEmailTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
        addListenerToButtons(importId);*/
    }

    private void updatePayMentPatternObservableList(Integer importId) {
        List<PaylistPattern> paylistPatterns = null;
        try {
            payListPatternFXES.clear();
            paylistPatterns = importDAO.getBookKeepingPayListPattern(importId);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        paylistPatterns.forEach(element -> {
            PayListPatternFX payListPatternFX = new PayListPatternFX();
            payListPatternFX.setImportId(element.getImportId());
            payListPatternFX.setIdList(element.getIdList());
            payListPatternFX.setNumberList(element.getNumberList());
            payListPatternFX.setDescriptionList(element.getDescriptionList());
            payListPatternFX.setDateList(element.getDateList().toString());
            payListPatternFX.setDatePayment(element.getDatePayment().toString());
            payListPatternFX.setDateFrom(element.getDateFrom().toString());
            payListPatternFX.setDateTo(element.getDateTo().toString());
            payListPatternFX.setDepartmentCode(element.getDepartmentCode());
            payListPatternFX.setAgreeList(element.getAgreeList());
            payListPatternFX.setBookKeepingPatternType(element.getBookKeepingPatternType());
            payListPatternFX.setBookKeepingPatterntTypeName(element.getBookKeepingPatterntTypeName());
            payListPatternFX.setBook(element.getBook());
            payListPatternFXES.add(payListPatternFX);
        });
    }
}
