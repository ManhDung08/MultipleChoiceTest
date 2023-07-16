package mct.multiplechoicetest.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mct.multiplechoicetest.Model.Quiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImportController implements Initializable {
    @FXML
    private JFXButton ImportFileBtn;

    @FXML
    void ImportFile(ActionEvent event) {

        if(a.endsWith(".txt")){
            if(aikenFormat(a.replace("\\", "\\\\"))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thong bao");
                alert.setContentText("OK");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thong bao");
                alert.setContentText("ERROR");
                alert.showAndWait();
            }
            loadQuestion(a.replace("\\", "\\\\"));


        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thong bao");
            alert.setContentText("Wrong Format");
            alert.showAndWait();
        }

    }
    private void loadQuestion(String pathFile) {
        String filename = pathFile; // Replace with the actual file path

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder questionText = new StringBuilder();
            StringBuilder optionA = new StringBuilder();
            StringBuilder optionB = new StringBuilder();
            StringBuilder optionC = new StringBuilder();
            StringBuilder optionD = new StringBuilder();
            StringBuilder optionE = new StringBuilder();
            StringBuilder correctOption = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    if (line.startsWith("ANSWER: ")) {
                        correctOption.append(line.substring(8));
                    } else if (questionText.length() == 0) {
                        questionText.append(line);
                    } else if (optionA.length() == 0) {
                        optionA.append(line);
                    } else if (optionB.length() == 0) {
                        optionB.append(line);
                    } else if (optionC.length() == 0) {
                        optionC.append(line);
                    } else if (optionD.length() == 0) {
                        optionD.append(line);
                    } else if (optionE.length() == 0) {
                        optionE.append(line);
                    }

                } else if (questionText.length() > 0) {
                    // Insert the question into the database
                    insertQuestion(questionText.toString(), optionA.toString(), optionB.toString(),
                            optionC.toString(), optionD.toString(), optionE.toString(), correctOption.toString());

                    // Reset the StringBuilder objects
                    questionText.setLength(0);
                    optionA.setLength(0);
                    optionB.setLength(0);
                    optionC.setLength(0);
                    optionD.setLength(0);
                    optionE.setLength(0);
                    correctOption.setLength(0);
                }
            }

            // Insert the last question if it is not followed by a blank line
            if (questionText.length() > 0) {
                insertQuestion(questionText.toString(), optionA.toString(), optionB.toString(),
                        optionC.toString(), optionD.toString(), optionE.toString(), correctOption.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static void insertQuestion(String questionText, String optionA, String optionB, String optionC,
                                       String optionD, String optionE, String correctOption) throws SQLException, ClassNotFoundException {
        // Register the JDBC driver
        Class.forName("org.sqlite.JDBC");

        // Define the connection URL for the SQLite database
        String url = "jdbc:sqlite:D:\\hoclaptrinhjava\\MultipleChoiceTest\\csdl.db";

        // Open a connection to the database
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO questions (question_text, option1_text, option2_text, option3_text, option4_text,option5_text, answer,quiz_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");






        {

            statement.setString(1, questionText);
            statement.setString(2, optionA);
            statement.setString(3, optionB);
            statement.setString(4, optionC);
            statement.setString(5, optionD);
            statement.setString(6, optionE);
            statement.setString(7, correctOption);
            statement.setInt(8, Quiz.getLatestAddedQuizFromDatabase().getQuiz_id());

            statement.executeUpdate();

        }
    }

    @FXML
    private JFXButton getPathFileButton;
    private static String a;

    @FXML
     String getPathFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn tệp");

        Stage stage = new Stage();
        stage.setTitle("JavaFX FileChooser");

        // Thiết lập bộ lọc tệp (nếu muốn)
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Tất cả các tệp", "*.*");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Hiển thị hộp thoại chọn tệp
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            String selectedFilePath = selectedFile.getAbsolutePath();
            System.out.println("Đường dẫn tệp đã chọn: " + selectedFilePath);
            a = selectedFilePath;

            return selectedFilePath;
        } else {
            System.out.println("Không có tệp nào được chọn.");
            return null;
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private AnchorPane getPathFileByDDBtn;


    public void getPathFileDD(DragEvent event) {
    }
    private boolean aikenFormat(String inputString){
        String filePath = inputString; // Thay bằng đường dẫn phù hợp

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            int questionCount = 0;
            boolean isValidFormat = true;
            Pattern answerPattern = Pattern.compile("^[A-Z]\\. .+");
            Set<String> validAnswers = new HashSet<>();
            boolean isQuestion = false;
            boolean hasCorrectAnswer = false;
            boolean hasBlankLine = false;
            int answerLineCount = 0;

            while ((line = br.readLine()) != null) {
                lineCount++;
                line = line.trim();

                if (line.isEmpty()) {
                    if (isQuestion) {
                        if (!hasCorrectAnswer || !hasBlankLine || answerLineCount < 2) {
                            // Không đúng AikenFormat
                            isValidFormat = false;
                            break;
                        }
                        isQuestion = false;
                        hasCorrectAnswer = false;
                        hasBlankLine = false;
                        answerLineCount = 0; // Reset answer line count
                    }
                    continue;
                }

                Matcher answerMatcher = answerPattern.matcher(line);

                if (!isQuestion) {
                    // First line of a new question
                    questionCount++;
                    isQuestion = true;
                    hasCorrectAnswer = false;
                    hasBlankLine = false;
                    answerLineCount = 0; // Reset answer line count
                } else if (answerMatcher.matches()) {
                    // Answer line within a question
                    validAnswers.add(line.substring(0, 1));
                    hasBlankLine = false;
                    answerLineCount++; // Increment answer line count
                } else if (line.startsWith("ANSWER: ")) {
                    // Correct answer line within a question
                    String correctAnswer = line.substring(8).trim();

                    if (!validAnswers.contains(correctAnswer)) {
                        // Invalid correct answer
                        isValidFormat = false;
                        break;
                    }

                    hasCorrectAnswer = true;
                    hasBlankLine = false;
                } else {
                    // Invalid line within a question
                    isValidFormat = false;
                    break;
                }

                hasBlankLine = true; // Set the flag when a non-empty line is encountered
            }

            if (isValidFormat && questionCount > 0 && hasCorrectAnswer && answerLineCount >= 2) {
                System.out.println("Success: " + questionCount + " question(s) found");
            } else {
                System.out.println("Error at line " + lineCount);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }




}