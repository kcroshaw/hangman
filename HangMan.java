/*
 * CSIS 2450
 * Kolby Croshaw
 * A01 Hangman
 * 9/27/2020
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;
import java.util.Scanner;

public class HangMan {

 
 public static final String[] WORDS = 
			 {
					 "utah", "ogden", "farmington", "kaysville", "logan", "smithfield", "weber", "morgan", "layton" 
			 };
 //Random number to select word for user to guess
 public static final Random RANDOM = new Random();
 // 6 lives for the user to guess the word
 public static final int maxError = 6;
 // This is the word to find
 private String wordToFind;
 // Word stored in an array to track progression
 private char[] wordGuessed;
 private int nbErrors;
 // Array List to store letters guessed by user
 private ArrayList < String > letters = new ArrayList < > ();

 // Method returning randomly next word to find
 private String nextWordToGuess() 
	 {
		 return WORDS[RANDOM.nextInt(WORDS.length)];
	 }

 // Method to start a new game
 public void newGame() 
	 {
	  nbErrors = 0;
	  letters.clear();
	  wordToFind = nextWordToGuess();
	
	  // word guessed initialization
	  wordGuessed = new char[wordToFind.length()];
	
	  for (int i = 0; i < wordGuessed.length; i++) 
		  {
			  wordGuessed[i] = '_';
		  }  
	 }

 // Method returning true if word is guessed by user
 public boolean wordGuessed() 
	 {
		 return wordToFind.contentEquals(new String(wordGuessed));
	 }

 // Method updating the word found after user entered a character
 private void enter(String c) 
	 {
	  // we update only if c has not already been entered
	  if (!letters.contains(c)) 
		  {
		   // we check if word to find contains c
		   if (wordToFind.contains(c)) 
			   {
			    // if so, we replace _ by the character c
			    int index = wordToFind.indexOf(c);	
			    while (index >= 0) {
			    wordGuessed[index] = c.charAt(0);
			    index = wordToFind.indexOf(c, index + 1);
    }
   } else 
	   {
	    // c not in the word => error
	    nbErrors++;
	   }

   // c is now a letter entered into the array list
   letters.add(c);
		  }
	 }

 // Method returning the state of the word found by the user until by now
 private String wordFoundContent() 
	 {
	  StringBuilder builder = new StringBuilder();
	
	  for (int i = 0; i < wordGuessed.length; i++) 
	  {
	   builder.append(wordGuessed[i]);
	
	   if (i < wordGuessed.length - 1) 
		{
	    builder.append(" ");
		}
	  }
	
	  return builder.toString();
	 }

 // Method to play the game
 public void play() 
	 {
	  try (Scanner input = new Scanner(System.in)) 
	  {
	   // we play while nbErrors is lower than max errors or user has found the word
	   while (nbErrors < maxError) 
		{
		    System.out.println("\nEnter a letter : ");
		    // get next input from user
		    String str = input.next();
		
		    // In case the user enters more than one letter in their input
		    if (str.length() > 1) 
		    {
		     str = str.substring(0, 1);
		    }
		
		    // update word guessed
		    enter(str);
		
		    // display current state
		    System.out.println("\n" + wordFoundContent());
		
		    // check if word is guessed
		    if (wordGuessed()) 
		    {
		     System.out.println("\ncongratulations!");
		     break;
		    } 
		    else 
		    {
		     System.out.println("lives left: " + (maxError - nbErrors));
		     System.out.print("Letters already guessed: ");
		     
		     Enumeration<String> e = Collections.enumeration(letters);
		     
		     while(e.hasMoreElements())
		    	 System.out.print(e.nextElement() + " ");
		    }
		}

   if (nbErrors == maxError) 
   {
    System.out.println("The word was " + wordToFind);
   }
  }
 }

 public static void main(String[] args) 
	 {
	  System.out.println("Let's Play Hangman");
	  HangMan hangman = new HangMan();
	  hangman.newGame();
	  hangman.play();
	 }

}