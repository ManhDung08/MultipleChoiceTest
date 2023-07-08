package mct.multiplechoicetest.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizMap {
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public QuizMap(int id, Quiz quiz, int timeLimit) {
        Id = id;
        this.quiz = quiz;
        this.timeLimit = timeLimit;
    }

    private Quiz quiz;
    private int timeLimit;
    public static void createTable() {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS QuizMap (\n" +
                    "\t\t\tID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\t\tTimeLimit INT,\n" +
                    "\t\t\tQuizID INT,\n" +
                    "\t\t\tFOREIGN KEY(QuizID) REFERENCES quizzes(Quizid)\n" +
                    "  \t\t\t)");

            statement.executeUpdate();
            System.out.println("Table QuizMap created successfully");

            // Close the connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void save(QuizMap quizMap) {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);

            // Prepare the SQL statement to insert the QuizMap
            String sql = "INSERT INTO QuizMap (TimeLimit, QuizID) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set the values for the parameters in the SQL statement
            statement.setInt(1, timeLimit);
            statement.setInt(2, quizMap.getQuiz().getQuiz_id());

            // Execute the SQL statement to insert the QuizMap
            statement.executeUpdate();
            System.out.println("QuizMap saved successfully");

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<QuizMap> getAllQuizMapFromDatabase() {
        List<QuizMap> quizMaps = new ArrayList<>();

        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Define the connection URL for the SQLite database
            String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

            // Open a connection to the database
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM QuizMap");

            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set and create QuizMap objects
            while (resultSet.next()) {
                int quizMapId = resultSet.getInt("ID");
                int timeLimit = resultSet.getInt("TimeLimit");
                int quizId = resultSet.getInt("QuizID");

                // Retrieve the associated quiz from the database
                Quiz quiz = Quiz.getQuizFromDatabase(quizId); // Implement this method to retrieve the Quiz object

                // Create a QuizMap object with the retrieved values
                QuizMap quizMap = new QuizMap(quizMapId, quiz, timeLimit);
                quizMaps.add(quizMap);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return quizMaps;
    }



}

