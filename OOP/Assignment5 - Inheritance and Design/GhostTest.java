import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * The test class GhostTest.
 *
 * @author  Olaf Chitil
 * @version 4/2/2020
 */
public class GhostTest
{
    Room r1, r2;
    Ghost g3,g,g2,g4,g5;
    
    /**
     * Default constructor for test class GhostTest
     */
    public GhostTest()
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
        r1 = new Room("room1");
        r2 = new Room("room2");
        g3 = new Ghost(r1, "huibuh");
        g = new Ghost(r1, "huibuh");
        g2 = new Ghost(r1,"iuibuh");
        g4 = new Ghost(r1,"David");
        g5 = new Ghost(r1, "Jason");

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
     * Check toString.
     */
    @Test
    public void toString1()
    {
        assertEquals("huibuh", g.toString());
    }
    
    /**
     * Check that after goRandom a ghost is still in some room.
     */
    @Test
    public void goRandom1()
    {
        List<Room> rooms = Arrays.asList(r1,r2);
        g.goRandom(rooms);
        assertTrue(rooms.contains(g.getLocation()));
    }

    @Test
    public void compareToNegative(){
        assertEquals(-1, g.compareTo(g2));
    }

    @Test
    public void compareToPositive(){
        assertEquals(1, g2.compareTo(g));
    }

    @Test
    public void compareToEqual(){
        assertEquals(0,g.compareTo(g3));
    }
    @Test
    public void compareAtWork(){
        ArrayList<Character> a = new ArrayList<>();
        a.add(g5);
        a.add(g4);
        Arrays.sort(a.toArray());
        for(Character c : a) System.out.println(c.toString());
    }
}