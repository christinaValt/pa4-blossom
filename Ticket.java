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

// This class will work to give the customer an individual "ticket" based off purchase
// This will depend on the flight chosen, ticket quantity, number of seats, and include a list of the tickets gotten
public class Ticket {
    private String username;
    private int flightIDChosen;
    private String ticketType;
    private int quantityOfTickets;
    private double totalPrice;
    private double totalPriceWithFees;
    private double totalPriceWithTaxes;
    private double totalPriceWithDiscount;
    private String confirmationNumber;
    private String ticketStatus;
    private Person ticketBuyer;
    private double minerAirlinesFee;
    private double securityFee;
    private double totalSaved;
    private double taxCharged;


    // Default constructor
    /**
     * Default constructor
     */
    public Ticket() {

    }

    // Constructor with given attributes
    /**
     * @param usernameIn
     * @param flightIDChosenIn
     * @param ticketTypeIn
     * @param quantityOfTicketsIn
     * @param totalPriceIn
     * @param totalPriceWithFeesIn
     * @param totalPriceWithTaxesIn
     * @param totalPriceWithDiscountIn
     * @param confirmationNumberIn
     * @param ticketBuyerIn
     * @param totalSavedIn
     * @param taxChargedIn
     * Cosntructor for object based off purchasing information
     */
    public Ticket(String usernameIn, int flightIDChosenIn, String ticketTypeIn, int quantityOfTicketsIn, double totalPriceIn, double totalPriceWithFeesIn, double totalPriceWithTaxesIn, double totalPriceWithDiscountIn, String confirmationNumberIn, Person ticketBuyerIn, double totalSavedIn, double taxChargedIn) {
        this.username = usernameIn; 
        this.flightIDChosen = flightIDChosenIn; 
        this.ticketType = ticketTypeIn; 
        this.quantityOfTickets = quantityOfTicketsIn;
        this.totalPrice = totalPriceIn; 
        this.totalPriceWithFees = totalPriceWithFeesIn;
        this.totalPriceWithTaxes = totalPriceWithTaxesIn;
        this.totalPriceWithDiscount = totalPriceWithDiscountIn; 
        this.confirmationNumber = confirmationNumberIn; 
        this.ticketStatus = "Active"; 
        this.ticketBuyer = ticketBuyerIn;
        this.minerAirlinesFee = 9.15; 
        this.securityFee = 5.60 * quantityOfTicketsIn; 
        this.totalSaved = totalSavedIn;
        this.taxCharged = taxChargedIn; 
    }

    // Getters and setters
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
     * @return flight ID chosen
     */
    public int getFlightIDChosen() {
        return this.flightIDChosen;
    }

    /**
     * @param flightIDChosen
     * set flight ID chosen
     */
    public void setFlightIDChosen(int flightIDChosen) {
        this.flightIDChosen = flightIDChosen;
    }

    /**
     * @return ticket type (Main, Business, First)
     */
    public String getTicketType() {
        return this.ticketType;
    }

    /**
     * @param ticketType
     * set ticket type
     */
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @return quantity of tickets
     */
    public int getQuantityOfTickets() {
        return this.quantityOfTickets;
    }

    /**
     * @param quantityOfTickets
     * set quantity of tickets
     */
    public void setQuantityOfTickets(int quantityOfTickets) {
        this.quantityOfTickets = quantityOfTickets;
    }

    /**
     * @return total price
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * @param totalPrice
     * set total price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return total price with added fees (MinerAirlines and Security)
     */
    public double getTotalPriceWithFees() {
        return this.totalPriceWithFees;
    }

    /**
     * @param totalPriceWithFees
     * set total price with added fees (MinerAirlines and Security)
     */
    public void setTotalPriceWithFees(double totalPriceWithFees) {
        this.totalPriceWithFees = totalPriceWithFees;
    }

    /**
     * @return total price wqith taxes
     */
    public double getTotalPriceWithTaxes() {
        return this.totalPriceWithTaxes;
    }

