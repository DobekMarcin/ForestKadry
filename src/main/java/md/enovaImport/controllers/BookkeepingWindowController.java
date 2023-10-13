package md.enovaImport.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.enovaImport.Stage.MainStage;

import java.io.IOException;
import java.util.ResourceBundle;

public class BookkeepingWindowController {

    private static final String BOOKKEEPING_ADD_PATTERN_DIALOG_WINDOW = "/FXML/BookkeepingAddPatterDialogWindow.fxml";
    private MainWindowController mainWindowController;

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void addPattern() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_ADD_PATTERN_DIALOG_WINDOW));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Parent parent = fxmlLoader.load();
            BookkeepingAddPatterDialogWindowController bookkeepingAddPatterDialogWindowController = fxmlLoader.getController();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Dodaj nowy wzorzec");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
