import org.junit.Test;

import static org.junit.Assert.*;

public class EmptySetTest {

    private final IntSet es = EmptySet.create();

    @Test
    public void containsReturnsFalse(){
        assertFalse(es.contains(7));
    }

    @Test
    public void referencesTheSameObject(){
        assertSame(es, EmptySet.create());
    }

    @Test
    public void addEmptySetReturnsSingletonWithValue(){
        Singleton s =(Singleton) es.add(21);
        assertTrue(es.add(21) instanceof Singleton);
        assertEquals(es.add(5), Singleton.create(5));
        assertEquals(s.getValue(), 21);
    }

    @Test
    public void unionReturnsDifferentObject(){
        assertNotSame(es, es.union(Singleton.create(5)));
    }

    @Test
    public void makeStringReturnsCurlyBraces(){
        assertEquals("{}", es.makeString());
    }

}