    /**
     * @param totalPriceWithTaxes
     * set total price with taxes
     */
    public void setTotalPriceWithTaxes(double totalPriceWithTaxes) {
        this.totalPriceWithTaxes = totalPriceWithTaxes;
    }

    /**
     * @return total price with discount
     */
    public double getTotalPriceWithDiscount() {
        return this.totalPriceWithDiscount;
    }

    /**
     * @param totalPriceWithDiscount
     * set total price with discount
     */
    public void setTotalPriceWithDiscount(double totalPriceWithDiscount) {
        this.totalPriceWithDiscount = totalPriceWithDiscount;
    }

    /**
     * @return confirmation number
     */
    public String getConfirmationNumber() {
        return this.confirmationNumber;
    }

    /**
     * @param confirmationNumber
     * set confirmation number
     */
    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    } 

    /**
     * @return ticket status
     */
    public String getTicketStatus() {
        return this.ticketStatus;
    }

    /**
     * @param ticketStatus
     * set ticket status
     */
    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    /**
     * @return miner airlines fee
     */
    public double getMinerAirlinesFee() {
        return this.minerAirlinesFee;
    }

    /**
     * @param minerAirlinesFee
     * set miner airlines fee
     */
    public void setMinerAirlinesFee(double minerAirlinesFee) {
        this.minerAirlinesFee = minerAirlinesFee;
    }

    /**
     * @return ticket buyer (Person object)
     */
    public Person getTicketBuyer() {
        return this.ticketBuyer;
    }

    /**
     * @param ticketBuyer
     * set ticket buyer (Person object)
     */
    public void setTicketBuyer(Person ticketBuyer) {
        this.ticketBuyer = ticketBuyer;
    } 

    /**
     * @return security fee
     */
    public double getSecurityFee() {
        return this.securityFee;
    }

    /**
     * @param securityFee
     * set security fee
     */
    public void setSecurityFee(double securityFee) {
        this.securityFee = securityFee;
    } 

    /**
     * @return total saved
     */
    public double getTotalSaved() {
        return this.totalSaved;
    }

    /**
     * @param totalSaved
     * set total saved
     */
    public void setTotalSaved(double totalSaved) {
        this.totalSaved = totalSaved;
    } 
//
    /**
     * @return tax charged
     */
    public double getTaxCharged() {
        return this.taxCharged;
    }

    /**
     * @param taxCharged
     * set tax charged
     */
    public void setTaxCharged(double taxCharged) {
        this.taxCharged = taxCharged;
    } 

    /**
     * Print all ticket information
     */
    public void printTicket() {
        this.totalPriceWithFees = this.totalPrice + this.minerAirlinesFee + this.securityFee; 
        this.taxCharged = this.totalPriceWithFees * 0.0825; 
        this.totalPriceWithTaxes = this.totalPriceWithFees + this.taxCharged; 
        System.out.println("-----------------------------------------------------");
        System.out.println("Here is the ticket information for this transaction:");
        System.out.println("Confirmation number: " + this.getConfirmationNumber());
        System.out.println("Flight ID purchased: " + this.getFlightIDChosen());
        System.out.println("Ticket type: " + this.getTicketType());
        System.out.println("Quantity of tickets purchased: " + this.getQuantityOfTickets());
        System.out.println("Total transaction price without fees: " + this.getTotalPrice());
        System.out.println("Miner AirLines Fee: " + this.getMinerAirlinesFee());
        System.out.println("Security Fee: " + this.getSecurityFee());
        System.out.println("Total transaction price with fees: " + this.getTotalPriceWithFees());
        System.out.println("Taxes charged: " + this.getTaxCharged());
        System.out.println("Total transaction price with taxes: " + this.getTotalPriceWithTaxes());
        System.out.println("Total saved for this ticket: " + this.getTotalSaved());
        System.out.println("Ticket status: " + this.getTicketStatus());
        System.out.println("-----------------------------------------------------");
    }    
}
