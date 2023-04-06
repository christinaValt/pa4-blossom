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

    This gets attributes from Person class
    Also gets individual attributes from corresponding CSV file
*/

 public class Employee extends Person {

     private double amountSaved;

    // Default constructor
    /**
     * Default constructor
     */
    public Employee() {

    }
//
    // Constructor with given attributes
    /**
     * @param nameIn
     * @param lastNameIn
     * @param dateOfBirthIn
     * @param usernameIn
     * @param passwordIn
     * @param moneyAvailableIn
     * @param flightsPurchasedIn
     * @param minerAirMembershipIn
     * Object creation based off file contents
     */
    public Employee(String nameIn, String lastNameIn, String dateOfBirthIn, String usernameIn, String passwordIn, double moneyAvailableIn, int flightsPurchasedIn, boolean minerAirMembershipIn) {
        super(nameIn, lastNameIn, dateOfBirthIn, usernameIn, passwordIn, moneyAvailableIn, flightsPurchasedIn, minerAirMembershipIn);
        this.amountSaved = 0.00; 
    }

    /**
     * @return amount saved
     */
    public double getAmountSaved() {
        return this.amountSaved;
    }

    /**
     * @param amountSaved
     * sets amount saved
     */
    public void setAmountSaved(double amountSaved) {
        this.amountSaved = amountSaved;
    }


}