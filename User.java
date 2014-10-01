package hw5;

/**
 * The User class. SetID, getID, isValid, and toString methods.
 * Also has option methods such as set/get name, email and phone number.
 * @author Jiaxin He, jxh604@case.edu
 */
public class User {
  
  /* The id of the user*/
  private String id;
  
  /* The boolean value of the validity of the user*/
  private boolean isValid;
  
  /* The String representation of the first name of user*/
  private String firstName;
  
  /* The String representation of the middle name of user*/
  private String middleName;
  
  /* The String representation of the last name of user*/
  private String lastName;
  
  /* The String representation of the email of user*/
  private String email;
  
  /* The String representation of the phone number of user*/
  private String phoneNumber;
  
  /* The boolean representation of whether the user is a valid user in the neighborhood in socialnetwork */
  private boolean isFriendValid;
  
  /**
   * Creates a new invalid user.
   */
  public User() {
    isValid = false;
  }
  
  /**
   * Sets the ID for the user.
   * @param id The ID of the user represented as a string.
   * @return True if ID is set, and false if otherwise.
   * @throws NullPointerException When input is null.
   */
  public boolean setID(String id) throws NullPointerException {
    
    //If the id is null, throw exception
    if (id == null) {
      throw new NullPointerException("The id that you have entered is null");
    }
    
    //If this user is not valid, then set the id!
    else if (!isValid()){  
      this.id = id;
      isValid = true;
      return true;
    }
    
    //If it arrives here, it means that the id is not null, and the user is valid. 
    return false;
  }
  
  /**
   * Returns the ID of the user.
   * @return The String representation of the ID.
   */
  public String getID() {
    return id;
  }
  
  /**
   * Returns if the user is valid or not.
   * @return True if user is valid, and false otherwise.
   */
  public boolean isValid() {
    return isValid;
  }
  
  /**
   * Returns the user after setting the first name.
   * @param name The first name of the user being set or replaced.
   * @return The user after setting the first name.
   * @throws UninitializedObjectException Thrown when user is invalid.
   */
  public User setFirstName(String name) throws UninitializedObjectException {
    
    //If name is null, then throw exception
    if (name == null) {
      throw new NullPointerException();
    }
    
    // If user is not valid, throw exception
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    
    //If this part of the code is reached, then user exists and name is not null. Set first name.
    this.firstName = name;
    return this;
  }
  
  /**
   * Returns the first name of the user.
   * @return The string representation of the first name of user.
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * Returns the user after setting the middle name.
   * @param name The middle name of the user being set or replaced.
   * @return The user after setting the middle name.
   * @throws UninitializedObjectException Thrown when user is invalid.
   */
  public User setMiddleName(String name) throws UninitializedObjectException {
    if (name == null) {
      throw new NullPointerException();
    }
    
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    
    this.middleName = name;
    return this;
  }
  
  /**
   * Returns the middle name of the user.
   * @return The string representation of the middle name of user.
   */
  public String getMiddleName() {
    return middleName;
  }
  
  /**
   * Returns the user after setting the last name.
   * @param name The last name of the user being set or replaced.
   * @return The user after setting the last name.
   * @throws UninitializedObjectException Thrown when user is invalid.
   */
  public User setLastName(String name) throws UninitializedObjectException {
    if (name == null) {
      throw new NullPointerException();
    }
    
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    this.lastName = name;
    return this;
  }
  
  /**
   * Returns the last name of the user.
   * @return The string representation of the last name of user.
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * Returns the user after setting the email.
   * @param name The email of the user being set or replaced.
   * @return The user after setting the email.
   * @throws UninitializedObjectException Thrown when user is invalid.
   */
  public User setEmail(String emailName) throws UninitializedObjectException {
    if (emailName == null) {
      throw new NullPointerException();
    }
    
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    this.email = emailName;
    return this;
  }
  
  /**
   * Returns the email of the user.
   * @return The string representation of the email of user.
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Returns the user after setting the phone number.
   * @param name The phone number of the user being set or replaced.
   * @return The user after setting the phone number.
   * @throws UninitializedObjectException Thrown when user is invalid.
   */
  public User setPhoneNumber(String phoneNumber) throws UninitializedObjectException {
    if (phoneNumber == null) {
      throw new NullPointerException();
    }
    
    if (!isValid()) {
      throw new UninitializedObjectException();
    }
    this.phoneNumber = phoneNumber;
    return this;
  }
  
  /**
   * Returns the phone number of the user.
   * @return The string representation of the phone number of user.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  } 
  
  /**
   * Helper method to sum up string of name.
   * Help reduces complexity.
   * @return The string of name if it exists.
   */
  private String nameToString() {
    
    String returnString = null;
    
    //Checks getter method. If that string is not null, then add to returnString.
    if (getFirstName() != null) {
      returnString = returnString + ", First name: " + getFirstName();
    }
    
    //Checks getter method. If that string is not null, then add to returnString.
    if (getMiddleName() != null) {
      returnString = returnString + ", Middle name: " + getMiddleName();
    }
    
    //Checks getter method. If that string is not null, then add to returnString.
    if (getLastName() != null) {
      returnString = returnString + ", Last name: " + getLastName();
    }
    return returnString;
  }
  
  /**
   * Helper method to sum up string of email, and phone number.
   * Help reduces complexity.
   * @return The string of email, and phone number, if it exists.
   */
  private String emailPhoneNumberToString() {
    
    String returnString = null;
    
    //Checks getter method. If that string is not null, then add to returnString.
    if (getEmail() != null) {
      returnString = returnString + ", Email: " + getEmail();
    }
    
    //Checks getter method. If that string is not null, then add to returnString.
    if (getPhoneNumber() != null) {
      returnString = returnString + ", Phone Number: " + getPhoneNumber();
    }
    
    return returnString;
  }
  
  /**
   * Helper method to sum up string of name, email, and phone number.
   * Help reduces complexity.
   * @return The string of name, email, and phone number, if it exists.
   */
  private String sumAllString() {
    
    String returnString = null;
    
    //Calls on helper method. If that string is not null, then add to returnString.
    if (nameToString() != null) {
      returnString = returnString + nameToString();
    }
    
    //Calls on helper method. If that string is not null, then add to returnString.
    if (emailPhoneNumberToString() != null) {
      returnString = returnString + emailPhoneNumberToString();
    }
    
    return returnString;
    
  }
  /**
   * Uses helper methods to make toString of user.
   * Return the string representation of the user and whether if its valid. And name, email, phone number if valid.
   */
  public String toString() {
    
    String returnString = null;
    
    //If the user is valid, then return the user and validity.
    if (isValid()) {
      returnString = "The user: " + getID() + " is valid: "+ isValid();
      
      //If string has name, email or phone number, add to returnString.
      if (sumAllString() != null) {
        returnString = returnString + sumAllString();
      }
    }
    
    //If the returnString is not null, then return.
    if (returnString != null) {
      return returnString;
    }
    //The user doesn't exist!
    else {
      return "Invalid User: Uninitialized ID";
    }
  }
  
  /**
   * Stores whether it has been visited in socialNetworks.
   * @return True or false.
   */
  public boolean isFriendValid() {
    return isFriendValid;
  }
  
  /**
   * Sets whether it as been visited in socialNetworks.
   * @param isValid True or false.
   */
  public void setIsFriendValid(boolean isValid) {
    this.isFriendValid = isValid;
  }
  
}