# beginning of class Student definition -------------------------
class Student:
   # class ("static") attributes and intended constants
   DEFAULT_NAME = "zz-error"
   DEFAULT_POINTS = 0
   MAX_POINTS = 30
   SORT_BY_FIRST = 88
   SORT_BY_LAST = 98
   SORT_BY_POINTS = 108
   sort_key = 2

   # initializer ("constructor") method -------------------
   def __init__(self,
                last=DEFAULT_NAME,
                first=DEFAULT_NAME,
                points=DEFAULT_POINTS):
      # instance attributes
      if (not self.set_last_name(last)):
         self.last_name = Student.DEFAULT_NAME
      if (not self.set_first_name(first)):
         self.first_name = Student.DEFAULT_NAME
      if (not self.set_points(points)):
         self.total_points = Student.DEFAULT_POINTS

   # mutator ("set") methods -------------------------------
   def set_last_name(self, last):
      if not self.valid_string(last):
         return False
      # else
      self.last_name = last
      return True

   def set_first_name(self, first):
      if not self.valid_string(first):
         return False
      # else
      self.first_name = first
      return True

   def set_points(self, points):
      if not self.valid_points(points):
         return False
      # else
      self.total_points = points
      return True

   # accessor ("get") methods -------------------------------
   def get_last_name(self):
      return self.last_name

   def get_first_name(self):
      return self.first_name

   def get_total_points(self):
      return self.total_points

   # output method  ----------------------------------------
   def display(self, client_intro_str="--- STUDENT DATA ---"):
      print(client_intro_str + str(self))

   # standard python stringizer ------------------------
   def __str__(self):
      return self.to_string()

   # instance helpers -------------------------------
   def to_string(self, optional_title=" ---------- "):
      ret_str = ((optional_title
                  + "\n    name: {}, {}"
                  + "\n    total points: {}.").
                 format(self.last_name, self.first_name,
                        self.total_points))
      return ret_str

   # static/class methods -----------------------------------
   @staticmethod
   def valid_string(test_str):
      if (len(test_str) > 0) and test_str[0].isalpha():
         return True;
      return False

   @classmethod
   def valid_points(cls, test_points):
      if 0 <= test_points <= cls.MAX_POINTS:
         return False
      else:
         return True

   @classmethod
   def compare_two_students(cls, first_stud, second_stud):
      """ comparison done on parameters' last names """
      return cls.compare_strings_ignore_case(
         first_stud.last_name, second_stud.last_name)

   @staticmethod
   def compare_strings_ignore_case(first_string, second_string):
      """ returns -1 if first < second, lexicographically,
         +1 if first > second, and 0 if same
         this particular version based on last name only
         (case insensitive) """

      fst_upper = first_string.upper()
      scnd_upper = second_string.upper()
      if fst_upper < scnd_upper:
         return -1
      # else if
      if fst_upper > scnd_upper:
         return 1
      # else
      return 0

   @classmethod
   def set_sort_key(cls, key):
      if not (key == cls.SORT_BY_FIRST or key == cls.SORT_BY_LAST or key ==
              cls.SORT_BY_POINTS):
         cls.sort_key = cls.SORT_BY_LAST
         return False
      cls.sort_key = key
      return True

   @classmethod
   def get_sort_key(cls):
      return cls.sort_key

   @classmethod
   def compare_two_students(cls, first_stud, second_stud):
      if(cls.sort_key == cls.SORT_BY_FIRST):
         return cls.compare_strings_ignore_case(first_stud.get_first_name(),
                                         second_stud.get_first_name())
      elif(cls.sort_key == cls.SORT_BY_LAST):
         return cls.compare_strings_ignore_case(first_stud.get_last_name(),
                                         second_stud.get_last_name())
      elif(cls.sort_key == cls.SORT_BY_POINTS):
         return first_stud.get_total_points() - second_stud.get_total_points()

