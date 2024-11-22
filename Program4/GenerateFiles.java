//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Four
//
//  File Name:     GenerateFiles.java
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
//  Description:   This class generates text files that list the date, 
//                 month, year, and prices sorted from the lowest price to the highest
//                 and from the highest price to the lowest.
//
//********************************************************************
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class GenerateFiles {
    HashMap<Integer, HashMap<String, Double>> yearlyInfoMap = new HashMap<>();
    HashMap<String, Double> fullDatePriceMap = new HashMap<>();
    //***************************************************************
    //
    //  Method:       GenerateFiles
    //
    //  Description:  Constructor
    //
    //  Parameters:   HashMap<Integer, HashMap<String, Double>>
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public GenerateFiles(HashMap<Integer, HashMap<String, Double>> yearlyInfoMap) 
    {
        this.yearlyInfoMap = yearlyInfoMap;
    }
    //***************************************************************
    //
    //  Method:       createYearPriceMap
    //
    //  Description:  This method will combine the month and date and year into a 
    //                single key with the price being the value. This simplifies
    //                yearlyInfoMap to a list of prices.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void createYearPriceMap()
    {
        for (Integer year : yearlyInfoMap.keySet()) 
        {
            HashMap<String, Double> monthDateMap = yearlyInfoMap.get(year);
            for (String monthDateKey : monthDateMap.keySet()) 
            {
                Double gasPrice = monthDateMap.get(monthDateKey);
                fullDatePriceMap.put(monthDateKey + "-" + year, gasPrice);
            }
        }
    }
    //***************************************************************
    //
    //  Method:       generateFiles
    //
    //  Description:  This method will generate two text files. One will list the date,
    //                month, year, and prices sorted from the lowest price to the highest.
    //                The other will list the date, month, year, and prices sorted from the
    //                highest price to the lowest. Uses Comparator to sort the prices.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void generateFiles() 
    {
        List<Map.Entry<String, Double>> priceList = new ArrayList<>(fullDatePriceMap.entrySet());
        // Sort prices from lowest to highest
        Collections.sort(priceList, Comparator.comparing(Map.Entry::getValue));
        writeToFile(priceList, "prices_low_to_high.txt");

        // Sort prices from highest to lowest
        Collections.sort(priceList, (a, b) -> b.getValue().compareTo(a.getValue()));
        writeToFile(priceList, "prices_high_to_low.txt");
    }
    //***************************************************************
    //
    //  Method:       writeToFile
    //
    //  Description:  This method will write the sorted prices to a text file.
    //
    //  Parameters:   List<Map.Entry<String, Double>>, String
    //  
    //  Returns:      N/A
    //
    //**************************************************************
    private void writeToFile(List<Map.Entry<String, Double>> priceList, String fileName) 
    {
        try (FileWriter writer = new FileWriter(fileName)) 
        {
            for (Map.Entry<String, Double> entry : priceList) 
            {
                writer.write(String.format("%-15s $%.3f%n", entry.getKey(), entry.getValue()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
