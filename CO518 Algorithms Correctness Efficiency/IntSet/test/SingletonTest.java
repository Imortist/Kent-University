import org.junit.Test;

import static org.junit.Assert.*;

public class SingletonTest {
    private final IntSet s = Singleton.create(1);

    @Test
    public void usesExistingSingleton(){
        assertEquals(Singleton.create(1), Singleton.create(1));
    }

    @Test
    public void createsNewSingleton(){
        assertNotSame(s, Singleton.create(9));
    }

    @Test
    public void containsComparesValueOfSingleton(){
        assertTrue(s.contains(1));
        assertFalse(s.contains(8));
    }

    @Test
    public void unionReturnsSingletonIfComparedToEmptySet(){
        IntSet es = EmptySet.create();
        assertTrue(s.union(es) instanceof Singleton);
    }

    @Test
    public void addReturnsTreeSetOfBothValues(){
        Singleton s4 = Singleton.create(9);
        assertTrue(s4.add(4) instanceof TreeSet);
        TreeSet t = (TreeSet) s4.add(4);
        assertTrue(t.getLeft() instanceof Singleton);
    }

    @Test
    public void makeStringReturnsCurlybracedInteger(){
        assertEquals(s.makeString(),"{1}");
    }

    @Test
    public void unionWithEmptySetReturnsSingleton(){
        Singleton s = Singleton.create(44);
        assertSame(s, s.union(EmptySet.create()));
    }

    @Test
    public void unionWithSingletonReturnsTreeSet(){
        Singleton s = Singleton.create(44);
        assertTrue(s.union(Singleton.create(14)) instanceof TreeSet);
    }

    @Test
    public void untionWithSameSingletonReturnsSingleton(){
        Singleton s = Singleton.create(17);
        Singleton s1 = Singleton.create(4);
        assertEquals(s.union(s), s);
    }

}