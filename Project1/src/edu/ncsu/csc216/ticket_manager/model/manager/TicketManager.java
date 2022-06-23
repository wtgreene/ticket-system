/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Controls creation and modification of TicketLists.
 * 
 * @author Will Greene
 */
public class TicketManager {
	
	/** ticket list */
	private TicketList ticketList;
	
	/**
	 * Constructs a TicketManager.
	 */
	private TicketManager() {
		ticketList = new TicketList();
	}
	
	/**
	 * Returns an instance of TicketManager.
	 * 
	 * @return an instance of TicketManager
	 */
	public static TicketManager getInstance() {
		return null;
	}
	
	/**
	 * Saves a ticket list to a file.
	 * 
	 * @param filename file to save tickets to
	 */
	public void saveTicketsToFile(String filename) {
		// save
	}
	
	/**
	 * Loads a ticket list from a file.
	 * 
	 * @param filename file to read tickets from
	 */
	public void loadTicketsFromFile(String filename) {
		// load
	}
	
	/**
	 * Creates a new ticket list.
	 */
	public void createNewTicketList() {
		// create
	}
	
	/**
	 * Returns a String array of tickets.
	 * 
	 * @return a String array of tickets
	 */
	public String[][] getTicketsForDisplay() {
		return null;
	}
	
	/**
	 * Returns a String array of tickets by ticket type.
	 * 
	 * @param ticketType ticket type
	 * @return a String array of tickets by ticket type
	 */
	public String[][] getTicketsForDisplayByType(TicketType ticketType) {
		return null;
	}
	
	/**
	 * Returns a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @return a specified ticket
	 */
	public Ticket getTicketById(int ticketId) {
		return ticketList.getTicketById(ticketId - 1);
	}
	
	/**
	 * Executes command for a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @param command command to change ticket characteristic
	 */
	public void executeCommand(int ticketId, Command command) {
		// execute
	}
	
	/**
	 * Deletes a specified ticket.
	 * 
	 * @param ticketId ticket id
	 */
	public void deleteTicketById(int ticketId) {
		// delete
	}
	
	/**
	 * Adds a ticket to the list.
	 * 
	 * @param ticketType ticket type
	 * @param subject ticket subject
	 * @param caller ticket caller 
	 * @param category ticket category
	 * @param priority ticket priority
	 * @param note ticket note(s)
	 */
	public void addTicketToList(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note) {
		// add
	}
}
