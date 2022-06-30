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
	
	private static int id = 0;
	private static String state;
	private static String ticketType;
	private static String subject;
	private static String caller;
	private static String category;
	private static String priority;
	private static String owner;
	private static String code;
	private static ArrayList<String> notes = new ArrayList<String>();
	private static int noteCounter = 0;
	private static int totalNoteCounter = 0;
	private static int lastNoteAmount = 0;
	
	/**
	 * Reads tickets from a text file and returns a list of valid tickets.
	 * 
	 * @param filename file to read tickets from
	 * @return a list of valid tickets
	 * @throws FileNotFoundException if errors result from processing the file
	 */
	public static ArrayList<Ticket> readTicketFile(String filename) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(filename));
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		
		while (fileReader.hasNextLine()) {
			
			String line = fileReader.nextLine();
			
			if (line.charAt(0) == '*' && id != 0) {
				
				ArrayList<String> ticketNotes = new ArrayList<String>();
				for (int i = lastNoteAmount; i < totalNoteCounter; i++) {
					ticketNotes.add(notes.get(i));
				}
					
				ticketList.add(new Ticket(id, state, ticketType, subject, caller, category, priority, owner, code, notes));
				ticketNotes.clear();
				lastNoteAmount = totalNoteCounter;
				id = 0;
				noteCounter = 0;
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
				noteCounter++;
				totalNoteCounter++;
			}
		}
		
		return ticketList;
	}
}
