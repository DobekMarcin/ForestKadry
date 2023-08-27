package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.enovaImport.Stage.MainStage;
import md.enovaImport.modelsFX.PersonFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.sql.models.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PersonListWindowController {

    public static final String PROGRAM_ICON = "/icon/programicon.png";
    private static final String BUFFER_PERSON_WINDOW_FXML = "/FXML/BufferPersonWindow.fxml";
    private static final String TEST="/FXML/PersonListWindow.fxml";
    @FXML
    private TableView personDicTable;
    @FXML
    private TableColumn personTableCodeColumn;
    @FXML
    private TableColumn personTableNameColumn;
    @FXML
    private TableColumn personTableEmailColumn;
    @FXML
    private TableColumn personTableSendColumn;
    private MainWindowController mainWindowController;
    private final ObservableList<PersonFX> personDicObservableList = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();


    public void initialize() {
        updatePersonDicObservableList();
        personDicTable.setItems(personDicObservableList);
        personTableCodeColumn.setCellValueFactory(new PropertyValueFactory<PersonFX, Integer>("code"));
        personTableNameColumn.setCellValueFactory(new PropertyValueFactory<PersonFX, String>("name"));
        personTableEmailColumn.setCellValueFactory(new PropertyValueFactory<PersonFX, String>("email"));
        personTableEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        personTableEmailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PersonFX, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<PersonFX, String> cellEditEvent) {
                Integer personCode = cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow()).getCode();
                try {
                    importDAO.updateDictionaryPersonEmail(personCode, cellEditEvent.getNewValue());
                } catch (SQLException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
                updatePersonDicObservableList();
            }
        });

        personTableSendColumn.setCellValueFactory(new PropertyValueFactory<PersonFX, Boolean>("send"));
        personTableSendColumn.setCellFactory(CheckBoxTableCell.forTableColumn(personTableSendColumn));

        addListenerToCheckBox();

        personDicTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
    }


    private void updatePersonDicObservableList() {
        personDicObservableList.clear();
        List<Person> implortList = null;
        try {
            implortList = importDAO.getPersonDictionaryList();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        implortList.forEach(element -> {
            PersonFX personFX = new PersonFX();
            personFX.setCode(element.getCode());
            personFX.setName(element.getName());
            personFX.setEmail(element.getEmail());
            personFX.setSend(element.getSend());
            personDicObservableList.add(personFX);
        });
        addListenerToCheckBox();
    }

    private void addListenerToCheckBox() {
        for (PersonFX item : personDicObservableList) {
            item.sendProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    try {
                        importDAO.updateDictionaryPersonSend(item.getCode(), newValue);
                    } catch (SQLException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                }
                if (!newValue) {
                    try {
                        importDAO.updateDictionaryPersonSend(item.getCode(), newValue);
                    } catch (SQLException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                }
                updatePersonDicObservableList();
                personDicTable.refresh();
            });
        }

    }

    public void bufferOnAction() {
        Stage bufferStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((BUFFER_PERSON_WINDOW_FXML)));
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
        bufferStage.initOwner(personDicTable.getScene().getWindow());
        bufferStage.showAndWait();
           updatePersonDicObservableList();
    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
