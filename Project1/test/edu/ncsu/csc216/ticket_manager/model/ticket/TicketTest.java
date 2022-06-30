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
		assertDoesNotThrow(() -> new Ticket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE));
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
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.CC_DUPLICATE, NOTES);
		assertEquals(Command.CC_DUPLICATE, t.getCancellationCode());
	}
	
	/**
	 * Tests Ticket.getCategory().
	 */
	@Test
	public void testGetCategory() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(CATEGORY_STRING, t.getCategory());
	}
	
	/**
	 * Tests Ticket.getFeedbackCode().
	 */
	@Test
	public void testGetFeedbackCode() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.F_CALLER, NOTES);
		assertEquals(Command.F_CALLER, t.getFeedbackCode());
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
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(PRIORITY_STRING, t.getPriority());
	}
	
	/**
	 * Tests Ticket.getResolutionCode().
	 */
	@Test
	public void testGetResolutionCode() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, Command.RC_CALLER_CLOSED, NOTES);
		assertEquals(Command.RC_CALLER_CLOSED, t.getResolutionCode());
	}
	
	/**
	 * Tests Ticket.getState().
	 */
	@Test
	public void testGetState() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(Ticket.NEW_NAME, t.getState());
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
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(TICKET_TYPE, t.getTicketType());
	}
	
	/**
	 * Tests Ticket.getTicketTypeString().
	 */
	@Test
	public void testGetTicketTypeString() {
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, NOTES);
		assertEquals(TICKET_TYPE_STRING, t.getTicketTypeString());
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
		t.update(c1);
		assertEquals(Ticket.WORKING_NAME, t.getState());
		t.update(c2);
		t.update(c3);
	}
	
	/**
	 * Tests Ticket.toString().
	 */
	@Test
	public void testToString() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("hi");
		notes.add("hello");
		
		Ticket t = new Ticket(ID, STATE, TICKET_TYPE_STRING, SUBJECT, CALLER, CATEGORY_STRING, PRIORITY_STRING, OWNER, CODE, notes);
		assertEquals("*" + ID + "#" + STATE + "#" + TICKET_TYPE_STRING + "#" + SUBJECT + "#" + CALLER + "#" + CATEGORY_STRING + "#" + PRIORITY_STRING + "#" + OWNER + "#" + CODE + "\n" + t.getNotes(), t.toString());
	}
}
