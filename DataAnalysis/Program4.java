//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Four
//
//  File Name:     Program4.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      10/28/2024
//
//  Java Version:  17.0.12
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 6
//
//  Description:   This class reads in the text file and creates a map of the data
//                 and then calls the YearInfo class to calculate the average price
//                 per month, the average price per year, and the highest and lowest
//                 prices per year. It then calls the GenerateFiles class to generate
//                 the text files.
//
//********************************************************************
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Program4 {
    Integer month;
    Integer year;
    Double gasPrice;
    Integer date;
    String[] columns;
    HashMap<Integer, HashMap<String, Double>> yearlyInfoMap = new HashMap<>();
    //***************************************************************
    //
    //  Method:       main
    //
    //  Description:  The main method of the program
    //                
    //  Parameters:   String array
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public static void main (String[] args)
    {
        Program4 obj = new Program4();
        obj.developerInfo();
        obj.readData();
        YearInfo yearInfo = new YearInfo(obj.yearlyInfoMap);
        yearInfo.createYearMonthPriceMap();
        yearInfo.yearlyAveragePrice();
        yearInfo.monthlyAveragePrice();
        yearInfo.highestAndLowestPricesPerYear();
        GenerateFiles generateFiles = new GenerateFiles(obj.yearlyInfoMap);
        generateFiles.createYearPriceMap();
        generateFiles.generateFiles();   
    }// End of main
    //***************************************************************
    //
    //  Method:       readData
    //
    //  Description:  This method reads the data from the text file
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void readData()
    {
        Scanner input;
        try {
            input = new Scanner(new File("Project4.txt"));
            while(input.hasNextLine())
            {
                // Text File Sample, 
                // Beginning April 5, 1993, and ending on August 26, 2013
                // 04-05-1993:1.068
                // 04-12-1993:1.079
                columns = input.nextLine().split("[:-]"); // This will split the line into 4 parts or "columns"
                // [04] [12] [1993] [1.079]
                year = Integer.parseInt(columns[2]);
                month = Integer.parseInt(columns[0]);
                date = Integer.parseInt(columns[1]);
                gasPrice = Double.parseDouble(columns[3]);
                if(!yearlyInfoMap.containsKey(year))
                {
                    yearlyInfoMap.put(year, new HashMap<>());
                }
                // Create a composite key for month and date
                String monthDateKey = month + "-" + date;
                // year -> monthDateKey -> gasPrice
                yearlyInfoMap.get(year).put(monthDateKey, gasPrice);   
            } // End of while
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } 
    } // End of readData
    //***************************************************************
    //
    //  Method:       developerInfo
    // 
    //  Description:  The developer information method of the program
	//                This method and comments must be included in all
	//                programming assignments.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo()
    {
       System.out.println("Name:   Silvia Asmat");
       System.out.println("Course:   COSC 4301 - Modern Programming");
       System.out.println("Program:  Four");
	   System.out.println("Due Date: 10/28/2024\n");

    } // End of the developerInfo method

}