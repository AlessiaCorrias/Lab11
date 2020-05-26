package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulatore {

	// parametri simulazione
	double q;
	double fin;
	double foutm;
	double fout;
	float p; // probabilità del 5% di avere fout = 10*foutm -> ogni volta estraggo p,
				// se è < 0.05 aggiorno fout
	Random ran = new Random();

	// output
	int numGiorniNo;
	double cMed;
	double totc;
	double totg;

	// stato del sistema
	double c;

	// coda degli eventi
	private PriorityQueue<Event> queue;

	// INIZIALIZZAZIONE
	public void init(River fiume, double k) {
		double fmed = fiume.getFlowAvg();

		q = k * (fmed * 3600 * 24) * 30;

		c = q / 2;

		foutm = 0.8 * fmed;

		numGiorniNo = 0;
		cMed = 0;
		totc = 0;
		totg = fiume.getFlows().size();

		this.queue = new PriorityQueue<>();

		// riempimento coda eventi

		for (Flow f : fiume.getFlows()) {
			Event e = new Event(f.getDay(), f.getFlow());
			this.queue.add(e);
		}

	}

	public int getNumGiorniNo() {
		return numGiorniNo;
	}

	public double getcMed() {
		cMed = totc/totg;
		return cMed;
	}

	// ESECUZIONE
	public void run() {
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		
		fin = e.getQta();
		
		p=ran.nextFloat();
		
		if(p<0.05)
			fout = 10 * foutm;
		else
			fout = foutm;
		
		c+= fin-fout;
		
		if (c<0) { //non posso soddisfare richiesta
			c+=fout-fin;
			this.numGiorniNo++;
		}
		
		
		if(c>q) { //tracimazione
			c+=fout-fin; //torno a situazione di prima
		}
		
		//aggiorno somma c
		this.totc+=c;
		
		
		
		
		

	}

}
