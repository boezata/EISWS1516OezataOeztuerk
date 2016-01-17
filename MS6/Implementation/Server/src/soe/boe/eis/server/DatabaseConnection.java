package soe.boe.eis.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

	private static String url;
	private Statement st;
	private ResultSet rs;
	private String ben;
	private String pw;

	/**
	 * Constructor.
	 * 
	 * gets the connection datas
	 */
	public DatabaseConnection() {
		
		url = Settings.getInstance().getDatabaseLink();
		ben = Settings.getInstance().getDatabaseUserName();
		pw = Settings.getInstance().getDatabasePassword();
		
	}

	/**
	 * connects the database Server
	 * 
	 * @return <tt>true</tt> only if this was succesfull
	 */
	public boolean connect() {

		try {

			Connection conn = DriverManager.getConnection(url, ben, pw);

			st = conn.createStatement();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	/**
	 * Checks wheather an parkingPlaceID is avaiable
	 * 
	 * @param parkingPlaceNumber
	 *            the id of the parking place
	 * @return <tt>true</tt> only if is it is avaiable
	 */
	public boolean parkingPlaceIDAvaiable(int parkingPlaceNumber) {

		try {

			rs = st.executeQuery("SELECT COUNT(*) FROM `parkingPlaces` WHERE `id` = " + parkingPlaceNumber);

			if (rs.next()) {

				if (rs.getInt(1) == 1) {
					return true;
				} else {
					return false;
				}

			}

			return false;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	/**
	 * Checks wheather an parkingPlaceID with the checkcode is avaiable
	 * 
	 * @param parkingPlaceNumber
	 *            the id of the parking place
	 * @param sCheckCode
	 *            the checksum of the parking place
	 * @return <tt>true</tt> only if is it is avaiable
	 */
	public boolean checkParkingPlaceSoftwareLogin(int parkingPlaceNumber, String sCheckCode) {

		try {

			rs = st.executeQuery("SELECT COUNT(*) FROM `parkingPlaces` WHERE `id` = " + parkingPlaceNumber
					+ " AND `secCode` = \"" + sCheckCode + "\";");

			if (rs.next()) {

				if (rs.getInt(1) == 1) {
					return true;
				} else {
					return false;
				}

			}

			return false;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	/**
	 * decreases the number of free parking places
	 * 
	 * @param id
	 *            the id of the parking place
	 * @return <tt>Enums.decreaseOfNumberOfFreeParkingPlacesSuccesFull</tt> only if it was succesfull <tt>Enums.FailOfDecreasingOfNumberOfFreeParkingPlacesBecauseOfTooLessParkingPlaces</tt> only if too less
	 *         parkingplaces to degrees <tt>Enums.FailOfDecreasingOfNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery</tt> only if one of the queries
	 *         failed
	 */
	public int decreaseNumberOfFreeParkingPlaces(int id) {

		try {

			rs = st.executeQuery("SELECT `freeParkingPlaces` FROM `parkingPlaces` WHERE `id` =" + id + ";");

			if (rs.next()) {

				if ((rs.getInt(1) - 1) >= 0) {

					st.execute("UPDATE `parkingPlaces` SET `freeParkingPlaces`=" + (rs.getInt(1) - 1) + " WHERE `id`="
							+ id + ";");

					return Enums.decreaseOfNumberOfFreeParkingPlacesSuccesFull;

				} else {
					return Enums.FailOfDecreasingOfNumberOfFreeParkingPlacesBecauseOfTooLessParkingPlaces; // da waren dann negativ Parkplätze frei
				}

			}

			return Enums.FailOfDecreasingOfNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Enums.FailOfDecreasingOfNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery;

	}

	/**
	 * increases the number of free parking places
	 * 
	 * @param id
	 *            the id of the parking place
	 * @return <tt>Enums.increaseOfNumberOfFreeParkingPlacesSuccesFull</tt> only if it was succesfull <tt>Enums.FailOfIncreasingOfNumberOfFreeParkingPlacesBecauseOfTooMuchFreeParkingPlaces</tt> only if too much
	 *         parkingplaces to degrees <tt>Enums.FailOfIncreasingOfNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery</tt> only if one of the queries
	 *         failed
	 */
	public int increaseNumberOfFreeParkingPlaces(int id) {

		try {

			rs = st.executeQuery(
					"SELECT `parkingPlaces`, `freeParkingPlaces` FROM `parkingPlaces` WHERE `id` = " + id + ";");

			if (rs.next()) {

				if (rs.getInt(1) >= (rs.getInt(2) + 1)) {

					st.execute("UPDATE `parkingPlaces` SET `freeParkingPlaces`=" + (rs.getInt(1) + 1) + " WHERE `id`="
							+ id + ";");

					return Enums.increaseOfNumberOfFreeParkingPlacesSuccesFull;

				} else {
					return Enums.FailOfIncreasingOfNumberOfFreeParkingPlacesBecauseOfTooMuchFreeParkingPlaces; // da wären zu viele Parkplätze frei
				}

			}

			return Enums.FailOfIncreasingOfNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Enums.FailOfIncreasingOfNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery;

	}

	/**
	 * sets the number of free parking places
	 * 
	 * @param id
	 *            the id of the parking place
	 * @param pNumberOfFreeParkingPlaces
	 *            the number of free parking place
	 * @return <tt>Enums.settingOfNumberOfFreeParkingPlacesSuccesFull</tt> only if it was succesfull <tt>Enums.FailOfSettingNumberOfFreeParkingPlacesBecauseOfTooHighOrTooSmallNumberOfFreeParkingPlaces</tt> only if too less
	 *         or too much parkingplaces to degrees <tt>Enums.FailOfSettingNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery</tt> only if one of
	 *         the queries failed
	 */
	public int setValueOfFreePakingPlaces(int id, int pNumberOfFreeParkingPlaces) {

		try {

			rs = st.executeQuery("SELECT `parkingPlaces`  FROM `parkingPlaces` WHERE `id` =" + id + ";");

			if (rs.next()) {

				if (rs.getInt(1) >= pNumberOfFreeParkingPlaces && pNumberOfFreeParkingPlaces >= 0) {

					st.execute("UPDATE `parkingPlaces` SET `freeParkingPlaces`=" + pNumberOfFreeParkingPlaces
							+ " WHERE `id`=" + id + ";");

					return Enums.settingOfNumberOfFreeParkingPlacesSuccesFull;

				} else {
					return Enums.FailOfSettingNumberOfFreeParkingPlacesBecauseOfTooHighOrTooSmallNumberOfFreeParkingPlaces; // ungültiger Zahlenwert
				}

			}

			return Enums.FailOfSettingNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Enums.FailOfSettingNumberOfFreeParkingPlacesBecauseOfFailedDatebaseQuery;

	}

	/**
	 * saves a new user
	 * 
	 * @param newUserObject
	 *            the datas of the new user
	 * @return <tt>true</tt> only if it was successfull
	 */
	public boolean saveNewUser(UserObject newUserObject) {


		if (checkAvaiablityOfMailAdress(newUserObject.getEmail())) {

			String secCode = GeneralFunctions.generateNewSecCode();

			try {
				st.execute("INSERT INTO `user`(`email`, `password`, `usergroup`, `secCode`, `mailChecked`) VALUES (\""
					+ newUserObject.getEmail() + "\",\"" + newUserObject.getPassword() + "\","
					+ newUserObject.getUserGroup() + ",\"" + secCode + "\",\"N\")");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			MailController.sendNewUserMail(newUserObject, secCode);
			
			return true;

		}
		
		return false;

	}

	/**
	 * checks wheater the mail address is aviable
	 * 
	 * @param mailAdress
	 *            the mailadress to check
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean checkAvaiablityOfMailAdress(String mailAdress) {

		try {

			rs = st.executeQuery("SELECT COUNT(*) FROM `user` WHERE `email` = \"" + mailAdress + "\";");

			if (rs.next()) {

				if (rs.getInt(1) == 0) {
					return true;
				} else {
					return false;
				}

			}

			return false;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	/**
	 * checks wheater a user with this mailaddress and this password is aviable
	 * 
	 * @param mailAddress
	 *            the mail address to check
	 * @param password
	 *            the password to check
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean checkUserWithPassword(String mailAddress, String password) {

		try {

			rs = st.executeQuery("SELECT COUNT(*) FROM `user` WHERE `email` = \"" + mailAddress + "\" && password = \""
					+ password + "\";");

			if (rs.next()) {

				if (rs.getInt(1) == 1) {
					return true;
				} else {
					return false;
				}

			}

			return false;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	/**
	 * checks wheater an user with this mailaddress ist avaiable
	 * 
	 * @param mailAddress
	 *            the mail address to check
	 * @return <tt>true</tt> only if it is avaiable
	 */
	public boolean checkAvaiablityOfUser(String mailAddress) {

		try {

			rs = st.executeQuery("SELECT COUNT(*) FROM `user` WHERE `email` = \"" + mailAddress + "\";");

			if (rs.next()) {

				if (rs.getInt(1) == 1) {
					return true;
				} else {
					return false;
				}

			}

			return false;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	/**
	 * gets the checkcode of an user
	 * 
	 * @param mailAddress
	 *            the mailAddress of the user
	 * @return the checkcode
	 */
	public String getSecCodeForUser(String mailAddress) {

		try {

			rs = st.executeQuery("SELECT `secCode` FROM `user` WHERE `email` = \"" + mailAddress + "\";");

			if (rs.next()) {
				return rs.getString(1);
			}

			return "";

		} catch (SQLException e) {

			e.printStackTrace();

			return "";

		}

	}

	/**
	 * gets the userGroup of an user
	 * 
	 * @param mailAddress
	 *            the mailadress of the user
	 * @return the userGroup
	 */
	public int getUserGroupForEMail(String mailAddress) {

		try {

			rs = st.executeQuery("SELECT `usergroup` FROM `user` WHERE `email` = \"" + mailAddress + "\";");

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;

		} catch (SQLException e) {

			e.printStackTrace();

			return 0;

		}

	}

	/**
	 * gets all parkingPlaces which are avaiable for the usergroup
	 * 
	 * @param userGroup
	 *            the userGroup of the user
	 * @return the ParkingPlaceList
	 */
	public ParkingPlaceList getParkingPlacesArrayForUserGroup(int userGroup) {

		List<ParkingPlace> back = new ArrayList<ParkingPlace>();

		String type = "avaiableForGuests";

		switch (userGroup) {
		case 1:
			type = "avaiableForProffessors";
			break;
		case 2:
			type = "avaiableForStudents";
			break;
		case 3:
			type = "avaiableForWorkers";
			break;
		}

		try {

			rs = st.executeQuery("SELECT * FROM `parkingPlaces` WHERE `" + type + "` =1");

			while (rs.next()) {

				OpeningTimeObject openingTimes = new OpeningTimeObject(rs.getTime(13), rs.getTime(14), rs.getTime(15),
						rs.getTime(16), rs.getTime(17), rs.getTime(18), rs.getTime(19), rs.getTime(20), rs.getTime(21),
						rs.getTime(22), rs.getTime(23), rs.getTime(24), rs.getTime(25), rs.getTime(26));

				back.add(new ParkingPlace(rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6),
						rs.getString(7), rs.getDouble(8), rs.getString(9), rs.getString(10), rs.getInt(11),
						rs.getString(12), openingTimes, rs.getTime(27), rs.getDouble(28), rs.getDouble(29),
						rs.getBoolean(30), rs.getBoolean(31), rs.getBoolean(32), rs.getBoolean(33), rs.getBoolean(34),
						rs.getBoolean(35)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ParkingPlaceList(back);

	}

}
