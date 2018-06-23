package adapter;

import model.Statistics;
import model.Student;
import model.StudentRecord;
import util.FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Gradebook implements Gradeable, Instructable {
    StudentRecord[] sturec;
    Gradebook(){}
    Gradebook(String fname) {
    	readFileAndSerielize(fname);
    }

    private void readFileAndSerielize(String fname) {
    	
        //a. read the file and build a student array.
        Student labtmp[] = new Student[40];
        FileIO fileIO = new FileIO();
        //Populate the student array
        labtmp = fileIO.readFile(fname, labtmp);
        //remove null elements
        List<Student> listofStudents = new ArrayList<>();
        for (int i = 0; i < labtmp.length; i++) {
            if (labtmp[i] != null) {
                listofStudents.add(labtmp[i]);
            }
        }
        //Create Student Array
        Student[] lab2 = listofStudents.toArray(new Student[listofStudents.size()]);

        ///b. compute statistics
        Statistics statlab2 = new Statistics();
        statlab2.calculateStatistics(lab2);

        //c. build StudentRecord []
        sturec = new StudentRecord[listofStudents.size()];
        for (int i = 0; i < sturec.length; i++) {
            sturec[i] = new StudentRecord(lab2[i], statlab2);

        }

        //d. serialize StudentRecord array (studentid.dat or studentid.ser
        for (int i = 0; i < sturec.length; i++) {
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(new FileOutputStream(String.valueOf(sturec[i].thisstudent.getSID()) + ".dat"));
            } catch (IOException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
            try {
                out.writeObject(sturec[i]); //writes an object to disk
            } catch (IOException e) {
                if (DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    	
    	
    }
    public void printstats(int studid) {
        //deserialize any file and print stats.
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(studid + ".dat"));
            StudentRecord b1 = (StudentRecord) in.readObject(); //return an Object;
            b1.thisstatistics.print();
        } catch (IOException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    public void printscores(int studid) {
        ObjectInputStream in = null;
        try {
            //deserialize studid.dat or studid.ser
            in = new ObjectInputStream(new FileInputStream(studid + ".dat"));
            StudentRecord b1 = (StudentRecord) in.readObject();

            //call print method in student class.
            b1.thisstudent.printScore();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void MakeGradeBook(String filename) {
    	//Make Gradebook According to desired Subject
    	readFileAndSerielize(filename);
    }

    @Override
    public void printall() {
        ObjectInputStream in = null;
        File curDir = new File(".");
        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isDirectory());
            if(f.isFile()){
                if(f.getName().substring(f.getName().lastIndexOf('.')+1).equals("dat")){
                    printgrades(Integer.valueOf(f.getName().replaceFirst("[.][^.]+$", "")));
                }
            }
        }
    }

    public String convert2grade(double score){
        if(score>=97) return ("A+");
        else if(score>=93) return ("A");
        else if(score>=90) return ("A-");
        else if(score>=87) return ("B+");
        else if(score>=83) return ("B");
        else if(score>=80) return ("B-");
        else if(score>=77) return ("C+");
        else if(score>=73) return ("C");
        else if(score>=70) return ("D+");
        else if(score>=63) return ("D");
        else if(score>=60) return ("D-");
        else  return ("F");
    }

    private double average(int[] data) {
        int sum = 0;

        for(int i=0; i < data.length; i++) sum = sum + data[i];
        double average = sum / data.length;;
        return average;
    }

    @Override
    public void printgrades(int studid) {
        ObjectInputStream in = null;
        try {
            //deserialize studid.dat or studid.ser
            in = new ObjectInputStream(new FileInputStream(studid + ".dat"));
            StudentRecord b1 = (StudentRecord) in.readObject();

            System.out.print("\nStudent ID: " + studid + "\n");
            for(int j=0;j<b1.thisstudent.getScores().length;j++){
                int score = b1.thisstudent.getScores()[j];
                System.out.print("Quiz " + (j + 1) + ": ");
                System.out.print(convert2grade(score));
                System.out.print("\n");
            }
            System.out.print("Overall: "+convert2grade(average(b1.thisstudent.getScores())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        }
}

