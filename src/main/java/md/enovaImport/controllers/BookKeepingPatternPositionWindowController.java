package md.enovaImport.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.BookKeepingPatternsFX;

import java.io.IOException;
import java.util.ResourceBundle;

public class BookKeepingPatternPositionWindowController {

    private static final String BOOKKEEPING_PATTERN_ADD_POSITION="/FXML/BookKeepingPatternAddPositionWindow.fxml";
    private Stage stage;
    private BookKeepingPatternsFX bookKeepingPatternsFX;


    public void addPositionButton( ) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_PATTERN_ADD_POSITION));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Parent parent = fxmlLoader.load();
       //     BookKeepingPatternAddPositionWindowController bookKeepingPatternAddPositionWindowController = fxmlLoader.getController();


            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Dodaj nową pozycję");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
//            bookKeepingPatternAddPositionWindowController.setStage(stage);
            stage.showAndWait();

        } catch (IOException exception) {
            throw new RuntimeException(exception);
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
