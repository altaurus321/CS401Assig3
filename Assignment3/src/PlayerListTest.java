// CS 0401 Summer 2019
// Program to test PlayerList and Player classes.  Note the sample output shown
// for this program -- the first time run some new players are added but the
// second time none are added since the updated list was saved back to the file.

// Your Player and PlayerList classes can be implemented in various ways and must
// have additional methods to implement your word finder game effectively.  However, the
// required methods below demonstrate how the classes should fundamentally work, and
// your Player and PlayerList classes must produce the same results given the same
// players.txt file.

// For some help with these files you should look at Lab 7 and, in particular the
// Movie class and the MovieDB class.

import java.util.*;
import java.io.*;

public class PlayerListTest
{
	public static void main(String [] args) throws IOException
	{
		System.out.println("Testing PlayerList and Player classes");
		
		// Create a PlayerList from a file of players.  Note the format of the file
		// and note that after this constructor all of the player information is stored
		// within the allPlayers object.  Note also the effect of the toString() method.
		// Parse the output carefully in order to figure out what it entails.
		PlayerList allPlayers = new PlayerList("players.txt");
		System.out.println("Here are the player stats: ");
		System.out.println(allPlayers.toString());
		
		System.out.println();
		String [] names = {"Marge", "Fezzik", "Ingmar", "Inigo"};
		String [] passes = {"IHeartCS401", "Sportsmanlike", "Programming!", "YouKeepUsingThatWord"};
		for (int i = 0; i < names.length; i++)
		{
			String S = names[i];
			String P = passes[i];
			// containsId will return true if the Player's id is within the PlayerList and
			// false otherwise.  This will be useful in your main program.
			boolean found = allPlayers.containsId(S);
			if (found)
			{
				System.out.println(S + " is an id in the PlayerList\n");
			}
			else
			{
				System.out.println(S + " is not in the list -- will be added:");
				// Create a new player with just a String for the name.  The remaining
				// fields should be null or 0
				Player onePlayer = new Player(S);
				// Set the password for the player
				onePlayer.setPass(passes[i]);
				// Show player info using the toString() method.
				System.out.println("\tNew player info: ");
				System.out.println(onePlayer.toString());
				// Add the player to the PlayerList
				if (allPlayers.addPlayer(onePlayer))
					System.out.println("has been added to the PlayerList");
				else
					System.out.println("Not added!");
			}
		}
		
		System.out.println("Checking for valid Players");
		for (int i = 0; i < names.length; i++)
		{
			String S = names[i];
			String P = passes[i];
			Player temp = new Player(S);
			temp.setPass(P);
			// authenticate() takes a Player argument and checks to see if a Player within
			// the PlayerList matches that Player (by both ID and password).  If a
			// Player matches, return that Player; otherwise return null.  The idea is that
			// we can ask a user to enter an id and password and create a simple Player
			// object out of those.  However, in order to get the rest of the information
			// (game stats) we need to retrieve the Player in question from the PlayerList.
			// authenticate() will do that, provided that the id and password are valid.  If
			// they are not the authenticate() method will return null.
			Player play = allPlayers.authenticate(temp);
			if (play != null)
			{
				// The authenticate() method will return the reference of a Player within the
				// PlayerList and NOT a copy of the Player.  We discussed this issue in lecture.
				// It may not always be the best idea to do this, but since we have a reference into
				// the same Player that is in the PlayerList, we can mutate it and the effect will
				// occur within the PlayerList.  Note the mutations below.
				System.out.println("Player " + play + " has been authenticated and can play!\n");
				// The methods below can be used to update a Player during the game (after each
				// completed round).  You may want some additional mutators as well.  Note
				// carefully what each mutator takes for an argument and see the test output for
				// the effect of each mutator.
				play.addRounds(1);
				play.addWords(4);
				play.addPoints(15);
			}
			else // In this example Inigo will not be valid since the password we have
			{	 // for him in the passes[] array does not match the password in the file
				System.out.println("Player " + temp + " does not have a valid password.\n");
			}
			
			
		}
		// Note the format and information returned by the toString() method.  Your
		// output should contain the same, well formatted information.  Note that this
		// call shows ALL of the Players -- this is because the toString() method in the
		// PlayerList class will iterate through all of the Player objects and call the
		// toString() method for each, appending them all into one (very big) StringBuilder.
		// It will then return the single string representing the entire list.
		System.out.println("Here are the player stats: ");
		System.out.println(allPlayers.toString());
		
		// Write the players back to a text file so that they can be retrieved later.
		// Clearly to write the Player information to the file, the PlayerList class must
		// be able to get the information from each of the Player objects.  Use this to
		// help you design both the Player and PlayerList classes.  The idea of this method
		// is as follows:  Iterate through the list of players, writing the information for
		// each player back to the file.  This can be done by defining a method in the
		// Player class such as
		//     public String saveString() {}
		// which will return all of the information from the Player appended together as
		// a single comma separated String.  Note that this is NOT the same as toString(), 
		// since toString() has formatting in it.  saveString() is simply the raw data in
		// the format necessary for the file.
		//allPlayers.saveList();
	}		
}