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

// This class focuses on the main attributes of a flight
// This is an abstract class, meaning we will create no instances of it, but we will use the attributes to extend to Domestic or International objects
public abstract class Flight {

    // Attributes
    private int flightNumber;
    private Airport airport;
    private String arrivalDate;
    private String departureDate;
    private String arrivalTime;
    private String departureTime;
    private int flightID;
    private int duration;
    private int distance;
    private double surcharge;
    private boolean foodServed;
    private double routeCost;
    private int minerPoints;
    private int totalSeats;
    private int firstClassSeats;
    private int businessClassSeats;
    private int mainCabinSeats;
    private double firstClassPrice;
    private double businessClassPrice;
    private double mainCabinPrice;
    private double totalSeatsProfit;
    private double firstClassProfit;
    private double businessClassProfit;
    private double mainCabinProfit;
    private HashMap<Person, Ticket> flightTicketsPurchasedList;
    private String flightStatus;
    private double minerAirlinesFeeTotal;
    private double securityFeeTotal;
    private double taxChargedTotal;
    private double totalDiscounted;

    // Default constructor
    /**
     * Default constructor
     */
    public Flight() {}

    // Constructor to receive attributes -- same order as the csv file
    /**
     * @param flightNumberIn
     * @param originAirportIn
     * @param originAirportCodeIn
     * @param originAirportHasLoungeIn
     * @param originAirportFeeIn
     * @param originAirportCityIn
     * @param originAirportStateIn
     * @param originAirportCountryIn
     * @param destinationAirportIn
     * @param destinationAirportCodeIn
     * @param destinationAirportHasLoungeIn
     * @param destinationAirportFeeIn
     * @param destinationAirportCityIn
     * @param destinationAirportStateIn
     * @param destinationAirportCountryIn
     * @param timeZoneDifferenceIn
     * @param departureDateIn
     * @param departureTimeIn
     * @param durationIn
     * @param distanceIn
     * @param arrivalDateIn
     * @param arrivalTimeIn
     * @param surchargeIn
     * @param foodServedIn
     * @param routeCostIn
     * @param minerPointsIn
     * @param totalSeatsIn
     * @param firstClassSeatsIn
     * @param businessClassSeatsIn
     * @param mainCabinSeatsIn
     * @param firstClassPriceIn
     * @param businessClassPriceIn
     * @param mainCabinPriceIn
     * Constructor for object creation based off file contents
     */
    public Flight(int flightNumberIn, String originAirportIn, String originAirportCodeIn, boolean originAirportHasLoungeIn, double originAirportFeeIn, String originAirportCityIn, String originAirportStateIn, String originAirportCountryIn, String destinationAirportIn, String destinationAirportCodeIn, boolean destinationAirportHasLoungeIn, double destinationAirportFeeIn, String destinationAirportCityIn, String destinationAirportStateIn, String destinationAirportCountryIn, int timeZoneDifferenceIn, String departureDateIn, String departureTimeIn, int durationIn, int distanceIn, String arrivalDateIn, String arrivalTimeIn, double surchargeIn, boolean foodServedIn, double routeCostIn, int minerPointsIn, int totalSeatsIn, int firstClassSeatsIn, int businessClassSeatsIn, int mainCabinSeatsIn, double firstClassPriceIn, double businessClassPriceIn, double mainCabinPriceIn) {
        this.flightNumber = flightNumberIn; 
        this.airport = new Airport(originAirportIn, originAirportCodeIn, originAirportHasLoungeIn, originAirportFeeIn, originAirportCityIn, originAirportStateIn, originAirportCountryIn, destinationAirportIn, destinationAirportCodeIn, destinationAirportHasLoungeIn, destinationAirportFeeIn, destinationAirportCityIn, destinationAirportStateIn, destinationAirportCountryIn, timeZoneDifferenceIn);
        this.departureDate = departureDateIn;
        this.departureTime = departureTimeIn; 
        this.duration = durationIn; 
        this.distance = distanceIn;
        this.arrivalDate = arrivalDateIn;
        this.arrivalTime = arrivalTimeIn;
        this.surcharge = surchargeIn;
        this.foodServed = foodServedIn;
        this.routeCost = routeCostIn;
        this.minerPoints = minerPointsIn; 
        this.totalSeats = totalSeatsIn;
        this.firstClassSeats = firstClassSeatsIn;
        this.businessClassSeats = businessClassSeatsIn;
        this.mainCabinSeats = mainCabinSeatsIn;
        this.firstClassPrice = firstClassPriceIn;
        this.businessClassPrice = businessClassPriceIn;
        this.mainCabinPrice = mainCabinPriceIn; 
        this.flightTicketsPurchasedList = new HashMap<>(); 
        this.flightStatus = "Active"; 
        this.minerAirlinesFeeTotal = 0;
        this.securityFeeTotal = 0; 
        this.taxChargedTotal = 0;
        this.totalDiscounted = 0.00; 
    }

    // Getters and setters
    /**
     * @return flight number
     */
    public int getFlightNumber() {
        return this.flightNumber;
    }

