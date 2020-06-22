import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class to store a list of StudentLoan objects.
 * 
 * @author Aleksej Bratkovskij (ab2323)
 * @version 21/10/19 19.23 initial class completed with methods and handmade unit tests
 * @version 05/11/19 21.20 fixed find & removeClearedLoans to pass proffessors tests.
 * @version 08/11/19 16.00 replaced logic of 2x forEach loops with while loop and Iterator
 * (method removeClearedLoans)
 **/

public class LoanCompany {

	/**
	 * Since there is no need for constructor, performing inline initialisation.
	 */
	private ArrayList<StudentLoan> students = new ArrayList<>();

	/**
	 * Taking StudentLoan obj and adding it to the ArrayList of StudentLoans
	 */
	public void addLoan(StudentLoan student) {
		students.add(student);
	}

	/**
	 * Returns count of StudentLoan objects in ArrayList
	 */
	public int getNumberOfLoans() {
		return students.size();
	}

	/**
	 * Method takes two integers and returns true if object at index was found
	 * and performs payOff() method call on StudenLoan obj.
	 * Returns false if index was out of bound.
	 */
	public boolean repay(int index, int amount) {
		if (index < students.size()) {
			students.get(index).payOff(amount);
			return true;
		} else return false;
	}

	/**
	 * Method removes object from given index and returns removed object.
	 * If index is out of bound - null is returned.
	 */
	public StudentLoan removeLoan(int index) {
		if (index < students.size() && index >= 0) {
			StudentLoan result = students.get(index);
			students.remove(index);
			return result;
		}
		return null;
	}

	/**
	 * Method prints out all student details amending their position in collection.
	 */
	public void list() {
		for (StudentLoan std : students) System.out.println(std.getDetails());
	}

	/**
	 * Method prints all students that have ballance greater than 0
	 */
	public void showOutstanding() {
		for (StudentLoan std : students) {
			if (std.getAmount() > 0) System.out.println(std.getDetails());
		}
	}

	/**
	 * returns FIRST student record that completely matches entered ID. if nothing is found, null is returned
	 */
	public StudentLoan find(String id) {

		for (StudentLoan std : students) if (std.getStudentID().equals(id)) return std;
		return null;
	}

	/**
	 * removes records from the collection that have amount field at 0,
	 * also adds removed records to a LinkedList and returns it as the result of method
	 */
	public LinkedList<StudentLoan> removeClearedLoans() {
		LinkedList<StudentLoan> cleared = new LinkedList<>();
		Iterator iterator = students.iterator();
		while(iterator.hasNext()){
			StudentLoan student = (StudentLoan) iterator.next();
			if(student.getAmount() == 0){
				cleared.add(student);
				iterator.remove();
			}
		}
		return cleared;
	}
}

