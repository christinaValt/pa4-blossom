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
import java.util.InputMismatchException;
import java.util.Scanner;

// imports for departure dates and times modifications
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 

// This is the driver code for PA4
// Modifications are to be made for new submissions
// Methods within class // 
// main: Run the main program flow -> Ask for input, call corresponding methods and classes / object creation
// checkName: Check that the name or last name user wishes to use matches data within stored HashMap
// checkNumberOfSeats: Check that the desired range of tickets for purchase is within 1 and 8 (return boolean)
// checkMoneyAvailable: Check that the user has enough money to purchase the desired ticket(s)
// generateConfirmationNumber: Create a confirmation number for user based off information from purchase
// printOptions: Print out options for employee functionality (Consult, change)
public class RunFlightTest {
    
    /** 
     * @param args
     * Main driver code for all commands
     * Customer and Employee
     */
    public static void main(String[] args) {

        // Scanner object for input scanner
        Scanner scnr = new Scanner(System.in);
        LogWriter logWriter = new LogWriter("ChangesInFlight"); // Only adding with actions (Flight purchases, changes)
        CsvWriter csvCustomerWriter = new CsvWriter("newCustomerListPA4");
        CsvWriter csvFlightWriter = new CsvWriter("NewFlightSchedule"); 

        // HashMaps: one to store flight objects and their IDs, second for storing customer info, third for tickets
        HashMap<Integer, Flight> flightFile = new HashMap<>();
        HashMap<String, Person> userFile = new HashMap<>();

        try {
            CsvReader userCsvReader = new CsvReader("CustomerListPA4.csv");
            userFile = userCsvReader.readCsvPerson();
        } catch (NullPointerException e) {
            System.out.println("Not reading customer CSV file");
        }

        try {
            CsvReader flightCsvReader = new CsvReader("FlightSchedule.csv");
            flightFile = flightCsvReader.readFlightCSV();
        } catch (NullPointerException e) {
            System.out.println("Not reading flight CSV file");
        } 

        boolean individual = false;
        boolean exitMainMenu = false; 
        boolean rightCustomerCommand = false;
        boolean validLoginAccess = false; 
        String loginOption = ""; 
        String username = "";
        String password = ""; 
        String ticketType = "";
        int numTickets = -0; 
        double flightPrice; 
        boolean rightUserType = false;
        boolean exitCustomerOptions = false;
        boolean exitEmployeeOptions = false; 
        String confirmTicket = ""; 

        System.out.println("Hello, welcome to Miner Airlines!");

        // Initial set up -> Is the user an individual or not?
        while(!individual) {
            System.out.println("Are you an individual? (Type YES or NO)");
            String user = scnr.nextLine();

            if (user.equalsIgnoreCase("yes")) {
                individual = true; 
                while (!exitMainMenu) {                     
                    while(!validLoginAccess) {
                        System.out.println("Welcome! Do you want to login?");
                        System.out.println("[A] Yes (Purchase flight - Customer or consult flight - Employee)");
                        System.out.println("[B] No, please let me exit");
                        loginOption = scnr.nextLine();
                        if (loginOption.toLowerCase().equals("a") || loginOption.toLowerCase().equals("b")) {
                            validLoginAccess = true; 
                        }
                        else {
                            System.out.println("Hmm seems like that isn't an option!");
                            System.out.println("Please try again"); 
                        }
                    }

                    if (loginOption.equalsIgnoreCase("b")) { // Exit program fully
                        System.out.println("Bye! Thank you for using MinerAirlines! :D");
                        rightUserType = true; 
                        exitMainMenu = true;
                        csvCustomerWriter.writeCustomerCsv(userFile);
                        csvFlightWriter.writeFlightCsv(flightFile);
                        break; 
                    }

                    System.out.println("Welcome! Please provide your name below");
                    String name = scnr.nextLine(); 

                    boolean checkName = checkName(userFile, name); 
                    while (!checkName) {
                        System.out.println("Hmm, seems like your name isn't in our files!");
                        System.out.println("Please try again");
                        name = scnr.nextLine();
                        checkName = checkName(userFile, name); 
                    } 

                    System.out.println("Hello " + name + ". Please now provide your last name");
                    String lastName = scnr.nextLine();

                    checkName = checkName(userFile, lastName); 
                    while (!checkName) {
                        System.out.println("Hmm, seems like your name isn't in our files!");
                        System.out.println("Please try again");
                        lastName = scnr.nextLine();
                        checkName = checkName(userFile, lastName); 
                    }

                    boolean rightUsername = false;
                    // Checking for username validation within file first
                    System.out.println("Please provide your username:");
                    username = scnr.nextLine(); 
                    String usernameClass = ""; 
                    while(!rightUsername) { 
                        if (userFile.containsKey(username)) {
                            rightUsername = true; 
                            usernameClass = String.valueOf(userFile.get(username).getClass()); 
                        }
                        else {
                            System.out.println("Oop, not a valid username within file, please try again");
                            username = scnr.nextLine(); 
                        }     
                    } // End of right username within file while loop

                    Person currUser = userFile.get(username);

                    // Checking for password validation
                    System.out.println("Please provide your password:");
                    password = scnr.nextLine(); 
                    boolean rightPassword = false; 
                    while(!rightPassword) {
                        if(password.equals(currUser.getPassword())) {
                            rightPassword = true; 
                        }
                        else {
                        System.out.println("Oop, wrong password, try again");
                        password = scnr.nextLine();   
                        }
                    } // End of right password while loop 

                    System.out.println("Hello " + name + " " + lastName);
                    System.out.println("What type of user are you?");
                    System.out.println("[A] Customer (Purchase flights - You can also access as Employee)");
                    System.out.println("[B] Employee (Consult flight information)");
                    System.out.println("[C] None, let me exit");
                    String userType = scnr.nextLine();
                    rightUserType = false;
                    exitCustomerOptions = false;
                    exitEmployeeOptions = false;

                    // Check that the username given matches the access (Customer or Employee)
                    boolean validUserAccess = checkUsernameAccess(usernameClass, userType);
                    // If the desired option is C (To log out -> Continue as true for program flow)
                    if (userType.equalsIgnoreCase("C")) {
                        validUserAccess = true;
                        rightUserType = false;  
                    }

                    while(!validUserAccess) {
                        System.out.println("Hmm seems like you don't have access for that!");
                        System.out.println("Please select another option command");
                        System.out.println("What type of user are you?");
                        System.out.println("[A] Customer");
                        System.out.println("[B] Employee");
                        System.out.println("[C] None, let me exit");
                        userType = scnr.nextLine();
                        validUserAccess = checkUsernameAccess(usernameClass, userType);

                        if (userType.equalsIgnoreCase("C")) {
                            validUserAccess = true;
                            rightUserType = false;  
                        }
                    }
    
                    while(!rightUserType) {       
                        if (userType.equalsIgnoreCase("a")) { // Customer command / Purchasing flights
                            rightUserType = true; 
                            while (!exitCustomerOptions) {
                                System.out.println("Select an option");
                                System.out.println("[A] Purchase a flight");
                                System.out.println("[B] See all my purchased flights");
                                System.out.println("[C] Cancel a flight");
                                System.out.println("[D] Please log me out");
                                String customerOption = scnr.nextLine();

                                if (customerOption.equalsIgnoreCase("a")) { // Customer command: purchase flight
                                    rightCustomerCommand = true; 
                                    // Welcome user + check for flight wanted
                                    System.out.println("Welcome " + currUser.getName() + " " + currUser.getLastName());
                                    System.out.println("Do you know which flight you want to purchase?");
                                    System.out.println("[A] Yes, I have the ID");
                                    System.out.println("[B] No, please show me all flight info");
                                    String purchaseOption = scnr.nextLine(); 
                                    boolean rightID = false; 
                                    boolean rightPurchaseOption = false;  
                                    int purchaseFlightID = -1; 

                                    while(!rightPurchaseOption) { // Check if right purchase option was selected (Has ID or Display flight Info)
                                        if (((purchaseOption).toLowerCase()).equals("a")) { // Knowing flight ID
                                            rightPurchaseOption = true;   
                                        } // End of if statement for A - Knowing flight ID (Will ask for ID input later)

                                        if (((purchaseOption).toLowerCase()).equals("b")) {
                                            System.out.println("What type of flight are you looking for?");
                                            System.out.println("[A] Domestic");
                                            System.out.println("[B] International");
                                            purchaseOption = scnr.nextLine(); 
                                            boolean validDestination = false; 
                                            rightPurchaseOption = true; 
                        
                                            while(!validDestination) { // Check a valid option is selected (Domestic or International)
                        
                                                if ((purchaseOption).toLowerCase().equals("a")) {
                                                    printDomesticFlights(flightFile);
                                                    validDestination = true;
                                                }
                        
                                                if ((purchaseOption).toLowerCase().equals("b")) {
                                                    printInternationalFlights(flightFile);
                                                    validDestination = true;
                                                }
                        
                                                else if (!validDestination) { // In case an option other than domestic or international is selected
                                                    System.out.println("Hmm we don't carry that flight type, please try again");
                                                    purchaseOption = scnr.nextLine(); 
                                                }
                        
                                            } // End of while loop for valid destination checking (Domestic or International)

                                        } // End of if statement for B - Not knowing ID -> Display Flight Info (Domestic or International)

                                        else if (!rightPurchaseOption){
                                            System.out.println("Hmm that is not an option, please try again.");
                                            purchaseOption = scnr.nextLine(); 
                                        }

                                    } // End of while loop for right purchase option

                                    // Ask for ID (A option jumps directly, B allows for display of flights and then asks for ID input

                                    // Check if the ID given is valid 
                                    while(!rightID) {
                                        try {
                                            System.out.println("Please provide Flight ID");
                                            purchaseFlightID = scnr.nextInt();
                                        }
                                        catch(InputMismatchException e) {
                                            System.out.println("Not a valid flight ID. Please try again.");
                                            scnr.nextLine();
                                        }

                                        if (flightFile.get(purchaseFlightID) != null) {
                                            if ((flightFile.get(purchaseFlightID)).getMainCabinPrice() <= currUser.getMoneyAvailable()) {
                                                rightID = true; 
                                            } 
                                            else {
                                                System.out.println("You don't have enough money, please choose another flight ID");
                                            }
                                        }
                                    }
                                    
                                    scnr.nextLine();

                                    boolean ticketReady = false;
                                    while (!ticketReady) {
                                        System.out.println("Here are the available seats and prices for Flight ID " + purchaseFlightID);
                                        Flight flightDetails = flightFile.get(purchaseFlightID); 
                                        System.out.println("Surcharge for this transaction " + flightDetails.getSurcharge());
                                        System.out.println("[A] Main Cabin:");
                                        System.out.println("    Available Seats " + flightDetails.getMainCabinSeats() + " - Ticket Price: " + flightDetails.getMainCabinPrice());
                                        System.out.println("[B] Business Class:");
                                        System.out.println("    Available Seats " + flightDetails.getBusinessClassSeats() + " - Ticket Price: " + flightDetails.getBusinessClassPrice());
                                        System.out.println("[C] First Class:");
                                        System.out.println("    Available Seats " + flightDetails.getFirstClassSeats() + " - Ticket Price: " + flightDetails.getFirstClassPrice());
                                        System.out.println();
                                        System.out.println("What type of seat would you like?");
                                        String selectedSeatType = scnr.nextLine();

                                        boolean rightSeatType = false;
                                        boolean rightSeats = false;
                                        boolean enoughMoneyAvailable = false; 

                                        while(!rightSeatType) {
                                            if (((selectedSeatType).toLowerCase()).equals("a")) {
                                                System.out.println("Selecting: Main Cabin Seats");
                                                rightSeatType = true; 
                                                ticketType = "MainCabin"; 
                                                System.out.println("How many tickets would you like? (1-8)");
                                                numTickets = scnr.nextInt(); 
                                                scnr.nextLine();
                                                int numSeatsAvailable = flightDetails.getMainCabinSeats(); 
                                                rightSeats = checkNumberOfSeats(numTickets, numSeatsAvailable);

                                                while(!rightSeats) {
                                                    System.out.println("Hmm seems like you selected the wrong number of seats or no seats are available");
                                                    System.out.println("You can only do 1-8 tickets per transaction, please try again.");
                                                    numTickets = scnr.nextInt(); 
                                                    scnr.nextLine();
                                                    rightSeats = checkNumberOfSeats(numTickets, numSeatsAvailable);
                                                }

                                                flightPrice = ((flightDetails.getMainCabinPrice()) * numTickets) + flightDetails.getSurcharge(); 
                                                enoughMoneyAvailable = checkMoneyAvailable(currUser, flightPrice);

                                                if (enoughMoneyAvailable) {
                                                    System.out.println("Please confirm your purchase for flight " + purchaseFlightID);
                                                    System.out.println("Ticket type: Main Cabin | Number of seats: " + numTickets);

                                                    // Method that prints + calculates all charges per transaction
                                                    getFullFlightPrice(flightDetails, currUser, numTickets, flightPrice, usernameClass, ticketType); 

                                                    System.out.println("Type [A] Confirm or [B] Go back to seat selection");
                                                    System.out.println("Your money available is: " + currUser.getMoneyAvailable());
                                                    confirmTicket = scnr.nextLine(); 
                                                    if (confirmTicket.equalsIgnoreCase("A")) {
                                                        System.out.println("Creating confirmation number ...");
                                                        String ticketConfirmationNum = generateConfirmationNumber(currUser, purchaseFlightID, ticketType, numTickets);
                                                
                                                        // Creating ticket object from method: This returns the ticket to display to user after purchase + updates all data for flight & user
                                                        Ticket ticketPurchased = buyFullFlightPrice(numTickets, currUser, usernameClass, flightDetails, flightPrice, ticketType, ticketConfirmationNum, purchaseFlightID);
    
                                                        // Write actions to log writer
                                                        String changes = currUser.getName() + " " + currUser.getLastName() + " bought a Main Cabin ticket for flight ID " + purchaseFlightID;  
                                                        logWriter.writeChanges(changes);

                                                        // Display ticket to user
                                                        ticketPurchased.printTicket(); 
                                                        System.out.println("Going back to main menu...");
                                                        ticketReady = true;
                                                    } 
                                                    else if (confirmTicket.equalsIgnoreCase("B")) {
                                                        System.out.println("Going back to seat selection...");
                                                        break; 
                                                    }
                                                    else {
                                                        System.out.println("Hmm seems like that wasn't an option, please select seats again");
                                                    }
                                                } // End of loop to allow purchase + create Ticket
                                                else if (!ticketReady) {
                                                    System.out.println("Oop, not enough money, going back to seat selection loop");
                                                    break; 
                                                }
                                                
                                            } // Seat type commands: Main Cabin Price

                                            if (((selectedSeatType).toLowerCase()).equals("b")) {
                                                System.out.println("Selecting: Business Class Seats");
                                                rightSeatType = true; 
                                                ticketType = "BusinessClass"; 
                                                System.out.println("How many tickets would you like? (1-8)");
                                                numTickets = scnr.nextInt(); 
                                                scnr.nextLine();
                                                int numSeatsAvailable = flightDetails.getBusinessClassSeats(); 
                                                rightSeats = checkNumberOfSeats(numTickets, numSeatsAvailable);
            
                                                while(!rightSeats) {
                                                    System.out.println("Hmm seems like you selected the wrong number of seats or no seats are available");
                                                    System.out.println("You can only do 1-8 tickets per transaction, please try again.");
                                                    numTickets = scnr.nextInt(); 
                                                    scnr.nextLine();
                                                    rightSeats = checkNumberOfSeats(numTickets, numSeatsAvailable);
                                                }
            
                                                flightPrice = ((flightDetails.getBusinessClassPrice()) * numTickets)+ flightDetails.getSurcharge(); 
                                                enoughMoneyAvailable = checkMoneyAvailable(currUser, flightPrice);
                                        
                                                if (enoughMoneyAvailable) {
                                                    System.out.println("Please confirm your purchase for flight " + purchaseFlightID);
                                                    System.out.println("Ticket type: Business Class | Number of seats: " + numTickets);

                                                    // Method that prints + calculates all charges per transaction
                                                    getFullFlightPrice(flightDetails, currUser, numTickets, flightPrice, usernameClass, ticketType);

                                                    System.out.println("Type [A] Confirm or [B] Go back to seat selection");
                                                    confirmTicket = scnr.nextLine(); 
                                                    if (confirmTicket.equalsIgnoreCase("A")) {
                                                        System.out.println("Creating confirmation number ...");
                                                        String ticketConfirmationNum = generateConfirmationNumber(currUser, purchaseFlightID, ticketType, numTickets);
                                                
                                                        // Creating ticket object from method: This returns the ticket to display to user after purchase + updates all data for flight & user
                                                        Ticket ticketPurchased = buyFullFlightPrice(numTickets, currUser, usernameClass, flightDetails, flightPrice, ticketType, ticketConfirmationNum, purchaseFlightID);
    
                                                        // Display ticket to user
                                                        ticketPurchased.printTicket(); 
                                                        System.out.println("Going back to main menu...");
                                                        ticketReady = true;
                                                    } 
                                                    else if (confirmTicket.equalsIgnoreCase("B")) {
                                                        System.out.println("Going back to seat selection...");
                                                        break; 
                                                    }
                                                    else {
                                                        System.out.println("Hmm seems like that wasn't an option, please select seats again");
                                                    }
                                                } // End of loop to allow purchase + create Ticket
                                                else if (!ticketReady) {
                                                    System.out.println("Oop, not enough money, going back to seat selection loop");
                                                    break; 
                                                }
                        
                                            } // Seat type commands: Business Class Price

                                            if (((selectedSeatType).toLowerCase()).equals("c")) {
                                                System.out.println("Selecting: First Class Seats");
                                                rightSeatType = true; 
                                                ticketType = "FirstClass"; 
                                                System.out.println("How many tickets would you like? (1-8)");
                                                numTickets = scnr.nextInt(); 
                                                scnr.nextLine();
                                                int numSeatsAvailable = flightDetails.getFirstClassSeats(); 
                                                rightSeats = checkNumberOfSeats(numTickets, numSeatsAvailable);
            
                                                while(!rightSeats) {
                                                    System.out.println("Hmm seems like you selected the wrong number of seats or no seats are available");
                                                    System.out.println("You can only do 1-8 tickets per transaction, please try again.");
                                                    numTickets = scnr.nextInt(); 
                                                    scnr.nextLine();
                                                    rightSeats = checkNumberOfSeats(numTickets, numSeatsAvailable);
                                                }
            
                                                flightPrice = ((flightDetails.getFirstClassPrice()) * numTickets) + flightDetails.getSurcharge(); 
                                                enoughMoneyAvailable = checkMoneyAvailable(currUser, flightPrice);
            
                                                if (enoughMoneyAvailable) {
                                                    System.out.println("Please confirm your purchase for flight " + purchaseFlightID);
                                                    System.out.println("Ticket type: First Class | Number of seats: " + numTickets);
                                                    
                                                    // Method that prints + calculates all charges per transaction
                                                    getFullFlightPrice(flightDetails, currUser, numTickets, flightPrice, usernameClass, ticketType);

                                                    System.out.println("Type [A] Confirm or [B] Go back to seat selection");
                                                    confirmTicket = scnr.nextLine(); 
                                                    if (confirmTicket.equalsIgnoreCase("A")) {
                                                        System.out.println("Creating confirmation number ...");
                                                        String ticketConfirmationNum = generateConfirmationNumber(currUser, purchaseFlightID, ticketType, numTickets);
                                                
                                                        // Creating ticket object from method: This returns the ticket to display to user after purchase + updates all data for flight & user
                                                        Ticket ticketPurchased = buyFullFlightPrice(numTickets, currUser, usernameClass, flightDetails, flightPrice, ticketType, ticketConfirmationNum, purchaseFlightID);
    
                                                        // Display ticket to user
                                                        ticketPurchased.printTicket(); 
                                                        System.out.println("Going back to main menu...");
                                                        ticketReady = true;
                                                    } 
                                                    else if (confirmTicket.equalsIgnoreCase("B")) {
                                                        System.out.println("Going back to seat selection...");
                                                        break; 
                                                    }
                                                    else {
                                                        System.out.println("Hmm seems like that wasn't an option, please select seats again");
                                                    }
                                                } // End of loop to allow purchase + create Ticket
                                                else if (!ticketReady) {
                                                    System.out.println("Oop, not enough money, going back to seat selection loop");
                                                    break; 
                                                }
                                            } // Seat type commands: First Class Price

                                            else if (!rightSeatType) {
                                                System.out.println("Hmm seems like we don't carry those types of seats, please try again");
                                                System.out.println("What type of seat would you like?");
                                                selectedSeatType = scnr.nextLine();
                                            }
                                        } // End of selecting a seat type loop
                                    } // End of ticket ready loop                   
                                } // End of customer command: Selecting flight for purchase

                                else if (customerOption.equalsIgnoreCase("b")) { // Customer command: Look at flights purchased
                                    if ((currUser.getFlightTicketsPurchasedList()).isEmpty()) {
                                        System.out.println("Oop, seems like you don't have any tickets yet!");
                                        System.out.println("Going back to main menu for other options...");
                                    }
                                    // Printing all elements in user's ticket HashMap
                                    System.out.println("Here are all your tickets purchased:");
                                    for (HashMap.Entry<Integer, Ticket> userTicketFile : currUser.getFlightTicketsPurchasedList().entrySet()) {
                                        userTicketFile.getValue().printTicket();
                                    }

                                } // End of customer command: Printing all flights

                                else if (customerOption.equalsIgnoreCase("c")) { // Customer command: Cancel a flight
                                    int cancelFlightNum = -1; 
                                    System.out.println("Would you like to see your list of flights before cancelling?");
                                    System.out.println("[A] Yes please");
                                    System.out.println("[B] No, I already know which one to cancel");
                                    customerOption = scnr.nextLine(); 

                                    if (customerOption.equalsIgnoreCase("a")) { // Options to print tickets before cancelling
                                        // Printing all elements in user's ticket HashMap
                                        System.out.println("Here are all your tickets purchased:");
                                        for (HashMap.Entry<Integer, Ticket> userTicketFile : currUser.getFlightTicketsPurchasedList().entrySet()) {
                                            userTicketFile.getValue().printTicket();
                                        }   
                                        customerOption = "B"; 
                                    }

                                    if (customerOption.equalsIgnoreCase("b")) {
                                        System.out.println("Please provide flight ID of flight to cancel");
                                        cancelFlightNum = scnr.nextInt(); 
                                        scnr.nextLine();
                                        if (currUser.getFlightTicketsPurchasedList().get(cancelFlightNum) != null) {
                                            // Set status of the ticket from "Active" to "Cancelled" in Customer Map
                                            currUser.getFlightTicketsPurchased().get(cancelFlightNum).setTicketStatus("Cancelled");
                                            currUser.getFlightTicketsPurchased().get(cancelFlightNum).setQuantityOfTickets(0);
                                            currUser.getFlightTicketsPurchased().get(cancelFlightNum).setTotalPrice(0);
                                            // Set status of the ticket from "Active" to "Cancelled" in Customer Map
                                            int flightIdPurchased = currUser.getFlightTicketsPurchased().get(cancelFlightNum).getFlightIDChosen();
                                            flightFile.get(flightIdPurchased).getFlightTicketsPurchasedList().get(currUser).setTicketStatus("Cancelled");
                                            // Refund the customer (Without the Security Fee)
                                            currUser.setMoneyAvailable(currUser.getMoneyAvailable() + ((currUser.getFlightTicketsPurchased().get(cancelFlightNum).getTotalPriceWithDiscount()) - (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getSecurityFee())));
                                            // Return seats to flight
                                            flightFile.get(flightIdPurchased).setTotalSeats((flightFile.get(flightIdPurchased).getTotalSeats()) + (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getQuantityOfTickets()));
                                            // Return seats individually depending on type, along with profit
                                            String ticketPurchasedSeatType = ""; 
                                            ticketPurchasedSeatType = currUser.getFlightTicketsPurchased().get(cancelFlightNum).getTicketType();

                                            if (ticketPurchasedSeatType.equalsIgnoreCase("MainCabin")) {
                                                flightFile.get(flightIdPurchased).setMainCabinSeats((flightFile.get(flightIdPurchased).getMainCabinSeats()) + (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getQuantityOfTickets()));
                                                flightFile.get(flightIdPurchased).setMainCabinProfit((flightFile.get(flightIdPurchased).getMainCabinProfit()) - (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getTotalPrice()));

                                            }
                                            else if (ticketPurchasedSeatType.equalsIgnoreCase("BusinessCabin")) {
                                                flightFile.get(flightIdPurchased).setBusinessClassSeats((flightFile.get(flightIdPurchased).getBusinessClassSeats()) + (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getQuantityOfTickets()));
                                                flightFile.get(flightIdPurchased).setBusinessClassProfit((flightFile.get(flightIdPurchased).getBusinessClassProfit()) - (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getTotalPrice()));

                                            }
                                            else if (ticketPurchasedSeatType.equalsIgnoreCase("FirstClass")) {
                                                flightFile.get(flightIdPurchased).setBusinessClassSeats((flightFile.get(flightIdPurchased).getBusinessClassSeats()) + (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getQuantityOfTickets()));
                                                flightFile.get(flightIdPurchased).setFirstClassProfit((flightFile.get(flightIdPurchased).getBusinessClassProfit()) - (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getTotalPrice()));

                                            }
                                            flightFile.get(flightIdPurchased).setTotalSeatsProfit((flightFile.get(flightIdPurchased).getTotalSeatsProfit()) - (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getTotalPriceWithFees()));

                                            // Write actions to log writer
                                            String changes = currUser.getName() + " " + currUser.getLastName() + " cancelled ticket with confirmation number of " + (currUser.getFlightTicketsPurchased().get(cancelFlightNum).getConfirmationNumber());  
                                            logWriter.writeChanges(changes);

                                            System.out.println("Flight cancelled successfully, here's your new ticket info:");
                                            currUser.getFlightTicketsPurchased().get(cancelFlightNum).printTicket();
                                        }
                                        else {
                                            System.out.println("Hmm seems like that flight doesn't exist, please try again");
                                            System.out.println("Going back to main menu");
                                        } 
                                    }

                                }

                                else if (customerOption.equalsIgnoreCase("d")) { // Customer command: Log out of account
                                    exitCustomerOptions = true; 
                                    validLoginAccess = false; 
                                    System.out.println("Bye! Logging you out now ...");
                                    // break; 
                                } // End of customer command: Log out of account
                                
                            }
                        } // End of customer commands loop
    
                        else if (userType.equalsIgnoreCase("b")) { // Employee commands
                            rightUserType = true;  
                            boolean rightEmployeeFlightID = false;
                            int consultFlightID = -1;
                            String employeeCommand = ""; 
                            // Add menu here - Do you know flight ID or want all info?
                             
                            while (!exitEmployeeOptions) {
                                System.out.println("Welcome " + currUser.getName() + " " + currUser.getLastName());
                                System.out.println("Do you know which flight you want to purchase?");
                                System.out.println("[A] Yes, I have the ID");
                                System.out.println("[B] No, please show me all flight info");
                                String purchaseOption = scnr.nextLine(); 
                                boolean rightPurchaseOption = false;  

                                while(!rightPurchaseOption) { // Check if right purchase option was selected (Has ID or Display flight Info)
                                    if (((purchaseOption).toLowerCase()).equals("a")) { // Knowing flight ID
                                        rightPurchaseOption = true;   
                                    } // End of if statement for A - Knowing flight ID (Will ask for ID input later)

                                    if (((purchaseOption).toLowerCase()).equals("b")) {
                                        System.out.println("What type of flight are you looking for?");
                                        System.out.println("[A] Domestic");
                                        System.out.println("[B] International");
                                        purchaseOption = scnr.nextLine(); 
                                        boolean validDestination = false; 
                                        rightPurchaseOption = true; 
                    
                                        while(!validDestination) { // Check a valid option is selected (Domestic or International)
                    
                                            if ((purchaseOption).toLowerCase().equals("a")) {
                                                printDomesticFlights(flightFile);
                                                validDestination = true;
                                            }
                    
                                            if ((purchaseOption).toLowerCase().equals("b")) {
                                                printInternationalFlights(flightFile);
                                                validDestination = true;
                                            }
                    
                                            else if (!validDestination) { // In case an option other than domestic or international is selected
                                                System.out.println("Hmm we don't carry that flight type, please try again");
                                                purchaseOption = scnr.nextLine(); 
                                            }
                    
                                        } // End of while loop for valid destination checking (Domestic or International)

                                    } // End of if statement for B - Not knowing ID -> Display Flight Info (Domestic or International)

                                    else if (!rightPurchaseOption){
                                        System.out.println("Hmm that is not an option, please try again.");
                                        purchaseOption = scnr.nextLine(); 
                                    }

                                } // End of while loop for right purchase option

                                // Ask for ID (A option jumps directly, B allows for display of flights and then asks for ID input
                                // Check if the ID given is valid 
                                while(!rightEmployeeFlightID) {
                                    try {
                                        System.out.println("Please provide Flight ID for action");
                                        consultFlightID = scnr.nextInt();
                                    }
                                    catch(InputMismatchException e) {
                                        System.out.println("Not a valid flight ID. Please try again.");
                                        scnr.nextLine();
                                    }

                                    if (flightFile.get(consultFlightID) != null) {
                                        rightEmployeeFlightID = true;
                                    }
                                }
 
                                System.out.println("Press enter to go back to main menu");
                                scnr.nextLine();

                                System.out.println("Select an option");
                                System.out.println("[A] Print all flight information");
                                System.out.println("[B] Consult an existing flight");
                                System.out.println("[C] Make changes to an existing flight");
                                System.out.println("[D] Inquire all seats information");
                                System.out.println("[E] Get list of customers on flight");
                                System.out.println("[F] Compute amount sold for flight");
                                System.out.println("[G] Compute profit: expected vs current");
                                System.out.println("[H] Cancel the flight");
                                System.out.println("[I] Please log me out");
                                employeeCommand = scnr.nextLine(); 

                                if (employeeCommand.equalsIgnoreCase("a")) { // Employee command: Printing all flight info
                                    // Display details
                                    // Call object from flightFile (our HashMap) and display to user using printFlight method
                                    System.out.println("----------------------------------------------------------");
                                    System.out.println("Here are the details for flight ID " + consultFlightID);
                                    Flight flightPrint = flightFile.get(consultFlightID);
                                    flightPrint.printFlight();
                                    rightEmployeeFlightID = false;

                                } // End of employee command: Print all flight information

                                else if (employeeCommand.equalsIgnoreCase("b")) { // Employee command: Consult an existing flight
                                    // Get Flight object from flightFile map 
                                    Flight flightConsult = flightFile.get(consultFlightID);

                                    // Display possible menu of options to user + store input
                                    System.out.println("What would you like to consult?");
                                    printOptions(); 

                                    String consultOption = scnr.nextLine();

                                    // This loop allows for the continuous ask in the case a wrong option is selected
                                    boolean rightOption = false;
                                    while(!rightOption) {

                                        // Switch statement will return the information based off selected option from menu
                                        // Using corresponding getter to obtain the data
                                        switch (consultOption.charAt(0)) {

                                            case 'A':
                                                System.out.println("Origin Airport: " + flightConsult.getAirport().getOriginAirport());
                                                rightOption = true;
                                                break;

                                            case 'B':
                                                System.out.println("Origin Code: " + flightConsult.getAirport().getOriginAirportCode());
                                                rightOption = true;
                                                break;

                                            case 'C':
                                                System.out.println("Destination Airport: " + flightConsult.getAirport().getDestinationAirport());
                                                rightOption = true;
                                                break;

                                            case 'D':
                                                System.out.println("Destination Code: " + flightConsult.getAirport().getDestinationAirportCode());
                                                rightOption = true;
                                                break;

                                            case 'E':
                                                System.out.println("Departure Date: " + flightConsult.getDepartureDate());
                                                rightOption = true;
                                                break;

                                            case 'F':
                                                System.out.println("Departure Time: " + flightConsult.getDepartureTime());
                                                rightOption = true;
                                                break;

                                            case 'G':
                                                System.out.println("Arrival Date: " + flightConsult.getArrivalDate());
                                                rightOption = true;
                                                break;

                                            case 'H':
                                                System.out.println("Arrival Time: " + flightConsult.getArrivalTime());
                                                rightOption = true;
                                                break;

                                            case 'I':
                                                System.out.println("Flight duration: " + flightConsult.getDuration() + "minutes");
                                                rightOption = true;
                                                break;

                                            case 'J':
                                                System.out.println("Total distance to travel: " + flightConsult.getDistance() + " miles");
                                                rightOption = true;
                                                break;

                                            case 'K':
                                                System.out.println("Time Zone Difference: " + flightConsult.getAirport().getTimeZoneDifference());
                                                rightOption = true;
                                                break;

                                            case 'L':
                                                System.out.println("First Class Price: " + flightConsult.getFirstClassPrice());
                                                rightOption = true;
                                                break;

                                            case 'M':
                                                System.out.println("Business Class Price: " + flightConsult.getBusinessClassPrice());
                                                rightOption = true;
                                                break;

                                            case 'N':
                                                System.out.println("Main Cabin Price: " + flightConsult.getMainCabinPrice());
                                                rightOption = true;
                                                break;

                                            case 'O':
                                                System.out.println("First Class Seats: " + flightConsult.getFirstClassSeats());
                                                rightOption = true;
                                                break;

                                            case 'P':
                                                System.out.println("Business Class Seats: " + flightConsult.getBusinessClassSeats());
                                                rightOption = true;
                                                break;

                                            case 'Q':
                                                System.out.println("Main Cabin Seats: " + flightConsult.getMainCabinSeats());
                                                rightOption = true;
                                                break;

                                            case 'R':
                                                System.out.println("Total Seats: " + flightConsult.getTotalSeats());
                                                rightOption = true;
                                                break;

                                            default:
                                                System.out.println("Hmm that option is not available, please try again");
                                                System.out.println();
                                                System.out.println("What would you like to consult?");
                                                printOptions();
                                                consultOption = scnr.nextLine();
                                        }
                                    }
                                    scnr.nextLine(); 
                                }

                                else if (employeeCommand.equalsIgnoreCase("c")) { // Employee command: Make changes to an existing flight
                                    // Get Flight object from flightFile map 
                                    Flight flightChange = flightFile.get(consultFlightID);

                                    // Display possible menu of options to user + store input
                                    System.out.println("What would you like to change today?");
                                    printOptions();

                                    String changeOption = scnr.nextLine();

                                    // This loop allows for the continuous ask in the case a wrong option is selected
                                    boolean rightChangeOption = false;
                                    while(!rightChangeOption) {

                                        // Switch statement will return the information based off selected option from menu
                                        switch (changeOption.charAt(0)) {

                                            // For every corresponding case
                                            // Input asking for the new data
                                            // Writes changes by calling the writeChanges method -> Pass onto txt file
                                            // Sets the information to the one given by user
                                            // Breaks out of loop that asks for continuous input in the case of wrong option selection

                                            case 'A':
                                                System.out.println("What would you like to change the Origin Airport to?");
                                                String changeAirport = scnr.nextLine();

                                                String changes = "Flight ID " + consultFlightID + " updated Origin Airport from " + flightChange.getAirport().getOriginAirport() + " to " + changeAirport;
                                                logWriter.writeChanges(changes);

                                                flightChange.getAirport().setOriginAirport(changeAirport);
                                                rightChangeOption = true;
                                                break;

                                            case 'B':
                                                System.out.println("What would you like to change the Origin Code to?");
                                                String changeOriginCode = scnr.nextLine();

                                                String changesInCode = "Flight ID " + consultFlightID + " updated Origin Airport from " + flightChange.getAirport().getOriginAirportCode() + " to " + changeOriginCode;
                                                logWriter.writeChanges(changesInCode);

                                                flightChange.getAirport().setOriginAirportCode(changeOriginCode);
                                                rightChangeOption = true;
                                                break;

                                            case 'C':
                                                System.out.println("What would you like to change the Destination Airport to?");
                                                String changeDestinationAirport = scnr.nextLine();

                                                String changesInDestAir = "Flight ID " + consultFlightID + " updated Destination Airport from " + flightChange.getAirport().getDestinationAirport() + " to " + changeDestinationAirport;
                                                logWriter.writeChanges(changesInDestAir);

                                                flightChange.getAirport().setDestinationAirport(changeDestinationAirport);
                                                rightChangeOption = true;
                                                break;

                                            case 'D':
                                                System.out.println("What would you like to change the Destination Code to?");
                                                String changeDestCode = scnr.nextLine();

                                                String changesInDestCode = "Flight ID " + consultFlightID + " updated Destination Code from " + flightChange.getAirport().getDestinationAirportCode() + " to " + changeDestCode;
                                                logWriter.writeChanges(changesInDestCode);

                                                flightChange.getAirport().setDestinationAirportCode(changeDestCode);
                                                rightChangeOption = true;
                                                break;

                                            case 'E':
                                                System.out.println("What would you like to change the Departure Date to?");
                                                String changeDepartureDate = scnr.nextLine();

                                                String changeInDepDate = "Flight ID " + consultFlightID + " updated Departure Date from " + flightChange.getDepartureDate() + " to " + changeDepartureDate;
                                                logWriter.writeChanges(changeInDepDate);

                                                String oldArrivalDate = flightChange.getArrivalDate();
                                                flightChange.setNewDepartureDate(changeDepartureDate);
                                                String changeInArrivalDat = "Flight ID " + consultFlightID + " updated Arrival Date from " + oldArrivalDate + " to " + flightChange.getArrivalDate();
                                                logWriter.writeChanges(changeInArrivalDat);

                                                rightChangeOption = true;
                                                break;

                                            case 'F':
                                            // This case is special -> Changing departure time changes arrival time automatically as well 
                                            // For this I used libraries like SimpleDateFormat and Date for objects
                                            // This allowed for calculations to be cleaner
                                            // Aside from this -> Same format as any other option
                                            
                                                System.out.println("What would you like to change the Departure Time to?");
                                                String changeDepTime = scnr.nextLine();

                                                String changeInDepTime = "Flight ID " + consultFlightID + " updated Departure Time from " + flightChange.getDepartureTime() + " to " + changeDepTime;
                                                logWriter.writeChanges(changeInDepTime);

                                                // Set new Departure Time to the one given
                                                flightChange.setDepartureTime(changeInDepTime);

                                                // Start updates for new arrival time
                                                int flightDuration = flightChange.getDuration();
                                                int timeZoneDifferenceUpdate = flightChange.getAirport().getTimeZoneDifference(); 

                                                // Store old arrival time for later -> Use it to add change to file later
                                                String oldArrivalTime = flightChange.getArrivalTime();

                                                // Set format for time
                                                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

                                                // Conversion to a Date object -> departure time (Used for arrival time updates)
                                                Date newDepTime = null;
                                                try {
                                                    newDepTime = timeFormat.parse(changeDepTime);
                                                }
                                                catch (Exception e) {
                                                    System.out.println("Wrong time format :(");
                                                }

                                                // Using a Calendar object
                                                // Add minutes
                                                Calendar cal = Calendar.getInstance();
                                                cal.setTime(newDepTime);
                                                cal.add(Calendar.MINUTE, flightDuration);

                                                // Conversion to a Date object -> For new arrival time
                                                // By this point addition of duration is done
                                                Date newArrTime = cal.getTime();

                                                // Next step is add or remove an hour depending on timeZone
                                                cal.setTime(newArrTime);
                                                cal.add(Calendar.HOUR, timeZoneDifferenceUpdate);

                                                // Update arrival time after time zone difference
                                                newArrTime = cal.getTime(); 

                                                // Get new time by changing date object type back to String
                                                String newArrivTime = timeFormat.format(newArrTime);

                                                // Update flight object with new arrival time
                                                flightChange.setArrivalTime(newArrivTime);

                                                String changeInArrivalTim = "Flight ID " + consultFlightID + " updated Arrival Time from " + oldArrivalTime + " to " + flightChange.getArrivalTime();
                                                logWriter.writeChanges(changeInArrivalTim);

                                                rightChangeOption = true;
                                                break;

                                            case 'G':
                                                System.out.println("What would you like to change the Arrival Date to?");
                                                String changeArrivalDate = scnr.nextLine();

                                                String changeInArrivalDate = "Flight ID " + consultFlightID + " updated Arrival Date from " + flightChange.getArrivalDate() + " to " + changeArrivalDate;
                                                logWriter.writeChanges(changeInArrivalDate);

                                                flightChange.setArrivalDate(changeArrivalDate);
                                                rightChangeOption = true;
                                                break;

                                            case 'H':
                                                System.out.println("What would you like to change the Arrival Time to?");
                                                String changeArrivalTime = scnr.nextLine();

                                                String changesInArrTime = "Flight ID " + consultFlightID + " updated Arrival Time from " + flightChange.getAirport().getOriginAirport() + " to " + changeArrivalTime;
                                                logWriter.writeChanges(changesInArrTime);

                                                flightChange.setArrivalTime(changeArrivalTime);
                                                rightChangeOption = true;
                                                break;

                                            case 'I':
                                                System.out.println("What would you like to change the Duration to?");
                                                String changeDuration = scnr.nextLine();

                                                String changesInDuration = "Flight ID " + consultFlightID + " updated Duration from " + flightChange.getDuration() + " to " + changeDuration;
                                                logWriter.writeChanges(changesInDuration);

                                                flightChange.setDistance(Integer.parseInt(changeDuration));
                                                rightChangeOption = true;
                                                break;

                                            case 'J':
                                                System.out.println("What would you like to change the Distance to?");
                                                String changeDistance = scnr.nextLine();

                                                String changeInDistance = "Flight ID " + consultFlightID + " updated Distance from " + flightChange.getDistance() + " to " + changeDistance;
                                                logWriter.writeChanges(changeInDistance);

                                                flightChange.setDistance(Integer.parseInt(changeDistance));
                                                rightChangeOption = true;
                                                break;

                                            case 'K':
                                                System.out.println("What would you like to change the Time Zone Difference to?");
                                                String changeTimeZonet = scnr.nextLine();

                                                String changeInTimeZone = "Flight ID " + consultFlightID + " updated TimeZone Difference from " + flightChange.getAirport().getTimeZoneDifference() + " to " + changeTimeZonet;
                                                logWriter.writeChanges(changeInTimeZone);

                                                flightChange.getAirport().setTimeZoneDifference(Integer.parseInt(changeTimeZonet));
                                                rightChangeOption = true;
                                                break;

                                            case 'L':
                                                System.out.println("What would you like to change the First Class Price to?");
                                                String changeFirstClassPrice = scnr.nextLine();

                                                String changeInFirstClassPrice = "Flight ID " + consultFlightID + " updated First Class Price from " + flightChange.getFirstClassPrice() + " to " + changeFirstClassPrice;
                                                logWriter.writeChanges(changeInFirstClassPrice);

                                                flightChange.setFirstClassPrice(Integer.parseInt(changeFirstClassPrice));
                                                rightChangeOption = true;
                                                break;

                                            case 'M':
                                                System.out.println("What would you like to change the Business Class Price to?");
                                                String changeBusinessClassPrice = scnr.nextLine();

                                                String changeInBusiPrice = "Flight ID " + consultFlightID + " updated Business Class Price from " + flightChange.getBusinessClassPrice() + " to " + changeBusinessClassPrice;
                                                logWriter.writeChanges(changeInBusiPrice);

                                                flightChange.setBusinessClassPrice(Integer.parseInt(changeBusinessClassPrice));
                                                rightChangeOption = true;
                                                break;

                                            case 'N':
                                                System.out.println("What would you like to change the Main Cabin Price to?");
                                                String changeMainCabinPrice = scnr.nextLine();

                                                String changeInMainCabinPrice = "Flight ID " + consultFlightID + " updated Main Cabin Price from " + flightChange.getMainCabinPrice() + " to " + changeMainCabinPrice;
                                                logWriter.writeChanges(changeInMainCabinPrice);

                                                flightChange.setMainCabinPrice(Integer.parseInt(changeMainCabinPrice));
                                                rightChangeOption = true;
                                                break;

                                            case 'O':
                                                System.out.println("What would you like to change the number of First Class Seats to?");
                                                String changeInFirstClassSeats = scnr.nextLine();

                                                String changeFirstSeats = "Flight ID " + consultFlightID + " updated number of First Class Seats from " + flightChange.getFirstClassSeats() + " to " + changeInFirstClassSeats;
                                                logWriter.writeChanges(changeFirstSeats);

                                                flightChange.setFirstClassSeats(Integer.parseInt(changeInFirstClassSeats));
                                                rightChangeOption = true;
                                                break;

                                            case 'P':
                                                System.out.println("What would you like to change the number of Business Class Seats to?");
                                                String changeBusiSeats = scnr.nextLine();

                                                String changeInBusiSeats = "Flight ID " + consultFlightID + " updated number of Business Class Seats from " + flightChange.getBusinessClassSeats() + " to " + changeBusiSeats;
                                                logWriter.writeChanges(changeInBusiSeats);

                                                flightChange.setBusinessClassSeats(Integer.parseInt(changeBusiSeats));
                                                rightChangeOption = true;
                                                break;


                                            case 'Q':
                                                System.out.println("What would you like to change the number of Main Cabin seats to?");
                                                String changeCabin = scnr.nextLine();

                                                String changesInCabin = "Flight ID " + consultFlightID + " updated Main Cabin number of seats from " + flightChange.getMainCabinSeats() + " to " + changeCabin;
                                                logWriter.writeChanges(changesInCabin);

                                                flightChange.setMainCabinSeats(Integer.parseInt(changeCabin));
                                                rightChangeOption = true;
                                                break;

                                            case 'R':
                                                System.out.println("What would you like to change the number of total seats to?");
                                                String changeTotalSeats = scnr.nextLine();

                                                String changesInSeats = "Flight ID " + consultFlightID + " updated total number of seats from " + flightChange.getTotalSeats() + " to " + changeTotalSeats;
                                                logWriter.writeChanges(changesInSeats);

                                                flightChange.setTotalSeats(Integer.parseInt(changeTotalSeats));
                                                rightChangeOption = true;
                                                break;

                                            default:
                                                System.out.println("Hmm that option is not available, please try again");
                                                System.out.println();
                                                printOptions();
                                                changeOption = scnr.nextLine();
                                            }
                                    }
                                }

                                else if (employeeCommand.equalsIgnoreCase("d")) { // Employee command: Inquire all seats information
                                    System.out.println("Here is the information of all seats remaining for flight ID " + consultFlightID);
                                    System.out.println("Total Seats: " + (flightFile.get(consultFlightID)).getTotalSeats());
                                    System.out.println("Total Main Cabin Seats: " + (flightFile.get(consultFlightID)).getMainCabinSeats());
                                    System.out.println("Total Business Class Seats: " + (flightFile.get(consultFlightID)).getBusinessClassSeats());
                                    System.out.println("Total First Class Seats: " + (flightFile.get(consultFlightID)).getFirstClassSeats());
                                }

                                else if (employeeCommand.equalsIgnoreCase("e")) { // Employee command: Get list of customers on flight
                                    System.out.println("These are all your purchased flights");
                                    if (((flightFile.get(consultFlightID)).getFlightTicketsPurchasedList()).isEmpty()) {
                                        System.out.println("Oop, seems like no one has purchased any tickets yet!");
                                    }
                                    // Printing all elements in user's ticket HashMap
                                    for (HashMap.Entry<Person, Ticket> employeeTicketFile : (flightFile.get(consultFlightID)).getFlightTicketsPurchasedList().entrySet()) {
                                        System.out.println("Ticket to the name of: " + employeeTicketFile.getKey().getName() + " " + employeeTicketFile.getKey().getLastName());
                                        employeeTicketFile.getValue().printTicket();
                                    }
                                }

                                else if (employeeCommand.equalsIgnoreCase("f")) { // Employee command: Compute amount sold for flight
                                    flightFile.get(consultFlightID).computeTotalCollected();
                                }

                                else if (employeeCommand.equalsIgnoreCase("g")) { // Employee command: Compute expected profit
                                    flightFile.get(consultFlightID).returnExpectedProfit(); 
                                }

                                else if (employeeCommand.equalsIgnoreCase("h")) { // Employee command: Cancel flight
                                    if (((flightFile.get(consultFlightID)).getFlightTicketsPurchasedList()).isEmpty()) {
                                        System.out.println("Oop, seems like no one has purchased any tickets yet!");
                                    }
                                    Flight flightCancel = flightFile.get(consultFlightID);
                                    // Reset all profits of flight to 0
                                    flightCancel.setTotalSeatsProfit(0);
                                    flightCancel.setMinerAirlinesFeeTotal(0);
                                    flightCancel.setSecurityFeeTotal(0);
                                    flightCancel.setTaxChargedTotal(0);
                                    flightCancel.setTotalDiscounted(0);
                                    // Printing all elements in user's ticket HashMap
                                    for (HashMap.Entry<Person, Ticket> employeeTicketFile : flightCancel.getFlightTicketsPurchasedList().entrySet()) {
                                        // Set ticket status to "Cancelled"
                                        employeeTicketFile.getKey().getFlightTicketsPurchasedList().get(consultFlightID).setTicketStatus("Cancelled");
                                        employeeTicketFile.getKey().getFlightTicketsPurchasedList().get(consultFlightID).setQuantityOfTickets(0);
                                        employeeTicketFile.getKey().getFlightTicketsPurchasedList().get(consultFlightID).setTotalPrice(0);
                                        // Return money to user
                                        employeeTicketFile.getKey().setMoneyAvailable((employeeTicketFile.getKey().getMoneyAvailable()) + employeeTicketFile.getKey().getFlightTicketsPurchasedList().get(consultFlightID).getTotalPriceWithDiscount());
                                        // Set Flight Status
                                        flightFile.get(consultFlightID).setFlightStatus("Cancelled");

                                        // // Write actions to log writer
                                        // String changes = currUser.getName() + " " + currUser.getLastName() + " cancelled ticket with confirmation number of " + (currUser.getFlightTicketsPurchased().get(consultFlightID).getConfirmationNumber());  
                                        // logWriter.writeChanges(changes);
                                    }
                                    System.out.println("Flight cancelled successfully");
                                    System.out.println("Going back to main menu now...");
                                }

                                else if (employeeCommand.equalsIgnoreCase("i")) { // Employee command: Log out of account
                                    exitEmployeeOptions = true; 
                                    validLoginAccess = false;
                                    System.out.println("Bye! Logging you out now...");
                                } // End of employee command: exit out of account
                            } // End of employee loop: Exit employee options
                        } // End of employee commands
    
                        else if (userType.equalsIgnoreCase("c")) { // Exit MinerAirlines completely -> Exit fully
                            System.out.println("Bye! Thank you for using MinerAirlines :D");
                            // logWriter.flush(); -> ? 
                            rightUserType = true; 
                            exitMainMenu = true;
                            csvCustomerWriter.writeCustomerCsv(userFile);
                            csvFlightWriter.writeFlightCsv(flightFile); 
                        } // End of exiting back to menu command

                        else if (!rightUserType) { // Handling invalid input for main menu options
                            System.out.println("Sorry MinerAir doesn't allow aliens :(");
                            System.out.println("Please try again");
                            userType = scnr.nextLine();
                        }
                    } 

                } // End of commands for options [A] Customer [B] Employee [C] EXIT

            } // End of commands for option "yes" (login -> customer / employee)

            else if (user.equalsIgnoreCase("no")) {
                individual = true; 
                System.out.println("Sorry! Miner Airlines doesn't allow aliens :(");
                System.out.println("Please run program again and retry!");
            } // End of commands for option "no" (Program ends)

            else {
                System.out.println("Seems like that isn't an option!");
                System.out.println("Please try again");
            } // Not a valid intial option: individual? yes / no (Ask for retry))
        }

        scnr.close(); // Closing scanner used for user inputs

    } // End of main method 

