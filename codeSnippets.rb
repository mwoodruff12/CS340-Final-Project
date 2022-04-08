def main()
  temp = Integer(gets)
  while (temp != 0)
    choice = case
    when temp > 32 && temp < 60 then puts("its nice outside")
    when temp >= 60 then puts("its warm outside")
    when temp < 32 then puts("its cold outside")
    end
    temp = Integer(gets)
  end
end
main()