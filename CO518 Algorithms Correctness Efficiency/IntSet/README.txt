Int Sets (Assignment 1) implementation spec:

	> The empty set {}.

	> Singleton sets {n}.

	> Tree sets. These are sets containing at least two numbers. All tree sets are a tree
	  of two branches which are both integer sets. However all even numbers go into the left
	  branch, all odd numbers go into the right branch. Insides those branches we forget their
	  last bit, i.e. divide all numbers by 2.

All set objects are immutable.

Building a set: initially create an empty set object, and then 'add' method adds elements - but instead
of modifying an existing set each call returns the new set as a resutl. 

TASKS:

 1. Define the 3 classes and the interface as indicated above.

	a. The class of empty set use a smart constructor which always returns the same object

	b. The class of singleton sets should also use a smart constructor, singleton(n).
	   We require that if 0 <= n <= 7 then two different calls of singleton(n) should 
	   return identical objects.

	c. For the class of three sets there are no special requirement of object sharing. Note
	   though that the class design means all numbers change when you go down the tree.
	   For example, when checking whether the number 35 is contained in a tree set you go right
	   branch (because 35 is odd) and look there for 17 (because this is 35/2).

2. The IntSet interface (and thus all its implementing classes) should provide the following methods:

	a. IntSet add(int x), which adds the number x to the set, i.e. returning a set object that contains
	   all numbers of this set, plus the number x, but no others.
	
	b. boolean contains(int x). This should return true if x is in this set.

	c. IntSet union(IntSet other) This should compute to union of this IntSet with the other IntSet,
	   returning an IntSet that represents this union.

	d. String toString(). This inherited from class objet, should be replaced with a bespoke method
	   that puts elements next to one another, separated by commas, and enclosed in curly brackets.