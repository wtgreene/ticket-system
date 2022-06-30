package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.assertEquals;

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
		list = TicketReader.readTicketFile("test-files/ticket1.txt");
		assertEquals("GitHub down", list.get(0).getSubject());
		
		ArrayList<Ticket> list2 = new ArrayList<Ticket>();
		list2 = TicketReader.readTicketFile("test-files/ticket2.txt");
		assertEquals("Canceled", list2.get(1).getState());
	}
}
