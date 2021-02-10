/*
 * Interfaces are implicitly abstract, so there is no need for explicit declaration of abstract
 * class name. Methods created in an interface are implicitly abstract and public.
 *
 * author: ab2323 Aleksej Bratkovskij
 */

public interface IntSet {

     IntSet add(int x);
     boolean contains(int x);
     IntSet union(IntSet other);
     String makeString();
}
