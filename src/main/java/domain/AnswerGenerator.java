package domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class AnswerGenerator {
    private static final LocalDate CRITERIA_DATE = LocalDate.of(2021, 6, 19);
    private List<String> words;
    private final LocalDate criteriaDate;

    public AnswerGenerator(String filePath) {
        this.words = getWordList(filePath);
        this.criteriaDate = CRITERIA_DATE;
    }

    public String getAnswer(LocalDate currentDate) {
        Period period = Period.between(criteriaDate, currentDate);
        int days = period.getDays();
        return words.get(days % words.size());
    }

    private List<String> getWordList(String filePath) {
        getFile(filePath);
        if (words.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }
        return words;
    }

    private void getFile(String filePath) {
        Path path = Path.of(filePath);
        try {
            words = Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 변환중에 err가 발생하였습니다.");
        }
    }
}
