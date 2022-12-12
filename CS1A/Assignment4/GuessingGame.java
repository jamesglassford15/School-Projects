
// You may use the nested comments below as hints only. Remember, there is no one right or wrong way 
// to 'solve' this Assignment, so feel free to delete the comments if you find them distracting.

import java.util.*;

/**
 * CS1A, Assignment 4, "Guessing Game" <br>
 * Quarter: Spring 2020 <br>
 * THE CLASS DESCRIPTION GOES HERE <br>
 * THE DESCRIPTION OF HOW TO WIN MOST EFFICIENTLY GOES HERE <br>
 * 
 * @author James Glassford
 * @author Phillip Williams
 */
public class GuessingGame extends Object
{
    // List any instance variables, including constants, here.
    private static final int MAX_POSSIBLE_GUESS = 64; // This is a constant
    private static final int MIN_POSSIBLE_GUESS = -64;
    // Use an array instance variable to hold the guesses
    
    int[] guessArray = new int[8];
    int arrayCounter = 0;
    Random randomNumberGenerator = new Random();
    
    // If you need to get a number for the user to guess (randomly),
    // call the method below and it will create one for you.
    // The number may be as low as -64, and as high as 64
    // (This will be 129 separate numbers that the user might try to guess)
    // Of course, you will have to create a variable space to hold this number
    // when it is returned (for example, secretNumber)
    public int getRandomNumber()
    {
        int max = MAX_POSSIBLE_GUESS - MIN_POSSIBLE_GUESS;
        int zeroToMax = randomNumberGenerator.nextInt(max + 1);
        return zeroToMax + MIN_POSSIBLE_GUESS;
    }

    // You may want to create a method that will display the welcome message and
    // rules (e.g., welcome)
    
    public void welcome() {
       System.out.println("Welcome!");
       System.out.println("Rules: ");
       System.out.println("1) You have 8 guesses");
       System.out.println("2) If you guess the wrong number, a hint will be displayed");
       System.out.println("3) Your guess has to be between -64 and 64");
    }

    // You may want to create a method (e.g., isGuessNum) that will check to see
    // that the numbered entered is a whole
    // number, place it into a variable space (e.g., userGuess), and then handle all
    // the various options regarding that number: has it been guessed before?; is it
    // the secret number?; if it isn't the secret number store it in the proper
    // location
    // for cross checking; display messages regarding the number of guesses made,
    // incorrect guess, guesses remaining, secret number if all guesses used up,
    // error
    // handling, etc.
    
    public boolean isGuessNum(int userGuess, int secretNumber) {
       boolean temp = false;
       for (int j = 0; j < arrayCounter; j++) {
          if (userGuess == guessArray[j]) {
             System.out.println("You've guessed this before!");
             temp = false;
          }
          else if (userGuess == secretNumber) {
             System.out.println("You've guessed it! Good job buddy");
             ++arrayCounter;
             temp = true;
          }
          else if (userGuess < secretNumber) {
             System.out.println("Hint: The number is LARGER than you guessed");
             guessArray[arrayCounter] = userGuess;
             ++arrayCounter;
             temp = false;
          }
          else if (userGuess > secretNumber) {
             System.out.println("Hint: The number is SMALLER than you guessed");
             guessArray[arrayCounter] = userGuess;
             ++arrayCounter;
             temp = false;
          }
       }
       return temp;
    }


    // You may want to create a method to print the user's previous guesses
    
    public void printGuesses() {
       int remainingGuesses = 0;
       remainingGuesses = 8 - arrayCounter;
       System.out.println("Guesses so far: ");
       for (int l = 0; l < arrayCounter; l++) {
          System.out.print(guessArray[l]);
          }
       System.out.println("You have " + remainingGuesses + " guesses left");
       System.out.println("");
    }
    // You may want to create a method to store the user's guesses in the 8 elements
    // of the array
    public void addToArray ()
    {
       guessArray [arrayCounter] = userGuess;
    }

