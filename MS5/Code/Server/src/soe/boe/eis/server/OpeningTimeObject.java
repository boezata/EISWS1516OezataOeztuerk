package soe.boe.eis.server;

import java.sql.Time;

public class OpeningTimeObject {

	private Time mondayOpening;
	private Time mondayClosing;
	private Time tuesdayOpening;
	private Time tuesdayClosing;
	private Time wednesdayOpening;
	private Time wednesdayClosing;
	private Time thursdayOpening;
	private Time thursdayClosing;
	private Time fridayOpening;
	private Time fridayClosing;
	private Time saturdayOpening;
	private Time saturdayClosing;
	private Time sundayOpening;
	private Time sundayClosing;

	/**
	 * Constructor.
	 * 
	 * nothing to do here
	 * 
	 * @param mondayOpening
	 *            the opening time on monday
	 * @param mondayClosing
	 *            the closing time on monday
	 * @param tuesdayOpening
	 *            the opening time on tuesday 
	 * @param tuesdayClosing
	 *            the closing time on tuesday 
	 * @param wednesdayOpening
	 *            the opening time on wednesday 
	 * @param wednesdayClosing
	 *            the closing time on wednesday 
	 * @param thursdayOpening
	 *            the opening time on thursday
	 * @param thursdayClosing
	 *            the closing time on thursday
	 * @param fridayOpening
	 *            the opening time on friday 
	 * @param fridayClosing
	 *            the closing time on friday
	 * @param saturdayOpening
	 *            the opening time on saturday
	 * @param saturdayClosing
	 *            the closing time on saturday
	 * @param sundayOpening
	 *            the opening time on sunday 
	 * @param sundayClosing
	 *            the closing time on sunday 
	 */
	public OpeningTimeObject(Time mondayOpening, Time mondayClosing, Time tuesdayOpening, Time tuesdayClosing,
			Time wednesdayOpening, Time wednesdayClosing, Time thursdayOpening, Time thursdayClosing,
			Time fridayOpening, Time fridayClosing, Time saturdayOpening, Time saturdayClosing, Time sundayOpening,
			Time sundayClosing) {

		this.setMondayOpening(mondayOpening);
		this.setMondayClosing(mondayClosing);
		this.setTuesdayOpening(tuesdayOpening);
		this.setTuesdayClosing(tuesdayClosing);
		this.setWednesdayOpening(wednesdayOpening);
		this.setWednesdayClosing(wednesdayClosing);
		this.setThursdayOpening(thursdayOpening);
		this.setThursdayClosing(thursdayClosing);
		this.setFridayOpening(fridayOpening);
		this.setFridayClosing(fridayClosing);
		this.setSaturdayOpening(saturdayOpening);
		this.setSaturdayClosing(saturdayClosing);
		this.setSundayOpening(sundayOpening);
		this.setSundayClosing(sundayClosing);
		
	}

	/**
	 * gets the opening time on monday
	 * 
	 * @return the opening time on monday
	 */
	public Time getMondayOpening() {
		return mondayOpening;
	}

	/**
	 * sets the opening time on monday
	 * 
	 * @param mondayOpening
	 *            the opening time on monday
	 */
	public void setMondayOpening(Time mondayOpening) {
		this.mondayOpening = mondayOpening;
	}

	/**
	 * gets the closing time on monday
	 * 
	 * @return the closing time on monday
	 */
	public Time getMondayClosing() {
		return mondayClosing;
	}

	/**
	 * sets the closing time on monday
	 * 
	 * @param mondayClosing
	 *            the closing time on monday
	 */
	public void setMondayClosing(Time mondayClosing) {
		this.mondayClosing = mondayClosing;
	}

	/**
	 * gets the opening time on tuesday
	 * 
	 * @return the opening time on tuesday
	 */
	public Time getTuesdayOpening() {
		return tuesdayOpening;
	}

	/**
	 * sets the opening time on tuesday
	 * 
	 * @param tuesdayOpening
	 *            the opening time on tuesday 
	 */
	public void setTuesdayOpening(Time tuesdayOpening) {
		this.tuesdayOpening = tuesdayOpening;
	}

	/**
	 * gets the closing time on tuesday
	 * 
	 * @return the closing time on tuesday
	 */
	public Time getTuesdayClosing() {
		return tuesdayClosing;
	}

