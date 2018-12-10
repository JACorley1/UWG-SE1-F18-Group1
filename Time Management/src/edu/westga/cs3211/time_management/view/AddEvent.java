package edu.westga.cs3211.time_management.view;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.EventDataValidator;
import edu.westga.cs3211.time_management.model.Visibility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/** Codebehind for the AddEvent Scene.
 * 
 * @author Jonathan Corley, Dylan Knox
 */
public class AddEvent {

	private static final String START_TIME_INVALID = "Start time invalid";
	
    /** The resources. */
    @FXML private ResourceBundle resources;
    
    /** The location. */
    @FXML private URL location;
    
    /** The visibility label. */
    @FXML private Label visibilityLabel;
    
    /** The location label. */
    @FXML private Label locationLabel;
    
    /** The attendees label. */
    @FXML private Label attendeesLabel;
    
    /** The end time label. */
    @FXML private Label endTimeLabel;
    
    /** The start time label. */
    @FXML private Label startTimeLabel;
    
    /** The description label. */
    @FXML private Label descriptionLabel;
    
    /** The name label. */
    @FXML private Label nameLabel;
    
    /** The location text. */
    @FXML private TextField locationText;
    
    /** The description text. */
    @FXML private TextField descriptionText;
    
    /** The name text. */
    @FXML private TextField nameText;
    
    /** The start time date. */
    @FXML private DatePicker startTimeDate;
    
    /** The end time date. */
    @FXML private DatePicker endTimeDate;
    
    /** The new attendee text. */
    @FXML private TextField newAttendeeText;
    
    /** The attendees list. */
    @FXML private ComboBox<String> attendeesList;
    
    /** The visibility list. */
    @FXML private ComboBox<Visibility> visibilityList;
    
	/** The calendar. */
	private Calendar calendar;

    /**
     * Display error message.
     *
     * @param errorMessage the error message
     */
    private void displayErrorMessage(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR, errorMessage);
		alert.showAndWait();
    }
    
    /**
     * Adds the attendee.
     *
     * @param event the event
     */
    @FXML
    void addAttendee(ActionEvent event) {
    	String name = this.newAttendeeText.getText();
		if (EventDataValidator.checkName(name)) {
    		this.attendeesList.getItems().add(name);
    	} else {
			this.displayErrorMessage("Invalid name for new attendee: " + name);
		}
    }

    /**
     * Removes the attendee.
     *
     * @param event the event
     */
    @FXML
    void removeAttendee(ActionEvent event) {
    	String name = this.attendeesList.getValue();
    	this.attendeesList.getItems().remove(name);
    }

    /**
     * Cancel.
     *
     * @param event the event
     */
    @FXML
    void cancel(ActionEvent event) {
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Adds the event.
     *
     * @param event the ActionEvent
     */
    @FXML
    void addEvent(ActionEvent event) {
    	String errorText = this.buildErrorText();
    	
    	if (!errorText.isEmpty()) {
    		this.displayErrorMessage(errorText);
    		return;
    	}

    	var newEvent = this.buildValidEvent();
    	
		var alert = this.bulidConfirmationAlert(newEvent);
		var result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.OK) {
			this.calendar.addTimeCommitment(newEvent);
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
    }

    private String buildErrorText() {
    	var errorText = "";
    	
    	var name = this.nameText.getText();
    	if (!EventDataValidator.checkName(name)) {
    		errorText += "Name is invalid" + System.lineSeparator();
    	}
    	
    	var startTime = LocalDateTime.of(this.startTimeDate.getValue(), LocalTime.of(9, 0));
    	var endTime = LocalDateTime.of(this.endTimeDate.getValue(), LocalTime.of(5, 0));
    	if (!EventDataValidator.checkStartTime(startTime)) {
    		errorText += START_TIME_INVALID + System.lineSeparator();
    	} else if (!EventDataValidator.checkStartTime(endTime)) {
    		errorText += "Start time is invalid" + System.lineSeparator();
    	}
    	
    	var attendees = this.attendeesList.getItems();
    	if (!EventDataValidator.checkAttendees(attendees)) {
    		errorText += "List of attendee names is invalid" + System.lineSeparator();
    	}
    	
    	return errorText;
    }
    
    private Event buildValidEvent() {
    	var name = this.nameText.getText();
    	var description = this.descriptionText.getText();
    	
    	var visibility = this.visibilityList.getValue();
    	
    	var startTime = LocalDateTime.of(this.startTimeDate.getValue(), LocalTime.of(9, 0));
    	var endTime = LocalDateTime.of(this.endTimeDate.getValue(), LocalTime.of(5, 0));
    	
    	var location = this.locationText.getText();
    	
    	var attendees = this.attendeesList.getItems();
    	
    	if (location == null) {
    		location = "";
    	}
    	if (description == null) {
    		description = "";
    	}
    	
    	Event newEvent = new Event(name, description, visibility, startTime, endTime, location, attendees);
    	return newEvent;
    }
    
    private String buildConflictingEventsText(Event event) {
    	var conflictText = "";
    	var conflictingEvents = this.calendar.declareConflicts(event);
    	
    	for (var currEvent : conflictingEvents) {
    		conflictText += ((Event) currEvent).toString() + System.lineSeparator();
    	}
    	
    	return conflictText;
    }
    
    private Alert bulidConfirmationAlert(Event event) {
    	var eventText = event.toStringFull();
    	var conflictText = this.buildConflictingEventsText(event);
    	
    	var eventSummaryAndConflictText = "NEW EVENT DETAILS" + System.lineSeparator() + eventText + System.lineSeparator() + "CONFLICTING EVENTS" + conflictText;
    	
		var alert = new Alert(AlertType.CONFIRMATION, eventSummaryAndConflictText);
		alert.setTitle("Create New Event?");
		
		return alert;
    }
    
    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert this.visibilityLabel != null : "fx:id=\"visibilityLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.locationText != null : "fx:id=\"locationText\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.startTimeDate != null : "fx:id=\"startTimeDate\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.attendeesLabel != null : "fx:id=\"attendeesLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.endTimeDate != null : "fx:id=\"endTimeDate\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.newAttendeeText != null : "fx:id=\"newAttendeeText\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.endTimeLabel != null : "fx:id=\"endTimeLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.attendeesList != null : "fx:id=\"attendeesList\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.startTimeLabel != null : "fx:id=\"startTimeLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.visibilityList != null : "fx:id=\"visibilityList\" was not injected: check your FXML file 'AddEvent.fxml'.";
        assert this.nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";

        this.attendeesList.setItems(FXCollections.observableArrayList());
        this.visibilityList.setItems(FXCollections.observableArrayList());
        this.visibilityList.getItems().add(Visibility.PUBLIC);
        this.visibilityList.getItems().add(Visibility.PRIVATE);
        this.visibilityList.getItems().add(Visibility.FRIENDS_ONLY);
        this.visibilityList.setValue(Visibility.PUBLIC);
        this.startTimeDate.setValue(LocalDate.now());
        this.endTimeDate.setValue(LocalDate.now());
    }

	/**
	 * Sets the calendar.
	 *
	 * @precondition calendar != null
	 *
	 * @param calendar the new calendar
	 * 
	 * @throws NullPointerException if calender == null
	 */
	public void setCalendar(Calendar calendar) {
		if (calendar == null) {
			throw new NullPointerException("Calendar provided was null");
		}
		
		this.calendar = calendar;
	}
}
