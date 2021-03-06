//Your Name          :Bo Ying, Su
//Class and Section  :CIS35A
//Assignment Number  :7


import adapter.CHEM1A;
import adapter.CIS35a;
import adapter.Gradeable;
import adapter.Instructable;

public class Driver {

    public static void main(String[] args) {
        Gradeable gr = new CIS35a("15students.txt");
        gr.printstats(4532);
        gr.printscores(4532); //test with a few function calls.

        
        Instructable in = new CHEM1A("40students.txt");
        //You can also make GradeBook later
        //in.MakeGradeBook("40students.txt");
        in.printall();
        in.printstats(1947);
        in.printgrades(1947);

    }


}

