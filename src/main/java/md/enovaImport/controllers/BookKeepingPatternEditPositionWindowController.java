package md.enovaImport.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.BookKeepingPatternsPositionFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.BookKeepingPatternsPosition;
import md.enovaImport.utils.DialogUtils;

import java.sql.SQLException;

public class BookKeepingPatternEditPositionWindowController {

    private final ImportDAO importDAO = new ImportDAO();
    @FXML
    private TextField lpTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField blaneTextField;
    @FXML
    private TextField hasTextField;
    @FXML
    private TextField distibutorAccountTextField;
    @FXML
    private CheckBox distributorCheckField;
    @FXML
    private CheckBox paymentCheckBox;
    @FXML
    private TextField positionTextField;
    private Integer positionId;
    private Stage stage;

    private BookKeepingPatternsPositionFX bookKeepingPatternsPositionFX;

    public void initialize() {
        if (bookKeepingPatternsPositionFX != null) {

            lpTextField.setText(String.valueOf(bookKeepingPatternsPositionFX.getPositionId()));
            nameTextField.setText(bookKeepingPatternsPositionFX.getName());
            blaneTextField.setText(bookKeepingPatternsPositionFX.getAccountBlame());
            hasTextField.setText(bookKeepingPatternsPositionFX.getAccountHas());
            distibutorAccountTextField.setText(bookKeepingPatternsPositionFX.getAccountDistributor());
            positionTextField.setText(String.valueOf(bookKeepingPatternsPositionFX.getDistributorPosition()));
            distributorCheckField.setSelected(bookKeepingPatternsPositionFX.distributorProperty().get());
            paymentCheckBox.setSelected(bookKeepingPatternsPositionFX.paymentProperty().get());

            distibutorAccountTextField.disableProperty().bind(distributorCheckField.selectedProperty().not());
            positionTextField.disableProperty().bind(distributorCheckField.selectedProperty().not());
            lpTextField.setDisable(true);
        }
    }

    public void savePositionButton() {
        BookKeepingPatternsPosition bookKeepingPatternsPosition = new BookKeepingPatternsPosition();

        try {
            Integer lp = Integer.valueOf(lpTextField.getText());
            String name = nameTextField.getText();
            String blameAccount = blaneTextField.getText();
            String hasAccount = hasTextField.getText();
            String distributorAccount = distibutorAccountTextField.getText();
            Boolean distributor = distributorCheckField.isSelected();
            Boolean payment = paymentCheckBox.isSelected();
            Integer distributorPosition = Integer.valueOf(positionTextField.getText());

            bookKeepingPatternsPosition.setPatternId(bookKeepingPatternsPositionFX.getPatternId());
            bookKeepingPatternsPosition.setPositionId(lp);
            bookKeepingPatternsPosition.setName(name);
            bookKeepingPatternsPosition.setAccountBlame(blameAccount);
            bookKeepingPatternsPosition.setAccountHas(hasAccount);
            bookKeepingPatternsPosition.setDistributor(distributor);
            bookKeepingPatternsPosition.setAccountDisributor(distributorAccount);
            bookKeepingPatternsPosition.setPayment(payment);
            bookKeepingPatternsPosition.setDistributorPosition(distributorPosition);

            if (bookKeepingPatternsPosition.getPatternId() == 0 || bookKeepingPatternsPosition.getPatternId() == null || bookKeepingPatternsPosition.getPositionId() < 1 || bookKeepingPatternsPosition.getName().isEmpty() || bookKeepingPatternsPosition.getAccountBlame().isEmpty() || bookKeepingPatternsPosition.getAccountHas().isEmpty() || (bookKeepingPatternsPosition.getAccountDisributor().isEmpty() && bookKeepingPatternsPosition.getDistributor()))
                DialogUtils.errorDialog("Podaj brakujące dane!");
            else {
                importDAO.updateBookKeepingPatternsOnePositionsById(bookKeepingPatternsPosition);
                stage.close();
            }
        } catch (NumberFormatException | SQLException e) {
            DialogUtils.errorDialog("Podajesz błędnę dane lub problem z komunikacją do bazy danych!");
        }
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

    public BookKeepingPatternsPositionFX getBookKeepingPatternsPositionFX() {
        return bookKeepingPatternsPositionFX;
    }

    public void setBookKeepingPatternsPositionFX(BookKeepingPatternsPositionFX bookKeepingPatternsPositionFX) {
        this.bookKeepingPatternsPositionFX = bookKeepingPatternsPositionFX;
    }
}
