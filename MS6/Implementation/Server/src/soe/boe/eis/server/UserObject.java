package soe.boe.eis.server;

public class UserObject {

	private String email = "";
	private String password = "";
	private int userGroup = 4;

	/**
	 * Constructor.
	 * 
	 * @param pEmail
	 *            the email of the user
	 * @param pPassword
	 *            the password of the user
	 * @param pUserGroup
	 *            the userGroup of the user
	 */
	public UserObject(String pEmail, String pPassword, int pUserGroup) {

		email = pEmail;
		password = pPassword;
		userGroup = pUserGroup;

	}

	/**
	 * updates the mail address
	 * 
	 * @param pEmail
	 *            the email of the user
	 */
	public void setEmail(String pEmail) {
		email = pEmail;
	}

	/**
	 * gets the mail address
	 * 
	 * @return the email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * updates the passwword
	 * 
	 * @param pPassword
	 *            the password of the user
	 */
	public void setPassword(String pPassword) {
		password = pPassword;
	}

	/**
	 * gets the password
	 * 
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * updates the userGroup
	 * 
	 * @param pUserGroup
	 *            the userGroup of the user
	 */
	public void setUserGroup(int pUserGroup) {
		userGroup = pUserGroup;
	}

	/**
	 * gets the userGroup
	 * 
	 * @return the userGroup of the user
	 */
	public int getUserGroup() {
		return userGroup;
	}

}
