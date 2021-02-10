import java.util.*;
import java.io.*;
/**
 *
 * @author ab2323 (Aleksej Bratkovskij)
 * @version 10/12/2020
 */
public class ab2323_task1
{
    /**
     *
     * @param args input numbers followed by space: number of disks, number of towers, source tower index, destination tower index (indexing is 1 based)
     *
     */
    public static void main(String[] args) {
        int n, t, s, d;
        String my_ID = "ab2323";

        //Check if input was provided as required;
        if(args.length<4) //Left untouched from sb2213_task1;
        {
            System.out.printf("Usage: java %s_task1 <n> <t> <s> <d>\n", my_ID);
            return;
        }

        // get user input for n,t,s,d
        n = Integer.parseInt(args[0]);
        t = Integer.parseInt(args[1]);
        s = Integer.parseInt(args[2]);
        d = Integer.parseInt(args[3]);

        //Sanity check
        if (n<1 || t<3 || s<1 || s>t || d<1 || d>t)
        {
            System.out.print("Please enter proper parameters. (n>=1; t>=3; 1<=s<=t; 1<=d<=t)\n");
            return;
        }
        String filename = my_ID + "_ToH_n" + n + "_t" + t + "_s" + s + "_d" + d + ".txt";
        try{
            //set up file output
            FileWriter writer = new FileWriter(filename, true);
            writer.write(n + "\t" + t + "\t" + s + "\t" + d);
            ab2323_task1 task1 = new ab2323_task1();

            //Problem setup
            task1.solve(n,s,d,task1.makeBuffer(t,s,d),task1.setUp(n,t,s),writer);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * Set up Tower of Hanoi problem using Stack arrays.
     * @param numberOfDisks number of disks in source
     * @param numberOfTowers number Stacks in array
     * @param source Stack with disks
     * @return Tower Of Hanoi representation with source filled with disks
     *
     */
    private ArrayList<Stack<Integer>> setUp(int numberOfDisks, int numberOfTowers, int source){
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

    /**
     *
     * Tower of Hanoi solver.
     * @param disksCount number of disks in the problem
     * @param fromTower source tower(Stack) index
     * @param toTower destination tower(Stack) index
     * @param bufferTower buffer tower(Stack) index
     * @param towers collection of towers (Stacks)
     * @param fw file writer to write results in file
     *
     */
    private void solve(int disksCount, int fromTower, int toTower, int bufferTower, ArrayList<Stack<Integer>> towers, FileWriter fw){
            // solving tower of hanoi for one disk
        if(disksCount==1){
            moveDisk(disksCount, fromTower, toTower, towers, fw);
            return;
        }
        //solve tower of hanoi from source to buffer tower using destination as buffer
        solve((disksCount-1),fromTower,bufferTower,toTower,towers,fw);
        moveDisk(disksCount,fromTower,toTower,towers,fw);
        //solve tower of hanoi from buffer tower as source and source tower as buffer
        solve((disksCount-1),bufferTower,toTower,fromTower,towers,fw);
    }

    /**
     *
     * Moves one disk and records it in console and in file
     * @param disksCount number of disks
     * @param fromTower Index of source tower
     * @param toTower Index of destination tower
     * @param towers ArrayList<Stack<Integer>> representing current problem
     * @param fw filewriter
     *
     */
    private void moveDisk(int disksCount, int fromTower, int toTower, ArrayList<Stack<Integer>> towers, FileWriter fw) {
        System.out.println("Move disk "+ disksCount +" from T"+ fromTower +" to T"+ toTower); //Hardcoded reply if there is 1 disc;
        try{ //TextWriter for the alternative output if there is 1 disc;
            fw.write("\n"+ disksCount +"\t"+ fromTower +"\t"+ toTower);
        } catch (IOException e) {
            e.printStackTrace();
        }
        towers.get(toTower).push(towers.get(fromTower).pop()); //Moving disks
    }

    /**
     *
     * @param towersCount amount of towers available
     * @param source index of source tower
     * @param destination index of destination tower
     * @return index of buffer tower to use
     *
     */
    public int makeBuffer(int towersCount, int source,int destination){
        int buffer=0;
        if(source+1 != destination && source+1 <=towersCount){ //Source +1 valid?
            buffer=source+1;
        }
        else if(source-1 !=0 && source-1 != destination){ //Source -1 valid?
            buffer=source-1;
        }
        else if(destination+1 != source && destination+1<=towersCount){ //Destination +1 valid?
            buffer=destination+1;
        }
        else if(destination-1 != source && destination-1 !=0){ //Destination -1 valid?
            buffer=destination-1;
        }
        return buffer;
    }
}