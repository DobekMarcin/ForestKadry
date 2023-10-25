package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.ElementSlownikFX;
import md.enovaImport.modelsFX.PartsFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.Parts;
import md.enovaImport.utils.DialogUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookKeepingPartsWindowController {

    private static final String BOOKKEEPING_PARTS_DICTIONARY = "/FXML/BookKeepingPartsDictionaryWindow.fxml";
    @FXML
    private TableView partsPositionTable;
    @FXML
    private TableColumn lpTableColumn;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn nameTableColumn;
    @FXML
    private TableColumn symbolTableColumn;

    private Integer patternId=0;
    private Integer positionId=0;

    private final ObservableList<PartsFX> partsFXES = FXCollections.observableArrayList();
    private ImportDAO importDAO = new ImportDAO();
    List<Parts> partsList = new ArrayList<>();

    public void initialize(){
            if(!(patternId==0 || positionId==0))
        try {
            partsFXES.clear();
            partsList=importDAO.getPartsById(patternId,positionId);

            partsList.forEach(element->{
                PartsFX partsFX = new PartsFX();
                partsFX.setPatternId(element.getPatternId());
                partsFX.setPositionId(element.getPositionId());
                partsFX.setId(element.getId());
                partsFX.setPartsId(element.getPartsId());
                partsFX.setPartsName(element.getPartsName());
                partsFX.setSymbol(element.getSymbol());
                partsFXES.add(partsFX);
            });

            lpTableColumn.setCellValueFactory(new PropertyValueFactory<PartsFX,Integer>("id"));
            idTableColumn.setCellValueFactory(new PropertyValueFactory<PartsFX,Integer>("partsId"));
            nameTableColumn.setCellValueFactory(new PropertyValueFactory<PartsFX,String>("partsName"));
            symbolTableColumn.setCellValueFactory(new PropertyValueFactory<PartsFX,String>("symbol"));
            partsPositionTable.setItems(partsFXES);

        } catch (SQLException e) {
            DialogUtils.errorDialog("Błąd komunikacji z bazą danych!");
        }

    }


    public void addPartButton() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_PARTS_DICTIONARY));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));

        try {
            Parent parent = fxmlLoader.load();
            BookKeepingPartsDictionaryWindowController bookKeepingPartsDictionaryWindowController = fxmlLoader.getController();
            bookKeepingPartsDictionaryWindowController.setPatternId(patternId);
            bookKeepingPartsDictionaryWindowController.setPositionId(positionId);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Wybierz składnik z słownika");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            bookKeepingPartsDictionaryWindowController.setStage(stage);
            bookKeepingPartsDictionaryWindowController.initialize();
            stage.showAndWait();
            initialize();
        } catch (IOException e) {
            DialogUtils.errorDialog("Problem z komunikacją z bazą danych.");
            e.printStackTrace();
        }
    }

    public Integer getPatternId() {
        return patternId;
    }

    public void setPatternId(Integer patternId) {
        this.patternId = patternId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public void deletePartsButton() {
        PartsFX partsFX = (PartsFX) partsPositionTable.getSelectionModel().getSelectedItem();
        if(partsFX==null){
            DialogUtils.informationDialog("Zaznacz pozycję!");
        }else
        try {
            importDAO.deletePartsById(partsFX,patternId,positionId);
            initialize();
        } catch (SQLException e) {
            DialogUtils.errorDialog("Błąd komunikacji z bazą danych");
        }

    }

    public void changeSignButton() {
        PartsFX partsFX = (PartsFX) partsPositionTable.getSelectionModel().getSelectedItem();
        if (partsFX == null) {
            DialogUtils.informationDialog("Zaznacz pozycję!");
        } else{
            try {
                importDAO.updatesPartsById(partsFX,patternId,positionId);
            } catch (SQLException e) {
                DialogUtils.errorDialog("Błąd komunikacji z bazą danych!");
            }
            initialize();
        }
    }
}
