package mct.multiplechoicetest.AikenFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFile {
    public static void main(String[] args) {
        String filePath = "D:\\hoclaptrinhjava\\MultipleChoiceTest\\src\\main\\java\\mct\\multiplechoicetest\\AikenFormat\\Test1.txt"; // Thay bằng đường dẫn phù hợp

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

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
