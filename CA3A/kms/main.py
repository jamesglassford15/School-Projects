def doIWantToKMS(endMySuffering):
    if(endMySuffering):
        print("Yes you do! End it you fucking pussy")
    else:
        print("You live another day you sack of shit")

user_input = str(input("Do you feel like killing yourself g?"))
endIt = True
if (user_input.lower() == "yes"):
    endIt = True
elif (user_input.lower() == "no"):
    endIt = False
doIWantToKMS(endIt)