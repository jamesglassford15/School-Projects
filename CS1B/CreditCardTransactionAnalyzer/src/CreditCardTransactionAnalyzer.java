//-------------------------------------------------------------------------------------
//@version1.0 07-08-2020
//@author James Glassford
//File name: CreditCardTransactionAnalyzer.java
//Program purpose: This program was created to handle transaction on a credit card
//DISCLAIMER: Writing this while heavily fatigued, so apologies for any typos

//Revision History:
// Date           Programmer        Change ID   Description
// 07/13/2020     James Glassford   1           Created CreditCardTransactionAnalyzer, Date, and Transaction classes
// 07/14/2020     James Glassford   2           Created Transaction subclasses and Customer class
// 07/16/2020     James Glassford   3           Various changes and improvements 
// 07/18/2020     James Glassford   4           Final check to make sure program works as intended                                               
//                                             
//-------------------------------------------------------------------------------------

import java.io.*;
import java.nio.file.*;

import java.nio.charset.*;

//------------------------------------------------------------------------------------
public class CreditCardTransactionAnalyzer
{
   public static void main(String[] args)
   {
      Customer customer = new Customer("James Glassford", "4060 6870 3654 0287", 2836754.56, 1000);
      customer.readTransactions();
      customer.reportTransactions();
      customer.reportCharges();
      customer.reportRewardSummary();
   }
}

// ------------------------------------------------------------------------------------
class Date
{
   private int month;
   private int day;
   private int year;

   public Date()
   {
      month = 1;
      day = 1;
      year = 1970;
   }

   // non-default constructor
   public Date(int month, int day, int year)
   {
      if (month > 0 && month < 13)
      {
         this.month = month;
      } else
      {
         month = 1;
      }
      if (day > 0 && day < 32)
      {
         this.day = day;
      } else
      {
         day = 1;
      }
      if (year >= 1970)
      {
         this.year = year;
      } else
      {
         year = 1970;
      }
   }

   // toString method that prints out date in a readable format
   public String toString()
   {
      String output = String.format("%02d/%02d/%04d", month, day, year);
      return output;
   }

   // returns true if day, month and year match on the two dates; returns false if
   // any discrepancies
   public boolean equals(Date dateOne, Date dateTwo)
   {
      return (dateOne.getYear() == dateTwo.getYear() && dateOne.getMonth() == dateTwo.getMonth()
            && dateOne.getDay() == dateTwo.getDay());
   }

   // accessor / mutator methods
   public int getDay()
   {
      return day;
   }

   public int getMonth()
   {
      return month;
   }

   public int getYear()
   {
      return year;
   }

   public void setDay(int newDay)
   {
      day = newDay;
   }

   public void setMonth(int newMonth)
   {
      month = newMonth;
   }

   public void setYear(int newYear)
   {
      year = newYear;
   }

   public Date clone(Date date) throws CloneNotSupportedException
   {
      return (Date) date.clone();
   }

   // checks if dates are equal; otherwise, compares and finds the discrepancy and
   // returns it
   public int compareTo(Date dateOne, Date dateTwo)
   {
      if (equals(dateOne, dateTwo))
      {
         return 0;
      } else if (dateOne.getYear() < dateTwo.getYear())
      {
         return 1;
      } else if (dateOne.getYear() > dateTwo.getYear())
      {
         return -1;
      } else if (dateOne.getMonth() < dateTwo.getMonth())
      {
         return 1;
      } else if (dateOne.getMonth() > dateTwo.getMonth())
      {
         return -1;
      } else if (dateOne.getDay() < dateTwo.getDay())
      {
         return 1;
      } else
      {
         return -1;
      }
   }
}

// ------------------------------------------------------------------------------------
abstract class Transaction
{
   protected int transID;
   protected Date date = null;
   protected double transAmount;

   public Transaction()
   {
      transID = 0;
      date = new Date();
      transAmount = 0.0;
   }

   // non-default constructor
   public Transaction(int transID, int mm, int dd, int yyyy, double transAmount)
   {
      this.transID = transID;
      date = new Date(mm, dd, yyyy);
      this.transAmount = transAmount;
   }

   // declaring abstract list method for use in subclasses
   abstract public String list();

   // overrides toString to print out the basic info of each transaction
   public String toString(Date date, int transactionID, double transAmount)
   {
      return date.toString() + "~" + transactionID + "~" + transAmount + "~";
   }

   // returns true if transaction IDs are identical
   public boolean equals(Transaction transactionOne, Transaction transactionTwo)
   {
      return (transactionOne.getID() == transactionTwo.getID());
   }

