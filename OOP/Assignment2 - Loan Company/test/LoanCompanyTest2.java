import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * The test class LoanCompanyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LoanCompanyTest2
{
    private StudentLoan studentL1;
    private StudentLoan studentL2;
    private StudentLoan studentL3;
    private StudentLoan studentL4;
    private StudentLoan studentL5;
    private StudentLoan studentL6;
    private LoanCompany loanComp1;   

    // Catch any program output.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private String report;
    
    /**
     * Default constructor for test class LoanCompanyTest
     */
    public LoanCompanyTest2()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        report = null;
        
        studentL1 = new StudentLoan("ABC123", 5000);
        studentL2 = new StudentLoan("Z9Z88Z", 0);
        studentL3 = new StudentLoan("XYZ123", 200);
        studentL4 = new StudentLoan("B9Z77Z", 0);
        studentL5 = new StudentLoan("M9Z99Z", 0);
        studentL6 = new StudentLoan("XYZ123", 500);
        loanComp1 = new LoanCompany();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        System.setOut(originalOut);
        System.setErr(originalErr);
        if(report != null) {
            System.err.println(report);
            report = null;
        }
        studentL1 = null;
        studentL2 = null;
        studentL3 = null;
        loanComp1 = null;
    }
    
    /**
     * Test the initial state of the list.
     */
    @Test
    public void testEmpty()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
    }

    /**
	 * Add one loan to an empty list.
	 */
    @Test
    public void testAddOne()
    {
        // Basic test of addition.
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
    }

	/**
	 * Add several loans to the list.
	 */
    @Test
    public void testAddSeveral()
    {
        // Basic tests of the getNumberOfLoans method.
        assertEquals(0, loanComp1.getNumberOfLoans());
        
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
        
        loanComp1.addLoan(studentL2);
        assertEquals(2, loanComp1.getNumberOfLoans());
        
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
    }
    
    /**
     * Try repaying a loan that does exist.
     */
    @Test
    public void testRepayPositive1()
    {
        loanComp1.addLoan(studentL1);
        int loanAmount = studentL1.getAmount();
        int payment = 1;
        assertEquals(1, loanComp1.getNumberOfLoans());
        assertEquals(true, loanComp1.repay(0, payment));
        assertEquals(loanAmount - payment, studentL1.getAmount());
    }
    
    /**
     * Try repaying the full amount of a loan that does exist.
     */
    @Test
    public void testRepayPositive2()
    {
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
        assertEquals(true, loanComp1.repay(0, studentL1.getAmount()));
        assertEquals(0, studentL1.getAmount());
    }
    
    /**
     * Try repaying a loan that does exist but with an
     * invalid amount.
     */
    @Test
    public void testRepayPositive3()
    {
        loanComp1.addLoan(studentL1);
        int loanAmount = studentL1.getAmount();
        int payment = -1;
        assertEquals(1, loanComp1.getNumberOfLoans());
        assertEquals(true, loanComp1.repay(0, payment));
        assertEquals(loanAmount, studentL1.getAmount());
        checkOutput("Negative payments are not allowed:-1");
    }
    
    /**
     * Try repaying a loan that does exist but with an
     * invalid amount.
     * The repay method should still return true.
     */
    @Test
    public void testRepayPositive4()
    {
        String lineSeparator = System.lineSeparator();
        loanComp1.addLoan(studentL1);
        int loanAmount = studentL1.getAmount();
        int payment = loanAmount + 1;
        assertEquals(1, loanComp1.getNumberOfLoans());
        assertEquals(true, loanComp1.repay(0, payment));
        assertEquals(loanAmount, studentL1.getAmount());
        checkOutput("The payment is too high: 5001" +
                    lineSeparator + "ABC123 owes 5000");
    }
    
    /**
     * Try repaying a loan that does not exist.
     * The list is empty.
     */
    @Test
    public void testRepayNegative1()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        assertEquals(false, loanComp1.repay(0, 1));
    }
    
    /**
     * Try repaying a loan that does not exist.
     * The list is empty.
     */
    @Test
    public void testRepayNegative2()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        assertEquals(false, loanComp1.repay(1, 1));
    }
    
    /**
     * Try repaying a loan that does not exist.
     * The list is not empty but the index is not valid.
     */
    @Test
    public void testRepayNegative3()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        assertEquals(false, loanComp1.repay(3, 100));
    }
    
    /**
     * Try repaying a loan that does not exist.
     * The list is not empty but the index is not valid.
     * The repayment amount is in the valid index range
     * so this is a test of the parameter ordering.
     */
    @Test
    public void testRepayNegative4()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        assertEquals(false, loanComp1.repay(3, 2));
    }
    
    /**
     * Try removing a loan that does not exist.
     * The list is empty.
     */
    @Test
    public void testRemoveNegative1()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        assertEquals(null, loanComp1.removeLoan(0));
    }
    
    /**
     * Try removing a loan that does not exist.
     * The list is not empty.
     */
    @Test
    public void testRemoveNegative2()
    {
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
        assertEquals(null, loanComp1.removeLoan(1));
    }
    
    /**
     * Try removing a loan with an illegal index.
     * The list is not empty.
     */
    @Test
    public void testRemoveNegative3()
    {
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        assertEquals(null, loanComp1.removeLoan(-1));
    }
    
    /**
     * Try removing a loan that exists.
     * The list is not empty.
     */
    @Test
    public void testRemovePositive1()
    {
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
        assertEquals(studentL1, loanComp1.removeLoan(0));
    }

    /**
     * Try removing a loan that exists.
     * The list is not empty.
     */
    @Test
    public void testRemovePositive2()
    {
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        assertEquals(studentL3, loanComp1.removeLoan(2));
    }
    
    /**
     * Test printing an empty list.
     */
    @Test
    public void testList1()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        loanComp1.list();
        checkOutput("");
        assertEquals(null, report);
    }
    
    /**
     * Test printing a non-empty list.
     */
    @Test
    public void testList2()
    {
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
        loanComp1.list();
        checkOutput("ABC123 owes 5000");
        assertEquals(null, report);
    }
    
    /**
     * Test printing a non-empty list.
     */
    @Test
    public void testList3()
    {
        String lineSeparator = System.lineSeparator();
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        loanComp1.list();
        checkOutput("ABC123 owes 5000" +
                    lineSeparator +
                    "Z9Z88Z owes 0" +
                    lineSeparator + 
                    "XYZ123 owes 200");
        assertEquals(null, report);
    }
    
    /**
     * Test printing an empty list.
     */
    @Test
    public void testOutstanding1()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        loanComp1.showOutstanding();
        checkOutput("");
        assertEquals(null, report);
    }
    
    /**
     * Test printing a non-empty list.
     */
    @Test
    public void testOutstanding2()
    {
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
        loanComp1.showOutstanding();
        checkOutput("ABC123 owes 5000");
        assertEquals(null, report);
    }
    
    /**
     * Test printing a non-empty list.
     */
    @Test
    public void testOutstanding3()
    {
        String lineSeparator = System.lineSeparator();
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        loanComp1.showOutstanding();
        checkOutput("ABC123 owes 5000" + 
                    lineSeparator + 
                    "XYZ123 owes 200");
        assertEquals(null, report);
    }
    
    /**
     * Test removing cleared loans when there are none.
     */
    @Test
    public void testCleared1()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
        assertTrue(cleared instanceof LinkedList);
        assertTrue(cleared != null);
        assertEquals(0, cleared.size());
        assertEquals(0, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test removing cleared loans when there are none.
     */
    @Test
    public void testCleared2()
    {
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
        assertTrue(cleared instanceof LinkedList);
        assertTrue(cleared != null);
        assertEquals(0, cleared.size());
        assertEquals(1, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test removing cleared loans when there are none.
     */
    @Test
    public void testCleared3()
    {
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL3);
        assertEquals(2, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
	    //noinspection ConstantConditions
	    assertTrue(cleared instanceof LinkedList);
        assertNotNull(cleared);
        assertEquals(0, cleared.size());
        assertEquals(2, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test removing cleared loans when there is one at the beginning.
     */
    @Test
    public void testCleared4()
    {
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
	    //noinspection ConstantConditions
	    assertTrue(cleared instanceof LinkedList);
        assertNotNull(cleared);
        assertEquals(1, cleared.size());
        assertEquals(studentL2, cleared.get(0));
        assertEquals(2, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test removing cleared loans when there is one in the middle.
     */
    @Test
    public void testCleared5()
    {
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
	    //noinspection ConstantConditions
	    assertTrue(cleared instanceof LinkedList);
        assertNotNull(cleared);
        assertEquals(1, cleared.size());
        assertEquals(studentL2, cleared.get(0));
        assertEquals(2, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test removing cleared loans when there is one at the end.
     */
    @Test
    public void testCleared6()
    {
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL3);
        loanComp1.addLoan(studentL2);
        assertEquals(3, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
	    //noinspection ConstantConditions
	    assertTrue(cleared instanceof LinkedList);
        assertNotNull(cleared);
        assertEquals(1, cleared.size());
        assertEquals(studentL2, cleared.get(0));
        assertEquals(2, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test removing cleared loans when there are two together.
     */
    @Test
    public void testCleared7()
    {
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(4, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
	    //noinspection ConstantConditions
	    assertTrue(cleared instanceof LinkedList);
        assertNotNull(cleared);
        assertEquals(2, cleared.size());
        assertEquals(studentL2, cleared.get(0));
        assertEquals(studentL2, cleared.get(1));
        assertEquals(2, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test removing cleared loans when they
     * are all cleared.
     */
    @Test
    public void testCleared8()
    {
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL4);
        loanComp1.addLoan(studentL5);
        assertEquals(3, loanComp1.getNumberOfLoans());
        List<StudentLoan> cleared = loanComp1.removeClearedLoans();
	    //noinspection ConstantConditions
	    assertTrue(cleared instanceof LinkedList);
        assertNotNull(cleared);
        assertEquals(3, cleared.size());
        assertTrue(cleared.contains(studentL2));
        assertTrue(cleared.contains(studentL4));
        assertTrue(cleared.contains(studentL5));
        assertEquals(0, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test the find method when there is no match.
     * The list is empty.
     */
    @Test
    public void testFindNegative1()
    {
        assertEquals(0, loanComp1.getNumberOfLoans());
	    assertNull(loanComp1.find("ABC123"));
        assertEquals(0, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test the find method when there is no match.
     * The list is not empty.
     */
    @Test
    public void testFindNegative2()
    {
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(2, loanComp1.getNumberOfLoans());
	    assertNull(loanComp1.find("ABC123"));
        assertEquals(2, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test the find method when there is no match.
     * The list is not empty.
     */
    @Test
    public void testFindNegative3()
    {
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL4);
        loanComp1.addLoan(studentL5);
        assertEquals(3, loanComp1.getNumberOfLoans());
        assertNull(loanComp1.find("Z"));
        assertEquals(3, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test the find method when there is a match.
     * The list is not empty.
     */
    @Test
    public void testFindPositive1()
    {
        loanComp1.addLoan(studentL1);
        assertEquals(1, loanComp1.getNumberOfLoans());        
        assertEquals(studentL1, loanComp1.find(studentL1.getStudentID()));
        assertEquals(1, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test the find method when there is a match.
     * The list is not empty.
     */
    @Test
    public void testFindPositive2()
    {
        loanComp1.addLoan(studentL1);
        loanComp1.addLoan(studentL2);
        loanComp1.addLoan(studentL3);
        assertEquals(3, loanComp1.getNumberOfLoans());        
        assertEquals(studentL1, loanComp1.find(studentL1.getStudentID()));
        assertEquals(studentL2, loanComp1.find(studentL2.getStudentID()));
        assertEquals(studentL3, loanComp1.find(studentL3.getStudentID()));
        assertEquals(3, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Test the find method when there is a match.
     * The list has two separate loans for a single ID.
     * The list is not empty.
     */
    @Test
    public void testFindPositive3()
    {
        loanComp1.addLoan(studentL3);
        loanComp1.addLoan(studentL6);
        assertEquals(2, loanComp1.getNumberOfLoans());
        assertEquals(studentL3, loanComp1.find(studentL3.getStudentID()));
        assertEquals(2, loanComp1.getNumberOfLoans());        
    }
    
    /**
     * Check whether what was output by the Exhibition object matched
     * what was expected.
     * If they don't match, set the report field.
     * @param expectedOutput What is expected.
     */
    private void checkOutput(String expectedOutput)
    {
        String actualOutput = outContent.toString().trim()
                            + errContent.toString().trim();
        if(!expectedOutput.equals(actualOutput)) {
            StringBuilder builder = new StringBuilder();
            if(expectedOutput.isEmpty()) {
                builder.append("No output expected.");
            }
            else {
                builder.append("Expected output should be something like: ")
                .append(expectedOutput);
            }
            builder.append("\n")
            .append("    Actual output was: ")
            .append(actualOutput);
            report = builder.toString();
        }
        else {
            report = null;
        }            
    }

}
