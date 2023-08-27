package md.enovaImport.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ResourceBundle;

public class FXMLUtils {

    public static Pane fxmlLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FXMLUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());

        try {
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("bundles.messages");
    }

    public static String getBundle(String key){
        return  getResourceBundle().getString(key);
    }
}
