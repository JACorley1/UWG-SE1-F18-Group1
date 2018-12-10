package edu.westga.cs3211.time_management.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Store basic information for an event.
 * 
 * @author Lucas Carlson, Carson Bedrosian, Nolan Williams, Kevin Flynn,
 *         Jonathan Corley, Tristen Rivera, Tyler Scott, Dexter Tarver
 */
public class Event extends TimeCommitment {

	private String location;
	private List<String> attendees;

	/**
	 * Creates a new Event
	 * 
	 * @precondition name != null
	 * 				 && description != null
	 * 				 && visibility != null
	 * 				 && startTime != null
	 * 				 && endTime != null
	 * 				 location != null
	 * 				 attendees != null
	 * 
	 * @postcondition getName() == name 
	 * 				  && getDescription() == description
	 * 				  && getVisibility() == visibility
	 * 				  && getStartTime() == startTime
	 * 				  && getEndTime() == endTime
	 * 				  && getLocation() == location
	 * 				  && getAttendees() == attendees
	 * 
	 * @param name
	 * 			name of the event
	 * @param description 
	 * 			description of the event
	 * @param visibility
	 * 			visibility of the event
	 * @param startTime
	 * 			start time of event
	 * @param endTime
	 * 			end time of event
	 * @param location
	 * 			location of event
	 * @param attendees
	 * 			other people who will be attending the event
	 * 
	 * @throws NullPointerException if any parameter is null
	 */
	public Event(String name, String description, Visibility visibility, LocalDateTime startTime, LocalDateTime endTime, String location, List<String> attendees) {
		super(name, description, visibility, startTime, endTime);

		if (location == null) {
			throw new NullPointerException("Location cannot be null");
		}
		if (attendees == null) {
			throw new NullPointerException("Attendees cannot be null");
		}
		
		this.location = location;
		this.attendees = attendees;
	}

	/**
	 * return the location of the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the location of the event
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * return the list of names of attendees for the event
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of names of attendees for the event
	 */
	public List<String> getAttendees() {
		return this.attendees;
	}

	/**
	 * Convert the Event to a String representation.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return String representation of the Event
	 */
	@Override
	public String toString() {
		return this.getName() + "(" + this.getStartTime() + "," + this.getEndTime() + ")";
	}

	/**
	 * Generate a multi-line full string representation of the event.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return Multi-line full string representation
	 */
	public String toStringFull() {
		String fullEventDetails = super.toStringFull();
		
		fullEventDetails += "Location: " + this.location + System.lineSeparator();
		fullEventDetails += "Attendees: " + String.join(", ", this.attendees) + System.lineSeparator();

		return fullEventDetails;
	}

}
