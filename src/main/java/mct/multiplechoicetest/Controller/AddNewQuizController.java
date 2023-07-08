package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.Model.QuizMap;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddNewQuizController {

    @FXML
    public TextField newQuizForExam;

    @FXML
    private TextField quizTime;



    @FXML JFXButton addQuizForExamBtn;




    @FXML
    public void addQuizForExam() throws IOException {
    if(newQuizForExam.getText().length()==0){
        QuizMap quizMap1 = new QuizMap(1,Quiz.getQuizFromName(newQuizForExam.getText()),-1);
        quizMap1.save(quizMap1);
    }else {
        QuizMap quizMap1 = new QuizMap(1,Quiz.getQuizFromName(newQuizForExam.getText()),Integer.parseInt(quizTime.getText()));
        quizMap1.save(quizMap1);
    }

    }








}
