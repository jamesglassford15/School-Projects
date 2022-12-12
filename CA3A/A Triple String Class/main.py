# ---------------- SOURCE ----------------------------------------
class TripleString:
   # Intended class constants
   MIN_LEN = 1
   MAX_LEN = 50
   DEFAULT_STRING = "(undefined)"

   # Default constructor
   def TripleString(self):
      self.set_string1(self.DEFAULT_STRING)
      self.set_string2(self.DEFAULT_STRING)
      self.set_string3(self.DEFAULT_STRING)

   # Constructor method
   def __init__(self, string1=DEFAULT_STRING, string2=DEFAULT_STRING,
                string3=DEFAULT_STRING):
      if self.valid_string(string1):
         self.set_string1(string1)
      else:
         self.set_string1(self.DEFAULT_STRING)
      if self.valid_string(string2):
         self.set_string2(string2)
      else:
         self.set_string2(self.DEFAULT_STRING)
      if self.valid_string(string3):
         self.set_string3(string3)
      else:
         self.set_string3(self.DEFAULT_STRING)

   # set methods
   def set_string1(self, new_string):
      if (self.valid_string(new_string)):
         self.string1 = new_string
         return True
      else:
         self.string1 = self.DEFAULT_STRING
         return False

   def set_string2(self, new_string):
      if (self.valid_string(new_string)):
         self.string2 = new_string
         return True

      else:
         self.string2 = self.DEFAULT_STRING
         return False

   def set_string3(self, new_string):
      if (self.valid_string(new_string)):
         self.string3 = new_string
         return True
      else:
         self.string3 = self.DEFAULT_STRING
         return False

   # Get methods
   def get_string1(self):
      return ("Requested string: " + self.string1 + "\n")

   def get_string2(self):
      return ("Requested string: " + self.string2 + "\n")

   def get_string3(self):
      return ("Requested string: " + self.string3 + "\n")

   # ToString method
   def to_string(self):
      print("Strings: '{0}', '{1}', '{2}'\n".format(self.string1, self.string2,
                                                    self.string3))

   # Helper methods
   def valid_string(self, the_str):
      if (len(the_str) <= self.MIN_LEN or len(the_str) >= self.MAX_LEN):
         return False
      else:
         return True


# --------------CLIENT--------------------------------------------------------

# Instantiating objects
triple_string_num_1 = TripleString()
triple_string_num_2 = TripleString("python's", "cool!")
triple_string_num_3 = TripleString("three", "random", "words")
triple_string_num_4 = TripleString("program", "works", "hopefully")

print("\n\n-------------------PRINTING STRING LISTS----------------")
triple_string_num_1.to_string()
triple_string_num_2.to_string()
triple_string_num_3.to_string()
triple_string_num_4.to_string()

# Mutating strings
triple_string_num_1.set_string3("get changed")
triple_string_num_2.set_string1("new string i guess")
triple_string_num_3.set_string2("i dont know what else to put")
triple_string_num_4.set_string1("random stuff to show program working")

print("\n\n-------------PRINTING WITH MUTATED STRINGS----------------")
triple_string_num_1.to_string()
triple_string_num_2.to_string()
triple_string_num_3.to_string()
triple_string_num_4.to_string()

print("\n\n---------------MUTATOR TESTS--------------------------------")
mutator_test_one = "will this work?"
mutator_test_two = "I want to create a string that will fail the second test " \
                   "but making one that's zero characters is kinda lame so " \
                   "here we go we're trying to get past 50 chracters I'm sure " \
                   "we're there by now"
print("-MUTATOR TEST ONE- \n Requested string change: '" +
      triple_string_num_4.string2 + "' to '" + mutator_test_one + "'")
if (triple_string_num_4.set_string2(mutator_test_one)):
   print("Worked! New string replaced the old string.\n")
else:
   print("Failed! string hasn't been changed.\n")

print("-MUTATOR TEST TWO- \n Requested string change: '" +
      triple_string_num_4.string3 + "' to '" + mutator_test_two + "'")
if (triple_string_num_4.set_string3(mutator_test_two)):
   print("Worked! New string replaced the old string.\n")
else:
   print("Failed! string hasn't been changed.\n")

print("\n\n-----------------ACCESSOR TESTS------------------------")
print("\nAccess of string test #1:")
print(triple_string_num_4.get_string1())
print("\nAccess of string test #2:")
print(triple_string_num_2.get_string3())
