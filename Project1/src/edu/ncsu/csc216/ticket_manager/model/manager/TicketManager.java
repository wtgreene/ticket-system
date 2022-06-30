/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.manager;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.io.TicketReader;
import edu.ncsu.csc216.ticket_manager.model.io.TicketWriter;
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
	
	/** instance of the TicketManager */
	private static TicketManager instance;
	
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
		 if (instance == null) {
			instance = new TicketManager();
		}
		
		return instance;
	}
	
	/**
	 * Saves a ticket list to a file.
	 * 
	 * @param filename file to save tickets to
	 */
	public void saveTicketsToFile(String filename) {
		TicketWriter.writeTicketFile(filename, ticketList.getTickets());
	}
	
	/**
	 * Loads a ticket list from a file.
	 * 
	 * @param filename file to read tickets from
	 */
	public void loadTicketsFromFile(String filename) {
		ticketList.addTickets(TicketReader.readTicketFile(filename));
		Ticket.setCounter(ticketList.getTickets().size());
	}
	
	/**
	 * Creates a new ticket list.
	 */
	public void createNewTicketList() {
		ticketList = new TicketList();
	}
	
	/**
	 * Returns a String array of tickets.
	 * 
	 * @return a String array of tickets
	 */
	public String[][] getTicketsForDisplay() {
		String[][] s = new String[ticketList.getTickets().size()][6];
		
		for (int i = 0; i < ticketList.getTickets().size(); i++) {
			Ticket t = ticketList.getTickets().get(i);
			
			s[i][0] = Integer.toString(t.getTicketId());
			s[i][1] = t.getTicketTypeString();
			s[i][2] = t.getState();
			s[i][3] = t.getSubject();
			s[i][4] = t.getCategory();
			s[i][5] = t.getPriority();
		}
		
		return s;
	}
	
	/**
	 * Returns a String array of tickets by ticket type.
	 * 
	 * @param ticketType ticket type
	 * @return a String array of tickets by ticket type
	 */
	public String[][] getTicketsForDisplayByType(TicketType ticketType) {
		int numTickets = 0;
		
		for (int i = 0; i < ticketList.getTickets().size(); i++) {
			if (ticketList.getTickets().get(i).getTicketType() == ticketType) {
				numTickets++;
			}
		}
		
		String[][] s = new String[numTickets][6];
		int counter = 0;
		
		for (int i = 0; i < ticketList.getTickets().size(); i++) {
			if (ticketList.getTickets().get(i).getTicketType() == ticketType) {
				Ticket t = ticketList.getTickets().get(i);
				
				s[counter][0] = Integer.toString(t.getTicketId());
				s[counter][1] = t.getTicketTypeString();
				s[counter][2] = t.getState();
				s[counter][3] = t.getSubject();
				s[counter][4] = t.getCategory();
				s[counter][5] = t.getPriority();
				
				counter++;
			}
		}
		
		return s;
	}
	
	/**
	 * Returns a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @return a specified ticket
	 */
	public Ticket getTicketById(int ticketId) {
		return ticketList.getTicketById(ticketId);
	}
	
	/**
	 * Executes command for a specified ticket.
	 * 
	 * @param ticketId ticket id
	 * @param command command to change ticket characteristic
	 */
	public void executeCommand(int ticketId, Command command) {
		ticketList.executeCommand(ticketId, command);
	}
	
	/**
	 * Deletes a specified ticket.
	 * 
	 * @param ticketId ticket id
	 */
	public void deleteTicketById(int ticketId) {
		ticketList.deleteTicketById(ticketId);
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
		ticketList.addTicket(ticketType, subject, caller, category, priority, note);
	}
}
