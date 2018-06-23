package adapter;

public interface Instructable {
    public void MakeGradeBook(String filename);
    public void printall();
    public void printstats(int studid);
    public void printgrades(int studid); //assign, A, B, B+ etc. (pl. check syllabus for logic)
}
