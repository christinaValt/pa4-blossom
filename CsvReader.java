/*
    April 7, 2023 (CASS Extended Deadline - Original: April 4, 2023)
    Programming Assignment 4
    Christina A. Valtierra - 80714931
    CS 3331 - Advanced Object-Oriented Programming
    Dr. Daniel Mejia

    Honesty statement:
    I completed this work entirely on
    my own without any outside sources including peers, experts, online sources, or the
    like. I only received assistance from the instructor, TA, or IA.  

    Lab description: 
    I have recently been hired to work for MinerAir, an airline company. I am asked to analyze a file and display
    information based off the content that allows for modifications and updates on the flight data. Additionally, there
    are some customers interested in the AirLine, for whom I am given a csv file as well with all their information.

    This new version of CSV takes into account the creation of different Person objects (Employee vs Customer)

*/

// Imports as needed
import java.util.HashMap; 
import java.util.Scanner;
import java.io.*;

// This class will focus on reading the two csv files given for analysis
// It contains two main methods, one for each file analysis plus an additional helper to create the header given each file
// This allows for avoiding the repetition of the same code execution more than once
// It creates a HashMap based on the content of each file (Customer or Flight Information)
// This class is implemented using a Factory Design Pattern, which creates Customer or Employee objects vs 
// International vs Domestic objects

//

public class CsvReader {

    private String fileName;

    /**
     * Default constructor
     */
    public CsvReader() {
    } // End of default CsvReader constructor

    /**
     * @param fileNameIn
     * Constructor for object with filename from user / driver code
     */
    public CsvReader(String fileNameIn) {
        this.fileName = fileNameIn;
    } // End of CsvReader constructor with fileName attribute

