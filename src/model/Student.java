//Your Name          :Bo Ying, Su
//Class and Section  :CIS35A
//Assignment Number  :6
package model;

import java.io.Serializable;

public class Student implements Serializable {
    //Declare Student's Data
    private int SID;
    private int scores[] = new int[5];

    //Getter for Student ID
    public int getSID() {
        return this.SID;
    }

    //Setter for Student ID
    public void setSID(int id) {
        this.SID = id;
    }

    //Getter for Student Score
    public int[] getScores() {
        return this.scores;
    }

    //Setter for Student Score
    public void setScores(int[] s) {
        this.scores = s;
    }

    //Print SID
    public void printSID() {
        System.out.print("Student ID: " + getSID() + "\n");
    }


    //Print Student's Scores
    public void printScore() {
        System.out.print("\n\nStud Q1 Q2 Q3 Q4 Q5\n" + getSID());
        for (int i = 0; i < getScores().length; i++) {
            System.out.print(" " + getScores()[i]);
        }
        System.out.print("\n");
    }
}