    // This method checks for the existence of the user's name or last name within the file
    /**
     * @param userFile
     * @param nameInput
     * @return if name is valid within file
     */
    public static boolean checkName(HashMap<String, Person> userFile, String nameInput) { 
        for (HashMap.Entry<String, Person> userKey : userFile.entrySet()) {
            if ((userKey.getValue().getName()).equalsIgnoreCase((nameInput.toLowerCase()))) {
                return true;
            }
            else if ((userKey.getValue().getLastName()).equalsIgnoreCase((nameInput.toLowerCase()))) {
                return true; 
            }
        }
        return false; 
    }

    // This method checks that the user has access to option selected (Customer vs Employee)
    /**
     * @param classOfUsername
     * @param userType
     * @return if username has access to command
     */
    public static boolean checkUsernameAccess(String classOfUsername, String userType) {
        if (classOfUsername.equals("class Customer") && userType.equalsIgnoreCase("A")) {
            return true; 
        }
        if (classOfUsername.equals("class Employee") && userType.equalsIgnoreCase("A") || classOfUsername.equals("class Employee") && userType.equalsIgnoreCase("B")) {
            return true; 
        }
        
        return false; 
    }

    // This method prints the Domestic Flight objects individual info stored in the flightFile HashMap
    /**
     * @param flightFile
     * Print all domestic flights from file
     */
    public static void printDomesticFlights(HashMap<Integer, Flight> flightFile) {
        System.out.println("Here are all the domestic flight options:");
        Domestic dummyDom = new Domestic(); 

        for (HashMap.Entry<Integer, Flight> domesticKey : flightFile.entrySet()) {
            if (domesticKey.getValue().getClass().equals(dummyDom.getClass())) {
                System.out.println("Flight ID: " + domesticKey.getKey());
                domesticKey.getValue().printFlight();
            }
        }
    }