    /**
     * @return HashMap with Flight objects with information of each flight
     */
    public HashMap<Integer, Flight> readFlightCSV() {

        // Declare variables / objects to be used
        // Scanner to read file + HashMap to store Flight objects
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Rip, flight file not found correctly :(");
        }

        HashMap<Integer, Flight> flightMap = new HashMap<>();

        // Getting header from first line
        String[] header = fileScanner.nextLine().split(","); 

        // Store this information in a separate HashMap -> From fileHeaderMap method
        HashMap<String, Integer> mapHeader = fileHeaderMap(header);

        // Starting to read the file -> Store objects based off the index (Domestic vs International)
        while(fileScanner.hasNextLine()) { 
            // Similarly as above, we use the line we are looking at and storing it in an array
            String[] fileLine = fileScanner.nextLine().split(",");
    
            if ((fileLine[mapHeader.get("Type")]).equals("Domestic")) {
                Domestic newDomesticFlight = new Domestic(Integer.parseInt(fileLine[mapHeader.get("Flight Number")]), fileLine[mapHeader.get("Origin Airport")], fileLine[mapHeader.get("Origin Code")], Boolean.parseBoolean(fileLine[mapHeader.get("Origin Airport Lounge")]), Double.parseDouble(fileLine[mapHeader.get("Origin Airport Fee")]), fileLine[mapHeader.get("Origin Airport City")], fileLine[mapHeader.get("Origin Airport State")], fileLine[mapHeader.get("Origin Airport Country")], fileLine[mapHeader.get("Destination Airport")], fileLine[mapHeader.get("Destination Code")], Boolean.parseBoolean(fileLine[mapHeader.get("Destination Airport Lounge")]), Double.parseDouble(fileLine[mapHeader.get("Destination Airport Fee")]), fileLine[mapHeader.get("Destination Airport City")], fileLine[mapHeader.get("Destination Airport State")], fileLine[mapHeader.get("Destination Airport Country")], Integer.parseInt(fileLine[mapHeader.get("Time Zone Difference")]), fileLine[mapHeader.get("Departing Date")], fileLine[mapHeader.get("Departing Time")], Integer.parseInt(fileLine[mapHeader.get("Duration")]), Integer.parseInt(fileLine[mapHeader.get("Distance")]), fileLine[mapHeader.get("Arrival Date")], fileLine[mapHeader.get("Arrival Time")], Double.parseDouble(fileLine[mapHeader.get("Surcharge")]), Boolean.parseBoolean(fileLine[mapHeader.get("Food Served")]), Double.parseDouble(fileLine[mapHeader.get("Route Cost")]), Integer.parseInt(fileLine[mapHeader.get("Miner Points")]), Integer.parseInt(fileLine[mapHeader.get("Total Seats")]), Integer.parseInt(fileLine[mapHeader.get("First Class Seats")]), Integer.parseInt(fileLine[mapHeader.get("Business Class Seats")]), Integer.parseInt(fileLine[mapHeader.get("Main Cabin Seats")]), Double.parseDouble(fileLine[mapHeader.get("First Class Price")]), Double.parseDouble(fileLine[mapHeader.get("Business Class Price")]), Double.parseDouble(fileLine[mapHeader.get("Main Cabin Price")]));
                flightMap.put(Integer.parseInt(fileLine[mapHeader.get("ID")]), newDomesticFlight);
            }
            else if ((fileLine[mapHeader.get("Type")]).equals("International")) { 
                // International newInternationalFlight = new International(Integer.parseInt(fileLine[mapHeader.get("Flight Number")]), fileLine[mapHeader.get("Origin Airport")], fileLine[mapHeader.get("Origin Code")], fileLine[mapHeader.get("Destination Airport")], fileLine[mapHeader.get("Destination Code")], fileLine[mapHeader.get("Departing Date")], fileLine[mapHeader.get("Departing Time")], Integer.parseInt(fileLine[mapHeader.get("Duration")]), Integer.parseInt(fileLine[mapHeader.get("Distance")]), Integer.parseInt(fileLine[mapHeader.get("Time Zone Difference")]), fileLine[mapHeader.get("Arrival Date")], fileLine[mapHeader.get("Arrival Time")], Integer.parseInt(fileLine[mapHeader.get("Surcharge")]), Boolean.parseBoolean(fileLine[mapHeader.get("Food Served")]), Integer.parseInt(fileLine[mapHeader.get("Route Cost")]), Integer.parseInt(fileLine[mapHeader.get("Miner Points")]), Integer.parseInt(fileLine[mapHeader.get("Total Seats")]), Integer.parseInt(fileLine[mapHeader.get("First Class Seats")]), Integer.parseInt(fileLine[mapHeader.get("Business Class Seats")]), Integer.parseInt(fileLine[mapHeader.get("Main Cabin Seats")]), Integer.parseInt(fileLine[mapHeader.get("First Class Price")]), Integer.parseInt(fileLine[mapHeader.get("Business Class Price")]), Integer.parseInt(fileLine[mapHeader.get("Main Cabin Price")]));
                International newInternationalFlight = new International(Integer.parseInt(fileLine[mapHeader.get("Flight Number")]), fileLine[mapHeader.get("Origin Airport")], fileLine[mapHeader.get("Origin Code")], Boolean.parseBoolean(fileLine[mapHeader.get("Origin Airport Lounge")]), Double.parseDouble(fileLine[mapHeader.get("Origin Airport Fee")]), fileLine[mapHeader.get("Origin Airport City")], fileLine[mapHeader.get("Origin Airport State")], fileLine[mapHeader.get("Origin Airport Country")], fileLine[mapHeader.get("Destination Airport")], fileLine[mapHeader.get("Destination Code")], Boolean.parseBoolean(fileLine[mapHeader.get("Destination Airport Lounge")]), Double.parseDouble(fileLine[mapHeader.get("Destination Airport Fee")]), fileLine[mapHeader.get("Destination Airport City")], fileLine[mapHeader.get("Destination Airport State")], fileLine[mapHeader.get("Destination Airport Country")], Integer.parseInt(fileLine[mapHeader.get("Time Zone Difference")]), fileLine[mapHeader.get("Departing Date")], fileLine[mapHeader.get("Departing Time")], Integer.parseInt(fileLine[mapHeader.get("Duration")]), Integer.parseInt(fileLine[mapHeader.get("Distance")]), fileLine[mapHeader.get("Arrival Date")], fileLine[mapHeader.get("Arrival Time")], Double.parseDouble(fileLine[mapHeader.get("Surcharge")]), Boolean.parseBoolean(fileLine[mapHeader.get("Food Served")]), Double.parseDouble(fileLine[mapHeader.get("Route Cost")]), Integer.parseInt(fileLine[mapHeader.get("Miner Points")]), Integer.parseInt(fileLine[mapHeader.get("Total Seats")]), Integer.parseInt(fileLine[mapHeader.get("First Class Seats")]), Integer.parseInt(fileLine[mapHeader.get("Business Class Seats")]), Integer.parseInt(fileLine[mapHeader.get("Main Cabin Seats")]), Double.parseDouble(fileLine[mapHeader.get("First Class Price")]), Double.parseDouble(fileLine[mapHeader.get("Business Class Price")]), Double.parseDouble(fileLine[mapHeader.get("Main Cabin Price")]));
                flightMap.put(Integer.parseInt(fileLine[mapHeader.get("ID")]), newInternationalFlight);
            }
            
        } // End of while loop to store flight (domestic or international) objects in CustomerMap HashMap

        fileScanner.close();

        return flightMap;

    } // End of flight file reading + map creation

