package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.QuestionMap;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.Model.QuizMap;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BankQuizController implements Initializable {




    @FXML
    private JFXButton hideTreeViewBtn;

    @FXML
    private TreeView<String> treeView;

    public BankQuizController() throws IOException {
    }


    @FXML
    void hideTreeView(ActionEvent event) {
        if (event.getSource() == hideTreeViewBtn) {
            if (treeView.visibleProperty().getValue() == true) {
                treeView.setVisible(false);
            } else treeView.setVisible(true);

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("Course: IT");
        treeView.setRoot(rootItem);
        addChildItem(rootItem, 1);



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

    String xuLiChuoi(String inputString) {
        if (inputString.charAt(inputString.length() - 1) == ')') {
            int index = inputString.indexOf(" (");
            inputString = inputString.substring(0, index);
        } else return inputString;
        return inputString;
    }


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
            FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuestionCard2.fxml"));
            try {
                Node node = fxmlLoader.load();
                vBoxD.getChildren().add(node);
                QuestionCard2Controller questionCard2Controller = fxmlLoader.getController();
                questionCard2Controller.setQuestionInfor(question.getQuestionText()+"  "+question.getOption1Text()+"  "+question.getOption2Text()+"  "+question.getOption3Text()+"  "+
                        question.getOption4Text()+"  "+question.getOption5Text());
                questionCard2Controller.setQuestion(question);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private CheckBox showChildQuestionBtn;


    @FXML
    private void selectItem(MouseEvent event) {
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        hideTreeViewBtn.setText(treeView.getSelectionModel().getSelectedItem().getValue());
        List<Question> questions = Question.selectAllByQuizId(Quiz.getQuizIdFromName(xuLiChuoi(hideTreeViewBtn.getText())));
        showQuestionList(questions);
    }

    @FXML
    void change(ActionEvent event) {
        vBoxD.getChildren().clear();
        List<Question> questions = Question.selectAllByQuizId(Quiz.getQuizIdFromName(xuLiChuoi(hideTreeViewBtn.getText())));
    if(showChildQuestionBtn.isSelected() ){
       addChildQuestion(questions,Quiz.getQuizIdFromName(xuLiChuoi(hideTreeViewBtn.getText())));
        for (Question question : questions) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuestionCard2.fxml"));
            try {
                Node node = fxmlLoader.load();
                vBoxD.getChildren().add(node);
                QuestionCard2Controller questionCard2Controller = fxmlLoader.getController();
                questionCard2Controller.setQuestionInfor(question.getQuestionText()+"  "+question.getOption1Text()+"  "+question.getOption2Text()+"  "+question.getOption3Text()+"  "+
                                                        question.getOption4Text()+"  "+question.getOption5Text());
                questionCard2Controller.setQuestion(question);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }else {
        for (Question question : questions) {
            FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("QuestionCard2.fxml"));
            try {
                Node node = fxmlLoader.load();
                vBoxD.getChildren().add(node);
                QuestionCard2Controller questionCard2Controller = fxmlLoader.getController();
                questionCard2Controller.setQuestionInfor(question.getQuestionText()+"  "+question.getOption1Text()+"  "+question.getOption2Text()+"  "+question.getOption3Text()+"  "+
                        question.getOption4Text()+"  "+question.getOption5Text());
                questionCard2Controller.setQuestion(question);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    }
    @FXML
    private JFXButton saveQuestionMapBtn;
    public void setDisPlayQuizMap(QuizMap maap){
        this.quizMap=maap;


    }
    private static QuizMap quizMap;

    private static List<Question> questions;

    public  void setQuestions(List<Question> questions) {
        BankQuizController.questions = questions;
    }

    @FXML
    void saveQuestionMap(ActionEvent event ) throws IOException {
      System.out.println(quizMap.getQuiz().getName());
      if(questions != null){
          for(Question question : questions){
              QuestionMap questionMap = new QuestionMap(1, quizMap, question);
              questionMap.save(quizMap,question);
      }

        }

        Stage stage = (Stage) saveQuestionMapBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("PreviewQuiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);





    }



}

