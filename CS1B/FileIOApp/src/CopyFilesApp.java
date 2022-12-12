// ----------------------------------------------------------------------------------------------------------

// @version 1.0 04-15-2018

// @author  TP@FH Computer Science Department

//  File name:  ParseTextFile.java

//  Program purpose: This program is to parse a text file that contains Product data in the form:

//                                serial~version~price. After the parsing it will instantiate appropriate Product

//                                objects and store them in an array of Product objects.

//                                You may assume the text file is perfectly formatted with the right data

//                                 and it contains the correct number of objects as expected by the program.

//  Revision history:

//   Date                  Programmer               Change ID   Description

//   04/14/18            John Doe                     1276           Initial implementation

// ----------------------------------------------------------------------------------------------------------
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import javax.swing.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
public class CopyFilesApp{
      public static void main(String[] args) {
         
      }
}

class TVModels {
   //assuming map already stores the information of each TV
   TreeMap<Integer, ArrayList<String>> map = new TreeMap<Integer, ArrayList<String>>();
   
   public void hasFeatures() {
      String input = "";
      ArrayList<String> userSelections = new ArrayList<String>();
      ArrayList<Integer> compatibleYears = new ArrayList<Integer>();
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Please List the features you would like? Please separate features by commas");
      try {
      input = reader.readLine();
      } catch(IOException e) {
         e.printStackTrace();
      }
      String[] temp = input.split(",");
      for(String add: temp) {
         userSelections.add(add);
      }
      for(Entry<Integer, ArrayList<String>> entry: map.entrySet()) {
         boolean hasFeatures = true;
         if (entry != null) {
            for(String list: userSelections) {
               if(!entry.getValue().contains(list)) {
                  hasFeatures = false;
                  break;
               }
            }
            if(hasFeatures) {
               compatibleYears.add(entry.getKey());
            }
         }
      }
      if(compatibleYears != null) {
         System.out.println("Here's what we found: ");
         for(int years: compatibleYears) {
            System.out.println(years);
         }
      }
      else {
         System.out.println("We're sorry! we were unable to find any matching TV models. Please refine your search and try again.");
      }
   }
}