ans = 'Y'       # (upper case)
errors = 6
num_tries = 5



count = 0

# note uppercase 'Y'
if ( (ans == 'Y' and errors < 5)
     or num_tries < 10 ):
   count += 1

print(count)