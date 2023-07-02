package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeView;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.StartApp;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class AddCategoryController implements Initializable {

    @FXML
    private JFXButton goBackShowQuestionBtn;

    @FXML
    void goBackShowQuestion(ActionEvent event) throws IOException {
        Stage stage = (Stage) goBackShowQuestionBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("ShowQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);
    }

    @FXML
    private JFXTreeView<String> treeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem rootItem = new TreeItem<>("Default");
        addChildItem(rootItem, 1);
        treeView.setRoot(rootItem);

    }

    private void addChildItem(TreeItem treeItem, int parentID) {
        List<Quiz> quizzes = Quiz.getAllQuizzesFromDatabase();
        for (Quiz quiz : quizzes) {
            if (quiz.getParent_id() == parentID) {
                TreeItem childItem = new TreeItem<>(quiz.getName());
                treeItem.getChildren().add(childItem);
                addChildItem(childItem, quiz.getQuiz_id());
            }

        }

    }

    @FXML
    void SelectItem(MouseEvent event) {
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();

    }

    @FXML
    private TextField newQuizID;

    @FXML
    private TextField newQuizName;


    @FXML
    private JFXButton addNewQuizBtn;

    @FXML
    void addNewQuiz(ActionEvent event) {
        if (newQuizName.getText().isEmpty()) {
            System.out.println("Nhập Quiz Name hợp lệ");
        }else {
            if(newQuizID.getText().length()==0){
                Quiz quiz = new Quiz(0,newQuizName.getText(),Quiz.getQuizIdFromName(treeView.getSelectionModel().getSelectedItem().getValue()));
                quiz.saveToDatabaseWithNoID();

            }else  {
                Quiz quiz = new Quiz(Integer.parseInt(newQuizID.getText()),newQuizName.getText(),Quiz.getQuizIdFromName(treeView.getSelectionModel().getSelectedItem().getValue()));
                quiz.saveToDatabase();
                System.out.println("ĐÃ LƯU");
            }
        }
    }
}








