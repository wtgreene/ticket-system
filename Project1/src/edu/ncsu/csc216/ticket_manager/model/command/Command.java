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
		
	/** command value */
	private CommandValue c;
	/** feedback code */
	private FeedbackCode feedbackCode;
	/** resolution code */
	private ResolutionCode resolutionCode;
	/** cancellation code */
	private CancellationCode cancellationCode;
	
	/**
	 * Constructs a Command object.
	 * 
	 * @param c command
	 * @param ownerId owner id
	 * @param feedbackCode FeedbackCode
	 * @param resolutionCode ResolutionCode
	 * @param cancellationCode CancellationCode
	 * @param note note
	 * @throws IllegalArugmentException if parameters are invalid in one of the 6 ways below
	 */
	public Command(CommandValue c, String ownerId, FeedbackCode feedbackCode, ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {
		
		// parameter error checking - null command
		if (c == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - process command & null / empty owner id
		if (c == CommandValue.PROCESS && (ownerId == null || "".equals(ownerId))) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - feedback command & null / empty feedback code
		if (c == CommandValue.FEEDBACK && feedbackCode == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - resolve command & null / empty resolution code
		if (c == CommandValue.RESOLVE && resolutionCode == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - cancel command & null / empty cancellation code
		if (c == CommandValue.CANCEL && cancellationCode == null) {
			throw new IllegalArgumentException("TODO");
		}
		
		// parameter error checking - null / empty note
		if (note == null || "".equals(note)) {
			throw new IllegalArgumentException("TODO");
		}
		
		this.ownerId = ownerId;
		this.note = note;
		
		this.c = c;
		this.feedbackCode = feedbackCode;
		this.resolutionCode = resolutionCode;
		this.cancellationCode = cancellationCode;
	}
	
	/**
	 * Returns CommandValue.
	 * @return CommandValue.
	 */
	public CommandValue getCommand() {
		return c;
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
		return resolutionCode;
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
		return feedbackCode;
	}

	/**
	 * Returns CancellationCode.
	 * @return CancellationCode
	 */
	public CancellationCode getCancellationCode() {
		return cancellationCode;
	}
}
