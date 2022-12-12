//-------------------------------------------------------------------------------------
//@version1.0 07-28-2020
//@author James Glassford
//File name: MLSListingsApp.java
//Program purpose: This program was created to list properties for sale in a GUI environment
//as well as be able to sort the listings based on type and price
//Revision History:
// Date           Programmer        Change ID   Description
// 07/20/2020     James Glassford   1           Created MLSListingsApp and MLSListingsView classes
// 07/22/2020     James Glassford   2           Finished implementing GUI components
// 07/25/2020     James Glassford   3           Completed all Property classes/subclasses, began work on PropertyList 
// 07/26/2020     James Glassford   4           Completed work on PropertyList, small tweaks to other classes                                               
// 07/28/2020     James Glassford   5           Final check to make sure program works as intended    
//
//-------------------------------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import javax.swing.*;

//-------------------------------------------------------------------------------------

public class MLSListingsApp
{
   public static void main(String[] args)
   {
      PropertyList propertyList = new PropertyList();
      propertyList.initialize();
      EventQueue.invokeLater((new Runnable()
      {
         public void run()
         {
            MLSListingsView mlsView = new MLSListingsView();
            mlsView.setProperties(propertyList);
            mlsView.setVisible(true);
         }
      }));
   }
}

// -------------------------------------------------------------------------------------

class PropertyList
{
   private Property head;
   private static final String fileLocation = "C:\\Users\\james\\eclipse-workspace\\MLSListingsApp\\listings.txt";
   private String header = "Address \t\t\t Price \t Year \tOther info\n";

   // default constructor
   public PropertyList()
   {
      head = null;
   }

   private void insert(Property property)
   {
      property.setNext(head);
      head = property;
   }

   // Goes through text file and creates Properties based on whether it's a condo
   // or sfh
   public void initialize()
   {
      Path textFile = Paths.get(fileLocation);
      BufferedReader reader = null;
      String line = null;
      try
      {
         reader = Files.newBufferedReader(textFile, StandardCharsets.US_ASCII);
         while ((line = reader.readLine()) != null)
         {
            String[] input = line.split(";");
            switch (input[0].toLowerCase())
            {
            case "sfh":
               insert(new SingleFamilyHouse(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]),
                     Integer.parseInt(input[4])));
               break;
            case "condo":
               insert(new Condo(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]),
                     Double.parseDouble(input[4])));
            }
         }
      } catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   // returns all properties when "show all" is selected
   public String getAllProperties()
   {
      Property list = head;
      String readyToPrint = header;
      while (list != null)
      {
         readyToPrint = readyToPrint + list.toString() + "\n";
         list = list.getNext();
      }
      return (readyToPrint);
   }

   // sorts linked list by condos and returns them
   public String getCondo()
   {
      Property list = head;
      String readyToPrint = header;
      while (list != null)
      {
         if (list instanceof Condo)
         {
            readyToPrint = readyToPrint + list.toString() + "\n";
         }
         list = list.getNext();
      }
      return readyToPrint;
   }

   // sorts linked list by sfh and returns the list
   public String getSingleFamilyHouse()
   {
      Property list = head;
      String readyToPrint = header;
      while (list != null)
      {
         if (list instanceof SingleFamilyHouse)
         {
            readyToPrint = readyToPrint + list.toString() + "\n";
         }
         list = list.getNext();
      }
      return readyToPrint;
   }

   // takes a min and max value and sorts based on that input, returns property
   // listings that are within the bounds
   public String searchByPriceRange(double min, double max)
   {
      Property list = head;
      String readyToPrint = header;
      while (list != null)
      {
         if (list.getPrice() > min && list.getPrice() < max)
         {
            readyToPrint = readyToPrint + list.toString() + "\n";
         }
         list = list.getNext();
      }
      return readyToPrint;
   }
}

// -------------------------------------------------------------------------------------

class MLSListingsView extends JFrame
{

   private static final int viewWidth = 720;
   private static final int viewHeight = 450;
   private static final int listingsRows = 20;
   private static final int listingsColumns = 60;

   private PropertyList propertyList;
   private double min;
   private double max;

   private JTextArea listings;
   private JButton showAllButton;
   private JLabel searchProperty;
   private JComboBox propertySpecs;
   private JButton go;
   private FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
   private JButton SFH;
   private JButton condoButton;
   private JButton clear;
   private String[] searchParameters =
   { "Under 400K", "400K - <600K", "600K - <800K", "800K - <1M", "1M or more" };

