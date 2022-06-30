/**
 * 
 */
package edu.ncsu.csc216.ticket_manager.model.ticket;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;

/**
 * Represents a single ticket in the ticket system.
 * 
 * @author Will Greene
 */
public class Ticket {

	/** ticket categories */
	public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }

	/** ticket priorities */
	public enum Priority { URGENT, HIGH, MEDIUM, LOW }
		
	/** ticket types */
	public enum TicketType { REQUEST, INCIDENT }

	/** ticket type - request */
	public static final String TT_REQUEST = "Request";
	/** ticket type - incident */
	public static final String TT_INCIDENT = "Incident";
	/** category - inquiry */
	public static final String C_INQUIRY = "Inquiry";
	/** category - software */
	public static final String C_SOFTWARE = "Software";
	/** category - hardware */
	public static final String C_HARDWARE = "Hardware";
	/** category - network */
	public static final String C_NETWORK = "Network";
	/** category - database */
	public static final String C_DATABASE = "Database";
	/** priority - urgent */
	public static final String P_URGENT = "Urgent";
	/** priority - high */
	public static final String P_HIGH = "High";
	/** priority - medium */
	public static final String P_MEDIUM = "Medium";
	/** priority - low */
	public static final String P_LOW = "Low";
	
	/** state of ticket - new */
	public static final String NEW_NAME = "New";
	/** state of ticket - working */
	public static final String WORKING_NAME = "Working";
	/** state of ticket - feedback */
	public static final String FEEDBACK_NAME = "Feedback";
	/** state of ticket - resolved */
	public static final String RESOLVED_NAME = "Resolved";
	/** state of ticket - closed */
	public static final String CLOSED_NAME = "Closed";
	/** state of ticket - cancelled */
	public static final String CANCELED_NAME = "Canceled";
	
	/** ticket state */
	private TicketState state;
	/** state of a new ticket */
	private final TicketState newState = new NewState();
	/** state of a working ticket */
	private final TicketState workingState = new WorkingState();
	/** state of a ticket in feedback */
	private final TicketState feedbackState = new FeedbackState();
	/** state of a ticket as resolved */
	private final TicketState resolvedState = new ResolvedState();
	/** state of a ticket as closed */
	private final TicketState closedState = new ClosedState();
	/** state of a ticket as cancelled */
	private final TicketState canceledState = new CanceledState();
	
	/** ticket feedback code */
	private Command.FeedbackCode feedbackCode;
	/** ticket resolution code */
	private Command.ResolutionCode resolutionCode;
	/** ticket cancellation code */
	private Command.CancellationCode cancellationCode;
	
	/** ticket category */
	private Category category;
	/** ticket priority */
	private Priority priority;
	/** ticket type */
	private TicketType ticketType;

	/** ticket counter (for ticket id purposes) */
	private static int counter = 1;
	/** ticket id */
	private int ticketId;
	/** ticket subject */
	private String subject;
	/** ticket caller */
	private String caller;
	/** ticket owner */
	private String owner;
	/** ticket note(s) */
	private ArrayList<String> notes = new ArrayList<String>();
	
	/**
	 * Increments the counter by 1.
	 */
	public static void incrementCounter() {
		counter++;
	}

	/**
	 * Sets the initial counter.
	 * 
	 * @param number number to begin counting with
	 * @throws IllegalArgumentException if invalid counter
	 */
	public static void setCounter(int number) {
		
		// parameter error checking - negative number, or zero
		if (number < 1) {
			throw new IllegalArgumentException("Invalid counter.");
		}
		
		counter = number;
	}

	/**
	 * Constructs a Ticket - reading from a ticket file.
	 * 
	 * @param id ticket id
	 * @param state ticket state
	 * @param ticketType ticket type
	 * @param subject ticket subject
	 * @param caller ticket caller
	 * @param category ticket category
	 * @param priority ticket priority
	 * @param owner ticket owner
	 * @param code ticket code
	 * @param notes ticket note(s)
	 */
	public Ticket(int id, String state, String ticketType, String subject, String caller, String category,
			String priority, String owner, String code, ArrayList<String> notes) {
			
		if (id > counter) {
			setCounter(id + 1);
		}
		
		ticketId = id;
		setState(state);
		setTicketType(ticketType);
		setSubject(subject);
		setCaller(caller);
		setCategory(category);
		setPriority(priority);
		setOwner(owner);
		setFeedbackCode(code);
		setResolutionCode(code);
		setCancellationCode(code);
	
		this.notes = notes;
	}

	/**
	 * Constructs a Ticket.
	 * 
	 * @param ticketType ticket type
	 * @param subject ticket subject
	 * @param caller ticket caller
	 * @param category ticket category
	 * @param priority ticket priority
	 * @param note ticket note(s)
	 */
	public Ticket(TicketType ticketType, String subject, String caller, Category category, Priority priority,
			String note) {
		
		ticketId = counter;
		Ticket.incrementCounter();
		
		if (ticketType == null) {
			throw new IllegalArgumentException("Invalid ticket type.");
		}
		
		switch (ticketType) {
		
		case REQUEST :
			setTicketType(TT_REQUEST);
			break;
		case INCIDENT :
			setTicketType(TT_INCIDENT);
			break;
			
		default :
			break;
		}
		
		setSubject(subject);
		setCaller(caller);
		
		if (category == null) {
			throw new IllegalArgumentException("Invalid category.");
		}
		
		switch (category) {
		
		case INQUIRY :
			setCategory(C_INQUIRY);
			break;
		case SOFTWARE :
			setCategory(C_SOFTWARE);
			break;
		case HARDWARE :
			setCategory(C_HARDWARE);
			break;
		case NETWORK :
			setCategory(C_NETWORK);
			break;
		case DATABASE :
			setCategory(C_DATABASE);
			break;
			
		default :
			break;
		}
		
		if (priority == null) {
			throw new IllegalArgumentException("Invalid priority.");
		}
		
		switch (priority) {
		
		case URGENT :
			setPriority(P_URGENT);
			break;
		case HIGH :
			setPriority(P_HIGH);
			break;
		case MEDIUM :
			setPriority(P_MEDIUM);
			break;
		case LOW :
			setPriority(P_LOW);
			break;
			
		default :
			break;
		}
		
		notes.add(note);
		setOwner("");
		
		state = newState;
		feedbackCode = null;
		resolutionCode = null;
		cancellationCode = null;
	}

	/**
	 * Returns caller.
	 * @return caller
	 */
	public String getCaller() {
		return caller;
	}

	/**
	 * Returns cancellation code.
	 * @return cancellationCode
	 */
	public String getCancellationCode() {
		
		switch (cancellationCode) {
		
		case DUPLICATE :
			return Command.CC_DUPLICATE;
		case INAPPROPRIATE :
			return Command.CC_INAPPROPRIATE;
			
		default:
			return null;
		}
	}

	/**
	 * Returns category.
	 * @return category
	 */
	public String getCategory() {
		
		switch (category) {
		
		case INQUIRY :
			return C_INQUIRY;
		case SOFTWARE :
			return C_SOFTWARE;
		case HARDWARE :
			return C_HARDWARE;
		case NETWORK :
			return C_NETWORK;
		case DATABASE :
			return C_DATABASE;
			
		default:
			return null;
		}
	}

	/**
	 * Returns feedback code.
	 * @return feedback code
	 */
	public String getFeedbackCode() {
		
		switch (feedbackCode) {
		
		case AWAITING_CALLER :
			return Command.F_CALLER;
		case AWAITING_CHANGE :
			return Command.F_CHANGE;
		case AWAITING_PROVIDER :
			return Command.F_PROVIDER;
			
		default:
			return null;
		}
	}

	/**
	 * Returns note(s).
	 * @return note(s)
	 */
	public String getNotes() {
		
		String s = "";
		
		for (int i = 0; i < notes.size(); i++) {
			s += "-" + notes.get(i) + "\n";
		}
		
		return s;
	}

	/**
	 * Returns owner.
	 * @return owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Returns priority.
	 * @return priority
	 */
	public String getPriority() {
		
		switch (priority) {
		
		case URGENT :
			return P_URGENT;
		case HIGH :
			return P_HIGH;
		case MEDIUM :
			return P_MEDIUM;
		case LOW :
			return P_LOW;
			
		default:
			return null;
		}	
	}

	/**
	 * Returns resolution code.
	 * @return resolution code
	 */
	public String getResolutionCode() {
		
		switch (resolutionCode) {
		
		case COMPLETED :
			return Command.RC_COMPLETED;
		case NOT_COMPLETED :
			return Command.RC_NOT_COMPLETED;
		case SOLVED :
			return Command.RC_SOLVED;
		case WORKAROUND :
			return Command.RC_WORKAROUND;
		case NOT_SOLVED :
			return Command.RC_NOT_SOLVED;
		case CALLER_CLOSED :
			return Command.RC_CALLER_CLOSED;
			
		default:
			return null;
		}	
	}

	/**
	 * Returns state.
	 * @return state
	 */
	public String getState() {
		return state.getStateName();
	}

	/**
	 * Returns subject.
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Returns ticket id.
	 * @return ticket id
	 */
	public int getTicketId() {
		return ticketId;
	}

	/**
	 * Returns ticket type as a TicketType.
	 * @return ticket type as a TicketType
	 */
	public TicketType getTicketType() {
		return ticketType;
	}
	
	/**
	 * Returns ticket type as a String.
	 * @return ticket type as a String
	 */
	public String getTicketTypeString() {
		
		switch (ticketType) {
		
		case REQUEST :
			return TT_REQUEST;
		case INCIDENT :
			return TT_INCIDENT;
		default:
			return null;
		}	
	}

	/**
	 * Sets caller.
	 * 
	 * @param caller ticket caller
	 * @throws IllegalArgumentException if invalid caller
	 */
	private void setCaller(String caller) {
		
		// parameter error checking - null or empty string
		if (caller == null || "".equals(caller)) {
			throw new IllegalArgumentException("Invalid caller.");
		}
		
		this.caller = caller;
	}

	/**
	 * Sets cancellation code.
	 * 
	 * @param cancellationCode ticket cancellation code
	 */
	private void setCancellationCode(String cancellationCode) {
		
		if (cancellationCode == null || "".equals(cancellationCode)) {
			this.cancellationCode = null;
		}
		
		else {
		
			switch (cancellationCode) {

			case Command.CC_DUPLICATE:
				this.cancellationCode = CancellationCode.DUPLICATE;
				break;
			case Command.CC_INAPPROPRIATE:
				this.cancellationCode = CancellationCode.INAPPROPRIATE;
				break;

			default:
				this.cancellationCode = null;
				break;
			}
		}
	}

	/**
	 * Sets category.
	 * 
	 * @param category ticket category
	 */
	private void setCategory(String category) {
		
		if (category == null || "".equals(category)) {
			throw new IllegalArgumentException("Invalid category.");
		}
		
		switch (category) {
		
		case C_INQUIRY :
			this.category = Category.INQUIRY;
			break;
		case C_SOFTWARE :
			this.category = Category.SOFTWARE;
			break;
		case C_HARDWARE :
			this.category = Category.HARDWARE;
			break;
		case C_NETWORK :
			this.category = Category.NETWORK;
			break;
		case C_DATABASE :
			this.category = Category.DATABASE;
			break;
			
		default:
			break;
		}
	}

	/**
	 * Sets owner.
	 * 
	 * @param owner ticket owner
	 */
	private void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Sets feedback code.
	 * 
	 * @param feedbackCode ticket feedback code
	 */
	private void setFeedbackCode(String feedbackCode) {
		
		if (feedbackCode == null || "".equals(feedbackCode)) {
			this.feedbackCode = null;
		}
		
		else {
		
			switch (feedbackCode) {

			case Command.F_CALLER:
				this.feedbackCode = FeedbackCode.AWAITING_CALLER;
				break;
			case Command.F_CHANGE:
				this.feedbackCode = FeedbackCode.AWAITING_CHANGE;
				break;
			case Command.F_PROVIDER:
				this.feedbackCode = FeedbackCode.AWAITING_PROVIDER;
				break;

			default:
				this.feedbackCode = null;
				break;
			}
		}
	}

	/**
	 * Sets priority.
	 * 
	 * @param priority ticket priority
	 */
	private void setPriority(String priority) {
		
		if (priority == null || "".equals(priority)) {
			throw new IllegalArgumentException("Invalid priority.");
		}
		
		switch (priority) {
		
		case P_URGENT :
			this.priority = Priority.URGENT;
			break;
		case P_HIGH :
			this.priority = Priority.HIGH;
			break;
		case P_MEDIUM :
			this.priority = Priority.MEDIUM;
			break;
		case P_LOW :
			this.priority = Priority.LOW;
			break;
			
		default:
			break;
		}
	}

	/**
	 * Sets resolution code.
	 * 
	 * @param resolutionCode ticket resolution code
	 */
	private void setResolutionCode(String resolutionCode) {
		
		if (resolutionCode == null || "".equals(resolutionCode)) {
			this.resolutionCode = null;
		}
		
		else {

			switch (resolutionCode) {

			case Command.RC_COMPLETED:
				this.resolutionCode = ResolutionCode.COMPLETED;
				break;
			case Command.RC_NOT_COMPLETED:
				this.resolutionCode = ResolutionCode.NOT_COMPLETED;
				break;
			case Command.RC_SOLVED:
				this.resolutionCode = ResolutionCode.SOLVED;
				break;
			case Command.RC_WORKAROUND:
				this.resolutionCode = ResolutionCode.WORKAROUND;
				break;
			case Command.RC_NOT_SOLVED:
				this.resolutionCode = ResolutionCode.NOT_SOLVED;
				break;
			case Command.RC_CALLER_CLOSED:
				this.resolutionCode = ResolutionCode.CALLER_CLOSED;
				break;

			default:
				this.resolutionCode = null;
				break;
			}
		}
	}

	/**
	 * Sets state.
	 * 
	 * @param state ticket state
	 */
	private void setState(String state) {
		
		if (state == null || "".equals(state)) {
			throw new IllegalArgumentException("Invalid state.");
		}
		
		switch (state) {
		
		case NEW_NAME :
			this.state = newState;
			break;
		case WORKING_NAME :
			this.state = workingState;
			break;
		case FEEDBACK_NAME :
			this.state = feedbackState;
			break;
		case RESOLVED_NAME :
			this.state = resolvedState;
			break;
		case CLOSED_NAME :
			this.state = closedState;
			break;
		case CANCELED_NAME :
			this.state = canceledState;
			break;
			
		default:
			break;
		}
	}

	/**
	 * Sets subject.
	 * 
	 * @param subject ticket subject
	 * @throws IllegalArgumentException if invalid subject
	 */
	private void setSubject(String subject) {
		
		// parameter error checking - null or empty string
		if (subject == null || "".equals(subject)) {
			throw new IllegalArgumentException("Invalid subject.");
		}
		
		this.subject = subject;
	}

	/**
	 * Sets ticket type.
	 * 
	 * @param ticketType ticket type
	 */
	private void setTicketType(String ticketType) {
		
		if (ticketType == null || "".equals(ticketType)) {
			throw new IllegalArgumentException("Invalid ticket type.");
		}
		
		switch (ticketType) {
		
		case TT_REQUEST :
			this.ticketType = TicketType.REQUEST;
			break;
		case TT_INCIDENT :
			this.ticketType = TicketType.INCIDENT;
			break;
			
		default:
			break;
		}
	}

	/**
	 * Updates the Ticket state.
	 * 
	 * @param command command to change ticket state
	 */
	public void update(Command command) {
		state.updateState(command);
	}

	/**
	 * Returns a string representation of a ticket appropriate for a file.
	 * 
	 * @return string representation of a ticket
	 */
	@Override
	public String toString() {
		
		String s1 = "*" + getTicketId() + "#" + getState() + "#" + getTicketTypeString() + "#" + getSubject() + "#" + getCaller() + "#" + getCategory() + "#" + getPriority() + "#" + getOwner() + "#";
		String s2 = "";
		
		switch (state.getStateName()) {
		
		case NEW_NAME :
			s2 = null;
			break;
		case WORKING_NAME :
			s2 = null;
			break;
		case FEEDBACK_NAME :
			s2 = getFeedbackCode();
			break;
		case RESOLVED_NAME :
			s2 = getResolutionCode();
			break;
		case CLOSED_NAME :
			s2 = null;
			break;
		case CANCELED_NAME :
			s2 = getCancellationCode();
			break;
			
		default:
			break;
		}
		
		String s3 = "\n" + getNotes();
		return s1 + s2 + s3;
	}

	
	
	/**
	 * Ticket state representing a new ticket.
	 * 
	 * @author Will Greene
	 */
	public class NewState implements TicketState {
		
		/**
		 * Updates the current state.
		 * 
		 * @param command command to change state to
		 */
		@Override
		public void updateState(Command command) {
			
			switch (command.getCommand()) {
			
			case PROCESS:
				state = workingState;
				owner = command.getOwnerId();
				notes.add(command.getNote());
				break;
			case REOPEN:
				throw new UnsupportedOperationException("");
			case FEEDBACK:
				throw new UnsupportedOperationException("");
			case RESOLVE:
				throw new UnsupportedOperationException("");
			case CONFIRM:
				throw new UnsupportedOperationException("");
			case CANCEL:
				state = canceledState;
				cancellationCode = command.getCancellationCode();
				notes.add(command.getNote());
				break;
			default:
				break;
			}
		}
		
		/**
		 * Returns state name.
		 * @return state name
		 */
		@Override
		public String getStateName() {
			return NEW_NAME;
		}
	}
	
	/**
	 * Ticket state representing a working ticket.
	 * 
	 * @author Will Greene
	 */
	public class WorkingState implements TicketState {
		
		/**
		 * Updates the current state.
		 * 
		 * @param command command to change state to
		 */
		@Override
		public void updateState(Command command) {
			
			switch (command.getCommand()) {
			
			case PROCESS:
				throw new UnsupportedOperationException("");
			case REOPEN:
				throw new UnsupportedOperationException("");
			case FEEDBACK:
				state = feedbackState;
				feedbackCode = command.getFeedbackCode();
				notes.add(command.getNote());
				break;
			case RESOLVE:
				state = resolvedState;
				resolutionCode = command.getResolutionCode();
				notes.add(command.getNote());
				break;
			case CONFIRM:
				throw new UnsupportedOperationException("");
			case CANCEL:
				state = canceledState;
				cancellationCode = command.getCancellationCode();
				notes.add(command.getNote());
				break;
			default:
				break;
			}
		}
		
		/**
		 * Returns state name.
		 * @return state name
		 */
		@Override
		public String getStateName() {
			return WORKING_NAME;
		}
	}
	
	/**
	 * Ticket state representing a feedback ticket.
	 * 
	 * @author Will Greene
	 */
	public class FeedbackState implements TicketState {
		
		/**
		 * Updates the current state.
		 * 
		 * @param command command to change state to
		 */
		@Override
		public void updateState(Command command) {
			
			switch (command.getCommand()) {
			
			case PROCESS:
				state = workingState;
				notes.add(command.getNote());
				break;
			case REOPEN:
				throw new UnsupportedOperationException("");
			case FEEDBACK:
				throw new UnsupportedOperationException("");
			case RESOLVE:
				if (command.getResolutionCode() == ResolutionCode.COMPLETED) {
					throw new UnsupportedOperationException("");
				}
				
				state = resolvedState;
				resolutionCode = command.getResolutionCode();
				notes.add(command.getNote());
				break;
			case CONFIRM:
				throw new UnsupportedOperationException("");
			case CANCEL:
				state = canceledState;
				cancellationCode = command.getCancellationCode();
				notes.add(command.getNote());
				break;
			default:
				break;
			}
		}
		
		/**
		 * Returns state name.
		 * @return state name
		 */
		@Override
		public String getStateName() {
			return FEEDBACK_NAME;
		}
	}
	
	/**
	 * Ticket state representing a resolved ticket.
	 * 
	 * @author Will Greene
	 */
	public class ResolvedState implements TicketState {
		
		/**
		 * Updates the current state.
		 * 
		 * @param command command to change state to
		 */
		@Override
		public void updateState(Command command) {
			
			switch (command.getCommand()) {
			
			case PROCESS:
				state = workingState;
				notes.add(command.getNote());
				break;
			case REOPEN:
				throw new UnsupportedOperationException("");
			case FEEDBACK:
				state = feedbackState;
				feedbackCode = command.getFeedbackCode();
				notes.add(command.getNote());
				break;
			case RESOLVE:
				throw new UnsupportedOperationException("");
			case CONFIRM:
				state = closedState;
				notes.add(command.getNote());
				break;
			case CANCEL:
				throw new UnsupportedOperationException("");
			default:
				break;
			}
		}
		
		/**
		 * Returns state name.
		 * @return state name
		 */
		@Override
		public String getStateName() {
			return RESOLVED_NAME;
		}
	}
	
	/**
	 * Ticket state representing a closed ticket.
	 * 
	 * @author Will Greene
	 */
	public class ClosedState implements TicketState {
		
		/**
		 * Updates the current state.
		 * 
		 * @param command command to change state to
		 */
		@Override
		public void updateState(Command command) {
			
			switch (command.getCommand()) {
			
			case PROCESS:
				throw new UnsupportedOperationException("");
			case REOPEN:
				state = workingState;
				notes.add(command.getNote());
				break;
			case FEEDBACK:
				throw new UnsupportedOperationException("");
			case RESOLVE:
				throw new UnsupportedOperationException("");
			case CONFIRM:
				throw new UnsupportedOperationException("");
			case CANCEL:
				throw new UnsupportedOperationException("");
			default:
				break;
			}
		}
		
		/**
		 * Returns state name.
		 * @return state name
		 */
		@Override
		public String getStateName() {
			return CLOSED_NAME;
		}
	}
	
	/**
	 * Ticket state representing a canceled ticket.
	 * 
	 * @author Will Greene
	 */
	public class CanceledState implements TicketState {
		
		/**
		 * Updates the current state.
		 * 
		 * @param command command to change state to
		 */
		@Override
		public void updateState(Command command) {
			
			switch (command.getCommand()) {
			
			case PROCESS:
				throw new UnsupportedOperationException("");
			case REOPEN:
				throw new UnsupportedOperationException("");
			case FEEDBACK:
				throw new UnsupportedOperationException("");
			case RESOLVE:
				throw new UnsupportedOperationException("");
			case CONFIRM:
				throw new UnsupportedOperationException("");
			case CANCEL:
				throw new UnsupportedOperationException("");
			default:
				break;
			}
		}
		
		/**
		 * Returns state name.
		 * @return state name
		 */
		@Override
		public String getStateName() {
			return CANCELED_NAME;
		}
	}
}
