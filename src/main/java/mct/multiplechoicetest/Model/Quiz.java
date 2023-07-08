package mct.multiplechoicetest.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz {
    private int quiz_id;
    private String name;
    private int parent_id;


    public Quiz(int quizId, String name, int parentId) {
        quiz_id = quizId;
        this.name = name;
        parent_id = parentId;

    }





    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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
                    "CREATE TABLE IF NOT EXISTS quizzes (" +
                            "  Quizid INT PRIMARY KEY ," +
                            "  name VARCHAR(255)," +
                            "  Parentid INT" +
                            ");");

            statement.executeUpdate();
            System.out.println("Table Quiz created successfully");

            // Close the connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToDatabase() {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO quizzes (Quizid, name, Parentid) VALUES (?, ?, ?)");

            // Save the current quiz
            statement.setInt(1, quiz_id);
            statement.setString(2, name);
            statement.setInt(3, parent_id);
            statement.executeUpdate();

            // Save the child quizzes recursively


            // Close the connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Quiz> getAllQuizzesFromDatabase() {
        List<Quiz> quizzes = new ArrayList<>();

        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM quizzes");

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and create Quiz objects
            while (resultSet.next()) {
                int quizId = resultSet.getInt("Quizid");
                String name = resultSet.getString("name");
                int parentId = resultSet.getInt("Parentid");

                // Create a Quiz object with the retrieved values
                Quiz quiz = new Quiz(quizId, name, parentId);
                quizzes.add(quiz);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public static int getQuizIdFromName(String quizName) {
        int quizId = -1; // Default value if quiz name is not found

        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Quizid FROM quizzes WHERE name = ?");

            // Set the quiz name parameter in the SQL statement
            statement.setString(1, quizName);

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Check if the result set has a row
            if (resultSet.next()) {
                // Retrieve the quiz ID from the result set
                quizId = resultSet.getInt("Quizid");
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return quizId;
    }

    public static Quiz getQuizFromName(String quizName) {
        Quiz quiz = null;

        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM quizzes WHERE name = ?");

            // Set the quiz name parameter in the SQL statement
            statement.setString(1, quizName);

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Check if the result set has a row
            if (resultSet.next()) {
                // Retrieve the quiz ID, name, and parent ID from the result set
                int quizId = resultSet.getInt("Quizid");
                String name = resultSet.getString("name");
                int parentId = resultSet.getInt("Parentid");

                // Create a Quiz object with the retrieved values
                quiz = new Quiz(quizId, name, parentId);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return quiz;
    }



    // chú ý phương thức này trong DAO interface
    public void saveToDatabaseWithNoID() {
        int parentId = 0;

        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);

            // Get the largest quizId from the database
            PreparedStatement getMaxIdStatement = connection.prepareStatement(
                    "SELECT MAX(Quizid) FROM quizzes");
            ResultSet resultSet = getMaxIdStatement.executeQuery();
            int quizId = resultSet.getInt(1) + 1; // Increment the largest ID by 1

            // Prepare the SQL statement for inserting the quiz
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO quizzes (Quizid, name, Parentid) VALUES (?, ?, ?)");
            insertStatement.setInt(1, quizId);
            insertStatement.setString(2, name);

            insertStatement.setInt(3, parent_id);
            insertStatement.executeUpdate();

            // Close the result set, statements, and connection
            resultSet.close();
            getMaxIdStatement.close();
            insertStatement.close();
            connection.close();

            System.out.println("Quiz saved successfully with ID: " + quizId);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Quiz getQuizFromDatabase(int quizId) {
        Quiz quiz = null;

        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM quizzes WHERE Quizid = ?");

            // Set the quiz ID parameter in the SQL statement
            statement.setInt(1, quizId);

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Check if the result set has a row
            if (resultSet.next()) {
                // Retrieve the quiz name and parent ID from the result set
                String name = resultSet.getString("name");
                int parentId = resultSet.getInt("Parentid");

                // Create a Quiz object with the retrieved values
                quiz = new Quiz(quizId, name, parentId);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return quiz;
    }


}
