package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.Model.QuizMap;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class QuizCardController implements Initializable {

    @FXML
    private JFXButton startQuizBtn;

    @FXML
    void startQuiz(ActionEvent event) throws IOException {
        Stage stage = (Stage) startQuizBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("PreviewQuiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);

        PreviewQuizController previewQuizController = fxmlLoader.getController();
        previewQuizController.setButtonQuizName(startQuizBtn.getText());
        previewQuizController.setLabelQuizName(startQuizBtn.getText());
        previewQuizController.setGetTimeLimit(String.valueOf(QuizMap.getQuizMapByQuizId(Quiz.getQuizIdFromName(startQuizBtn.getText())).getTimeLimit())+" minute");
    }

    public void setStartQuizBtn(String value) {
        this.startQuizBtn.setText(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
