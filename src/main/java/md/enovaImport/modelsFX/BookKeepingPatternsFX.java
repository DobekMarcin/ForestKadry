package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;

public class BookKeepingPatternsFX  {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty patternName = new SimpleStringProperty();
    private SimpleIntegerProperty patternType = new SimpleIntegerProperty();
    private SimpleStringProperty patternTypeName = new SimpleStringProperty();
    private SimpleStringProperty patternComment = new SimpleStringProperty();
    private Button bookKeepingPositionButton = new Button();

    @Override
    public String toString() {
        return  id.get() +
                "." + patternName.get();
    }

    public Button getBookKeepingPositionButton() {
        return bookKeepingPositionButton;
    }

    public void setBookKeepingPositionButton(Button bookKeepingPositionButton) {
        this.bookKeepingPositionButton = bookKeepingPositionButton;
    }

    public BookKeepingPatternsFX() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getPatternName() {
        return patternName.get();
    }

    public SimpleStringProperty patternNameProperty() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName.set(patternName);
    }

    public int getPatternType() {
        return patternType.get();
    }

    public SimpleIntegerProperty patternTypeProperty() {
        return patternType;
    }

    public void setPatternType(int patternType) {
        this.patternType.set(patternType);
    }

    public String getPatternTypeName() {
        return patternTypeName.get();
    }

    public SimpleStringProperty patternTypeNameProperty() {
        return patternTypeName;
    }

    public void setPatternTypeName(String patternTypeName) {
        this.patternTypeName.set(patternTypeName);
    }

    public String getPatternComment() {
        return patternComment.get();
    }

    public SimpleStringProperty patternCommentProperty() {
        return patternComment;
    }

    public void setPatternComment(String patternComment) {
        this.patternComment.set(patternComment);
    }
}
