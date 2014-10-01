package hw5;

import java.util.Date;

/**
 * Class that make up event element in the event list. Contains getDate and getIsActive methods.
 * @author Jiaxin He, jxh604@case.edu
 */
public class Event {

	/* The date of the event. */
	private Date date;

	/* Whether the date is active */
	private boolean isActive;

	/**
	 * Constructor initialize and stores date and isActive.
	 * @param date The date of the event.
	 * @param isActive If event at date is active.
	 */
	public Event(Date date, boolean isActive) {
		this.date = date;
		this.isActive = isActive;
	}

	/**
	 * Returns the date of the event.
	 * @return The date of event.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Whether if the event is active.
	 * @return True if active, false otherwise.
	 */
	public boolean getIsActive() {
		return isActive;
	}

}