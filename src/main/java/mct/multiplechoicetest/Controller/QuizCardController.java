package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.QuestionMap;
import mct.multiplechoicetest.Model.QuizMap;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class QuizCardController implements Initializable {
    private QuizMap quizMap;


    public void setQuizMap(QuizMap quizMap) {
        this.quizMap = quizMap;
    }

    @FXML
    private JFXButton startQuizBtn;

    private static QuizMap selectedQuizMap ;


    public JFXButton getStartQuizBtn() {
        return startQuizBtn;
    }

    public void setStartQuizBtn(JFXButton startQuizBtn) {
        this.startQuizBtn = startQuizBtn;
    }

    @FXML
    QuizMap startQuiz(ActionEvent event) throws IOException {
        Stage stage = (Stage) startQuizBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("PreviewQuiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);

        PreviewQuizController previewQuizController = fxmlLoader.getController();

        ////////////////////////////////////////////////////////////
        QuizMap selectQuizmap = this.quizMap;
        System.out.println(selectQuizmap.getId());

        ////////////////////////////////////////////////////////////


        previewQuizController.setButtonQuizName(selectQuizmap.getQuiz().getName());
        previewQuizController.setLabelQuizName(selectQuizmap.getQuiz().getName());
        previewQuizController.setGetTimeLimit(String.valueOf(selectQuizmap.getTimeLimit())+" minutes");
        ////////////////////////////////////////
        selectedQuizMap = selectQuizmap;
        BankQuizController bankQuizController = new BankQuizController();
        bankQuizController.setDisPlayQuizMap(selectQuizmap);

        ////////////////////
        previewQuizController.setQuizMap(selectQuizmap);





        return selectQuizmap;

    }





    public void setStartQuizBtn(String value) {
        this.startQuizBtn.setText(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


}
