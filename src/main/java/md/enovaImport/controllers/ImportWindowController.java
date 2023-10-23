package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import md.enovaImport.Stage.MainStage;
import md.enovaImport.modelsFX.ImportModelFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.ImportModel;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ImportWindowController {

    private static final String NEW_IMPORT = "/FXML/NewImportWindow.fxml";
    private static final String LIST_WINDOW = "/FXML/ListWindow.fxml";
    private final ObservableList<ImportModelFX> importModelFX = FXCollections.observableArrayList();

    ImportDAO importDAO = new ImportDAO();
    @FXML
    private BorderPane borderPane;
    @FXML
    private TableColumn importTableIdColumn;
    @FXML
    private TableColumn importTableDescriptionColumn;
    @FXML
    private TableColumn importTableDateColumn;
    @FXML
    private TableColumn importTableEmailColumn;
    @FXML
    private TableColumn importTableBKColumn;
    @FXML
    private TableView importTable;
    private File file;
    private MainWindowController mainWindowController;

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void initialize() {
        try {
            List<ImportModel> implortList = importDAO.getImportList();
            implortList.forEach(element -> {
                ImportModelFX modelFX = new ImportModelFX();
                modelFX.setId(element.getId());
                modelFX.setOpis(element.getOpis());
                modelFX.setData(element.getDataImportu().toString());
                modelFX.setEmail(element.getEmail());
                modelFX.setBookKeeping(element.getBookKeeping());
                importModelFX.add(modelFX);
            });

        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        importTableIdColumn.setCellValueFactory(new PropertyValueFactory<ImportModelFX, String>("id"));
        importTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ImportModelFX, String>("opis"));
        importTableDateColumn.setCellValueFactory(new PropertyValueFactory<ImportModelFX, String>("data"));
        importTableEmailColumn.setCellValueFactory(new PropertyValueFactory<ImportModelFX,Boolean>("email"));
        importTableEmailColumn.setCellFactory(CheckBoxTableCell.forTableColumn(importTableEmailColumn));
        importTableBKColumn.setCellValueFactory(new PropertyValueFactory<ImportModelFX,Boolean>("bookKeeping"));
        importTableBKColumn.setCellFactory(CheckBoxTableCell.forTableColumn(importTableBKColumn));
        importTable.setItems(importModelFX);

        importTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
    }

    public void newImportButton() {
        borderPane.getBottom().setVisible(false);
        newImportWindow();
    }

    public void newImportWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((NEW_IMPORT)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Pane bordPane = fxmlLoader.load();
            NewImportWindowController newImportWindowController = fxmlLoader.getController();
            newImportWindowController.setMainWindowController(mainWindowController);
            borderPane.setCenter(bordPane);
        } catch (IOException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void deleteImport() {
        try {
            ImportModelFX importModelFX1 = (ImportModelFX) importTable.getSelectionModel().getSelectedItem();
            Boolean statusEmail=importDAO.checkEmailGenerate(importModelFX1.getId());
            Boolean statusBK=importDAO.checkBookKeppingGenerate(importModelFX1.getId());
            if (statusEmail || statusBK){
                DialogUtils.informationDialog("Nie można skasować!");
            }else{
            if (importModelFX1 != null) {
                Integer result = DialogUtils.deleteImport();
                if (result == 1) {
                    importDAO.deleteImportListById(importModelFX1.getId());
                    importDAO.deleteElementWyplatyById(importModelFX1.getId());
                    importDAO.deleteListaPlacById(importModelFX1.getId());
                    importDAO.deletePodatkiSkladkiById(importModelFX1.getId());
                    importDAO.deleteWyplataById(importModelFX1.getId());

                    importModelFX.clear();
                    initialize();
                }
            } else {
                DialogUtils.choosePosition();
            }}
        } catch (NullPointerException e) {
            DialogUtils.errorDialog(e.getMessage());
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void detailButton() {
        ImportModelFX importModelFX1 = (ImportModelFX) importTable.getSelectionModel().getSelectedItem();
        if (importModelFX1 != null) {
            mainWindowController.setImportDetailId(importModelFX1.getId());
            mainWindowController.setListWindow();
        } else {
            DialogUtils.choosePosition();
        }
    }


}
