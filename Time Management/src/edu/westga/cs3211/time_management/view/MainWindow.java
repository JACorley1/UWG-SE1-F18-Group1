package edu.westga.cs3211.time_management.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.time_management.Main;
import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.TimeCommitment;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Codebehind for the MainWindow Scene.
 * 
 * @author Jonathan Corley
 */
public class MainWindow {

    /** The resources. */
    @FXML private ResourceBundle resources;
    
    /** The location. */
    @FXML private URL location;
    
    /** The event list. */
    @FXML private ListView<TimeCommitment> timeCommitmentList;
    
    /** The event details text. */
    @FXML private TextArea eventDetailsText;
    
    /** The calendar. */
    private Calendar calendar;

    /**
     * Adds the event.
     *
     * @param event the event
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @FXML
    void addEvent(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("view/AddEvent.fxml"));
    	loader.load();
    	Parent parent = loader.getRoot();
    	Scene scene = new Scene(parent);
    	Stage addEventStage = new Stage();
    	addEventStage.setTitle("Add New Event");
    	addEventStage.setScene(scene);
    	addEventStage.initModality(Modality.APPLICATION_MODAL);
    	AddEvent addEventDialog = loader.getController();
    	addEventDialog.setCalendar(this.calendar);
    	addEventStage.showAndWait();

        this.timeCommitmentList.setItems(FXCollections.observableArrayList(this.calendar.getTimeCommitments()));
    }
    
    /**
     * Select event.
     *
     * @param event the event
     */
    @FXML
    void selectEvent(MouseEvent event) {
    	var selectedCommitment = this.timeCommitmentList.getSelectionModel().getSelectedItem();
    	if (selectedCommitment != null) {
    		this.eventDetailsText.setText(selectedCommitment.toStringFull());
    	}
    }

    /**
     * Initialize.
     */
    @FXML
    void initialize() {
        assert this.timeCommitmentList != null : "fx:id=\"eventList\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.eventDetailsText != null : "fx:id=\"eventDetailsText\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.calendar = new Calendar();
        this.timeCommitmentList.setItems(FXCollections.observableArrayList(this.calendar.getTimeCommitments()));
    }
}

