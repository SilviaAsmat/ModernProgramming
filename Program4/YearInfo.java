//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Four
//
//  File Name:     YearInfo.java
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
//  Description:   This class contains the YearInfo class which will
//                 calculate the average price per month, the average
//                 price per year, and the highest and lowest prices
//                 per year.
//
//********************************************************************
import java.util.HashMap;
import java.util.Comparator;

public class YearInfo {
    HashMap<Integer, HashMap<String, Double>> yearMonthDatePriceMap = new HashMap<>();
    HashMap<Integer, HashMap<String, Double>> yearMonthPriceMap = new HashMap<>();
    String month;
    double avgPrice;
    //***************************************************************
    //
    //  Method:       YearInfo
    //
    //  Description:  Constructor
    //
    //  Parameters:   int, double
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public YearInfo(HashMap<Integer, HashMap<String, Double>> yearlyInfo) 
    {
        this.yearMonthDatePriceMap = yearlyInfo;
    }
    //***************************************************************
    //
    //  Method:       createYearMonthPriceMap
    //
    //  Description:  This method will remove the date from month key into
    //                a new map to simplify the data.
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void createYearMonthPriceMap() 
    {
        for (Integer year : yearMonthDatePriceMap.keySet()) 
        {
            for (String monthDate : yearMonthDatePriceMap.get(year).keySet()) 
            {
                String month = monthDate.split("-")[0];
                double price = yearMonthDatePriceMap.get(year).get(monthDate);

                yearMonthPriceMap.putIfAbsent(year, new HashMap<>());
                yearMonthPriceMap.get(year).put(month, 
                yearMonthPriceMap.get(year).getOrDefault(month, 0.0) + price);
            }
        }
    }
    //***************************************************************
    //
    //  Method:       monthlyAveragePrice
    //
    //  Description:  This method will calculate and display the 
    //                average price per month
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void monthlyAveragePrice() 
    {
        System.out.println("Monthly Average Price");
        System.out.printf("%-10s %-10s %-10s%n", "Month", "Year", "Avg Price");
        for (Integer year : yearMonthPriceMap.keySet()) 
        {
            yearMonthPriceMap.get(year).keySet().stream()
                .sorted(Comparator.comparingInt(m -> Integer.parseInt(m)))
                .forEach(month -> {
                    double total = 0;
                    int count = 0;
                    for (String monthDate : yearMonthDatePriceMap.get(year).keySet()) 
                    {
                        if (monthDate.startsWith(month + "-")) {
                            total += yearMonthDatePriceMap.get(year).get(monthDate);
                            count++;
                        }
                    }
                    double average = total / count;
                    System.out.printf("%-10s %-10d $%-10.3f%n", month, year, average);
                });
        }
    } 
    //***************************************************************
    // 
    //  Method:       yearlyAveragePrice
    //
    //  Description:  This method will calculate and display the
    //                average price per year
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void yearlyAveragePrice() 
    {
        System.out.println("Yearly Average Price");
        System.out.printf("%-10s %-10s%n", "Year", "Avg Price");
        for (Integer year : yearMonthDatePriceMap.keySet()) 
        {
            double sum = 0;
            int count = 0;
            for (Double price : yearMonthDatePriceMap.get(year).values()) 
            {
                sum += price;
                count++;
            }
            double average = sum / count;
            System.out.printf("%-10d $%-10.3f%n", year, average);
        }
    }
    //***************************************************************
    //
    //  Method:       highestAndLowestPricesPerYear
    //
    //  Description:  This method will calculate and display the highest
    //                and lowest prices per year
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void highestAndLowestPricesPerYear() 
    {
        System.out.println("Highest and Lowest Prices Per Year");
        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n", 
        "Year", "Lowest Date", "Lowest Price", "Highest Date", "Highest Price");
        for (Integer year : yearMonthDatePriceMap.keySet())
        {
            double highestPrice = Double.MIN_VALUE;
            double lowestPrice = Double.MAX_VALUE;
            String highestMonth = "";
            String lowestMonth = "";
            for (String month : yearMonthDatePriceMap.get(year).keySet()) 
            {
                double price = yearMonthDatePriceMap.get(year).get(month);
                if (price > highestPrice) 
                {
                    highestPrice = price;
                    highestMonth = month;
                }
                if (price < lowestPrice) 
                {
                    lowestPrice = price;
                    lowestMonth = month;
                }
            }
            System.out.printf("%-10d %-15s $%-14.3f %-15s $%-14.3f%n",
             year, lowestMonth, lowestPrice, highestMonth, highestPrice);
        }
    }
}
