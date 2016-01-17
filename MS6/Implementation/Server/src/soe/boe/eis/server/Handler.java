package soe.boe.eis.server;

public class Handler extends Thread {

	// nächste Handler in der Liste
	private Handler follower;

	/**
	 * Constructor.
	 * 
	 * nothing to do here
	 */
	public Handler() {
		// Nothing to do
	}

	/**
	 * Checks wheather the handler has an follower
	 * 
	 * @return <tt>true</tt> only if one is avaiable
	 */
	public boolean hasFollower() {
		return follower != null;
	}

	/**
	 * get the following handler
	 * 
	 * @return the following handler
	 */
	public Handler getFollower() {
		return follower;
	}

	/**
	 * sets the following handler
	 * 
	 * @param pHandler
	 *            the following handler
	 */
	public void setFollower(Handler pHandler) {
		follower = pHandler;
	}

}
