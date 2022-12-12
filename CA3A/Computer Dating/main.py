class DateProfile:
   MIN_ROMANCE_FINANCE = 1
   MAX_ROMANCE_FINANCE = 10
   MIN_NAME_LENGTH = 1
   GENDER_LENGTH = 1
   DEFAULT_GEND = "T"
   DEFAULT_SEARCH = "T"
   DEFAULT_ROMANCE = 1
   DEFAULT_FINANCE = 1
   DEFAULT_NAME = "John Doe"

   def __init__(self, gender=DEFAULT_GEND, search_gender=DEFAULT_SEARCH,
                romance=DEFAULT_ROMANCE, finance=DEFAULT_FINANCE,
                name=DEFAULT_NAME):
      if not (self.set_gender(gender)):
         print("Invalid input for user gender, resorting to default value.")
      if not (self.set_search(search_gender)):
         print("Invalid input for search gender, resorting to default value.")
      if not (self.set_romance(romance)):
         print("Invalid input for romance value, resorting to default value.")
      if not (self.set_finance(finance)):
         print("Invalid input for finance value, resorting to default value.")
      if not (self.set_name(name)):
         print("Invalid input for user name, resorting to default value.")

#getters
   def get_gender(self):
      return self.gender

   def get_search(self):
      return self.search

   def get_romance(self):
      return self.romance

   def get_finance(self):
      return self.finance

   def get_name(self):
      return self.name

#setters
   def set_gender(self, new_gender):
      if self.valid_gender(new_gender):
         self.gender = new_gender.upper()
         return True
      else:
         self.gender = self.DEFAULT_GEND
         return False

   def set_search(self, new_search):
      if self.valid_gender(new_search):
         self.search = new_search.upper()
         return True
      else:
         self.search = self.DEFAULT_SEARCH
         return False

   def set_romance(self, new_romance):
      if (self.valid_number(new_romance)):
         self.romance = new_romance
         return True
      else:
         self.romance = self.DEFAULT_ROMANCE
         return False

   def set_finance(self, new_finance):
      if (self.valid_number(new_finance)):
         self.finance = new_finance
         return True
      else:
         self.finance = self.DEFAULT_FINANCE
         return False

   def set_name(self, new_name):
      if (self.valid_string(new_name)):
         self.name = new_name
         return True
      else:
         self.name = self.DEFAULT_NAME
         return False

   def set_all(self, new_gender, new_search, new_romance, new_finance,
               new_name):
      self.gender = new_gender
      self.search = new_search
      self.romance = new_romance
      self.finance = new_finance
      self.name = new_name

   def set_defaults(self):
      self.gender = self.DEFAULT_GEND
      self.search = self.DEFAULT_SEARCH
      self.romance = self.DEFAULT_ROMANCE
      self.finance = self.DEFAULT_FINANCE
      self.name = self.DEFAULT_NAME

   def fit_value(self, partner):
      fit = 0
      if self.determine_gender_fit(partner) == 0:
         fit = "{0:.3f}".format(float(0))
      else:
         fit += self.determine_romance_fit(partner)
         fit += self.determine_finance_fit(partner)
         fit /= 2
         fit = "{0:.3f}".format(float(fit))
      return fit

   def determine_gender_fit(self, partner):
      if (self.get_search() == partner.get_gender() and self.get_gender() ==
         partner.get_search()):
         return 1
      else:
         return 0

   def determine_romance_fit(self, partner):
      lowest_possible_value = .1

      profile1_value = self.get_romance()
      profile2_value = partner.get_romance()
      difference = abs(profile1_value - profile2_value)
      fit = self.MAX_ROMANCE_FINANCE - difference
      fit = (fit - 1) * ((self.MAX_ROMANCE_FINANCE - lowest_possible_value) / (
         self.MAX_ROMANCE_FINANCE - 1))
      fit += lowest_possible_value
      fit = fit / self.MAX_ROMANCE_FINANCE
      return fit

   def determine_finance_fit(self, partner):
      lowest_possible_value = .1

      profile1_value = self.get_finance()
      profile2_value = partner.get_finance()
      diff = abs(profile1_value - profile2_value)
      fit = self.MAX_ROMANCE_FINANCE - diff
      fit = (fit - 1) * ((self.MAX_ROMANCE_FINANCE - lowest_possible_value) / (
         self.MAX_ROMANCE_FINANCE - 1))
      fit += lowest_possible_value
      fit = fit / self.MAX_ROMANCE_FINANCE
      return fit

   def __str__(self):
      self.to_string()

   def to_string(self):
      print("Date Profile:\n\t{4}({0}) searching for {1}, w/fin={3} and rom={"
            "2}."
            .format(self.get_gender(), self.get_search(), self.get_romance(),
                    self.get_finance(), self.get_name()))

   @classmethod
   def valid_string(cls, the_str):
      if (len(the_str) >= cls.MIN_NAME_LENGTH):
         return True
      else:
         return False

   @classmethod
   def valid_number(cls, the_val):
      if (the_val >= cls.MIN_ROMANCE_FINANCE and the_val <=
         cls.MAX_ROMANCE_FINANCE):
         return True
      else:
         return False

   @staticmethod
   def valid_gender(gen):
      if (gen.upper() == "M" or gen.upper() == "F"):
         return True
      else:
         return False


# line 88 is over the 80 character limit but this was the only way I could
# get the values on the right to line up successfully with no issues. Just
# putting "\t", which I had when I originally wrote the program, would result
# in some values being way too far to the left or to the right.
def display_two_profiles(profile1, profile2):
   print(
      f"{'Fit between {0} and {1}:'.format(profile1.get_name(), profile2.get_name()):<57} "
      + "\t\t" + f"{DateProfile.fit_value(profile1, profile2)}")


#setting up the applicants
applicant1 = DateProfile("M", "M", 10, 2, "Jaydn Chester")
applicant2 = DateProfile("M", "F", 6, 10, "Arvin Little")
applicant3 = DateProfile("M", "M", 4, 9, "Nicholas Bolton")
applicant4 = DateProfile("F", "M", 10, 6, "Renee Clements")

#putting DateProfiles into list in order to use for loops to print values
applicant_list = [applicant1, applicant2, applicant3, applicant4]
for profile in applicant_list:
   profile.to_string()

print("\n\n")

for profile1 in range(4):
   for profile2 in range(4):
      display_two_profiles(applicant_list[profile1], applicant_list[profile2])


#testing mutators
print("\n\n")
test_object_one = "Y"
test_object_two = "m"
if applicant1.set_gender(test_object_one):
   print(test_object_one+" accepted as gender")
else:
   print(test_object_one + " rejected as gender")

if(applicant1.set_search(test_object_two)):
   print(test_object_two + " accepted as search gender")
else:
   print(test_object_two + " rejected as search gender")