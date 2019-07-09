import java.util.Scanner;

public class Assignment3 {

    public static void main (String [] args)
    {
        WordFinder wfGame = new WordFinder("dictionary.txt");       //Initializing
        MyTimer timer = new MyTimer();                                       //Initializing
        Scanner inScan = new Scanner(System.in);
        int test, round = 0;                                                 //overall stat counters
        double totalWords = 0, totalPoints = 0;                              //overall stat counters

        //Welcome message
        System.out.println("Welcome to the Word Finder Program\n\n" +

                         "Here is how to play:\n" +
                         "      For each round you will see a randomly selected word. \n" +
                         "      you will have 1 minute to find as many words as you can \n" +
                         "      that can be generated with the letters in your random \n" +
                         "      word. Your words do not have to use all of the letters \n" +
                         "      in the word but they must be valid words. You will receive \n" +
                         "      a point for each letter in each word, so longer words count \n" +
                         "      more than shorter words.\n");

        System.out.print("Would you like to play? (1 = yes / 2 = no) ");
        test = inScan.nextInt();
        inScan.nextLine();

        //input doesn't match yes or no
        while ((test != 1) && (test != 2))
        {
            System.out.println("Invalid value -- please try again");

            System.out.print("Would you like to play? (1 = yes / 2 = no) ");
            test = inScan.nextInt();
            inScan.nextLine();
        }

        //They want to play the game
        while (test == 1)
        {
            String wordCheck;               //Stores each guess the user inputs temporarily
            int wordLength;                 //Stores word length choice
            int count = 0;                  //Word counter for each game
            Boolean guess;                  //Stores whether the guessed word was valid

            //new object created each game to store words guessed by user
            Dictionary currentGameWords = new Dictionary();

            //Allows for customizable word lengths that differ each game
            System.out.println("How long would you like your random word to be (at least)?  ");
            wordLength = inScan.nextInt();
            inScan.nextLine();
            wfGame.nextWord(wordLength);

            System.out.println("Great! 1 minute is on the clock... GO!\n");
            timer.set(60000);
            timer.start();

            //continues loop while timer is going
            do {

                System.out.printf("Word: %-15s Guess?  ", wfGame.showWord());
                wordCheck = inScan.nextLine();

                //another check to see if the timer is valid prior to storing input
                if (timer.check())
                {
                    //checks whether the input was valid
                    guess = wfGame.goodWord(wordCheck);

                    //if it is and isn't already contained in the current game's dictionary,
                    //it stores it and increases the word count.
                    if (guess && !currentGameWords.contains(wordCheck))
                    {
                        currentGameWords.addWord(wordCheck);
                        count++;
                        System.out.println("     Answer " +count+ " is " +wordCheck);
                    }

                    //if it's valid but already added to the current game's dictionary,
                    //it lets them know it's a duplicate word.
                    else if (guess)
                    {
                        System.out.println(wordCheck+ " is a duplicate word.");
                    }

                    //otherwise, it tells the user the input wasn't valid
                    else
                        System.out.println(wordCheck+ " is not a valid word.");
                }

                //runs once the time runs out
                else
                {

                    //round statistics
                    System.out.println("Sorry! Time is up!\n" +
                                       "You found " +count+ " words.\n" +
                                       "You earned " +(currentGameWords.toString().length() - count)+ " points.\n" +
                                       "Here are your words:\n" +currentGameWords.toString());

                    //keeps track of points across rounds
                    totalPoints += (currentGameWords.toString().length() - count);

                    //keeps track of the number of words across rounds
                    totalWords += count;

                    //keeps track of the number of rounds
                    round++;

                    //Asks if the user would like another round
                    System.out.print("Would you like to play? (1 = yes / 2 = no) ");
                    test = inScan.nextInt();
                }
            } while (timer.check());
        }

        //They're done playing the game
        System.out.println("\nThanks for playing!\n");

        //only gives stats if the user played at least once
        if (round > 0)
            System.out.println("You played " +round+ " round(s). \n" +
                               "You found a total of " +(int) totalWords+ " word(s). \n" +
                               "You earned a total of " +(int) totalPoints+ " point(s). \n" +
                               "Your average found was " +(totalWords / round)+ " words per round. \n" +
                               "Your average points were " +(totalPoints / round)+ " points per round.");
        else
            System.out.println("Come back soon!!");
    }
}
