//Travis Honaker

//import statements
import java.io.*;
import acm.program.*;
import acm.util.RandomGenerator;
import acmx.export.java.util.ArrayList;
import acmx.export.java.util.List;

//main class, extends to be a console program.
public class WordGuess extends ConsoleProgram
{
	//creates an instance of the random generator method
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	//-----------------------------------------------------------------------------------------------------------
	
	//intro method, outputs what the code does.
	public void intro()
	{
		println("This game is a secret word guessing game. Enjoy and Good luck!");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	//playOnegame runs through the game.
	public int PlayOneGame(String secretWord)
	{
		//sets the global variable to the secret word
		SW = secretWord;
		
		//initializes the number of guesses the player has, along with initializing the length to be the length of the secret word.
		int guessesLeft = 8;
		int length = secretWord.length();
		
		//for loop that adds the dashes to the hing that represents the characters of the secret word.
		for (int i = 0; i < length; i++)
		{
			HINT += "-";
		}
		
		//outputs the hing, your guesses, and the amount of guesses you have left.
		println(HINTHEADER + HINT);
		println(TGH + GUESSES);
		println("Guesses left: " + guessesLeft);
		
		// creates a variable that will hold an empty string
		String update = "";
		
		//creates a variable and sets it to the return
		char check = readGuess(GUESSES);
		
		//checks to see if the secret word contains the character that the user entered.
		if (secretWord.contains(GUESSES.substring(0)) == true)
		{
			//if so then it outputs that they were correct and outputs the updated hint and all of their total guesses.
			println("Correct!");
			update = updateHint(secretWord, HINT, check);
			println("Your Guesses: "+ GUESSES);
		}
		//if not then it outputs that they were incorrect and takes a guess away from the player
		else
		{
			println("Incorrect");
			guessesLeft--;
		}
		
		//while loop that runs while the hint still has a dash as part of the string.
		while (HINT.contains("-"))
		{
			// outputs the current hint, how many guesses are left, and their total guesses
			println("Secret Word: " + HINT);
			println("Guesses left: " + guessesLeft);
			println("Your Guesses: "+ GUESSES);
			
			//calls the readguess method to get the next guess
			check = readGuess(GUESSES);
			
			//checks to see if the current guess is not in the word.
			if (secretWord.contains(GUESSES.substring(GUESSES.length()-1)) == false)
			{
				//if so then it outputs that they were incorrect and takes a guess away
				println("Incorrect");
				guessesLeft--;
			}
			//else outputs that they were correct and updates the hint.
			else
			{
				println("Correct!");
				update = updateHint(secretWord, HINT, check);
			}
			
			//if statement that checks to see if the player has used all of their guesses.
			if (guessesLeft == 0)
			{
				//if so then it outputs the secret word and returns to the run program
				println("Secret Word was: " + secretWord);
				return guessesLeft;
			}

		}
		
		//if this return statement triggers, that means that there are no dashes left in the word and the player wins, returns the amount of guesses the player had left.
		return guessesLeft;
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	//readguess method, takes in all their total guesses and reads in their next guess
	public char readGuess(String guessedLetter)
	{

		//creates a variable that holds the users current guess.
		String currentGuess = readLine("Current Guess: ");
		
		//raises the users guess to be a capital letter
		currentGuess = currentGuess.toUpperCase();
		
		//turns the string into a character
		char ch = currentGuess.charAt(0);
		
		//checks to see if the length of the guess is longer than 1
		if (currentGuess.length() > 1)
		{
			//if so outputs that the guess was too long and recalls the readguess method passing in the players total guesses
			println("Type a single letter from A-Z");
			
			//recursively calls the readGuess method and then returns a correct character
			return readGuess(GUESSES);
			
		}
		//else if checks to see if the player has already entered in that guess.
		else if (GUESSES.contains(currentGuess))
		{
			//if so then outputs that they have already entered that guess and recalls the readguess method.
			println("You already entered that letter");
			
			//recursively calls the readGuess method and then returns a correct character
			return readGuess(GUESSES);
		}
		//else, runs when the character has length 1 and hasn't been already guessed.
		else
		{
			//adds it to the total guess and returns that character
			GUESSES += ch;
			return ch;
		}
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	//updateHint method, takes in the secret word, the current hint, and the guess that is being made, returns the updated hint.
	public String updateHint(String secretWord, String currentHint, char nextGuess)
	{
		
		//creates a count variable that keeps track of how many of that character is in the word.
		int count = 0;
		
		//for loop that runs through the characters of the secretWord.
		for (int i = 0; i < secretWord.length(); i++)
		{
			//checks to see if the character at the current location equals the character being guessed.
			if (secretWord.charAt(i) == nextGuess)
			{
				//iterates the count.
				count++;
			}
		}
		
		//checks to see if count == 1, meaning that there is only of that character in the secretword.
		if (count == 1)
		{
			//finds and sets the index of the character in the secret word
			int index = secretWord.indexOf(nextGuess);
			
			//turns the current hint into a character array
			char[] hintCharacters = currentHint.toCharArray();
			
			//changes the value at the index to be the guess value
			hintCharacters[index] = nextGuess;
			
			//sets hint to be empty
			HINT = "";
			
			//for loop that runs through the char array and adds them back to the hint string
			for (int j = 0; j < hintCharacters.length; j++)
			{
				HINT += hintCharacters[j];
			}
			
			//returns the hint string.
			return HINT;
		}
		
		//runs if there is more than 1 of the guessed character in the secret word.
		else
		{
			//finds the first index
			int index = secretWord.indexOf(nextGuess);
			
			//creates a character array from the current hint string
			char[] hintCharacters = currentHint.toCharArray();
			
			//changes that value at that index to the guessed character.
			hintCharacters[index] = nextGuess;
			
			//sets hint to be empty
			HINT = "";
			
			//readds the values from the character array back to the hint string.
			for (int j = 0; j < hintCharacters.length; j++)
			{
				HINT += hintCharacters[j];
			}
			
			//handles the remaining characters.
			while ((count - 1) != 0)
			{
				//finds the next index
				index = secretWord.indexOf(nextGuess, index + 1);
				
				//creates a character array of the values that are in the hint.
				hintCharacters = HINT.toCharArray();
				
				//sets the value at the index to be the guess.
				hintCharacters[index] = nextGuess;
				
				//sets hint to be an empty string
				HINT = "";
				
				//reads the values from the array back to the hint string.
				for (int k = 0; k < hintCharacters.length; k++)
				{
					HINT += hintCharacters[k];
				}
				
				//deiterates the count.
				count--;				
			}
			
			//returns the updated hint.
			return HINT;
				
		}
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	//getRandomWord method, reads in a file and returns with a random word.
	public String getRandomWord(String filename)
	{
		//creates 2 variables, one that will hold the count of the file, and one that is an empty string. 
		int count = 0;
		String returnVal = "";
		
		// try case, used when reading in a file.
		try
		{
			//calls the file reader and buffered reader methods to read in the file.
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			//reads in a line from the file
			String line = br.readLine();
			
			//creates an array list
			List list = new ArrayList();

			//while loop that will run until the end of the file
			while (line != null)
			{
				//adds the read in line to the list
				list.add(line);
				
				//iterates the count
				count++;
				
				//reads in the next line
				line = br.readLine();
			}
			
			// creates a variable that is set to a random number from 1 to the count of the file
			int randomVal = rgen.nextInt(1, count);
			
			//sets the return Val to the the string located at the random position in the file.
			returnVal = (String) list.get(randomVal);
			
			//close the reader.
			br.close();
		}
		//catch case, used when an exception will arise.
		catch(IOException e)
		{
			//outputs that an error has occurred and will output the error.
			println("An error occurred " + e);
		}

		//returns the random word.
		return returnVal;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	//main run function of the code
	public void run()
	{
		//calls and outputs the intro line of the code
		intro();
		
		//sets the variable to the name of the file being used.
		String filename = "dict.txt";
		
		//calls and sets the getRandomWord method using the filename to a variable
		String secretWord = getRandomWord(filename);
		
		//calls the playonegame method passing in the secret word
		int game = PlayOneGame(secretWord);
		
		//if the game returns 0, it means the player lost.
		if (game == 0)
		{
			//outputs that the player lost.
			println("You ran out of guesses, You lose! Secret Word was: " + secretWord);
		}
		//else runs when the player won
		else 
		{
			//outputs that the player won along with how many guesses they had left.
			println("You Win! You had " + game + " guesses remaining. Secret Word was: " + secretWord);
		}
		
		//creates a variable that is set to the entered string after asking the user if they would like to play agian.
		String text = readLine("Play again? Enter YES to play again, or NO to end code: ");
		
		//raises the string to be all uppercase.
		text = text.toUpperCase();
		
		//while loop that runs while the entered text equals YES
		while(text.equals("YES"))
		{
			//double checks that text equals YES
			if (text.equals("YES"))
			{
				//calls getRandomWord and sets it to secretWord for the new game.
				secretWord = getRandomWord(filename);
				
				//resets hint to be empty
				HINT = "";
				
				//clears the users previous guesses from their last game.
				GUESSES = "";
				
				//calls the play game method passing in the new secret word
				game = PlayOneGame(secretWord);		
				
				//looks at if the returned value is 0, if so then the player looses and it outputs what the secret word was.
				if (game == 0)
				{
					println("You ran out of guesses, You lose! Secret Word was: " + secretWord);
				}
				//else, runs if the player won. outputs that they won, how many guesses they had remaining, and what the secret word was.
				else 
				{
					println("You Win! You had " + game + " guesses remaining. Secret Word was: " + secretWord);
				}
			}
			//reasks the user after their second game if they would like to play again.
			text = readLine("Play again? Enter YES to play again, or NO to end code: ");
			
			//raises the users entered yes or no to capital letters
			text = text.toUpperCase();
		}
		//if the user entered no then it outputs that the code is ending.
		if (text.equals("NO"))
		{
			println("Ending Code.");
		}

		
	}
	
	//-----------------------------------------------------------------------------------------------------------

	public String TGH = "Your Guesses: ";
	public String GUESSES = "";
	public String HINT = "";
	public String HINTHEADER = "Secret Word: ";
	public String SW = "";
}