    /**
     * @return HashMap with Person objects from user file
     */
    public HashMap<String, Person> readCsvPerson() {
        // Declare variables / objects to be used
        // Scanner to read file + HashMap to store Customer objects
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Rip, user file not read correctly :(");
        }

        HashMap<String, Person> userMap = new HashMap<>();

        // Getting header from first line
        String[] header = fileScanner.nextLine().split(","); 

        // Store this information in a separate HashMap -> From fileHeaderMap method
        HashMap<String, Integer> mapHeader = fileHeaderMap(header);

        // Starting to read the file -> Store objects
        while(fileScanner.hasNextLine()) {
            // Similarly as above, we use the line we are looking at and storing it in an array
            String[] fileLine = fileScanner.nextLine().split(",");

            if ((fileLine[mapHeader.get("Role")]).equals("Customer")) {
                Customer customer = new Customer(fileLine[mapHeader.get("First Name")], fileLine[mapHeader.get("Last Name")], fileLine[mapHeader.get("DOB")], fileLine[mapHeader.get("Username")], fileLine[mapHeader.get("Password")], Double.parseDouble(fileLine[mapHeader.get("Money Available")]), Integer.parseInt(fileLine[mapHeader.get("Flights Purchased")]), Boolean.parseBoolean(fileLine[mapHeader.get("MinerAirlines Membership")]));
                userMap.put(fileLine[mapHeader.get("Username")], customer); 
            }
            else if ((fileLine[mapHeader.get("Role")]).equals("Employee")) {
                Employee employee = new Employee(fileLine[mapHeader.get("First Name")], fileLine[mapHeader.get("Last Name")], fileLine[mapHeader.get("DOB")], fileLine[mapHeader.get("Username")], fileLine[mapHeader.get("Password")], Double.parseDouble(fileLine[mapHeader.get("Money Available")]), Integer.parseInt(fileLine[mapHeader.get("Flights Purchased")]), Boolean.parseBoolean(fileLine[mapHeader.get("MinerAirlines Membership")]));
                userMap.put(fileLine[mapHeader.get("Username")], employee); 
            }
            
        } // End of while loop to store customer objects in CustomerMap HashMap

        fileScanner.close();

        return userMap; 

    } // End of customer file reading + map creation

    // This method returns a map with the header contents -> Find index (value) through content (key)
    /**
     * @param header
     * @return HashMap with header contents from file stored as content and index within array header
     */
    public HashMap<String, Integer> fileHeaderMap(String[] header) {
        // Create new map to return header contents
        HashMap<String, Integer> mapHeader = new HashMap<>();

        // Populate the HashMap we just created based off the header array content
        // This is individual for each file (Flight vs Users (Employee, Customer))
        for (int i = 0; i < header.length; i++) {
            mapHeader.put(header[i], i); 
        }

        return mapHeader; 
    }
    
} // End of CsvReader class