   // accessor / mutator methods
   public int getID()
   {
      return transID;
   }

   public Date getDate()
   {
      return date;
   }

   public double getAmount()
   {
      return transAmount;
   }

   public void setID(int newID)
   {
      this.transID = newID;
   }

   public void setDate(int month, int day, int year)
   {
      date.setMonth(month);
      date.setDay(day);
      date.setYear(year);

   }

   public void setAmount(double newAmt)
   {
      this.transAmount = newAmt;
   }

}

// ------------------------------------------------------------------------------------
class DepartmentStoreTransaction extends Transaction implements Rewardable
{
   private String deptName;
   private String returns;

   // method for listing department store transactions
   public String list()
   {
      return (date + "\t Department Store\t" + deptName + "\t\t $" + transAmount + " (return in " + returns + " days)");
   }

   public DepartmentStoreTransaction()
   {
      super();
      deptName = "no store selected";
      returns = "no store selected";
   }

   // non-default constructor
   public DepartmentStoreTransaction(int transID, int month, int day, int year, double transAmt, String deptName,
         String returns)
   {
      super(transID, month, day, year, transAmt);
      this.deptName = deptName;
      this.returns = returns;
   }

   // accessor / mutator methods
   public String getDept()
   {
      return deptName;
   }

   public String getReturns()
   {
      return returns;
   }

   public void setDept(String newDept)
   {
      deptName = newDept;
   }

   public void setReturns(String newReturn)
   {
      returns = newReturn;
   }

   public String toString()
   {
      return ("DS~" + super.toString(date, transID, transAmount) + "~" + deptName + "~" + returns);
   }

   // controls the amount of point rewarded per dollar spent, returns number of
   // points earned
   public double earnPoints(int transAmt)
   {
      return transAmt * 3;
   }
}

// ------------------------------------------------------------------------------------
class BankingTransaction extends Transaction
{
   private boolean transType;
   private double charge;

   public BankingTransaction()
   {
      super();
      transType = false;
      charge = 0.0;
   }

   // non-default constructor
   public BankingTransaction(int transID, int month, int day, int year, double transAmt, boolean transType,
         double charge)
   {
      super(transID, month, day, year, transAmt);
      this.transType = transType;
      this.charge = charge;
   }

   // tells whether transaction is through ATM or CASH. use of boolean due to what
   // was in the discussion posts.
   public String checkType()
   {
      if (transType)
      {
         return "ATM";
      } else
      {
         return "CASH";
      }
   }

   // accessor / mutator methods
   public boolean getTrans()
   {
      return transType;
   }

   public double getCharge()
   {
      return charge;
   }

   public void setTrans(boolean newTrans)
   {
      transType = newTrans;
   }

   public void setCharge(double newCharge)
   {
      charge = newCharge;
   }

   // overrides super class method
   public String toString()
   {
      return ("BK~" + super.toString(date, transID, transAmount) + "~" + checkType() + "~" + charge);
   }

   public boolean equals(Object objectOne, Object objectTwo)
   {
      return (objectOne == objectTwo);
   }

   // displays banking purchases
   public String list()
   {
      String temp = String.format("%.02f", transAmount);
      return (date + "\t Banking\t\t" + checkType() + "\t\t $" + temp);
   }

}

class GroceryTransaction extends Transaction implements Rewardable
{
   private String storeName;

   public GroceryTransaction()
   {
      super();
   }

   // non-default constructor
   public GroceryTransaction(int transID, int month, int day, int year, double transAmt, String storeName)
   {
      super(transID, month, day, year, transAmt);
      this.storeName = storeName;
   }

   // accessor/mutator methods
   public String getStore()
   {
      return storeName;
   }

   public void setStore(String newStore)
   {
      storeName = newStore;
   }

   public String list()
   {
      return (date + "\t Grocery\t\t" + storeName + "\t\t $" + transAmount);
   }

   // overrides super class method
   public String toString()
   {
      return ("GR~" + super.toString(date, transID, transAmount) + "~" + storeName);

   }

   // controls # of points earned, returns however many are earned
   public double earnPoints(int transAmt)
   {
      return transAmt * 5;
   }
}

// ------------------------------------------------------------------------------------
interface Rewardable
{
   public double earnPoints(int transAmt);
}

// ------------------------------------------------------------------------------------
class Customer
{

   private static int arraySize = 8;
   private String customerName;
   private String creditCard;
   private double totalBalance;
   private double rewardBalance;
   Transaction[] transactions;
   Transaction[] rewardables;
   private static final String file_location = "C:\\Users\\james\\eclipse-workspace\\Programming Assignment 2\\Transactions.txt";

