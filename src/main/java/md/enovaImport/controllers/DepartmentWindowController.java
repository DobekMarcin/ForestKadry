package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.DepartmentFX;
import md.enovaImport.modelsFX.PartsFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.jdbc.MSSQLDAO;
import md.enovaImport.sql.models.Department;
import md.enovaImport.utils.DialogUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentWindowController {

    private static final String DEPARTMENT_DISTRIBUTOR="/FXML/DepartmentDistributor.fxml";
    private final ObservableList<DepartmentFX> departmentFXObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn distributorColumn;
    @FXML
    private TableView departmentTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn codeColumn;
    @FXML
    private TableColumn nameColumn;

    private MSSQLDAO mssqldao = new MSSQLDAO();
    private ImportDAO importDAO = new ImportDAO();

    public void initialize(){
        try {
            departmentFXObservableList.clear();
            List<Department> departmentList = importDAO.getDepartment();

            departmentList.forEach(element->{
                DepartmentFX departmentFX = new DepartmentFX();

                departmentFX.setId(element.getId());
                departmentFX.setCode(element.getCode());
                departmentFX.setName(element.getName());

                departmentFX.getDistributor().setText("Rozdzielnik");

                departmentFX.getDistributor().setOnAction(e->{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(DEPARTMENT_DISTRIBUTOR));
                    fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));

                    try {
                        Parent parent = fxmlLoader.load();
                        DepartmentDistributorController departmentDistributorController = fxmlLoader.getController();
                        departmentDistributorController.setDepartmentId(element.getId());
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Rozdzielnik KORG");
                        stage.setResizable(false);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        departmentDistributorController.initialize();
                        stage.showAndWait();
                        initialize();
                    } catch (IOException ee) {
                        DialogUtils.errorDialog("Problem z komunikacją z bazą danych.");
                        ee.printStackTrace();
                    }
                });

                departmentFXObservableList.add(departmentFX);
            });

            idColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX,Integer>("id"));
            codeColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX,String>("code"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX,String>("name"));
            distributorColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX, Button>("distributor"));

            departmentTable.setItems(departmentFXObservableList);
        } catch (SQLException e) {
            DialogUtils.errorDialog("Błąd pobierania danych z enova.");
        }
    }
    public void updateButton() {

        try {
            List<Department> departmentList = mssqldao.getDepartment();

            departmentList.forEach(element->{

                try {
                    if(importDAO.checkdepartmentById(element.getId())>0){
                        importDAO.updatesDepartmentsById(element);
                    }else{
                    importDAO.addDepartment(element);
                        initialize();
                    }
                } catch (SQLException e) {
                    DialogUtils.errorDialog("Błąd pobierania danych.");
                }

            });
            DialogUtils.informationDialog("Zakończono aktualizację!");


        } catch (SQLException e) {
            DialogUtils.errorDialog("Błąd pobierania danych.");
        }

    }
}
