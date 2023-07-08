package mct.multiplechoicetest.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import mct.multiplechoicetest.Model.QuizMap;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QuizListController implements Initializable {

    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addQuizList();
    }

    private void addQuizList() {
        List<QuizMap> quizMapList = QuizMap.getAllQuizMapFromDatabase();

        for(QuizMap quizMap : quizMapList){
            FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuizCard.fxml"));
            try {
                Node node = fxmlLoader.load();
                stackPane.getChildren().add(node);
                QuizCardController quizCardController = fxmlLoader.getController();
                quizCardController.setStartQuizBtn(quizMap.getQuiz().getName());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
