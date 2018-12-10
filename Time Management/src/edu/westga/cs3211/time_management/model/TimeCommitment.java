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
	 * @param name 			name of the time commitment
	 * @param description 			brief description of time commitment
	 * @param visibility 			visibility of time commitment
	 * @param startTime 			the start time
	 * @param endTime 			the end time
	 * @throws NullPointerException if any parameter is null.
	 * @precondition name != null
	 * 				 && description != null 
	 * 				 && visibility != null 
	 * 				 && startTime != null 
	 * 				 && endTime != null
	 * @postcondition getName() == name
	 * 				  && getDescription() == description
	 * 				  && getVisibility() == visibility
	 * 				  && getStartTime() == startTime
	 * 				  && getEndTime() == endTime
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
	 * @return the name
	 * @precondition none
	 * @postcondition none
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 * @precondition none
	 * @postcondition none
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Gets the visibility.
	 *
	 * @return the visibility
	 * @precondition none
	 * @postcondition none
	 */
	public Visibility getVisibility() {
		return this.visibility;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 * @precondition none
	 * @postcondition none
	 */
	public LocalDateTime getStartTime() {
		return this.startTime;
	}
	
	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 * @precondition none
	 * @postcondition none
	 */
	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	/**
	 * Generate a multi-line full string representation of the time commitment.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return Multi-line full string representation
	 */
	public String toStringFull() {
		var fullCommitmentDetails = "";
		
		fullCommitmentDetails += "Name: " + this.name + System.lineSeparator();
		fullCommitmentDetails += "Description: " + this.description + System.lineSeparator();
		fullCommitmentDetails += "Visibility: " + this.visibility + System.lineSeparator();
		fullCommitmentDetails += "Start time: " + this.startTime + System.lineSeparator();
		fullCommitmentDetails += "End time: " + this.endTime + System.lineSeparator();
		
		return fullCommitmentDetails;
	}
}
