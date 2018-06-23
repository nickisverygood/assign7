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
    public void MakeGradeBook(String coursename, String filename) {
        if (coursename.equals("CHEM1A")){
            sturec=new CHEM1A(filename).sturec;
        }else if (coursename.equals("CIS35a")){
            sturec=new CIS35a(filename).sturec;
        }
    }

    @Override
    public void printall() {
        for (int i=0;i<sturec.length;i++){
            sturec[i].thisstudent.printScore();
        }
    }

    @Override
    public void printstats() {
        sturec[0].thisstatistics.print();
    }

    @Override
    public void printgrades() {
        for (int i=0;i<sturec.length;i++){
            System.out.print("\nStudent ID: " + sturec[i].thisstudent.getSID() + "\n");
            for(int j=0;j<sturec[i].thisstudent.getScores().length;j++){
                int score = sturec[i].thisstudent.getScores()[j];
                System.out.print("Quiz " + (i + 1) + ": ");
                if(score>=97)System.out.print("A+");
                else if(score>=93)System.out.print("A");
                else if(score>=90)System.out.print("A-");
                else if(score>=87)System.out.print("B+");
                else if(score>=83)System.out.print("B");
                else if(score>=80)System.out.print("B-");
                else if(score>=77)System.out.print("C+");
                else if(score>=73)System.out.print("C");
                else if(score>=70)System.out.print("D+");
                else if(score>=63)System.out.print("D");
                else if(score>=60)System.out.print("D-");
                else System.out.print("F");
                System.out.print("\n");

            }

        }
    }
}

