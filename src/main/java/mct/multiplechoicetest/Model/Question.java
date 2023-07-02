package mct.multiplechoicetest.Model;

import java.sql.*;
import java.util.ArrayList;

public class Question {
    private Quiz quiz;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionImg() {
        return questionImg;
    }

    public void setQuestionImg(String questionImg) {
        this.questionImg = questionImg;
    }

    public int getQuestionMark() {
        return questionMark;
    }

    public void setQuestionMark(int questionMark) {
        this.questionMark = questionMark;
    }

    public String getOption1Text() {
        return option1Text;
    }

    public void setOption1Text(String option1Text) {
        this.option1Text = option1Text;
    }

    public String getOption1Img() {
        return option1Img;
    }

    public void setOption1Img(String option1Img) {
        this.option1Img = option1Img;
    }

    public int getOption1Mark() {
        return option1Mark;
    }

    public void setOption1Mark(int option1Mark) {
        this.option1Mark = option1Mark;
    }

    public String getOption2Text() {
        return option2Text;
    }

    public void setOption2Text(String option2Text) {
        this.option2Text = option2Text;
    }

    public String getOption2Img() {
        return option2Img;
    }

    public void setOption2Img(String option2Img) {
        this.option2Img = option2Img;
    }

    public int getOption2Mark() {
        return option2Mark;
    }

    public void setOption2Mark(int option2Mark) {
        this.option2Mark = option2Mark;
    }

    public String getOption3Text() {
        return option3Text;
    }

    public void setOption3Text(String option3Text) {
        this.option3Text = option3Text;
    }

    public String getOption3Img() {
        return option3Img;
    }

    public void setOption3Img(String option3Img) {
        this.option3Img = option3Img;
    }

    public int getOption3Mark() {
        return option3Mark;
    }

    public void setOption3Mark(int option3Mark) {
        this.option3Mark = option3Mark;
    }

    public String getOption4Text() {
        return option4Text;
    }

    public void setOption4Text(String option4Text) {
        this.option4Text = option4Text;
    }

    public String getOption4Img() {
        return option4Img;
    }

    public void setOption4Img(String option4Img) {
        this.option4Img = option4Img;
    }

    public int getOption4Mark() {
        return option4Mark;
    }

    public void setOption4Mark(int option4Mark) {
        this.option4Mark = option4Mark;
    }

    public String getOption5Text() {
        return option5Text;
    }

    public void setOption5Text(String option5Text) {
        this.option5Text = option5Text;
    }

    public String getOption5Img() {
        return option5Img;
    }

    public void setOption5Img(String option5Img) {
        this.option5Img = option5Img;
    }

    public int getOption5Mark() {
        return option5Mark;
    }

    public void setOption5Mark(int option5Mark) {
        this.option5Mark = option5Mark;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private int questionId;
    private String questionText;
    private String questionImg;
    private int questionMark;
    private String option1Text;
    private String option1Img;
    private int option1Mark;
    private String option2Text;
    private String option2Img;
    private int option2Mark;
    private String option3Text;
    private String option3Img;
    private int option3Mark;
    private String option4Text;
    private String option4Img;
    private int option4Mark;
    private String option5Text;
    private String option5Img;
    private int option5Mark;
    private String answer;

    public Question() {
        this.quiz = quiz;
    }

    public Question(int questionId) {
        this.questionId = questionId;
    }

    public Question(Quiz quiz, int questionId, String questionText, String questionImg, int questionMark, String option1Text, String option1Img, int option1Mark, String option2Text, String option2Img, int option2Mark, String option3Text, String option3Img, int option3Mark, String option4Text, String option4Img, int option4Mark, String option5Text, String option5Img, int option5Mark, String answer) {
        this.quiz = quiz;
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionImg = questionImg;
        this.questionMark = questionMark;
        this.option1Text = option1Text;
        this.option1Img = option1Img;
        this.option1Mark = option1Mark;
        this.option2Text = option2Text;
        this.option2Img = option2Img;
        this.option2Mark = option2Mark;
        this.option3Text = option3Text;
        this.option3Img = option3Img;
        this.option3Mark = option3Mark;
        this.option4Text = option4Text;
        this.option4Img = option4Img;
        this.option4Mark = option4Mark;
        this.option5Text = option5Text;
        this.option5Img = option5Img;
        this.option5Mark = option5Mark;
        this.answer = answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    public static void createTable() {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS questions (" +
                            "  question_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "  question_text VARCHAR," +
                            "  question_img VARCHAR," +
                            "  question_mark INTEGER," +
                            "  option1_text VARCHAR," +
                            "  option1_img VARCHAR," +
                            "  option1_mark INTEGER," +
                            "  option2_text VARCHAR," +
                            "  option2_img VARCHAR," +
                            "  option2_mark INTEGER," +
                            "  option3_text VARCHAR," +
                            "  option3_img VARCHAR," +
                            "  option3_mark INTEGER," +
                            "  option4_text VARCHAR," +
                            "  option4_img VARCHAR," +
                            "  option4_mark INTEGER," +
                            "  option5_text VARCHAR," +
                            "  option5_img VARCHAR," +
                            "  option5_mark INTEGER," +
                            "  answer VARCHAR," +
                            "  quiz_id INTEGER," +
                            "  FOREIGN KEY (quiz_id) REFERENCES quizzes (Quizid)" +
                            ");");

            statement.executeUpdate();
            System.out.println("Table questions created successfully");

            // Close the connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    public static int countQuestionsForQuiz(int quizId) {
        int questionCount = 0;
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");


            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);

            // Accept the quiz_id input from the user


            // Prepare the SQL statement
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) AS question_count FROM questions WHERE quiz_id = ?"
            );
            statement.setInt(1, quizId);

            // Execute the SQL statement and retrieve the result
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionCount = resultSet.getInt("question_count");
                // System.out.println("Number of questions for quiz_id " + quizId + ": " + questionCount);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return questionCount;
    }

    public static  ArrayList<Question> selectAllByQuizId(int quizId) {
        ArrayList<Question> questions = new ArrayList<>();
        String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM questions WHERE quiz_id = ?")) {
            statement.setInt(1, quizId);

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
}
