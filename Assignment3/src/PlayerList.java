// CS 0401 Summer 2019
// PlayerList class.
// This class represents a collection of Players -- a very simple database.  The
// collection can be represented in various ways.  In the variables below I used a
// Java ArrayList.  You may keep this or change it to an array if you prefer.

// Note the imports below.  java.util.* is necessary for ArrayList and java.io.* is
// necessary for the file reading and writing.

import java.util.*;
import java.io.*;

public class PlayerList
{
	// Keep an ArrayList of Player objects to store the players within this PlayerList.
	// Also keep track of the file name associated with the PlayerList so that it can
	// be saved back.
	private ArrayList<Player> players;
	private String file;
	
	// Initialize the list from a file.  Note that the file name is a parameter.  You
	// must open the file, read the data, making a new Player object for each line and
	// putting them into the ArrayList.  Note that this method throws IOException.
	// Because of this, any method that CALLS this method must also either catch
	// IOException or throw IOException.  Note that the main() in PlayerListTest.java
	// throws IOException.  Keep this in mind for your main program (Assig3.java).
	// Note that your saveList() method will also need "throws IOException" in its header.
	public PlayerList(String fName) throws IOException
	{
		Scanner fScan = new Scanner(new File(fName));
		while (fScan.hasNextLine())
		{
			String createPlayer = fScan.nextLine();
			String [] playerInfo = createPlayer.split(",");
			players.add(new Player(playerInfo[0], playerInfo[1], Integer.parseInt(playerInfo[2]), Integer.parseInt(playerInfo[3]), Integer.parseInt(playerInfo[4])));
		}
	}

	public String toString()
	{
		StringBuilder playersInfo = new StringBuilder();
		playersInfo.append("Total Players: " +players.size()+ "\n");
		for (int i = 0; i < players.size(); i++)
		{
			playersInfo.append(players.get(i).toString());
		}
		return playersInfo.toString();
	}

	public boolean addPlayer(Player newPlayer)
	{
		boolean alreadyAdded = containsId(newPlayer.getId());

		if (!alreadyAdded)
		{
			players.add(newPlayer);
			alreadyAdded = true;
			return alreadyAdded;
		}
		else
		{
			alreadyAdded = false;
			return alreadyAdded;
		}
	}

	public boolean containsId(String idCheck)
	{
		boolean check = false;

		for (int i = 0; i < players.size(); i++)
		{
			if (players.get(i).getId().equals(idCheck))
			{
				check = true;
			}
		} return check;
	}

	public Player authenticate(Player checkPlayer)
	{
		for (int i = 0; i < players.size(); i++)
		{
			if (checkPlayer.getId().equals(players.get(i).getId()))
			{
				if (checkPlayer.getPw().equals(players.get(i).getPw()))
				{
					checkPlayer = players.get(i);
					return checkPlayer;
				}
			}
		}
		checkPlayer = null;
		return checkPlayer;
	}

	public void saveList() throws IOException
	{

	}



	// See program PlayerListTest.java to see the other methods that you will need for
	// your PlayerList class.  There are a lot of comments in that program explaining
	// the required effects of the methods.  Read that program very carefully before
	// completing the PlayerList class.  You will also need to complete the Player class
	// before the PlayerList class will work, since your ArrayList is an ArrayList of
	// Player objects.
	
	// You may also need some methods that are not tested in PlayerListTest.java.  Think
	// about what you need to do to a PlayerList in your Assig3 program and write the methods
	// to achieve those tasks.
}