//Your Name          :Bo Ying, Su
//Class and Section  :CIS35A
//Assignment Number  :5


import adapter.CHEM1A;
import adapter.CIS35a;
import adapter.Gradeable;
import adapter.Instructable;

public class Driver {

    public static void main(String[] args) {
//        Gradeable gr = new CIS35a("15students.txt");
//        System.out.print("\n#########Print Statistics#########\n");
//        gr.printstats(1234);
//        System.out.print("\n#########Print Scores#########\n");
//        gr.printscores(4532); //test with a few function calls.

        Instructable in = new CHEM1A();
        in.MakeGradeBook("CHEM1A","40students.txt");
        
        System.out.print("\n#########Print All#########\n");
        in.printall();
        System.out.print("\n#########Print Statistics#########\n");
        in.printstats();
        System.out.print("\n#########Print Grades#########\n");
        in.printgrades();

    }


}

