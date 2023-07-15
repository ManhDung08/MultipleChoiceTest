package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mct.multiplechoicetest.Dao.QuestionDAO;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;

public class QuestionCard1Controller {
    public String xuLiKetQua(Float input) {
        if (input == 0) return "None";
        else {
            return String.valueOf(input*100 + "%");
        }
    }

    private Question question;

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setQuestionInFor(String questionInFor) {
        this.questionInFor.setText(questionInFor);
    }

    @FXML
    private JFXButton editButton;

    @FXML
    private Label questionInFor;

    @FXML
    void editQuestion(ActionEvent event) throws IOException {
        Stage stage = (Stage) editButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("AddQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);

        AddQuestionController addQuestionController = fxmlLoader.getController();
        addQuestionController.setLabell("Editing Multiple choice question");
        addQuestionController.setNewTextAreaQuestion(question.getQuestionText());
        addQuestionController.setNewChoice1(question.getOption1Text());
        addQuestionController.setNewChoice2(question.getOption2Text());
        addQuestionController.setNewChoice3(question.getOption3Text());
        addQuestionController.setNewChoice4(question.getOption4Text());
        addQuestionController.setNewChoice5(question.getOption5Text());

        addQuestionController.setChoiceBox1(xuLiKetQua(question.getOption1Mark()));
        addQuestionController.setChoiceBox2(xuLiKetQua(question.getOption2Mark()));
        addQuestionController.setChoiceBox3(xuLiKetQua(question.getOption3Mark()));
        addQuestionController.setChoiceBox4(xuLiKetQua(question.getOption5Mark()));
        addQuestionController.setChoiceBox5(xuLiKetQua(question.getOption5Mark()));
        addQuestionController.setSaveQuestionAndContinueBtn(jfxButton);
        addQuestionController.setDeleteQuestions(question);

    }
    private JFXButton jfxButton = new JFXButton();

    public JFXButton getJfxButton() {
        return jfxButton;
    }

    public void setJfxButton(JFXButton jfxButton) {
        this.jfxButton = jfxButton;
    }
}
