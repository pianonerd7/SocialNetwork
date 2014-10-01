package hw5;

import java.util.Date;
import java.util.Set;

/**
 * The Link class connects two users and is never removed, they keep a list of dates.
 * @author Jiaxin He, jxh604@case.edu
 *
 */
public class Link {
  
  /* Whether the link is valid */
  private boolean isValid;
  
  /* The Set of users */
  private Set<User> users;
  
  /* The list of dates between the users stored into an arraylist */
  private EventList listOfDates;
  
  /* The SocialNetworkStatus of the link */
  private SocialNetworkStatus linkStatus;
  
  /*
   * Creates an invalid link.
   * Initializes the listOfDates, linkStatus, and sets isValid to false.
   */
  public Link() {
    listOfDates = new EventList();
    isValid = false;
    linkStatus = SocialNetworkStatus.INVALID_USERS;
  }
  
  /**
   * Gives the socialNetworkStatus of the link.
   * @return SocialNetworkStatus of link.
   */
  public SocialNetworkStatus getStatus() {
    return linkStatus;
  }
  
  
  /**
   * Creates the link for the two users.
   * @param users The set of 2 users.
   * @param status The status of the link.
   */
  public void setUsers(Set<User> users, SocialNetworkStatus status) {
    
    //Throw exception if users is null.
    if (users == null) {
      throw new NullPointerException();
    }
    
    //Creates an array of User that stores the users from parameter
    User[] userArr = getUserArr(users);
    
    //If the userArr length is not 2, then there can't be a 2 user link.
    if (userArr.length != 2) {
      linkStatus = SocialNetworkStatus.INVALID_USERS;
    }
    
    //If link is already valid
    else if (isValid()) {
      linkStatus = SocialNetworkStatus.ALREADY_VALID;
    }
    
    //If the link is invalid then that means I can create one!
    else if (!isValid()) {
      this.users = users;
      isValid = true;
      linkStatus = SocialNetworkStatus.SUCCESS;
    }
  }
  
  /**
   * Converts the keySet in the hashtable(users) to an array of users.
   * @return an array of users.
   */
  public User[] getUserArr(Set<User> users) {
    return users.toArray(new User[0]);
  }
  
  /**
   * If this link is valid.
   * @return The boolean representation of the validity of the link.
   */
  public boolean isValid() {
    return isValid;
  }
  
  /**
   * Gets the users in a link. If there is none, then exception thrown.
   * @return A set representation of the users.
   * @throws UninitializedObjectException Thrown when link is not valid.
   */
  public Set<User> getUsers() throws UninitializedObjectException {
    
    //If link is invalid, throw exception
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    
    //If link is valid, then the return set of users.
    return getUserSet();
  }
  
  /**
   * Stores the date of the link establishment into the arraylist of dates.
   * @param date The date the event was established.
   * @param status The status of the link.
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  public void establish(Date date, SocialNetworkStatus status) throws UninitializedObjectException {
    
    //If date is null, throw exception.
    if (date == null) {
      throw new NullPointerException();
    }
    
    //There can't be a date of establishment with 2 non linked users!
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    
    //Calls on helper method to set link status.
    helperEstablish(date);
  }
  
  /**
   * Helper method of establish method to reduce complexity.
   * @param date The date the event was established. 
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  private void helperEstablish(Date date) throws UninitializedObjectException {
    
    //If date is already active
    if (isActive(date)) {
      linkStatus = SocialNetworkStatus.ALREADY_ACTIVE;
    }
    
    //given date precedes last date on record
    else if (!testDateCondition(date)) {
      linkStatus = SocialNetworkStatus.INVALID_DATE;
    }
    
    //valid, inactive link, acceptable date
    else {
      listOfDates.addEvent(date, true);
      linkStatus = SocialNetworkStatus.SUCCESS;
    }
  }
  
  /**
   * Helper method to test if date meets condition. This helper method reduces complexity.
   * @param date The date evaluating.
   * @return If date is after last date in eventList.
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  private boolean testDateCondition(Date date) throws UninitializedObjectException {
    
    //If the date is before last date in eventlist return false.
    if (listOfDates.numElements() > 0 && date.before(listOfDates.lastEvent())) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Deactivate date on the link establishment into arraylist of dates.
   * @param date The date the link is torndown.
   * @param status The status of the link.
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  public void tearDown(Date date, SocialNetworkStatus status) throws UninitializedObjectException {
    
    //If date is null, throw exception
    if (date == null) {
      throw new NullPointerException();
    }
    
    //There can't be a date of establishment that you want teardown with 2 non linked users!
    else if (!isValid()) {
      throw new UninitializedObjectException();
    }
    
    helperTearDown(date);
  }
  
  /**
   * Helper method of tearDown method to reduce complexity.
   * @param date The date the event was established. 
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  private void helperTearDown(Date date) throws UninitializedObjectException {
    
    //If the date does not pass the test date condition.
    if (!testDateCondition(date)) {
      linkStatus = SocialNetworkStatus.INVALID_DATE;
    }
    
    //If the link was already inactive or if given date precedes the last date on record
    else if (!isActive(date)) {
      linkStatus = SocialNetworkStatus.ALREADY_INACTIVE;
    }
    
    else {
      //set day!
      listOfDates.addEvent(date, false);
      linkStatus = SocialNetworkStatus.SUCCESS;
    }
  }
  
  /**
   * Tells whether the link is active at the parameter date.
   * @param date The date checked to see if link is active or not.
   * @return True if link is active, and false otherwise.
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  public boolean isActive(Date date) throws UninitializedObjectException {
    
    //If date is null, throw exception.
    if (date == null) {
      throw new NullPointerException();
    }
    
    //There can't be any activity between two non linked users!
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    
    //Calls on method in EventList class and see if this date is active.
    return listOfDates.isActive(date);
  }
  
  /**
   * Finds the first event in a link.
   * @return Date of the first event between two users.
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  public Date firstEvent() throws UninitializedObjectException {
    
    Date firstEvent = listOfDates.firstEvent();
    
    if (firstEvent == null) {
      throw new UninitializedObjectException();
    }
    
    return firstEvent;
  }
  
  /**
   * Returns the next event from the date in parameter.
   * @param date The date compared to the calendar to find next date.
   * @return Return the next date after date in parameter in the arraylist.
   * @throws UninitializedObjectException Thrown when link is invalid.
   */
  public Date nextEvent(Date date) throws UninitializedObjectException {
    
    //If date is null, throw exception
    if (date == null) {
      throw new NullPointerException();
    }
    
    //No relationship between invalid link.
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    
    //If date is valid, and link is valid
    return listOfDates.nextEvent(date);
  }
  
  /**
   * Return the string representation of the link.
   * @return String return the string of link.
   */
  public String toString() {
    try {
      String isValid = "The link is valid: "+isValid()+"\n";
      String users = "The users in this link are: "+getUsers().toArray().toString()+"\n";
      String firstEvent = "The first event was: " + firstEvent()+"\n";
      String isActive = "The link is currently active: " + isActive(listOfDates.lastEvent())+""; 
      return isValid + users + firstEvent + isActive;
    }
    catch (UninitializedObjectException e) {
      return "Invalid Link: Uninitialized IDs";
    }
  }
  
  /**
   * Returns the users as a set.
   * @return Set representation of users.
   */
  public Set<User> getUserSet() {
    return users;
  }
  
  /**
   * Returns the event list of the link.
   * @return EventList of the link.
   */
  public EventList getEventList() {
    return listOfDates;
  }
}