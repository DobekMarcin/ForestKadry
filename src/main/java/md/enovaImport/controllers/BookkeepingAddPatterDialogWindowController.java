package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.BookKeepingPatternTypeFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.BookKeepingPatternType;
import md.enovaImport.sql.models.BookKeepingPatterns;
import md.enovaImport.utils.DialogUtils;
import java.sql.SQLException;
import java.util.List;

public class BookkeepingAddPatterDialogWindowController {

    private final ObservableList<BookKeepingPatternTypeFX> bookKeepingPattenrTypeFXES = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    @FXML
    private TextField patternNameTextField;
    @FXML
    private ComboBox patternTypeComboBox;
    @FXML
    private TextArea patternCommentsTextArea;
    private List<BookKeepingPatternType> bookKeepingPatternTypes;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        try {
            bookKeepingPatternTypes = importDAO.getBookKeepingPatternsType();
        } catch (SQLException e) {
            DialogUtils.errorDialog("Problem z pobraniem danych z bazy!");
        }
        bookKeepingPatternTypes.forEach(element -> {
            BookKeepingPatternTypeFX bookKeepingPatternTypeFX = new BookKeepingPatternTypeFX();
            bookKeepingPatternTypeFX.setId(element.getId());
            bookKeepingPatternTypeFX.setName(element.getName());
            bookKeepingPattenrTypeFXES.add(bookKeepingPatternTypeFX);
        });


        patternTypeComboBox.getItems().addAll(bookKeepingPattenrTypeFXES);

        patternTypeComboBox.setCellFactory(cell -> new ListCell<BookKeepingPatternTypeFX>() {
            final GridPane gridPane = new GridPane();
            final Label labelId = new Label();
            final Label labelName = new Label();

            {
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(10, 30, 10), new ColumnConstraints(100, 50, 100));
                gridPane.add(labelId, 0, 1);
                gridPane.add(labelName, 1, 1);
            }


            @Override
            protected void updateItem(BookKeepingPatternTypeFX bookKeepingPatternTypeFX, boolean b) {
                super.updateItem(bookKeepingPatternTypeFX, b);

                if (!b && bookKeepingPatternTypeFX != null) {
                    labelId.setText(bookKeepingPatternTypeFX.getId() + ".");
                    labelName.setText(bookKeepingPatternTypeFX.getName());
                    setGraphic(gridPane);
                } else {
                    setGraphic(null);
                }
            }
        });
    }

    public void addPatternOnAction() {
        BookKeepingPatternTypeFX bookKeepingPatternTypeFX = (BookKeepingPatternTypeFX) patternTypeComboBox.getSelectionModel().getSelectedItem();
        if (patternNameTextField.getText().isEmpty() || bookKeepingPatternTypeFX == null) {
            DialogUtils.errorDialog("Uzupełnij brakujące danę!");
        }else {
            try {
                importDAO.addNewBookKeepingPattern(new BookKeepingPatterns(0, patternNameTextField.getText(), bookKeepingPatternTypeFX.getId(),bookKeepingPatternTypeFX.getName(), patternCommentsTextArea.getText()));
                stage.close();
            } catch (SQLException e) {
                DialogUtils.errorDialog("Problem z dodaniem pozycji do bazy danych!");
            }
        }

    }
}
