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
    // Đường dẫn chính xác  D:\hoclaptrinhjava\MultipleChoiceTest1\csdl.db
    // Đường dẫn hợp lệ  :  D:\\hoclaptrinhjava\\MultipleChoiceTest1\\csdl.db
        // hoặc             D:/hoclaptrinhjava/MultipleChoiceTest1/csdl.db

    }
    public static void main(String[] args) {
        launch();
    }

}