	/**
	 * sets the closing time on tuesday
	 * 
	 * @param tuesdayClosing
	 *            the closing time on tuesday 
	 */
	public void setTuesdayClosing(Time tuesdayClosing) {
		this.tuesdayClosing = tuesdayClosing;
	}

	/**
	 * gets the opening time on wednesday
	 * 
	 * @return the opening time on wednesday
	 */
	public Time getWednesdayOpening() {
		return wednesdayOpening;
	}

	/**
	 * sets the opening time on wednesday
	 * 
	 * @param wednesdayOpening
	 *            the opening time on wednesday 
	 */
	public void setWednesdayOpening(Time wednesdayOpening) {
		this.wednesdayOpening = wednesdayOpening;
	}

	/**
	 * gets the closing time on wednesday
	 * 
	 * @return the closing time on wednesday
	 */
	public Time getWednesdayClosing() {
		return wednesdayClosing;
	}

	/**
	 * sets the closing time on wednesday
	 * 
	 * @param wednesdayClosing
	 *            the closing time on wednesday 
	 */
	public void setWednesdayClosing(Time wednesdayClosing) {
		this.wednesdayClosing = wednesdayClosing;
	}

	/**
	 * gets the opening time on thursday
	 * 
	 * @return the opening time on thursday
	 */
	public Time getThursdayOpening() {
		return thursdayOpening;
	}

	/**
	 * sets the opening time on thursday
	 * 
	 * @param thursdayOpening
	 *            the opening time on thursday
	 */
	public void setThursdayOpening(Time thursdayOpening) {
		this.thursdayOpening = thursdayOpening;
	}

	/**
	 * gets the closing time on thursday
	 * 
	 * @return the closing time on thursday
	 */
	public Time getThursdayClosing() {
		return thursdayClosing;
	}

	/**
	 * sets the closing time on thursday
	 * 
	 * @param thursdayClosing
	 *            the closing time on thursday
	 */
	public void setThursdayClosing(Time thursdayClosing) {
		this.thursdayClosing = thursdayClosing;
	}

	/**
	 * gets the opening time on friday
	 * 
	 * @return the opening time on friday
	 */
	public Time getFridayOpening() {
		return fridayOpening;
	}

	/**
	 * sets the opening time on friday
	 * 
	 * @param fridayOpening
	 *            the opening time on friday 
	 */
	public void setFridayOpening(Time fridayOpening) {
		this.fridayOpening = fridayOpening;
	}

	/**
	 * gets the closing time on friday
	 * 
	 * @return the closing time on friday
	 */
	public Time getFridayClosing() {
		return fridayClosing;
	}

	/**
	 * sets the closing time on friday
	 * 
	 * @param fridayClosing
	 *            the closing time on friday
	 */
	public void setFridayClosing(Time fridayClosing) {
		this.fridayClosing = fridayClosing;
	}

	/**
	 * gets the opening time on saturday
	 * 
	 * @return the opening time on saturday
	 */
	public Time getSaturdayOpening() {
		return saturdayOpening;
	}

	/**
	 * sets the opening time on saturday
	 * 
	 * @param saturdayOpening
	 *            the opening time on saturday
	 */
	public void setSaturdayOpening(Time saturdayOpening) {
		this.saturdayOpening = saturdayOpening;
	}

	/**
	 * gets the closing time on saturday
	 * 
	 * @return the closing time on saturday
	 */
	public Time getSaturdayClosing() {
		return saturdayClosing;
	}

	/**
	 * sets the closing time on saturday
	 * 
	 * @param saturdayClosing
	 *            the closing time on saturday
	 */
	public void setSaturdayClosing(Time saturdayClosing) {
		this.saturdayClosing = saturdayClosing;
	}

	/**
	 * gets the opening time on sunday
	 * 
	 * @return the opening time on sunday
	 */
	public Time getSundayOpening() {
		return sundayOpening;
	}

	/**
	 * sets the opening time on sunday
	 * 
	 * @param sundayOpening
	 *            the opening time on sunday 
	 */
	public void setSundayOpening(Time sundayOpening) {
		this.sundayOpening = sundayOpening;
	}

	/**
	 * gets the closing time on sunday
	 * 
	 * @return the closing time on sunday
	 */
	public Time getSundayClosing() {
		return sundayClosing;
	}

	/**
	 * sets the closing time on sunday
	 * 
	 * @param sundayClosing
	 *            the closing time on sunday 
	 */
	public void setSundayClosing(Time sundayClosing) {
		this.sundayClosing = sundayClosing;
	}

}
