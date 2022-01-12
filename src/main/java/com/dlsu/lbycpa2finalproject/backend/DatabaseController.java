package com.dlsu.lbycpa2finalproject.backend;
import com.opencsv.*;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.util.*;

public class DatabaseController {
    private LinkedList<QuestionObject> questions = new LinkedList<QuestionObject>();
    private int numberQuestions;

    public QuizObject loadDatabase(String filename) throws Exception {
        Reader reader = Files.newBufferedReader(java.nio.file.Path.of("src/main/assets/" + filename));
        List<String[]> data = readAll(reader);
        numberQuestions = data.size();
        LinkedList<QuestionObject> questions = new LinkedList<>();
        QuestionObject temp;
        QuizObject quiz = new QuizObject();

        for (int i = 0; i < numberQuestions; i++) {
            temp = new QuestionObject();
            temp.setQuestion(data.get(i)[0]);
            temp.setChoices(new String[] {data.get(i)[1], data.get(i)[2], data.get(i)[3], data.get(i)[4]});
            temp.setAnswer(Integer.parseInt(data.get(i)[5]));
            temp.setPointWeight(Integer.parseInt(data.get(i)[6]));

            questions.add(temp);
        }

        quiz.setQuestions(questions);
        return quiz;
    }

    private List<String[]> readAll(Reader reader) throws Exception {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(false)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();

        List<String[]> list;
        list = csvReader.readAll();

        reader.close();
        csvReader.close();
        return list;
    }

}
