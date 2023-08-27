package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.SimplePersonFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.sql.models.SimplePerson;

import java.sql.SQLException;
import java.util.List;

public class BufferPersonWindowController {

    @FXML
    private TableView personTable;
    @FXML
    private TableColumn personTableCodeColumn;
    @FXML
    private TableColumn personTableNameColumn;
    private final ObservableList<SimplePersonFX> personFXObservableList = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    private List<SimplePerson> personList;

    public void initialize() {
        try {
            personList = importDAO.getNewPersonDictionaryPosition();
            personList.forEach(element -> {
                SimplePersonFX personFX = new SimplePersonFX();
                personFX.setCode(element.getCode());
                personFX.setName(element.getName());
                personFXObservableList.add(personFX);
            });
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        personTableCodeColumn.setCellValueFactory(new PropertyValueFactory<SimplePersonFX, Integer>("code"));
        personTableNameColumn.setCellValueFactory(new PropertyValueFactory<SimplePersonFX, String>("name"));
        personTable.setItems(personFXObservableList);

        personTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
    }


    public void addPersonsButton(ActionEvent actionEvent) {
        personList.forEach(element -> {
            try {
                importDAO.addNewPersonDictionary(element);

            } catch (SQLException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        });
        Stage stage = (Stage) personTable.getScene().getWindow();
        stage.close();
    }

    public void closeButton( ) {
        Stage stage = (Stage) personTable.getScene().getWindow();
        stage.close();
    }
}
