import java.util.List;

/**
 * A simple 'driver' for the WordAnalyser class.
 * 
 * Split a 'document' into individual words and pass them
 * to a WordAnalyser to be analysed.
 * 
 * In this example, the 'document' is a String literal in
 * the main method, but this could easily be changed to an
 * external file, if desired (e.g., use a Scanner).
 * 
 * Print a few sample statistics.
 *
 * @author David J. Barnes
 * @version 2019.12.04
 */
public class WordAnalyserMain
{
    public static void main(String[] args)
    {
        String document = 
            "The first day of the third month was always difficult for Joy because there was little to look forward to in the month of March.";
        String[] words = document.split("[ ,.;:?]+");
        WordAnalyser analyser = new WordAnalyser();
        for(String word : words) {
            analyser.addWord(word);
        }
        for(String word : List.of("The", "the", "joy", "was")) {
            System.out.println(word);
            System.out.println("    " + analyser.getCount(word));
            System.out.println("    " + analyser.getCaseInsensitiveCount(word));
            System.out.println("    " + analyser.followedBy(word, "month"));
        }
    }
}
