key_chracter = ""
target_string = ""

def get_key_character():
   legal_input = False
   while(legal_input == False):
       key_character =  input(str("Please enter a SINGLE character to act as "
                              "key: "))
       if (len(key_character) == 1):
           return key_character

def get_string():
   legal_input = False
   while(legal_input == False):
       req_string = input(str("Please enter a phrase or sentence >= 4 and <= 500 characters:"))
       if(len(req_string) >= 4 and len(req_string) <= 500):
           return req_string

def mask_character(the_string, key_character):
   new_string = ""
   for target in range(len(the_string)):
       if the_string[target] == key_character:
           new_string += "*"
       else:
           new_string += the_string[target]
   print("\n\nString with key character, '{0}', masked: {1}".format(
       key_character,
                                                              new_string))

def remove_character(the_string, key_character):
   new_string = ""
   for target in range(len(the_string)):
       if the_string[target] != key_character:
           new_string += the_string[target]
   print("\n\nString with '{0}' removed: {1}".format(key_character,
                                                     new_string))

def count_key(the_string, key_character):
   counter = 0
   for target in range(len(the_string)):
       if the_string[target] == key_character:
           counter += 1
   print("\n\n# of occurrences of key character, '{0}': {1}".format(
       key_character, counter))

key_chracter = get_key_character()
target_string = get_string()
mask_character(target_string, key_chracter)
remove_character(target_string, key_chracter)
count_key(target_string, key_chracter)

""" --------------- RUN 1 -----------------
"C:\Users\james\PycharmProjects\Text Processing\venv\Scripts\python.exe" "C:/Users/james/PycharmProjects/Text Processing/main.py"
Please enter a SINGLE character to act as key: oierungre
Please enter a SINGLE character to act as key: 
Please enter a SINGLE character to act as key: p
Please enter a phrase or sentence >= 4 and <= 500 characters:p
Please enter a phrase or sentence >= 4 and <= 500 characters:pp
Please enter a phrase or sentence >= 4 and <= 500 characters:ppp
Please enter a phrase or sentence >= 4 and <= 500 characters:e5nugapoerigma-ew04g8ja3-e095hma45
h

String with key character, 'p', masked: e5nuga*oerigma-ew04g8ja3-e095hma45


String with 'p' removed: e5nugaoerigma-ew04g8ja3-e095hma45


# of occurrences of key character, 'p': 1

Process finished with exit code 0


-------------------------------------- """
""" --------------- RUN 2 -----------------
"C:\Users\james\PycharmProjects\Text Processing\venv\Scripts\python.exe" "C:/Users/james/PycharmProjects/Text Processing/main.py"
Please enter a SINGLE character to act as key: !
Please enter a phrase or sentence >= 4 and <= 500 characters:W$TYEH#$^#@$@!!!!5usrjd6jsdjsRTJSRTNDGTYKE%^UGHMNJEtujhsrbtgisurntg09w346t897ghbne46y$%^U&UIJYMNHW$U!!@#%@#T!


String with key character, '!', masked: W$TYEH#$^#@$@****5usrjd6jsdjsRTJSRTNDGTYKE%^UGHMNJEtujhsrbtgisurntg09w346t897ghbne46y$%^U&UIJYMNHW$U**@#%@#T*


String with '!' removed: W$TYEH#$^#@$@5usrjd6jsdjsRTJSRTNDGTYKE%^UGHMNJEtujhsrbtgisurntg09w346t897ghbne46y$%^U&UIJYMNHW$U@#%@#T


# of occurrences of key character, '!': 7

Process finished with exit code 0


-------------------------------------- """
""" --------------- RUN 3 -----------------
"C:\Users\james\PycharmProjects\Text Processing\venv\Scripts\python.exe" "C:/Users/james/PycharmProjects/Text Processing/main.py"
Please enter a SINGLE character to act as key: a
Please enter a phrase or sentence >= 4 and <= 500 characters:aaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaab


String with key character, 'a', masked: *************************b*********b


String with 'a' removed: bb


# of occurrences of key character, 'a': 34

Process finished with exit code 0


-------------------------------------- """
""" --------------- RUN 4 -----------------
"C:\Users\james\PycharmProjects\Text Processing\venv\Scripts\python.exe" "C:/Users/james/PycharmProjects/Text Processing/main.py"
Please enter a SINGLE character to act as key: a
Please enter a phrase or sentence >= 4 and <= 500 characters:For example, if the key character were 'a' and the string were "He who laughs last, laughs fast, faster, FASTEST." then the operation of replacing the 'a' by an asterisk would result in the new string: 'He who l*ughs l*st, l*ughs f*st, f*ster, FASTEST.'


String with key character, 'a', masked: For ex*mple, if the key ch*r*cter were '*' *nd the string were "He who l*ughs l*st, l*ughs f*st, f*ster, FASTEST." then the oper*tion of repl*cing the '*' by *n *sterisk would result in the new string: 'He who l*ughs l*st, l*ughs f*st, f*ster, FASTEST.'


String with 'a' removed: For exmple, if the key chrcter were '' nd the string were "He who lughs lst, lughs fst, fster, FASTEST." then the opertion of replcing the '' by n sterisk would result in the new string: 'He who l*ughs l*st, l*ughs f*st, f*ster, FASTEST.'


# of occurrences of key character, 'a': 15

Process finished with exit code 0


-------------------------------------- """

