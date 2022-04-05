# CS Final 
class WORDGAME
  def init()
  end
  def intro()
    puts("This game is a secret word guessing game. Enjoy and good luck!")
  end
  # def play(String word)
  # end
  # def guessCheck(String g)
  # end
  # def update(String word, String cH, char nG)
  # end
  def random(file)
    returnVal = ""
    words = Array.new()
    count = words.size()
    file = File.open("dict.txt", "r")

    file.close

  end


def main
  test = WORDGAME.new()
  test.intro
  filename = "dict.txt"
  test.random(filename)
end
end
