package mct.multiplechoicetest.Model;

import java.sql.*;
import java.util.Date;

public class QuizResults {
    private int id;
    private Quiz quiz;
    private int rightAnswer;
    private Timestamp timelimit;
    {
        timelimit = new Timestamp(new Date().getTime());
    }

    public QuizResults(int id, Quiz quiz, int rightAnswer) {
        this.id = id;
        this.quiz = quiz;
        this.rightAnswer = rightAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Timestamp getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(Timestamp timelimit) {
        this.timelimit = timelimit;
    }
    public static void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS quiz_results (" +
                "quiz_results_id INTEGER PRIMARY KEY," +
                "quiz_id INTEGER NOT NULL," +
                "right_answer INTEGER," +
                "FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id)" +
                ");";

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db");
            statement = connection.createStatement();
            statement.executeUpdate(createTableQuery);
            System.out.println("Table 'quiz_results' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();

                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
