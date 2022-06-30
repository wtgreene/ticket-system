package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Tests TicketReader.java
 * 
 * @author Will Greene
 */
public class TicketReaderTest {
	
	/**
	 * Tests TicketReader.readTicketFile().
	 */
	@Test
	public void testTicketReaderTest() {
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		
		try {
			list = TicketReader.readTicketFile("test-files/ticket1.txt");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
		
		assertEquals("GitHub down", list.get(0).getSubject());
		list.clear();
	}
}
