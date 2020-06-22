import java.util.*;

/**
 * Keep track of word counts and word pairs.
 *
 * @author Aleksej Bratkovskij
 * @version 11/12/19 Initial production
 * @version 09/01/20 Fixed issue with followedBy evaluating first accurance of pair. Added List to properly iterate through words.
 */
public class WordAnalyser{

    // Using LinkedHashMap holds entries in insertion order.
    private Map<String,Integer> countedWords = new LinkedHashMap<>();
    private ArrayList<String> words = new ArrayList<>();

    /**
     * Add a word to the analyser.
     * The issue of Single Responsibility Principle was raised with the lecturer. Bug related to SRP raises in followedBy method.
     * @param word the word to be added.
     */
    public void addWord(String word){
        if(countedWords.containsKey(word)) countedWords.replace(word, countedWords.get(word)+1);
        countedWords.putIfAbsent(word, 1);
        words.add(word); 
    }
    
    /**
     * Get the number of times the given word has been seen.
     * @param word The word to be looked up.
     * @return The number of times the word has been seen.
     */
    public int getCount(String word){
        if(countedWords.containsKey(word)) return countedWords.get(word);
        return 0;
    }
    
    /**
     * @return true if firstWord has been immediately
     * followed by secondWord; false otherwise.
     * @param firstWord target word
     * @param secondWord following word
     */
    public boolean followedBy(String firstWord, String secondWord){
        Iterator i = words.iterator();
        boolean result;
        if(words.contains(firstWord) && words.contains(secondWord))
        while(i.hasNext()){
            String word = (String) i.next();
            if(firstWord.equals(word)) {
                result = secondWord.equals(i.next());
                if(result) return true;
            }
        }
        return false;
    }
    
    /**
     * Get the number of times the given word has been seen,
     * regardless of the case of its occurrences.
     * @param word The word to be looked up.
     * @return The number of times the word has been seen.
     * 0 if no word was found
     */
    public int getCaseInsensitiveCount(String word){
        int count = 0;
        if(countedWords.containsKey(word)) {
          for(String w : countedWords.keySet()){
            if(w.equalsIgnoreCase(word)) count+= countedWords.get(w);
          }
        }
        return count;
    }
}
