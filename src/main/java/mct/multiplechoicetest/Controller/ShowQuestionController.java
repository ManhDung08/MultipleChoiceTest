package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class ShowQuestionController implements Initializable {

    @FXML
    private JFXButton goAddCategoryButton;

    @FXML
    void goAddCategory(ActionEvent event) throws IOException {
        Stage stage = (Stage) goAddCategoryButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("AddCategory.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);
    }

    @FXML
    private JFXButton goAddQuestionBtn;

    @FXML
    void goAddQuestion(ActionEvent event) throws IOException {
        Stage stage = (Stage) goAddQuestionBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("AddQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);
    }

    private final TreeView<String> zoomTreeView = new TreeView<>();

    @FXML
    void seLectItem(MouseEvent event) {
        TreeItem<String> rootItem = new TreeItem<>("Default");
        treeView.setRoot(rootItem);

    }

    @FXML
    private TreeView<String> treeView;
    @FXML
    private JFXButton popupTreeViewBtn;

    @FXML
    void popupTreeView(ActionEvent event) {
        if (event.getSource() == popupTreeViewBtn) {
            if (treeView.visibleProperty().getValue() == true) {
                treeView.setVisible(false);
            } else treeView.setVisible(true);

        }
    }

    @FXML
    void selectItem(MouseEvent event) {
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();

        popupTreeViewBtn.setText(treeView.getSelectionModel().getSelectedItem().getValue());
        List<Question> questions = Question.selectAllByQuizId(Quiz.getQuizIdFromName(xuLiChuoi(popupTreeViewBtn.getText())));
        showQuestionList(questions);
    }

    String xuLiChuoi(String inputString) {
        if (inputString.charAt(inputString.length() - 1) == ')') {
            int index = inputString.indexOf(" (");
            inputString = inputString.substring(0, index);
        } else return inputString;
        return inputString;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("Course: IT");
        treeView.setRoot(rootItem);
        addChildItem(rootItem, 1);
        List<Question> questions = Question.selectAllByQuizId(Quiz.getQuizIdFromName(xuLiChuoi(popupTreeViewBtn.getText())));
        showQuestionList(questions);

    }

    private void addChildItem(TreeItem treeItem, int parentID) {
        List<Quiz> quizzes = Quiz.getAllQuizzesFromDatabase();
        for (Quiz quiz : quizzes) {
            int numberOfQuestion = Question.countQuestionsForQuiz(quiz.getQuiz_id());
            if (quiz.getParent_id() == parentID) {
                if (numberOfQuestion != 0) {
                    TreeItem childItem = new TreeItem<>(quiz.getName() + " (" + numberOfQuestion + ")");
                    treeItem.getChildren().add(childItem);

                    addChildItem(childItem, quiz.getQuiz_id());
                } else {
                    TreeItem childItem = new TreeItem<>(quiz.getName());
                    treeItem.getChildren().add(childItem);

                    addChildItem(childItem, quiz.getQuiz_id());
                }
            }
        }
    }


    @FXML
    private CheckBox showQuestionCheckBox;

    private List<Question> addChildQuestion(List<Question> questions, int parenId) {
        List<Quiz> quizzes = Quiz.getAllQuizzesFromDatabase();
        for (Quiz quiz : quizzes) {
            if (quiz.getParent_id() == parenId) {
                List<Question> questionList = Question.selectAllByQuizId(quiz.getQuiz_id());
                questions.addAll(questionList);
                addChildQuestion(questions, quiz.getQuiz_id());
            }
        }
        return questions;
    }
    @FXML
    private VBox vBoxD;
    private void showQuestionList(List<Question> q) {
        vBoxD.getChildren().clear(); // Clear existing questions
        for (Question question : q) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuestionCard1.fxml"));
            try {
                Node node = fxmlLoader.load();
                vBoxD.getChildren().add(node);
                QuestionCard1Controller questionCard1Controller = fxmlLoader.getController();
                questionCard1Controller.setQuestionInFor(question.getQuestionText()+" "+question.getOption1Text()+
                                                        " "+ question.getOption2Text()+" "+question.getOption3Text()+
                        " "+ question.getOption4Text()+" "+question.getOption5Text());
                questionCard1Controller.setQuestion(question);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void showQuestion(ActionEvent event) {
        vBoxD.getChildren().clear();
        List<Question> questions = Question.selectAllByQuizId(Quiz.getQuizIdFromName(xuLiChuoi(popupTreeViewBtn.getText())));
        if (showQuestionCheckBox.isSelected()) {
            addChildQuestion(questions, Quiz.getQuizIdFromName(xuLiChuoi(popupTreeViewBtn.getText())));
            for (Question question : questions) {
                FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuestionCard1.fxml"));
                try {
                    Node node = fxmlLoader.load();
                    vBoxD.getChildren().add(node);
                    QuestionCard1Controller questionCard1Controller = fxmlLoader.getController();
                    questionCard1Controller.setQuestionInFor(question.getQuestionText()+" "+question.getOption1Text()+
                            " "+ question.getOption2Text()+" "+question.getOption3Text()+
                            " "+ question.getOption4Text()+" "+question.getOption5Text());
                    questionCard1Controller.setQuestion(question);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


        } else {
            for (Question question : questions) {
                FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuestionCard1.fxml"));
                try {
                    Node node = fxmlLoader.load();
                    vBoxD.getChildren().add(node);
                    QuestionCard1Controller questionCard1Controller = fxmlLoader.getController();
                    questionCard1Controller.setQuestionInFor(question.getQuestionText()+" "+question.getOption1Text()+
                            " "+ question.getOption2Text()+" "+question.getOption3Text()+
                            " "+ question.getOption4Text()+" "+question.getOption5Text());
                    questionCard1Controller.setQuestion(question);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }


    }
}






