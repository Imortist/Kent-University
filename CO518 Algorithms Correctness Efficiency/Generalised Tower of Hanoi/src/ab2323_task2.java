import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author ab2323 (Aleksej Bratkovskij)
 * @version 10/12/2020
 */
public class ab2323_task2 {


    public static void main(String[] args) {
        String my_ID = "ab2323";
        // Check if the input filename has been provided as an argument;
        if (args.length < 1){
            System.out.printf("Usage: java %s_task2 <file_name>\n", my_ID);
            return;
        }
        try {

            String[] firstLine = Files.readAllLines(Paths.get(args[0])).get(0).split("\t");
            int disks = Integer.parseInt(firstLine[0]);
            int towers = Integer.parseInt(firstLine[1]);
            int source = Integer.parseInt(firstLine[2]);
            int destination = Integer.parseInt(firstLine[3]);
            ArrayList<Stack<Integer>> toh = setUp(disks,towers,source);

            //print default state of problem
            System.out.print("The status of all towers at the start is as follows:\n");
            for(int i = 0; i<toh.size();++i){
                System.out.printf("Tower %d: %s\n", i+1, toh.get(i).toString());
            }

            //evaluate each move
            Files.lines(Paths.get(args[0])).skip(1)
                    .map(l -> l.split("\t"))
                    .forEach(a -> evaluate(a[0], a[1], a[2], toh));//evaluate each line
            //after evaluating lines, check if all disks in destination tower.
            if(!allTowersEmptyButDestination(toh,destination)){
                System.out.print("\n Not all disks are at destination.\n");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     *
     * @param toh ArrayList<Stack<Integer>> representing Tower of Hanoi
     * @param destination index of destination tower
     * @return true/false depending on every stack being empty apart from destination stack
     */
    private static boolean allTowersEmptyButDestination(ArrayList<Stack<Integer>> toh, int destination) {
        ArrayList<Boolean> results = new ArrayList<>();
        for(Stack<Integer> tower : toh){
            if(toh.indexOf(tower) != destination && tower.isEmpty()) results.add(true);
            else if(toh.indexOf(tower) != destination && !tower.isEmpty()) results.add(false);
        }
        return !results.contains(false);
    }

    /**
     * Core evaluating method. Evaluates moves recorded in text file and prints error messages.
     * @param diskNum number of disks
     * @param from index of source stack
     * @param to index of destination stack
     * @param problem ArrayList<Stack<Integer>> representing Tower of Hanoi
     */
    private static void evaluate(String diskNum, String from, String to, ArrayList<Stack<Integer>> problem) {

        int disk = Integer.parseInt(diskNum);
        int source = Integer.parseInt(from);
        int destination = Integer.parseInt(to);

        boolean wrongDiskOnTop = problem.get(source).isEmpty() || disk != problem.get(source).peek(); //records if disk being moved is incorrect or doesnt exist in the source tower
        boolean diskIsTooBig = !problem.get(destination).isEmpty() && disk > problem.get(destination).peek(); // records if disk being moved is bigger than the disk at destination tower

        defaultPrint(problem, disk, source, destination);

        //printing messages if conditions met
        if (wrongDiskOnTop) {
            printWrongDiskDeclaration(problem.get(source), disk);
        } else if (diskIsTooBig) {
            printDiskIsTooBig(disk, problem.get(destination));
        } else { // if everything OK, proceed with confirmation message
            problem.get(destination).push(problem.get(source).pop());
            printAllGood(problem, source, destination);
        }

    }

    /**
     *  prints message of disk being too big
     * @param disk number of disk that is being moved
     * @param destinationTower index of destination tower
     */
    private static void printDiskIsTooBig(int disk, Stack<Integer> destinationTower) {
        System.out.printf("Move Error:\n Destination Tower: %s, has a smaller disk than %d on the top\n", destinationTower.toString(), disk);
    }

    /**
     *   prints if declared disk to move doesnt exist or stack is empty
     * @param from index of source tower
     * @param diskNum number of disk that is being moved
     */
    private static void printWrongDiskDeclaration(Stack<Integer> from, int diskNum) {
        if(!from.isEmpty()) System.out.printf("Move Error:\n Disk not found: Expected %d at %s. Found %d\n", diskNum, from.toString(), from.peek());
        else System.out.printf("Move Error:\n Disk not found: Expected %d at %s. Found EmptyStack\n",  diskNum, from.toString());
    }

    /**
     *  prints the move that is being red from the text file
     * @param problem ArrayList<Stack<Integer>> representing Tower of Hanoi
     * @param disk number of disk being moved
     * @param source index of source tower
     * @param destination index of destination tower
     */
    private static void defaultPrint(ArrayList<Stack<Integer>> problem, int disk, int source, int destination) {
        System.out.printf("Move: disk %d from tower %d to tower %d\n", disk, source, destination);
        System.out.println("Before the move:");
        System.out.printf("Source tower %d: %s\n", source, problem.get(source).toString());
        System.out.printf("Destination tower %d: %s\n", destination, problem.get(destination).toString());
    }

    /**
     * prints if move was successfull
     * @param problem ArrayList<Stack<Integer>> representing Tower of Hanoi
     * @param source index of source tower
     * @param destination index of destination tower
     */
    private static void printAllGood(ArrayList<Stack<Integer>> problem, int source, int destination) {

        System.out.println("After the move:");
        System.out.printf("Source tower %d: %s\n", source, problem.get(source).toString());
        System.out.printf("Destination tower %d: %s\n", destination, problem.get(destination).toString());
    }

    /**
     * sets up ArrayList<Stack<Integer>> representing Tower of Hanoi
     * @param numberOfDisks int number of disks in tower
     * @param numberOfTowers number of towers in problem
     * @param source index of a source tower
     * @return ArrayList<Stack<Integer>> representing Tower of Hanoi
     */
    private static ArrayList<Stack<Integer>> setUp(int numberOfDisks, int numberOfTowers, int source){
        ArrayList<Stack<Integer>> collectionOfTowers = new ArrayList<>(numberOfTowers);
        //creating stacks in array
        for(int i=0; i<=numberOfTowers;i++){
            collectionOfTowers.add(new Stack<>());
        }
        //populating source with disks
        for(int j=numberOfDisks;j>0;j--){
            collectionOfTowers.get(source).push(j);
        }
        return collectionOfTowers;
    }
}