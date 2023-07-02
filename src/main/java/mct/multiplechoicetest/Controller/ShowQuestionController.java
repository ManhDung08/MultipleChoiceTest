package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
        System.out.println(xuLiChuoi(treeView.getSelectionModel().getSelectedItem().getValue()));

    }
    String xuLiChuoi(String inputString){
        if(inputString.charAt(inputString.length() - 1) == ')'){
            int index = inputString.indexOf(" (");
            inputString= inputString.substring(0,index);
        }else return inputString;
        return  inputString;
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


    @FXML
    private CheckBox showQuestionCheckBox;


    @FXML
    private ListView<?> listView;

    public void showQuestion(ActionEvent actionEvent) {
        if (showQuestionCheckBox.isSelected()) {
            listView.setVisible(true);
            showQuestionsInListView((ListView<String>) listView);
        } else {
            listView.setVisible(false);
        }
    }

    private void showQuestionsInListView(ListView<String> listView) {
      ArrayList<Question> questions = Question.selectAllByQuizId(Quiz.getQuizIdFromName(xuLiChuoi(treeView.getSelectionModel().getSelectedItem().getValue())));

      ArrayList<String> questionList = new ArrayList<>();
      for (Question question : questions){
          questionList.add("     "+ question.getQuestionText()+" " +question.getQuestionImg()+question.getQuestionMark()+" "+question.getOption1Text()+question.getOption1Img()+question.getOption1Mark()+
                  " "+question.getOption2Text()+" "+question.getOption2Img()+" "+question.getOption2Mark()+
                  " "+question.getOption3Text()+" "+question.getOption3Img()+" "+question.getOption3Mark()+
                  " "+question.getOption4Text()+" "+question.getOption4Img()+" "+question.getOption4Mark()+
                  " "+question.getOption5Text()+" "+question.getOption5Img()+" "+question.getOption5Mark()
          );
      }
        ObservableList<String> items = FXCollections.observableArrayList(questionList);
        listView.setItems(items);
        listView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Tạo layout tùy chỉnh cho từng item
                    HBox hbox = new HBox();
                    Label nameLabel = new Label(item);
                    nameLabel.setPrefWidth(500);
                    CheckBox checkBox = new CheckBox();
                    checkBox.setText(null);
                    FontAwesomeIconView iconView = new FontAwesomeIconView();

                    iconView.setGlyphName("SORT_DOWN");
                    iconView.setSize("12px");
                    iconView.setFill(Paint.valueOf("#0000FF"));

                    JFXButton editButton = new JFXButton("Edit");
                    editButton.setStyle("-fx-text-fill: blue;");
                    // Thiết lập sự kiện cho nút Edit
                    editButton.setOnAction(event -> {
                        // Xử lý sự kiện khi nút Edit được nhấn
                        System.out.println("Edit button clicked for item: " + item);
                    });
                    hbox.getChildren().addAll(checkBox,nameLabel,new Region(), editButton,iconView);
                    HBox.setHgrow(new Region(), Priority.ALWAYS);
                    setGraphic(hbox);
                }
            }
        });
    }


}






