

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LoanTest.
 * Very basic tests.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LoanTest
{
    private StudentLoan studentL1;

    /**
     * Default constructor for test class LoanTest
     */
    public LoanTest()
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
        studentL1 = new StudentLoan("ABC123", 5000);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Basic accessor test.
     */
    @Test
    public void getID()
    {
        assertEquals("ABC123", studentL1.getStudentID());
    }

    /**
     * Basic accessor test.
     */
    @Test
    public void getAmount()
    {
        assertEquals(5000, studentL1.getAmount());
    }

    /**
     * Basic formatting test.
     */
    @Test
    public void getDetails()
    {
        assertEquals("ABC123 owes 5000", studentL1.getDetails());
    }

    @Test
    public void payOffAll()
    {
        assertEquals(5000, studentL1.getAmount());
        studentL1.payOff(5000);
        assertEquals(0, studentL1.getAmount());
    }

    @Test
    public void payOffTooMuch()
    {
        assertEquals(5000, studentL1.getAmount());
        studentL1.payOff(5001);
        assertEquals(5000, studentL1.getAmount());
    }

    @Test
    public void payOffNegative()
    {
        assertEquals(5000, studentL1.getAmount());
        studentL1.payOff(-1);
        assertEquals(5000, studentL1.getAmount());
    }

    @Test
    public void payOffSome()
    {
        assertEquals(5000, studentL1.getAmount());
        studentL1.payOff(1000);
        assertEquals(4000, studentL1.getAmount());
    }
}







