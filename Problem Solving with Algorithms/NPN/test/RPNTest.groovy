import org.junit.Test

import static org.junit.Assert.*

class RPNTest {

    RPN rpn = new RPN()

    @Test
void test1(){
        assertEquals(3, rpn.calculate("3"))
}

    @Test
    void test2(){
        assertEquals(0, rpn.calculate("2 0 x"))
    }

    @Test
    void test3(){
        assertEquals(20, rpn.calculate("2 3 + 2 2 + x"))
    }

    @Test
    void test4(){
        assertEquals(14, rpn.calculate("2 3 4 + x"));
    }
}