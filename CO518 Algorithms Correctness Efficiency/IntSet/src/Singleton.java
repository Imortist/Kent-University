import java.util.ArrayList;
import java.util.Arrays;

public class Singleton implements IntSet {
    private final int i;
    private static final ArrayList<Singleton> SINGLETONS = new ArrayList<>(
            Arrays.asList(
                    new Singleton(0),
                    new Singleton(1),
                    new Singleton(2),
                    new Singleton(3),
                    new Singleton(4),
                    new Singleton(5),
                    new Singleton(6),
                    new Singleton(7)));

    private Singleton(int i){
        this.i = i;
    }

    public static Singleton create(int i){
        if(i>=0 && i <=7) { //optimization for search of condition
            for (Singleton s : SINGLETONS) {
                if (s.getValue() == i) return s;
            }
        }
        return new Singleton(i); //if we dont have premade singletons, create a new one
    }

    //getter
    public int getValue() {
        return i;
    }

    //inherited methods

    @Override
    public IntSet add(int x) {
        return new TreeSet(x,i); //always creates a new TreeSet with both values (treeset Constructor manages the value halving and assignment)
    }

    @Override
    public boolean contains(int x) {
        return x == i;
    }

    @Override
    public IntSet union(IntSet other) {
        if (other instanceof EmptySet) return this; //if emptySet
        if (other instanceof Singleton) { //if Singleton
            if(((Singleton) other).getValue() == i) return this;
            return add(((Singleton) other).getValue());
        }
        if (other.contains(i)); //if TreeSet contains value already
        return other.union(this); //if TreeSet doesn't have this value
    }

    @Override
    public String makeString() {
        return "{"+ i + "}";
    }
}
