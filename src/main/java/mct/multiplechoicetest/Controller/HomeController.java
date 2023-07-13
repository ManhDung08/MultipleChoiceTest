package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.Model.QuizMap;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class HomeController extends AddNewQuizController implements Initializable{

    @FXML
    private JFXButton popupButton;
    @FXML
    private AnchorPane popupPane;

    @FXML
    private JFXButton goShowQuestionButton;




    @FXML
    void popup(ActionEvent event) {
        if (event.getSource() == popupButton) {
            if (popupPane.visibleProperty().getValue() == true) {
                popupPane.setVisible(false);
            } else popupPane.setVisible(true);

        }

    }
    @FXML
    void goShowQuestion(ActionEvent event) throws IOException {
        // chu y action event
        Stage stage = (Stage) goShowQuestionButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("ShowQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);
    }
    @FXML
    private JFXButton goAddNewQuizBtn;
    @FXML
    void goAddNewQuiz(ActionEvent event) throws IOException {
        Stage stage = (Stage) goShowQuestionButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("AddNewQuiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);
    }


    @FXML
    private FlowPane quizLisstContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addShowQuizList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void addShowQuizList() throws IOException {
        List<QuizMap> quizMapList = QuizMap.getAllQuizMapFromDatabase();

        for(QuizMap quizMap : quizMapList){
            FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuizCard.fxml"));
            try {
                Node node = fxmlLoader.load();
                quizLisstContainer.getChildren().add(node);
                QuizCardController quizCardController = fxmlLoader.getController();
                quizCardController.setStartQuizBtn(quizMap.getQuiz().getName());
                quizCardController.setQuizMap(quizMap);




            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


    }



}


