//Your Name          :Bo Ying, Su
//Class and Section  :CIS35A
//Assignment Number  :5
package util;

import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import static adapter.Gradeable.DEBUG;

public class FileIO {
    final int titleLines = 1;
    final int columns = 5;

    public Student[] readFile(String filename, Student[] studentArr) {
        int i = 0;
        int skipLinesAtStart = titleLines;
        try {
            FileReader file = new FileReader(filename); //Open the file using FileReader Object.
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine(); //In a loop read a line using readLine method.
                if (line == null) {
                    eof = true;
                } else if (skipLinesAtStart > 0) {
                    if (DEBUG)
                        System.out.println("Reading" + line);
                    //Skip the title line
                    skipLinesAtStart--;
                } else {
                    //create new student for each line
                    studentArr[i] = new Student();
                    //tokenize the line
                    StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreTokens()) {
                        //first token is student id
                        studentArr[i].setSID(Integer.parseInt(st.nextToken()));
                        if (DEBUG)
                            System.out.println("StudentID read: "+studentArr[i].getSID());
                        //the rest of the tokens are quiz scores
                        int[] data = new int[columns];
                        for (int j = 0; j < columns; j++) {
                            data[j] = Integer.parseInt(st.nextToken());
                            if (DEBUG)
                                System.out.println("Data read: "+data[j]);

                        }
                        studentArr[i].setScores(data);
                    }
                    i++;
                }

            }
            buff.close();
        } catch (IOException e) {
            //print exception to console
            if (DEBUG)
                System.out.println(e.toString());
        }
        return studentArr;
    }

}