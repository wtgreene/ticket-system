/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Tests TicketManager.java
 * 
 * @author Will Greene
 */
public class TicketManagerTest {

	/** subject */
	private static final String SUBJECT = "Help";
	/** caller */
	private static final String CALLER = "wgreene";
	
	/** ticket type */
	private static final TicketType TICKET_TYPE = TicketType.INCIDENT;
	/** category */
	private static final Category CATEGORY = Category.INQUIRY;
	/** priority */
	private static final Priority PRIORITY = Priority.HIGH;
	/** note */
	private static final String NOTE = "hi";
	
	/** TicketManager instance */
	private TicketManager manager;
	
	/**
	 * Sets up the TicketManager and creates a new ticket list.
	 * 
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = TicketManager.getInstance();
		manager.createNewTicketList();
		Ticket.setCounter(1);
	}
	
	/**
	 * Tests TicketManager.saveTicketsToFile().
	 */
	@Test
	public void testSaveTicketsToFile() {
		manager = TicketManager.getInstance();
		assertDoesNotThrow(() -> manager.saveTicketsToFile("test-files/ticket99.txt"));
	}
	
	// flagged
	
	/**
	 * Tests TicketManager.loadTicketsFromFile().
	 */
	@Test
	public void testLoadTicketsFromFile() {
		manager = TicketManager.getInstance();
		assertDoesNotThrow(() -> manager.loadTicketsFromFile("test-files/ticket2.txt"));
	}
	
	/**
	 * Tests TicketManager.createNewTicketList().
	 */
	@Test
	public void testCreateNewTicketList() {
		manager = TicketManager.getInstance();
		assertDoesNotThrow(() -> manager.createNewTicketList());
	}
	
	/**
	 * Tests TicketManager.getTicketsForDisplay().
	 */
	@Test
	public void testGetTicketsForDisplay() {
		manager = TicketManager.getInstance();
		assertDoesNotThrow(() -> manager.getTicketsForDisplay());
	}
	
	// flagged
	
	/**
	 * Tests TicketManager.getTicketsForDisplayByType().
	 */
	@Test
	public void testGetTicketsForDisplayByType() {
		manager = TicketManager.getInstance();
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		assertDoesNotThrow(() -> manager.getTicketsForDisplayByType(TicketType.INCIDENT));
	}
	
	/**
	 * Tests TicketManager.getTicketById().
	 */
	@Test
	public void testGetTicketById() {
		manager = TicketManager.getInstance();
		assertDoesNotThrow(() -> manager.getTicketById(1));
	}
	
	// flagged
	
	/**
	 * Tests TicketManager.executeCommand().
	 */
	@Test
	public void testExecuteCommand() {
		manager = TicketManager.getInstance();
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		Command c = new Command(CommandValue.PROCESS, "wgreene", null, null, null, "hi");
		assertDoesNotThrow(() -> manager.executeCommand(1, c));
	}
	
	/**
	 * Tests TicketManager.deleteTicketById().
	 */
	@Test
	public void testDeleteTicketById() {
		manager = TicketManager.getInstance();
		assertDoesNotThrow(() -> manager.deleteTicketById(17));
	}
	
	/**
	 * Tests TicketManager.addTicketToList().
	 */
	@Test
	public void testAddTicketToList() {
		manager = TicketManager.getInstance();
		assertDoesNotThrow(() -> manager.addTicketToList(TICKET_TYPE, SUBJECT, CALLER, CATEGORY, PRIORITY, NOTE));
	}
}
