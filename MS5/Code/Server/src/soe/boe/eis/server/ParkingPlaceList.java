package soe.boe.eis.server;

import java.util.List;

public class ParkingPlaceList {

	private List<ParkingPlace> list;

	/**
	 * Constructor.
	 * 
	 * nothing to do here
	 * 
	 * @param list
	 *            the list of parking Places
	 */
	public ParkingPlaceList(List<ParkingPlace> list) {
		this.list = list;
	}

	/**
	 * gets the list of ParkingPlaces
	 * 
	 * @return the list of parking Places
	 */
	public List<ParkingPlace> getList() {
		return list;
	}

	/**
	 * updates the list of ParkingPlaces
	 * 
	 * @param list
	 *            the list of parking Places
	 */
	public void setList(List<ParkingPlace> list) {
		this.list = list;
	}

}
