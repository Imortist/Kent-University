

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * The test class ExhibitionTest.
 *
 * @author  David J. Barnes
 * @version 2019.10.15
 */
public class ExhibitionTest1
{
    // The exhibition used for all the tests.
    private Exhibition exhibition1;
    private static final int defaultExhibitionSize = 100;
    // Catch the Exhibition class's output.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private String report;

    /**
     * Default constructor for test class ExhibitionTest
     */
    public ExhibitionTest1()
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
        exhibition1 = new Exhibition("Tracey Emin", defaultExhibitionSize);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        report = null;
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
    }

    @Test
    /**
     * Test that the location is set correctly.
     */
    public void testGetName()
    {
        assertEquals("Tracey Emin", exhibition1.getName());
        checkOutput("");
    }

    @Test
    /**
     * Test that the capacity is set correctly.
     */
    public void testGetCapacity()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
    }

    @Test
    /**
     * Test that the exhibition is initially empty.
     */
    public void testInitialOccupation()
    {
        assertEquals(0, exhibition1.getOccupancy());
    }

    @Test
    /**
     * Test with a single visitor.
     */
    public void testOneVisitor()
    {
        int capacity = exhibition1.getCapacity();
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        assertEquals(capacity, exhibition1.getCapacity());
    }

    @Test
    /**
     * Test completely filling the exhibition.
     */
    public void testFill()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        for(int i = 1; i <= defaultExhibitionSize; i++) {
            exhibition1.attend();
            assertEquals(i, exhibition1.getOccupancy());
        }
    }

    @Test
    /**
     * Test trying to have too many visitors.
     */
    public void testOverFill()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        for(int i = 1; i <= defaultExhibitionSize; i++) {
            exhibition1.attend();
            assertEquals(i, exhibition1.getOccupancy());
        }
        exhibition1.attend();
        // This should generate an error message, which is fine.
        checkOutput("The exhibition is full.");
        assertEquals(defaultExhibitionSize, exhibition1.getOccupancy());
    }

    @Test
    public void testLeave()
    {
        int capacity = exhibition1.getCapacity();
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        exhibition1.leave();
        assertEquals(0, exhibition1.getOccupancy());
        assertEquals(capacity, exhibition1.getCapacity());
    }

    @Test
    public void testFillAndClear()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        for(int i = 1; i <= defaultExhibitionSize; i++) {
            exhibition1.attend();
            assertEquals(i, exhibition1.getOccupancy());
        }
        for(int i = defaultExhibitionSize - 1; i >= 0; i--) {
            exhibition1.leave();
            assertEquals(i, exhibition1.getOccupancy());
        }
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
    }

    @Test
    /**
     * Test trying to leave an empty exhibition.
     */
    public void testLeaveEmpty1()
    {
        exhibition1.leave();
        // This should generate an error message, which is fine.
        checkOutput("The exhibition is empty.");
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        exhibition1.leave();
        assertEquals(0, exhibition1.getOccupancy());
   }

    @Test
    /**
     * Test trying to leave an empty exhibition.
     */
    public void testLeaveEmpty2()
    {
       assertEquals(0, exhibition1.getOccupancy());
       exhibition1.attend();
       exhibition1.leave();
       assertEquals(0, exhibition1.getOccupancy());
       exhibition1.leave();
       // This should generate an error message, which is fine.
       checkOutput("The exhibition is empty.");
       assertEquals(0, exhibition1.getOccupancy());
    }
    
    @Test
    /**
     * Test trying to change the capacity of an
     * empty exhibition.
     */
    public void testChangeCapacityOk1()
    {
        int change = 5;
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        exhibition1.changeCapacity(change);
        assertEquals(defaultExhibitionSize + change, exhibition1.getCapacity());
        exhibition1.changeCapacity(-2 * change);
        assertEquals(defaultExhibitionSize - change, exhibition1.getCapacity());
    }

    @Test
    /**
     * Test trying to change the capacity of an
     * non-empty exhibition.
     */
    public void testChangeCapacityOk2()
    {
        int change = 5;
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        exhibition1.changeCapacity(change);
        assertEquals(defaultExhibitionSize + change, exhibition1.getCapacity());
        exhibition1.changeCapacity(-2 * change);
        assertEquals(defaultExhibitionSize - change, exhibition1.getCapacity());
        assertEquals(1, exhibition1.getOccupancy());
    }

    @Test
    /**
     * Test trying to change the capacity of an
     * non-empty exhibition to leave just enough
     * room for the current number of visitors.
     */
    public void testChangeCapacityOk3()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        exhibition1.changeCapacity(-(defaultExhibitionSize - 1));
        assertEquals(1, exhibition1.getCapacity());
        assertEquals(1, exhibition1.getOccupancy());
    }

    @Test
    /**
     * Test trying to change the capacity of an
     * non-empty exhibition to leave just enough
     * room for the current number of visitors.
     */
    public void testChangeCapacityOk4()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        exhibition1.leave();
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        exhibition1.changeCapacity(-(defaultExhibitionSize - 1));
        assertEquals(1, exhibition1.getCapacity());
        assertEquals(1, exhibition1.getOccupancy());
    }
    


    @Test
    /**
     * Test trying to change the capacity of an
     * empty exhibition.
     */
    public void testChangeCapacityOk5()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.changeCapacity(-(defaultExhibitionSize + 1));
        // Output should be a message saying the exhibition is closed.
        checkOutput("The exhibition is now closed.");
        assertEquals(0, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
    }
    
    @Test
    /**
     * Test trying to change the capacity so
     * that there isn't room for the current
     * number of visitors.
     */
    public void testChangeCapacityFail1()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        exhibition1.changeCapacity(-defaultExhibitionSize);
        // This should generate an error message.
        checkOutput("Unable to change the capacity.");
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(1, exhibition1.getOccupancy());
    }
    
    @Test
    /**
     * Test trying to change the capacity so
     * that there isn't room for the current
     * number of visitors.
     */
    public void testChangeCapacityFail2()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        exhibition1.changeCapacity(-2 * defaultExhibitionSize);
        // This should generate an error message.
        checkOutput("Unable to change the capacity.");
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(1, exhibition1.getOccupancy());
    }
    
    @Test
    /**
     * Test the isSpace query method.
     * Test for false returned with a zero parameter.
     */
    public void testIsSpace1()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        assertEquals(false, exhibition1.isSpace(0));
        checkOutput("");
        assertEquals(0, exhibition1.getOccupancy());
    }
    
    @Test
    /**
     * Test the isSpace query method.
     * Test for false returned with a negative parameter.
     */
    public void testIsSpace2()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        assertEquals(false, exhibition1.isSpace(-1));
        checkOutput("");
        assertEquals(0, exhibition1.getOccupancy());
    }
           
    @Test
    /**
     * Test the isSpace query method.
     * Test for true returned when there is space.
     */
    public void testIsSpace3()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        assertEquals(true, exhibition1.isSpace(1));
        checkOutput("");
        assertEquals(0, exhibition1.getOccupancy());
    }
           
    @Test
    /**
     * Test the isSpace query method.
     * Test for true returned when there is space.
     */
    public void testIsSpace4()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        assertEquals(true, exhibition1.isSpace(defaultExhibitionSize));
        checkOutput("");
        assertEquals(0, exhibition1.getOccupancy());
    }
           
    @Test
    /**
     * Test the isSpace query method.
     * Test for true returned when there is space.
     */
    public void testIsSpace5()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        assertEquals(true, exhibition1.isSpace(defaultExhibitionSize - 1));
        checkOutput("");
        assertEquals(1, exhibition1.getOccupancy());
    }
           
    @Test
    /**
     * Test the isSpace query method.
     * Test for false returned when there isn't space.
     */
    public void testIsSpace6()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        exhibition1.attend();
        assertEquals(1, exhibition1.getOccupancy());
        assertEquals(false, exhibition1.isSpace(defaultExhibitionSize));
        checkOutput("");
        assertEquals(1, exhibition1.getOccupancy());
    }
           
    @Test
    /**
     * Test the isSpace query method.
     * Test for true returned when there is space.
     */
    public void testIsSpace7()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        for(int i = 0; i < defaultExhibitionSize-1; i++) {
            exhibition1.attend();
        }
        assertEquals(defaultExhibitionSize-1, exhibition1.getOccupancy());
        assertEquals(true, exhibition1.isSpace(1));
        checkOutput("");
        assertEquals(defaultExhibitionSize-1, exhibition1.getOccupancy());
    }
           
    @Test
    /**
     * Test the isSpace query method.
     * Test for false returned when there isn't space.
     */
    public void testIsSpace8()
    {
        assertEquals(defaultExhibitionSize, exhibition1.getCapacity());
        assertEquals(0, exhibition1.getOccupancy());
        for(int i = 0; i < defaultExhibitionSize; i++) {
            exhibition1.attend();
        }
        assertEquals(defaultExhibitionSize, exhibition1.getOccupancy());
        assertEquals(false, exhibition1.isSpace(1));
        checkOutput("");
        assertEquals(defaultExhibitionSize, exhibition1.getOccupancy());
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
            builder.append("Expected output should be something like: ")
            .append(expectedOutput).append("\n")
            .append("    Actual output was: ")
            .append(actualOutput);
            report = builder.toString();
        }
        else {
            report = null;
        }            
    }
}





