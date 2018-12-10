package edu.westga.cs3211.time_management.model;

import java.time.LocalDateTime;

/**
 * Represents a time commitment.
 * 
 * @author Dylan Knox, Kyle Riggs, Cody Graham
 */
public abstract class TimeCommitment {

	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The visibility. */
	private Visibility visibility;
	
	/** The start time. */
	private LocalDateTime startTime;
	
	/** The end time. */
	private LocalDateTime endTime;
	
	/**
	 * Instantiates a new object of the TimeCommitment class.
	 *
	 * @precondition name != null
	 * 				 && description != null 
	 * 				 && visibility != null 
	 * 				 && startTime != null 
	 * 				 && endTime != null
	 * 
	 * @postcondition getName() == name
	 * 				  && getDescription() == description
	 * 				  && getVisibility() == visibility
	 * 				  && getStartTime() == startTime
	 * 				  && getEndTime() == endTime
	 *
	 * @param name
	 * 			name of the time commitment
	 * @param description 
	 * 			brief description of time commitment
	 * @param visibility 
	 * 			visibility of time commitment
	 * @param startTime 
	 * 			the start time
	 * @param endTime 
	 * 			the end time
	 */
	public TimeCommitment(String name, String description, Visibility visibility, LocalDateTime startTime, LocalDateTime endTime) {
		if (name == null) {
			throw new NullPointerException("Name cannot be null");
		}
		if (description == null) {
			throw new NullPointerException("Description cannot be null");
		}
		if (visibility == null) {
			throw new NullPointerException("Visibility cannot be null");
		}
		if (startTime == null) {
			throw new NullPointerException("Start time cannot be null");
		}
		if (endTime == null) {
			throw new NullPointerException("End time cannot be null");
		}
		
		this.name = name;
		this.description = description;
		
		this.visibility = visibility;
		
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Gets the name.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the description.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description
	 * 
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Gets the visibility.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		return this.visibility;
	}
	
	/**
	 * Gets the start time.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the start time
	 */
	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	
	/**
	 * Gets the end time.
	 * 
	 * @precondition none
	 * @postcondition none
	 *
	 * @return the end time
	 */
	public LocalDateTime getEndTime() {
		return this.endTime;
	}
}
