/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Reads tickets from a text file.
 * 
 * @author Will Greene
 */
public class TicketReader {
	
//	/** id */
//	private static int id = 0;
//	/** state name */
//	private static String state;
//	/** ticket type - string */
//	private static String ticketType;
//	/** subject */
//	private static String subject;
//	/** caller */
//	private static String caller;
//	/** category */
//	private static String category;
//	/** priority */
//	private static String priority;
//	/** owner */
//	private static String owner;
//	/** code */
//	private static String code;
//	/** notes */
//	private static ArrayList<String> notes = new ArrayList<String>();
	
	/**
	 * Reads tickets from a text file and returns a list of valid tickets.
	 * 
	 * @param filename file to read tickets from
	 * @return a list of valid tickets
	 * @throws FileNotFoundException if errors result from processing the file
	 */
	public static ArrayList<Ticket> readTicketFile(String filename) {
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
		
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		
		int id = 0;
		String state = "";
		String ticketType = "";
		String subject = "";
		String caller = "";
		String category = "";
		String priority = "";
		String owner = "";
		String code = "";
		ArrayList<String> notes = new ArrayList<String>();
		
		while (fileReader.hasNextLine()) {
			
			String line = fileReader.nextLine();
			
			if (line.charAt(0) == '*' && id != 0) {	
				ticketList.add(new Ticket(id, state, ticketType, subject, caller, category, priority, owner, code, notes));
			}

			if (line.charAt(0) == '*' && id == 0) {

				try {
					Scanner lineScanner = new Scanner(line);
					lineScanner.useDelimiter("#");

					String s1 = lineScanner.next();
					String s2 = s1.substring(1);
					id = Integer.parseInt(s2);

					state = lineScanner.next();
					ticketType = lineScanner.next();
					subject = lineScanner.next();
					caller = lineScanner.next();
					category = lineScanner.next();
					priority = lineScanner.next();
					owner = lineScanner.next();

					code = null;

					if (lineScanner.hasNext()) {
						code = lineScanner.next();
					}
					
					lineScanner.close();

				} catch (InputMismatchException e) {
					throw new IllegalArgumentException("No Such Element Exception.");
				} catch (NoSuchElementException e) {
					throw new IllegalArgumentException("Input Mismatch Exception.");
				}
			}
			
			else if (line.charAt(0) == '-') {
				notes.add(line);
			}
		}
		
		return ticketList;
	}
}
