import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class Workshop1Test {

    int[] ints = {1,2,3,4,5,6};
    ArrayList<Integer> ints2 = new ArrayList<>();


    @Before
    public void setUp(){
        for(int i : ints){
            ints2.add(i);
        }
    }

    @Test
    public void first() {
        assertEquals(6, Workshop1.first(ints));
    }

    @Test
    public void third(){
        assertEquals(4, Workshop1.third(ints2));
    }

    @Test
    public void babylonianZero(){
        assertEquals(0, Workshop1.babylonianInt(0));
    }

    @Test
    public void babylonianOne(){
        assertEquals(1, Workshop1.babylonianInt(1));
    }

    @Test
    public void babylonianTwo(){
        assertEquals(1, Workshop1.babylonianInt(2));
    }

    @Test
    public void babylonianThree(){
        assertEquals(1, Workshop1.babylonianInt(3));
    }

    @Test
    public void babylonianFour(){
        assertEquals(2, Workshop1.babylonianInt(4));
    }

    @Test
    public void babylonianFive(){
        assertEquals(2, Workshop1.babylonianInt(5));
    }

    @Test
    public void babylonianSix(){
        assertEquals(2, Workshop1.babylonianInt(6));
    }

    @Test
    public void babylonianSeven(){
        assertEquals(2, Workshop1.babylonianInt(7));
    }

    @Test
    public void babylonianEight(){
        assertEquals(2, Workshop1.babylonianInt(8));
    }

    @Test
    public void babylonianNine(){
        assertEquals(3, Workshop1.babylonianInt(9));
    }

    @Test
    public void fBabylonianZero(){
        assertEquals(0, Workshop1.babylonianFloat(0));
    }

    @Test
    public void fBabylonianOne(){
        assertEquals(1, Workshop1.babylonianFloat(1));
    }

    @Test
    public void fBabylonianTwo(){
        assertEquals(1, Workshop1.babylonianFloat(2));
    }

    @Test
    public void fBabylonianThree(){
        assertEquals(1, Workshop1.babylonianFloat(3));
    }

    @Test
    public void fBabylonianFour(){
        assertEquals(2, Workshop1.babylonianFloat(4));
    }

    @Test
    public void fBabylonianFive(){
        assertEquals(2, Workshop1.babylonianFloat(5));
    }

    @Test
    public void fBabylonianSix(){
        assertEquals(2, Workshop1.babylonianFloat(6));
    }

    @Test
    public void fBabylonianSeven(){
        assertEquals(2, Workshop1.babylonianFloat(7));
    }

    @Test
    public void fBabylonianEight(){
        assertEquals(2, Workshop1.babylonianFloat(8));
    }

    @Test
    public void fBabylonianNine(){
        assertEquals(3, Workshop1.babylonianFloat(9));
    }

    @Test
    public void fBabylonian187(){
        assertEquals(13, Workshop1.babylonianFloat(187));
    }

    @Test
    public void fbabylonian187point8(){
        assertEquals(13, Workshop1.babylonianFloat(187.8f));
    }

    private boolean compareFloats(float a, float b, float precision){
        return Math.abs(a - b) < precision;
    }

    @Test
    public void babylonianFloatZeroZero() {
        assertEquals("0.1", Workshop1.babylonianFloat(0, 0));
    }

    @Test
    public void babylonianFloatRR(){
        assertEquals("9.848857", Workshop1.babylonianFloat(97,0.00001f));
    }
}