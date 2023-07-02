package mct.multiplechoicetest.Dao;

import mct.multiplechoicetest.Model.Question;
import mct.multiplechoicetest.Model.Quiz;

import java.sql.*;
import java.util.ArrayList;

public class QuestionDAO implements DAOinterface<Question> {
    private static final String DB_URL = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

    @Override
    public int save(Question question) {
        int rowsAffected = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO questions (question_id,question_text, question_img, question_mark, option1_text, option1_img, " +
                             "option1_mark, option2_text, option2_img, option2_mark, option3_text, option3_img, " +
                             "option3_mark, option4_text, option4_img, option4_mark, option5_text, option5_img, " +
                             "option5_mark, answer, quiz_id) " +
                             "VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            //statement.setInt(1,question.getQuestionId());
            statement.setString(2, question.getQuestionText());
            statement.setString(3, question.getQuestionImg());
            statement.setInt(4, question.getQuestionMark());
            statement.setString(5, question.getOption1Text());
            statement.setString(6, question.getOption1Img());
            statement.setInt(7, question.getOption1Mark());
            statement.setString(8, question.getOption2Text());
            statement.setString(9, question.getOption2Img());
            statement.setInt(10, question.getOption2Mark());
            statement.setString(11, question.getOption3Text());
            statement.setString(12, question.getOption3Img());
            statement.setInt(13, question.getOption3Mark());
            statement.setString(14, question.getOption4Text());
            statement.setString(15, question.getOption4Img());
            statement.setInt(16, question.getOption4Mark());
            statement.setString(17, question.getOption5Text());
            statement.setString(18, question.getOption5Img());
            statement.setInt(19, question.getOption5Mark());
            statement.setString(20, question.getAnswer());
            statement.setInt(21, question.getQuiz().getQuiz_id());

            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;

    }

    @Override
    public int update(Question question) {
        int rowsAffected = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE questions SET question_text = ?, question_img = ?, question_mark = ?, " +
                             "option1_text = ?, option1_img = ?, option1_mark = ?, option2_text = ?, " +
                             "option2_img = ?, option2_mark = ?, option3_text = ?, option3_img = ?, " +
                             "option3_mark = ?, option4_text = ?, option4_img = ?, option4_mark = ?, " +
                             "option5_text = ?, option5_img = ?, option5_mark = ?, answer = ?, quiz_id = ? " +
                             "WHERE question_id = ?")) {
            statement.setString(1, question.getQuestionText());
            statement.setString(2, question.getQuestionImg());
            statement.setInt(3, question.getQuestionMark());
            statement.setString(4, question.getOption1Text());
            statement.setString(5, question.getOption1Img());
            statement.setInt(6, question.getOption1Mark());
            statement.setString(7, question.getOption2Text());
            statement.setString(8, question.getOption2Img());
            statement.setInt(9, question.getOption2Mark());
            statement.setString(10, question.getOption3Text());
            statement.setString(11, question.getOption3Img());
            statement.setInt(12, question.getOption3Mark());
            statement.setString(13, question.getOption4Text());
            statement.setString(14, question.getOption4Img());
            statement.setInt(15, question.getOption4Mark());
            statement.setString(16, question.getOption5Text());
            statement.setString(17, question.getOption5Img());
            statement.setInt(18, question.getOption5Mark());
            statement.setString(19, question.getAnswer());
            statement.setInt(20, question.getQuiz().getQuiz_id());


            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;

    }

    @Override
    public int delete(Question question) {
        int rowsAffected = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM questions WHERE question_id = ?")) {
            statement.setInt(1, question.getQuestionId());

            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAffected;

    }

    @Override
    public ArrayList<Question> selectAll() {
        ArrayList<Question> questions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM questions")) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;

    }

    @Override
    public Question selectById(Question question) {

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM questions WHERE question_id = ?")) {
            statement.setInt(1, question.getQuestionId());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }

    @Override
    public Question selectByQuizId(Question question) {
        return null;
    }


}