package mct.multiplechoicetest.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import mct.multiplechoicetest.Model.Question;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionCard2Controller implements Initializable {
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @FXML
    private CheckBox checkBox;

    public void setQuestionInfor(String value) {
        this.questionInfor.setText(value);
    }

    @FXML
    private Label questionInfor;



   private static List<Question> questions = new ArrayList<>();

    @FXML
    void addQuestion(ActionEvent event) {
    if(checkBox.isSelected()){
        questions.add(this.question);
        System.out.println(question.getQuestionText());
    }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {  {
        try {
            BankQuizController bankQuizController = new BankQuizController();
            bankQuizController.setQuestions(questions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
