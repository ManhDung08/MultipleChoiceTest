package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mct.multiplechoicetest.Dao.QuestionDAO;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.StartApp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {
    @FXML
    private Label Labell;

    public void setChoiceBox1(String value) {
        this.choiceBox1.setValue(value);
    }

    public void setChoiceBox2(String value) {
        this.choiceBox2.setValue(value);
    }

    public void setChoiceBox3(String value) {
        this.choiceBox3.setValue(value);
    }

    public void setChoiceBox4(String value) {
        this.choiceBox4.setValue(value);
    }

    public void setChoiceBox5(String value) {
        this.choiceBox5.setValue(value);
    }

    public void setNewChoice1(String value) {
        this.newChoice1.setText(value);
    }

    public void setSaveQuestionAndContinueBtn(JFXButton saveQuestionAndContinueBtn) {
        this.saveQuestionAndContinueBtn = saveQuestionAndContinueBtn;
    }

    public void setNewChoice2(String value) {
        this.newChoice2.setText(value);
    }

    public void setNewChoice3(String value) {
        this.newChoice3.setText(value);
    }

    public void setNewChoice4(String value) {
        this.newChoice4.setText(value);
    }

    public void setNewChoice5(String value) {
        this.newChoice5.setText(value);
    }

    public void setNewTextAreaQuestion(String value) {
        this.newTextAreaQuestion.setText(value);
    }

    public void setLabell(String value) {
        Labell.setText(value);
    }

    @FXML
    private AnchorPane popup3Choice;
    @FXML
    private JFXButton setMore3ChoiceBtn;

    @FXML
    void setMore3Choice(ActionEvent event) {
        if(event.getSource()==setMore3ChoiceBtn){
            if (popup3Choice.visibleProperty().getValue() == true) {
                popup3Choice.setVisible(false);
            } else {
                popup3Choice.setVisible(true);
            }
        }

    }

    // hiển thi treeView
    @FXML
    public TreeView<String> treeView;


    @FXML
    void selectItem(MouseEvent event) {

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
    ///////////////////////////////////
    private String[] grade = {"None","100%","90%","83.3333%","80%","75%","70%","66.66667%","60%","50%"
            ,"40%","33.333333%","30%","25%","20%","16.666667%","14.28571%","12.5%","11.11111%","10%","5%",
            "-5%","-10%", "-11.11111%","-12.5%","-14.28571%","-16.666667%","-20%","-25%","-30%","-33.333333%","-40%",
            "-50%", "-60%","-66.66667%","-70%","-75%","-80%","-83.3333%"};
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private ChoiceBox<String> choiceBox3;
    @FXML
    private ChoiceBox<String> choiceBox4;
    @FXML
    private ChoiceBox<String> choiceBox5;

    @FXML
    private TextField questionMark;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("Default");
        treeView.setRoot(rootItem);
        addChildItem(rootItem,1);
       choiceBox1.getItems().addAll(grade);
       choiceBox1.setValue("None");
       choiceBox2.getItems().addAll(grade);
       choiceBox3.getItems().addAll(grade);
        choiceBox4.getItems().addAll(grade);
        choiceBox5.getItems().addAll(grade);
        choiceBox2.setValue("None");
        choiceBox3.setValue("None");
        choiceBox4.setValue("None");
        choiceBox5.setValue("None");

        getPathFile(newChoice1);
        getPathFile(newChoice2);
        getPathFile(newChoice3);
        getPathFile(newChoice4);
        getPathFile(newChoice5);
        getPathFile2(newTextAreaQuestion);
        questionMark.setText("1");
    }
    //
    @FXML
    public TextField newTextQuestion;
    @FXML
    private TextField newChoice1;
    @FXML
    private TextField newChoice2;
    @FXML
    private TextField newChoice3;
    @FXML
    private TextField newChoice4;
    @FXML
    private TextField newChoice5;
    @FXML
    private TextArea newTextAreaQuestion;

    String xuLiChuoi(String inputString){
        if(inputString.charAt(inputString.length() - 1) == ')'){
            int index = inputString.indexOf(" (");
            inputString= inputString.substring(0,index);
        }else return inputString;
        return  inputString;
    }

    public static TextField getPathFile(TextField textField){
        textField.setOnMouseClicked(event -> {
            // click 2 lần để chọn hình ảnh
            if (event.getClickCount() == 2) {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    textField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        return textField;

    }
    public  static TextArea getPathFile2(TextArea textArea){
        textArea.setOnMouseClicked(event -> {
            // click 2 lần để chọn hình ảnh
            if (event.getClickCount() == 2) {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                    textArea.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        return textArea;
    }
    public String fileCheck(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return filePath;
        } else {
            return null;
        }
    }
    public String textCheck(String string){
        File file = new File(string);
        if(file.exists()){
            return null;
        }else {
            return string;
        }
    }

    public static float processString(String string) {
            if (string == "None") {
                return 0;
            } else if (string.endsWith("%")) {
                String numberString = string.substring(0, string.length() - 1);
                return (float) (Float.parseFloat(numberString) *0.01);
            } else {
                return (float) (Float.parseFloat(string));
            }
        }
    @FXML
    private JFXButton saveQuestionAndContinueBtn;
    @FXML
  public   void saveQuestionAndContinue(ActionEvent event) {

        Question question = new Question();
        question.setQuiz(Quiz.getQuizFromName(xuLiChuoi(treeView.getSelectionModel().getSelectedItem().getValue())));
        question.setQuestionText(newTextQuestion.getText() + textCheck(newTextAreaQuestion.getText()));
        question.setQuestionImg(fileCheck(newTextAreaQuestion.getText()));
        question.setQuestionMark(1);
        question.setOption1Text(textCheck(newChoice1.getText()));
        question.setOption1Img(fileCheck(newChoice1.getText()));
        question.setOption1Mark(processString(choiceBox1.getValue()));
        question.setOption2Text(textCheck(newChoice2.getText()));
        question.setOption2Img(fileCheck(newChoice2.getText()));
        question.setOption2Mark(processString(choiceBox2.getValue()));
        question.setOption3Text(textCheck(newChoice3.getText()));
        question.setOption3Img(fileCheck(newChoice3.getText()));
        if(choiceBox3.getValue().length() != 0){
            question.setOption3Mark(processString(choiceBox3.getValue()));
        }

        question.setOption4Text(textCheck(newChoice4.getText()));
        question.setOption4Img(fileCheck(newChoice4.getText()));
        if(choiceBox4.getValue().length() != 0){
            question.setOption4Mark(processString(choiceBox4.getValue()));
        }

        question.setOption5Text(textCheck(newChoice5.getText()));
        question.setOption5Img(fileCheck(newChoice5.getText()));
        if(choiceBox5.getValue().length() != 0){
            question.setOption5Mark(processString(choiceBox5.getValue()));
        }

        if (choiceBox1.getValue() == "100%") {
            question.setAnswer("A");
            question.setOption2Mark(0);
            question.setOption3Mark(0);
            question.setOption4Mark(0);
            question.setOption5Mark(0);


        } else if (choiceBox2.getValue() == "100%") {
            question.setAnswer("B");
            question.setOption1Mark(0);
            question.setOption3Mark(0);
            question.setOption4Mark(0);
            question.setOption5Mark(0);
        }  else if(choiceBox3.getValue() == "100%"){
            question.setAnswer("C");
            question.setOption1Mark(0);
            question.setOption2Mark(0);
            question.setOption4Mark(0);
            question.setOption5Mark(0);
        }   else if(choiceBox4.getValue() == "100%"){
            question.setAnswer("D");
            question.setOption1Mark(0);
            question.setOption2Mark(0);
            question.setOption3Mark(0);
            question.setOption5Mark(0);
        }   else if(choiceBox5.getValue() == "100%"){
            question.setAnswer("E");
            question.setOption1Mark(0);
            question.setOption2Mark(0);
            question.setOption3Mark(0);
            question.setOption4Mark(0);
        }

        if(newChoice1.getText() == null) {
            question.setOption1Text(newChoice2.getText());
            question.setOption1Mark(processString(choiceBox2.getValue()));
            newChoice2.setText(null);
            if(newChoice2.getText() == null) {
                question.setOption2Text(newChoice3.getText());
                question.setOption2Mark(processString(choiceBox3.getValue()));
                newChoice3.setText(null);
                if(newChoice3.getText() == null) {
                    question.setOption3Text(newChoice4.getText());
                    question.setOption3Mark(processString(choiceBox4.getValue()));
                    newChoice4.setText(null);
                    if(newChoice4.getText() == null){
                        question.setOption4Text(newChoice5.getText());
                        question.setOption4Mark(processString(choiceBox5.getValue()));
                        newChoice5.setText(null);
        }

         }

         }

        }



        if(newTextQuestion.getText().length()!=0 && newTextAreaQuestion.getText().length() != 0 ) {
            QuestionDAO questionDAO = new QuestionDAO();
            questionDAO.save(question);
            QuestionDAO questionDAO1= new QuestionDAO();
            questionDAO1.delete(deleteQuestions);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("thong bao");
            alert.setContentText("success");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("thong bao");
            alert.setContentText(" Select category anh fill in the information question form ! ");
            alert.showAndWait();
        }




    }
    private Question deleteQuestions;

    public void setDeleteQuestions(Question deleteQuestions) {
        this.deleteQuestions = deleteQuestions;
    }

    @FXML
    private JFXButton saveChangeButton;
    @FXML
    void saveChange(ActionEvent event) throws IOException {
        Question question = new Question();
        question.setQuiz(Quiz.getQuizFromName(xuLiChuoi(treeView.getSelectionModel().getSelectedItem().getValue())));
        question.setQuestionText(newTextQuestion.getText() + textCheck(newTextAreaQuestion.getText()));
        question.setQuestionImg(fileCheck(newTextAreaQuestion.getText()));
        question.setQuestionMark(1);
        question.setOption1Text(textCheck(newChoice1.getText()));
        question.setOption1Img(fileCheck(newChoice1.getText()));
        question.setOption1Mark(processString(choiceBox1.getValue()));
        question.setOption2Text(textCheck(newChoice2.getText()));
        question.setOption2Img(fileCheck(newChoice2.getText()));
        question.setOption2Mark(processString(choiceBox2.getValue()));
        question.setOption3Text(textCheck(newChoice3.getText()));
        question.setOption3Img(fileCheck(newChoice3.getText()));
        if(choiceBox3.getValue().length() != 0){
            question.setOption3Mark(processString(choiceBox3.getValue()));
        }

        question.setOption4Text(textCheck(newChoice4.getText()));
        question.setOption4Img(fileCheck(newChoice4.getText()));
        if(choiceBox4.getValue().length() != 0){
            question.setOption4Mark(processString(choiceBox4.getValue()));
        }

        question.setOption5Text(textCheck(newChoice5.getText()));
        question.setOption5Img(fileCheck(newChoice5.getText()));
        if(choiceBox5.getValue().length() != 0){
            question.setOption5Mark(processString(choiceBox5.getValue()));
        }

        if (choiceBox1.getValue() == "100%") {
            question.setAnswer("A");
            question.setOption2Mark(0);
            question.setOption3Mark(0);
            question.setOption4Mark(0);
            question.setOption5Mark(0);


        } else if (choiceBox2.getValue() == "100%") {
            question.setAnswer("B");
            question.setOption1Mark(0);
            question.setOption3Mark(0);
            question.setOption4Mark(0);
            question.setOption5Mark(0);
        }  else if(choiceBox3.getValue() == "100%"){
            question.setAnswer("C");
            question.setOption1Mark(0);
            question.setOption2Mark(0);
            question.setOption4Mark(0);
            question.setOption5Mark(0);
        }   else if(choiceBox4.getValue() == "100%"){
            question.setAnswer("D");
            question.setOption1Mark(0);
            question.setOption2Mark(0);
            question.setOption3Mark(0);
            question.setOption5Mark(0);
        }   else if(choiceBox5.getValue() == "100%"){
            question.setAnswer("E");
            question.setOption1Mark(0);
            question.setOption2Mark(0);
            question.setOption3Mark(0);
            question.setOption4Mark(0);
        }

        if(newChoice1.getText() == null) {
            question.setOption1Text(newChoice2.getText());
            question.setOption1Mark(processString(choiceBox2.getValue()));
            newChoice2.setText(null);
            if(newChoice2.getText() == null) {
                question.setOption2Text(newChoice3.getText());
                question.setOption2Mark(processString(choiceBox3.getValue()));
                newChoice3.setText(null);
                if(newChoice3.getText() == null) {
                    question.setOption3Text(newChoice4.getText());
                    question.setOption3Mark(processString(choiceBox4.getValue()));
                    newChoice4.setText(null);
                    if(newChoice4.getText() == null){
                        question.setOption4Text(newChoice5.getText());
                        question.setOption4Mark(processString(choiceBox5.getValue()));
                        newChoice5.setText(null);
                    }

                }

            }

        }



        if(newTextQuestion.getText().length()!=0 && newTextAreaQuestion.getText().length() != 0 ) {
            QuestionDAO questionDAO = new QuestionDAO();
            questionDAO.save(question);
            QuestionDAO questionDAO1= new QuestionDAO();
            questionDAO1.delete(deleteQuestions);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("thong bao");
            alert.setContentText(" unsuccessful");
            alert.showAndWait();
        }  Stage stage = (Stage) saveChangeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("ShowQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);


    }


}