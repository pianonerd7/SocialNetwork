package hw5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * SocialNetwork class implements a hashmap of users and links. 
 * @author Jiaxin He, jxh604@case.edu
 *
 */
public class SocialNetwork {

	/** A hashmap to store all the users where the key is the id, and value is the user */
	private HashMap <String, User> userMap;

	/** A hashmap to store the set of ids as key and the links as value */
	private HashMap<Set<String>, Link> linkMap;

	/** Status of the SocialNetwork */
	private SocialNetworkStatus networkStatus;

	/**
	 * Creates an empty network and sets networkStatus to invalid users.
	 */
	public SocialNetwork() {
		userMap = new HashMap<String, User>();
		linkMap = new HashMap<Set<String>, Link>();

		networkStatus = SocialNetworkStatus.INVALID_USERS;
	}

	/**
	 * Returns the SocialNetworkStatus of the network.
	 * @return SocialNetworkStatus of network.
	 */
	public SocialNetworkStatus getNetworkStatus() {
		return networkStatus;
	}

	/**
	 * Add a user to the network!
	 * @param user The user trying to be added to the network.
	 * @return True is addition succeed, fail otherwise.
	 * @throws NullPointerException if user was null.
	 */
	public boolean addUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}

		if (userMap.containsValue(user)) {
			return false;
		}

		userMap.put(user.getID(), user);
		return true;
	}

	/**
	 * Check to see if the the user exists in network when only given the id.
	 * @param id The id of the user.
	 * @return If the user exists in network, return true, false otherwise.
	 * @throws NullPointerException if id is null;
	 */
	public boolean isMember(String id) {
		if (id == null) {
			throw new NullPointerException();
		}

		//Uses helper method findID. Returns -1 if not found. 
		if (userMap.containsKey(id)) {
			return true;
		}

		return false;
	}

	/**
	 * Return the user in network with given id.
	 * @param id The id of the user.
	 * @return The user, and user exists in network.
	 * @throws NullPointerException if id is null.
	 */
	public User getUser(String id) throws NullPointerException {

		//null case
		if (id.length() == 0) {
			throw new NullPointerException("id is null");
		}

		return userMap.get(id);
	}

	/**
	 * Establish link between users on a certain date
	 * @param ids The ids of users
	 * @param date The day of date
	 * @param status The status of the network.
	 * @throws Exception if ids or date is null, ir invalid link.
	 */
	public void establishLink(Set<String> ids, Date date, SocialNetworkStatus status) throws Exception {

		//if null, then throw exception.

		if (throwException (ids, date)) {
			throw new NullPointerException();
		}
		
		if (testCases(ids, date, status)) {
			return;
		}
		
		if (isActive(ids, date)) {
			networkStatus = SocialNetworkStatus.ALREADY_ACTIVE;
		}

		if (!isActive(ids, date)) {
			Link newLink = new Link();//Create new link
			newLink.setUsers(getSetUser(ids), status);//links the two users together based on their ids
			newLink.establish(date, status);//establish date

			linkMap.put(ids, newLink);

			networkStatus = SocialNetworkStatus.SUCCESS;
			return;
		}
	}
	
	/**
	 * Checks to see if ids and date are valid
	 * @param ids The ids we are examining
	 * @param date The date we are examining
	 * @return If the ids and date passes this test
	 */
	private boolean throwException(Set<String> ids, Date date) {
		
		if (ids == null || date == null) {
			return true;
		}
		return false;
	}

	/**
	 * Reduces repeated code in teardown and establish link
	 * @param ids The id to be set or torn down
	 * @param date The date 
	 * @param status The SocialNetworkStatus 
	 * @return Wether it passes the test or not 
	 * @throws UninitializedObjectException
	 */
	private boolean testCases(Set<String> ids, Date date, SocialNetworkStatus status) throws UninitializedObjectException {
		//Uses the ids from method parameter and converts to an array;
		String[] iDArray = getIDArray(ids);

		if (!isValidUser(iDArray)) {
			networkStatus = SocialNetworkStatus.INVALID_USERS;
			return true;
		}

		if (linkMap.get(ids) != null && !dateTestPass(linkMap.get(ids), date)) {
			networkStatus = SocialNetworkStatus.INVALID_DATE;
			return true;
		}
		
		return false;
	}

	/**
	 * Helper method checks to see if the users are valid.
	 * @param iDArray The string array of the id of users.
	 * @return If array contains 2 difference users that are both in the network.
	 */
	private boolean isValidUser(String[] iDArray) {

		//If array is not length 2, or if array contains two of the same user return false.
		if (iDArray.length != 2 || iDArray[0].equals(iDArray[1])) {
			return false;
		}

		//If the users are not members of network return false.
		if (!isMember(iDArray[0]) || !isMember(iDArray[1])) {
			return false;
		}

		//otherwise return true.
		return true;
	}

	/**
	 * Helper method tests to see if the date of the link passes conditions.
	 * @param userLink The link being tested.
	 * @param date The date evaluated. 
	 * @return If the date of link passes the conditions.
	 * @throws UninitializedObjectException if link is not valid.
	 */
	private boolean dateTestPass(Link userLink, Date date) throws UninitializedObjectException {

		if (userLink.getEventList().numElements() > 0 && date.before(userLink.getEventList().lastEvent())) {
			return false;
		}

		return true;
	}

	/**
	 * Converts the Id to an array of strings.
	 * @param ids The set of Id converting to String array.
	 * @return A String array of all the ids from input parameter.
	 */
	private String[] getIDArray(Set<String> ids) {
		return ids.toArray(new String[0]);
	}

	/**
	 * Helper method that converts id set to a user set.
	 * @param ids The id set given.
	 * @return The corresponding user set.
	 */
	private Set<User> getSetUser(Set<String> ids) {
		String[] iDArray = getIDArray(ids);

		Set<User> userSet = new HashSet<User>();
		userSet.add(getUser(iDArray[0]));
		userSet.add(getUser(iDArray[1]));

		return userSet;
	}

	/**
	 * invalidates the link.
	 * @param ids Of the users
	 * @param date The day we are checking.
	 * @param status The status of the network.
	 * @throws Exception if ids or date is null, and or invalid link.
	 */
	public void tearDownLink(Set<String> ids, Date date, SocialNetworkStatus status) throws Exception {

		//If ids or date is null, throw exception.
		if (throwException (ids, date)) {
			throw new NullPointerException();
		}

		if (testCases(ids, date, status)) {
			return;
		}

		if (isActive(ids, date)) {
			linkMap.get(ids).tearDown(date, status);

			networkStatus = SocialNetworkStatus.SUCCESS;
			return;
		}

		if (!isActive(ids, date)) {
			networkStatus = SocialNetworkStatus.ALREADY_INACTIVE;
		}

	}


	/**
	 * Returns whether or not the users are active on a certain date.
	 * @param ids The ids of the users.
	 * @param date The specific date checked.
	 * @return True if active, false otherweise.
	 * @throws hw3.UninitializedObjectException 
	 * @throws Exception if id, or date is null.
	 */
	public boolean isActive(Set<String> ids, Date date) throws UninitializedObjectException {

		//If input is null, throw exception.
		if (ids == null || date == null) {
			throw new NullPointerException();
		}

		if (linkMap.get(ids) != null && linkMap.get(ids).isActive(date)) {
			return true;
		}

		return false;
	}

	/**
	 * the neighborhood method, uses helper method to return a set of friends.
	 * @param id The id of the user
	 * @param date The date evaluated
	 * @param status Status check
	 * @return Return the set of friends
	 * @throws UninitializedObjectException
	 */
	public Set<Friend> neighborhood(String id, Date date, SocialNetworkStatus status) throws UninitializedObjectException {

		/**
		if (id == null) {
			networkStatus = SocialNetworkStatus.INVALID_USERS;
			return null;
		} */

		//initializing the set of friends to be returned
		HashSet<Friend> returnSet = new HashSet<Friend>();

		//A hashmap containing all the visited nodes
		HashMap<User, Boolean> visited = new HashMap<User, Boolean>();

		//Queue to store all of the to visit nodes
		Queue<User> toVisit = new LinkedList<User>();

		//Finding the user of the root
		User root = getUser(id);

		//list of all associated active friends of the given root that is not contained in visited
		ArrayList<Friend> associatedActiveFriends = associatedActiveFriends(root, date, 0, visited);

		visited.put(root, true);

		for (int i = 0; i < associatedActiveFriends.size(); i++) {
			returnSet.add(associatedActiveFriends.get(i));
			visited.put(associatedActiveFriends.get(i).getUser(), true);
			toVisit.add(associatedActiveFriends.get(i).getUser());
		}

		int distance = 0;
		boolean isNotFirstTime = false;

		while (!toVisit.isEmpty()) {

			User node = toVisit.remove();

			if (isNotFirstTime) {
				distance = getDistance(node, returnSet);
			}

			associatedActiveFriends = associatedActiveFriends(node, date, distance+1, visited);

			for (int i = 0; i < associatedActiveFriends.size(); i++) {
				returnSet.add(associatedActiveFriends.get(i));
				visited.put(associatedActiveFriends.get(i).getUser(), true);
				toVisit.add(associatedActiveFriends.get(i).getUser());
			}

			isNotFirstTime = true;
		}

		networkStatus = SocialNetworkStatus.SUCCESS;
		return returnSet;
	}


	/**
	 * Finds the node from the returnset and tells the distance
	 * @param node The node we are searching for
	 * @param returnSet The returnSet of the neighborhood method
	 * @return The distance of the node
	 */
	public int getDistance(User node, HashSet<Friend> returnSet) {
		int distance = 0;

		ArrayList<Friend> friendArray = new ArrayList<Friend>(Arrays.asList(returnSet.toArray(new Friend[0])));

		//Search through each friend 
		for (int i = 0; i < friendArray.size(); i++) {
			if (friendArray.get(i).getUser() != null) {
				//if that user is the same as the node, return the distance
				if (friendArray.get(i).getUser().equals(node)) {
					distance = friendArray.get(i).getDistance();
					break;
				}
			}
		}
		return distance;
	}

	/**
	 * Returns the user in a link that is not the user that we already have
	 * @param root The user that we already have
	 * @param link The link that we are searching for
	 * @return The active associated user
	 */
	private User associatedActiveUser(User root, Link link) {

		User[] userArray = link.getUserArr(link.getUserSet());

		if (userArray[0] == root) {
			return userArray[1];
		}
		else {
			return userArray[0];
		}
	}

	/**
	 * Pass in id and date, will output a link array of all associated link to that id with valid date.
	 * @param user The user searching for
	 * @param date The date evaluating
	 * @return Arraylist of all the links associated with this user
	 * @throws UninitializedObjectException throws exceptions
	 */
	private ArrayList<Friend> associatedActiveFriends(User user, Date date, int distance, HashMap<User, Boolean> visited) throws UninitializedObjectException {

		ArrayList<Link> linkArray = new ArrayList<Link>(Arrays.asList(linkMap.values().toArray(new Link[0])));

		linkArray = removeNullFriends(linkArray);

		ArrayList<Friend> returnLink = new ArrayList<Friend>();

		User associatedActiveUser;

		//going through every position in the link array
		for (int i = 0; i < linkArray.size(); i++) {

			//If the link array's user contains the user we have, and is active at the given date
			if (linkArray.get(i).getUsers().contains(user) && linkArray.get(i).isActive(date)) {

				//Find the user
				associatedActiveUser = associatedActiveUser(user, linkArray.get(i));

				//if the visited map doesnt have it yet, then make a new friend!
				if (!visited.containsKey(associatedActiveUser)) {
					Friend newFriend = new Friend();
					newFriend.set(associatedActiveUser, distance);
					returnLink.add(newFriend);
				}
			}
		}
		return returnLink;
	}
	
	/**
	 * Removes null links from an arraylist of link
	 * @param linkArray The link array we are looking at
	 * @return The linkarray with the null links removed
	 */
	private ArrayList<Link> removeNullFriends(ArrayList<Link> linkArray) {
		//If the link is not valid, then remove
		for (int i = 0; i < linkArray.size(); i++) {
			if (!linkArray.get(i).isValid()) {
				linkArray.remove(i);
			}
		}
		return linkArray;
	}

	/**
	 * 
	 * @param id The id of the user passed in
	 * @param date The date searched for the neighborhood
	 * @param distance_max The maximum distance allowed between friend
	 * @param status The socialNetworkStatus
	 * @return The set of friends that fit the criterias above
	 * @throws UninitializedObjectException Throws exception
	 */
	public Set<Friend> neighborhood(String id, Date date, int distance_max, SocialNetworkStatus status) throws UninitializedObjectException {

		if (id == null) {
			networkStatus = SocialNetworkStatus.INVALID_USERS;
			return null;
		}

		if (distance_max <0) {
			networkStatus = SocialNetworkStatus.INVALID_DISTANCE;
		}

		//Call on the neighborhood method without distance restriction
		Set<Friend> set = neighborhood(id, date, status);

		Friend[] friendArray = set.toArray(new Friend[0]);

		HashSet<Friend> returnSet = new HashSet<Friend>();

		//loop through all the friends and remove the ones that exceed the max distance
		for (int i = 0; i < friendArray.length; i++) {
			if (friendArray[i].getDistance() <= distance_max) {
				returnSet.add(friendArray[i]);
			}
		}
		networkStatus = SocialNetworkStatus.SUCCESS;
		return returnSet;
	} 

	/**
	 * Returns a map where the key is the date, and the value is the size of the neighborhood at the date
	 * @param id The user's id of the neighborhood we are looking at
	 * @param status The SocialNetworkStatus
	 * @return The map containing the date as key and size of neighborhood as value
	 * @throws UninitializedObjectException
	 */
	public Map<Date, Integer> neighborhoodTrend(String id, SocialNetworkStatus status) throws UninitializedObjectException {

		if (id == null) {
			networkStatus = SocialNetworkStatus.INVALID_USERS;
			return null;
		}

		//The map containing the dates and size of neighborhood
		HashMap<Date, Integer> returnMap = new HashMap<Date, Integer>();

		//The arraylist containing all the links
		ArrayList<Link> links = new ArrayList<Link>(Arrays.asList(linkMap.values().toArray(new Link[0])));

		ArrayList<Event> eventList;

		Date date;

		//loop through every link
		for (int i = 0; i < links.size(); i++) {

			eventList = links.get(i).getEventList().getEventList();

			//loop through every date and addd to the map
			for (int j = 0; j < eventList.size(); j++) {

				date = eventList.get(j).getDate();
				int neighborhoodSize = neighborhood(id, date, status).size();

				returnMap.put(date, neighborhoodSize);
			}	
		} 
		networkStatus = SocialNetworkStatus.SUCCESS;
		return returnMap;
	} 
}