package edu.westga.cs3211.time_management.model;

import java.util.List;
import java.time.LocalDateTime;

/**
 * Validate Event information.
 * 
 * @author Dylan Knox, Kyle Riggs, Cody Graham, Tyler Wingfield, Jeremiah
 *         Liscum, Joseph Fuller, Jonathan Corley, Tristen Rivera, Tyler Scott,
 *         Dexter Tarver, Daniel Jeselnik, Dylan McCleskey, Justin Smith
 */
public class EventDataValidator {
	
	/**
	 * Validates the event.
	 * 
	 * @param event
	 * 			the event to validate
	 * @return true if event is valid, false otherwise
	 */
	public static boolean eventIsValid(Event event) {
		var nameValid = checkName(event.getName());
		var startTimeValid = checkStartTime(event.getStartTime());
		var endTimeValid = checkEndTime(event.getStartTime(), event.getEndTime());
		var attendeesValid = checkAttendees(event.getAttendees());
		
		return nameValid && startTimeValid && endTimeValid && attendeesValid;
	}
	
	/**
	 * Checks if the even name is valid.
	 *
	 * @param name the name of the event
	 * @return true if the name is valid
	 * @precondition name must be < 60 characters and name cannot be empty
	 * @postcondition none
	 */
	public static boolean checkName(String name) {
		boolean result = true;
		if (name == null) {
			result = false;
		} else if (name.length() >= 60) {
			result = false;
		} else if (name.isEmpty()) {
			result = false;
		}
		return result;
	}
	
	/**
	 * Checks the start time, determining if it is before the current system time.
	 * This is used when displaying user warning when creating events startng in the past.
	 *
	 * @param startTime the starting time of the event
	 * @return true if the start time is before the current time, false otherwise.
	 * @throws NullPointerException if startTime is null
	 * @precondition startTime != null
	 */
	public static boolean checkStartTime(LocalDateTime startTime) {
		if (startTime == null) {
			return false;
		}
		
		return !startTime.isBefore(LocalDateTime.now());
	}
	
	/**
	 * Check end time.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @return true  if endTime is a valid time after startTime
	 * 		   false if endTime is not or is not after startTime
	 * @authors TylerWingfield, JeremiahLiscum, JosephFuller
	 * @precondition startTime != null
	 */
	public static boolean checkEndTime(LocalDateTime startTime, LocalDateTime endTime) {
		if (startTime == null) {
			throw new NullPointerException("startTime cannot be null");
		}
		if (endTime == null) {
			return false;
		}
		return endTime.isAfter(startTime);
	}

	/**
	 *  Checks the list of attendees.
	 *
	 * @param names the names
	 * @return true  if list contains valid names (see checkName for more details)
	 * 		   false if list contains one or more invalid names (see checkName for more details)
	 * @precondition none
	 * @postcondition none
	 * 					
	 */
	public static boolean checkAttendees(List<String> names) {
		if (names == null) {
			return false;
		}
		
		for (String name : names) {
			if (!EventDataValidator.checkName(name)) {
				return false;
			}
		}
		return true;
	}
}
