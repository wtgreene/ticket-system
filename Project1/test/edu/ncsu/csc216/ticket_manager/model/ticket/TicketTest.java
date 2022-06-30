/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.ticket;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Tests Ticket.java
 * 
 * @author Will Greene
 */
public class TicketTest {
	
	/** ticket type - request */
	private static final int ID = 1;
	/** state name */
	private static final String STATE = Ticket.NEW_NAME;
	/** ticket type - string */
	private static final String TICKET_TYPE_STRING = Ticket.TT_INCIDENT;
	/** subject */
	private static final String SUBJECT = "Help";
	/** caller */
	private static final String CALLER = "wgreene";
	/** category - string */
	private static final String CATEGORY_STRING = Ticket.C_INQUIRY;
	/** priority - string */
	private static final String PRIORITY_STRING = Ticket.P_HIGH;
	/** owner */
	private static final String OWNER = "bstark";
	/** code */
	private static final String CODE = null;
	/** notes */
	private static final ArrayList<String> NOTES = null;
	
	/** ticket type */
	private static final TicketType TICKET_TYPE = TicketType.INCIDENT;
	/** category */
	private static final Category CATEGORY = Category.INQUIRY;
	/** priority */
	private static final Priority PRIORITY = Priority.HIGH;
	/** note */
	private static final String NOTE = "hi";
	
	/**
	 * Resets the Ticket counter at the beginning of every test.
	 */
	@Before
	public void setUp() throws Exception {
		Ticket.setCounter(1);
	}
	
