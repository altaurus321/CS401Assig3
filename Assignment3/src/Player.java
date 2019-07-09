// CS 0401 Summer 2019
// Player class
// You must complete this class for Assignment 3.  It must run in your Assig3.java
// main program and it must also run correctly with the test program
// PlayerListTest.java.

// Some instance variables and constructors are shown.  You may add some additional 
// instance variables if you wish.  You will also need to add the methods that are 
// called in the program PlayerListTest.java.  Read over that program carefully so 
// that you know the method that you need in this class.

// You may also need some methods that are not tested in PlayerListTest.java.  Think
// about what you need to do to a Player in your Assig3 program and write the methods
// to achieve those tasks.

// Note that all of the methods in class are fairly simple since they all deal with
// a SINGLE Player.  The methods that deal with multiple players will be in the
// PlayerList class.

public class Player
{
	private String id, password;  // must store id and password for each player
	private int roundsPlayed, wordsFound, points;  // must also store these
						// values for the player stats

	// Default constructor 
	public Player()
	{
		id = null;
		password = null;
		roundsPlayed = 0;
		wordsFound = 0;
		points = 0;
	}
	
	// Construct new Player with just ID
	public Player(String newId)
	{
		id = new String(newId);
		password = null;
		roundsPlayed = 0;
		wordsFound = 0;
		points = 0;
	}
	
	// Construct new Player with all fields passed in
	public Player(String newid, String pass, int rds, int wds, int pts)
	{
		id = new String(newid);
		password = new String(pass);
		roundsPlayed = rds;
		wordsFound = wds;
		points = pts;
	}

	public String getId()
	{
		return id;
	}

	public String getPw()
	{
		return password;
	}

	public void setPass(String newPass)
	{
		password = new String(newPass);
	}

	public void addPoints(int p)
	{
		points += p;
	}

	public void addWords(int w)
	{
		wordsFound += w;
	}

	public void addRounds(int r)
	{
		roundsPlayed += r;
	}

	public String toString()
	{
		StringBuilder playerInfo = new StringBuilder();
		playerInfo.append("Id: " +id+ "\n");
		playerInfo.append("Total Rounds Played: " +roundsPlayed+ "\n");
		playerInfo.append("Total Words Found: " +wordsFound+ "\n");
		playerInfo.append("Ave Words Found: " +(wordsFound / roundsPlayed)+ "\n");
		playerInfo.append("Ave Points Earned: " +(points / roundsPlayed)+ "\n");
		return playerInfo.toString();
	}
	// You must fill in the remaining methods.  See PlayerListTest.java for the
	// methods that are minimally required.  However, you may certainly add some
	// methods that you will find useful, even if they are not used in the
	// PlayerListTest.java program.
}