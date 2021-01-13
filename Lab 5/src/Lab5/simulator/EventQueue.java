package Lab5.simulator;

/**
 * 
 * @author johlax-8 A queue that keeps track of general events from the class
 *         "Event" in this projekt
 *
 */

public class EventQueue {

	private class Node {
		private Event event;
		private int ID;
		private Node next;

		Node(Event event, int ID) {
			this.event = event;
			this.ID = ID;

		}

		Node(Event event) {
			this.event = event;
		}

	}

	private Node header;
	private Node last;

	/**
	 * Creates a header node with an invalid index
	 */
	public EventQueue() {
		header = new Node(null, -1);
		header.next = null;
		this.last = header;
	}

	/**
	 * Lï¿½gger till ett event i kï¿½n, sorteras automatiskt
	 * 
	 * @param event Eventen som skall lï¿½ggas kï¿½n
	 * @param ID    Vilken kund eventet ï¿½r kopplat till
	 */
	public void addEvent(Event event, int ID) {

		Node current = header;
		Node prev = null;

		while (true) {
			// nullcheck
			if (current.next == null) {
				current.next = new Node(event, ID);
				last = current.next;
				return;
			}

			// flyttar pekarna ett steg frammåt
			prev = current;
			current = current.next;

			// jämför tiderna
			if (event.getTimeStamp() < current.event.getTimeStamp()) {
				prev.next = new Node(event, ID);
				prev.next.next = current;
				return;
			}

		}

	}

	public void addEvent(Event event) {

		Node current = header;
		Node prev = null;

		while (true) {
			// nullcheck
			if (current.next == null) {
				current.next = new Node(event);
				last = current.next;

				return;
			}

			// flyttar pekarna ett steg frammåt
			prev = current;
			current = current.next;

			// jämför tiderna
			if (event.getTimeStamp() < current.event.getTimeStamp()) {

				prev.next = new Node(event);
				prev.next.next = current;
				return;
			}

		}

	}

	/**
	 * 
	 * @return Kund-ID för nästa event
	 */
	public int getID() {
		try {
			return header.next.ID;
		} catch (NullPointerException e) {
			return 0;
		}
	}

	/**
	 * Bör kanske inte använda denna method, lite bökig
	 * 
	 * @param index det indexet i kön man vill nå
	 * @return en Node med event, och möjligen ID
	 */
	public Node getEvent(int index) {
		Node node = header;
		Node pointer = header.next;
		for (int i = index; i > 0; i--) {
			if (node == null) {
				throw new IndexOutOfBoundsException();
			}
			node = pointer;
			pointer = pointer.next;
		}
		return node;
	}

	/**
	 * Returnerar och tar bort nästa event i kön
	 * 
	 * @return Nästa event i kön, returnerar null ifall kön är tom
	 */
	public Event getNextEvent() throws NullPointerException{
		
		Event next = header.next.event;
		removeNext();
		return next;
	}

	private void removeNext() {
		try {
			header.next = header.next.next;
		} catch (NullPointerException e) {
			return;
		}
	}
	
	public boolean isNextLast() {
		return header.next == last;
	}

}
