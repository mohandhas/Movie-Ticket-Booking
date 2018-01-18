package com.mtr.pojo;

public class SendBookedTickets {
	public String ticketNumber;

	
	public SendBookedTickets() {
		super();
	}

	public SendBookedTickets(String ticketNumber) {
		super();
		this.ticketNumber = ticketNumber;
	}

	@Override
	public String toString() {
		return "SendBookedTickets [ticketNumber=" + ticketNumber + "]";
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	
	
}