   // sets up the JFrame and all GUI components
   public MLSListingsView()
   {
      setTitle("MLSListings");
      setSize(MLSListingsView.viewWidth, MLSListingsView.viewHeight);
      setLocationRelativeTo(null);
      JPanel searchPanel = new JPanel();
      JPanel displayPanel = new JPanel();
      JPanel actionPanel = new JPanel();

      searchProperty = new JLabel("Search property:");
      propertySpecs = new JComboBox<>(searchParameters);
      go = new JButton("Go");

      listings = new JTextArea(MLSListingsView.listingsRows, MLSListingsView.listingsColumns);
      listings.setBorder(BorderFactory.createEtchedBorder());
      displayPanel.add(listings);

      searchPanel.setLayout(layout);
      searchPanel.add(searchProperty);
      searchPanel.add(propertySpecs);
      searchPanel.add(go);

      showAllButton = new JButton("Show all");
      actionPanel.add(showAllButton);
      SFH = new JButton("Show SFH");
      actionPanel.add(SFH);
      condoButton = new JButton("Show Condo");
      actionPanel.add(condoButton);
      clear = new JButton("Clear");
      actionPanel.add(clear);

      add(searchPanel, BorderLayout.NORTH);
      add(displayPanel, BorderLayout.CENTER);
      add(actionPanel, BorderLayout.SOUTH);

      // various listeners for all GUI components
      go.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            listings.setText(propertyList.searchByPriceRange(min, max));
         }
      });
      showAllButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            listings.setText(propertyList.getAllProperties());
         }
      });

      SFH.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            listings.setText(propertyList.getSingleFamilyHouse());
         }
      });

      condoButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            listings.setText(propertyList.getCondo());
         }
      });

      clear.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            listings.setText("");
         }
      });

      propertySpecs.addItemListener(new ItemListener()
      {
         public void itemStateChanged(ItemEvent event)
         {
            Object change = event.getItem();
            String test = change.toString();
            switch (test)
            {
            case "Under 400K":
               min = 0.00;
               max = 399999.99;
               break;
            case "400K - <600K":
               min = 400000.00;
               max = 599999.99;
               break;
            case "600K - <800K":
               min = 600000.00;
               max = 799999.99;
               break;
            case "800K - <1M":
               min = 800000.00;
               max = 999999.99;
               break;
            case "1M or more":
               min = 1000000.00;
               max = 9999999999.99;
            }
         }
      });

      addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            System.exit(0);
         }
      });
   }

   // sets propertyList to property parameter
   public void setProperties(PropertyList property)
   {
      propertyList = property;
   }

}

// -------------------------------------------------------------------------------------

class Property
{
   private String address;
   private int offeredPrice;
   private int year;
   private Property next;

   // default constructor
   public Property()
   {
      address = "12345 El Monte Road, Los Altos Hills, CA 94022";
      offeredPrice = 25;
      year = 1957;
      next = null;
   }

   // non-default constructor
   public Property(String address, int price, int year)
   {
      this.address = address;
      this.offeredPrice = price;
      this.year = year;
      this.next = null;
   }

   // setters / getters
   public String getAddress()
   {
      return address;
   }

   public int getPrice()
   {
      return offeredPrice;
   }

   public int getYear()
   {
      return year;
   }

   public void setAddress(String newAddress)
   {
      address = newAddress;
   }

   public void setPrice(int newPrice)
   {
      offeredPrice = newPrice;
   }

   public void setYear(int newYear)
   {
      year = newYear;
   }

   // returns property informaiton as a String
   public String toString()
   {
      return String.format("%-10s \t$%d.00 \t %d \t", address, offeredPrice, year);
   }

   // compares address awnd year of two properties
   public boolean equals(Property propOne, Property propTwo)
   {
      return (propOne.getAddress() == propTwo.getAddress() && propOne.getYear() == propTwo.getYear());
   }

   // more setters / getters
   public Property getNext()
   {
      return next;
   }

   public void setNext(Property nextProp)
   {
      next = nextProp;
   }
}

// -------------------------------------------------------------------------------------

class SingleFamilyHouse extends Property
{
   private int backyardArea;

   public SingleFamilyHouse()
   {
      super();
      backyardArea = 0;
   }

   // non-default constructor
   public SingleFamilyHouse(String address, int price, int year, int area)
   {
      super(address, price, year);
      this.backyardArea = area;
   }

   // setters / getters
   public int getArea()
   {
      return backyardArea;
   }

   public void setArea(int newArea)
   {
      backyardArea = newArea;
   }

   // takes parent class' toString and adds its own info to the end
   public String toString()
   {
      return (super.toString() + String.format("%d (sqft)", backyardArea));
   }

   // compares two property's areas on top of parent class' equals
   public boolean equals(SingleFamilyHouse propOne, SingleFamilyHouse propTwo)
   {
      return (super.equals(propOne, propTwo) && propOne.getArea() == propTwo.getArea());
   }
}

// -------------------------------------------------------------------------------------

class Condo extends Property
{
   private double hoaFee;

   public Condo()
   {
      super();
      hoaFee = 175.00;
   }

   // non-default constructor
   public Condo(String address, int price, int year, double fee)
   {
      super(address, price, year);
      this.hoaFee = fee;
   }

   // setters / getters
   public double getFee()
   {
      return hoaFee;
   }

   public void setFee(double newFee)
   {
      hoaFee = newFee;
   }

   // takes parent class' toString and adds its own info to the end
   public String toString()
   {
      return (super.toString() + String.format("HOA fee: $%.2f", hoaFee));
   }

   // compares two property's areas on top of parent class' equals
   public boolean equals(Condo propOne, Condo propTwo)
   {
      return (super.equals(propOne, propTwo) && propOne.getFee() == propTwo.getFee());
   }
}
