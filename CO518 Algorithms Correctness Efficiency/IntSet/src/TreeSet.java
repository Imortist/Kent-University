public class TreeSet implements IntSet{
    private static IntSet left = EmptySet.create();
    private static IntSet right = EmptySet.create();


    public TreeSet(IntSet i) {
        int halved = ((Singleton) i).getValue() / 2;
        if (((Singleton) i).getValue() % 2 == 0) { //if the value is even
            if (left instanceof EmptySet) { // if there is nothing in TreeSet
                left = Singleton.create(halved);
            } else { //if there is something stored
                left = left.union(i);
            }
        } else { // same procedure if the value is not even
            if (right instanceof EmptySet) {
                right = Singleton.create(halved);
            } else {
                right = right.union(i);
            }
        }
    }

    //Constructor to build a simple TreeSet based of ints;
    public TreeSet(int a, int b){
        if(a%2 == 0) left = Singleton.create(a/2);
                else right = Singleton.create(a/2);
        if(b%2 == 0) left = Singleton.create(b/2);
                else right = Singleton.create(b/2);
    }

    //Original constructor
    private TreeSet(IntSet left, IntSet right){
        TreeSet.left = left;
        TreeSet.right = right;
    }

    //Getters
    public  IntSet getLeft() {
        return left;
    }

    public  IntSet getRight() {
        return right;
    }

    //inherited methods
    @Override
    public IntSet add(int x) { //returns Construction to witch it was added.
        boolean isEven = x%2 == 0;
        int val = x/2;
        if(isEven){
            //if value is even, add to the left
            return left.add(val);
        }
        return right.add(val); //if not, add to the right
    }

    @Override
    public boolean contains(int x) {
        return (left.contains(x/2)||right.contains(x/2));
    } //does a lookup of values in both directions, returns true if at least one holds the value

    @Override
    public IntSet union(IntSet other) {
        if(other instanceof EmptySet) return this; //empty set has no value for us
        if(other instanceof Singleton){
            if(this.contains(((Singleton) other).getValue()))return this; // if it's the same singleton, do nothing. We already have the value
            int val = ((Singleton) other).getValue(); // save the value, to save space further down
            this.add(val); //singleton doesnt exist in our tree, we create a new one by calling add.
            return this; //returns this tree with adjusted values (in theory)
        }
         //union 2 sides of a tree
            IntSet newLeft = left.union(((TreeSet) other).getLeft());
            IntSet newRight = right.union(((TreeSet) other).getRight());
            return new TreeSet(newLeft,newRight);
    }

    @Override
    public String makeString() {
        String insides = left.makeString() + right.makeString(); //get string representation of values
        insides = insides.substring(1,insides.length()-1).replaceAll("[{}]", ",").replaceAll(",,",","); //formatting
        return "{" + insides + "}";

        //TODO: finish toString method to print correct values
    }
}