    // This method prints the International Flight objects individual info stored in the flightFile HashMap
    /**
     * @param flightFile
     * Print all international flights from file
     */
    public static void printInternationalFlights(HashMap<Integer, Flight> flightFile) {
        System.out.println("Here are all the international flight options:");
        International dummyInt = new International(); 

        for (HashMap.Entry<Integer, Flight> internationalKey : flightFile.entrySet()) {
            if (internationalKey.getValue().getClass().equals(dummyInt.getClass())) {
                System.out.println("Flight ID: " + internationalKey.getKey());
                internationalKey.getValue().printFlight();
            }
        }
    }

    // This method checks to see if the selected number of seats is within the desired range (1-6 tickets per transaction)
    /**
     * @param desiredSeats
     * @param seatsAvailable
     * @return if seats desired are within range
     */
    public static boolean checkNumberOfSeats(int desiredSeats, int seatsAvailable) {
        if (desiredSeats >= 1 && desiredSeats <= 8 && desiredSeats < seatsAvailable) {
            return true;
        }

        return false;

    }

    // This method checks to see if the current user has enough money available for the desired purchase
    /**
     * @param currUserIn
     * @param flightPrice
     * @return if user has enough money available
     */
    public static boolean checkMoneyAvailable(Person currUserIn, double flightPrice) {
        double moneyAvailable = currUserIn.getMoneyAvailable();

        if (moneyAvailable >= flightPrice) {
            currUserIn.setMoneyAvailable(moneyAvailable - flightPrice);
            return true; 
        }

        return false;
    }

