package mct.multiplechoicetest.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class QuestionCard2Controller {

    @FXML
    private CheckBox checkBox;

    public void setQuestionInfor(String value) {
        this.questionInfor.setText(value);
    }

    @FXML
    private Label questionInfor;
}
