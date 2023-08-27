package md.enovaImport.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.ElementFX;
import md.enovaImport.modelsFX.ElementSlownikFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.Element;
import md.enovaImport.xml.models.ElementSlownik;

import java.sql.SQLException;
import java.util.List;

public class BufferWindowController {
    private final ObservableList<ElementFX> elementObservableList = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    @FXML
    private TableView bufferTable;
    @FXML
    private TableColumn bufferTableDescriptionColumn;
    private List<Element> elementList;

    public void initialize() {
        try {
            elementList = importDAO.getNewElementDictionaryPosition();
            elementList.forEach(element -> {
                ElementFX elementFX = new ElementFX();
                elementFX.setElementName(element.getElementName());
                elementObservableList.add(elementFX);
            });
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bufferTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<ElementFX, String>("elementName"));

        bufferTable.setItems(elementObservableList);

        bufferTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
    }

    public void addFromBufferOnAction() {
        elementList.forEach(element -> {
            try {
                importDAO.addNewElementDictionary(element);

            } catch (SQLException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        });
        Stage stage = (Stage) bufferTable.getScene().getWindow();
        stage.close();
    }

    public void closeStageOnAction() {
        Stage stage = (Stage) bufferTable.getScene().getWindow();
        stage.close();
    }
}
