package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.ElementSlownikFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.xml.models.ElementSlownik;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookKeepingPartsDictionaryWindowController {

    private final ObservableList<ElementSlownikFX> elementSlownikFXES = FXCollections.observableArrayList();
    List<ElementSlownik> elementSlowniks = new ArrayList<>();
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn nameTableColumn;
    @FXML
    private TableView partsDictionaryPositionTable;
    private final ImportDAO importDAO = new ImportDAO();
    private Integer patternId=0;
    private Integer positionId=0;
    private Stage stage;

    public void initialize() {
        if(!(patternId==0 || positionId==0))
        try {

            elementSlowniks = importDAO.getDictionaryElementListOnlyNoParts(patternId,positionId);

            elementSlowniks.forEach(element -> {
                ElementSlownikFX elementSlownikFX = new ElementSlownikFX();
                elementSlownikFX.setId(element.getId());
                elementSlownikFX.setNazwa(element.getNazwa());
                elementSlownikFX.setAlias(element.getAlias());
                elementSlownikFX.setCzyDrukowac(element.getCzyDrukowac());
                elementSlownikFX.setKolejnosc(element.getKolejnosc());
                elementSlownikFXES.add(elementSlownikFX);
            });


            idTableColumn.setCellValueFactory(new PropertyValueFactory<ElementSlownikFX, Integer>("id"));
            nameTableColumn.setCellValueFactory(new PropertyValueFactory<ElementSlownikFX, String>("nazwa"));
            partsDictionaryPositionTable.setItems(elementSlownikFXES);

        } catch (SQLException e) {
        //    DialogUtils.errorDialog("Problem z komunikacją z bazą danych!");
          e.printStackTrace();
        }
    }

    public void chooseParts() {

        try {
            importDAO.addBookKeepingPart(patternId, positionId, ((ElementSlownikFX) partsDictionaryPositionTable.getSelectionModel().getSelectedItem()));
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //    DialogUtils.errorDialog("Błąd komunikacji z bazą danych!");
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
