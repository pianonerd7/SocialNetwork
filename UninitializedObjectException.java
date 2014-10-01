package hw5;

/**
 * The UninitializedObjectException class used in other classes. Extends Exception.
 * @author Jiaxin He, jxh604@case.edu
 */
@SuppressWarnings("serial")
public class UninitializedObjectException extends Exception {
  
  /**
   * Throws exception
   */
  public UninitializedObjectException() {
  }
  
  /**
   * Throws exception
   * @param message The error as a string
   */
  public UninitializedObjectException(String message) {
    super(message);
  }
  
  /**
   * Throws exception
   * @param message The error as a string
   * @param cause The cause of this throwable or null if the cause is nonexistent or unknown.
   */
  public UninitializedObjectException(String message, Throwable cause) {
    super(message, cause);
  }
  
  /**
   * Throws exception
   * @param cause The cause of this throwable or null if the cause is nonexistent or unknown.
   */
  public UninitializedObjectException(Throwable cause) {
    super(cause);
  }
}
