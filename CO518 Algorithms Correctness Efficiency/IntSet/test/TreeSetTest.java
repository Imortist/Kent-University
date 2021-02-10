import org.junit.Test;

import static org.junit.Assert.*;

public class TreeSetTest {

    //TODO: Implement @BEFORE and @AFTER for unit tests;

    @Test
    public void evenNumbersAddedToTheLeftHalved(){
        TreeSet t1 = new TreeSet(Singleton.create(6));
        assertTrue(t1.getLeft() instanceof Singleton);
        assertEquals(((Singleton) t1.getLeft()).getValue(), 3);
    }

    @Test
    public void evenNumbersAddedAndRightIsEmptySet(){
        TreeSet t2 = new TreeSet(Singleton.create(6));
        assertTrue(t2.getRight()instanceof EmptySet);
    }

    @Test
    public void oddNumbersAddedToTheRightHalved(){
        TreeSet t3 = new TreeSet(Singleton.create(11));
        assertTrue(t3.getRight() instanceof Singleton);
        assertEquals(((Singleton) t3.getRight()).getValue(), 5);
    }

    @Test
    public void oddNumbersAddedAndLeftIsEmptySet(){
        TreeSet t4 = new TreeSet(Singleton.create(11));
        assertNotNull(t4.getRight());
        assertNotNull(t4.getLeft());
        assertTrue(t4.getLeft() instanceof EmptySet);
    }

    @Test
    public void addEvenReturnsSingletonHalved(){
        TreeSet set = new TreeSet(Singleton.create(11));
        IntSet added = set.add(10);
        assertTrue(added instanceof Singleton);
        assertTrue(added.contains(5));
    }

    @Test
    public void addOddReturnsSingletonHalved(){
        TreeSet set = new TreeSet(Singleton.create(10));
        IntSet added = set.add(15);
        assertTrue(added instanceof Singleton);
        assertTrue(added.contains(7));
    }

    @Test
    public void containsReturnsTrueIfHalvedValueIsFound(){
        TreeSet t7 = new TreeSet(Singleton.create(5));
        t7.add(8);
        assertTrue(t7.contains(4));
        assertTrue(t7.contains(5));
    }

    @Test
    public void unionWithEmptySetReturnsSameTree(){
        TreeSet tree = new TreeSet(Singleton.create(0));
        assertEquals(tree.union(EmptySet.create()), tree);
    }

    @Test
    public void unionWithSameSingletonReturnsCurrentTree(){
        TreeSet tree2 = new TreeSet(Singleton.create(1));
        assertEquals(tree2.union(Singleton.create(1)), tree2);
    }

    @Test
    public void unionWithSingletonCreatesNewTree(){
        TreeSet tree3 = new TreeSet(Singleton.create(8));
        assertTrue(tree3.union(Singleton.create(4)) instanceof TreeSet);
    }

    @Test
    public void makeStringReturnsString(){
        assertEquals("{}", EmptySet.create().makeString());
        assertEquals("{1}", EmptySet.create().add(1).makeString());
        assertEquals("{10,9}", EmptySet.create().add(19).add(20).makeString());
        assertEquals("{10,9,0}", EmptySet.create().add(19).add(20).add(0).makeString());
    }

}