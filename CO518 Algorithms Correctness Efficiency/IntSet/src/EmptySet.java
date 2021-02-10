public class EmptySet implements IntSet{

    private static final EmptySet EMPTYSET = new EmptySet();

    //Default constructor
    private EmptySet(){

    }

    //reference to the instance of EmptySet aka smart constructor
    public static EmptySet create(){
        return EMPTYSET;
    }

    //Inherited methods:

    //only returns new singleton with the value x
    @Override
    public IntSet add(int x) {
        return Singleton.create(x);
    }

    //empty set never contains anything so simply return false
    @Override
    public boolean contains(int x) {
        return false;
    }

    //epmty sets get replaced by non-empty sets, return other set
    @Override
    public IntSet union(IntSet other) {
        return other;
    }

    @Override
    public String makeString() {
        return "{}";
    }
}
