package mct.multiplechoicetest.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionMap {
    private int QuestionMapID;
    private QuizMap quizMap;
    private Question question;

    public int getQuestionMapID() {
        return QuestionMapID;
    }

    public void setQuestionMapID(int questionMapID) {
        QuestionMapID = questionMapID;
    }

    public QuizMap getQuizMap() {
        return quizMap;
    }

    public void setQuizMap(QuizMap quizMap) {
        this.quizMap = quizMap;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionMap(int questionMapID, QuizMap quizMap, Question question) {
        QuestionMapID = questionMapID;
        this.quizMap = quizMap;
        this.question = question;
    }
    public static void createTable() {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE QuestionMap (\n" +
                    "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "  QuizMapID INT,\n" +
                    "  QuestionID INT,\n" +
                    "  FOREIGN KEY (QuizMapID) REFERENCES QuizMap (ID),\n" +
                    "  FOREIGN KEY (QuestionID) REFERENCES questions (question_id)\n" +
                    ");\n");

            statement.executeUpdate();
            System.out.println("Table QuestionMap created successfully");

            // Close the connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void save(QuizMap quizMap, Question question) {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO QuestionMap (QuizMapID, QuestionID) VALUES (?, ?)");

            // Set the parameter values
            statement.setInt(1, quizMap.getId());
            statement.setInt(2, question.getQuestionId());

            // Execute the SQL statement
            statement.executeUpdate();
            System.out.println("QuestionMap saved successfully");

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