    // You will NEED a getInput method for getting integer input from the user (see
    // the
    // assignment instructions on applying the techniques of structured
    // (functional) decomposition)).
    
    public int getInput(int min, int max)
    {
       Scanner keyboard = new Scanner(System.in);
       boolean youHaveInt = false;
       do {
       System.out.println("Please enter a whole number between " + MIN_POSSIBLE_GUESS + " and " + MAX_POSSIBLE_GUESS);
       if (keyboard.hasNextInt())
       {
          if (keyboard.nextInt() > min || keyboard.nextInt() < max)
          {
         youHaveInt = true;
          } else
          {
             System.out.println("Please enter a number within the guess range!");
             youHaveInt = false;
          }
       } else
       {
          System.out.println("That is not a valid integer!");
          youHaveInt = false;
       }
       }
       while (youHaveInt = false);
       System.out.println("You guessed: " + keyboard.nextInt());
       return keyboard.nextInt();
    }

    public boolean outOfGuesses() {
       boolean temp = false;
             if (arrayCounter == 8) {
                temp = true;
             }
             else {
                temp = false;
             }
       return temp;
    }
    // You may want to create a boolean method to check that the user's guess is
    // within range (between a minimum and maximum)
    // and will return true if it is

    // You may want to create a boolean method to see if the number has been guessed
    // previously and will return true if it has

    // You may want to create a boolean method to see if the number matches the
    // secret number and will return true if it does or false if it doesn't

    // You may want to create a method to give a hint about the number guessed
    // (e.g., "My secret number is GREATER than " or "My secret number is LESS than
    // ")

    // You may want to create a method (e.g., playGame) that will check if the user
    // wants to play again, by calling the method for getting integer input from the
    // user,
    // (1 for 'yes', 0 for 'no') and incorporate the proper functionality depending
    // on the user's choice ( 1 or 0).
    
    public boolean willYouPlayAgain() 
    {
       boolean temp = true;
       boolean entered = false;
       int entry = 2;
       Scanner keyboard = new Scanner (System.in);
       do {
       System.out.println("1) Enter '1' to play again.");
       System.out.println("2) Enter '0' to quit.");
       if (keyboard.hasNextInt()) {
          entry = keyboard.nextInt();
       }
       else {
          System.out.println("Please enter 0 or 1!");
          entered = false;
       }
       if (entry == 0) {
          temp = false;
          entered = true;
          System.out.println("Exiting game...")
       }
       else if (entry == 1) {
          temp = true;
          entered = true;
       }
       else {
          System.out.println("Please enter 0 or 1!");
          entered = false;
       }
       }
       while (entered = false);
       return temp;
    }

    public void playGuessingGame()
    {
        int secretNumber = this.getRandomNumber(); // Feel free to move this into another method
        int userGuess=0;
        boolean playing = true;
        boolean noMoreGuesses = false;
        boolean playAgain = true;
        this.welcome();
        do {
        do {
        userGuess = this.getInput(MIN_POSSIBLE_GUESS, MAX_POSSIBLE_GUESS);
        playing = !this.isGuessNum(userGuess, secretNumber);
        }
        while (playing = true);
        noMoreGuesses = this.outOfGuesses();
        if (playing == true && noMoreGuesses == false) { 
        guessArray [arrayCounter] = userGuess;
        this.printGuesses();
        playing = !this.outOfGuesses();
        }
        while (playing=true);   
        }
          

        // You might create a loop here that will check numberGuesses 8 times and print
        // the guess number and call the
        // pertinent method (e.g., isGuessNum)

        // You might call and capture results from method (e.g., playGame) to see if
        // user wants to play again and if
        // so return it. Example: int playAgain = this.playGame();

     //   return; // if you want to end the game early & go directly back to main,
                // you can use a "return;" statement like this one (e.g., return playAgain;)
    }

}