	/**
	 * Tests Ticket constructor.
	 */
	@Test
	public void testTicketIntStringStringStringStringStringStringStringStringArrayListString() {
		assertDoesNotThrow(() -> new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES));
	}
	
	/**
	 * Tests Ticket constructor.
	 */
	@Test
	public void testTicketTicketTypeStringStringCategoryPriorityString() {
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, Priority.LOW, NOTE));
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, Priority.MEDIUM, NOTE));
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, Priority.HIGH, NOTE));
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, Priority.URGENT, NOTE));
		
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, Category.DATABASE, PRIORITY, NOTE));
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, Category.HARDWARE, PRIORITY, NOTE));
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, Category.INQUIRY, PRIORITY, NOTE));
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, Category.NETWORK, PRIORITY, NOTE));
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, Category.SOFTWARE, PRIORITY, NOTE));
	}
	
	/**
	 * Tests Ticket.incrementCounter().
	 */
	@Test
	public void testIncrementcounter() {
		
		Ticket.incrementCounter();
		Ticket t = new Ticket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		assertEquals(2, t.getTicketId());
	}
	
	/**
	 * Tests Ticket.setCounter().
	 */
	@Test
	public void testSetCounter() {
		Ticket.setCounter(10);
		Ticket t = new Ticket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		assertEquals(10, t.getTicketId());
	}
	
	/**
	 * Tests Ticket.getCaller().
	 */
	@Test
	public void testGetCaller() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(CALLER, t.getCaller());
	}

	/**
	 * Tests Ticket.getCancellationCode().
	 */
	@Test
	public void testGetCancellationCode() {
		Ticket t1 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Duplicate", NOTES);
		Ticket t2 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Inappropriate", NOTES);
		assertEquals("Duplicate", t1.getCancellationCode());
		assertEquals("Inappropriate", t2.getCancellationCode());
	}
	
	/**
	 * Tests Ticket.getCategory().
	 */
	@Test
	public void testGetCategory() {
		Ticket t1 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, "Inquiry", PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t2 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, "Software", PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t3 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, "Hardware", PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t4 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, "Network", PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t5 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, "Database", PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals("Inquiry", t1.getCategory());
		assertEquals("Software", t2.getCategory());
		assertEquals("Hardware", t3.getCategory());
		assertEquals("Network", t4.getCategory());
		assertEquals("Database", t5.getCategory());
	}
	
	/**
	 * Tests Ticket.getFeedbackCode().
	 */
	@Test
	public void testGetFeedbackCode() {
		Ticket t1 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Awaiting Caller", NOTES);
		Ticket t2 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Awaiting Change", NOTES);
		Ticket t3 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Awaiting Provider", NOTES);
		assertEquals("Awaiting Caller", t1.getFeedbackCode());
		assertEquals("Awaiting Change", t2.getFeedbackCode());
		assertEquals("Awaiting Provider", t3.getFeedbackCode());
	}
	
	/**
	 * Tests Ticket.getNotes().
	 */
	@Test
	public void testGetNotes() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("hi");
		notes.add("hello");
		
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		assertEquals("-hi" + "\n" + "-hello" + "\n", t.getNotes());
	}
	
	/**
	 * Tests Ticket.getOwner().
	 */
	@Test
	public void testGetOwner() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(OWNER, t.getOwner());
	}
	
	/**
	 * Tests Ticket.getPriority().
	 */
	@Test
	public void testGetPriority() {
		Ticket t1 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, "Low", OWNER, CODE, NOTES);
		Ticket t2 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, "Medium", OWNER, CODE, NOTES);
		Ticket t3 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, "High", OWNER, CODE, NOTES);
		Ticket t4 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, "Urgent", OWNER, CODE, NOTES);
		assertEquals("Low", t1.getPriority());
		assertEquals("Medium", t2.getPriority());
		assertEquals("High", t3.getPriority());
		assertEquals("Urgent", t4.getPriority());
	}
	
	/**
	 * Tests Ticket.getResolutionCode().
	 */
	@Test
	public void testGetResolutionCode() {
		Ticket t1 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Solved", NOTES);
		Ticket t2 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Not Solved", NOTES);
		Ticket t3 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Workaround", NOTES);
		Ticket t4 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Caller Closed", NOTES);
		Ticket t5 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Completed", NOTES);
		Ticket t6 = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Not Completed", NOTES);
		assertEquals("Solved", t1.getResolutionCode());
		assertEquals("Not Solved", t2.getResolutionCode());
		assertEquals("Workaround", t3.getResolutionCode());
		assertEquals("Caller Closed", t4.getResolutionCode());
		assertEquals("Completed", t5.getResolutionCode());
		assertEquals("Not Completed", t6.getResolutionCode());
	}
	
	/**
	 * Tests Ticket.getState().
	 */
	@Test
	public void testGetState() {
		Ticket t1 = new Ticket(ID, "New", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t2 = new Ticket(ID, "Working", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t3 = new Ticket(ID, "Feedback", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t4 = new Ticket(ID, "Resolved", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t5 = new Ticket(ID, "Closed", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t6 = new Ticket(ID, "Cancelled", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals("New", t1.getState());
		assertEquals("Working", t2.getState());
		assertEquals("Feedback", t3.getState());
		assertEquals("Resolved", t4.getState());
		assertEquals("Closed", t5.getState());
		assertEquals("Cancelled", t6.getState());
	}
	
	/**
	 * Tests Ticket.getSubject().
	 */
	@Test
	public void tesetGetSubject() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(SUBJECT, t.getSubject());
	}
	
	/**
	 * Tests Ticket.getTicketId().
	 */
	@Test
	public void testGetTicketId() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(1, t.getTicketId());
	}
	
	/**
	 * Tests Ticket.getTicketType().
	 */
	@Test
	public void testGetTicketType() {
		Ticket t1 = new Ticket(ID, STATE, "Incident", SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t2 = new Ticket(ID, STATE, "Request", SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(TicketType.INCIDENT, t1.getTicketType());
		assertEquals(TicketType.REQUEST, t2.getTicketType());
	}
	
	/**
	 * Tests Ticket.getTicketTypeString().
	 */
	@Test
	public void testGetTicketTypeString() {
		Ticket t1 = new Ticket(ID, STATE, "Incident", SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Ticket t2 = new Ticket(ID, STATE, "Request", SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals("Incident", t1.getTicketTypeString());
		assertEquals("Request", t2.getTicketTypeString());
	}
	
	/**
	 * Tests Ticket.update(command).
	 */
	@Test
	public void testUpdate() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		Command c1 = new Command(CommandValue.PROCESS, OWNER, null, null, null, "hi");
		Command c2 = new Command(CommandValue.FEEDBACK, null, FeedbackCode.AWAITING_CALLER, null, null, "hi");
		Command c3 = new Command(CommandValue.RESOLVE, null, null, ResolutionCode.CALLER_CLOSED, null, "hi");
		Command c4 = new Command(CommandValue.CONFIRM, null, null, null, null, "hi");
		Command c5 = new Command(CommandValue.REOPEN, null, null, null, null, "hi");
		
		Command c6 = new Command(CommandValue.RESOLVE, null, null, ResolutionCode.NOT_COMPLETED, null, "hi");
		Command c7 = new Command(CommandValue.FEEDBACK, null, FeedbackCode.AWAITING_CHANGE, null, null, "hi");
		Command c8 = new Command(CommandValue.PROCESS, "new owner", null, null, null, "hi");
		Command c9 = new Command(CommandValue.CANCEL, null, null, null, CancellationCode.INAPPROPRIATE, "hi");
		t.update(c1);
		assertEquals(Ticket.WORKING_NAME, t.getState());
		t.update(c2);
		t.update(c3);
		t.update(c4);
		t.update(c5);
		t.update(c6);
		t.update(c7);
		t.update(c8);
		t.update(c9);
	}
	
	/**
	 * Tests Ticket.toString().
	 */
	@Test
	public void testToString() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("hi");
		notes.add("hello");
		
		Ticket t1 = new Ticket(ID, "New", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t2 = new Ticket(ID, "Working", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t3 = new Ticket(ID, "Feedback", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t4 = new Ticket(ID, "Resolved", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t5 = new Ticket(ID, "Closed", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t6 = new Ticket(ID, "Cancelled", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		
		assertEquals("*" + ID + "#" + "New" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t1.getNotes(), t1.toString());
		assertEquals("*" + ID + "#" + "Working" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t2.getNotes(), t2.toString());
		assertEquals("*" + ID + "#" + "Feedback" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t3.getNotes(), t3.toString());
		assertEquals("*" + ID + "#" + "Resolved" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t4.getNotes(), t4.toString());
		assertEquals("*" + ID + "#" + "Closed" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t5.getNotes(), t5.toString());
		assertEquals("*" + ID + "#" + "Cancelled" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t6.getNotes(), t6.toString());
	}
}