    /**
     * @param flightDetails
     * @param currUser
     * @param numTickets
     * @param flightPrice
     * @param usernameClass
     * @param ticketType
     * Do all calculations for flight purchasing (Fees, Taxes and Discount)
     */
    public static void getFullFlightPrice(Flight flightDetails, Person currUser, int numTickets, double flightPrice, String usernameClass, String ticketType) {
        double totalWithFees = flightPrice + 9.15 + (5.60 * numTickets);
        double totalWithTaxes = totalWithFees * 1.0825; 
        double totalDiscount = 0;
        double totalWithDiscount = totalWithTaxes; 
        System.out.println(" --- Surcharge fee: " + flightDetails.getSurcharge());
        System.out.println("Your total amount for seats is: " + flightPrice);

        System.out.println(" --- Miner Airlines Fee: 9.15");
        System.out.println(" --- Security Fee: " + (5.60 * numTickets));
        System.out.println("Your total amount with fees is: " + totalWithFees);

        System.out.println(" --- Tax charged: " + totalWithFees * 0.0825);
        System.out.println("Total with taxes: " + totalWithTaxes);

        if (currUser.isTicketMinerMembership()) {
            totalDiscount += totalWithDiscount * 0.05;
            totalWithDiscount = totalWithDiscount - (totalWithDiscount * 0.05);
        }

        if (usernameClass.equals("class Employee")) {
            if (ticketType.equals("MainCabin") || ticketType.equals("BusinessClass")) {
                totalWithDiscount = totalWithDiscount - (totalWithDiscount * 0.75);
                totalDiscount += totalWithDiscount * 0.75;
            }
            if (ticketType.equals("FirstClass")) {
                totalWithDiscount = totalWithDiscount - (totalWithDiscount * 0.50);
                totalDiscount += totalWithDiscount * 0.50;
            }
        }

        System.out.println(" --- Total discount: " + totalDiscount);
        System.out.println("Total charged: " + totalWithDiscount);
    }

