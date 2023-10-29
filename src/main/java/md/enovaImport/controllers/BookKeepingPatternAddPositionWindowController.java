package md.enovaImport.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.BookKeepingPatternsFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.BookKeepingPatternsPosition;
import md.enovaImport.utils.DialogUtils;
import java.sql.SQLException;


public class BookKeepingPatternAddPositionWindowController {

    @FXML
    private TextField positionTextField;
    @FXML
    private CheckBox paymentCheckBox;
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
    private final ImportDAO importDAO = new ImportDAO();
    private Stage stage;

    private BookKeepingPatternsFX bookKeepingPatternsFX;

    public void initialize() {
        distibutorAccountTextField.disableProperty().bind(distributorCheckField.selectedProperty().not());
        positionTextField.disableProperty().bind(distributorCheckField.selectedProperty().not());


    }

    public void addPositionButton() {
        BookKeepingPatternsPosition bookKeepingPatternsPosition = new BookKeepingPatternsPosition();

        try {
            Integer lp = Integer.valueOf(lpTextField.getText());
            String name = nameTextField.getText();
            String blameAccount = blaneTextField.getText();
            String hasAccount = hasTextField.getText();
            String distributorAccount = distibutorAccountTextField.getText();
            Boolean distributor = distributorCheckField.isSelected();
            Boolean payment = paymentCheckBox.isSelected();
            Integer distributorPosition=Integer.valueOf(positionTextField.getText());

            bookKeepingPatternsPosition.setPatternId(bookKeepingPatternsFX.getId());
            bookKeepingPatternsPosition.setPositionId(lp);
            bookKeepingPatternsPosition.setName(name);
            bookKeepingPatternsPosition.setAccountBlame(blameAccount);
            bookKeepingPatternsPosition.setAccountHas(hasAccount);
            bookKeepingPatternsPosition.setDistributor(distributor);
            bookKeepingPatternsPosition.setAccountDisributor(distributorAccount);
            bookKeepingPatternsPosition.setPayment(payment);
            bookKeepingPatternsPosition.setDistributorPosition(distributorPosition);

            if (bookKeepingPatternsPosition.getPatternId() == 0 || bookKeepingPatternsPosition.getPatternId() == null || bookKeepingPatternsPosition.getPositionId() < 1 || bookKeepingPatternsPosition.getName().isEmpty() ||
                    bookKeepingPatternsPosition.getAccountBlame().isEmpty() || bookKeepingPatternsPosition.getAccountHas().isEmpty() ||
                    (bookKeepingPatternsPosition.getAccountDisributor().isEmpty() && bookKeepingPatternsPosition.getDistributor()))
                DialogUtils.errorDialog("Podaj brakujące dane!");
            else {
                Integer check = importDAO.checkBookKeppingPositionId(bookKeepingPatternsFX.getId(), bookKeepingPatternsPosition.getPositionId());
                if (check > 0) {
                    DialogUtils.informationDialog("Pozycja z tą LP jest już dodana!");
                } else {importDAO.addBookKeepingPatternPosition(bookKeepingPatternsPosition);
                stage.close();}
            }
        } catch (NumberFormatException | SQLException e) {
            DialogUtils.errorDialog("Podajesz błędnę dane lub problem z komunikacją do bazy danych!");
        }
    }

    public BookKeepingPatternsFX getBookKeepingPatternsFX() {
        return bookKeepingPatternsFX;
    }

    public void setBookKeepingPatternsFX(BookKeepingPatternsFX bookKeepingPatternsFX) {
        this.bookKeepingPatternsFX = bookKeepingPatternsFX;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
