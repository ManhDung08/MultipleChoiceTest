package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mct.multiplechoicetest.StartApp;

import java.io.IOException;


public class HomeController {

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

}


