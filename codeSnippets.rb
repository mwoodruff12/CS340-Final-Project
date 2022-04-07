def main()
  words = Array.new()
  file = File.foreach("dict.txt") { |line| words << line}
  count = words.size()
  randomVal = rand(0..count)
  puts randomVal - 1
  puts words[randomVal]
end
main()