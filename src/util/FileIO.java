//Your Name          :Bo Ying, Su
//Class and Section  :CIS35A
//Assignment Number  :6
package util;

import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import static adapter.Gradeable.DEBUG;

public class FileIO {
    final int titleLines = 1;
    final int numofquizes = 5;

    public Student[] readFile(String filename, Student[] stu) {
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
                        System.out.println("\nReading Line: " + line);
                    //Skip the title line
                    skipLinesAtStart--;
                } else {
                    //create new student for each line
                	
                	if(i<stu.length) {
	                    stu[i] = new Student();
	                    //tokenize the line
	                    StringTokenizer st = new StringTokenizer(line);
	                    while (st.hasMoreTokens()) {
	                        //first token is student id
	                        stu[i].setSID(Integer.parseInt(st.nextToken()));
	                        if (DEBUG)
	                            System.out.println("StudentID read: "+stu[i].getSID());
	                        //the rest of the tokens are quiz scores
	                        int[] data = new int[numofquizes];
	                        for (int j = 0; j < numofquizes; j++) {
	                            data[j] = Integer.parseInt(st.nextToken());
	                            if (DEBUG)
	                                System.out.println("Data read: "+data[j]);
	
	                        }
	                        stu[i].setScores(data);
	                    }
	                    i++;
                	}
                    
                    
                }

            }
            buff.close();
        } catch (IOException e) {
            //print exception to console
            if (DEBUG)
                System.out.println(e.toString());
        }
        return stu;
    }

}