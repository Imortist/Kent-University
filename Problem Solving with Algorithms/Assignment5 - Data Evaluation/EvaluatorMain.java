import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Starting point for the decision-tree evaluator.
 * Filenames for the tree and data may be provided
 * as command-line arguments. Both filenames must
 * be provided if the defaults are to be changed.
 *
 * @author Aleksej Bratkovskij (ab2323) & Gregory Fomichev (gf217)
 * @version 13/04/20
 */
public class EvaluatorMain
{
    public static void main(String[] args)
            throws IOException
    {
        // Source of the decision tree.
        String treeFile = "tree.txt";
        // Source of the data to be evaluated.
        String dataFile = "data.txt";
        if(args.length == 2) {
            treeFile = args[0];
            dataFile = args[1];
        }
        List<String> nodes = readFile(treeFile);
        List<String> data = readFile(dataFile);


        Evaluator ev = new Evaluator(nodes);
        System.out.println(ev.evaluate(data));
    }

    /**
     * Read the contents of the given file.
     * Return a list of the lines in the file.
     * Newline characters are not included.
     * @param filename The file to be read.
     * @return A list of the lines in the file.
     * @throws IOException if the file cannot be read.
     */
    static List<String> readFile(String filename)
        throws IOException
    {
        Scanner sc = new Scanner(new File(filename));
        List<String> lines = new ArrayList<>();
        while(sc.hasNext()) {
            lines.add(sc.nextLine());
        }
        return lines;
    }
}
