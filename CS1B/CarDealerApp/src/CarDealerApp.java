//-------------------------------------------------------------------------------------
//@version1.0 07-08-2020
//@author James Glassford
//File name: CarDealerApp.java
//Program purpose: This program was created to run a car dealer app through the console
//Revision History:
// Date           Programmer        Change ID   Description
// 07/05/2020     James Glassford   1           Created CarDealerApp and Vehicle classes
// 07/06/2020     James Glassford   2           Created CarDealer class
// 07/06/2020     James Glassford   3           Various changes to the classes and methods 
//                                              in order to run more efficiently and be in 
//                                              line with the given program requirements
//-------------------------------------------------------------------------------------

import java.io.*;

//------------------------------------------------------------------------------------
public class CarDealerApp
{
   public static void main(String[] args)
   {
      CarDealer cd = null;
      cd = new CarDealer("East Palo Alto");
      cd.init();
      cd.run();
   }
}

// -------------------------------------------------------------------------------------
class Vehicle
{
   // instance fields
   private String make;
   private String model;
   private int year;
   private double price;

   // default constructor
   public Vehicle()
   {
      make = "MFG";
      model = "MOD";
      year = 1970;
      price = 0.0;
   }

   // non-default constructor
   public Vehicle(String make, String model, int year, double price)
   {
      this.make = make;
      this.model = model;
      this.year = year;
      this.price = price;
   }

   // ----------------------------accessor and mutator
   // methods-----------------------------
   public String getMake()
   {
      return make;
   }

   public String getModel()
   {
      return model;
   }

   public int getYear()
   {
      return year;
   }

   public double getPrice()
   {
      return price;
   }

   public void setMake(String newMake)
   {
      make = newMake;
   }

   public void setModel(String newModel)
   {
      model = newModel;
   }

   public void setYear(int newYear)
   {
      year = newYear;
   }

   public void setPrice(double newPrice)
   {
      price = newPrice;
   }
   // -------------------------------------------------------------------------------------

   // overriding super class methods
   public String toString()
   {
      return make + " " + model + "; " + year + "; $" + price;
   }

   public boolean equals(String vehicle1, String vehicle2)
   {
      return vehicle1.equals(vehicle2);
   }

}

// -------------------------------------------------------------------------------------

class CarDealer
{
   // Instance fields
   Vehicle[] vehicleList;
   private String location;
   private int vehicleCount;
   private static String brand = "Foothill Car Dealership";
   private static int maxNumber = 1024;
   boolean isDoneRunning = false;

   // initial welcome message
   public void startup()
   {
      System.out.println("Welcome to " + brand + " at " + location + ". \n");
      System.out.println("Loading vehicles from Database. Please wait... \n");
   }

   // calls startup and loads the car inventory
   public void init()
   {
      startup();
      String[] make =
      { "Toyota", "Ford", "Ford", "Ferrari", "BMW", "Rolls-Royce", "Porsche", "Cadillac", "Volkswagen", "Dodge" };
      String[] model =
      { "Camry", "Taurus", "Taurus", "FF Coupe", "325i", "Phantom Coupe", "911 Convertible", "ATS Sedan", "Window Bus",
            "Challenger R/T" };
      int[] year =
      { 2015, 2012, 2013, 2015, 2016, 2015, 2016, 2014, 1964, 2017 };
      double[] price =
      { 22158.95, 9566.99, 17899.00, 302320.99, 3789.88, 471175.00, 103925.00, 42955.00, 42500.00, 28851.00 };
      for (vehicleCount = 0; vehicleCount < make.length; vehicleCount++)
      {
         if (vehicleCount % 2 == 0)
         {
            vehicleList[vehicleCount] = new Vehicle();
            vehicleList[vehicleCount].setMake(make[vehicleCount]);
            vehicleList[vehicleCount].setModel(model[vehicleCount]);
            vehicleList[vehicleCount].setYear(year[vehicleCount]);
            vehicleList[vehicleCount].setPrice(price[vehicleCount]);
         } else
         {
            vehicleList[vehicleCount] = new Vehicle(make[vehicleCount], model[vehicleCount], year[vehicleCount],
                  price[vehicleCount]);
         }
      }
      System.out.println("Done. \n\n");
   }

