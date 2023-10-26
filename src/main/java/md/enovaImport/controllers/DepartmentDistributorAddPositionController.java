package md.enovaImport.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.DepartmentDistributorPosition;
import md.enovaImport.utils.DialogUtils;

import java.sql.SQLException;

public class DepartmentDistributorAddPositionController {


    @FXML
    private TextField lpTextField;
    @FXML
    private TextField accountTextField;
    private ImportDAO importDAO = new ImportDAO();
    private Stage stage;
    private Integer departmentId=0;



    public void addButton() {
        if (lpTextField.getText().isEmpty() || accountTextField.getText().isEmpty()) {
            DialogUtils.informationDialog("Uzupełnij brakujące dane!");
        } else {
            DepartmentDistributorPosition departmentDistributorPosition = new DepartmentDistributorPosition();

            try {
                departmentDistributorPosition.setKorg_id(departmentId);
                departmentDistributorPosition.setId(Integer.valueOf(lpTextField.getText()));
                departmentDistributorPosition.setAccount(accountTextField.getText());
            Integer count = importDAO.checkDepartmentDistributorPositionById(departmentDistributorPosition);
            if(count>0){
                DialogUtils.informationDialog("Pozycja z podanym lp już istnieje!");
            }else{
            importDAO.addDepartmentDistributorPosition(departmentDistributorPosition);
            stage.close();
            }

            } catch (NumberFormatException | SQLException e) {
                DialogUtils.errorDialog("Podajesz nie poprawne dane!");
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
