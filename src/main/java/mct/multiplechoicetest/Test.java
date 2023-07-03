package mct.multiplechoicetest;

import javafx.application.Application;
import javafx.stage.Stage;
import mct.multiplechoicetest.Dao.QuestionDAO;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.Model.QuizResults;

import java.io.File;

public class Test extends Application {
    public static void main(String[] args) {
        QuizResults.createTable();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