   public Customer()
   {
      customerName = "John Doe";
      creditCard = "1111222233334444";
      totalBalance = 0.0;
      rewardBalance = 1000.0;
      transactions = new Transaction[arraySize];
      rewardables = new Transaction[arraySize];

   }

   // non-default constructor
   public Customer(String name, String cc, double total, int reward)
   {
      this.customerName = name;
      this.creditCard = cc;
      this.totalBalance = total;
      this.rewardBalance = reward;
      transactions = new Transaction[arraySize];
      rewardables = new Transaction[arraySize];
   }

   // reads and collects data by accessing the file and splitting by the "~" and
   // assigning the results to an array. Date is in its own array and read through
   // the same method. Checks for transaction type and assigns class accordingly
   public void readTransactions()
   {
      Path inputTextFile = Paths.get(file_location);
      BufferedReader reader = null;
      String line = null;
      int counter = 0;
      int rewardCounter = 0;
      try
      {
         reader = Files.newBufferedReader(inputTextFile, StandardCharsets.US_ASCII);
         while ((line = reader.readLine()) != null)
         {
            String[] input = line.split("~");
            int transID = Integer.parseInt(input[2]);
            String[] date = input[1].split("/");
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            double transAmt = Double.parseDouble(input[3]);
            if (input[0].equals("DS"))
            {
               transactions[counter] = new DepartmentStoreTransaction(transID, month, day, year, transAmt, input[4],
                     input[5]);
               counter++;
            } else if (input[0].equals("BK"))
            {
               boolean transType = true;
               if (input[4].equals("CASH"))
               {
                  transType = false;
               }
               double charge = Double.parseDouble(input[5]);
               transactions[counter] = new BankingTransaction(transID, month, day, year, transAmt, transType, charge);
               counter++;
            } else
            {

               transactions[counter] = new GroceryTransaction(transID, month, day, year, transAmt, input[4]);
               counter++;
            }
         }
         for (Transaction transaction : transactions)
         {
            if (transaction instanceof Rewardable)
            {
               rewardables[rewardCounter] = transaction;
               rewardCounter++;
            }
         }
      } catch (IOException e)
      {
         e.printStackTrace();
      }

   }

   // lists the transactions of the user using the list() methods

   public void reportTransactions()
   {
      System.out.println("\t\tTRANSACTION LISTING REPORT");
      System.out.println("");
      System.out.println("DATE\t\t TRANSACTION TYPE\t\t\t AMOUNT");
      System.out.println("--------------------------------------------------------------------------------");
      for (Transaction transaction : transactions)
      {
         System.out.println(transaction.list() + "\n");
      }
   }

   // reports total charges the user has accumulated

   public void reportCharges()
   {
      double totalCharges = 0.00;
      for (Transaction transaction : transactions)
      {
         if (!(transaction instanceof Rewardable))
         {
            totalCharges += transaction.getAmount();
         }
      }
      String readyToPrint = String.format("%.02f", totalCharges);
      System.out.println("Total charges: $" + readyToPrint + "\n");
   }

   // tells user # of points saved, # of points earned from different store
   // purchases, and the total number of points they have to spend

   public void reportRewardSummary()
   {
      double deptCounter = 0.00;
      double groceryCounter = 0.00;
      double totalCount = 0.00;
      String[] cc = creditCard.split(" ");
      System.out.println("Rewards Summary for " + customerName + " " + cc[3] + "\n");
      System.out.println("Previous points balance:\t\t" + String.format("%.0f", rewardBalance) + "\n");
      for (Transaction reward : rewardables)
      {
         if (reward == null)
         {
            break;
         }
         String temp = reward.toString();
         String checkpoint[] = temp.split("~");
         if (checkpoint[0].equals("DS"))
         {
            deptCounter += Double.parseDouble(checkpoint[3]);
         } else
         {
            groceryCounter += Double.parseDouble(checkpoint[3]);
         }

      }
      System.out
            .println("+ Points earned on Department store purchases: \t\t" + String.format("%.0f", deptCounter) + "\n");
      System.out
            .println("+ Points earned on Grocery store purchases: \t\t" + String.format("%.0f", groceryCounter) + "\n");
      totalCount = deptCounter + groceryCounter + rewardBalance;
      System.out.println("----------------------------------------------------------------\n");
      System.out.println(" = Total points available for redemption:\t\t" + String.format("%.0f", totalCount) + "\n");
      System.out.println();
   }

}