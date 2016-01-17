package soe.boe.eis.thparkplatzapp;

import java.sql.Time;

public class ParkingPlace {

	private int id;
	private int parkingPlaces;
	private int freeParkingPlaces;
	private double longitude;
	private double latitude;
	private String name;
	private double distanceToFH;
	private String addressStreet;
	private String addressNumber;
	private int addressPostalCode;
	private String addressCity;
	private OpeningTimeObject openingtimes;
	private Time freeTime;
	private double priceFirstHour;
	private double priveFurtherHour;
	private boolean parkingPlacesForWomen;
	private boolean parkingPlacesForHandicapped;
	private boolean avaiableForProffessors;
	private boolean avaiableForWorkers;
	private boolean avaiableForStudents;
	private boolean avaiableForGuests;

	/**
	 * Constructor.
	 * 
	 * nothing to do here
	 * 
	 * @param id
	 *            the id
	 * @param parkingPlaces
	 *            the number of parking places
	 * @param freeParkingPlaces
	 *            the number of free parking places
	 * @param longitude
	 *            the longitude of the parking place
	 * @param latitude
	 *            the latitude of the parking place
	 * @param name
	 *            the name of the parking place
	 * @param distanceToFH
	 *            the distance to the fh building
	 * @param addressStreet
	 *            the street of the parking place
	 * @param addressNumber
	 *            the house number of the parking place
	 * @param addressPostalCode
	 *            the postal code of the parking place
	 * @param addressCity
	 *            the city of the parking place
	 * @param openingTimes
	 *            the opening times
	 * @param freeTime
	 *            the time you don't have to pay
	 * @param priceFirstHour
	 *            the price for the first hour
	 * @param priveFurtherHour
	 *            the price for every further hour
	 * @param parkingPlacesForWomen
	 *            the avaiability of parking places for women
	 * @param parkingPlacesForHandicapped
	 *            the avaiability of parking places for handicapped persons
	 * @param avaiableForProffessors
	 *            the avaiability for proffessors
	 * @param avaiableForWorkers
	 *            the avaiability for workers
	 * @param avaiableForStudents
	 *            the avaiability for students
	 * @param avaiableForGuests
	 *            the avaiability for guests
	 */
	public ParkingPlace(int id, int parkingPlaces, int freeParkingPlaces, double longitude, double latitude,
			String name, double distanceToFH, String addressStreet, String addressNumber, int addressPostalCode,
			String addressCity, OpeningTimeObject openingTimes, Time freeTime, double priceFirstHour,
			double priveFurtherHour, boolean parkingPlacesForWomen, boolean parkingPlacesForHandicapped,
			boolean avaiableForProffessors, boolean avaiableForWorkers, boolean avaiableForStudents,
			boolean avaiableForGuests) {

		this.id = id;
		this.parkingPlaces = parkingPlaces;
		this.freeParkingPlaces = freeParkingPlaces;
		this.longitude = longitude;
		this.latitude = latitude;
		this.name = name;
		this.distanceToFH = distanceToFH;
		this.addressStreet = addressStreet;
		this.addressNumber = addressNumber;
		this.addressPostalCode = addressPostalCode;
		this.addressCity = addressCity;
		this.setOpeningtimes(openingTimes);
		this.freeTime = freeTime;
		this.priceFirstHour = priceFirstHour;
		this.priveFurtherHour = priveFurtherHour;
		this.parkingPlacesForWomen = parkingPlacesForWomen;
		this.parkingPlacesForHandicapped = parkingPlacesForHandicapped;
		this.avaiableForProffessors = avaiableForProffessors;
		this.avaiableForWorkers = avaiableForWorkers;
		this.avaiableForStudents = avaiableForStudents;
		this.avaiableForGuests = avaiableForGuests;

	}

	/**
	 * gets the id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * sets the id
	 * 
	 * @param id
	 *            the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * gets the number of parking places
	 * 
	 * @return the number of parking places
	 */
	public int getParkingPlaces() {
		return parkingPlaces;
	}

	/**
	 * sets the number of parking places
	 * 
	 * @param parkingPlaces
	 *            the number of parking places
	 */
	public void setParkingPlaces(int parkingPlaces) {
		this.parkingPlaces = parkingPlaces;
	}

	/**
	 * gets the number of free parking places
	 * 
	 * @return the number of free parking places
	 */
	public int getFreeParkingPlaces() {
		return freeParkingPlaces;
	}

	/**
	 * sets the number of free parking places
	 * 
	 * @param freeParkingPlaces
	 *            the number of free parking places
	 */
	public void setFreeParkingPlaces(int freeParkingPlaces) {
		this.freeParkingPlaces = freeParkingPlaces;
	}

	/**
	 * gets the longitude of the parking place
	 * 
	 * @return the longitude of the parking place
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * sets the longitude of the parking place
	 * 
	 * @param longitude
	 *            the longitude of the parking place
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * gets the latitude of the parking place
	 * 
	 * @return the latitude of the parking place
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * sets the latitude of the parking place
	 * 
	 * @param latitude
	 *            the latitude of the parking place
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * gets the name of the parking place
	 * 
	 * @return the name of the parking place
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the parking place
	 * 
	 * @param name
	 *            the name of the parking place
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gets the distance to the fh building
	 * 
	 * @return the distance to the fh building
	 */
	public double getDistanceToFH() {
		return distanceToFH;
	}

	/**
	 * sets the distance to the fh building
	 * 
	 * @param distanceToFH
	 *            the distance to the fh building
	 */
	public void setDistanceToFH(double distanceToFH) {
		this.distanceToFH = distanceToFH;
	}