    /**
     * @param numTickets
     * @param currUser
     * @param usernameClass
     * @param flightDetails
     * @param flightPrice
     * @param ticketType
     * @param confirmationNum
     * @param purchaseFlightID
     * @return Ticket object with all information + changes made for profit, fees colected and money available left
     */
    public static Ticket buyFullFlightPrice(int numTickets, Person currUser, String usernameClass, Flight flightDetails, double flightPrice, String ticketType, String confirmationNum, int purchaseFlightID) {
        double totalFees = 9.15 + (5.60 * numTickets);
        double totalWithFees = flightPrice + totalFees;
        double totalWithTaxes = totalWithFees * 1.0825; 
        double totalDiscount = 0;
        double totalWithDiscount = totalWithTaxes; 

        if (currUser.isTicketMinerMembership()) {
            totalDiscount += totalWithDiscount * 0.05;
            totalWithDiscount = totalWithDiscount - (totalWithDiscount * 0.05);
        }

        if (usernameClass.equals("class Employee")) {
            if (ticketType.equals("MainCabin") || ticketType.equals("BusinessClass")) {
                totalWithDiscount = totalWithDiscount - (totalWithDiscount * 0.75);
                totalDiscount += totalWithDiscount * 0.75;
            }
            if (ticketType.equals("FirstClass")) {
                totalWithDiscount = totalWithDiscount - (totalWithDiscount * 0.50);
                totalDiscount += totalWithDiscount * 0.50;
            }
        }

        // Changing the user money for after purchase
        currUser.setMoneyAvailable((currUser.getMoneyAvailable()) - totalWithDiscount);

        // Change available seats of flight
        int currAvailableSeats = flightDetails.getMainCabinSeats();
        flightDetails.setMainCabinSeats((currAvailableSeats - numTickets));

        // Generate Ticket object to store in user's map
        Ticket ticketPurchased = new Ticket(currUser.getUsername(), purchaseFlightID, ticketType, numTickets, flightPrice, totalWithFees, totalWithTaxes, totalWithDiscount, confirmationNum, currUser, totalDiscount, (totalWithFees * 0.0825));
        currUser.getFlightTicketsPurchasedList().put(purchaseFlightID, ticketPurchased);

        // Store Ticket object in flightFile map
        flightDetails.getFlightTicketsPurchasedList().put(currUser, ticketPurchased); 

        // Set profit earned from base ticket price (No fees)
        if (ticketType.equals("MainCabin")) {
            flightDetails.setMainCabinProfit(flightDetails.getMainCabinProfit() + ((flightDetails.getMainCabinPrice()) * numTickets));
        }
        if (ticketType.equals("BusinessClass")) {
            flightDetails.setBusinessClassProfit(flightDetails.getBusinessClassProfit() + ((flightDetails.getBusinessClassPrice()) * numTickets));

        }
        if (ticketType.equals("FirstClass")) {
            flightDetails.setFirstClassProfit(flightDetails.getFirstClassProfit() + ((flightDetails.getFirstClassPrice()) * numTickets));
        }
        flightDetails.setTotalSeatsProfit(flightDetails.getTotalSeatsProfit() + (totalWithDiscount - totalFees));

        // Set amount earned in fees + discount
        flightDetails.setMinerAirlinesFeeTotal((flightDetails.getMinerAirlinesFeeTotal()) + 9.15);
        flightDetails.setSecurityFeeTotal((flightDetails.getSecurityFeeTotal()) + (5.60 * numTickets));
        flightDetails.setTaxChargedTotal((flightDetails.getTaxChargedTotal()) + (totalWithFees * 0.0825));
        flightDetails.setTotalDiscounted((flightDetails.getTotalDiscounted()) + totalDiscount);
        currUser.setTotalSaved(totalDiscount);

        return ticketPurchased; 
    }


