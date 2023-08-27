package md.enovaImport.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils {

    private static final ResourceBundle bundle = FXMLUtils.getResourceBundle();

    public static ResourceBundle getBundle(){
        return bundle;
    }


    public static void importConfirmation() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle(bundle.getString("import.title"));
        informationAlert.setHeaderText(bundle.getString("import.header"));
        informationAlert.setContentText(bundle.getString("import.content"));
        informationAlert.showAndWait();
    }

    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));
        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static void informationDialog(String information) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle(bundle.getString("information.title"));
        errorAlert.setHeaderText(bundle.getString("information.title"));
        TextArea textArea = new TextArea(information);
        textArea.setPrefSize(400,40);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static Integer deleteImport() {
        ButtonType yes = new ButtonType("TAK", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NIE", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, bundle.getString("import.delete.content"), yes, no);
        alert.setTitle(bundle.getString("import.delete.topic"));
        alert.setHeaderText(bundle.getString("import.delete.content2"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            return 1;
        } else return 0;
    }

    public static Integer deleteAllImport() {
        ButtonType yes = new ButtonType("TAK", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NIE", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, bundle.getString("import.deleteall.content"), yes, no);
        alert.setTitle(bundle.getString("import.deleteall.topic"));
        alert.setHeaderText(bundle.getString("import.delete.content2"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            return 1;
        } else return 0;
    }

    public static void choosePosition() {
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setTitle(bundle.getString("import.delete.content2"));
        errorAlert.setHeaderText(bundle.getString("import.delete.choose"));
        errorAlert.showAndWait();
    }
}
