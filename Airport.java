/*
    March 25, 2023 (CASS Extended Deadline - Original: March 22, 2023)
    Programming Assignment 3
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

// Change code -> Only one airport within class 
// Constructor will now have two objects with one for origin, another one for departure

// This class is abstract and will extend onto the International or Domestic classes
// It focuses on keeping the basic information for both types of flights
// It has origin and departing information based on name and code, as well as the time zone difference between locations
public class Airport {
    private String originAirport;
    private String originAirportCode; // Origin Code in file
    private boolean originAirportHasLounge;
    private double originAirportFee;
    private String originAirportCity;
    private String originAirportState;
    private String originAirportCountry;
    private String destinationAirport;
    private String destinationAirportCode; // Destination Code in file
    private boolean destinationAirportHasLounge;
    private double destinationAirportFee;
    private String destinationAirportCity;
    private String destinationAirportState;
    private String destinationAirportCountry;
    private int timeZoneDifference;

    // Default constructor
    /**
     * Default constructor
     */
    public Airport() {

    }

    // Constructor with given attributes
    /**
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
     * Creates Airport object based off file contents
     */
    public Airport(String originAirportIn, String originAirportCodeIn, boolean originAirportHasLoungeIn, double originAirportFeeIn, String originAirportCityIn, String originAirportStateIn, String originAirportCountryIn, String destinationAirportIn, String destinationAirportCodeIn, boolean destinationAirportHasLoungeIn, double destinationAirportFeeIn, String destinationAirportCityIn, String destinationAirportStateIn, String destinationAirportCountryIn, int timeZoneDifferenceIn) {
        this.originAirport = originAirportIn;
        this.originAirportCode = originAirportCodeIn;
        this.originAirportHasLounge = originAirportHasLoungeIn;
        this.originAirportFee = originAirportFeeIn;
        this.originAirportCity = originAirportCityIn;
        this.originAirportState = originAirportStateIn;
        this.originAirportCountry = originAirportCountryIn;  
        this.destinationAirport = destinationAirportIn;
        this.destinationAirportCode = destinationAirportCodeIn;
        this.destinationAirportHasLounge = destinationAirportHasLoungeIn;
        this.destinationAirportFee = destinationAirportFeeIn;
        this.destinationAirportCity = destinationAirportCityIn;
        this.destinationAirportState = destinationAirportStateIn;
        this.timeZoneDifference = timeZoneDifferenceIn; 
    }

    // Getters and setters
    /** 
     * @return origin airport
     */
    public String getOriginAirport() {
        return this.originAirport;
    }

    /**
     * @param originAirport
     * Sets origin airport
     */
    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    /**
     * @return origin airport code
     */
    public String getOriginAirportCode() {
        return this.originAirportCode;
    }

    /**
     * @param originAirportCode
     * sets origin airport code
     */
    public void setOriginAirportCode(String originAirportCode) {
        this.originAirportCode = originAirportCode;
    }

    /**
     * @return if origin airport has a lounge
     */
    public boolean isOriginAirportHasLounge() {
        return this.originAirportHasLounge;
    }

    /**
     * @param originAirportHasLounge
     * Sets if origin airport has a lounge
     */
    public void setOriginAirportHasLounge(boolean originAirportHasLounge) {
        this.originAirportHasLounge = originAirportHasLounge;
    }

    /**
     * @return origin airport fee
     */
    public double getOriginAirportFee() {
        return this.originAirportFee;
    }

    /**
     * @param originAirportFee
     * sets origin airport fee
     */
    public void setOriginAirportFee(double originAirportFee) {
        this.originAirportFee = originAirportFee;
    }

    
    /** 
     * @return origin airport city
     */
    public String getOriginAirportCity() {
        return this.originAirportCity;
    }

    /**
     * @param originAirportCity
     * sets origin airport city
     */
    public void setOriginAirportCity(String originAirportCity) {
        this.originAirportCity = originAirportCity;
    }

    /** 
     * @return origin airport state
     */
    public String getOriginAirportState() {
        return this.originAirportState;
    }

    /**
     * @param originAirportState
     * sets origin airport state
     */
    public void setOriginAirportState(String originAirportState) {
        this.originAirportState = originAirportState;
    }

    /**
     * @return origin airport country
     */
    public String getOriginAirportCountry() {
        return this.originAirportCountry;
    }

    /**
     * @param originAirportCountry
     * sets origin airport country
     */
    public void setOriginAirportCountry(String originAirportCountry) {
        this.originAirportCountry = originAirportCountry;
    }

    /**
     * @return destination airport
     */
    public String getDestinationAirport() {
        return this.destinationAirport;
    }


    /** 
     * @param destinationAirport
     * Sets Destination Airport
     */
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * @return destination airport code
     */
    public String getDestinationAirportCode() {
        return this.destinationAirportCode;
    }

    /**
     * @param destinationAirportCode
     * sets destination airport code
     */
    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    /**
     * @return if destination airport has a lounge
     */
    public boolean isDestinationAirportHasLounge() {
        return this.destinationAirportHasLounge;
    }

    /**
     * @param destinationAirportHasLounge
     * sets if destination airport has a lounge
     */
    public void setDestinationAirportHasLounge(boolean destinationAirportHasLounge) {
        this.destinationAirportHasLounge = destinationAirportHasLounge;
    }

    /**
     * @return destination airport fee
     */
    public double getDestinationAirportFee() {
        return this.destinationAirportFee;
    }

    /**
     * @param destinationAirportFee
     * sets destination airport fee
     */
    public void setDestinationAirportFee(double destinationAirportFee) {
        this.destinationAirportFee = destinationAirportFee;
    }

    /**
     * @return destination airport city
     */
    public String getDestinationAirportCity() {
        return this.destinationAirportCity;
    }

    /**
     * @param destinationAirportCity
     * sets destination airport city
     */
    public void setDestinationAirportCity(String destinationAirportCity) {
        this.destinationAirportCity = destinationAirportCity;
    }

    /**
     * @return destination airport state
     */
    public String getDestinationAirportState() {
        return this.destinationAirportState;
    }

    /**
     * @param destinationAirportState
     * sets destination airport state
     */
    public void setDestinationAirportState(String destinationAirportState) {
        this.destinationAirportState = destinationAirportState;
    }

    /**
     * @return destination airport country
     */
    public String getDestinationAirportCountry() {
        return this.destinationAirportCountry;
    }

    /**
     * @param destinationAirportCountry
     * sets destination airport country
     */
    public void setDestinationAirportCountry(String destinationAirportCountry) {
        this.destinationAirportCountry = destinationAirportCountry;
    }

    /**
     * @return time zone difference
     */
    public int getTimeZoneDifference() {
        return this.timeZoneDifference;
    }

    /**
     * @param timeZoneDifference
     * sets time sone difference
     */
    public void setTimeZoneDifference(int timeZoneDifference) {
        this.timeZoneDifference = timeZoneDifference;
    }

    public static void printOriginAirport() {
        System.out.println("ESTOY HARTA YA BASTAAAA");
        System.out.println("Action items <3");
        System.out.println("Agregar methods para obtener info de flight con code (4) + JavaDoc");
        System.out.println("Deshacer airport class -> Add to flight with 2 airport objs + csv + money and shenanigans");
        System.out.println("Autopurchaser: New class + map processing within file -> Diferentes opciones");
        System.out.println("Si se puede -> Thr all day coding challenge");
    }

}
