package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
		Ticket t1 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t2 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t3 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		
		//write to a file
		
		checkFiles(null, null);
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
