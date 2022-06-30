package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Tests TicketWriter.java
 * 
 * @author Will Greene
 */
public class TicketWriterTest {
	
	private static final int ID = 1;
	private static final String STATE = Ticket.NEW_NAME;
	private static final String TICKET_TYPE_STRING = Ticket.TT_INCIDENT;
	private static final String SUBJECT = "Help";
	private static final String CALLER = "wgreene";
	private static final String CATEGORY_STRING = Ticket.C_INQUIRY;
	private static final String PRIORITY_STRING = Ticket.P_HIGH;
	private static final String OWNER = "bstark";
	private static final String CODE = null;
	private static final ArrayList<String> NOTES = null;
	
	private static final TicketType TICKET_TYPE = TicketType.INCIDENT;
	private static final Category CATEGORY = Category.INQUIRY;
	private static final Priority PRIORITY = Priority.HIGH;
	private static final String NOTE = "hi";
	
	/**
	 * Tests TicketWriter.writeTicketFile().
	 */
	@Test
	public void testWriteTicketFile() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("hi");
		notes.add("hello");
		List<Ticket> ticketList = new ArrayList<Ticket>();
		Ticket t1 = new Ticket(1, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t2 = new Ticket(2, STATE, TICKET_TYPE_STRING, "Helpp", CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t3 = new Ticket(3, STATE, TICKET_TYPE_STRING, "Helppp", CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		ticketList.add(t1);
		ticketList.add(t2);
		ticketList.add(t3);
		
		try {
		TicketWriter.writeTicketFile("test-files/ticket_list.txt", ticketList);
		} catch (IOException e) {
			throw new IllegalArgumentException("Can't write to file.");
		}
		
		checkFiles("test-files/ticket_list.txt", "test-files/expected_ticket_list.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Error reading files.");
		}
	}
}
