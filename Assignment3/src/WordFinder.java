// CS 401 Summer 2019
// Assignment 2 WordFinder class
// You must complete the implementation of this class.  You will need
// to use an instance of the Dictionary class within this class.  See
// Dictionary.java for details on the Dictionary class.

public class WordFinder
{
	private String currRoundWord;
	Dictionary D;

	// Think about the instance variables that you will need for this class.
	// Minimally you will need a Dictionary and a String.
	
	// Initialize a WordFinder object.  String fileName is the name of a
	// Dictionary file from which the Dictionary instance variable will be
	// initialized.
	public WordFinder(String fileName)
	{
		currRoundWord = "";
		D = new Dictionary(fileName);
	}
	
	// Obtain and store (with this WordFinder object) a random word from the
	// Dictionary of "size" or more letters.
	public void nextWord(int size)
	{
		currRoundWord = D.randWord(size);
	}
	
	// Return the word that was obtained. This is necessary since the word itself
	// will be stored in a private instance variable.
	public String showWord()
	{
		return currRoundWord;
	}
	
	// This is the most challenging method in this class.  The "test" argument is
	// a String that will be checked for validity within the current word that was
	// obtained from the Dictionary.  This method should return true only if all of
	// the characters in "test" are found within the word (such that each letter in
	// the word is used at most one time) and if "test" is also a valid word in the
	// Dictionary.  Think about how you will do this and consult the Java API for
	// some ideas (in particular look at methods in the StringBuilder class)



	//The logic of this method converts both the current game's word and the user's
	//input word into StringBuilder objects, then iterates through comparing each
	//StringBuilder. The outer loop is the user's input that the method is testing
	//for validity, while the inner loop is the current game's word. Each time a
	//letter is matched, it deletes that letter from the Inner loop's StringBuilder
	//and moves to the next letter of the outer loop. If a letter can't be found,
	//the loop ends and returns a false value.
	public boolean goodWord(String test)
	{
		StringBuilder tempCurrRoundWord = new StringBuilder(this.currRoundWord);
		StringBuilder tempTest = new StringBuilder(test);
		boolean goodWordMatch = true;

		//checks to see if the user's word is found within dictionary.txt
		if (D.contains(test))
		{
			//iterates through each letter of the user's input
			for (int i = 0; (i < (tempTest.length())) && (goodWordMatch); )
			{
				//iterates through each letter of the current round's word
				for (int x = 0; x < tempCurrRoundWord.length(); ) {

					//checks to see if test word matches
					if (tempTest.charAt(i) == tempCurrRoundWord.charAt(x))
					{
						tempCurrRoundWord.deleteCharAt(x);
						x = tempCurrRoundWord.length();
						i++;
					}

					//increments to next letter of round word, the
					//-1 is so it doesn't stop interior for loop
					else if (x < (tempCurrRoundWord.length() - 1))
					{
						x++;
					}

					//character not found and returns false
					else
					{
						goodWordMatch = false;
						return goodWordMatch;
					}
				}
			}
			//word is valid and returns true
			return goodWordMatch;

		} else   //word isn't found in dictionary.txt, returns false
		{
			goodWordMatch = false;
			return goodWordMatch;
		}
	}
}
