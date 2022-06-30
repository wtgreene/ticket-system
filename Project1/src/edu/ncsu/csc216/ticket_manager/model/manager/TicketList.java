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
	private ArrayList<Ticket> tickets;
	
	/**
	 * Constructs a TicketList.
	 */
	public TicketList() {
		tickets = new ArrayList<Ticket>();
		Ticket.setCounter(1);
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
		Ticket t = new Ticket(ticketType, subject, caller, category, priority, note);
		tickets.add(t);
		return 1;
	}
	
	/**
	 * Adds a list of tickets to the list (the list is cleared first).
	 * 
	 * @param ticketList list of tickets to add
	 */
	public void addTickets(ArrayList<Ticket> ticketList) {
		tickets = new ArrayList<Ticket>();
		
		for (int i = 0; i < ticketList.size(); i++) {
			tickets.add(ticketList.get(i));
		}
	}
	
	/**
	 * Returns ticket list.
	 * 
	 * @return ticket list
	 */
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	
	/**
	 * Returns ticket list by ticket type.
	 * 
	 * @param ticketType ticket type to filter on
	 * @return ticket list by ticket type
	 */
	public ArrayList<Ticket> getTicketsByType(TicketType ticketType) {
		ArrayList<Ticket> t = new ArrayList<Ticket>();
		
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getTicketType().equals(ticketType)) {
				t.add(tickets.get(i));
			}
		}
		
		return t;
	}
	
	/**
	 * Returns a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @return a specified ticket
	 */
	public Ticket getTicketById(int ticketId) {
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getTicketId() == ticketId) {
				return tickets.get(i);
			}
		}
		
		return null; // Or IAE
	}
	
	/**
	 * Executes a command on a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @param command command to change ticket characteristic
	 */
	public void executeCommand(int ticketId, Command command) {
		getTicketById(ticketId).update(command);
	}
	
	/**
	 * Deletes a ticket from the list.
	 * 
	 * @param ticketId ticket id of the ticket to delete
	 */
	public void deleteTicketById(int ticketId) {
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getTicketId() == ticketId) {
				tickets.remove(i);
			}
		}
	}
}
