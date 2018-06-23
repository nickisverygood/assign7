//My Name          :Bo Ying, Su
//Class and Section  :CIS35A
//Assignment Number  :6
package model;

import java.io.Serializable;

public class Statistics implements Serializable

{
    final int numberofquizzes = 5;
    private int numberOfStudents;
    private int[] lowscores = new int[numberofquizzes];
    private int[] highscores = new int[numberofquizzes];
    private float[] avgscores = new float[numberofquizzes];
    private boolean[] statisticsCheckList = {false, false, false}; //status of {lowest, highest, avg} being calculated

    /**
     * find the lowest score among the students
     *
     * @param a all students
     * @return
     */
    public int[] findlow(Student[] a) {
        /* This method will find the lowest score and store it in an array names lowscores. */
        for (int lab = 0; lab < numberofquizzes; lab++) {
            int min = a[0].getScores()[lab];
            for (int i = 1; i < a.length; i++) {
                if (a[i] == null) continue;
                if (min > a[i].getScores()[lab]) {
                    min = a[i].getScores()[lab];
                }
                lowscores[lab] = min;
            }

        }
        statisticsCheckList[0] = true;
        return lowscores;
    }

    public int[] findhigh(Student[] a) {
        /* This method will find the highest score and store it in an array names highscores. */
        for (int lab = 0; lab < numberofquizzes; lab++) {
            int max = a[0].getScores()[lab];
            for (int i = 1; i < a.length; i++) {
                if (a[i] == null) continue;
                if (max < a[i].getScores()[lab]) {
                    max = a[i].getScores()[lab];
                }
                highscores[lab] = max;
            }
        }
        statisticsCheckList[1] = true;

        return highscores;

    }

    public void calculateStatistics(Student[] a) {
    	numberOfStudents = a.length;
        findlow(a);
        findhigh(a);
        findavg(a);
    }

    float[] findavg(Student[] a) {
        /* This method will find avg score for each quiz and store it in an array names avgscores. */
        for (int lab = 0; lab < numberofquizzes; lab++) {
            int count = 0;
            int total = 0;
            for (int i = 1; i < a.length; i++) {
                if (a[i] == null) continue;
                total += a[i].getScores()[lab];
                count++;
            }
            avgscores[lab] = total / count;
        }
        statisticsCheckList[2] = true;
        return avgscores;
    }

    public void print() {
        if (operatorANDtoArray(statisticsCheckList)) {
        	System.out.print("\nNumber Of Students: "+numberOfStudents);
            System.out.print("\nHigh Score ");
            for (int i = 0; i < numberofquizzes; i++) {
                System.out.print(highscores[i] + " ");
            }
            System.out.print("\nLow Score ");
            for (int i = 0; i < numberofquizzes; i++) {
                System.out.print(lowscores[i] + " ");
            }
            System.out.print("\nAverage ");
            for (int i = 0; i < numberofquizzes; i++) {
                System.out.print(avgscores[i] + " ");
            }

        } else {
            System.out.print("Sorry, there is no statistics availible now\n");
        }

    }


    private boolean operatorANDtoArray(boolean[] toapplyAND) {
        boolean toReturn = toapplyAND[0];
        if (toapplyAND.length > 1) {
            for (int i = 1; i < toapplyAND.length; i++) {
                toReturn = toReturn && toapplyAND[i];
            }
        }
        return toReturn;

    }
}