# beginning of class StudentArrayUtilities definition ---------------
class StudentArrayUtilities:
   @classmethod
   def print_array(cls, stud_array,
                   optional_title="--- The Students -----------:\n"):
      print(cls.to_string(stud_array, optional_title))

   @classmethod
   def array_sort(cls, data, array_size):
      for k in range(array_size):
         if not cls.float_largest_to_top(data, array_size - k):
            return

            # class stringizers ----------------------------------

   @staticmethod
   def to_string(stud_array,
                 optional_title="--- The Students -----------:\n"):
      ret_val = optional_title + "\n"
      for student in stud_array:
         ret_val = ret_val + str(student) + "\n"
      print(ret_val)

   @staticmethod
   def float_largest_to_top(data, array_size):

      changed = False

      # notice we stop at array_size - 2 because of expr. k + 1 in loop
      for k in range(array_size - 1):
         if Student.compare_two_students(data[k], data[k + 1]) > 0:
            data[k], data[k + 1] = data[k + 1], data[k]
            changed = True

      return changed

   @classmethod
   def get_median_destructive(cls, array, array_size):
      min_array_length = 1
      divide_array = 2
      middle_element = 0
      median_score = 0
      score_one_position = 0
      score_two_position = 0
      original_sort_key = Student.get_sort_key()
      Student.set_sort_key(Student.SORT_BY_POINTS)
      StudentArrayUtilities.array_sort(array, array_size)
      if (array_size < min_array_length):
         median_score = 0
      elif (array_size == min_array_length):
         score_one_position = array[0]
         median_score = score_one_position.get_total_points()
      if(array_size % divide_array == 0):
         middle_element = int(array_size / divide_array)
         score_one_position = array[middle_element-1]
         score_two_position = array[middle_element]
         median_score = int((score_one_position.get_total_points() +
                         score_two_position.get_total_points())/divide_array)
      elif(array_size % divide_array != 0):
         middle_element = int((array_size - min_array_length) / divide_array)
         score_one_position = array[middle_element]
         median_score = score_one_position.get_total_points()
      Student.set_sort_key(original_sort_key)
      return median_score




# client --------------------------------------------

# instantiate some students, one with and illegal name ...
my_students_even = \
   [
      Student("parry", "assiyah", 350),
      Student("pitt", "brennan", 142),
      Student("atherton", "arthur", 472),
      Student("davlia", "blaine", 403),
      Student("hook", "zeenat", 430),
      Student("cousins", "renee", 134),
      Student("martin", "conal", 287),
      Student("dunlap", "marlie", 188),
      Student("perkins", "ivo", 470),
      Student("mcmanus", "paisley", 482),
      Student("jaramillo", "anayah", 168),
      Student("bell", "danniella", 50),
      Student("lim", "ella-may", 215),
      Student("forrest", "ezekiel", 58),
      Student("hatfield", "justin", 183),
      Student("gilmour", "kamal", 62)
   ]

my_students_odd = \
   [
      Student("parry", "assiyah", 350),
      Student("pitt", "brennan", 142),
      Student("atherton", "arthur", 472),
      Student("davlia", "blaine", 403),
      Student("hook", "zeenat", 430),
      Student("cousins", "renee", 134),
      Student("martin", "conal", 287),
      Student("dunlap", "marlie", 188),
      Student("perkins", "ivo", 470),
      Student("mcmanus", "paisley", 482),
      Student("jaramillo", "anayah", 168),
      Student("bell", "danniella", 23),
      Student("lim", "ella-may", 215),
      Student("forrest", "ezekiel", 58),
      Student("hatfield", "justin", 183),
   ]

my_students_single = \
   [
      Student("chen", "bryant", 499)
   ]
array_size = len(my_students_even)

StudentArrayUtilities.to_string(my_students_even, "Before default sort ("
                                                  "even): ")
StudentArrayUtilities.array_sort(my_students_even, array_size)
StudentArrayUtilities.to_string(my_students_even, "After default sort (even): ")
Student.set_sort_key(Student.SORT_BY_FIRST)
StudentArrayUtilities.array_sort(my_students_even, array_size)
StudentArrayUtilities.to_string(my_students_even, "After sort by FIRST: ")
Student.set_sort_key(Student.SORT_BY_POINTS)
StudentArrayUtilities.array_sort(my_students_even, array_size)
StudentArrayUtilities.to_string(my_students_even, "After sort by POINTS: ")
Student.set_sort_key(Student.SORT_BY_FIRST)
print("Median of even class=", StudentArrayUtilities.get_median_destructive(
      my_students_even, array_size))
if(Student.get_sort_key() == Student.SORT_BY_FIRST):
   print("Successfully preserved sort key.")
else:
   print("Sort key was not preserved!")

array_size = len(my_students_odd)
print("Median of odd class =", StudentArrayUtilities.get_median_destructive(
      my_students_odd, array_size))
array_size = len(my_students_single)
print("Median of small class =", StudentArrayUtilities.get_median_destructive(
      my_students_single, array_size))