package adapter;

public interface Instructable {
    public void MakeGradeBook(String coursename, String filename);
    public void printall();
    public void printstats();
    public void printgrades(); //assign, A, B, B+ etc. (pl. check syllabus for logic)
}
