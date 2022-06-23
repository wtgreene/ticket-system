/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import java.util.List;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Writes tickets to a text file.
 * 
 * @author Will Greene
 */
public class TicketWriter {
	
	/**
	 * Writes tickets to a text file.
	 * 
	 * @param filename file to write tickets to
	 * @param ticketList ArrayList of tickets
	 * @throws IllegalArgumentException if errors result from writing to the file
	 */
	public static void writeTicketFile(String filename, List<Ticket> ticketList) {
		// use Ticket.toString()
		
//		if (/*errors*/) {
//			throw new IllegalArgumentException("Unable to save file.");
//		}
	}
}
