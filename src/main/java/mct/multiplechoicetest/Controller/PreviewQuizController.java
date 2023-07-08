package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PreviewQuizController {

    @FXML
    private JFXButton buttonQuizName;

    @FXML
    private Label labelQuizName;
    @FXML
    private Label getTimeLimit;

    public void setGetTimeLimit(String value) {
        this.getTimeLimit.setText(value);
    }

    public void setButtonQuizName(String value) {
        this.buttonQuizName.setText(value);
    }

    public void setLabelQuizName(String value) {
        this.labelQuizName.setText(value);
    }
}
