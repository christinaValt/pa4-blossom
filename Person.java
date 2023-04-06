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

// Imports as needed
import java.util.HashMap;

// This class will extend to the Customer and Employee classes
// It keeps the attributes of name and last name as general for both objects
public abstract class Person {

    // Attributes
    private String name;
    private String lastName;
    private String dateOfBirth;
    private String username;
    private String password; 
    private double moneyAvailable;
    private double totalSaved;
    private int flightsPurchased;
    private boolean minerAirMembership;
    // This HashMap will store tickets by ticket number as key, and a ticket object as value
    private HashMap <Integer, Ticket> flightTicketsPurchasedList;

    // Constructors

    // Default constructor
    /**
     * Default constructor
     */
    public Person() {

    }

    // Constructor with desired attributes 
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
    public Person(String nameIn, String lastNameIn, String dateOfBirthIn, String usernameIn, String passwordIn, double moneyAvailableIn, int flightsPurchasedIn, boolean minerAirMembershipIn) {
        this.name = nameIn;
        this.lastName = lastNameIn;
        this.dateOfBirth = dateOfBirthIn; 
        this.username = usernameIn;
        this.password = passwordIn; 
        this.moneyAvailable = moneyAvailableIn; 
        this.flightsPurchased = flightsPurchasedIn;   
        this.minerAirMembership = minerAirMembershipIn; 
        this.flightTicketsPurchasedList = new HashMap<>();
    }

    // Getters and setters
    /**
     * @param nameIn
     * set name
     */
    public void setName(String nameIn) {
        this.name = nameIn;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param lastNameIn
     * set last name
     */
    public void setLastName(String lastNameIn) {
        this.lastName = lastNameIn;
    }

    /**
     * @return last name 
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @return date of birth
     */
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * @param dateOfBirth
     * set date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username
     * set username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password
     * set password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return amount of money available
     */
    public double getMoneyAvailable() {
        return this.moneyAvailable;
    }

    /**
     * @param moneyAvailable
     * set amount of money available
     */
    public void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    /**
     * @return number of flights purchased
     */
    public int getFlightsPurchased() {
        return this.flightsPurchased;
    }

    /**
     * @param flightsPurchased
     * set number of flights purchased
     */
    public void setFlightsPurchased(int flightsPurchased) {
        this.flightsPurchased = flightsPurchased;
    } 

    /**
     * @return list of flight tickets purchased
     */
    public HashMap <Integer, Ticket> getFlightTicketsPurchased() {
        return this.flightTicketsPurchasedList;
    }

    /**
     * @param flightTicketsPurchased
     * set list of flight tickets purchased
     */
    public void setFlightTicketsPurchased(HashMap <Integer, Ticket> flightTicketsPurchased) {
        this.flightTicketsPurchasedList = flightTicketsPurchased;
    } 

    /**
     * @return if ticket miner membership
     */
    public boolean isTicketMinerMembership() {
        return this.minerAirMembership;
    }

    /**
     * @param ticketMinerMembership
     * set if ticketminer membership
     */
    public void setTicketMinerMembership(boolean ticketMinerMembership) {
        this.minerAirMembership = ticketMinerMembership;
    }

    /**
     * @return list of flight tickets purchased
     */
    public HashMap<Integer, Ticket> getFlightTicketsPurchasedList() {
		return this.flightTicketsPurchasedList;
	}
//
    /**
     * @param flightTicketsPurchasedListIn
     * set flight tickets purchased list
     */
    public void setFlightTicketsPurchasedList(HashMap<Integer, Ticket> flightTicketsPurchasedListIn) {
        this.flightTicketsPurchasedList = flightTicketsPurchasedListIn; 
    }

    /**
     * @return total amount saved
     */
    public double getTotalSaved() {
        return this.totalSaved;
    }

    /**
     * @param totalSaved
     * set total amount saved
     */
    public void setTotalSaved(double totalSaved) {
        this.totalSaved = totalSaved;
    }
    
}