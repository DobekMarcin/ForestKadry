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
import md.enovaImport.modelsFX.DepartmentDistributorPositionFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.DepartmentDistributorPosition;
import md.enovaImport.utils.DialogUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentDistributorController {

    private final static String DEPARTMENT_DISTRIBUTOR_ADD_POSITION = "/FXML/DepartmentDistributorAddPosition.fxml";

    @FXML
    private TableView partsDictionaryPositionTable;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn nameTableColumn;

    private Integer departmentId = 0;

    private List<DepartmentDistributorPosition> departmentDistributorPositionList = new ArrayList<>();
    private final ObservableList<DepartmentDistributorPositionFX> departmentDistributorPositionFXES = FXCollections.observableArrayList();
private ImportDAO importDAO = new ImportDAO();

    public void initialize() {

        try {
            departmentDistributorPositionList =importDAO.getDepartmentPosition(departmentId);
            departmentDistributorPositionList.forEach(element->{
                DepartmentDistributorPositionFX departmentDistributorPositionFX = new DepartmentDistributorPositionFX();
                departmentDistributorPositionFX.setId(element.getId());
                departmentDistributorPositionFX.setKorg_id(element.getKorg_id());
                departmentDistributorPositionFX.setAccount(element.getAccount());

            });

            idTableColumn.setCellValueFactory(new PropertyValueFactory<DepartmentDistributorPositionFX,Integer>("id"));
            nameTableColumn.setCellValueFactory(new PropertyValueFactory<DepartmentDistributorPositionFX,String>("account"));
            partsDictionaryPositionTable.setItems(departmentDistributorPositionFXES);

        } catch (SQLException e) {
            DialogUtils.errorDialog("Bład pobierania danych z bazy!");
        }


    }

    public void addPositionButton() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(DEPARTMENT_DISTRIBUTOR_ADD_POSITION));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));

        try {
            Parent parent = fxmlLoader.load();
            DepartmentDistributorAddPositionController departmentDistributorAddPositionController = fxmlLoader.getController();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Nowa pozycja");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            departmentDistributorAddPositionController.setStage(stage);
            departmentDistributorAddPositionController.setDepartmentId(departmentId);
            stage.showAndWait();
            //      initialize();
        } catch (IOException ee) {
            DialogUtils.errorDialog("Problem z komunikacją z bazą danych.");
            ee.printStackTrace();
        }
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


}
