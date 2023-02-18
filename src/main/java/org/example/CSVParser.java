package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements AutoCloseable {
    private BufferedReader reader;
    private String currentLine;
    private int currentLineNumber;

    private int count;

    public CSVParser(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
        currentLineNumber = 0;
        nextLine();
    }



    private void nextLine() throws IOException {
        currentLine = reader.readLine();
        currentLineNumber++;
    }

    public void close() throws IOException {
        reader.close();
    }

    public  int countColumns(String filePath) throws IOException {
        try (CSVParser parser = new CSVParser(filePath)) {
            String firstLine = parser.getCurrentLine();
            if (firstLine == null) {
                return 0;
            }
            int count = 1;
            for (int i = 0; i < firstLine.length(); i++) {
                if (firstLine.charAt(i) == ',') {
                    count++;
                }
            }
            return count;
        }
    }
    public  void searchCsvFile (String filePath,int column, String symbol){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            long startTime = System.currentTimeMillis(); // запоминаем время начала поиска
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // разбиваем строку на поля
                if (data.length > column && data[column].indexOf(symbol) != -1) {
                    System.out.println(line);
                    count++;
                }
            }
            long endTime = System.currentTimeMillis(); // запоминаем время окончания поиска
            System.out.println("Search time: " + (endTime - startTime) + " ms");
            System.out.println("Количество строк: "+count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getCurrentLine() {
        return currentLine;
    }


    public void setCurrentLine(String currentLine) {
        this.currentLine = currentLine;
    }

    public int getCurrentLineNumber() {
        return currentLineNumber;
    }

    public void setCurrentLineNumber(int currentLineNumber) {
        this.currentLineNumber = currentLineNumber;
    }
}
