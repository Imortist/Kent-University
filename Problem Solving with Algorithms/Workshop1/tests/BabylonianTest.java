import org.junit.Test;

import static org.junit.Assert.*;

public class BabylonianTest {
    Babylonian b = new Babylonian();

    @Test
    public void test1(){
        assertEquals(2, b.calculate(8.99999999999999));
    }

    @Test
    public void test2(){
        assertEquals(87, b.calculate(7569.99999999999999999999));
    }

    @Test
    public void test0(){
        assertEquals(0, b.calculate(0));
        assertEquals(1, b.calculate(1));
        assertEquals(4, b.calculate(2));
    }

}