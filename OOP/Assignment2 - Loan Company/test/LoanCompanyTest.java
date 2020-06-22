

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Objects;

/**
 * The test class LoanCompanyTest.
 *
 * @author  Aleksej Bratkovskij (ab2323)
 * @version 22/10/19 Updated test showOutstandingPrintsRecords.
 * @version 04/11/19 Added error messages, reworked some tests to work without custom method.
 *
 * THESE TESTS WILL WORK ONLY WITH ALL METHODS COMPLETED FROM THE ASSIGNMENT
 */
public class LoanCompanyTest
{
    private StudentLoan studentL1;
    private StudentLoan studentL2;
    private StudentLoan studentL3;
    private LoanCompany loanCompany;
    private ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errorContent = new ByteArrayOutputStream();
    private PrintStream originalError = System.err;
    private PrintStream originalOutput = System.out;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        studentL1 = new StudentLoan("ABC123", 5000);
        studentL2 = new StudentLoan("Z9Z88Z", 0);
        studentL3 = new StudentLoan("XYZ123", 200);
        loanCompany = new LoanCompany();
        loanCompany.addLoan(studentL1);
        loanCompany.addLoan(studentL2);
        loanCompany.addLoan(studentL3);

        System.setOut(new PrintStream(outputContent));
        System.setErr(new PrintStream(errorContent));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        studentL1 = null;
        studentL2 = null;
        studentL3 = null;

        System.setErr(originalError);
        System.setOut(originalOutput);
    }


    @Test
    public void addLoanTest1(){
        int size = loanCompany.getNumberOfLoans();
        StudentLoan stub = new StudentLoan("CUSTOM333",500);
        loanCompany.addLoan(stub);
        assertTrue("addLoan should add new StudentLoan object to the list",size < loanCompany.getNumberOfLoans());
    }

    @Test
    public void getNumberOfLoansTest1(){
        assertEquals("getNumberOfLoans should return number of studentLoan objects",
                3, loanCompany.getNumberOfLoans());
    }

    @Test
    public void getNumberOfLoansTest2(){
        loanCompany.getNumberOfLoans();
        String output = outputContent.toString();
        assertEquals("getNumberOfLoans should NOT print number of loans",
                output, "");
    }

    @Test
    public void repayTest1(){
        assertTrue("Repay should return TRUE if index is within collection bounds",
                loanCompany.repay(0,300));
    }

    @Test
    public void repayTest2(){
        assertFalse("Repay should return FALSE if index is outside collection bounds",
                loanCompany.repay(4,30000));
    }

    @Test
    public void repayTest3(){
        loanCompany.repay(0,400);
        assertEquals("repay should perform payOff method on StudentLoan that was found",
                4600,loanCompany.find("ABC123").getAmount());
    }

    @Test
    public void removeLoanTest1(){
        loanCompany.removeLoan(0);
        assertEquals("removeLoan should remove StudentLoan object from collection",
                2, loanCompany.getNumberOfLoans());
    }

    @Test
    public void removeLoanTest2(){
        assertEquals("removeLoan should return a StudentLoan object that was removed",
                studentL1,loanCompany.removeLoan(0));
    }

    @Test
    public void removeLoanTest3(){
        assertNull("removeLoan should return null if index is out of collection bounds",
                loanCompany.removeLoan(5));
    }

    @Test
    public void removeLoanTest4(){
        loanCompany.removeLoan(5);
        String output = outputContent.toString();
        assertEquals("removeLoan should NOT print any messages",
                "", output);
    }

    @Test
    public void listTest1(){
        loanCompany.list();
        String output = outputContent.toString();
        assertEquals("list should print only details of objects in the list use getDetails() from StudentLoan",
                "ABC123 owes 5000\r\nZ9Z88Z owes 0\r\nXYZ123 owes 200\r\n",output);
    }

    @Test
    public void showOutstandingTest1(){
        String str = "ABC123 owes 5000\r\nXYZ123 owes 200\r\n";
        loanCompany.showOutstanding();
        String output = outputContent.toString();
        assertEquals("showOutstanding should print records that still have debt on their account",
                str, output);
    }

    @Test
    public void findTest1(){
        assertNull("find should return null if StudentLoan object was not found in the collection",
                loanCompany.find("ABC"));
    }

    @Test
    public void findTest2(){
        assertTrue("find should return StudentLoan object if that record ID exists in the collection. ID must match COMPLETLY.",
                loanCompany.find("ABC123")  instanceof StudentLoan);
    }

    @Test
    public void removeClearedLoansTest1(){
        assertTrue("removeClearedLoans should return LINKED LIST",
                loanCompany.removeClearedLoans() instanceof LinkedList);
    }

    @Test
    public void removeClearedLoansTest2(){
        assertEquals("removeClearedLoans should return linked list that has removed StudentLoan objects",
                1, loanCompany.removeClearedLoans().size());
    }

    @Test
    public void removeClearedLoansTest4(){
        loanCompany.removeLoan(2);
        assertEquals("removeClearedLoans should remove StudentLoan object from the collection.",
                2,loanCompany.getNumberOfLoans());
    }


}
