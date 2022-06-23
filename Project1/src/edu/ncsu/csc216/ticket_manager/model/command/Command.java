/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.command;

/**
 * Creates objects that encapsulates user actions (or transitions) that cause the state of a Ticket to update.
 * This description is derived from the instructions' Implementation section.
 * 
 * @author Will Greene
 */
public class Command {
	
	/** command values */
	public enum CommandValue { PROCESS, FEEDBACK, RESOLVE, CONFIRM, REOPEN, CANCEL }
	/** feedback codes */
	public enum FeedbackCode { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_PROVIDER }
	/** resolution codes */
	public enum ResolutionCode { COMPLETED, NOT_COMPLETED, SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }
	/** cancellation codes */
	public enum CancellationCode { DUPLICATE, INAPPROPRIATE }
	
	/** feedback code for awaiting caller */
	public static final String F_CALLER = "Awaiting Caller";
	/** feedback code for awaiting change */
	public static final String F_CHANGE = "Awaiting Change";
	/** feedback code for awaiting provider */
	public static final String F_PROVIDER = "Awaiting Provider";
	/** resolution code for completed */
	public static final String RC_COMPLETED = "Completed";
	/** resolution code for not completed */
	public static final String RC_NOT_COMPLETED = "Not Completed";
	/** resolution code for solved */
	public static final String RC_SOLVED = "Solved";
	/** resolution code for workaround */
	public static final String RC_WORKAROUND = "Workaround";
	/** resolution code for not solved */
	public static final String RC_NOT_SOLVED = "Not Solved";
	/** resolution code for caller closed */
	public static final String RC_CALLER_CLOSED = "Caller Closed";
	/** cancellation code for duplicate */
	public static final String CC_DUPLICATE = "Duplicate";
	/** cancellation code for inappropriate */
	public static final String CC_INAPPROPRIATE = "Inapproriate";
	
	/** owner id */
	private String ownerId;
	/** note */
	private String note;
	
	// [part 1] not in UML diagram but would these be necessary?
	
	/** command value */
	private CommandValue command;
	/** feedback code */
	private FeedbackCode feedback;
	/** resolution code */
	private ResolutionCode resolution;
	/** cancellation code */
	private CancellationCode cancellation;
	
	/**
	 * Constructs a Command object.
	 * 
	 * @param command c
	 * @param ownerId owner id
	 * @param feedback FeedbackCode
	 * @param resolution ResolutionCode
	 * @param cancellation CancellationCode
	 * @param note note
	 * @throws IllegalArugmentException if parameters are invalid in one of the 6 ways below
	 */
	public Command(CommandValue command, String ownerId, FeedbackCode feedback, ResolutionCode resolution, CancellationCode cancellation, String note) {
		
		// parameter error checking - null command
		if (command == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - process command & null / empty owner id
		if (command == CommandValue.PROCESS && (ownerId == null || "".equals(ownerId))) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - feedback command & null / empty feedback code
		if (command == CommandValue.FEEDBACK && feedback == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - resolve command & null / empty resolution code
		if (command == CommandValue.RESOLVE && resolution == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - cancel command & null / empty cancellation code
		if (command == CommandValue.CANCEL && cancellation == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - null / empty note
		if (note == null || "".equals(note)) {
			throw new IllegalArgumentException("TODO");
		}
		
		this.ownerId = ownerId;
		this.note = note;
		
		// [part 2] not in UML diagram but would these be necessary?
		// also, should setters be used for this class?
		this.command = command;
		this.feedback = feedback;
		this.resolution = resolution;
		this.cancellation = cancellation;
	}
	
	/**
	 * Returns CommandValue.
	 * @return CommandValue.
	 */
	public CommandValue getCommandValue() {
		return command;
	}
	
	/**
	 * Returns owner id.
	 * @return owner id
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Returns ResolutionCode.
	 * @return ResolutionCode
	 */
	public ResolutionCode getResolutionCode() {
		return resolution;
	}
	
	/**
	 * Returns note.
	 * @return note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Returns FeedbackCode.
	 * @return FeedbackCode
	 */
	public FeedbackCode getFeedbackCode() {
		return feedback;
	}

	/**
	 * Returns CancellationCode.
	 * @return CancellationCode
	 */
	public CancellationCode getCancellationCode() {
		return cancellation;
	}
}