   // default constructor
   public CarDealer()
   {
      vehicleList = new Vehicle[maxNumber];
      vehicleCount = 0;
      location = "The Lost City";
   }

   // non-default constructor
   public CarDealer(String location)
   {
      vehicleList = new Vehicle[maxNumber];
      vehicleCount = 0;
      this.location = location;
   }

   // displays the menu of options to the user
   private int menu()
   {
      int choice = 0;
      try
      {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("\t" + "MENU");
         System.out.println("1. View Vehicle Inventory");
         System.out.println("2. Search by make and model");
         System.out.println("3. Search by price");
         System.out.println("4. Quit" + "\n");
         System.out.println("\t\t" + "Enter your choice:");
         choice = Integer.parseInt(reader.readLine());
      } catch (Exception e)
      {
         e.printStackTrace();
      }
      return choice;
   }

   // prints the inventory of the car dealer to the user
   private void viewInventory()
   {
      int counter = 0;
      System.out.println("\n\t----------------------------------");
      System.out.print("\n\t|\tVEHICLE INVENTORY\t|\n");
      System.out.println("\n\t----------------------------------");
      System.out.println("\n\nMAKE & MODEL\t\tYEAR\tPRICE");
      System.out.println("\n----------------------------------------");
      for (Vehicle vehicle : vehicleList)
      {
         if (vehicleList[counter] == null)
         {
            break;
         }
         System.out.println(vehicle);
         System.out.println("");
         counter++;
      }
      System.out.println("\n");
   }

   // asks for make and model of a car, and searches for any similar vehicles
   private void searchMakeModel()
   {
      String make = null;
      String model = null;
      String readyToPrint = "";
      int success = 0;
      try
      {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("Who is the maker of the car?");
         make = reader.readLine();
         System.out.println("What is the model name of the car?");
         model = reader.readLine();
      } catch (Exception e)
      {
         e.printStackTrace();
      }
      for (Vehicle vehicle : vehicleList)
      {
         if (vehicle == null)
         {
            break;
         }
         if (make.equals(vehicle.getMake()) && model.equals(vehicle.getModel()))
         {
            readyToPrint = readyToPrint + vehicle + "\n";
            success++;
         }
      }
      numberFound(success);
      System.out.println(readyToPrint);

   }

   // asks for a minimum and maximum price and presents the user a selection of
   // cars between the bounds
   private void searchPriceRange()
   {
      double minPrice;
      double maxPrice;
      int success = 0;
      String readyToPrint = "";
      try
      {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("Please enter a minimum price:");
         minPrice = Integer.parseInt(reader.readLine());
         System.out.println("Please enter a maximum price:");
         maxPrice = Integer.parseInt(reader.readLine());
         for (Vehicle vehicle : vehicleList)
         {
            if (vehicle == null)
            {
               break;
            }
            if (minPrice <= vehicle.getPrice() && maxPrice >= vehicle.getPrice())
            {
               readyToPrint = readyToPrint + vehicle + "\n";
               success++;
            }
         }
         numberFound(success);
         System.out.println(readyToPrint);
      } catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   // ---------------------------- accessor and mutator
   // methods-----------------------------
   public void setBrand(String newBrand)
   {
      brand = newBrand;
   }

   public void setLocation(String newLocation)
   {
      location = newLocation;
   }

   public void setMax(int newNumber)
   {
      maxNumber = newNumber;
   }

   public void setNumber(int newNumber)
   {
      vehicleCount = newNumber;
   }
   // -------------------------------------------------------------------------------------

   private void numberFound(int count)
   {
      if (count == 0)
      {
         System.out.println("No such vehicles were found.\n ");
      } else if (count == 1)
      {
         System.out.println("Only one vehicle was found. \n");
      } else
      {
         System.out.println("Multiple vehicles were found. \n");
      }
   }

   // changes the boolean "isDoneRunning" to true
   private void quit()
   {
      System.out.println("Thank you for shopping with us at " + brand + " of " + location + "! Goodbye.");
      isDoneRunning = true;
   }

   // runs the menu program until the user decides to quit
   public void run()
   {
      do
      {
         switch (menu())
         {
         case 1:
            viewInventory();
            break;
         case 2:
            searchMakeModel();
            break;
         case 3:
            searchPriceRange();
            break;
         case 4:
            quit();
         }
      } while (isDoneRunning == false);
   }
}