package mct.multiplechoicetest;

import javafx.application.Application;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.Quiz;


import java.util.List;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        List<Question> questions = Question.selectAllByQuizId(4);
        addChildQuestion(questions,4);
        for(Question question : questions){
            System.out.println(question.getQuestionText());
        }
    }
    private boolean addChildQuestion(List<Question> questions, int parenId){
       List<Quiz> quizzes = Quiz.getAllQuizzesFromDatabase();
       for(Quiz quiz : quizzes){
           if (quiz.getParent_id() == parenId){
               List<Question>questionList= Question.selectAllByQuizId(quiz.getQuiz_id());
               questions.addAll(questionList);
               addChildQuestion(questions, quiz.getQuiz_id());
           }
       }
        return false;
    }




}
