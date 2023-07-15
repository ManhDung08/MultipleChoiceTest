package mct.multiplechoicetest.Model;

import javafx.event.ActionEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionMap {
    private int QuestionMapID;
    private QuizMap quizMap;
    private static Question question;

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
    public static List<Question> getAllQuestionsByQuizMap(QuizMap quizMap) {
        ArrayList<Question> questions = new ArrayList<>();
        String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement("Select * from questions, QuestionMap\n" +
                     "Where (questions.question_id=QuestionMap.QuestionID) and (QuestionMap.QuizMapID=?)")) {
            statement.setInt(1, quizMap.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Question question = new Question();
                    question.setQuestionId(resultSet.getInt("question_id"));
                    question.setQuestionText(resultSet.getString("question_text"));
                    question.setQuestionImg(resultSet.getString("question_img"));
                    question.setQuestionMark(resultSet.getInt("question_mark"));
                    question.setOption1Text(resultSet.getString("option1_text"));
                    question.setOption1Img(resultSet.getString("option1_img"));
                    question.setOption1Mark(resultSet.getInt("option1_mark"));
                    question.setOption2Text(resultSet.getString("option2_text"));
                    question.setOption2Img(resultSet.getString("option2_img"));
                    question.setOption2Mark(resultSet.getInt("option2_mark"));
                    question.setOption3Text(resultSet.getString("option3_text"));
                    question.setOption3Img(resultSet.getString("option3_img"));
                    question.setOption3Mark(resultSet.getInt("option3_mark"));
                    question.setOption4Text(resultSet.getString("option4_text"));
                    question.setOption4Img(resultSet.getString("option4_img"));
                    question.setOption4Mark(resultSet.getInt("option4_mark"));
                    question.setOption5Text(resultSet.getString("option5_text"));
                    question.setOption5Img(resultSet.getString("option5_img"));
                    question.setOption5Mark(resultSet.getInt("option5_mark"));
                    question.setAnswer(resultSet.getString("answer"));
                    // Set the Quiz object for the question

                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;


    }
    public static ActionEvent deleteQuestionMapByQuestion(Question question) {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM QuestionMap WHERE QuestionID = ?");

            // Set the parameter value
            statement.setInt(1, question.getQuestionId());

            // Execute the SQL statement
            statement.executeUpdate();
            System.out.println("QuestionMap deleted successfully");

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }






}
