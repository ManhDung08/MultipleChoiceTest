package mct.multiplechoicetest;

import javafx.application.Application;
import javafx.stage.Stage;
import mct.multiplechoicetest.Dao.QuestionDAO;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.Quiz;

import java.io.File;

public class Test extends Application {
    public static void main(String[] args) {
        Question question = new Question();

        question.setQuestionText("What is the capital of France?");
        question.setQuestionImg(""); // Set the image path if applicable
        question.setQuestionMark(10);
        question.setOption1Text("London");
        question.setOption1Img("");
        question.setOption1Mark(0);
        question.setOption2Text("Paris");
        question.setOption2Img("");
        question.setOption2Mark(10);
        question.setOption3Text("Berlin");
        question.setOption3Img("");
        question.setOption3Mark(0);
        question.setOption4Text("Madrid");
        question.setOption4Img("");
        question.setOption4Mark(0);
        question.setOption5Text("Rome");
        question.setOption5Img("");
        question.setOption5Mark(0);
        question.setAnswer("Paris");
        Quiz quiz = Quiz.getQuizFromName("Vật lí");
        question.setQuiz(quiz);
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.save(question);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
