package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main (String[]args) throws IOException {
           final String CSV_FILE="C:\\Users\\chuma\\IdeaProjects\\untitled23\\src\\main\\java\\org\\example\\airports.csv";
            CSVParser csvParser=new CSVParser(CSV_FILE);
            final int COLUMS=csvParser.countColumns(CSV_FILE);
            String findColumn=args[0];
            int column1= Integer.parseInt(findColumn);
            if(column1>COLUMS||column1<1){
                System.out.println("Значение должно быть от 1 до "+COLUMS);;
                System.exit(404);
            }
            Scanner scanner=new Scanner(System.in);
            while (true){
                String symbol=scanner.nextLine();
                if(symbol.equals("!quit")){
                    break;
                }
                csvParser.searchCsvFile(CSV_FILE,column1,symbol);



            }

        }
    }







