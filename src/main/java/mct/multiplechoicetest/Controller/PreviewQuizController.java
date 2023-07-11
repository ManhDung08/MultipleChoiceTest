package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviewQuizController implements Initializable {

    @FXML
    private JFXButton buttonQuizName;

    @FXML
    private Label labelQuizName;
    @FXML
    private Label getTimeLimit;

    public void setGetTimeLimit(String value) {
        this.getTimeLimit.setText(value);
    }

    public void setButtonQuizName(String value) {
        this.buttonQuizName.setText(value);
    }

    public void setLabelQuizName(String value) {
        this.labelQuizName.setText(value);
    }
    @FXML
    private JFXButton goAddQuestionBtn;
    @FXML
    private VBox vBoxD;
    @FXML
    void goAddQuestion(ActionEvent event) {
        if (event.getSource() == goAddQuestionBtn) {
            if (vBoxD.visibleProperty().getValue() == true) {
                vBoxD.setVisible(false);
            } else vBoxD.setVisible(true);

        }

    }
    @FXML
    private TextField labelGrade;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelGrade.setText("10.00");
    }
    @FXML
    private JFXButton changPaneBtn;
    @FXML
    private AnchorPane paneSau;

    @FXML
    private AnchorPane paneTruoc;
    @FXML
    private Label editQuizL;
    @FXML
    private Label gachcheo1;
    @FXML
    private JFXButton buttonQuizName1;
    @FXML
    void changPane(ActionEvent event) {
        if (event.getSource() == changPaneBtn) {
            paneTruoc.setVisible(false);
            paneSau.setVisible(true);
            editQuizL.setText(buttonQuizName.getText());
            gachcheo1.setVisible(true);
            buttonQuizName1.setVisible(true);
        }

    }
    @FXML
    private JFXButton goBankQuestionBtn;

    @FXML
    private JFXButton goRandomQuestionBtn;
    @FXML
    void goBankQuestion(ActionEvent event) throws IOException {
        Stage stage = (Stage) goBankQuestionBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("BankQuiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("app");
        stage.setScene(scene);
    }

    @FXML
    void goRandomQuestion(ActionEvent event) {

    }

}
