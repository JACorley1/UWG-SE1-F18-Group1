package edu.westga.cs3211.time_management.model;

import java.util.ArrayList;
import java.util.List;

/** Store and manage a collection of events.
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bedrosian, Nolan Williams, Kevin Flynn, Victoria Jenkins, Laura Smedley, Jonathan Nicholl, Brandon Walker
 */
public class Calendar {
	
	/** The Constant EVENT_NULL. */
	private static final String EVENT_NULL = "Event cannot be null";
	
	/** The time commitments. */
	private ArrayList<TimeCommitment> timeCommitments;

	/**
	 *  Create a new initially empty Calendar.
	 *
	 * @precondition none
	 * @postcondition getEvents().size() == 0
	 */
	public Calendar() {
		this.timeCommitments = new ArrayList<TimeCommitment>();
	}
	
	/**
	 *  Return the collection of events in the calendar.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the collection of events in the calendar
	 */
	public ArrayList<TimeCommitment> getTimeCommitments() {
		return this.timeCommitments;
	}
	
	/**
	 *  Add a new time commitment to the calendar.
	 *
	 * @param timeCommitment 			time commitment to be added to the calendar
	 * @precondition timeCommitment != null
	 * @postcondition getTimeCommitments().size() == getTimeCommitments().size()@prev + 1
	 */
	public void addTimeCommitment(TimeCommitment timeCommitment) {
		if (timeCommitment == null) {
			throw new NullPointerException(EVENT_NULL);
		}
		
		this.timeCommitments.add(timeCommitment);
	}
	
	/**
	 *  Finds and returns the list of events in the calendar that would conflict with the specified event.
	 *
	 * @param timeCommitment the time commitment to check for conflicts
	 * 
	 * @precondition timeCommitment != null
	 * @postcondition none
	 * 
	 * @return the time commitments in the calendar that would conflict with the specified event
	 */
	public List<TimeCommitment> declareConflicts(TimeCommitment timeCommitment) {
		if (timeCommitment == null) {
			throw new NullPointerException(EVENT_NULL);
		}
		
		List<TimeCommitment> conflicts = new ArrayList<TimeCommitment>();
		
		for (TimeCommitment current: this.timeCommitments) {
			if (!timeCommitment.getStartTime().isBefore(current.getStartTime()) && !timeCommitment.getStartTime().isAfter(current.getEndTime())) {
				conflicts.add(current);
			}
			if (!timeCommitment.getEndTime().isBefore(current.getStartTime()) && !timeCommitment.getEndTime().isAfter(current.getEndTime())) {
				conflicts.add(current);
			}
		}
		
		return conflicts;
	}
}
