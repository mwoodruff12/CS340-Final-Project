# CS Final 

# global vars??
GUESSES = ""
HINT =""
SW = ""

class WORDGAME
  def init()
    @_guesses = ""
    @_hint =""
    @_secretWord = ""
  end


  def intro()
    puts("This game is a secret word guessing game. Enjoy and good luck!")
  end


  # TO DO TRAVIS
  def getRandom(file)
    returnVal = ""
    words = Array.new()
    file = File.foreach("dict.txt") { |line| words << line }
    puts file
    count = words.size()
    randomVal = rand(0..count)
    randomWord = words[randomVal]
    file.close
    return randomWord
  end

  def playOneGame(secretWord)
    @_secretWord = secretWord

    guessLeft = 8
    length = secretWord.length

    for i in 0..(length - 1) do 
      @_hint += "-"
    end

    puts("Hint: " + @_hint)
    puts("Your guesses: " + @_guesses)
    puts("Guesses left: " + guessLeft)

    # create var that will hold empty string
    update = ""

    #creates a variable and sets it to the return
    check = readGuess(@_guesses);

    if (@_secretWord.include?(@_guesses[0] == true))
      puts "Correct!"
      # update = updateHint(secretWord, HINT, check)
      puts("Your Guesses: "+ @_guesses)
    else
      puts("Incorrect")
      guessesLeft = guessesLeft - 1
    end
    

    while (@_hint.include?("-")) do
      # outputs the current hint, how many guesses are left, and their total guesses
      puts("Secret Word: " + @_)
      puts("Guesses left: " + guessesLeft)
      puts("Your Guesses: "+ @_guesses)
      
      # calls the readguess method to get the next guess
      check = readGuess(@_guesses);
      

      # checks to see if the current guess is not in the word.
      if @_secretWord.include?(@_guesses[0, @_guesses.length - 1] == False)
        puts "Incorrect"
        guessesLeft = guessesLeft - 1
      # else outputs that they were correct and updates the hint.
      else
        puts("Correct!")
        update = updateHint(@_secretWord, @_hint, check);
      end

      
      # if statement that checks to see if the player has used all of their guesses.
      if (guessesLeft == 0)
      
        #if so then it outputs the secret word and returns to the run program
        puts("Secret Word was: " + @_secretWord);
        return guessesLeft;
      end
    end

    return guessesLeft

  end

  # readguess method, takes in all their total guesses and reads in their next guess
  def readGuess(guessLetter)

    # hold user current guess
    print("Current Guess:")
    currentGuess = gets.chomp

    # raises the users guess to be a capital letter
    currentGuess = currentGuess.upcase
    
    # turns the string into a character
    ch = currentGuess[0]
    
    # checks to see if the length of the guess is longer than 1
    if (currentGuess.length > 1)

      # if so outputs that the guess was too long and recalls the readguess method passing in the players total guesses
      print("Type a single letter from A-Z")
      
      # recursively calls the readGuess method and then returns a correct character
      return readGuess(@_guesses);
    # else if checks to see if the player has already entered in that guess.

    elsif(@_guesses.include?(currentGuess))
      # if so then outputs that they have already entered that guess and recalls the readguess method.
      print("You already entered that letter");
      
      # recursively calls the readGuess method and then returns a correct character
      return readGuess(@_guesses);

    # else, runs when the character has length 1 and hasn't been already guessed.
    else
      # adds it to the total guess and returns that character
      @_guesses = @_guesses + ch;
      return ch;
    end

  end

  def updateHint(secretWord, currentHint, nextGuess)
    #creates a count variable that keeps track of how many of that character is in the word.
    count = 0
    
    # for loop that runs through the characters of the secretWord.
    for i in 0..(secretWord.length - 1) do
      # checks to see if the character at the current location equals the character being guessed.
      if (secretWord[i] == nextGuess)
        #iterates the count
        count = count + 1
      end
    end
      
    
    # checks to see if count == 1, meaning that there is only of that character in the secretword.
    if (count == 1)
      # finds and sets the index of the character in the secret word
      indice = secretWord.index(nextGuess)
      
      # turns the current hint into a character array
      hintCharacters = currentHint.split("")
      
      # changes the value at the index to be the guess value
      hintCharacters[indice] = nextGuess
      
      # sets hint to be empty
      @_hint = ""
      
      # for loop that runs through the char array and adds them back to the hint string
      for i in 0..(hintCharacters.length - 1) do
        @_hint = @_hint + hintCharacters[i]
      end
      
      # returns the hint string.
      return @_hint
    
    #runs if there is more than 1 of the guessed character in the secret word.
    else
      # finds the first index
      indice = secretWord.index(nextGuess);
      
      # creates a character array from the current hint string
      hintCharacters = currentHint.split("");
      
      # changes that value at that index to the guessed character.
      hintCharacters[index] = nextGuess;
      
      # sets hint to be empty
      @_hint = "";
      
      # reads the values from the character array back to the hint string.
      for j in 0..(hintCharacters.length - 1) do
        @_hint = @_hint + hintCharacters[j]
      end
      
      # handles the remaining characters.
      while ((count - 1) != 0)
        # finds the next index
        indice = secretWord.index(nextGuess, indice + 1)
        
        #creates a character array of the values that are in the hint.
        hintCharacters = @_hint.split("")
        
        #sets the value at the index to be the guess.
        hintCharacters[indice] = nextGuess
        
        #sets hint to be an empty string
        @_hint = ""
        
        #reads the values from the array back to the hint string.
        for k in 0..(hintCharacters.length -1) do
          @_hint = @_hint + hintCharacters[k]
        end
        
        #deiterates the count.
        count = count - 1
      end
    end
      
      
    #returns the updated hint.
    return @_hint
  end

  def run()
    # calls and outputs the intro line of the code
    intro()
    
    #sets the variable to the name of the file being used.
    filename = "dict.txt";
    
    # calls and sets the getRandomWord method using the filename to a variable
    secretWord = getRandom(filename)
    
    #calls the playonegame method passing in the secret word
    game = playOneGame(@_secretWord)
    
    # if the game returns 0, it means the player lost.
    if (game == 0)
      #outputs that the player lost.
      print("You ran out of guesses, You lose! Secret Word was: " + @_secretWord)
    #else runs when the player won
    else 
      #outputs that the player won along with how many guesses they had left.
      print("You Win! You had " + game + " guesses remaining. Secret Word was: " + @_secretWord)
    end


    #creates a variable that is set to the entered string after asking the user if they would like to play agian.
    print("Play again? Enter YES to play again, or NO to end code: ")
    text = gets.chomp
    
    # raises the string to be all uppercase.
    text = text.upcase;
    
    # while loop that runs while the entered text equals YES
    while (text == "YES")

      #double checks that text equals YES
      if (text == "YES")
        # calls getRandomWord and sets it to secretWord for the new game.
        @_secretWord = getRandom(filename)
        
        # resets hint to be empty
        @_hint = ""
        
        # clears the users previous guesses from their last game.
        @_guesses = ""
        
        # calls the play game method passing in the new secret word
        game = playOneGame(@_secretWord)  
        
        #looks at if the returned value is 0, if so then the player looses and it outputs what the secret word was.
        if (game == 0)
          print("You ran out of guesses, You lose! Secret Word was: " + secretWord);
        
        #else, runs if the player won. outputs that they won, how many guesses they had remaining, and what the secret word was.
        else 
          print("You Win! You had " + game + " guesses remaining. Secret Word was: " + secretWord);
        end

      end
      
      #reasks the user after their second game if they would like to play again.
      print("Play again? Enter YES to play again, or NO to end code: ")
      text = gets.chomp
      
      # raises the users entered yes or no to capital letters
      text = text.upcase;
    end
    # if the user entered no then it outputs that the code is ending.
    if (text == "NO")
      print("Ending Code.")
    end

  end 

end


def main
  
  test = WORDGAME.new()
  test.run()

end

main()