	/**
	 * gets the street of the parking place
	 * 
	 * @return the street of the parking place
	 */
	public String getAddressStreet() {
		return addressStreet;
	}

	/**
	 * sets the street of the parking place
	 * 
	 * @param addressStreet
	 *            the street of the parking place
	 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	/**
	 * gets the house number of the parking place
	 * 
	 * @return the house number of the parking place
	 */
	public String getAddressNumber() {
		return addressNumber;
	}

	/**
	 * sets the house number of the parking place
	 * 
	 * @param addressNumber
	 *            the house number of the parking place
	 */
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	/**
	 * gets the postal code of the parking place
	 * 
	 * @return the postal code of the parking place
	 */
	public int getAddressPostalCode() {
		return addressPostalCode;
	}

	/**
	 * sets the postal code of the parking place
	 * 
	 * @param addressPostalCode
	 *            the postal code of the parking place
	 */
	public void setAddressPostalCode(int addressPostalCode) {
		this.addressPostalCode = addressPostalCode;
	}

	/**
	 * gets the city of the parking place
	 * 
	 * @return the city of the parking place
	 */
	public String getAddressCity() {
		return addressCity;
	}

	/**
	 * sets the city of the parking place
	 * 
	 * @param addressCity
	 *            the city of the parking place
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	/**
	 * gets the opening times
	 * 
	 * @return the opening times
	 */
	public OpeningTimeObject getOpeningtimes() {
		return openingtimes;
	}

	/**
	 * sets the opening times
	 * 
	 * @param openingtimes
	 *            the opening times
	 */
	public void setOpeningtimes(OpeningTimeObject openingtimes) {
		this.openingtimes = openingtimes;
	}

	/**
	 * gets the time you don't have to pay
	 * 
	 * @return the time you don't have to pay
	 */
	public Time getFreeTime() {
		return freeTime;
	}

	/**
	 * sets the time you don't have to pay
	 * 
	 * @param freeTime
	 *            the time you don't have to pay
	 */
	public void setFreeTime(Time freeTime) {
		this.freeTime = freeTime;
	}

	/**
	 * gets the price for the first hour
	 * 
	 * @return the price for the first hour
	 */
	public double getPriceFirstHour() {
		return priceFirstHour;
	}

	/**
	 * sets the price for the first hour
	 * 
	 * @param priceFirstHour
	 *            the price for the first hour
	 */
	public void setPriceFirstHour(double priceFirstHour) {
		this.priceFirstHour = priceFirstHour;
	}

	/**
	 * gets the price for every further hour
	 * 
	 * @return the price for every further hour
	 */
	public double getPriveFurtherHour() {
		return priveFurtherHour;
	}

	/**
	 * sets the price for every further hour
	 * 
	 * @param priveFurtherHour
	 *            the price for every further hour
	 */
	public void setPriveFurtherHour(double priveFurtherHour) {
		this.priveFurtherHour = priveFurtherHour;
	}

	/**
	 * gets the avaiability of parking places for women
	 * 
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean isParkingPlacesForWomen() {
		return parkingPlacesForWomen;
	}

	/**
	 * sets the avaiability of parking places for women
	 * 
	 * @param parkingPlacesForWomen
	 *            the avaiability of parking places for women
	 */
	public void setParkingPlacesForWomen(boolean parkingPlacesForWomen) {
		this.parkingPlacesForWomen = parkingPlacesForWomen;
	}

	/**
	 * gets the avaiability of parking places for handicapped persons
	 * 
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean isParkingPlacesForHandicapped() {
		return parkingPlacesForHandicapped;
	}

	/**
	 * sets the avaiability of parking places for handicapped persons
	 * 
	 * @param parkingPlacesForHandicapped
	 *            the avaiability of parking places for handicapped persons
	 */
	public void setParkingPlacesForHandicapped(boolean parkingPlacesForHandicapped) {
		this.parkingPlacesForHandicapped = parkingPlacesForHandicapped;
	}

	/**
	 * gets the avaiability for proffessors
	 * 
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean isAvaiableForProffessors() {
		return avaiableForProffessors;
	}

	/**
	 * sets the avaiability for proffessors
	 * 
	 * @param avaiableForProffessors
	 *            the avaiability for proffessors
	 */
	public void setAvaiableForProffessors(boolean avaiableForProffessors) {
		this.avaiableForProffessors = avaiableForProffessors;
	}

	/**
	 * gets the avaiability for workers
	 * 
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean isAvaiableForWorkers() {
		return avaiableForWorkers;
	}

	/**
	 * sets the avaiability for workers
	 * 
	 * @param avaiableForWorkers
	 *            the avaiability for workers
	 */
	public void setAvaiableForWorkers(boolean avaiableForWorkers) {
		this.avaiableForWorkers = avaiableForWorkers;
	}

	/**
	 * gets the avaiability for students
	 * 
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean isAvaiableForStudents() {
		return avaiableForStudents;
	}

	/**
	 * sets the avaiability for students
	 * 
	 * @param avaiableForStudents
	 *            the avaiability for students
	 */
	public void setAvaiableForStudents(boolean avaiableForStudents) {
		this.avaiableForStudents = avaiableForStudents;
	}

	/**
	 * gets the avaiability for guests
	 * 
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean isAvaiableForGuests() {
		return avaiableForGuests;
	}

	/**
	 * sets the avaiability for guests
	 * 
	 * @param avaiableForGuests
	 *            the avaiability for guests
	 */
	public void setAvaiableForGuests(boolean avaiableForGuests) {
		this.avaiableForGuests = avaiableForGuests;
	}

}
