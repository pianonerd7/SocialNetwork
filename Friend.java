package hw5;

/**
 * The friends class, which represents the nodes of the neighborhood.
 * @author Jiaxin He, jxh604@case.edu
 *
 */
public class Friend {

	/** The distance between this friend and the user. */
	private Integer distance;
	
	/** The user of this friendship */
	private User user;

	/** initializes a friend that does not throw an exception */
	public Friend() {
		this.distance = null;
	}

	/**
	 * Sets the user of this friend at the given distance
	 * @param user The user being set
	 * @param distance The distance from friend
	 * @return True if successful, false otherwise
	 */
	public boolean set (User user, Integer distance) {

		if (user.isFriendValid()) {
			return false;
		}

		this.distance = distance;
		this.user = user;
		user.setIsFriendValid(true);
		return true;
	}

	/**
	 * Returns the user of this friendship.
	 * @return User of this friendship.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Returns the distance between the user in the friend.
	 * @return The interger representation of the distance.
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * Sets the distance between the user and the friend.
	 * @param distance The distance between the user and the friend.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	/**
	 * Returns something ligible when friend tostring is called.
	 * @return The string returned
	 */
	public String toString() {
		
		if (!getUser().isFriendValid()) {
			return "Invalid Friend";
		}

		return getUser() + " friend at the distance " + getDistance()+"";
	}
}