    // This method generates a confirmation number to print out for user
    /**
     * @param currUser
     * @param purchaseFlightID
     * @param ticketType
     * @param numTickets
     * @return confirmation number for ticket based off given information
     */
    public static String generateConfirmationNumber(Person currUser, int purchaseFlightID, String ticketType, int numTickets) {
        String confirmationNum = currUser.getUsername() + "-FID:" + purchaseFlightID + "-" + ticketType + numTickets; 
        return confirmationNum; 
    }
//
    // This is a helper method that prints out the options method for flight changes or consultations
    /**
     * Print consultation method for employee commands / Consultations
     */
    public static void printOptions() {
        System.out.println("[A] Origin Airport"); 
        System.out.println("[B] Origin Code"); 
        System.out.println("[C] Destination Airport"); 
        System.out.println("[D] Destination Code"); 
        System.out.println("[E] Departure Date"); 
        System.out.println("[F] Departure Time"); 
        System.out.println("[G] Arrival Date"); 
        System.out.println("[H] Arrival Time"); 
        System.out.println("[I] Duration"); 
        System.out.println("[J] Distance"); 
        System.out.println("[K] Time Zone Difference"); 
        System.out.println("[L] First Class Price"); 
        System.out.println("[M] Business Class Price"); 
        System.out.println("[N] Main Cabin Price"); 
        System.out.println("[O] First Class Seats"); 
        System.out.println("[P] Business Class Seats"); 
        System.out.println("[Q] Main Cabin Seats"); 
        System.out.println("[R] Total Seats");
    }

} // End of RunFlight class
