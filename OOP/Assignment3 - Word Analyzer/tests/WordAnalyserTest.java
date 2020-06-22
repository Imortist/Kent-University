import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WordAnalyserTest {

	WordAnalyser wa = new WordAnalyser();
	String document;

	@Before
	public void setUo(){
		document =
				"The first day of the third month was always difficult for Joy because there was little to look forward to in the month of March.";
		String[] words = document.split("[ ,.;:?]+");
		for(String word : words) {
			wa.addWord(word);
		}
		for(String word : List.of("The", "the", "joy", "was")) {
			System.out.println(word);
			System.out.println("    " + wa.getCount(word));
			System.out.println("    " + wa.getCaseInsensitiveCount(word));
			System.out.println("    " + wa.followedBy(word, "month"));
		}
	}

	@Test
	public void addWordCreatesNewEntryWithValueOne(){
		wa.addWord("aaa");
		assertEquals(1, wa.getCount("aaa"));
	}

	@Test
	public void addWordIncreasesCountByOneForExistingWords(){
		wa.addWord("aaa");
		wa.addWord("aaa");
		assertEquals(2, wa.getCount("aaa"));
	}

	@Test
	public void addWordIsCaseSensitive(){
		wa.addWord("aaa");
		wa.addWord("Aaa");
		wa.addWord("Aaa");
		assertEquals(2, wa.getCount("Aaa"));
		assertEquals(1, wa.getCount("aaa"));
	}

	@Test
	public void addWordAddsEmptyStrings(){
		wa.addWord("");
		wa.addWord("");
		assertEquals(2, wa.getCount(""));
	}

	@Test
	public void getCountReturnsZeroIfNoWordsFound(){
		assertEquals(0, wa.getCount("asdasd"));
	}

	@Test
	public void getCountReturnsZeroIfNullIsPassed(){
		assertEquals(0, wa.getCount(null));
	}

	@Test
	public void getCountReturnsCountForWordEncounteredInText(){
		wa.addWord("aaaa");
		wa.addWord("bbb");
		wa.addWord("bbb");
		wa.addWord("bbb");
		assertEquals(1, wa.getCount("aaaa"));
		assertEquals(3, wa.getCount("bbb"));
	}



	@Test
	public void followedByReturnsTrueIfOneWordIsFollowedByAnotherOne(){
		wa.addWord("aaa");
		wa.addWord("bbb");
		assertTrue(wa.followedBy("aaa","bbb"));
	}

	@Test
	public void followedByReturnsFalseIfFirstWordDoesntExist(){
		wa.addWord("aaa");
		assertFalse(wa.followedBy("bbb", "aaa"));
	}

	@Test
	public void followedByReturnsFalseIfSecondWordDoesntExist(){
		wa.addWord("aaa");
		assertFalse(wa.followedBy("aaa", "bbb"));
	}

	@Test
	public void followedByWorksWithBigSetOfData(){
		wa.addWord("mother");
		wa.addWord("father");
		wa.addWord("son");
		wa.addWord("sun");
		wa.addWord("day");
		assertTrue(wa.followedBy("mother", "father"));
		assertFalse(wa.followedBy("son", "day"));
	}

	@Test
	public void getCaseInsensitiveCountReturnsNumberOfWordsInCollectionRegardlessOfCase(){
		wa.addWord("Mother");
		wa.addWord("mother");
		wa.addWord("moTHeR");
		wa.addWord("aaa");
		assertEquals(3, wa.getCaseInsensitiveCount("mother"));
		assertEquals(1, wa.getCaseInsensitiveCount("aaa"));
		assertEquals(0, wa.getCaseInsensitiveCount("aaaaa"));
	}

	@Test
	public void getCaseInsensitiveCountReturnsZeroIfNoWordsInCollection(){
		assertEquals(0, wa.getCaseInsensitiveCount(null));
	}

	@Test
	public void GetCountTheWouldReturn1(){
		assertEquals(1, wa.getCount("The"));
	}

	@Test
	public void GetCountLowerTheWouldReturn2(){
		assertEquals(2,wa.getCount("the"));
	}

	@Test
	public void GetInsensitiveCountTheWouldReturn3(){
		assertEquals(3, wa.getCaseInsensitiveCount("The"));
	}

	@Test
	public void FollowsTheMonthReturnsFalse(){
		assertFalse(wa.followedBy("The", "month"));
	}

	@Test
	public void FollowsLowerTheMonthReturnsTrue(){
		assertTrue(wa.followedBy("the","month"));
	}

}