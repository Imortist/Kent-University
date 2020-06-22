import org.junit.Test;

import static org.junit.Assert.*;

public class NpnTest {

    Npn n = new Npn();

    @Test
    public void oneInputTest(){
        assertEquals(3,n.calculate("3"));
    }

    @Test
    public void simpleSumTest(){
        assertEquals(3, n.calculate("+ 1 2"));
    }

    @Test
    public void simpleMultiplicationTest(){
        assertEquals(0,n.calculate("x 2 0"));
    }

    @Test
    public void simpleSubtractionTest(){
        assertEquals(1, n.calculate("- 3 2"));
    }

    @Test
    public void twoOperationsTest1(){
        assertEquals(8,n.calculate("+ x 2 3 2"));
    }

    @Test
    public void complicatedOp1Test(){
        assertEquals(20,n.calculate("x + 2 3 + 2 2"));
    }

    @Test
    public void complicatedOp2Test(){
        assertEquals(10, n.calculate("+ x 2 3 4"));
    }
    

}
