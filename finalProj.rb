# CS Final 
class WORDGAME
  def init()
  end
  def intro()
    puts("This game is a secret word guessing game. Enjoy and good luck!")
  end
  def play(String word)
  end
  def guessCheck(String g)
  end
  def update(String word, String cH, char nG)
  end
  def random(String file)
    count = 0
    returnVal = ""
  end
end

def main
  test = WORDGAME.new()
  test.intro
  test.random(dict.txt)
end
main()
