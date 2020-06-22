import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class exponentTest {
    exponent e = new exponent();

    @Test
    public void testPow0(){
        assertEquals(BigInteger.valueOf(1), e.exp(BigInteger.valueOf(12),BigInteger.valueOf(0)));
    }

    @Test
    public void testPow1(){
        assertEquals(BigInteger.valueOf(5), e.exp(BigInteger.valueOf(5),BigInteger.valueOf(1)));
    }

    @Test
    public void testPow2(){
        assertEquals(BigInteger.valueOf(4), e.exp(BigInteger.valueOf(2),BigInteger.valueOf(2)));
    }

    @Test
    public void testPow3(){
        assertEquals(BigInteger.valueOf(8), e.exp(BigInteger.valueOf(2),BigInteger.valueOf(3)));
    }

    @Test
    public void testPow4(){
        assertEquals(BigInteger.valueOf(16), e.exp(BigInteger.valueOf(2),BigInteger.valueOf(4)));
    }

    @Test
    public void testPow12(){
        assertEquals(BigInteger.valueOf(4096), e.exp(BigInteger.valueOf(2),BigInteger.valueOf(12)));
    }

    @Test
    public void testPow16(){
        assertEquals(BigInteger.valueOf(65536), e.exp(BigInteger.valueOf(2),BigInteger.valueOf(16)));
    }


    @Test
    public void testpow30(){
        assertEquals(BigInteger.valueOf(5076944270305263616L), e.exp(BigInteger.valueOf(10), BigInteger.valueOf(30)));
    }

    @Test
    public void testPowLOADS(){
        assertEquals(BigInteger.ONE, e.exp(BigInteger.valueOf(2), BigInteger.valueOf(9223372036854775800L)));
    }
}