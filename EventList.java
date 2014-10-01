package hw5;

import java.util.ArrayList;
import java.util.Date;

/**
 * The EventList class creates events are stores into an arraylist. Inner class consists of events.
 * @author Jiaxin He, jxh604@case.edu
 */
public class EventList {

	/* The arraylist containing all of the events pertaining to a link */
	private ArrayList<Event> listOfEvents;

	/**
	 * Constructor creates an empty arraylist of events.
	 */
	public EventList() {
		listOfEvents = new ArrayList<Event>();
	}

	public ArrayList<Event> getEventList() {
		return listOfEvents;
	}

	/**
	 * Returns the number of events stored into the arraylist.
	 * @return int The number of elements in the arraylist.
	 */
	public int numElements() {
		return listOfEvents.size();
	}

	/**
	 * Add an event to eventList and stores if it's active.
	 * @param date The date the event took place.
	 * @param isActive Is the link active after this event.
	 */
	public void addEvent(Date date, boolean isActive) {
		listOfEvents.add(listOfEvents.size(), new Event(date, isActive));
	}

	/**
	 * Return the event at the given index;
	 * @param index The index of the event in the arraylist that want to retrieve
	 * @return Event The event at the given index.
	 */
	public Event getEvent(int index) {
		return listOfEvents.get(index);
	}

	/**
	 * Gives the very first even in the arraylist.
	 * @return Date The date of the very first event in the array list.
	 */
	public Date firstEvent() {

		//If the arraylist is empty, return null.
		if (numElements() < 1) {
			return null;
		}

		//If it is not null, then return first event.
		return listOfEvents.get(0).getDate();
	}

	/**
	 * Gives the last event in the arraylist
	 * @return Date The date of the last event in the array list.
	 */
	public Date lastEvent() {

		//If the array list is empty, return null
		if (numElements() < 1) {
			return null;
		}

		//If the array list is not empty, return the last event
		return listOfEvents.get(numElements()-1).getDate();
	}


	/**
	 * Evaluate if the event is active at the given date.
	 * @param date The date to evaluate.
	 * @return whether that date is active.
	 */
	public boolean isActive(Date date) {

		//If arraylist is empty, return false.
		if (numElements() < 1) {
			return false;
		}

		//If the last date is the equal to the input date, return that date.
		if (date.equals(lastEvent())) {
			return isLastDateActive();
		}

		//Call on helper method to determine if that date is active else wise.
		return eventIsActive(date);
	}

	/**
	 * Helper method to help determine if event at given date is active.
	 * @param date The date to evalute.
	 * @return whether that date is active.
	 */
	private boolean eventIsActive(Date date) {
		Event eventAtIndex;
		boolean isEventActive;

		//starts from the end of events, and find first event that comes before date
		for (int i = listOfEvents.size() -1 ; i >= 0; i--) {

			eventAtIndex = listOfEvents.get(i);
			isEventActive = eventAtIndex.getIsActive();

			//If the date exists in the arraylist, return.
			if (eventAtIndex.getDate().equals(date)) {
				return isEventActive;
			}

			//If the date is after return.
			if (eventAtIndex.getDate().before(date)) {
				return isEventActive;
			}
		}
		//No date could be found, so not active.
		return false;
	}

	/**
	 * Finds the next event after the given date.
	 * @param date The event after this date is returned if exists.
	 * @return The next date after the input date.
	 */
	public Date nextEvent(Date date) {

		Date dateAtIndex;

		//loops through arraylist and finds the first even after the given date.
		for (int i = 0; i < listOfEvents.size(); i++) {

			dateAtIndex = listOfEvents.get(i).getDate();

			//If date after exists, return.
			if (dateAtIndex.after(date)) {
				return dateAtIndex;
			}
		}

		//date does not exist, return null.
		return null;
	}

	/**
	 * Checks to see if the last date in the arraylist is active.
	 * @return True if last date is active, false otherwise.
	 */
	public boolean isLastDateActive() {

		//If arraylist is empty, return false.
		if (numElements() < 1) {
			return false;
		}

		return listOfEvents.get(numElements()-1).getIsActive();
	}
}
