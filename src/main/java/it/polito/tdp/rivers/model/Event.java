package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event> { //Evento è ingesso del flusso preso dal DB
	
	private LocalDate giorno ;
	private double qta; //quantità acqua coinvolta
	
	
	
	public Event(LocalDate giorno, double qta) {
		super();
		this.giorno = giorno;
		this.qta = qta;
	}


	public LocalDate getGiorno() {
		return giorno;
	}



	public void setGiorno(LocalDate giorno) {
		this.giorno = giorno;
	}



	public double getQta() {
		return qta;
	}



	public void setQta(double qta) {
		this.qta = qta;
	}



	@Override
	public int compareTo(Event other) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String toString() {
		return "Event [giorno=" + giorno + ", qta=" + qta + "]";
	}



	
	
	
	
	
	

}
