/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
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
		PrintStream fileWriter = null;
		File file = null;
		
		try {
			file = new File(filename);
			fileWriter = new PrintStream(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot write to file.");
		}

		for (int i = 0; i < ticketList.size(); i++) {
			fileWriter.print(ticketList.get(i).toString());
		}

		fileWriter.close();
	}
}
