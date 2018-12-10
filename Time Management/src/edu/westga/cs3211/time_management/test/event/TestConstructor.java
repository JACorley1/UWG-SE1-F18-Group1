package edu.westga.cs3211.time_management.test.event;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

class TestConstructor {

	@Test
	void testInvalidName() {	
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		List<String> attendees = List.of();
		
		assertThrows(
						NullPointerException.class, 
						()->{
							new Event(null, "", Visibility.PUBLIC, start, end, "", attendees);
						}
					);
	}

	@Test
	void testInvalidStartTime() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		List<String> attendees = List.of();
			
		assertThrows(
						NullPointerException.class, 
						()->{
							new Event("Bob", "", Visibility.PUBLIC, null, end, "", attendees);
						}
					);
	}

	@Test
	void testInvalidEndTime() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		List<String> attendees = List.of();
			
		assertThrows(
						NullPointerException.class, 
						()->{
							new Event("Bob", "", Visibility.PUBLIC, start, null, "", attendees);
						}
					);
	}

	@Test
	void testInvalidAttendees() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
			
		assertThrows(
						NullPointerException.class, 
						()->{
							new Event("Bob", "", Visibility.PUBLIC, start, end, "", null);
						}
					);
	}

	@Test
	void testNullLocation() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		List<String> attendees = List.of();
			
		assertThrows(
						NullPointerException.class, 
						()->{
							new Event("Bob", "", Visibility.PUBLIC, start, end, null, attendees);
						}
					);
	}

	@Test
	void testNullDescription() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		List<String> attendees = List.of();
			
		assertThrows(
						NullPointerException.class, 
						()->{
							new Event("Bob", null, Visibility.PUBLIC, start, end, "", attendees);
						}
					);
	}

	@Test
	void testNullVisibility() {		
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		List<String> attendees = List.of();
			
		assertThrows(
						NullPointerException.class, 
						()->{
							new Event("Bob", "", null, start, end, "", attendees);
						}
					);
	}

	@Test
	void testValidEvent() {			
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		List<String> attendees = List.of();
		
		Event result = new Event("Bob", "description", Visibility.PUBLIC, start, end, "location", attendees);
		
		assertEquals("Bob", result.getName(), "checking name");
		assertEquals(start, result.getStartTime(), "checking start time");
		assertEquals(end, result.getEndTime(), "checking end time");
		assertEquals("location", result.getLocation(), "checking location");
		assertEquals("description", result.getDescription(), "checking description");
		assertEquals(attendees, result.getAttendees(), "checking attendees");
		assertEquals(Visibility.PUBLIC, result.getVisibility(), "checking visibility");
	}

}
