package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.QuestionMap;

public class QuestionCard3Controller {
    private static Question question;

    public void setDeleteQuestionBtn(JFXButton deleteQuestionBtn) {
        this.deleteQuestionBtn = deleteQuestionBtn;
    }

    public void setQuestionInfor(String value) {
        this.questionInfor.setText(value);
    }

    public void setQuestionMarks(String value) {
        this.questionMarks.setText(value);
    }

    public void setStt(String value) {
        this.stt.setText(value);
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @FXML
    private JFXButton deleteQuestionBtn;

    @FXML
    private TextField questionInfor;

    @FXML
    private TextField questionMarks;

    @FXML
    private TextField stt;

    @FXML
    void deleteQuestion(ActionEvent event) {
    QuestionMap.deleteQuestionMapByQuestion(question);
    }

}
