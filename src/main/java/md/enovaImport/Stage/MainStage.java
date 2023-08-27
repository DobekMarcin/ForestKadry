package md.enovaImport.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import md.enovaImport.controllers.MainWindowController;

import java.util.ResourceBundle;

public class MainStage extends Application {

    public static final String BORDER_PANE_MAIN_FXML="/FXML/MainWindow.fxml";
    public static final String PROGRAM_ICON="/icon/programicon.png";

    public  void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource((BORDER_PANE_MAIN_FXML)));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        Pane bordPane =fxmlLoader.load();

        MainWindowController mainWindowController =(MainWindowController) fxmlLoader.getController();
        mainWindowController.setPrimaryStage(stage);

        Scene scene = new Scene(bordPane);
        stage.setScene(scene);
        stage.setTitle(ResourceBundle.getBundle("bundles.messages").getString("tittle.application"));
        stage.getIcons().add(new Image(MainStage.class.getResourceAsStream(PROGRAM_ICON)));
        stage.show();
    }
}