    /**
     * @param flightNumber
     * sets flight number
     */
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return airport object
     */
    public Airport getAirport() {
        return this.airport;
    }

    /**
     * @param airport
     * set airport object
     */
    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    /**
     * @return arrival date
     */
    public String getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * @param arrivalDate
     * sets arrival date
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * @return departure date
     */
    public String getDepartureDate() {
        return this.departureDate;
    }

    /**
     * @param departureDate
     * sets departure date
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    // Automatically update the new date for departure and change the arrival date as well 
    // Original Note: Changing the Departure Date/time should programmatically change the arrival date/time
    /**
     * @param departureDateIn
     * set a new departure date
     */
    public void setNewDepartureDate(String departureDateIn) {
        this.departureDate = departureDateIn;
        this.arrivalDate = departureDateIn;

    }

    /**
     * @return arrival date
     */
    public String getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * @param arrivalTime
     * set arrival time
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return departure time
     */
    public String getDepartureTime() {
        return this.departureTime;
    }

    /**
     * @param departureTime
     * sets departure time
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @return flight id
     */
    public int getFlightID() {
        return this.flightID;
    }

    /**
     * @param flightID
     * set flight id
     */
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    /**
     * @return flight duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * @param duration
     * set duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return distance
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * @param distance
     * set distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return total seats
     */
    public int getTotalSeats() {
        return this.totalSeats;
    }

    /**
     * @param totalSeats
     * set total seats
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * @return first class seats
     */
    public int getFirstClassSeats() {
        return this.firstClassSeats;
    }

    /**
     * @param firstClassSeats
     * set first class seats
     */
    public void setFirstClassSeats(int firstClassSeats) {
        this.firstClassSeats = firstClassSeats;
    }

    /**
     * @return business class seats
     */
    public int getBusinessClassSeats() {
        return this.businessClassSeats;
    }

    /**
     * @param businessClassSeats
     * return business class seats
     */
    public void setBusinessClassSeats(int businessClassSeats) {
        this.businessClassSeats = businessClassSeats;
    }

    /**
     * @return main cabin seats
     */
    public int getMainCabinSeats() {
        return this.mainCabinSeats;
    }

    /**
     * @param mainCabinSeats
     * set main cabin seats
     */
    public void setMainCabinSeats(int mainCabinSeats) {
        this.mainCabinSeats = mainCabinSeats;
    }

    /**
     * @return first class price
     */
    public double getFirstClassPrice() {
        return this.firstClassPrice;
    }

    /**
     * @param firstClassPrice
     * set first class price
     */
    public void setFirstClassPrice(double firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    /**
     * @return business class price
     */
    public double getBusinessClassPrice() {
        return this.businessClassPrice;
    }

    /**
     * @param businessClassPrice
     * set business class price
     */
    public void setBusinessClassPrice(double businessClassPrice) {
        this.businessClassPrice = businessClassPrice;
    }

    /**
     * @return main cabin price
     */
    public double getMainCabinPrice() {
        return this.mainCabinPrice;
    }

    /**
     * @param mainCabinPrice
     * return main cabin price
     */
    public void setMainCabinPrice(double mainCabinPrice) {
        this.mainCabinPrice = mainCabinPrice;
    }

    /**
     * @return total seats profit
     */
    public double getTotalSeatsProfit() {
        return this.totalSeatsProfit;
    }

    /**
     * @param totalSeatsProfit
     * set total seats profit
     */
    public void setTotalSeatsProfit(double totalSeatsProfit) {
        this.totalSeatsProfit = totalSeatsProfit;
    }

    /**
     * @return first class profit
     */
    public double getFirstClassProfit() {
        return this.firstClassProfit;
    }

    /**
     * @param firstClassProfit
     * set first class profit
     */
    public void setFirstClassProfit(double firstClassProfit) {
        this.firstClassProfit = firstClassProfit;
    }

    /**
     * @return business class profit
     */
    public double getBusinessClassProfit() {
        return this.businessClassProfit;
    }

    /**
     * @param businessClassProfit
     * set business class profit
     */
    public void setBusinessClassProfit(double businessClassProfit) {
        this.businessClassProfit = businessClassProfit;
    }

    /**
     * @return main cabin profit
     */
    public double getMainCabinProfit() {
        return this.mainCabinProfit;
    }

    /**
     * @param mainCabinProfit
     * set main cabin profit
     */
    public void setMainCabinProfit(double mainCabinProfit) {
        this.mainCabinProfit = mainCabinProfit;
    }

    /**
     * @return flight tickets purchased list as map
     */
    public HashMap<Person, Ticket> getFlightTicketsPurchasedList() {
        return this.flightTicketsPurchasedList;
    }

    /**
     * @param flightTicketsPurchased
     * set flight tickets purchased list as map 
     */
    public void setFlightTicketsPurchasedList(HashMap<Person, Ticket> flightTicketsPurchased) {
        this.flightTicketsPurchasedList = flightTicketsPurchased;
    } 

    /**
     * @return surcharge
     */
    public double getSurcharge() {
        return this.surcharge;
    }

    /**
     * @param surcharge
     * sets surcharge fee
     */
    public void setSurcharge(double surcharge) {
        this.surcharge = surcharge;
    }

    /**
     * @return if food is served
     */
    public boolean isFoodServed() {
        return this.foodServed;
    }

    /**
     * @param foodServed
     * set if food is served
     */
    public void setFoodServed(boolean foodServed) {
        this.foodServed = foodServed;
    }

    /**
     * @return route cost
     */
    public double getRouteCost() {
        return this.routeCost;
    }

    /**
     * @param routeCost
     * set route cost
     */
    public void setRouteCost(double routeCost) {
        this.routeCost = routeCost;
    }

    /**
     * @return miner points
     */
    public int getMinerPoints() {
        return this.minerPoints;
    }

    /**
     * @param minerPoints
     * set miner points
     */
    public void setMinerPoints(int minerPoints) {
        this.minerPoints = minerPoints;
    }

    /**
     * @return flight status
     */
    public String getFlightStatus() {
        return this.flightStatus;
    }

    /**
     * @param flightStatus
     * set flight status
     */
    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    } 

