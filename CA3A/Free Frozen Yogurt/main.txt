# ---------------- SOURCE ----------------------------------------
run = True
reward_points = 0
while (run):
  user_choice = str(
     input("Menu:\nP (process Purchase)\nS (Shut down)\n\nYour choice: "))
  if (user_choice[0].lower() == "s"):
     print("Goodbye!")
     break
  elif (user_choice[0].lower() == "p"):
     print("You have {0} rewards points in your balance. \n".format(
        reward_points))
     if (reward_points >= 7):
        free_yogurt = str(input("You have a free frozen yogurt available! "
                                "Would you like to redeem this? It will cost  "
                                "7 stamps. (Y or N)\n"))
        if (free_yogurt[0].lower() == "y"):
           reward_points -= 7
           print("Free yogurt redeemed! You now have {0} stamps.".format(
              reward_points))
           continue
        elif (free_yogurt[0].lower() != "n"):
           print("Error! Please enter Y or N")
           continue
        else:
           print("Continuing to normal transaction...\n")
     num_transactions = input(
        "How many frozen yogurts would you like to buy? ")
     if (num_transactions.isdigit() == False or (
        num_transactions.isdigit() == True and int(num_transactions) <= 0)):
        print("Error! Please enter a positive number.")
        continue
     else:
        reward_points += int(num_transactions)
        print("You just earned {0} stamps and have a total of {1} to "
              "use.".format(num_transactions, reward_points))
     print("\n\n")
  else:
     print("Error! Please enter a P or S.")

""" --------------- RUN 1-----------------
C:\Users\james\PycharmProjects\Free Frozen Yogurt\venv\Scripts\python.exe"
"C:/Users/james/PycharmProjects/Free Frozen Yogurt/main.py
Menu:
P (process Purchase)
S (Shut down)

Your choice: purchase
You have 0 rewards points in your balance.

How many frozen yogurts would you like to buy? 1
You just earned 1 stamps and have a total of 1 to use.



Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 1 rewards points in your balance.

How many frozen yogurts would you like to buy? 2
You just earned 2 stamps and have a total of 3 to use.



Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 3 rewards points in your balance.

How many frozen yogurts would you like to buy? 3
You just earned 3 stamps and have a total of 6 to use.



Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 6 rewards points in your balance.

How many frozen yogurts would you like to buy? 4
You just earned 4 stamps and have a total of 10 to use.



Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 10 rewards points in your balance.

You have a free frozen yogurt available! Would you like to redeem this? It will cost  7 stamps. (Y or N)
no
Continuing to normal transaction...

How many frozen yogurts would you like to buy? 5
You just earned 5 stamps and have a total of 15 to use.



Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 15 rewards points in your balance.

You have a free frozen yogurt available! Would you like to redeem this? It will cost  7 stamps. (Y or N)
n
Continuing to normal transaction...

How many frozen yogurts would you like to buy? 6
You just earned 6 stamps and have a total of 21 to use.



Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 21 rewards points in your balance.

You have a free frozen yogurt available! Would you like to redeem this? It will cost  7 stamps. (Y or N)
yeah budday
Free yogurt redeemed! You now have 14 stamps.
Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 14 rewards points in your balance.

You have a free frozen yogurt available! Would you like to redeem this? It will cost  7 stamps. (Y or N)
y
Free yogurt redeemed! You now have 7 stamps.
Menu:
P (process Purchase)
S (Shut down)

Your choice: shut down
Goodbye!

Process finished with exit code 0

-------------------------------------- """

""" --------------- RUN 2 -----------------
"C:\Users\james\PycharmProjects\Free Frozen Yogurt\venv\Scripts\python.exe" "C:/Users/james/PycharmProjects/Free Frozen Yogurt/main.py"
Menu:
P (process Purchase)
S (Shut down)

Your choice: what are my choices?
Error! Please enter a P or S.
Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 0 rewards points in your balance. 

How many frozen yogurts would you like to buy? what is a number?
Error! Please enter a positive number.
Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 0 rewards points in your balance. 

How many frozen yogurts would you like to buy? 0
Error! Please enter a positive number.
Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 0 rewards points in your balance. 

How many frozen yogurts would you like to buy? 20
You just earned 20 stamps and have a total of 20 to use.



Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 20 rewards points in your balance. 

You have a free frozen yogurt available! Would you like to redeem this? It will cost  7 stamps. (Y or N)
maybe?
Error! Please enter Y or N
Menu:
P (process Purchase)
S (Shut down)

Your choice: p
You have 20 rewards points in your balance. 

You have a free frozen yogurt available! Would you like to redeem this? It will cost  7 stamps. (Y or N)
y
Free yogurt redeemed! You now have 13 stamps.
Menu:
P (process Purchase)
S (Shut down)

Your choice: s
Goodbye!

Process finished with exit code 0


-------------------------------------- """



