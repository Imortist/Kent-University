import java.io.File;
import java.nio.file.Files;

import java.util.stream.Collectors;

/**
 * A simple 'driver' for the WordAnalyser class.
 * <p>
 * Split a 'document' into individual words and pass them
 * to a WordAnalyser to be analysed.
 * <p>
 * In this example, the 'document' can be either the contents of
 * a file passed to the main method, or a String literal embedded in
 * the main method.
 * <p>
 * This version times how long it takes to count the number of duplicate
 * words, which is not functionality provided directly by the WordAnalyser.
 * It acts as a test of the implementation speed of WordAnalyser.
 *
 * @author David J. Barnes
 * @version 2019.12.04
 */
public class WordAnalyserTimerMain
{
    public static void main(String[] args) 
        throws Exception 
    {
        String document;
            File text = new File("wordlist.txt");
            document = Files.lines(text.toPath()).collect(Collectors.joining(" ")).trim();
        
        String[] words = document.split("[ ,.;:?]+");
        WordAnalyser analyser = new WordAnalyser();
        // Count how many words occur more than once.
        int repeatCount = 0;
        long startTime = System.currentTimeMillis();
        for (String word : words) {
            analyser.addWord(word);
            if(analyser.getCount(word) == 2) {
                repeatCount++;
            }
        }
        long endTime = System.currentTimeMillis();
        double seconds = (endTime - startTime) / 1000.0;
        System.out.println(String.format("%d duplicates. %.1f seconds.", repeatCount, seconds));
    }
}
