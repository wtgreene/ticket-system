/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.command;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

/**
 * Tests Command.java
 * 
 * @author Will Greene
 */
public class CommandTest {
		
	/**
	 * Tests Command constructor.
	 */
	@Test
	public void testCommand() {
		assertDoesNotThrow(() -> new Command(Command.CommandValue.PROCESS, "wgreene", null, null, null, "hi"), "Should not throw exception");
		assertThrows(IllegalArgumentException.class, () -> new Command(null, "wgreene", null, null, null, "hi"));
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.PROCESS, null, null, null, null, "hi"));
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.FEEDBACK, null, null, null, null, "hi"));
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.RESOLVE, null, null, null, null, "hi"));
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.CANCEL, null, null, null, null, "hi"));
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.PROCESS, "wgreene", null, null, null, null));
		assertThrows(IllegalArgumentException.class, () -> new Command(Command.CommandValue.PROCESS, "wgreene", null, null, null, ""));
	}
	
	/**
	 * Tests Command.getCommandValue().
	 */
	@Test
	public void testGetCommandValue() {
		Command c = new Command(Command.CommandValue.PROCESS, "wgreene", null, null, null, "hi");
		assertEquals(c.getCommand(), Command.CommandValue.PROCESS);
	}
	
	/**
	 * Tests Command.getOwnerId().
	 */
	@Test
	public void testGetOwnerId() {
		Command c = new Command(Command.CommandValue.PROCESS, "wgreene", null, null, null, "hi");
		assertEquals(c.getOwnerId(), "wgreene");
	}
	
	/**
	 * Tests Command.getResolutionCode().
	 */
	@Test
	public void testGetResolutionCode() {
		Command c = new Command(Command.CommandValue.RESOLVE, "wgreene", null, Command.ResolutionCode.CALLER_CLOSED, null, "hi");
		assertEquals(c.getResolutionCode(), Command.ResolutionCode.CALLER_CLOSED);
	}
	
	/**
	 * Tests Command.getNote().
	 */
	@Test
	public void testGetNote() {
		Command c = new Command(Command.CommandValue.PROCESS, "wgreene", null, null, null, "hi");
		assertEquals(c.getNote(), "hi");
	}
	
	/**
	 * Tests Command.getFeedbackCode().
	 */
	@Test
	public void testGetFeedbackCode() {
		Command c = new Command(Command.CommandValue.FEEDBACK, "wgreene", Command.FeedbackCode.AWAITING_CALLER, null, null, "hi");
		assertEquals(c.getFeedbackCode(), Command.FeedbackCode.AWAITING_CALLER);
	}
	
	/**
	 * Tests Command.getCancellationCode().
	 */
	@Test
	public void testGetCancellationCode() {
		Command c = new Command(Command.CommandValue.CANCEL, "wgreene", null, null, Command.CancellationCode.DUPLICATE, "hi");
		assertEquals(c.getCancellationCode(), Command.CancellationCode.DUPLICATE);
	}
}
