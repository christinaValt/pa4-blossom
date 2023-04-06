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

    This class implements a Singleton design pattern. There is only one instance of it made within the main driver code, 
    and it is later used to write any actions taken throughout the flow of the program. 

*/

// Imports as needed
import java.io.FileWriter; 
import java.io.IOException;
import java.util.HashMap;
//
public class CsvWriter {

    // Attribute to be passed with desired fileName
    private String fileName;

    /**
     * Default constructor
     */
    public CsvWriter() {

    }

    /**
     * @param fileNameIn
     * Object with filename from user / driver code
     */
    public CsvWriter(String fileNameIn) {
        this.fileName = fileNameIn; 
    }

    // Method to create and add to csv file
    // This file will store all changes made to flight objects
    /**
     * @param userFile
     * Writes to new CSV file based off new HashMap with user information
     */
    public void writeCustomerCsv(HashMap<String, Person> userFile)  {
        try {
            // Create a FileWriter object  
            // FileWriter log = new FileWriter("ChangesInFlightLog.txt", true); 
            FileWriter log = new FileWriter(fileName + ".csv", true); 

            log.write("First Name,Last Name,DOB,Money Available,Flights Purchased,MinerAir Membership,Username,Password" + "\n");
            for (HashMap.Entry<String, Person> userTicketFile : userFile.entrySet()) {
                log.write(userTicketFile.getValue().getName() + "," + userTicketFile.getValue().getLastName() + "," + userTicketFile.getValue().getDateOfBirth() + "," + userTicketFile.getValue().getMoneyAvailable() + "," + (userTicketFile.getValue().getFlightTicketsPurchasedList()).size() + "," + userTicketFile.getValue().isTicketMinerMembership() + "," + userTicketFile.getValue().getUsername() + "," + userTicketFile.getValue().getPassword() + "\n");
            } 

            // Close the log FileWriter
            log.close(); 
            
        } catch (IOException e) {
            System.out.println("Rip, csv file not created successfully :(");
        }
    } // Log writer method

    /**
     * @param flightFile
     * Writes to new CSV file based off new HashMap with flight information
     */
    public void writeFlightCsv(HashMap<Integer, Flight> flightFile)  {
        try {
            // Create a FileWriter object  
            // FileWriter log = new FileWriter("ChangesInFlightLog.txt", true); 
            FileWriter log = new FileWriter(fileName + ".csv", true); 

            log.write("ID,Flight Number,Origin Airport,Origin Code,Destination Airport,Destination Code,Departing Date,Departing Time,Duration,Distance,Time Zone Difference,Arrival Date,Arrival Time,Surcharge,Food Served,Route Cost,Miner Points,Total Seats,First Class Seats,Business Class Seats,Main Cabin Seats,First Class Price,Business Class Price,Main Cabin Price, Total First Class Revenue, Total Business Class Revenue, Total Main Cabin Revenue" + "\n"); 
            for (HashMap.Entry<Integer, Flight> flightFileElement : flightFile.entrySet()) {
                log.write(flightFileElement.getKey() + "," + flightFileElement.getValue().getFlightNumber() + "," + flightFileElement.getValue().getAirport().getOriginAirport() + "," + flightFileElement.getValue().getAirport().getOriginAirportCode() + "," + flightFileElement.getValue().getAirport().getDestinationAirport() + "," + flightFileElement.getValue().getAirport().getDestinationAirportCode() + "," + flightFileElement.getValue().getDepartureDate() + "," + flightFileElement.getValue().getDepartureTime() + "," + 
                flightFileElement.getValue().getDuration() + "," + flightFileElement.getValue().getDistance() + "," + flightFileElement.getValue().getAirport().getTimeZoneDifference() + "," + flightFileElement.getValue().getArrivalDate() + "," + flightFileElement.getValue().getArrivalTime() + "," + flightFileElement.getValue().getSurcharge() + "," + flightFileElement.getValue().isFoodServed() + "," + flightFileElement.getValue().getRouteCost() + "," + flightFileElement.getValue().getMinerPoints() + "," + flightFileElement.getValue().getTotalSeats() + 
                "," + flightFileElement.getValue().getFirstClassSeats() + "," + flightFileElement.getValue().getBusinessClassSeats() + "," + flightFileElement.getValue().getMainCabinSeats() + ","
                + flightFileElement.getValue().getFirstClassPrice() + "," + flightFileElement.getValue().getBusinessClassPrice() + "," + flightFileElement.getValue().getMainCabinPrice() + "," +
                flightFileElement.getValue().getFirstClassProfit() + "," + flightFileElement.getValue().getBusinessClassProfit() + "," + flightFileElement.getValue().getMainCabinProfit()
                + "," + flightFileElement.getValue().getTotalSeatsProfit() + "\n"); 
            }

            // Close the log FileWriter
            log.close(); 
            
        } catch (IOException e) {
            System.out.println("Rip, csv file not created successfully :(");
        }
    } // Log writer method
    
}
