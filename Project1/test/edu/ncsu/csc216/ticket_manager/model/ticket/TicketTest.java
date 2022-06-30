/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.ticket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
		Ticket t5 = new Ticket(ID, STATE, "Request", SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Completed", NOTES);
		Ticket t6 = new Ticket(ID, STATE, "Request", SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, "Not Completed", NOTES);
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
		Ticket t6 = new Ticket(ID, "Canceled", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals("New", t1.getState());
		assertEquals("Working", t2.getState());
		assertEquals("Feedback", t3.getState());
		assertEquals("Resolved", t4.getState());
		assertEquals("Closed", t5.getState());
		assertEquals("Canceled", t6.getState());
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
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("hi");
		notes.add("hello");
		
		Ticket t11 = new Ticket(ID, "New", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, null, notes);
		Ticket t12 = new Ticket(ID, "New", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, null, notes);
		Ticket t21 = new Ticket(ID, "Working", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, null, notes);
		Ticket t22 = new Ticket(ID, "Working", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, null, notes);
		Ticket t23 = new Ticket(ID, "Working", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, null, notes);
		Ticket t31 = new Ticket(ID, "Feedback", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.F_PROVIDER, notes);
		Ticket t32 = new Ticket(ID, "Feedback", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.F_PROVIDER, notes);
		Ticket t33 = new Ticket(ID, "Feedback", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.F_PROVIDER, notes);
		Ticket t41 = new Ticket(ID, "Resolved", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.RC_COMPLETED, notes);
		Ticket t42 = new Ticket(ID, "Resolved", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.RC_COMPLETED, notes);
		Ticket t43 = new Ticket(ID, "Resolved", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.RC_COMPLETED, notes);
		Ticket t51 = new Ticket(ID, "Closed", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, null, notes);
		Ticket t61 = new Ticket(ID, "Canceled", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.CC_DUPLICATE, notes);
		
		Command process = new Command(CommandValue.PROCESS, OWNER, null, null, null, "hi");
		Command reopen = new Command(CommandValue.REOPEN, null, null, null, null, "hi");
		Command feedback = new Command(CommandValue.FEEDBACK, null, FeedbackCode.AWAITING_CALLER, null, null, "hi");
		Command resolve = new Command(CommandValue.RESOLVE, null, null, ResolutionCode.CALLER_CLOSED, null, "hi");
		Command confirm = new Command(CommandValue.CONFIRM, null, null, null, null, "hi");
		Command cancel = new Command(CommandValue.CANCEL, null, null, null, CancellationCode.INAPPROPRIATE, "hi");

		assertThrows(UnsupportedOperationException.class, () -> t11.update(reopen));
		assertThrows(UnsupportedOperationException.class, () -> t11.update(feedback));
		assertThrows(UnsupportedOperationException.class, () -> t11.update(resolve));
		assertThrows(UnsupportedOperationException.class, () -> t11.update(confirm));
		t11.update(cancel);
		assertEquals("Inappropriate", t11.getCancellationCode());
		t12.update(process);
		
		assertThrows(UnsupportedOperationException.class, () -> t21.update(process));
		assertThrows(UnsupportedOperationException.class, () -> t21.update(reopen));
		assertThrows(UnsupportedOperationException.class, () -> t21.update(confirm));
		t21.update(feedback);
		t22.update(resolve);
		t23.update(cancel);
		
		assertThrows(UnsupportedOperationException.class, () -> t31.update(process));
		assertThrows(UnsupportedOperationException.class, () -> t31.update(feedback));
		assertThrows(UnsupportedOperationException.class, () -> t31.update(confirm));
		t31.update(reopen);
		t32.update(resolve);
		t33.update(cancel);
		
		assertThrows(UnsupportedOperationException.class, () -> t41.update(process));
		assertThrows(UnsupportedOperationException.class, () -> t41.update(resolve));
		assertThrows(UnsupportedOperationException.class, () -> t41.update(cancel));
		t41.update(reopen);
		t42.update(feedback);
		t43.update(confirm);
		
		assertThrows(UnsupportedOperationException.class, () -> t51.update(process));
		assertThrows(UnsupportedOperationException.class, () -> t51.update(feedback));
		assertThrows(UnsupportedOperationException.class, () -> t51.update(resolve));
		assertThrows(UnsupportedOperationException.class, () -> t51.update(confirm));
		assertThrows(UnsupportedOperationException.class, () -> t51.update(cancel));
		t51.update(reopen);
		
		assertThrows(UnsupportedOperationException.class, () -> t61.update(process));
		assertThrows(UnsupportedOperationException.class, () -> t61.update(reopen));
		assertThrows(UnsupportedOperationException.class, () -> t61.update(feedback));
		assertThrows(UnsupportedOperationException.class, () -> t61.update(resolve));
		assertThrows(UnsupportedOperationException.class, () -> t61.update(confirm));
		assertThrows(UnsupportedOperationException.class, () -> t61.update(cancel));
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
		Ticket t3 = new Ticket(ID, "Feedback", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.F_CALLER, notes);
		Ticket t4 = new Ticket(ID, "Resolved", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.RC_CALLER_CLOSED, notes);
		Ticket t5 = new Ticket(ID, "Closed", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		Ticket t6 = new Ticket(ID, "Canceled", TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.CC_DUPLICATE, notes);
		
		assertEquals("*" + ID + "#" + "New" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t1.getNotes(), t1.toString());
		assertEquals("*" + ID + "#" + "Working" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t2.getNotes(), t2.toString());
		assertEquals("*" + ID + "#" + "Feedback" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + Command.F_CALLER + "\n" + t3.getNotes(), t3.toString());
		assertEquals("*" + ID + "#" + "Resolved" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + Command.RC_CALLER_CLOSED + "\n" + t4.getNotes(), t4.toString());
		assertEquals("*" + ID + "#" + "Closed" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t5.getNotes(), t5.toString());
		assertEquals("*" + ID + "#" + "Canceled" + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + Command.CC_DUPLICATE + "\n" + t6.getNotes(), t6.toString());
	}
}
