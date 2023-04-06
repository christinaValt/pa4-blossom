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

*/


// This gets attributes from Person class
// Also gets individual attributes from corresponding CSV file
// Simulates a customer with log-in credentials (username and password)
// Also gets a certain amount of money available and amount of flights purchased

/*
 * List of commands for customer: 
 * a. Allow the person to purchase a ticket for the flight (1-8 seats per
transaction)
    i. Buy tickets to only one flight per transaction
    1. International flights have a surcharge per seat
2. List the price for each ticket level and users will select
their ticket choice (i.e., First Class, Business, Main
Cabin)
3. List the total surcharge for the transaction (if applicable)
4. Show the user the final price and ask them to confirm
their purchase before finalizing the transaction
    ii. Buy only one ticket type per transaction (i.e., First Class,
    Business, Main Cabin)
    iii. Verify that there are seats available
1. If a seat type is sold out, display “Sold Out” for that
choice
    iv. Provide the customer with a “ticket” (you could think of this as
    a receipt/itinerary) in which it has the quantity of tickets, total
    price for the purchase, and a confirmation number
1. One “ticket” per transaction (regardless of the quantity)
2. Each ticket will have the number of seats purchased in
that transaction
3. Each ticket will have the total price for the transaction
4. Each user will have a “ticket” for every transaction
 */
//
public class Customer extends Person {

    private double amountSaved;

    // Default constructor
    /**
     * Default constructor
     */
    public Customer() {

    }

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
     * Constructor for customer object with contents from file
     */
    public Customer(String nameIn, String lastNameIn, String dateOfBirthIn, String usernameIn, String passwordIn, double moneyAvailableIn, int flightsPurchasedIn, boolean minerAirMembershipIn) {
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