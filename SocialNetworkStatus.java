package hw5;

/**
 * SocialNetworkStatus class is an enum consisting of all statuses relating to establish, teardown and set user.s
 * @author Jiaxin He, jxh604@case.edu
 *
 */
public enum SocialNetworkStatus {
  
  //All possible statuses
  SUCCESS("SUCCESS"), 
    ALREADY_VALID("ALREADY_VALID"), 
    INVALID_USERS("INVALID_USERS"), 
    INVALID_DATE("INVALID_DATE"), 
    ALREADY_ACTIVE("ALREADY_ACTIVE"), 
    ALREADY_INACTIVE("ALREADY_INACTIVE"),
    INVALID_DISTANCE("INVALID_DISTANCE");
  
  /* label stores the string representation of the status */
  private String label;
  
  /**
   * Constructor creates and initialize label.
   * @param string
   */
  private SocialNetworkStatus(String string) {
    this.label = string;
  }
  
  /**
   * Returns the status as a string
   * @return String The string representation of the status
   */
  public String getStatus() {
    return label;
  }
}
