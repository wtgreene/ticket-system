/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Holds a list of tickets.
 * 
 * @author Will Greene
 */
public class TicketList {
	
	/** ticket list */
//	private ArrayList<Ticket> tickets;
	
	/**
	 * Constructs a TicketList.
	 */
	public TicketList() {
		
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
	 * @return what does this return ? TODO
	 */
	public int addTicket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note) {
		return 0;
	}
	
	/**
	 * Adds a list of tickets to the list (the list is cleared first).
	 * 
	 * @param ticketList list of tickets to add
	 */
	public void addTickets(ArrayList<Ticket> ticketList) {
		
	}
	
	/**
	 * Returns ticket list.
	 * 
	 * @return ticket list
	 */
	public ArrayList<Ticket> getTickets() {
		return null;
	}
	
	/**
	 * Returns ticket list by ticket type.
	 * 
	 * @param ticketType ticket type to filter on
	 * @return ticket list by ticket type
	 */
	public ArrayList<Ticket> getTicketsByType(TicketType ticketType) {
		return null;
	}
	
	/**
	 * Returns a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @return a specified ticket
	 */
	public Ticket getTicketById(int ticketId) {
		return null;
	}
	
	/**
	 * Executes a command on a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @param command command to change ticket characteristic
	 */
	public void executeCommand(int ticketId, Command command) {
		
	}
	
	/**
	 * Deletes a ticket from the list.
	 * 
	 * @param ticketId ticket id of the ticket to delete
	 */
	public void deleteTicketById(int ticketId) {
		
	}
}
