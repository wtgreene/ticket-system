/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Tests TicketList.java
 * 
 * @author Will Greene
 */
public class TicketListTest {
	
	private static final String SUBJECT = "Help";
	private static final String CALLER = "wgreene";
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
	 * Tests TicketList constructor.
	 */
	@Test
	public void testTicketList() {
		assertDoesNotThrow(() -> new TicketList());
		
		TicketList list = new TicketList();
		list.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		assertEquals(1, list.getTicketById(1).getTicketId());
	}
	
	/**
	 * Tests TicketList.addTicket().
	 */
	@Test
	public void testAddTicket() {
		TicketList list = new TicketList();
		list.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		assertEquals(1, list.getTicketById(1).getTicketId());
	}
	
	/**
	 * Tests TicketList.addTickets().
	 */
	@Test
	public void testAddTickets() {
		TicketList list1 = new TicketList();
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		
		TicketList list2 = new TicketList();
		list2.addTickets(list1.getTickets());
		assertEquals(list1.getTicketById(1).getTicketId(), list2.getTicketById(1).getTicketId());
	}
	
	/**
	 * Tests TicketList.getTickets().
	 */
	@Test
	public void testGetTickets() {
		TicketList list1 = new TicketList();
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, "Help me", CALLER, CATEGORY, PRIORITY, NOTE);
		
		ArrayList<Ticket> list2 = list1.getTickets();
		assertEquals(list2.size(), 2);
		assertEquals(list2.get(0).getTicketType(), TICKET_TYPE);
		assertEquals(list2.get(1).getSubject(), "Help me");
	}
	
	/**
	 * Tests TicketList.getTicketsByType().
	 */
	@Test
	public void testGetTicketsByType() {
		TicketList list1 = new TicketList();
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TicketType.REQUEST, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TicketType.REQUEST, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		
		ArrayList<Ticket> list2 = list1.getTicketsByType(TICKET_TYPE);
		assertEquals(list2.size(), 3);
		assertEquals(list2.get(0).getTicketId(), 1);
		assertEquals(list2.get(1).getTicketId(), 2);
		assertEquals(list2.get(2).getTicketId(), 5);
	}
	
	/**
	 * Tests TicketList.getTicketById().
	 */
	@Test
	public void testGetTicketById() {
		TicketList list1 = new TicketList();
		list1.addTicket(TICKET_TYPE, SUBJECT, "will1", CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, "will2", CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TicketType.REQUEST, SUBJECT, "will3", CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TicketType.REQUEST, SUBJECT, "will4", CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, "will5", CATEGORY, PRIORITY, NOTE);
		
		assertEquals(list1.getTicketById(5).getCaller(), "will5");
	}
	
	/**
	 * Tests TicketList.executeCommand().
	 */
	@Test
	public void testExecuteCommand() {
		TicketList list1 = new TicketList();
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		
		Command c = new Command(CommandValue.PROCESS, "owner", null, null, null, NOTE);
		list1.executeCommand(1, c);
	}
	
	/**
	 * Tests TicketList.deleteTicketById().
	 */
	@Test
	public void testDeleteTicketById() {
		TicketList list1 = new TicketList();
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TicketType.REQUEST, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TicketType.REQUEST, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE);
		list1.addTicket(TICKET_TYPE, SUBJECT, "notwgreene", CATEGORY, PRIORITY, NOTE);
		
		list1.deleteTicketById(4);
		assertNull(list1.getTicketById(4));
	}
}
