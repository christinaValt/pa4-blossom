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

public class LogWriter {

    // Unnecessary attribute ? 
    private String fileName;

    /**
     * Default constructor
     */
    public LogWriter() {

    }
//
    /**
     * @param fileNameIn
     * Constructor based off file name provided by user / main driver code
     */
    public LogWriter(String fileNameIn) {
        this.fileName = fileNameIn; 
    }

    // Method to create and add to txt file
    // This file will store all changes made to flight objects
    /**
     * @param userActionsIn
     * Write user action given from driver code as new string to log file
     */
    public void writeChanges(String userActionsIn)  {
        try {
            // Create a FileWriter object  
            // FileWriter log = new FileWriter("ChangesInFlightLog.txt", true); 
            FileWriter log = new FileWriter(fileName + ".txt", true); 

            // Write changes into the log txt file
            log.write(userActionsIn + "\n");

            // Close the log FileWriter
            log.close(); 
            
        } catch (IOException e) {
            System.out.println("Rip, log file not created successfully :(");
        }
    } // Log writer method
    
}
