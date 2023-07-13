package mct.multiplechoicetest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.QuestionMap;
import mct.multiplechoicetest.Model.Quiz;
import mct.multiplechoicetest.Model.QuizResults;


public class StartApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApp.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BTL lập trình HDT");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {
        launch();
    }
    // còn xử lí nốt điểm cho từng câu hỏi
    //////
}
