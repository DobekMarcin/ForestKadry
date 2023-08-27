package md.enovaImport.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import md.enovaImport.Stage.MainStage;
import md.enovaImport.modelsFX.ElementFX;
import md.enovaImport.modelsFX.ElementSlownikFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.ElementSlownik;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ElementDicWindowController {

    public static final String PROGRAM_ICON = "/icon/programicon.png";
    private static final String BUFFER_WINDOW_FXML = "/FXML/BufferWindow.fxml";
    private  ObservableList<ElementSlownikFX> elementSlownikObservableList = FXCollections.observableArrayList();
    private  ImportDAO importDAO = new ImportDAO();
    @FXML
    private TableColumn elementDicTableIdColumn;
    @FXML
    private TableColumn elementDicTableNameColumn;
    @FXML
    private TableColumn elementDicTableAliasColumn;
    @FXML
    private TableColumn<ElementSlownikFX, Boolean> elementDicTablePrintColumn;
    @FXML
    private TableColumn elementDicTablePrintPosColumn;
    @FXML
    private TableView elementDicTable;
    private MainWindowController mainWindowController;

    public void initialize() {
        updateElementSlownikObservableList();
        elementDicTable.setItems(elementSlownikObservableList);
        elementDicTableIdColumn.setCellValueFactory(new PropertyValueFactory<ElementSlownikFX, Integer>("id"));
        elementDicTableNameColumn.setCellValueFactory(new PropertyValueFactory<ElementSlownikFX, String>("nazwa"));
        elementDicTableAliasColumn.setCellValueFactory(new PropertyValueFactory<ElementSlownikFX, String>("alias"));
        elementDicTableAliasColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        elementDicTableAliasColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ElementSlownikFX, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ElementSlownikFX, String> cellEditEvent) {
                try {
                    Integer elementId = cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow()).getId();
                    importDAO.updateDictionaryElementAlias(elementId, cellEditEvent.getNewValue());
                    updateElementSlownikObservableList();
                } catch (SQLException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
            }
        });

        elementDicTablePrintColumn.setCellValueFactory(new PropertyValueFactory<ElementSlownikFX, Boolean>("czyDrukowac"));
        elementDicTablePrintColumn.setCellFactory(CheckBoxTableCell.forTableColumn(elementDicTablePrintColumn));

        addListenerToCheckBox();

        elementDicTablePrintPosColumn.setCellValueFactory(new PropertyValueFactory<ElementSlownikFX, Integer>("kolejnosc"));
        elementDicTablePrintPosColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        elementDicTablePrintPosColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ElementSlownikFX, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ElementSlownikFX, Integer> cellEditEvent) {
                Integer elementId = cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow()).getId();
                try {
                    importDAO.updateDictionaryElementOrder(elementId, cellEditEvent.getNewValue());
                    updateElementSlownikObservableList();
                } catch (SQLException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
            }
        });


        elementDicTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
    }

    private void addListenerToCheckBox() {
        for (ElementSlownikFX item : elementSlownikObservableList) {
            item.czyDrukowacProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue==true) {
                    try {
                        importDAO.updateDictionaryElementPrint(item.getId(), newValue);
                    } catch (SQLException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                }
                if (newValue==false) {
                    try {
                        importDAO.updateDictionaryElementPrint(item.getId(), newValue);
                    } catch (SQLException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                }
                updateElementSlownikObservableList();
                elementDicTable.refresh();
            });
        }

    }

    private void updateElementSlownikObservableList() {
        elementSlownikObservableList.clear();
        List<ElementSlownik> implortList = null;
        try {
            implortList = importDAO.getDictionaryElementList();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        implortList.forEach(element -> {
            ElementSlownikFX elementSlownikFX = new ElementSlownikFX();
            elementSlownikFX.setId(element.getId());
            elementSlownikFX.setNazwa(element.getNazwa());
            elementSlownikFX.setAlias(element.getAlias());
            elementSlownikFX.setCzyDrukowac(element.getCzyDrukowac());
            elementSlownikFX.setKolejnosc(element.getKolejnosc());
            elementSlownikObservableList.add(elementSlownikFX);
        });
        addListenerToCheckBox();
    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void bufferOnAction() {
        Stage bufferStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((BUFFER_WINDOW_FXML)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        Pane bordPane = null;
        try {
            bordPane = fxmlLoader.load();
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        Scene scene = new Scene(bordPane);
        bufferStage.setScene(scene);
        bufferStage.setTitle(DialogUtils.getBundle().getString("title.buffer.window"));
        bufferStage.getIcons().add(new Image(MainStage.class.getResourceAsStream(PROGRAM_ICON)));
        bufferStage.initModality(Modality.WINDOW_MODAL);
        bufferStage.initOwner(elementDicTable.getScene().getWindow());
        bufferStage.showAndWait();
        updateElementSlownikObservableList();
    }
}
