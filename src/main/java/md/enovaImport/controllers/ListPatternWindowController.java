package md.enovaImport.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import md.enovaImport.modelsFX.*;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.BookKeepingPatterns;
import md.enovaImport.sql.models.ImportModel;
import md.enovaImport.sql.models.PaylistPattern;
import md.enovaImport.sql.models.SendMail;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListPatternWindowController {

    private final ObservableList<PayListPatternFX> payListPatternFXES = FXCollections.observableArrayList();
    private final ObservableList<ImportModelFX> importModelFXES = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    @FXML
    private TableColumn listTableDescriptionList;
    @FXML
    private TableColumn listTableImportId;
    @FXML
    private TableColumn listTableListId;
    @FXML
    private TableColumn listTableListName;
    @FXML
    private TableColumn listTableListDateColumn;
    @FXML
    private TableColumn listTableCodeList;
    @FXML
    private TableColumn listTableAgree;
    @FXML
    private TableColumn listTableComboBox;
    @FXML
    private TableColumn listTableBookButton;
    @FXML
    private TableView listTable;
    @FXML
    private ComboBox importComboBox;
    private List<ImportModel> importList;

    public void initialize() {
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

    public void initializeComboBox(PayListPatternFX payListPatternFX) {
        ComboBox comboBox = payListPatternFX.getBookKeepingPatternsComboBox();
        ObservableList<BookKeepingPatternsFX> bookKeepingPatternsFXES = FXCollections.observableArrayList();
        List<BookKeepingPatterns> bookKeepingPatterns = new ArrayList<>();
        try {
            bookKeepingPatterns = importDAO.getBookKeepingPatterns();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bookKeepingPatterns.forEach(element -> {
            BookKeepingPatternsFX bookKeepingPatternsFX = new BookKeepingPatternsFX();
            bookKeepingPatternsFX.setId(element.getId());
            bookKeepingPatternsFX.setPatternName(element.getPatterName());
            bookKeepingPatternsFX.setPatternType(element.getPatternType());
            bookKeepingPatternsFX.setPatternComment(element.getPatternComment());
            bookKeepingPatternsFXES.add(bookKeepingPatternsFX);
        });
        comboBox.getItems().addAll(bookKeepingPatternsFXES);
        comboBox.setCellFactory(cell -> new ListCell<BookKeepingPatternsFX>() {
            final GridPane gridPane = new GridPane();
            final Label labelId = new Label();
            final Label labelOpis = new Label();
            final Label labelData = new Label();

            {
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(20, 20, 10), new ColumnConstraints(200, 200, 100));
                gridPane.add(labelId, 0, 1);
                gridPane.add(labelOpis, 1, 1);
            }
            @Override
            protected void updateItem(BookKeepingPatternsFX importModelFX, boolean b) {
                super.updateItem(importModelFX, b);

                if (!b && importModelFX != null) {
                    labelId.setText(importModelFX.getId() + ".");
                    labelOpis.setText(importModelFX.getPatternName());
                    setGraphic(gridPane);
                } else {
                    setGraphic(null);
                }
            }
        });
        comboBox.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (newValue != null) {
                try {
                    payListPatternFX.setBookKeepingPatternType(((BookKeepingPatternsFX) newValue).getId());
                    payListPatternFX.setBookKeepingPatterntTypeName(((BookKeepingPatternsFX) newValue).getPatternName());

                    importDAO.updatePayListPattern(payListPatternFX);
                } catch (SQLException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
            }
        });
        BookKeepingPatterns bookKeepingPatterns1 = null;
        try {
            bookKeepingPatterns1 = importDAO.getBookKeepingPatternsById(payListPatternFX.getBookKeepingPatternType());
            if (bookKeepingPatterns1 == null) {
            } else {
                BookKeepingPatternsFX bookKeepingPatternsFX = new BookKeepingPatternsFX();
                bookKeepingPatternsFX.setId(bookKeepingPatterns1.getId());
                bookKeepingPatternsFX.setPatternTypeName(bookKeepingPatterns1.getPatternTypeName());
                bookKeepingPatternsFX.setPatternName(bookKeepingPatterns1.getPatterName());
                bookKeepingPatternsFX.setPatternComment(bookKeepingPatterns1.getPatternComment());
                bookKeepingPatternsFX.setPatternType(bookKeepingPatterns1.getPatternType());

                comboBox.getSelectionModel().select(bookKeepingPatternsFX);
            }
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
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
        listTableImportId.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, Integer>("importId"));
        listTableListId.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, Integer>("idList"));
        listTableListName.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("numberList"));
        listTableDescriptionList.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("descriptionList"));
        listTableListDateColumn.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("dateList"));
        listTableCodeList.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("departmentCode"));
        listTableAgree.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("agreeList"));
        listTableComboBox.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, ComboBox>("bookKeepingPatternsComboBox"));
        listTableBookButton.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, Button>("bookButton"));


        listTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));

        for (PayListPatternFX item : payListPatternFXES) {
            Button button = item.getBookButton();
            button.setText("Księguj");

            button.setOnAction(e -> {
                System.out.println(item.getBookKeepingPatterntTypeName());
            });
            initializeComboBox(item);
        }

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
