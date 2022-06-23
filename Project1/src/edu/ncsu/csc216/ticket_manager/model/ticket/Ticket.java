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
	public static final String CANCELED_NAME = "Cancelled";
	
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
	private static int counter;
	/** ticket id */
	private int ticketId;
	/** ticket subject */
	private String subject;
	/** ticket caller */
	private String caller;
	/** ticket owner */
	private String owner;
	/** ticket note(s) */
	private ArrayList<String> notes;
	
	/**
	 * Increments the counter by 1.
	 */
	public static void incrementCounter() {
		counter++;
	}

	/**
	 * Sets the initial counter to 1.
	 * 
	 * @param number number to begin counting with
	 */
	public static void setCounter(int number) {
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
		
		// THROW IAE's !!!
	
		if (id > counter) {
			setCounter(id + 1);
		}
		
		setState(state);
		setTicketType(ticketType);
		setSubject(subject);
		setCaller(caller);
		setCategory(category);
		setPriority(priority);
		setOwner(owner);
	
		switch (code) {
		
		case Command.F_CALLER :
		case Command.F_CHANGE :
		case Command.F_PROVIDER :
			setFeedbackCode(code);
			break;
			
		case Command.RC_COMPLETED :
		case Command.RC_NOT_COMPLETED :
		case Command.RC_SOLVED :
		case Command.RC_WORKAROUND :
		case Command.RC_NOT_SOLVED :
		case Command.RC_CALLER_CLOSED :
			setResolutionCode(code);
			break;
			
		case Command.CC_DUPLICATE :
		case Command.CC_INAPPROPRIATE :
			setCancellationCode(code);
			break;
		
		default :
			break;
		}
	
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
		
		// not sure if this suffices
		notes.add(note);
		
		setOwner("");
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
			s += notes.get(i) + "\n";
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
		
//		switch (state) { //TODO use instance of?
//		
//		case newState :
//			return NEW_NAME;
//		case workingState :
//			return WORKING_NAME;
//		case feedbackState :
//			return FEEDBACK_NAME;
//		case resolvedState :
//			return RESOLVED_NAME;
//		case closedState :
//			return CLOSED_NAME;
//		case canceledState :
//			return CANCELED_NAME;
//			
//		default:
			return null;
//		}
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
	 */
	private void setCaller(String caller) {
		this.caller = caller;
	}

	/**
	 * Sets cancellation code.
	 * 
	 * @param cancellationCode ticket cancellation code
	 */
	private void setCancellationCode(String cancellationCode) {
		
		switch (cancellationCode) {
		
		case Command.CC_DUPLICATE :
			this.cancellationCode = CancellationCode.DUPLICATE;
		case Command.CC_INAPPROPRIATE :
			this.cancellationCode = CancellationCode.INAPPROPRIATE;
			
		default:
			break;
		}
	}

	/**
	 * Sets category.
	 * 
	 * @param category ticket category
	 */
	private void setCategory(String category) {
		
		switch (category) {
		
		case C_INQUIRY :
			this.category = Category.INQUIRY;
		case C_SOFTWARE :
			this.category = Category.SOFTWARE;
		case C_HARDWARE :
			this.category = Category.HARDWARE;
		case C_NETWORK :
			this.category = Category.NETWORK;
		case C_DATABASE :
			this.category = Category.DATABASE;
			
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
		
		switch (feedbackCode) {
		
		case Command.F_CALLER :
			this.feedbackCode = FeedbackCode.AWAITING_CALLER;
		case Command.F_CHANGE :
			this.feedbackCode = FeedbackCode.AWAITING_CHANGE;
		case Command.F_PROVIDER :
			this.feedbackCode = FeedbackCode.AWAITING_PROVIDER;
			
		default:
			break;
		}
	}

	/**
	 * Sets priority.
	 * 
	 * @param priority ticket priority
	 */
	private void setPriority(String priority) {
		
		switch (priority) {
		
		case P_URGENT :
			this.priority = Priority.URGENT;
		case P_HIGH :
			this.priority = Priority.HIGH;
		case P_MEDIUM :
			this.priority = Priority.MEDIUM;
		case P_LOW :
			this.priority = Priority.LOW;
			
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
		
		switch (resolutionCode) {
		
		case Command.RC_COMPLETED :
			this.resolutionCode = ResolutionCode.COMPLETED;
		case Command.RC_NOT_COMPLETED :
			this.resolutionCode = ResolutionCode.NOT_COMPLETED;
		case Command.RC_SOLVED :
			this.resolutionCode = ResolutionCode.SOLVED;
		case Command.RC_WORKAROUND :
			this.resolutionCode = ResolutionCode.WORKAROUND;
		case Command.RC_NOT_SOLVED :
			this.resolutionCode = ResolutionCode.NOT_SOLVED;
		case Command.RC_CALLER_CLOSED :
			this.resolutionCode = ResolutionCode.CALLER_CLOSED;
			
		default:
			break;
		}	
	}

	/**
	 * Sets state.
	 * 
	 * @param state ticket state
	 */
	private void setState(String state) {
		
		switch (state) {
		
		case NEW_NAME :
			this.state = newState;
		case WORKING_NAME :
			this.state = workingState;
		case FEEDBACK_NAME :
			this.state = feedbackState;
		case RESOLVED_NAME :
			this.state = resolvedState;
		case CLOSED_NAME :
			this.state = closedState;
		case CANCELED_NAME :
			this.state = canceledState;
			
		default:
			break;
		}
	}

	/**
	 * Sets subject.
	 * 
	 * @param subject ticket subject
	 */
	private void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Sets ticket type.
	 * 
	 * @param ticketType ticket type
	 */
	private void setTicketType(String ticketType) {
		
		switch (ticketType) {
		
		case TT_REQUEST :
			this.ticketType = TicketType.REQUEST;
		case TT_INCIDENT :
			this.ticketType = TicketType.INCIDENT;
			
		default:
			break;
		}
	}

	/**
	 * not sure.
	 * 
	 * @param command
	 */
	public void update(Command command) {
		
		state.updateState(command);
		
		// add "non-null" notes?
		
//		switch (command.getCommandValue()) {
//		
//		case PROCESS:
//			state = workingState;
//			owner = command.getOwnerId();
//			break;
//		case REOPEN:
//			state = workingState;
//			break;
//		case FEEDBACK:
//			state = feedbackState;
//			break;
//		case RESOLVE:
//			state = resolvedState;
//			break;
//		case CONFIRM:
//			state = closedState;
//			break;
//		case CANCEL:
//			state = canceledState;
//			break;
//			
//		default:
//			break;
//		}
	}

	/**
	 * Returns a string representation of a ticket appropriate for a file.
	 * 
	 * @return string representation of a ticket
	 */
	@Override
	public String toString() {
		
		String s1 = getTicketId() + "#" + getState() + "#" + getTicketType() + "#" + getSubject() + "#" + getCaller() + "#" + getCategory() + "#" + getPriority() + "#" + getOwner() + "#";
		String s2 = "";
		
		switch (state.getStateName()) {
		
		case NEW_NAME :
			break;
		case WORKING_NAME :
			break;
		case FEEDBACK_NAME :
			s2 = getFeedbackCode();
		case RESOLVED_NAME :
			s2 = getResolutionCode();
		case CLOSED_NAME :
			break;
		case CANCELED_NAME :
			s2 = getCancellationCode();
		}
		
		String s3 = getNotes();
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
				owner = null; //TODO
				break;
			case REOPEN:
				state = workingState;
				break;
			case FEEDBACK:
				state = feedbackState;
				break;
			case RESOLVE:
				state = resolvedState;
				break;
			case CONFIRM:
				state = closedState;
				break;
			case CANCEL:
				state = canceledState;
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
	 * Ticket state representing a new ticket.
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
			
//			switch (command.getCommandValue()) {
//			
//			case PROCESS:
//				state = workingState;
//				owner = null; //TODO
//				break;
//			case REOPEN:
//				state = workingState;
//				break;
//			case FEEDBACK:
//				state = feedbackState;
//				break;
//			case RESOLVE:
//				state = resolvedState;
//				break;
//			case CONFIRM:
//				state = closedState;
//				break;
//			case CANCEL:
//				state = canceledState;
//				break;
//			default:
//				break;
//			}
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
	 * Ticket state representing a new ticket.
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
			
//			switch (command.getCommandValue()) {
//			
//			case PROCESS:
//				state = workingState;
//				owner = null; //TODO
//				break;
//			case REOPEN:
//				state = workingState;
//				break;
//			case FEEDBACK:
//				state = feedbackState;
//				break;
//			case RESOLVE:
//				state = resolvedState;
//				break;
//			case CONFIRM:
//				state = closedState;
//				break;
//			case CANCEL:
//				state = canceledState;
//				break;
//			default:
//				break;
//			}
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
	 * Ticket state representing a new ticket.
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
			
//			switch (command.getCommandValue()) {
//			
//			case PROCESS:
//				state = workingState;
//				owner = null; //TODO
//				break;
//			case REOPEN:
//				state = workingState;
//				break;
//			case FEEDBACK:
//				state = feedbackState;
//				break;
//			case RESOLVE:
//				state = resolvedState;
//				break;
//			case CONFIRM:
//				state = closedState;
//				break;
//			case CANCEL:
//				state = canceledState;
//				break;
//			default:
//				break;
//			}
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
	 * Ticket state representing a new ticket.
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
			
//			switch (command.getCommandValue()) {
//			
//			case PROCESS:
//				state = workingState;
//				owner = null; //TODO
//				break;
//			case REOPEN:
//				state = workingState;
//				break;
//			case FEEDBACK:
//				state = feedbackState;
//				break;
//			case RESOLVE:
//				state = resolvedState;
//				break;
//			case CONFIRM:
//				state = closedState;
//				break;
//			case CANCEL:
//				state = canceledState;
//				break;
//			default:
//				break;
//			}
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
	 * Ticket state representing a new ticket.
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
			
//			switch (command.getCommandValue()) {
//			
//			case PROCESS:
//				state = workingState;
//				owner = null; //TODO
//				break;
//			case REOPEN:
//				state = workingState;
//				break;
//			case FEEDBACK:
//				state = feedbackState;
//				break;
//			case RESOLVE:
//				state = resolvedState;
//				break;
//			case CONFIRM:
//				state = closedState;
//				break;
//			case CANCEL:
//				state = canceledState;
//				break;
//			default:
//				break;
//			}
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
}