    /**
     * @return miner airlines fee total
     */
    public double getMinerAirlinesFeeTotal() {
        return this.minerAirlinesFeeTotal;
    }

    /**
     * @param minerAirlinesFeeTotal
     * set miner airlines fee total
     */
    public void setMinerAirlinesFeeTotal(double minerAirlinesFeeTotal) {
        this.minerAirlinesFeeTotal = minerAirlinesFeeTotal;
    }

    /**
     * @return security fee total
     */ 
    public double getSecurityFeeTotal() {
        return this.securityFeeTotal;
    }

    /**
     * @param securityFeeTotal
     * set security fee total
     */
    public void setSecurityFeeTotal(double securityFeeTotal) {
        this.securityFeeTotal = securityFeeTotal;
    }

    /**
     * @return total tax charged
     */
    public double getTaxChargedTotal() {
        return this.taxChargedTotal;
    }

    /**
     * @param taxChargedTotal
     * set total tax charged
     */
    public void setTaxChargedTotal(double taxChargedTotal) {
        this.taxChargedTotal = taxChargedTotal;
    } 

    /**
     * @return total discount
     */
    public double getTotalDiscounted() {
        return this.totalDiscounted;
    }

    /**
     * @param totalDiscounted
     * set total discount
     */
    public void setTotalDiscounted(double totalDiscounted) {
        this.totalDiscounted = totalDiscounted;
    }

    /**
     * Return expected profit
     */
    public void returnExpectedProfit() {
        System.out.println("Expected profit for this flight: " + (mainCabinSeats * mainCabinPrice) + (businessClassSeats * businessClassPrice) + (firstClassSeats * firstClassPrice));
        System.out.println("Current profit is: " + mainCabinProfit + businessClassProfit + firstClassProfit + minerAirlinesFeeTotal + securityFeeTotal); 
    } 

    /**
     * Return total amount collected for flight
     */
    public void computeTotalCollected() {
        System.out.println("Total amount collected from main cabin seats:" + mainCabinProfit);
        System.out.println("Total amount collected from business class seats:" + businessClassProfit);
        System.out.println("Total amount collected from first class seats:" + firstClassProfit);
        System.out.println("Total amount collected from all seats:" + totalSeatsProfit);
        System.out.println("Total amount collected from MinerAirlines fee:" + minerAirlinesFeeTotal);
        System.out.println("Total amount collected from Security Fee: " + securityFeeTotal);
        System.out.println("Total amount collected from taxes (not profit): " + taxChargedTotal);
    }

    /**
     * Print all flight information
     */
    public void printFlight() {
        // System.out.println("Flight ID: " + this.getFlightID());
        System.out.print("Origin Airport: " + this.airport.getOriginAirport() + " - ");
        System.out.println("Origin Code: " + this.airport.getOriginAirportCode());
        System.out.println("Destination Airport: " + this.airport.getDestinationAirport());
        System.out.println("Destination Code: " + this.airport.getDestinationAirportCode());
        System.out.print("Departure Date: " + this.getDepartureDate() + " - ");
        System.out.println("Departure Time: " + this.getDepartureTime());
        System.out.print("Arrival Date: " + this.getArrivalDate() + " - ");
        System.out.println("Arrival Time: " + this.getArrivalTime());
        System.out.print("Flight Duration: " + this.getDuration() + " minutes - ");
        System.out.print("Distance: " + this.getDistance() + " miles - ");
        System.out.println("Time Zone Difference: " + this.airport.getTimeZoneDifference());
        System.out.println("Total Seats: " + this.getTotalSeats());
        System.out.print("First Class Seats: " + this.getFirstClassSeats() + " - ");
        System.out.print("Business Class Seats: " + this.getBusinessClassSeats() + " - ");
        System.out.println("Main Cabin Seats: " + this.getMainCabinSeats());
        System.out.print("First Class Price: " + this.getFirstClassPrice() + " - ");
        System.out.print("Business Class Price: " + this.getBusinessClassPrice() + " - ");
        System.out.println("Main Cabin Price: " + this.getMainCabinPrice());
        System.out.println("-----------------------------------------------------------------------------");       
    }
//
}

